package tp.pr5;

/**
 * PlaceInfo define una interfaz no modificable en un lugar. Es utiliza por las clases que 
 * necesitan acceder a la información contenida en el sitio, pero que no se puede 
 * modificar el lugar en sí.
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
	 * Devolver la descripción lugar
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
