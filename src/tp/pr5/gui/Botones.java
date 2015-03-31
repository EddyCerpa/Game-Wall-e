package tp.pr5.gui;

import javax.swing.JPanel;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.border.TitledBorder;

import tp.pr5.Direction;
import tp.pr5.NavigationObserver;
import tp.pr5.PlaceAndItemInfo;


/**
 * Crea un Panel con una matriz de botones encargado de mostrar al usuario
 * mediante el cambio de colores el recorrido de walle ademas del lugar en el
 * que se encuentra
 * 
 * @author Eddy Cuizaguana Cerpa
 * 
 */
@SuppressWarnings("serial")
public class Botones extends JPanel implements NavigationObserver{
	// Representa el array de botones
	private PlaceCell[][] botonCiudad;
	static final int  X = 11;
	static final int Y = 11;
	private int xActual = X/2;
	private int yActual = X/2;
	private AreaDeTexto log;

	/**
	 * Create the panel, con las configuraciones correspondientes.
	 * @param log : hace referncia al area de texto situado inferiormente
	 * @param gameController : controlador de gui
	 */
	
	public Botones (AreaDeTexto log, GUIController gameController){
		this.log =log;
		creaMapa();
		gameController.addNavigationModuleObserver(this);
	}
	/**
	 * Creamos el mapa de botones y lo situamos en el panel
	 */
	public void creaMapa() {
		setBorder(new TitledBorder(null, "City Map", TitledBorder.LEADING,
				TitledBorder.TOP, null, null));
		GridLayout gridBotones = new GridLayout(11, 11);
		setLayout(gridBotones);
		botonCiudad = new PlaceCell[X][Y];
		for (int posY = 0; posY < Y; posY++) {
			for (int posX = 0; posX < X; posX++) {
				botonCiudad[posX][posY] = new PlaceCell();
				add(botonCiudad[posX][posY]);
			}
		}

	}
	@Override
	public void initNavigationModule(PlaceAndItemInfo initialPlace, Direction heading) {
		botonCiudad[X/2][Y/2].cambioColor(Color.GREEN);
		botonCiudad[X/2][Y/2].setLugar(initialPlace.getName());
		botonCiudad[X/2][Y/2].setDescription(initialPlace.getDescription());
		botonCiudad[X/2][Y/2].setNaveEspacial(initialPlace.isSpaceship()); // cambiamos la situacion de la nave espacial
		botonCiudad[X/2][Y/2].mostrarTextoBoton();
		botonCiudad[X/2][Y/2].addActionListener(new muestaMensaje());
	}

	@Override
	public void robotArrivesAtPlace(Direction heading, PlaceAndItemInfo place) {
		botonCiudad[xActual][yActual].cambioColor(Color.GRAY);
		calculaXY(heading);
		botonCiudad[xActual][yActual].setLugar(place.getName());
		botonCiudad[xActual][yActual].setDescription(place.getDescription());
		botonCiudad[xActual][yActual].setNaveEspacial(place.isSpaceship());
		botonCiudad[xActual][yActual].cambioColor(Color.GREEN);
		botonCiudad[xActual][yActual].mostrarTextoBoton();
		botonCiudad[xActual][yActual].addActionListener(new muestaMensaje());
		
	}

	/**
	 * Metodo que calcula la direcion actual (sumando y restando uno a las coredenadas) 
	 * en donde se encuntra el robot mediante las cordenadas x,y.
	 * 
	 * @param heading : direccion a la que esta mirando el robot 
	 */
	private void calculaXY(Direction heading) {
			if (heading == Direction.EAST)
				this.xActual = this.xActual + 1;
			else if (heading == Direction.WEST)
				this.xActual = this.xActual - 1;
			else if (heading == Direction.NORTH)
				this.yActual = this.yActual - 1;
			else if (heading == Direction.SOUTH)
				this.yActual = this.yActual + 1;
	}
	@Override
	public void placeHasChanged(PlaceAndItemInfo placeDescription) {
		botonCiudad[xActual][yActual].setLugar(placeDescription.getName());
		botonCiudad[xActual][yActual].setDescription(placeDescription.getDescription());
	}
	@Override
	public void placeScanned(PlaceAndItemInfo placeDescription) {}
	@Override
	public void headingChanged(Direction newHeading) {}
	
	//----------------------------------
		/**
		 * Clase que cambia el mensaje del JTextArea por la del lugar al cual indica el boton (nombre, descripcion)
		 * @author Eddy Cuizaguana Cerpa
		 *
		 */
		public class muestaMensaje implements ActionListener {
			/**
			 * Metodo que escucha a un boton del PlaceCell escribiendo en JTextArea (log) el nombre y la descripcion del lugar
			 */
			@Override
			public void actionPerformed(ActionEvent event) {
				PlaceCell fuente = (PlaceCell) event.getSource();
				log.placeScanned(fuente);
			}	
		}
}
