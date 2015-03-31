package tp.pr5.items;

import tp.pr5.NavigationModule;
import tp.pr5.RobotEngine;

/**
 * Una partida que representa el combustible. Este elemento puede ser utilizado
 * al menos una vez y que proporciona energía de alimentación al robot. Cuando
 * el elemento se utiliza el número configurado de veces, entonces debe ser baja
 * en el inventario robot
 * 
 * @author Eddy Cuizaguana Cerpa
 */
public class Fuel extends Item {
	private int power; // la cantidad de energia q este item ofrese al robot
	private int veces; // numero de veces q el robot puede usar este Item

	/**
	 * Constructor Fuel
	 * 
	 * @param id
	 *            : id del item.
	 * @param description
	 *            : descripcion del item.
	 * @param power
	 *            : La cantidad de energía de la energía que este item
	 *            proporciona al robot.
	 * @param times
	 *            : Numero de veces que el robot puede utilizar el Item.
	 */

	public Fuel(String id, String description, int power, int times) {
		super(id, description);
		this.power = power;
		this.veces = times;
	}

	/**
	 * El combustible puede ser utilizado tantas veces como se configuró.
	 * 
	 * @return : true si el item puede ser utilizado.
	 */

	public boolean canBeUsed() {
		boolean salida = false;
		if (this.veces > 0)
			salida = true;
		return salida;
	}

	/**
	 * El uso de combustible proporcina energia al robot (si este puede suer
	 * utilizado)
	 * 
	 * @return : true si el item se ha utilizado.
	 */

	public boolean use(RobotEngine r, NavigationModule nav) {
		boolean salida = false;
		if (this.canBeUsed()) {
			r.addFuel(this.power);
			this.veces--;
			salida = true;
		}
		return salida;

	}

	/**
	 * Genea un String con la descripcion del item.
	 */
	public String toString() {
		return super.toString() + "// power = " + this.power + ", times = "
				+ this.veces;
	}
}
