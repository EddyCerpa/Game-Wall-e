package tp.pr5.instructions;

import tp.pr5.NavigationModule;
import tp.pr5.RobotEngine;
import tp.pr5.instructions.exceptions.*;
import tp.pr5.items.ItemContainer;

/**
 * Muestra la ayuda del juego con todas las instrucciones que el robot pueda
 * ejecutar. Esta instrucción funciona si el usuario escribe o HELP AYUDA
 * 
 * @author Eddy Cuizaguana Cerpa
 * 
 */
public class HelpInstruction implements Instruction {

	private RobotEngine robotEngine;

	/**
	 * Analiza el string devolviendo una instancia HelpInstruction o lanzar una
	 * WrongInstructionFormatException ()
	 * 
	 * @param cad
	 *            : Cadena de texto para analizar
	 * @return : Instrucción referencia a una instancia de HelpInstruction.
	 * @throws WrongInstructionFormatException
	 *             : Cuando la cadena no AYUDA
	 */
	public Instruction parse(String cad) throws WrongInstructionFormatException {
		String[] words = cad.split(" ");
		Instruction tmp;

		if (words.length == 1 && (words[0].equalsIgnoreCase("HELP"))
				|| (words[0].equalsIgnoreCase("AYUDA")))
			tmp = new HelpInstruction();
		else
			throw new WrongInstructionFormatException(
					"WALL·E says: I do not understand. Please repeat");
		return tmp;
	}

	/**
	 * Imprime el string de ayuda de cada instrucción. Delega a la clase de
	 * interpretación.
	 * 
	 * @throws InstructionExecutionException
	 *             : si existe algun error de ejecucion
	 */
	public void execute() throws InstructionExecutionException {
		this.robotEngine.requestHelp();
	}

	/**
	 * Ayuda sintaxis
	 * 
	 * @return : the instruction syntax HELP
	 */
	public String getHelp() {
		return "     HELP|AYUDA";
	}

	/**
	 * Configuración del contexto de esta instrucción
	 * 
	 * @param engine
	 *            : The robot engine
	 * @param navigation
	 *            : La informacion sobre el juego, es decir, los lugares,
	 *            dirección actual y el rumbo actual de navegar
	 * @param robotContainer
	 *            : The inventory of the robot.
	 */
	public void configureContext(RobotEngine engine,
			NavigationModule navigation, ItemContainer robotContainer) {

		this.robotEngine = engine;

	}

}
