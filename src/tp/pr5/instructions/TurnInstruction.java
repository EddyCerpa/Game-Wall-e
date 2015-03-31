package tp.pr5.instructions;

import tp.pr5.NavigationModule;
import tp.pr5.RobotEngine;
import tp.pr5.Rotation;
import tp.pr5.instructions.exceptions.*;
import tp.pr5.items.ItemContainer;

/**
 * Su ejecución gira el robot. La Instrucción funciona se escribe TURN LEFT o
 * RIGHT o GIRAR LEFT o RIGHT
 * 
 * @author Eddy Cuizaguana Cerpa
 * 
 */
public class TurnInstruction implements Instruction {

	private NavigationModule navigationModule;
	private RobotEngine robotEngine;
	@SuppressWarnings("unused")
	private String rot;
	private Rotation rotation;

	/**
	 * osntructor por defecto
	 */
	public TurnInstruction() {

	}

	/**
	 * Costructor con un argumento
	 * 
	 * @param rot
	 *            : String que indica si es derecha o izaquierda.
	 */
	public TurnInstruction(Rotation rot) {
		this.rotation = rot;
	}

	/**
	 * Analizadores de la cadena de devolver una instancia de QuitInstruction o
	 * lanzar una WrongInstructionFormatException ()
	 * 
	 * @param cad
	 *            : String texto para analizar
	 * @return : Instrucción referencia a una instancia de QuitInstruction
	 * @throws WrongInstructionFormatException
	 *             : Si la cadena no se TURN LEFT o RIGHT o GIRAR LEFT o RIGHT
	 */
	public Instruction parse(String cad) throws WrongInstructionFormatException {
		String[] words = cad.split(" ");
		if ((words[0].equalsIgnoreCase("TURN") || words[0].equalsIgnoreCase("GIRAR")) && words.length == 2) {
			if (words[1].equalsIgnoreCase("RIGHT")) 
				return new TurnInstruction(Rotation.RIGHT);
			else if (words[1].equalsIgnoreCase("LEFT")) 
				return new TurnInstruction(Rotation.LEFT);
			else
				throw new WrongInstructionFormatException(
						"WALL·E says: I do not understand. Please repeat");
			
		} 
		else
			throw new WrongInstructionFormatException(
					"WALL·E says: I do not understand. Please repeat");
	}

	/**
	 * Gira el robot hacia la izquierda o derecha
	 * 
	 * @throws InstructionExecutionException
	 *             : Cuando la rotación es desconocida
	 */
	public void execute() throws InstructionExecutionException {

		if (this.rotation != Rotation.UNKNOWN) {
			this.navigationModule.rotate(this.rotation);
			this.robotEngine.addFuel(-5);
			// informa  alos observadores de una rotacion y de decrentar el combustible
			this.navigationModule.informaObservadoresDirection();
		} else
			throw new InstructionExecutionException(
					"WALL·E says: I do not understand. Please repeat ");

	}

	/**
	 * Devuelve una descripción de la sintaxis de instrucciones. La cadena no
	 * termina con el separador de línea. Corresponde a la persona que llama de
	 * annadir antes de la impresion.
	 * 
	 * @return la sintaxis de instrucciones TURN | GIRAR < LEFT|RIGHT >
	 */
	public String getHelp() {
		return "     TURN | GIRAR < LEFT|RIGHT >";

	}

	/**
	 * Establecer el contexto de ejecución. El metodo recibe todo el motor
	 * (motor, la navegación y el contenedor de robot) a pesar de que la
	 * aplicacion efectiva de ejecutar () no lo requiera.
	 * 
	 * @param engine
	 *            : El motor de robot
	 * @param navigation
	 *            : La información sobre el juego, es decir, los lugares,
	 *            direccion actual y el rumbo actual de navegar
	 * @param robotContainer
	 *            : El inventario del robot
	 */
	public void configureContext(RobotEngine engine,
			NavigationModule navigation, ItemContainer robotContainer) {
		this.navigationModule = navigation;
		this.robotEngine = engine;
	}
}
