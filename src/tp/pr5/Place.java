package tp.pr5;

import tp.pr5.items.Item;
import tp.pr5.items.ItemContainer;

/**
 * Representa un lugar en la ciudad. Los lugares son conectados por calles segun
 * los 4 puntos cardinales, norte, este, sur y oeste. Cada lugar tiene un nombre
 * y una descripcion textual sobre si mismo. Esta descripcion se muestra cuando
 * el robot llega al lugar. Un lugar puede representar la nave espacial en donde
 * el robot esta seguro. Cuando el robot llega a este lugar, la aplicacion ha
 * terminado.
 * 
 * @author Eddy Cuizaguana Cerpa
 */
public class Place implements PlaceAndItemInfo {
	private String name;
	private boolean isSpaceShip;
	private String description;
	private ItemContainer itemContainer;

	/**
	 * Crea el Place
	 * 
	 * @param name
	 *            : nombre del lugar.
	 * @param isSpaceShip
	 *            : boolean que indica si esta la nave espacial en este lugar
	 * @param description
	 *            : descripcion del lugar
	 */
	public Place(String name, boolean isSpaceShip, String description) {
		this.name = name;
		this.isSpaceShip = isSpaceShip;
		this.description = description;
		this.itemContainer = new ItemContainer();
	}

	/**
	 * @return : true si el la nave espacial esta en este lugar.
	 */
	public boolean isSpaceship() {
		return this.isSpaceShip;
	}

	/**
	 * Devuelve el nombre del lugar, su descripcion y la lista de los articulos
	 * contenidos en el sitio.
	 */

	public String toString() {
		String cadena = "";
		if (this.itemContainer.numberOfItems() > 0)
			cadena = this.name + "\n" + this.description + "\n"
					+ "The place contains these objects:"
					+ this.itemContainer.toString();
		else
			cadena = (this.name + "\n" + this.description + "\n" + "The place is empty. There are no objects to pick");
		return cadena;

	}
	
	

	/**
	 * Comprueba si un elemento esta en este lugar.
	 * 
	 * @param id
	 *            : Identificador de un elemento.
	 * @return : true si y solo si el lugar contiene el elemento identificado
	 *         por ID
	 */

	public boolean existItem(String id) {
		boolean salida = false;

		if ((this.itemContainer.getItem(id) != null)
				&& this.itemContainer.getItem(id).getId().equalsIgnoreCase(id))
			salida = true;
		return salida;
	}

	/**
	 * Trata de recoger un articulo caracterizado por un identificador dado del
	 * lugar. Si la accion se completa el articulo debe ser retirado del lugar.
	 * 
	 * @param id
	 *            : El identificador del elemento
	 * @return : El elemento de identificador id si es que existe en el lugar.
	 *         Caso contrario, el m�todo devuelve null
	 */

	public Item pickItem(String id) {
		return this.itemContainer.pickItem(id);
	}

	/**
	 * Intenta agregar un elemento al lugar. La operacion puede fallar (si el
	 * lugar ya contiene un elemento con el mismo nombre)
	 * 
	 * @param it
	 *            : El elemento que se annade.
	 * @return : true si y solo si el elemento se puede annadir a el lugar, es
	 *         decir, el lugar no contiene un elemento con el mismo nombre
	 */

	public boolean addItem(Item it) {
		return this.itemContainer.addItem(it);
	}

	/**
	 * Suelta un elemento en este lugar. La operacion puede fallar, devuelve
	 * false
	 * 
	 * @param it
	 *            : El nombre del elemento que se va a quitar (soltar).
	 * @return : true si y solo si el articulo que se deja caer en el lugar, es
	 *         decir, un elemento con el mismo identificador no existe en el
	 *         lugar.
	 */

	public boolean dropItem(Item it) {
		boolean salida = false;
		if (!this.existItem(it.getId()))
			salida = this.itemContainer.addItem(it);
		return salida;
	}
	// ---------------------------------

	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public String getDescription() {
		return this.toString();
	}

	/**
	 * Clase que devuelve un string con todos los elemntos del lugar
	 * @return : cadena con todos los string del lugar
	 */
	@Override
	public String toStringAllItemsPlace() {
		return itemContainer.toString();
	}

}
