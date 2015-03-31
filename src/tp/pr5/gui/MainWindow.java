package tp.pr5.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSplitPane;

import tp.pr5.RobotEngineObserver;
import tp.pr5.gui.BarraDeMenu;
import tp.pr5.gui.InstructionsPanel;
import tp.pr5.gui.NavigationPanel;
import tp.pr5.gui.RobotPanel;

/**
 * Esta clase crea la ventana de la interfaz Swing. El MainWindow contiene los
 * siguientes componentes: Un men� con la acci�n QUIT Un panel de acci�n con
 * varios botones para realizar MOVE, a su vez, operan, selecci�n, y las
 * acciones de la gota. Adicionalmente tiene un cuadro combinado de giro y
 * rotaci�n de un campo de texto para escribir el nombre del elemento que el
 * robot quiere elegir el lugar actual Un RobotPanel de que muestra la
 * informaci�n del robot (el material de combustible y reciclados) y el
 * inventario de robot, que muestra una tabla con los nombres de elementos y
 * descripciones que el robot lleva. El usuario puede seleccionar los elementos
 * que figuran en el inventario con el fin de DROP u operar un art�culo Un
 * NavigationPanel que representa la ciudad. Se muestran los lugares que el
 * robot ha visitado y un icono que representa el titulo robot. El t�tulo robot
 * se actualiza cuando el usuario realiza una accion TURN. Los lugares visibles
 * se actualizan cuando el robot realiza una accion de movimiento. Una vez que
 * el lugar es visitado, el usuario puede hacer clic en �l con el fin de mostrar
 * la descripci�n del lugar (similar al comando RADAR). Un Panel de informaci�n
 * que muestra informaci�n sobre los diferentes eventos que se producen durante
 * el juego En esta ventana se implementa la interfaz GameObserver para ser
 * notificado sobre los eventos del juego.
 * 
 * @author Eddy Cuizaguana Cerpa
 * 
 */
@SuppressWarnings("serial")
public class MainWindow extends JFrame implements RobotEngineObserver {

	private BarraDeMenu barraMenu;
	private JPanel contentPane;
	private JSplitPane splitPane;
	private RobotPanel robotInfoPanel;
	private InstructionsPanel instructionsPanel;
	private NavigationPanel navigationPanel;
	private InfoPanel infoPanel;
	
	
	/**
	 * Crea la ventana y los paneles utilizando componentes Swing.
	 * @param gameController
	 */
	public MainWindow(GUIController gameController) {
		inicializaVistaWalle(gameController);
		gameController.addRobotEngineObserver(this);
	}
	

	private void inicializaVistaWalle(GUIController gameController) {
		setTitle("WALLE The garbage collector");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 675, 448);
		// menu bar

		barraMenu = new BarraDeMenu(gameController);
		setJMenuBar(barraMenu);

		// NuestroPanelPrincipal
		contentPane = new JPanel();
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		// --- navigationPanel---
		navigationPanel = new NavigationPanel(gameController);
		contentPane.add(navigationPanel, BorderLayout.CENTER);

		// ---creamosElSplit
		splitPane = new JSplitPane();
		contentPane.add(splitPane, BorderLayout.NORTH);

		// ---panelIzquiedo Instruccion
		instructionsPanel = new InstructionsPanel(gameController);
		splitPane.setLeftComponent(instructionsPanel);
		splitPane.setPreferredSize(new java.awt.Dimension(100, 100));// ---

		// ---panelderechoInfo
		robotInfoPanel = new RobotPanel(gameController,instructionsPanel);
		robotInfoPanel.setPreferredSize(new java.awt.Dimension(100, 200));
		splitPane.setRightComponent(robotInfoPanel);
		
		// --infoPanel
		infoPanel = new InfoPanel(gameController);
		contentPane.add(infoPanel, BorderLayout.SOUTH);
		infoPanel.setPreferredSize(new java.awt.Dimension(100, 20));
	}

	/**
	 * Metodo encargado de arrancar la ventana.
	 */
	public void arranca(){
        EventQueue.invokeLater(new Runnable(){
        	public void run() {
        		setVisible(true);
        	}
        });		
	}
	
	@Override
	public void engineOff(boolean atShip) {
		if(atShip){
			JOptionPane.showMessageDialog(this,"I am at my spaceship. Bye bye");
		}
		else JOptionPane.showMessageDialog(this,"Shutting down... Bye bye");
		System.exit(0);
	}
	//--- No se implementa por que no hace flata utiizarlos en esta clase
	@Override
	public void raiseError(String msg) {}
	@Override
	public void communicationHelp(String help) {}
	@Override
	public void communicationCompleted() {}
	@Override
	public void robotUpdate(int fuel, int recycledMaterial) {}
	@Override
	public void robotSays(String message) {}
}
