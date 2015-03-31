package tp.pr5.gui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import tp.pr5.Rotation;

@SuppressWarnings("serial")
public class InstructionsPanel extends JPanel {
	private static String BUTTON_MOVE = "btnMove";

	private static String BUTTON_TURN = "btnTurn";
	private static String BUTTON_GIRO = "comboBoxGiro";
	private static String BUTTON_PICK = "btnPick";
	private static String TEXT_FIELD = "textField";
	private static String BUTTON_DROP = "btnDrop";
	private static String BUTTON_OPERATE = "btnOperate";
	private static String BUTTON_QUIT = "btnQuit";

	private JButton btnMove;
	@SuppressWarnings("rawtypes")
	private JComboBox comboBoxGiro;
	private JButton btnTurn;
	private JButton btnPick;
	private JTextField textField;
	private JButton btnDrop;
	private JButton btnOperate;
	private JButton btnQuit;
	//-------
	private GUIController guiControler;
	
	public InstructionsPanel(GUIController gameController) { 
		this.guiControler = gameController;
		initPanelInstructions();
		addEscuchadores();
	}

	private void addEscuchadores() {
		btnTurn.addActionListener(new Turn());
		btnMove.addActionListener(new Move());
		btnPick.addActionListener(new Pick());
		btnDrop.addActionListener(new Drop());
		btnOperate.addActionListener(new Operate());
		btnQuit.addActionListener(new QuitButon());
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void initPanelInstructions() {
		setBorder(new TitledBorder(null, "Instructions", TitledBorder.LEADING,
				TitledBorder.TOP, null, null));
		setLayout(new GridLayout(4, 2, 0, 0));

		// Creamos los componentes 
		btnMove = new JButton("MOVE");
		btnQuit = new JButton("QUIT");
		btnTurn = new JButton("TURN");
		comboBoxGiro = new JComboBox();
		btnPick = new JButton("PICK");
		textField = new JTextField();
		btnDrop = new JButton("DROP");
		btnOperate = new JButton("OPERATE");
		
		// modificamos el nombre de los componentes
		btnMove.setName(BUTTON_MOVE);
		btnQuit.setName(BUTTON_QUIT);
		btnTurn.setName(BUTTON_TURN);
		comboBoxGiro.setName(BUTTON_GIRO);
		comboBoxGiro.setModel(new DefaultComboBoxModel(new String[] { "LEFT",
				"RIGHT" }));
		btnPick.setName(BUTTON_PICK);
		textField.setName(TEXT_FIELD);
		textField.setColumns(10);
		btnDrop.setName(BUTTON_DROP);
		btnDrop.setPreferredSize(new java.awt.Dimension(20, 20));
		btnOperate.setName(BUTTON_OPERATE);
		
		//a�adimos los componentes al panel
		add(btnMove);
		add(btnQuit);
		add(btnTurn);
		add(comboBoxGiro);
		add(btnPick);
		add(textField);
		add(btnDrop);
		add(btnOperate);
	}
	
	/**
	 * Modificamos el textFiel con un nuevo string,
	 * @param text : string que reeplazara al anterior
	 */
	public void setTextFiel(String text){
		textField.setText(text);
	}

	
	//--------------------------------------------
	//clases escuchadoras
	//--------------------------------------------

	/**
	 * Clase que avisa al controlador que tene que ejecutar la instrucion Turn
	 * Solo se produce un evento si se ha pulsado sobre el boton turn.
	 * 
	 * @author Eddy Cuizaguana Cerpa
	 *
	 */
	class Turn implements ActionListener{
		/**
		 * Metodo que escucha el pulsar de un boton que en nuetsro caso es el "Turn"
		 * La misma obtiene el elemnto que que hay en componente (comboBoxGiro) para informar al controlador 
		 * el sentido del giro que se quiere realzar
		 */
		@Override
		public void actionPerformed(ActionEvent arg0) {
			if (comboBoxGiro.getSelectedItem().equals("LEFT")){
				guiControler.turnAction(Rotation.LEFT);
			}
			else guiControler.turnAction(Rotation.RIGHT);
		}
		
	}
	//------------------
	/**
	 * Clase que avisa al controlador que tene que ejecutar la instrucion Move
	 * Solo se produce un evento si se ha pulsado sobre el boton Move.
	 *  
	 * @author Eddy Cuizaguana Cerpa
	 *
	 */
	class Move implements ActionListener{
		/**
		 * Metodo que escucha el pulsar de un boton que en nuetsro caso es el "Move"
		 * La misma para informa al controlador el avance del robot
		 */
		@Override
		public void actionPerformed(ActionEvent arg0) {
				guiControler.moveAction();
		}
		
	}
	//------------------
	/**
	 * Clase que avisa al controlador que tene que ejecutar la instrucion Pick
	 * Solo se produce un evento si se ha pulsado sobre el boton Pick y hay un elemento (cadena string)
	 * en el textField que indica el id del elemnto para ser obtenido
	 * @author Eddy Cuizaguana Cerpa
	 *
	 */
	class Pick implements ActionListener{
		/**
		 * Metodo que escucha el pulsar de un boton que en nuetsro caso es el "Pick"
		 * La misma informa al controlador el que el robot tiene que obtener un elemnto id(nombre del elemento)
		 * si no se introduce el id del elemnto en el textField mostrara un mensaje que dice "Into a id Item"
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			String text = textField.getText();
			if (!text.equalsIgnoreCase( "")){
				guiControler.pickAction(text);
				textField.setText("");
			}
			else JOptionPane.showMessageDialog(null, "Into a id Item");
			
		}
		
	}
	//------------------
	/**
	 * Clase que avisa al controlador que tene que ejecutar la instrucion Drop
	 * Solo se produce un evento si se ha pulsado sobre el boton Drop y hay un elemento (cadena string)
	 * en el textField que indica el id del elemnto para ser soltado.
	 * @author Eddy Cuizaguana Cerpa
	 *
	 */
	class Drop implements ActionListener{
		/**
		 * Metodo que escucha el pulsar de un boton que en nuetsro caso es el "Drop"
		 * La misma informa al controlador el que el robot tiene que soltar un elemnto id(nombre del elemento)
		 * * si no se introduce el id del elemnto en el textField mostrara un mensaje que dice "Into a id Item"
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			String text = textField.getText();
			if (!text.equalsIgnoreCase( "")){
			guiControler.dropAction(text);
			textField.setText("");
			}
			else JOptionPane.showMessageDialog(null, "Into a id Item");
		}
		
	}
	//------------------
	/**
	 * Clase que avisa al controlador que tene que ejecutar la instrucion Operate
	 * Solo se produce un evento si se ha pulsado sobre el boton Operate y hay un elemento (cadena string)
	 * en el textField que indica el id del elemnto que se quiere operar.
	 * @author Eddy Cuizaguana Cerpa
	 *
	 */	
	class Operate implements ActionListener{
		/**
		 * Metodo que escucha el pulsar de un boton que en nuetsro caso es el "Operate"
		 * La misma informa al controlador el que el robot tiene que operar con un elemnto id(nombre del elemento)
		 * * si no se introduce el id del elemnto en el textField mostrara un mensaje que dice "Into a id Item"
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			String text = textField.getText();
			if (!text.equalsIgnoreCase( "")){
			guiControler.operateAction(text);
			textField.setText("");
			}
			else JOptionPane.showMessageDialog(null, "Into a id Item");
		}
		
	}
	//---------------------------------
	/**
	 * Clase que sale de la aplicacion realiza para ello la accion quit, ates de eso mostrar� un mensaje de confirmacion
	 * @author Eddy Cuizaguana Cerpa
	 *
	 */
	class QuitButon extends JOptionPane implements ActionListener {
		/**
		 * Metodo que sale del juego cerrando la aplicacion, y muestra un mensaje para ver si se quiere realizar dicha aci�n
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			int seleccion = JOptionPane.showConfirmDialog(this, "Want to quit?", "Confirmacion de QUIT", JOptionPane.YES_NO_OPTION);
			if(seleccion == 0) {
				guiControler.quitAction();
				System.exit(0);
			}
		}
	}
	//-------------------------------
}

