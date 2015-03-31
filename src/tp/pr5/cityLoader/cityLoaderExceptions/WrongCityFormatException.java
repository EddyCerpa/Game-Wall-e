package tp.pr5.cityLoader.cityLoaderExceptions;

import java.io.IOException;

/**
 * Excepción lanzada por el cargador de mapa cuando el archivo no se adhiere al formato de archivo.
 * @author Eddy Cuizaguana Cerpa
 *
 */
@SuppressWarnings("serial")
public class WrongCityFormatException extends IOException {

	/**
	 * 
	 */
	public WrongCityFormatException() {
		
	}
	
	/**
	 * 
	 * @param msg
	 */
	public WrongCityFormatException(String msg) {
		super (msg);
	}
	
	/**
	 * 
	 * @param msg
	 * @param arg
	 */
	public WrongCityFormatException(String msg, Throwable arg) {
		super (msg,arg);
	}
	
	/**
	 * 
	 * @param arg
	 */
	public WrongCityFormatException(Throwable arg) {
		super (arg);
	}
}
