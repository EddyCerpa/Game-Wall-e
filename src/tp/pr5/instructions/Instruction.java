package tp.pr5.instructions;

import tp.pr5.NavigationModule;
import tp.pr5.RobotEngine;
import tp.pr5.instructions.exceptions.*;
import tp.pr5.items.ItemContainer;

/**
 * Esta interfaz representa una instrucción compatible con la aplicación. Cada
 * instrucción que el robot puede realizar implementa esta interfaz. Obliga
 * instrucciones para proporcionar a la aplicación de cuatro métodos diferentes:
 * Método Parse: intenta analizar una cadena con la información de la
 * instrucción de la clase que representa Metodo Ayuda: devuelve una cadena con
 * una explicación de la forma de expresión que el método de parse supports.
 * Método ConfigureContext: establece el marco necesario para ejecutar la
 * instrucción MétodoExecute: realiza el trabajo real de la instrucción, la
 * ejecución de la misma. El método de ejecución no tiene ningún parámetro. Por
 * lo tanto, el contexto de ejecución debe ser dado al objeto de instrucciones
 * antes de su invocación utilizando el método configureContext. Tenga en cuenta
 * que el contexto real depende de la subclase porque la información necesaria
 * varía entre instrucciones.
 * 
 * @author Eddy Cuizaguana Cerpa
 * 
 */
public interface Instruction {

	/**
	 * Analiza la cadena devolviendo una instancia de su subclase
	 * correspondiente si la cadena se ajusta a la sintaxis de la instrucción.
	 * De lo contrario, se produce una WrongInstructionFormatException. Cada
	 * subclase abstracta no debe poner en práctica su análisis sintáctico
	 * correspondiente.
	 * 
	 * @param cad
	 *            : cadena de texto
	 * @return : Referencia de instrucciones que apunta a una instancia de una
	 *         subclase de instrucciones, si es que corresponde a l string cad
	 * @throws WrongInstructionFormatException
	 *             : Cuando la cadena cad no se ajusta a la sintaxis de
	 *             instrucciones.
	 */
	public abstract Instruction parse(String cad)
			throws WrongInstructionFormatException;

	/**
	 * Devuelve una descripcion de la sintaxis de instrucciones. La cadena no
	 * termina con el separador de línea. Corresponde a la persona que llama de
	 * añadir antes de la impresión.
	 * 
	 * @return : La sintaxias de la instruccion.
	 */
	public abstract String getHelp();

	/**
	 * Establecer el contexto de ejecucion. El metodo recibe todo el motor
	 * (motor, la navgacion y el contenedor de robot) a pesar de que la
	 * aplicacion efectiva de ejecutar () no lo requiera.
	 * 
	 * @param engine
	 *            : The robot engine
	 * @param navigation
	 *            : La informacion sobre el juego, es decir, los lugares,
	 *            direcciion actual y el rumbo actual de navegar
	 * @param robotContainer
	 *            : El inventario del robot
	 */
	void configureContext(RobotEngine engine, NavigationModule navigation,
			ItemContainer robotContainer);

	/**
	 * Ejecuta la instruccion que se debe implementar en cada subclase no
	 * abstracta.
	 * 
	 * @throws InstructionExecutionException
	 *             : si no existe ningun error de ejecucion.
	 */
	void execute() throws InstructionExecutionException;

}
