package tp.pr5.instructions;

import tp.pr5.NavigationModule;
import tp.pr5.RobotEngine;
import tp.pr5.instructions.exceptions.*;
import tp.pr5.items.ItemContainer;

/**
 * Su ejecucion pedir al robot para terminar la simulación Esta instruccion
 * funciona si el usuario escribe SALIR o EXIT
 * 
 * @author Eddy Cuizaguana Cerpa
 * 
 */
public class QuitInstruction implements Instruction {

	private RobotEngine robotEngine;

	/**
	 * Constructor por defecto
	 */
	public QuitInstruction() {

	}

	/**
	 * Analizadores de la cadena de devolver una instancia de QuitInstruction o
	 * lanzar una WrongInstructionFormatException ()
	 * 
	 * @param cad
	 *            : String texto para analizar
	 * @return : Instrucción referencia a una instancia de QuitInstruction
	 * @throws WrongInstructionFormatException
	 *             : Si la cadena no se SALIR | EXIT
	 */
	public Instruction parse(String cad) throws WrongInstructionFormatException {
		String[] words = cad.split(" ");
		if ((words[0].equalsIgnoreCase("QUIT") || words[0]
				.equalsIgnoreCase("SALIR")) && words.length == 1)
			return new QuitInstruction();
		else
			throw new WrongInstructionFormatException(
					"WALLE�E says: I do not understand. Please repeat");
	}

	/**
	 * Solicitar el robot para detener la simulacion
	 * 
	 * @throws InstructionExecutionException
	 *             : si no existe ningún error de ejecución.
	 */
	public void execute() throws InstructionExecutionException {
		this.robotEngine.requestQuit();
	}

	/**
	 * Devuelve una descripción de la sintaxis de instrucciones. La cadena no
	 * termina con el separador de línea. Corresponde a la persona que llama de
	 * annadir antes de la impresion.
	 * 
	 * @return la sintaxis de instrucciones QUIT | SALIR
	 */
	public String getHelp() {
		return "     QUIT|SALIR";
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
		this.robotEngine = engine;
	}
}
