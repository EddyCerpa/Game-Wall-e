package tp.pr5;

/**
 * Interfaz de los observadores que deseen ser notificados acerca de los
 * acontecimientos ocurridos en el motor de robot. El motor robot notificará
 * los cambios en el robot (combustible y material reciclado), informará acerca
 * de los problemas de comunicación, errores y cuando el robot quiera decir
 * algo. Por último, el motor también notificará cuando el usuario solicita
 * ayuda y cuando el robot se apaga (debido a que el robot se quede sin
 * combustible ni cuando llegó a la nave espacial)
 * 
 * @author Eddy Cuizaguana Cerpa
 * 
 */
public interface RobotEngineObserver {

	/**
	 * El motor del robot informa de que ha levantado un error
	 * @param msg : Mensaje de error
	 */
	void raiseError(String msg);
	
	/**
	 * El motor del robot informa que la ayuda se ha solicitado
	 * @param help : Una cadena con la ayuda de información
	 */
	void communicationHelp(String help);
	
	/**
	 * El motor del robot informa que el robot se ha apagado (porque ha llegado a la nave espacial o que se ha quedado sin combustible)
	 * @param atShip :  True si el robot se apaga porque ha llegado a la nave espacial o false si se ha quedado sin combustible
	 */
	void engineOff(boolean atShip);

	/**
	 * El motor del robot informa que la comunicación ha terminado.
	 */
	void communicationCompleted();
	
	/**
	 * El motor del robot informa que el combustible y / o la cantidad de material reciclado ha cambiado
	 * @param fuel : antidad actual de combustible
	 * @param recycledMaterial : cantidad actual de material reciclado
	 */
	void robotUpdate(int fuel, int recycledMaterial);
	
	/**
	 * El motor del robot informa que el robot quiere decir algo
	 * @param message : el mensaje del robot
	 */
	void robotSays(java.lang.String message);
}
