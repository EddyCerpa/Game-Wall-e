package tp.pr5.instructions;

import tp.pr5.NavigationModule;
import tp.pr5.RobotEngine;
import tp.pr5.instructions.exceptions.*;
import tp.pr5.items.ItemContainer;

/**
 * Su ejecucion mueve al robot de un lugar al siguiente en una corriente
 * direccion. Esta instrucción funciona si el usuario escribe MOVE o MOVER
 * 
 * @author Eddy Cuizaguana Cerpa
 * 
 */
public class MoveInstruction implements Instruction {

	private NavigationModule navigationModule;
	private RobotEngine robot;

	/**
	 * Analiza el string devolviendo una instancia MoveInstruction o lanzar una
	 * WrongInstructionFormatException ()
	 * 
	 * @param cad
	 *            : Cadena de texto para analizar.
	 * @return : Instrucción referencia a una instancia de MoveInstruction
	 * @throws WrongInstructionFormatException
	 *             : Cuando la cadena no sea MOVER.
	 */
	public Instruction parse(String cad) throws WrongInstructionFormatException {
		String[] words = cad.split(" ");
		Instruction inst;
		if (words.length == 1 && (words[0].equalsIgnoreCase("MOVE"))
				|| (words[0].equalsIgnoreCase("MOVER")))
			inst = new MoveInstruction();
		else
			throw new WrongInstructionFormatException(
					"WALL·E says: I do not understand. Please repeat");
		return inst;
	}

	/**
	 * Se mueve de un lugar actual hasta el siguiente lugar en la dirección
	 * actual. Debe existir una calle abierta entre los dos lugares que se
	 * desean mover
	 * 
	 * @throws InstructionExecutionException
	 *             : Cuando el robot no puede ir a otro lugar (hay un muro, una
	 *             calle cerrada ...)
	 */
	public void execute() throws InstructionExecutionException {
			
			this.navigationModule.move();
			this.robot.addFuel(-5);
			//this.robot.isOver(); 
	}

	/**
	 * Devuelve una descripción de la sintaxis de instrucciones. La cadena no
	 * termina con el separador de línea.
	 * 
	 * @return : la sintaxis del comando de MOVE | MOVER.
	 */
	public String getHelp() {
		return "    MOVE |MOVER";
	}

	/**
	 * Establecer el contexto de ejecución. El método recibe todo el motor
	 * (motor, la navegación y el contenedor de robot) even though the actual
	 * implementation of execute() may not require it.
	 * 
	 * @param engine
	 *            : The robot engine
	 * @param navigation
	 *            : La información sobre el juego, es decir, los lugares,
	 *            dirección actual y el rumbo actual de navegar
	 * @param robotContainer
	 *            : El inventario del robot.
	 */
	public void configureContext(RobotEngine engine,
			NavigationModule navigation, ItemContainer robotContainer) {
		this.navigationModule = navigation;
		this.robot = engine;

	}

}
