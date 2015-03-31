package tp.pr5.instructions;

import tp.pr5.NavigationModule;
import tp.pr5.RobotEngine;
import tp.pr5.instructions.exceptions.InstructionExecutionException;
import tp.pr5.instructions.exceptions.WrongInstructionFormatException;
import tp.pr5.items.Item;
import tp.pr5.items.ItemContainer;

/**
 * Esta instrucción suelta un elemento desde el lugar actual y la coloca el
 * inventario robot. Esta instrucción funciona si el usuario escribe DROP o
 * soltar
 * 
 * @author Eddy Cuizaguana Cerpa
 * 
 */
public class DropInstruction implements Instruction {

	private NavigationModule navigationModule;
	private ItemContainer itemContainer;
	private String id;

	public DropInstruction() {}

	public DropInstruction(String ide) {
		this.id = ide;
	}

	/**
	 * Analiza el string retornando una instancia de DropInstruction o lanza un
	 * WrongInstructionFormatException ()
	 * 
	 * @param cad
	 *            : Cadena de texto para analizar
	 * @return : Instrucción referencia a una instancia de DropInstruction
	 * @throws WrongInstructionFormatException
	 *             : Cuando la cadena no es DROP (id)
	 */
	public Instruction parse(String cad) throws WrongInstructionFormatException {

		String[] words = cad.split(" ");
		if (words.length == 2
				&& (words[0].equalsIgnoreCase("DROP") || words[0]
						.equalsIgnoreCase("SOLTAR"))) {
			return new DropInstruction(words[1]);
		} else
			throw new WrongInstructionFormatException(
					"WALL·E says: I do not understand. Please repeat");
	}

	/**
	 * El robot suelta de un elemento de su inventario en el lugar actual, si
	 * existe el elemento
	 * 
	 * @throws InstructionExecutionException
	 *             : Cuando el inventario robot no contiene un elemento con este
	 *             nombre o cuando hay otro elemento con el mismo ID en el lugar
	 *             actual
	 */
	public void execute() throws InstructionExecutionException {
		// si existe en el contenedor de walle
		boolean inv = this.itemContainer.containsItem(this.id);
		// si existe en el lugar en el q esta walle
		boolean lug = this.navigationModule.findItemAtCurrentPlace(this.id);
		if (lug)
			throw new InstructionExecutionException(
					id + "WALL�E> Existe en el lugar ");
		else if (inv && !lug) {
			itemContainer.informarObseravDeleteElemTabla(id); // muestra que soltado  un elemnto
			Item item = this.itemContainer.pickItem(this.id);
			// eliminamos el elemontio de la mochila de walle
			this.navigationModule.dropItemAtCurrentPlace(item);
			// informamos a las vista-----
			navigationModule.informarObservadoresCambioLog();// actualizamos el log
		} else
			throw new InstructionExecutionException("You do not have any "
					+ this.id + ".");
	}

	/**
	 * Instruccion de ayuda
	 * 
	 * @return : la sintaxis de la instrucción DROP (ID)
	 */
	public String getHelp() {
		return "     DROP|SOLTAR <id>";
	}

	/**
	 * Fija el contexto actual, es decir, el motor de robot y el módulo de
	 * navegación
	 * 
	 * @param engine
	 *            : El motor de robot
	 * @param navigation
	 *            : La información sobre el juego, es decir, los lugares,
	 *            dirección actual y el rumbo actual de navegador
	 * @param robotContainer
	 *            : El inventario del robot.
	 */
	public void configureContext(RobotEngine engine,
			NavigationModule navigation, ItemContainer robotContainer) {

		this.navigationModule = navigation;
		this.itemContainer = robotContainer;
	}

}
