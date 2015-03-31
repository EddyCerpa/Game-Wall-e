package tp.pr5.items;

import tp.pr5.NavigationModule;
import tp.pr5.PlaceAndItemInfo;
import tp.pr5.RobotEngine;

/**
 * La superclase de cada tipo de elemento. Contiene la información común para
 * todos los elementos y define la interfaz que los elementos deben coincidir
 * 
 * @author Eddy Cuizaguana Cerpa
 */

public abstract class Item implements Comparable<Item>, PlaceAndItemInfo {
	protected String id;
	protected String descripcion;

	/**
	 * Construye un elemento con un un id dado y descripción
	 * 
	 * @param id
	 * @param description
	 *            : Descripción del artículo.
	 */
	public Item(String id, String description) {
		this.id = id;
		this.descripcion = description;
	}

	/**
	 * Comprueba si el elemento puede ser utilizado. Las subclases deben
	 * reemplazar este método
	 * 
	 * @return : true si puede ser utilizado.
	 */
	public abstract boolean canBeUsed();

	/**
	 * Trate de usar el elemento con un robot en un lugar determinado. Devuelve
	 * si la acción se ha completado. Las subclases deben reemplazar este método
	 * 
	 * @param r
	 *            : El robot que utiliza el item.
	 * @param nav
	 *            : El lugar donde se utiliza el elemento.
	 * @return : tue si la acción se ha completado. De lo contrario, devuelve
	 *         false.
	 */
	public abstract boolean use(RobotEngine r, NavigationModule nav);

	/**
	 * devuelve el id del item
	 * 
	 * @return : id del item.
	 */
	public String getId() {
		return this.id;
	}

	/**
	 * Genera una cadena con la descripción del Item.
	 */
	public String toString() {
		return this.getId() + ": " + this.descripcion;
	}
	// ---------------------------------------------------------------
	// se utiliza para mostrar la descripcion en la tabla
	public String toStringInterfaz() {
		return this.descripcion;
	}

	/**
	 * Comparamos el ide de este Item con el ide del Item que le pasamos
	 * 
	 * @param arg0
	 *            : el item que le queremos pasar
	 */
	@Override
	public int compareTo(Item arg0) {
		return this.id.compareToIgnoreCase(arg0.id);
	}

	public boolean equals(Object obj) {
		return (this == obj) || (obj != null)
				&& (this.getClass() == obj.getClass())
				&& (this.id == ((Item) obj).id);
		// tambien se podria haber tenidio en cuenta la descripcion
	}
	
	//----
	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return this.id;
	}

	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		return this.descripcion;
	}

	@Override
	public boolean isSpaceship() {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public String toStringAllItemsPlace() {
		// TODO Auto-generated method stub
		return null;
	}
}
