package tp.pr5.items;

import tp.pr5.NavigationModule;
import tp.pr5.RobotEngine;

/**
 * La basura es un tipo de elemento que genera material reciclado tras su uso.
 * La basura se puede utilizar solamente una vez. Después de su uso, debe ser
 * retirado del inventario robot.
 * 
 * @author Eddy Cuizaguana Cerpa
 * 
 */
public class Garbage extends Item {
	private int materialReciclado; // La cantidad de material reciclado

	/**
	 * Grabage constructor
	 * 
	 * @param id
	 *            : id del item.
	 * @param description
	 *            : descripcion del item.
	 * @param recycledMaterial
	 *            : La cantidad de material reciclado que el elemento genera.
	 */
	public Garbage(String id, String description, int recycledMaterial) {
		super(id, description);
		this.materialReciclado = recycledMaterial;
	}

	/**
	 * La basura puede ser utilizado una vez.
	 * 
	 * @return : si el item (basura) no se autilizado todavia.
	 */

	public boolean canBeUsed() {
		boolean salida = false;
		if (this.materialReciclado > 0)
			salida = true;
		return salida;
	}

	/**
	 * La basura genera material reciclado para la robot que utiliza.
	 * 
	 * @return : true si la basura se transformó en material reciclado
	 */
	public boolean use(RobotEngine r, NavigationModule nav) {
		boolean salida = false;
		if (this.canBeUsed()) {
			r.addRecycledMaterial(this.materialReciclado);
			this.materialReciclado = 0;
			salida = true;
		}
		return salida;
	}

	/**
	 * Genera una cadena con la descripción del Item.
	 */
	public String toString() {
		return super.toString() + "// recycled material = "
				+ this.materialReciclado;
	}

}
