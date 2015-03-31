package tp.pr5.gui;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import tp.pr5.Direction;
import tp.pr5.NavigationObserver;
import tp.pr5.PlaceAndItemInfo;
import tp.pr5.RobotEngineObserver;
import tp.pr5.items.InventoryObserver;

/**
 * Panel en la parte inferior de la ventana que muestra mensajes sobre los
 * eventos que se producen durante la simulacion. Este panel implementa todas
 * las interfaces de observaci�n con el fin de ser notificados acerca de todos
 * los eventos ocurridos
 * 
 * @author Eddy Cuizaguana Cerpa
 * 
 */
@SuppressWarnings("serial")
public class InfoPanel extends JPanel implements RobotEngineObserver,NavigationObserver, InventoryObserver {

	JLabel infolabel = new JLabel();
	
	public InfoPanel(GUIController gameController) {
		infolabel = new JLabel();
		add(infolabel);
		gameController.addInventarioObserver(this);
		gameController.addRobotEngineObserver(this);
		gameController.addNavigationModuleObserver(this);
	}
	// ------robot
	@Override
	public void raiseError(String msg) {
		JOptionPane.showMessageDialog(null, msg);
	}
	@Override
	public void engineOff(boolean atShip) {
		if(atShip){
			infolabel.setText("I am at my spaceship. Bye bye");
		}
		else infolabel.setText("Shutting down... Bye bye");
	}
	@Override
	public void robotUpdate(int fuel, int recycledMaterial) {
		infolabel.setText("Rbot atributes has been updated: (" + fuel +", "+ recycledMaterial + ")");
	}
	@Override
	public void robotSays(String message) {
		infolabel.setText(message);
	}
	@Override
	public void communicationHelp(String help) {}
	@Override
	public void communicationCompleted() {}
	//------Navigation
	@Override
	public void headingChanged(Direction newHeading) {}
	@Override
	public void initNavigationModule(PlaceAndItemInfo initialPlace,Direction heading) {}
	@Override
	public void robotArrivesAtPlace(Direction heading, PlaceAndItemInfo place) {}
	@Override
	public void placeScanned(PlaceAndItemInfo placeDescription) {}
	@Override
	public void placeHasChanged(PlaceAndItemInfo placeDescription) {}
//-----ItemContainer
	@Override
	public void addelementoTabla(String itemName, String itemDescription) {
		infolabel.setText("WALL·E says: I am happy! Now I have " + itemName);
	}
	@Override
	public void deletelementoTabla(String itemName, String itemDescription) {
		infolabel.setText("WALL·E says: Great! I have dropped " + itemName);
	}
	@Override
	public void itemEmpty(String itemName) {
		infolabel.setText("WALL·E says: What a pity! I have no more " + itemName+"in my inventory");
	}
	@Override
	public void inventoryScanned(String inventoryDescription) {}
	@Override
	public void itemScanned(String description) {}

	

}
