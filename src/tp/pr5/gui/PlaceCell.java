package tp.pr5.gui;

import java.awt.Color;

import javax.swing.JButton;

import tp.pr5.PlaceAndItemInfo;

/**
 * Es un bot�n que representa una casilla en la ciudad
 * 
 * @author Eddy Cuizaguana Cerpa
 * 
 */
@SuppressWarnings("serial")
public class PlaceCell extends JButton implements PlaceAndItemInfo {
	private String lugar; // Lugar en el cual se encutra o haya pasado walle
	private String description;
	private boolean naveEspacial;

	/**
	 * Costructora por defecto
	 */
	public PlaceCell() {}

	/**
	 * Modifica el lugar intruciendo uno nuevo ya que inicialmente esta null
	 * 
	 * @param lug
	 *            : cadena que indica el lugar en donde esta walle
	 */
	public void setLugar(String lug) {
		this.lugar = lug;
	}
	/**
	 * Modificamos la descripcion del lugar introduciendo uno nuevo
	 * @param description : la descripcion que sustituir� a la anterior
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Obtenemo el lugar
	 * 
	 * @return : el lugar al cual repesnta el boton
	 */
	public String getLugar() {
		return this.lugar;
	}
	
	/**
	 * Se obtiene la descripcion
	 */
	public String getDescription() {
		return description;
	}
	
	/**
	 * Metodo que muestra por el boton el texto correspondiente al lugar
	 */
	public void mostrarTextoBoton() {
		setText(lugar);
	}
	/**
	 * Indica si el boton se corresponde con al lugar en el que se encuantra la nave espacial
	 * @param naveEspacial
	 */
	public void setNaveEspacial(boolean naveEspacial) {
		this.naveEspacial = naveEspacial;
	}	
	@Override
	public boolean isSpaceship() {
		return this.naveEspacial;
	}

	@Override
	public String toStringAllItemsPlace() {
		return null;
	}

	/**
	 * Cambia el color del boton situado en la matriz de botones, esto lo hace cada vez que walle avance
	 * @param color : color al que tienen que cambiar el boton (verde o gis)
	 */
	public void cambioColor(Color color) {
		setBackground(color);
	}
	
}
