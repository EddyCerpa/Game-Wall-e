package tp.pr5.items;

import tp.pr5.NavigationModule;
import tp.pr5.RobotEngine;
import tp.pr5.Street;

/**
 * A CodeCard puede abrir o cerrar la puerta situada en las calles. La tarjeta
 * contiene un código que debe coincidir con el código de la calle con el fin de
 * realizar la acción.
 * 
 * @author Eddy Cuizaguana Cerpa
 */

public class CodeCard extends Item {
	private String code;

	/**
	 * Constructor CodeCard
	 * 
	 * @param id
	 *            :identificador del item
	 * @param description
	 *            :descripcion de la tarjeta
	 * @param code
	 *            :codigo de la tarjeta
	 */
	public CodeCard(String id, String description, String code) {
		super(id, description);
		this.code = code;
	}

	/**
	 * Una tarjeta de código siempre se puede utilizar. Puesto que el robot
	 * tiene la tarjeta de código nunca se pierde
	 */
	public boolean canBeUsed() {
			return true;
	}

	/**
	 * El método de utiliza la tarjeta de código. Si el robot se encuentra en un
	 * lugar que contiene una calle en la dirección que está mirando, entonces
	 * la calle se abre o cierra si el código de la calle y coinside con el
	 * código de la tarjeta.
	 * return : true si se ha intentado abrir o cerrar una puerta 
	 */
	public boolean use(RobotEngine r, NavigationModule nav) {
		boolean salida = false;	
		Street calle = nav.getHeadingStreet();
		
		if (calle != null && calle.getCode() != null && 
				calle.getCode().equalsIgnoreCase(code)){
			if (calle.isOpen())
				calle.close(this);// cerramos la calle abierta
			else
				calle.open(this);// abrimos la calle cerrada
			salida = true;
		}
		return salida;
	}

	/**
	 * Obtiene el código almacenado en la tarjeta de código.
	 * 
	 * @return : String que representa el código.
	 */
	public String getCode() {
		return this.code;
	}
	
}
