package tp.pr5.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

/**
 * Clase que representa la barra de menu, en ella se ha puetso la opcion de
 * salir
 * 
 * @author Eddy Cuizaguana Cerpa
 * 
 */

@SuppressWarnings("serial")
public class BarraDeMenu extends JMenuBar {

	private JMenu munuArchivo;
	private JMenuItem opcionQuit;
	private GUIController gameContol;

	
	public BarraDeMenu(GUIController gameController){
		gameContol = gameController;
		crearBarraDeMenu(gameController);
		
	}
	/**
	 * Creamos la barra de menu e inicia los compenentes de la barra de menu y
	 * los configura adecuadamente
	 * @param gameController 
	 */
	public void crearBarraDeMenu(GUIController gameController) {
		munuArchivo = new JMenu("Archivo");
		opcionQuit = new JMenuItem("Quit");
		opcionQuit.setName("btnQuit");
		munuArchivo.add(opcionQuit);
		add(munuArchivo);
		
		opcionQuit.addActionListener(new Quit());
		
	}

	//--------------------------------
	/**
	 * Clase que sale de la aplicacion realiza para ello la accion quit, ates de eso mostrará un mensaje de confirmacion
	 * @author Eddy Cuizaguana Cerpa
	 *
	 */
	public class Quit extends JOptionPane implements ActionListener {
		/**
		 * Metodo que sale del juego cerrando la aplicacion, y muestra un mensaje para ver si se quiere realizar dicha ación
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			int seleccion = JOptionPane.showConfirmDialog(this, "Want to quit?", "Confirmacion de QUIT", JOptionPane.YES_NO_OPTION);
			if(seleccion == 0) {
				gameContol.quitAction();
				System.exit(0);
			}
		}
		
	}
	//----------------------------
}
