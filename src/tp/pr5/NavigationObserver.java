package tp.pr5;

/**
 * Interfaz de los observadores que quieren ser notificado sobre los eventos relacionados con 
 * el m�dulo de navegacion. Las clases que implementan esta interfaz deben ser informados 
 * cuando el robot cambia su partida, cuando se llega a un lugar, cuando el lugar se 
 * modifica (porque el robot recogi� o se quita un elemento) o cuando el usuario quiere 
 * utilizar el radar.
 * @author Eddy Cuizaguana Cerpa
 *
 */
public interface NavigationObserver {

	/**
	 * Notifica que la partida robot ha cambiado
	 * @param newHeading : New robot heading
	 */
	void headingChanged(Direction newHeading);
	
	/**
	 * Avisa de que el m�dulo de navegaci�n se ha inicializado
	 * @param initialPlace : El lugar donde el robot comienza la simulaci�n
	 * @param heading : The initial robot heading
	 */
	void initNavigationModule(PlaceAndItemInfo initialPlace, Direction heading);
	
	/**
	 * Notifica que el robot ha llegado a un lugar
	 * @param heading : La direcci�n del movimiento del robot
	 * @param place : el lugar donde llega el robot
	 */
	void robotArrivesAtPlace(Direction heading,
            PlaceAndItemInfo place);
	
	/**
	 * Notifica que el usuario solicita una instrucci�n RADAR
	 * @param placeDescription : Informacion con el lugar actual
	 */
	void placeScanned(PlaceAndItemInfo placeDescription);
	
	/**
	 * Notifica que el lugar donde se queda el robot ha cambiado (debido a que el robot
	 *  recogi� o quito un elemento)
	 * @param placeDescription : 
	 */
	void placeHasChanged(PlaceAndItemInfo placeDescription);
	
}
