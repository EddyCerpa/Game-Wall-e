package tp.pr5;

import tp.pr5.items.CodeCard;

/**
 * Una calle une dos lugares A y B en una sola direcci�n. Si se define como la
 * calle (A, NORTH, B) significa que B es el norte de las calle A. Es
 * bidireccional es decir, si B es el norte de A, entonces A es en el sur de B.
 * Algunas calles estan cerradas y un codigo (contenida en una tarjeta de
 * codigo) se necesita para abrir
 * 
 * @author Eddy Cuizaguana Cerpa
 */

public class Street {

	private Place source;
	private Direction direction;
	private Place target;
	private boolean pueta;
	private String code;

	/**
	 * Crea una calle abierta y no tiene ningun codigo para abrirla o cerrarla
	 * 
	 * @param source
	 *            : lugar de origen
	 * @param direction
	 *            : Representa como se coloca el lugar de destino con respecto
	 *            al lugar de origen.
	 * @param target
	 *            : lugar de destino.
	 */

	public Street(Place source, Direction direction, Place target) {
		this.source = source; // lugar
		this.direction = direction;// direccion
		this.target = target;// objetivo
		this.pueta = true;
		this.code = null;
	}

	/**
	 * Crea una calle que tiene un codigo para abrir y cerrar
	 * 
	 * @param source
	 *            : lugar de origen
	 * @param direction
	 *            : Representa como se coloca el lugar de destino con respecto
	 *            al lugar de origen.
	 * @param target
	 *            : lugar de destino.
	 * @param isOpen
	 *            : Determina si la calle esta abietra o cerrada.
	 * @param code
	 *            : El codigo que abre y cierra la calle
	 */

	public Street(Place source, Direction direction, Place target,
			boolean isOpen, String code) {
		this.source = source; // lugar
		this.direction = direction;// direccion
		this.target = target;// objetivo
		this.pueta = isOpen;
		this.code = code;
	}

	/**
	 * comprueba que hay una calle desde place siguiendo la direccion a la que
	 * mira. Se tiene en cuenta que las calles son bidireccionales.
	 * 
	 * @param place
	 *            : Ellugar para comprobar.
	 * @param whichDirection
	 *            : Direccion usada.
	 * @return : Devuelve true si hay una calle entre dos lugares.
	 */

	public boolean comeOutFrom(Place place, Direction whichDirection) {
		boolean salida = false;
		if (place == this.source && whichDirection == this.direction)
			salida = true;
		else if (place == this.target
				&& whichDirection == Direction.opposite(this.direction))
			salida = true;
		return salida;
	}

	/**
	 * Siguiente lugar al cual el robot puede ir. Si no hubiera puerta el metodo
	 * devuelve null.
	 * 
	 * @param whereAmI
	 *            : Lugar en donde me encuetro.
	 * @return : Se devuelve el lugar en el otro lado de la calle (incluso si la
	 *         calle esta cerrada). Devuelve null si WhereAmI no pertenece a la
	 *         calle.
	 */

	public Place nextPlace(Place whereAmI) {
		Place palce = null;
		if (whereAmI == source)
			palce = this.target;
		else if (whereAmI == target)
			palce = this.source;
		return palce;
	}

	/**
	 * verifica si la calle esta abierta o cerrada
	 * 
	 * @return : true si la calle esta abireta y false si la calle esta cerrada
	 */

	public boolean isOpen() {
		return this.pueta;
	}

	/**
	 * Intenta abrir una calle utilizando una tarjeta de codigo. Los codigos
	 * deben coincidir con el fin de completar esta accion
	 * 
	 * @param card
	 *            : codigo de la tarjeta para abrir la calle.
	 * @return : True si la accion fue completada
	 */

	public boolean open(CodeCard card) {
		if (card.getCode().equals(this.code))
			this.pueta = true;
		else
			this.pueta = false;
		return this.pueta;
	}

	/**
	 * Intenta cerrar una calle utilizando una tarjeta de codigo. Los codigos
	 * deben coincidir con el fin de completar esta accion
	 * 
	 * @param card
	 *            : codigo de la tarjeta para cerrar la calle
	 * @return : True si la accion fue completada
	 */

	public boolean close(CodeCard card) {
		if (this.pueta && (card.getCode().equals(this.code)))
			this.pueta = false;
		return !this.pueta;
	}

	// ---------------------------------- // metodo creado por mi
	public String getCode() {
		return this.code;
	}
	// -------------------------------
}
