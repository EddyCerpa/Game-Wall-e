package tp.pr5;

/**
 * PlaceInfo define una interfaz no modificable en un lugar. Es utiliza por las clases que 
 * necesitan acceder a la informaci�n contenida en el sitio, pero que no se puede 
 * modificar el lugar en s�.
 * @author Eddy Cuizaguana Cerpa
 *
 */
public interface PlaceAndItemInfo {

	/**
	 * Devolver el nombre del lugar
	 * @return : El nombre del lugar
	 */
	String getName();
	
	/**
	 * Devolver la descripci�n lugar
	 * @return : Descripcion del lugar
	 */
	String getDescription();
	
	/**
	 * Hay un lugar de la nave espacial?
	 * @return : cierto si el lugar representa una nave espacial
	 */
	boolean isSpaceship();
	
	String toStringAllItemsPlace ();
}
