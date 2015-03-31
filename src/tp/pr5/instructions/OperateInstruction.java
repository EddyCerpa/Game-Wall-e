package tp.pr5.instructions;

import tp.pr5.NavigationModule;
import tp.pr5.RobotEngine;
import tp.pr5.instructions.exceptions.InstructionExecutionException;
import tp.pr5.instructions.exceptions.WrongInstructionFormatException;
import tp.pr5.items.Item;
import tp.pr5.items.ItemContainer;

/**
 * La instrucción para el uso de un elemento. Esta instrucción funciona si el
 * usuario escribe OPERATE id o OPERAR
 * 
 * @author Eddy Cuizaguana Cerpa
 * 
 */
public class OperateInstruction implements Instruction {

	private RobotEngine robotEngine;
	private NavigationModule navigationModule;
	private ItemContainer itemContainer;
	private String id;

	public OperateInstruction() {}

	public OperateInstruction(String id) {
		this.id = id;
	}

	/**
	 * Parses the String returning an instance of OperateInstruction or throwing
	 * a WrongInstructionFormatException()
	 * 
	 * @param cad
	 *            : Cadena de texto para analizar
	 * @return : Instrucción referencia a una instancia de OperateInstruction.
	 * @throws WrongInstructionFormatException
	 *             : Cuando el string no es OPERAR | OPERAR (id)
	 */
	public Instruction parse(String cad) throws WrongInstructionFormatException {
		String[] words = cad.split(" ");
		Instruction ins;

		if (words.length == 2
				&& ((words[0].equalsIgnoreCase("OPERATE")) || (words[0]
						.equalsIgnoreCase("OPERAR"))))

			ins = new OperateInstruction(words[1]);

		else
			throw new WrongInstructionFormatException(
					"WALL·E says: I do not understand. Please repeat");
		return ins;
	}

	/**
	 * El robot utiliza el elemento solicitado.
	 * 
	 * @throws InstructionExecutionException
	 *             : Cuando el inventario robot no contiene un elemento con este
	 *             nombre, o cuando el elemento no se puede utilizar. El robot
	 *             utiliza el elemento solicitado.
	 */
	public void execute() throws InstructionExecutionException {

		Item it = this.itemContainer.getItem(id);
		if (it != null) {
				if (it.use(this.robotEngine, this.navigationModule)) { // utiliza el item si puede ser utilizado
					this.itemContainer.useItem(it); // elimina el item del contenedor y avisa a los observadores
				} 
				else //if (!it.seGasta())  // no se puede usar y existe en el inventario =  error con la tarjeta
					throw new InstructionExecutionException(
							"la targeta no se corresponde con la calle");
		} else
				throw new InstructionExecutionException(
						"WALLE�E says: I have problems using the object " + id);
	}

	/**
	 * Devuelve una descripción de la sintaxis de instrucciones. La cadena no
	 * termina con el separador de línea. It is up to the caller adding it
	 * before printing.
	 * 
	 * @return : la sintaxis de la instrucción OPERATE|OPERAR <ID>
	 */
	public String getHelp() {
		return "     OPERATE|OPERAR <ID>";
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
	 *            : El inventario del robot
	 */
	public void configureContext(RobotEngine engine,
			NavigationModule navigation, ItemContainer robotContainer) {

		this.robotEngine = engine;
		this.navigationModule = navigation;
		this.itemContainer = robotContainer;

	}

}
