package tp.pr5;

import java.util.Iterator;

import tp.pr5.instructions.exceptions.InstructionExecutionException;
import tp.pr5.items.Item;

/**
 * Esta clase esta a cargo de las funciones de navegacion del robot. Contiene la
 * ciudad donde el robot busca la basura, el lugar actual donde el robot esta, y
 * la direccion actual del robot. Contiene metodos para controlar los
 * movimientos del robot y diferentes para elegir y colocar elementos en el
 * lugar actual.
 * 
 * @author Eddy Cuizaguana Cerpa
 */
public class NavigationModule extends Observable<NavigationObserver> {

	private City city;
	private Place place;
	private Direction direction;
	
	/**
	 * Constructor: Navegacion constructor del modulo. Necesita el plano de la
	 * ciudad y el lugar inicial
	 * 
	 * @param aCity
	 *            : plano de la ciudad
	 * @param initialPlace
	 *            : lugar inicial
	 */
	public NavigationModule(City aCity, Place initialPlace) {
		this.city = aCity;
		this.place = initialPlace;
		this.direction = Direction.NORTH;
	}

	/**
	 * Comprueba si el robot ha llegado a la nave espacial
	 * 
	 * @return : verdadero si y solo si el lugar actual es la nave espacial
	 */
	public boolean atSpaceship() {
		boolean salida = false;
		if (this.place.isSpaceship())
			salida = true;
		return salida;
	}

	/**
	 * Actualiza la direccion actual del robot de acuerdo con la rotacion
	 * 
	 * @param rotation
	 *            : left or right
	 */
	public void rotate(Rotation rotation) {
		direction = Direction.giroDerechaIzquierda(direction, rotation);
	}

	/**
	 * El metodo intenta mover el robot siguiendo la corriente direccion. Si el
	 * movimiento no es posible porque no hay una calle, o hay una calle que
	 * esta cerrada, entonces se produce una excepcion. De lo contrario el lugar
	 * actual se actualiza de acuerdo con el movimiento.
	 * 
	 * @throws InstructionExecutionException
	 *             : Una excepcion con un mensaje sobre el problema encontrado
	 */
	public void move() throws InstructionExecutionException {

		Street calle = this.city.lookForStreet(this.place, this.direction);
		if (calle != null) { 
			if (calle.isOpen()) {
				this.place = calle.nextPlace(this.place);
				informarObservadoresNuevoLugar();
			}	
			else 
				throw new InstructionExecutionException(
						"WALL·E says: Arrggg, there is a street but it is closed!");
		}
		else{
			throw new InstructionExecutionException(
					"WALL·E says: There is no street in direction "
							+ this.direction);
		}
			

	}


	/**
	 * Trata de escoger un item caracterizado por un identificador dado desde el
	 * lugar actual. Si la accion se completo el elemento se elimina del lugar
	 * actual.
	 * 
	 * @param id
	 *            : El identificador del elemento
	 * @return : El elemento de identificador id si es que existe en el lugar.
	 *         Caso contrario, el metodo devuelve null
	 */
	public Item pickItemFromCurrentPlace(String id) {
		return this.place.pickItem(id);
	}

	/**
	 * Suelta un elemento en el lugar actual. Se asume que no hay otro elemento
	 * con el mismo nombre / id alli. De lo contrario, el comportamiento es
	 * indefinido.
	 * 
	 * @param it
	 *            : El nombre del elemento (Item) que se va a quitar.
	 */
	public void dropItemAtCurrentPlace(Item it) {
		this.place.dropItem(it);
	}

	/**
	 * Comprueba si existe un elemento con un id indicado en este lugar
	 * 
	 * @param id
	 *            : Identificador del elemento que estamos buscando
	 * @return : verdadero si y solo si existe un elemento con ese id que esta
	 *         en el lugar actual
	 */
	public boolean findItemAtCurrentPlace(String id) {
		return this.place.existItem(id);
	}

	/**
	 * Inicializa el rumbo actual de acuerdo con el parametro
	 * 
	 * @param heading
	 *            : Nueva direccion para el robot
	 */
	public void initHeading(Direction heading) {
		this.direction = heading;
	}

	/**
	 * Imprime la informacion (descripcion + inventario) del lugar actual
	 */
	/*public void scanCurrentPlace() {
		System.out.println(this.getCurrentPlace().toString());
	}*/

	/**
	 * Devuelve la calle a la que el robot se enfrenta
	 * 
	 * @return : La calle que el robot se enfrenta, o nula, si no hay ninguna
	 *         calle en esta direccion
	 */
	public Street getHeadingStreet() {
		return city.lookForStreet(this.place, this.direction);
	}

	/**
	 * Retorna la direccion a la que el robot esta mirando.
	 * 
	 * @return : La direccion en la que el robot se enfrenta
	 */
	public Direction getCurrentHeading() {
		return this.direction;
	}

	/**
	 * Devuelve el lugar actual donde el robot esta (para propositos de prueba).
	 * 
	 * @return : El lugar actual (currentPlace)
	 */
	public Place getCurrentPlace() {
		return this.place;
	}
	//---------
	/**
	 * Informa a la lista de vistas (observadores) de la accion que se ha producido.
	 * Accion: iniciar la navegacion
	 */
	public void iniciaObservadores (){
		Iterator <NavigationObserver> o = iterator();
		while (o.hasNext()){
			o.next().initNavigationModule(place, direction);
		}
	}
	/**
	 * Informa a la lista de vistas (observadores) de la accion que se ha producido.
	 * Accion: cambio la partida del robot
	 */
	public void informaObservadoresDirection(){
		Iterator <NavigationObserver> o = iterator();
		while (o.hasNext()){
			o.next().headingChanged(direction);
		}
	}
	
	/**
	 * Informa a la lista de vistas (observadores) de la accion que se ha producido.
	 * Accion: el robot ha llegado a un nuevo lugar
	 */
	public void informarObservadoresNuevoLugar(){
		PlaceAndItemInfo lugar = this.place;
		Iterator <NavigationObserver> o = iterator();
		while (o.hasNext()){
			o.next().robotArrivesAtPlace(direction, lugar);
		}
	}
	
	/**
	 * Informa a la lista de vistas (observadores) de la accion que se ha producido.
	 * Accion: el robot ha cogido, soltado un elemnto, en este caso el metodo se encarga de la 
	 * correspondiente actualizacion de la informacion del lugar
	 */
	public void informarObservadoresCambioLog(){
		PlaceAndItemInfo lugar = this.place;
		Iterator <NavigationObserver> o = iterator();
		while (o.hasNext()){
			o.next().placeHasChanged(lugar);
		}
	}
	//avisa cuando se deja , coge, o usa un ellemto para q se escriba por el log
	//----------------------
	/**
	 * Informa a la lista de vistas (observadores) de la accion que se ha producido.
	 * Accion: se quiere escaner la zona para saber que elementos hay (usado solo en modo console)
	 */
	public void informarObservadoresRadar(){
		Iterator <NavigationObserver> o = iterator();
		while (o.hasNext()){
			o.next().placeScanned(place);
		}
	}
}
