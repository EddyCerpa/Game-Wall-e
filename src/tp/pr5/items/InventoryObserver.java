package tp.pr5.items;

/**
 * Interfaz de los observadores que deseen ser notificados acerca de los acontecimientos 
 * ocurridos en el inventario robot. El contenedor notificará a su observador cada cambio en el
 * contenedor (cuando las selecciones de robots o artículos gotas) y cuando se quita un 
 * elemento del contenedor porque está vacío. El contenedor también notificará cuando el 
 * usuario solicita  escanear un elemento.
 * @author Eddy Cuizaguana Cerpa
 *
 */
public interface InventoryObserver {
	/**
	 * Notifica que el contenedor ha cambiado
	 * @param inventory : Nuevo inventario
	 */
	//void inventoryChange(List<Item> inventoryString);
	/**
	 * Notifica que tiene que añadir un elemento a la tabla
	 * @param itemName : ide del item a ser añadido
	 * @param itemDescription : descripcion del item que se quiere añadir
	 */
	void addelementoTabla(String itemName, String itemDescription);
	/**
	 * Notifica a la tabla que tiene que borrar un elemento
	 * @param itemName : id del elemnto que tiene que borrar
	 * @param itemDescription : descripcion del elemnto a borrar de la tabla
	 */
	void deletelementoTabla(String itemName, String itemDescription);
	
	//void operateElement ();
	
	//-----------------------------------------------------------
	
	/**
	 * Notifica que el usuario solicita una instrucción SCAN en el inventario.
	 * @param inventoryDescription : Información sobre el inventario
	 */
	void inventoryScanned(String inventoryDescription);
	
	/**
	 * Notifica que el usuario desea escanear un elemento asignado en el inventario
	 * @param description : Descripción del artículo
	 */
	void itemScanned(String description);
	
	/**
	 * Notifica que un elemento está vacío y se retira del recipiente. Invocará al metodo
	 * inventoryChange.
	 * @param itemName : Nombre del elemento vacío
	 */
	void itemEmpty(String itemName);

}
