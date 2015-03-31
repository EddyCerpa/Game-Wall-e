package tp.pr5.items;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

import tp.pr5.Observable;
import tp.pr5.PlaceAndItemInfo;


/**
 * Un contenedor de articulos. Puede ser utilizado por cualquier clase que
 * almacena elementos. Un recipiente no puede almacenar dos elementos con el
 * mismo identificador. Proporciona metodos para agregar elementos nuevos,
 * acceder a ellos y sacarlos del contenedor.
 * 
 * @author Eddy Cuizaguana Cerpa
 * 
 */

public class ItemContainer extends Observable<InventoryObserver> {

	static String LINE_SEPARATOR = System.getProperty("line.separator");
	private ArrayList<Item> items;

	/**
	 * Crea el contenedor vacio
	 */
	public ItemContainer() {
		this.items = new ArrayList<Item>();
	}

	/**
	 * Devuelve el tamanno del contenedor
	 * 
	 * @return : El numero de elementos en el contenedor
	 */

	public int numberOfItems() {
		return this.items.size();

	}

	/**
	 * A�ade un articulo al recipiente. La operacion puede fallar, devuelve
	 * false
	 * 
	 * @param item
	 *            : El nombre del elemento que se va a agregar.
	 * @return : true si y solo si se agrega el elemento, es decir, un elemento
	 *         con el mismo identificador que no exista en el contenedor. false
	 *         si ya existe.
	 */
	public boolean addItem(Item item) {
		boolean add = false;
		int indice = Collections.binarySearch(items, item);
		if (indice < 0) {
			if (items.size() > 0) {
				indice = -(indice + 1);
				items.add(indice, item);
			} else
				this.items.add(item);
			add = true;
		}
		return add;
	}

	/**
	 * Devuelve el elemento de contenedor de acuerdo con el nombre del articulo
	 * 
	 * @param id
	 *            : nombre del articulo
	 * @return : Articulo con ese nombre o nulo si el contenedor no almacena un
	 *         articulo con ese nombre.
	 */

	public Item getItem(String id) {
		Item itemEncontrado = null;
		// creamos un objeto de tipo OtherItem poder realiza la busqueda binaria
		Item buscaItem = new OtherItem(id, null);
		int indice = Collections.binarySearch(items, buscaItem);
		if (indice >= 0) {
			itemEncontrado = this.items.get(indice);
		}
		return itemEncontrado;
	}

	/**
	 * Retorna y elimina un elemento del inventario. Esta operacion puede fallar
	 * y devuelve null.
	 * 
	 * @param id
	 *            : Nombre del elemento.
	 * @return : Un articulo si y si existe lo si el elemento identificado por
	 *         id existe en el inventario. De lo contrario, devuelve null.
	 */

	public Item pickItem(String id) {
		Item itemEncontrado = null;
		// creamos un objeto de tipo OtherItem poder realiza la busqueda binaria
		Item buscaItem = new OtherItem(id, null);
		int indice = Collections.binarySearch(items, buscaItem);
		if (indice >= 0) {
			itemEncontrado = this.items.get(indice);
			this.items.remove(indice);
		}
		return itemEncontrado;
	}

	/**
	 * Genera una cadena con informacion acerca de los elementos que figuran en
	 * el inventario.Tenga en cuenta que los articulos deben aparecer ordenadas
	 * por el nombre del elemento.
	 */
	public String toString() {
		Iterator<Item> it = items.iterator();
		String salida = LINE_SEPARATOR;

		while (it.hasNext()) {
			salida += "   " + it.next().getId() + LINE_SEPARATOR;

		}
		return salida;
	}

	/**
	 * Comprueba si el articulo con este id existe en el contenedor.
	 * 
	 * @param id
	 *            : Nombre del Item
	 * @return : true si el contenedor contiene un elemento con ese nombre.
	 */
	public boolean containsItem(String id) {
		boolean enc = false;
		// creamos un objeto de tipo OtherItem poder realiza la busqueda binaria
		Item buscaItem = new OtherItem(id, null);
		int indice = Collections.binarySearch(items, buscaItem);
		if (indice >= 0)
			enc = true;
		return enc;
	}

	/**
	 *Método llamado por el OperateInstruction cuando se utiliza con éxito 
	 *un elemento almacenado en la colección. La colección a continuación, 
	 *comprueba si el artículo podría ser utilizado de nuevo en el futuro. 
	 *Si no es posible debido a que el artículo es "vacío", a continuación, 
	 *se elimina de la colección (y el método notifica a todos los observadores).
	 * 
	 * @param item
	 *            : para ser utilizado.
	 */
	public void useItem(Item item) {
		if (this.containsItem(item.getId()) && !item.canBeUsed()){
			// eliminamos un item de la tabla en JPanel por agotarlo de tanto usarlo, en cosola se comporta mostrando un mensaje
			informarObseravOperaElement(item.getId());// en la swing : modificamos la tabla, en consola mandamos un mensaje de texto
			this.items.remove(item);
		} 
		
	}

	public void informarObseravOperaElement(String id) {
		PlaceAndItemInfo item = getItem(id);
		Iterator <InventoryObserver> o = iterator();
		while (o.hasNext()){
			o.next().itemEmpty(item.getName());
		}
	}

	// --------------------------------
	// ---- practica5
	/**
	 * Informa a los observadores que tienen que a�adir un elemento a la tabla para su correcta 
	 * visualizacion
	 * @param id : id del elemnto que se quiere a�adir
	 */
	public void informarObseravAddElemtoTabla (String id){
		PlaceAndItemInfo item = getItem(id);
		Iterator <InventoryObserver> o = iterator();
		while (o.hasNext()){
			o.next().addelementoTabla(item.getName(), item.getDescription());
		}
	}

	/**
	 * Informa a los observadores que se tiene que borrar un elemnto de la tabla
	 * @param id : ide del elemto que se quiere borrar
	 */
	public void informarObseravDeleteElemTabla (String id){
		PlaceAndItemInfo item = getItem(id);
		Iterator <InventoryObserver> o = iterator();
		while (o.hasNext()){
			o.next().deletelementoTabla(item.getName(), item.getDescription());
		}
	}
	
	//------utilizados para la consola
	/**
	 * Informa a los obsevadores de un elemnto escaneado,
	 * @param id : si es null se mostrara la lista de elemtos del lugar,
	 * si el id no es null y se encuenta el lugar se mostrara la descripcion del mismo
	 * y si no hay elementos en el lugar se notificara la jugador de la misma.
	 */
	public void informarObseravScan(String id){
		Iterator <InventoryObserver> o = iterator();
		if (numberOfItems() == 0 ) {
			while (o.hasNext()){
				o.next().itemScanned("");
			}
		}
		else if (id != null && containsItem(id)) {
			while (o.hasNext()){
				o.next().itemScanned(getItem(id).toString());
			}
		}
		else { // solo llega hasta aqui si el numero de items es mayor > 0 y se quiere escanear todo
			while (o.hasNext()){
				o.next().inventoryScanned(toString());
			}
		}	
	}
	

}
