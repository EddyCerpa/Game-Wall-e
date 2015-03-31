package tp.pr5.console;

import tp.pr5.Direction;
import tp.pr5.NavigationObserver;
import tp.pr5.PlaceAndItemInfo;
import tp.pr5.RobotEngineObserver;
import tp.pr5.items.InventoryObserver;

/**
 * La vista que muestra la aplicacion con el System.out. Se implementa todas las interfaces de observacion 
 * con el fin de ser notificados acerca de todos los acontecimientos que se producen cuando 
 * el robot esta funcionando.
 * @author Eddy Cuizaguana Cerpa
 *
 */
public class Console implements NavigationObserver, RobotEngineObserver, InventoryObserver {
	static String LINE_SEPARATOR = System.getProperty("line.separator");

	public Console() {}
	//------navigationObserver
	@Override
	public void headingChanged(Direction newHeading) {
		System.out.println("WALL·E is looking at direction " + newHeading);
	}

	/**
	 * Muesta un mensaje de este tipo : 
	 * Sol 
	 * You are at the center of Madrid The
	 * place contains these objects: 
	 * Battery 
	 * Petrol
	 */
	@Override
	public void initNavigationModule(PlaceAndItemInfo initialPlace,Direction heading) {
		System.out.println( initialPlace.getDescription()+ LINE_SEPARATOR 
							+ "WALL·E is looking at direction " + heading);
	}

	@Override
	public void robotArrivesAtPlace(Direction heading, PlaceAndItemInfo place) {
		System.out.println("WALL·E says: Moving in direction " + heading + LINE_SEPARATOR +
							place.getDescription());
	}

	@Override
	public void placeScanned(PlaceAndItemInfo placeDescription) {
		System.out.println("In this square you can find " + placeDescription.toStringAllItemsPlace());
	}

	@Override
	public void placeHasChanged(PlaceAndItemInfo placeDescription) {}
	//-----RobotEngineObserver

	@Override
	public void raiseError(String msg) {
		System.out.println(msg);
	}

	@Override
	public void communicationHelp(String help) {
		System.out.println("The valid instructions for WALL-E are : " +
							 LINE_SEPARATOR + help);
	}

	@Override
	public void engineOff(boolean atShip) {
		if(atShip){
			System.out.println("I am at my spaceship. Bye bye");
		}
		else System.out.println("Shutting down... Bye bye");
		
		
	}
	@Override
	public void robotUpdate(int fuel, int recycledMaterial) {
		System.out.println("      * My power is " + fuel + LINE_SEPARATOR +
					"      * My reclycled material is " + recycledMaterial);
		
	}

	@Override
	public void robotSays(String message) {
		System.out.println(message);
		
	}
	@Override
	public void communicationCompleted() {}
	//-----InventoryObserver

	@Override
	public void addelementoTabla(String itemName, String itemDescription) {
		System.out.println("WALL·E says: I am happy! Now I have " + itemName);
	}

	@Override
	public void deletelementoTabla(String itemName, String itemDescription) {
		System.out.println("WALL·E says: Great! I have dropped " + itemName);

	}

	@Override
	public void inventoryScanned(String inventoryDescription) {
		if (inventoryDescription == null)
			System.out.println("WALL·E says:My inventory is empty");
		else 
			System.out.println(inventoryDescription);
	}

	@Override
	public void itemScanned(String description) {
		if (description == "")
		System.out.println("WALL·E says: My inventory is empty ");
		else 
			System.out.println("WALL·E says:" + description);
	}

	@Override
	public void itemEmpty(String itemName) {
		System.out.println("WALL·E says: What a pity! I have no more " + itemName+"in my inventory");
	}

	

	
}
