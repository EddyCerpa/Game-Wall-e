package tp.pr5.instructions;

import tp.pr5.NavigationModule;
import tp.pr5.RobotEngine;
import tp.pr5.instructions.exceptions.*;
import tp.pr5.items.ItemContainer;

/**
 * La presente Instruccion muestra la descripción del lugar actual y los
 * elementos que contiene. Esta instrucción funciona si el usuario escribe RADAR
 * 
 * @author Eddy Cuizaguana Cerpa
 * 
 */
public class RadarInstruction implements Instruction {

	private NavigationModule navigationModule;

	/**
	 * Analiza la cadena devolver una instancia de RadarInstruction o lanzar una
	 * WrongInstructionFormatException ()
	 * 
	 * @param cad
	 *            : String texto para analizar
	 * @return : Instruccion referencia a una instancia de RadarInstruction
	 * @throws WrongInstructionFormatException
	 *             : Si la cadena no se RADAR.
	 */
	public Instruction parse(String cad) throws WrongInstructionFormatException {
		String[] words = cad.split(" ");
		if (words.length == 1 && words[0].equalsIgnoreCase("RADAR"))
			return new RadarInstruction();
		else
			throw new WrongInstructionFormatException(
					"WALL·E says: I do not understand. Please repeat");
	}

	/**
	 * Muestra la descripcion del lugar actual
	 * 
	 * @throws InstructionExecutionException
	 *             :si existe un error de ejecucion.
	 */
	public void execute() throws InstructionExecutionException {
		navigationModule.informarObservadoresRadar();
	}

	/**
	 * Devuelve una descripción de la sintaxis de instrucciones. La cadena no
	 * termina con el separador de línea. Corresponde a la persona que llama de
	 * annadir antes de la impresion.
	 * 
	 * @return la sintaxis de instrucciones RADAR
	 */
	public String getHelp() {
		return "     RADAR";
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
	}
}
