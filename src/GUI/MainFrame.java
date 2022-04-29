package GUI;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import database.DBTicketHolder;
import model.TicketHolder;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;

/**
 * Allows viewing and deleting of Ticket objects using a table as part of a GUI.
 * @author John Ryan
 *
 */
public class MainFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JTable table;
	private DefaultTableModel dTable;
	private JCheckBox chckbxAscending = new JCheckBox("Ascending");
	
	private TicketHolder tHolder = new TicketHolder();	// Holds ticket objects to be output to table
	private DBTicketHolder dbTicketHolder = new DBTicketHolder(); // Interfaces tHolder with database
	
	private JButton btnDelete = new JButton("Delete Ticket");	// button that deletes currently selected ticket
	private JButton btnView = new JButton("View Ticket"); // button that views currently selected ticket

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame frame = new MainFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainFrame() {
		this.setTitle("Ticket Manager");
		setBounds(100, 100, 1114, 648);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);

		// Populate the ticket holder object from database
		tHolder = dbTicketHolder.populateFromDB(tHolder);

		
		table = new JTable();
		table.setDefaultEditor(Object.class, null);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.addMouseListener(new java.awt.event.MouseAdapter() {
			@Override
			public void mousePressed(java.awt.event.MouseEvent evt) {
				updateButtonsEnable();
			}
		});
		

		final JScrollPane scrollTable = new JScrollPane(table);
		scrollTable.setBounds(10, 11, 937, 587);
		getContentPane().add(scrollTable);
		
		dTable = (DefaultTableModel) table.getModel();
		dTable.addColumn("First Name");
		dTable.addColumn("Last Name");
		dTable.addColumn("Date");
		dTable.addColumn("Device");
		dTable.addColumn("Problem");
		populateTable();
		
		//--- Buttons ---//
		
		JButton btnAdd = new JButton("Add Ticket");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					openAddWindow();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnAdd.setBounds(957, 11, 127, 23);
		getContentPane().add(btnAdd);
		
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				deleteTicket();
				updateButtonsDisable();
			}
		});
		btnDelete.setBounds(957, 45, 127, 23);
		getContentPane().add(btnDelete);
		

		btnView.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				openViewWindow();
			}
		});
		btnView.setBounds(957, 79, 127, 23);
		getContentPane().add(btnView);
		
		btnDelete.setEnabled(false);
		btnView.setEnabled(false);
		
		chckbxAscending.setBounds(971, 142, 97, 23);
		chckbxAscending.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				repopulateTable();
			}
		});
		getContentPane().add(chckbxAscending);
		
		String[] comboOptions = {"Organize by...", "Device", "Problem", "First Name", "Last Name", "Date"};
		JComboBox comboBoxOrganize = new JComboBox(comboOptions);
		comboBoxOrganize.addItemListener(this::comboBoxItemStateChanged);
		comboBoxOrganize.setBounds(957, 113, 127, 22);
		getContentPane().add(comboBoxOrganize);
		table.updateUI();
	}
	
	/**
	 * retrieves current row, with extra operations done if the table is ordered in ascending
	 * @return - int representing currently selected row
	 */
	private int getCurrentRow() {
		if(chckbxAscending.isSelected()) {
			return table.getRowCount() - table.getSelectedRow() - 1;
		}
		return table.getSelectedRow();
	}
	
	/**
	 * Populates the table with Ticket objects contained in TicketHolder. Changes order
	 * depending on if ascending checkbox is selected.
	 */
	private void populateTable() { 
		if(chckbxAscending.isSelected())
			for(int i = tHolder.getNumTickets() - 1; i > -1; i--)
				dTable.addRow(tHolder.displayRowOnGraphAscending(i));
		else
			for(int i = 0; i < tHolder.getNumTickets(); i++)
				dTable.addRow(tHolder.displayRowOnGraphDescending(i));
	}
	
	/**
	 * removes all rows from table
	 */
	private void resetTable() {
		while(dTable.getRowCount() > 0)
			dTable.removeRow(0);
	}
	
	/**
	 * Removes all rows & repopulates table with TicketObjects from TicketHolder.
	 */
	private void repopulateTable() {
		resetTable();
		populateTable();
	}
	
	/**
	 * deletes Ticket in currently selected row from both database and TicketHolder.
	 */
	private void deleteTicket() {
		tHolder.deleteByIndex(getCurrentRow());
		repopulateTable();
	}
	
	/**
	 * Listens to see if combo box has had a new option selected & reorganizes table if so
	 * @param e - ItemEvent for combo box
	 */
	public void comboBoxItemStateChanged(ItemEvent e) {
	    if (e.getStateChange() == ItemEvent.SELECTED) {
	        String selected = (String) e.getItem();
	        if(selected == "Device")
	        	tHolder.orgByDevice();
	        else if(selected == "Problem")
	        	tHolder.orgByProblem();
	        else if(selected == "First Name")
	        	tHolder.orgByFirst();
	        else if(selected == "Last Name")
	        	tHolder.orgByLast();
	        else if(selected == "Date")
	        	tHolder.orgByDate();
	        repopulateTable();
	    }
	}
	
	/**
	 * Listens to see if check box ascending has been toggled & reorganizes table in ascending/descending order if so
	 * @param e - ItemEvent for check box
	 */
	public void checkBoxItemStateChange(ItemEvent e) {
		if (e.getStateChange() == ItemEvent.SELECTED)
			repopulateTable();
	}
	
	/**
	 * opens window to add tickets
	 * @throws SQLException - If tickets window can't connect to database
	 */
	private void openAddWindow() throws SQLException {
		AddDialog aDialog = new AddDialog();
		aDialog.setVisible(true);
		aDialog = new AddDialog();
		tHolder = dbTicketHolder.populateFromDB(tHolder);
		repopulateTable();

	}
	
	/**
	 * opens window to view tickets
	 */
	private void openViewWindow() {
		ViewDialog vDialog = new ViewDialog();
		if(!table.getSelectionModel().isSelectionEmpty()) { // if any row on the table is selected
			vDialog.setTicket(tHolder.getTicket(getCurrentRow()));
			vDialog.setVisible(true);
		}

	}
	
	/**
	 * enables the delete and view buttons. This is to prevent the user from selecting them before an element on the table is selected
	 */
	private void updateButtonsEnable() {
		btnDelete.setEnabled(true);
		btnView.setEnabled(true);
	}
	
	/**
	 * disables the delete and view buttons. This is to prevent the user from pressing a button again after an element on the
	 * table has been deleted and before a new element has been selected
	 */
	private void updateButtonsDisable() {
		btnDelete.setEnabled(false);
		btnView.setEnabled(false);
	}
}
