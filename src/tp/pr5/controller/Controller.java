package tp.pr5.controller;

import tp.pr5.NavigationObserver;
import tp.pr5.RobotEngine;
import tp.pr5.RobotEngineObserver;
import tp.pr5.items.InventoryObserver;

/**
 * clase Controlador que se encarga de añadir los observadores correspondientes
 * @author eddy
 *
 */
public class Controller {
	public RobotEngine robot;
	
	public Controller(RobotEngine robot) {
		this.robot = robot;
	}	
	
	/**
	 * Metodo que añade un observador al robotengine, el obserbale (roborEngine) avisara a 
	 * todos sus observadores de la modificacion de ha tendido
	 * @param robotObserver
	 */
	public void addRobotEngineObserver (RobotEngineObserver robotObserver){
		robot.addEngineObserver(robotObserver);
	}
	
	public void addNavigationModuleObserver(NavigationObserver navigationObserver){
		robot.addNavigationObserver(navigationObserver);
		
	}
	
	public void addInventarioObserver(InventoryObserver inventario){
		robot.addItemContainerObserver(inventario);
	}
}
