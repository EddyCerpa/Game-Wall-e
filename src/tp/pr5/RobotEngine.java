package tp.pr5;

import java.util.Iterator;

import tp.pr5.instructions.Instruction;
import tp.pr5.instructions.exceptions.InstructionExecutionException;
import tp.pr5.items.InventoryObserver;
import tp.pr5.items.ItemContainer;

/**
 * Esta clase representa el motor del robot. Controla los movimientos del robot mediante 
 * el procesamiento de las instrucciones introducidas con el teclado. 
 * El motor se para cuando el robot llega a la nave espacial, se queda sin combustible 
 * o recibe una instrucción de dejar de funcionar.
 * El motor de robot es también responsable de la actualización del nivel de combustible 
 * y el material reciclado de acuerdo con las acciones que el robot lleva a cabo en la 
 * ciudad
 * El motor de robot también contiene un inventario donde el robot almacena los elementos 
 * que se recoge de la ciudad.
 * @author Eddy Cuizaguana Cerpa
 *
 */

public class RobotEngine extends Observable<RobotEngineObserver>{
	
	private  NavigationModule nav;
	private int fuel; 
	private int materialRecicle;
	private ItemContainer itemContainer;
	static String LINE_SEPARATOR = System.getProperty("line.separator");
	private boolean salir;
	//------------------------------------------------------
	/**
	 * Crea el motor de robot en un lugar inicial, frente a una dirección inicial y 
	 * con un mapa de la ciudad. Inicialmente, el robot no tiene ningún artículo 
	 * o material reciclado, pero tiene una cantidad inicial de combustible (100).
	 * @param cityMap : El mapa de laciudad
	 * @param initialPlace : Lugar inicial
	 * @param direction : Direccion a la que esta mirando
	 */
	public RobotEngine(City cityMap, Place initialPlace, Direction direction){
		this.nav = new NavigationModule (cityMap, initialPlace);
		this.nav.initHeading(direction);
		//----------------
		this.fuel = 100;// poner a 100 por q sino no pasa el validador
		this.materialRecicle = 0;
		this.itemContainer = new ItemContainer();
		this.salir = false;
	}
	
	/**
	 * Se ejecuta una instruccion. La instruccion debe estar configurado 
	 * con el contexto antes de ejecutarlo. Se controla el final de la simulacion. 
	 * Si la ejecucion de la instrucción produce una excepcion, entonces el mensaje 
	 * correspondiente se imprime.
	 * @param c : La instruccion a ser ejecutada
	 */
	public void communicateRobot(Instruction c) {
		c.configureContext(this, nav, this.itemContainer);
		try {
			if (/*!isOver()*/fuel>0){ // con q se cumpla uno es suficiente para no seguir la simulacion
				c.execute();
				isOver();
			}
		} catch (InstructionExecutionException e) { // captura las exepciones de instruction
			informaObservadoresMensajeError(e.getMessage());
		}
	}	
	/**
	 * Verifica si la simulacion ha terminado
	 * @return
	 */
	public boolean isOver(){
		boolean ok = false;
		if( nav.atSpaceship() || this.fuel <= 0 ||salir){
			informarObservadoresQuit();
			salir = true;
			ok = true;
		}
		return ok;
	}
	
	public boolean fin (){
		return salir;
	}
	
	
	/**
	 * Aumenta la cantidad de material reciclado de la robot
	 * @param weight : Cantidad de material reciclado
	 */
	public void addRecycledMaterial(int weight) {
		this.materialRecicle = this.materialRecicle + weight;
		informaObservadoresFuelMateral();
	}
	
	/**
	 * Añade una cantidad de combustible al robot (que puede ser negativo).
	 * @param fuels : Cantidad de combustible añadido al robot
	 */
	public void addFuel(int fuels) {
		this.fuel += fuels;
		informaObservadoresFuelMateral();
	}
	/**
	 * Consultamos la cantidad de material reciclado actual
	 * @return : La cantidad de material reciclado actual
	 */
	public int getRecycledMaterial() {
		return this.materialRecicle;
	}

	/**
	 * Consulatamos la cantidad de conbustible actual
	 * @return : La cantidad e conbustible actual
	 */
	public int getFuel() {
		return this.fuel;
	}

	//-----------------Metodos que llaman a informadores de vistas--------
	/**
	 * Requests the engine to inform the observers that the simulation starts
	 */
	public void requestStart(){
		nav.iniciaObservadores();
		informaObservadoresFuelMateral();
	}
	/**
	 * Solicita el final de la simulacion
	 */
	public void requestQuit() {
		salir = true;
		informarObservadoresQuit();
		
	}
	/**
	 * Solicita al Engine la informacion de todas las instrucciones posibles
	 */
	public void requestHelp() {
		informarObservadoresAyuda(Interpreter.interpreterHelp());
	}
	
	/**
	 * Solicita al engine sobre un mensaje de error
	 * @param msg : emnsaje de error
	 */
	public void requestError(String msg){
		informaObservadoresMensajeError(msg);
	}
	/**
	 * Solicita al engine que diga algo
	 * @param message : el mensaje que dice
	 */
	public void saySomething(java.lang.String message){
		informarObservadoresMensaje(message);
	}
	//-------------------------------PRACTICA 5 BIEN HECHA---------------------
	/**
	 * Informa a la lista de vistas (observadores) de la accion que se ha producido.
	 * Accion: cambio de fuel y mareial reciclado
	 */
	public void informaObservadoresFuelMateral(){
		Iterator <RobotEngineObserver> o = iterator();
		while (o.hasNext()){
			o.next().robotUpdate(fuel, this.materialRecicle);
		}
	}
	
	/**
	 * Informa a la lista de vistas (observadores) de la accion que se ha producido.
	 * Accion: cambio de fuel y mareial reciclado
	 */
	public void informarObservadoresAyuda(String help){
		Iterator <RobotEngineObserver> o = iterator();
		while (o.hasNext()){
			o.next().communicationHelp(help);
		}
	}
	/**
	 * Informa a la lista de vistas (observadores) de la accion que se ha producido.
	 * Accion: el robot ha llegadoa la nave espacial o se se ha apagado
	 */
	public void informarObservadoresQuit(){
		Iterator <RobotEngineObserver> o = iterator();
		while (o.hasNext()){
			o.next().engineOff(nav.atSpaceship());
		}
	}
	
	/**
	 * Informa a la lista de vistas (observadores) de la accion que se ha producido.
	 * Accion: ha reciclado el material
	 */
	public void informaObservadoresMensajeError(String mensaje){
		Iterator <RobotEngineObserver> o = iterator();
		while (o.hasNext()){
			o.next().raiseError(mensaje);
		}
	}
	/**
	 * Informa a la lista de vistas (observadores) de la accion que se ha producido.
	 * Accion: mensaje de lo que el robot quiere decir
	 */
	public void informarObservadoresMensaje(String mensaje){
		Iterator <RobotEngineObserver> o = iterator();
		while (o.hasNext()){
			o.next().robotSays(mensaje);
		}
	}
	//********** anadimos los observadors ------
	/**
	 * Metodo que añade un observador "vista" al navigationModule.
	 * @param robotObserver : observador que tiene que ser registrado
	 */
	public void addNavigationObserver(NavigationObserver robotObserver){
		nav.addObserver(robotObserver);
	}
	
	/**
	 * Metodo que añade un observador "vista" al robotEngine.
	 * @param observer : observador que tiene que ser registrado
	 */
	public void addEngineObserver(RobotEngineObserver observer){
		this.addObserver(observer);
	}
	
	/**
	 * Metodo que añade un observador "vista" al itemContainer.
	 * @param robotObserver : observador que tiene que ser registrado
	 */
	public void addItemContainerObserver(InventoryObserver observer){
		itemContainer.addObserver(observer);
	}
	
	
	
	//--------------------------------------		
}
