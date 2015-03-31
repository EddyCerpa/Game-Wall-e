package tp.pr5.instructions;

import tp.pr5.NavigationModule;
import tp.pr5.RobotEngine;
import tp.pr5.instructions.exceptions.*;
import tp.pr5.items.ItemContainer;

/**
 * La ejecucion de esta instruccion muestra la informacion del inventario del
 * robot o la descripcion completa sobre el elemento con id identificador
 * incluidos en el inventario presente la instruccion funciona si el jugador
 * escribe SCAN o ESCANEAR (el id no es obligatorio)
 * 
 * @author Eddy Cuizaguana Cerpa
 * 
 */
public class ScanInstruction implements Instruction {

	private ItemContainer itemContainer;
	String id;

	/**
	 * Constructor por defecto
	 */
	public ScanInstruction() {
	}

	/**
	 * Construsctor con un argumento
	 * 
	 * @param string
	 *            : ide del objeto.
	 */
	public ScanInstruction(String string) {
		this.id = string;
	}

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
		if (words[0].equalsIgnoreCase("SCAN")
				|| words[0].equalsIgnoreCase("ESCANEAR") && !(words.length > 2)) {
			if (words.length == 1)
				return new ScanInstruction();
			else
				return new ScanInstruction(words[1]);
		} else
			throw new WrongInstructionFormatException(
					"WALL路E says: I do not understand. Please repeat");
	}

	/**
	 * Imprime la descripcion de un elemento o varios que contiene en el inventario
	 * mandaremos a "itemContainer.informarObseravScanTodo" :
	 * <0> si el numero de items
	 * @throws InstructionExecutionException
	 *             : Cuando el robot no contiene el material que va a escanear
	 */
	public void execute() throws InstructionExecutionException {
		// hacemos una or paraluego en el metodo containsItem se a馻lice todas las posibilidades
		//no lo hacemos aqui por que nos obligan a lanzar una exepcion
		if (this.id == null  || itemContainer.containsItem(id)) 
			itemContainer.informarObseravScan(id);			
		else
			throw new InstructionExecutionException(
					"WALL路E says: I have not such object");
	}

	/**
	 * Devuelve una descripci贸n de la sintaxis de instrucciones. La cadena no
	 * termina con el separador de l铆nea. Corresponde a la persona que llama de
	 * annadir antes de la impresion.
	 * 
	 * @return la sintaxis de instrucciones SCAN | ESCANEAR [id]
	 */
	public String getHelp() {
		return "     SCAN | ESCANEAR [id]";
	}

	/**
	 * Establecer el contexto de ejecuci贸n. El metodo recibe todo el motor
	 * (motor, la navegaci贸n y el contenedor de robot) a pesar de que la
	 * aplicacion efectiva de ejecutar () no lo requiera.
	 * 
	 * @param engine
	 *            : El motor de robot
	 * @param navigation
	 *            : La informaci贸n sobre el juego, es decir, los lugares,
	 *            direccion actual y el rumbo actual de navegar
	 * @param robotContainer
	 *            : El inventario del robot
	 */
	public void configureContext(RobotEngine engine,
			NavigationModule navigation, ItemContainer robotContainer) {
		this.itemContainer = robotContainer;
	

	}
}
