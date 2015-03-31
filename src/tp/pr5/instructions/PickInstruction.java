package tp.pr5.instructions;

import tp.pr5.NavigationModule;
import tp.pr5.RobotEngine;
import tp.pr5.instructions.exceptions.*;
import tp.pr5.items.Item;
import tp.pr5.items.ItemContainer;

/**
 * Esta instruccion trata de elegir un tema, en el lugar actual y la coloca el
 * inventario robot. Esta instrucción funciona si el usuario escribe RECOGER id
 * id o coger
 * 
 * @author Eddy Cuizaguana Cerpa
 * 
 */
public class PickInstruction implements Instruction {

	private NavigationModule navigationModule;
	private ItemContainer itemContainer;
	private String id;
	
	/**
	 * Constructor por defecto
	 */

	public PickInstruction() {}

	/**
	 * Cosntructor con un argumento
	 * 
	 * @param ide
	 *            : identificador del objeto
	 */
	public PickInstruction(String ide) {
		this.id = ide;
	}

	/**
	 * Analiza la cadena devolver una instancia de PickInstruction o lanzar una
	 * WrongInstructionFormatException ()
	 * 
	 * @param cad
	 *            : Texto para analizar
	 */
	public Instruction parse(String cad) throws WrongInstructionFormatException {

		String[] words = cad.split(" ");
		if ((words[0].equalsIgnoreCase("PICK") || words[0]
				.equalsIgnoreCase("COGER")) && words.length == 2)
			return new PickInstruction(words[1]);

		else
			throw new WrongInstructionFormatException(
					"WALL�E says: I do not understand. Please repeat");
	}

	/**
	 * El robot agrega un elemento a su inventario desde el lugar actual, si
	 * existe
	 * 
	 * @throws InstructionExecutionException
	 *             : Cuando el lugar no contiene un elemento con este nombre o
	 *             cuando hay otro elemento con el mismo ID en el inventario
	 *             robot
	 */
	public void execute() throws InstructionExecutionException {
		// existe en el lugar
		boolean lug = this.navigationModule.findItemAtCurrentPlace(this.id);
		// existe en el lugar
		boolean inv = this.itemContainer.containsItem(this.id);
		if (lug && !inv) {
			Item it = this.navigationModule
						.pickItemFromCurrentPlace(this.id);
			this.itemContainer.addItem(it);
			
			//-----se invoca a estos metodos que informaran a los observadores
			itemContainer.informarObseravAddElemtoTabla(id);	
			navigationModule.informarObservadoresCambioLog();
			//------------------------------------------------------
		} 
		else if (!lug){//no existe en el lugar y quien sabe que en el inventario
			throw new InstructionExecutionException(
					"WALL�E says: Ooops, this place has not the object "
								+ this.id);
		}
		else if  (lug && inv){
			throw new InstructionExecutionException("I am stupid! I had already the object " + this.id);
		}

	}

	/**
	 * Devuelve una descripcion de la sintaxis de instrucciones. La cadena no
	 * termina con el separador de línea. Corresponde a la persona que llama de
	 * annadir antes de la impresion.
	 * 
	 * @return : la sintaxis de comandos SELECCIÓN | Coger <id>
	 */
	public String getHelp() {
		return "     PICK|COGER <id>";
	}

	/**
	 * Configura el contexto
	 * 
	 * @param engine
	 *            : El motor de robot
	 * @param navigation
	 *            : La informacion sobre el juego, es decir, los lugares,
	 *            direccion actual y el rumbo actual de navegar
	 * @param robotContainer
	 *            : El inventario del robot
	 */
	public void configureContext(RobotEngine engine,
			NavigationModule navigation, ItemContainer robotContainer) {

		this.navigationModule = navigation;
		this.itemContainer = robotContainer;
	}

}
