package tp.pr5.gui;

import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.TitledBorder;

import tp.pr5.Direction;
import tp.pr5.NavigationObserver;
import tp.pr5.PlaceAndItemInfo;

/**
 * Clase que crea un JTexArea que seran añdidas al Frame MainWindow
 * 
 * @author Eddy Cuizaguan cerpa
 * 
 */

@SuppressWarnings("serial")
public class AreaDeTexto extends JPanel implements NavigationObserver {
	private JScrollPane scrollPane;
	private JTextArea txtrLog;

	public AreaDeTexto (GUIController gameController){
		creaAreaDeTexto();
		gameController.addNavigationModuleObserver(this);
	}
	
	/**
	 * Metodo que crea el Objeto
	 */
	public void creaAreaDeTexto() {
		// configuramos el panel
		setBorder(new TitledBorder(null, "Log", TitledBorder.LEADING,
				TitledBorder.TOP, null, null));
		setLayout(new BorderLayout(0, 0));
		
		// Creamos los objetos
		scrollPane = new JScrollPane();
		txtrLog = new JTextArea();
		
		// modificamos y añadimso los objetos al panel
		scrollPane.setPreferredSize(new java.awt.Dimension(80, 80));
		scrollPane
				.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane
				.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		add(scrollPane, BorderLayout.CENTER);
		scrollPane.setViewportView(txtrLog);
	}

	@Override
	public void initNavigationModule(PlaceAndItemInfo initialPlace,Direction heading) {
		txtrLog.setText(initialPlace.getDescription());
	}
	@Override
	public void robotArrivesAtPlace(Direction heading, PlaceAndItemInfo place) {
		txtrLog.setText(place.getDescription());
	}
	@Override
	public void placeHasChanged(PlaceAndItemInfo placeDescription) {
		txtrLog.setText(placeDescription.getDescription());
	}
	@Override
	public void placeScanned(PlaceAndItemInfo placeDescription) {
		txtrLog.setText(placeDescription.getDescription());
	}
	// No utilizados en esta ventana
	@Override
	public void headingChanged(Direction newHeading) {}
}
