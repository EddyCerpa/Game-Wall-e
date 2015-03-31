package tp.pr5.gui;

import java.awt.BorderLayout;

import javax.swing.DebugGraphics;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import tp.pr5.Direction;
import tp.pr5.NavigationObserver;
import tp.pr5.PlaceAndItemInfo;
import tp.pr5.gui.AreaDeTexto;
import tp.pr5.gui.Botones;

/**
 * Esta clase está a cargo del panel que muestra la información del encabezado
 * del robot y la ciudad que está atravesando. Contiene la cuadrícula que
 * representa a la ciudad en la interfaz Swing, un área de texto para mostrar
 * las descripciones de lugar, y una etiqueta con un icono que representa el
 * título robot
 * 
 * La rejilla 11x11 contiene PlaceCell objetos y el primer lugar comienza a
 * (5,5). Este panel se actualizará los lugares visitados cuando el robot se
 * mueve de un lugar a otro. Además se mostrará la descripción lugar en un área
 * de texto si el usuario hace clic en un lugar visitado.
 * 
 * @author Eddy Cuizaguana Cerpa
 * 
 */
@SuppressWarnings("serial")
public class NavigationPanel extends JPanel implements NavigationObserver {
	private static String IMAGEN_NORTE = "src/tp/pr5/gui/images/walleNorth.png";
	private static String IMAGEN_SUR = "src/tp/pr5/gui/images/walleSouth.png";
	private static String IMAGEN_ESTE = "src/tp/pr5/gui/images/walleEast.png";
	private static String IMAGEN_OESTE = "src/tp/pr5/gui/images/walleWest.png";
	
	private JLabel lblImagWalle ;
	private Icon icoFigura;
	private Botones matrizDeBotones;
	private AreaDeTexto log;

	
	/**
	 * Costructora a la cual le pasamos la referencia de GUIcontroller
	 * @param gameController : le pasamos el controlador de la gui
	 */
	public NavigationPanel(GUIController gameController) {
		creaNavigationPanel(gameController);
		gameController.addNavigationModuleObserver(this); // lo hacemos solo por la imagen
	}
	/**
	 * Create the panel de navegacion, con las configuraciones correspondientes.
	 * @param gameController 
	 */
	public void creaNavigationPanel(GUIController gameController)  {
		setLayout(new BorderLayout(0, 0));
		
		// imagen walee
		icoFigura = new ImageIcon(IMAGEN_NORTE);
		lblImagWalle = new JLabel();
		lblImagWalle.setName("lblImagWalle");
		lblImagWalle.setIcon(icoFigura);
		lblImagWalle.setHorizontalTextPosition(SwingConstants.CENTER);
		lblImagWalle.setDebugGraphicsOptions(DebugGraphics.NONE_OPTION);
		add(lblImagWalle, BorderLayout.WEST);
		// log 
		log = new AreaDeTexto(gameController) ;
		add(log, BorderLayout.SOUTH);
		// matriz de botones
		matrizDeBotones = new Botones(log,gameController);
		add(matrizDeBotones, BorderLayout.CENTER);
	}
	/**
	 * Metodo que actualiza la imagen de wallee, para ello le mandamos la direccion nueva,
	 * @param dir : direcion que actualiza la imagen
	 */
	public void refrescarImagen (Direction dir){
		if (dir == Direction.NORTH)
		icoFigura = new ImageIcon(IMAGEN_NORTE);
		else if (dir == Direction.EAST)
			icoFigura = new ImageIcon(IMAGEN_ESTE);
		else if (dir == Direction.SOUTH)
			icoFigura = new ImageIcon(IMAGEN_SUR); 
		else if (dir == Direction.WEST)
			icoFigura = new ImageIcon(IMAGEN_OESTE);
		lblImagWalle.setIcon(icoFigura);
	}
	
	@Override
	public void headingChanged(Direction newHeading) {
		this.refrescarImagen(newHeading);
	}
	@Override
	public void initNavigationModule(PlaceAndItemInfo initialPlace, Direction heading) {
		this.refrescarImagen(heading);
	}
	@Override
	public void robotArrivesAtPlace(Direction heading, PlaceAndItemInfo place) {}
	@Override
	public void placeScanned(PlaceAndItemInfo placeDescription) {}
	@Override
	public void placeHasChanged(PlaceAndItemInfo placeDescription) {}

	

}
