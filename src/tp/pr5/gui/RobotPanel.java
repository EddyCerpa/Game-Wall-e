package tp.pr5.gui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Collections;


import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import tp.pr5.RobotEngineObserver;
import tp.pr5.items.InventoryObserver;
import tp.pr5.items.Item;
import tp.pr5.items.OtherItem;

/**
 * RobotPanel muestra información sobre el robot y su inventario. Más
 * específicamente, que contiene etiquetas para mostrar el combustible robot y
 * el peso de material reciclado y una tabla que representa el inventario del
 * robot. Cada fila muestra la información sobre un tema que figura en el
 * inventario.
 * 
 * @author Eddy Cuizaguana Cerpa
 * 
 */

@SuppressWarnings("serial")
public class RobotPanel extends JPanel implements RobotEngineObserver, InventoryObserver {	
	private static String LABEL_FUEL = "lblFuel";
	private static String LABEL_RECYCLED = "lblRecycled";
	private JLabel lblFuel;
	private JLabel lblRecycled;
	private JTable tableItem;
	private JScrollPane scrollPane;
	private DefaultTableModel modelo;
	private ArrayList<Item> listaItem;
	private InstructionsPanel instructPanel;

	/**
	 * Costructora por defecto
	 * @param gameController 
	 */
	public RobotPanel (GUIController gameController, InstructionsPanel instructionsPanel){
		this.instructPanel = instructionsPanel;
		// lista de los elemento que el robot tiene
		this.listaItem = new ArrayList<Item>();
		creaPanel();
		addObservadores(gameController);
		tableItem.addMouseListener(new selecItem());
	}
	
	
	/**
	 * Avisamos al controlodar de la GUI que que queremos añadir los observadores log 
	 * y matriz de botones, las mismas observaran a navigation module, es decir 
	 * cambiaran solo cuando navigation module se los diga
	 * @param gameController : hace referncia al controlador de la gui
	 */
	private void addObservadores(GUIController gameController) {
		gameController.addRobotEngineObserver(this);
		gameController.addInventarioObserver(this);
		
	}
	
	
	
	/**
	 * Crea el panel encargdo de mostrar la informacion del fuel, garbage y
	 * tabla de itemscon y los configura adecuadamente
	 * 
	 */
	public void creaPanel() {
		setBorder(new TitledBorder(null, "RobotInfo", TitledBorder.LEADING,
				TitledBorder.TOP, null, null));
		setLayout(new BorderLayout(0, 0));

		JPanel panel = new JPanel();
		add(panel, BorderLayout.NORTH);
		panel.setLayout(new GridLayout(1, 0, 0, 0));

		lblFuel = new JLabel("Fuel 0");
		lblRecycled = new JLabel(" Recycled 0");
		scrollPane = new JScrollPane();
		modelo = new DefaultTableModel();
		tableItem = new JTable(modelo);
		
		lblFuel.setHorizontalAlignment(SwingConstants.RIGHT);
		lblFuel.setName(LABEL_FUEL);
		lblRecycled.setHorizontalAlignment(SwingConstants.LEFT);
		lblRecycled.setName(LABEL_RECYCLED);
		
		add(scrollPane, BorderLayout.CENTER);
		panel.add(lblFuel);
		panel.add(lblRecycled);
		modelo.addColumn("Id");
		modelo.addColumn("Description");
		scrollPane.setViewportView(tableItem);
	}
	
	
	@Override
	public void robotUpdate(int fuel, int recycledMaterial) {
		this.lblFuel.setText("fuel: " + fuel);
		this.lblRecycled.setText(" MetialRecicled: " + recycledMaterial);
	}
	//-----------------
	@Override
	public void addelementoTabla(String itemName, String itemDescription) {
		//Añadimos in Item al contenedor de item de del RobotPanel
		addItem(new OtherItem(itemName, itemDescription));
		//eliminamos los ementos de la tabla
		reiniciarJTable();
		//añadimos los elemtos a la tabla
		addElementosTabla();	
	}
	@Override
	public void deletelementoTabla(String itemName, String itemDescription) {
		quitaElemtTabla(itemName);	
		reiniciarJTable();
		addElementosTabla();
	}
	//-- Metodos no utilizados por este panel
	@Override
	public void robotSays(String message) {}
	@Override
	public void raiseError(String msg) {}
	@Override
	public void communicationHelp(String help) {}
	@Override
	public void engineOff(boolean atShip) {}
	@Override
	public void communicationCompleted() {}
	@Override
	public void inventoryScanned(String inventoryDescription) {}
	@Override
	public void itemScanned(String description) {}
	@Override
	public void itemEmpty(String itemName) {
		quitaElemtTabla(itemName);	
		reiniciarJTable();
		addElementosTabla();
	}
	
	/**
	 * Metodo que limpia la tabla por completo antes de su nueva escritura
	 * 
	 * @param Tabla
	 *            : tabla que sera reiniciada
	 */
	public  void reiniciarJTable() {
		while (modelo.getRowCount() > 0)
			modelo.removeRow(0);
	}
	
	/**
	 * Añadimos elentos del contenedor de esa vista a la tabla 
	 */
	public void addElementosTabla() {
		Object[] fila = new Object[2];
		for (int i = 0; i < listaItem.size(); i++) {
			fila[0] = listaItem.get(i).getId();
			fila[1] = listaItem.get(i).toStringInterfaz();
			modelo.setNumRows(i); // escribimos ne la fila cero
			modelo.addRow(fila); // llenamos la fila i
		}
	}
	
	/**
	 * Añade un articulo al contenedor . La operacion puede fallar, devuelve
	 * false
	 * 
	 * @param item
	 *            : El nombre del elemento que se va a agregar.
	 * @return : true si y solo si se agrega el elemento, es decir, un elemento
	 *         con el mismo identificador que no exista en el contenedor. false
	 *         si ya existe.
	 */
	public boolean addItem(Item item) {
		boolean add = false;
		int indice = Collections.binarySearch(listaItem, item);
		if (indice < 0) {
			if (listaItem.size() > 0) {
				indice = -(indice + 1);
				listaItem.add(indice, item);
			} else
				this.listaItem.add(item);
			add = true;
		}
		return add;
	}
	
	/**
	 * Retorna y elimina un elemento del inventario. Esta operacion puede fallar
	 * y devuelve null.
	 * 
	 * @param id
	 *            : Nombre del elemento.
	 * @return : Un articulo si y si existe lo si el elemento identificado por
	 *         id existe en el inventario. De lo contrario, devuelve null.
	 */

	public Item quitaElemtTabla(String id) {
		Item itemEncontrado = null;
		// creamos un objeto de tipo OtherItem poder realiza la busqueda binaria
		Item buscaItem = new OtherItem(id, null);
		int indice = Collections.binarySearch(listaItem, buscaItem);
		if (indice >= 0) {
			itemEncontrado = this.listaItem.get(indice);
			this.listaItem.remove(indice);
		}
		return itemEncontrado;
	}
	//--------------------------------------------------------------
	//--------------------------------------------------------------
	/**
	 * Clase que escucha un evento del raton, la misma es utilizada para
	 *  saber que elemento de la tabla ha sido selecionado
	 * @author Eddy Cuizaguana Cerpa
	 */
	public class selecItem implements MouseListener{

		/**
		 * @param e : tipo de evento 
		 */
		@Override
		public void mouseClicked(MouseEvent e) {
			Point point =	e.getPoint();
			int row = tableItem.rowAtPoint(point);
			/* row devolvera -1 si se ha clicado fuera de la fila pero dentro de la tabla, 
			 * si no devolvera el indice de la fila en la que se ha clicado. */
			if (row != -1) {
			String id =  (String) tableItem.getValueAt(row, 0);
			instructPanel.setTextFiel(id);
			}
		}

		@Override
		public void mouseEntered(MouseEvent e) {}
		@Override
		public void mouseExited(MouseEvent e) {}
		@Override
		public void mousePressed(MouseEvent e) {}
		@Override
		public void mouseReleased(MouseEvent e) {}
		
	}
	
}
