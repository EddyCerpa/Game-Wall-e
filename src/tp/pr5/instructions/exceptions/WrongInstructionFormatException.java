package tp.pr5.instructions.exceptions;

/**
 * Excepción que se produce cuando un string no puede ser analizado para crear
 * un comando. La excepción tiene un mensaje fácil de usar con una explicación
 * sobre el error. Esta clase tiene varios constructores diferentes, uno para
 * cada constructor de la clase base.
 * 
 * @author Eddy Cuizaguana Cerpa
 * 
 */
@SuppressWarnings("serial")
public class WrongInstructionFormatException extends java.lang.Exception {
	/**
	 * Constructor sin parámetros (no hay mensaje que se da)
	 */
	public WrongInstructionFormatException() {

	}

	/**
	 * La excepción que se produce se crea con un mensaje del problema.
	 * 
	 * @param arg0
	 *            : string que explica el error.
	 */
	public WrongInstructionFormatException(String arg0) {
		super(arg0);
	}

	/**
	 * Constructor para crear la excepción anidada con una causa.
	 * 
	 * @param arg0
	 *            : Excepción anidada.
	 */
	public WrongInstructionFormatException(Throwable arg0) {
		super(arg0);
	}

	/**
	 * Constructor para crear la excepción anidada con una causa y un mensaje de
	 * error.
	 * 
	 * @param arg0
	 *            : string que explica el error.
	 * @param arg1
	 *            : Excepción anidada.
	 */
	public WrongInstructionFormatException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

}
