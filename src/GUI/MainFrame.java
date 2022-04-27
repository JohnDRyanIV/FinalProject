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
import javax.swing.table.DefaultTableModel;

import database.DBTicketHolder;
import model.TicketHolder;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;

/**
 * Allows viewing and deleting of objects using a table as part of a GUI.
 * @author John Ryan
 *
 */
public class MainFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private AddDialog aDialog = new AddDialog();
	private ViewDialog vDialog = new ViewDialog();
	private JTable table;
	private DefaultTableModel dTable;
	private JCheckBox chckbxAscending = new JCheckBox("Ascending");
	
	private TicketHolder tHolder = new TicketHolder();
	private DBTicketHolder dbTicketHolder = new DBTicketHolder();

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
				// TODO: Open Add Ticket Frame & update table after frame is filled out
				try {
					openAddWindow();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnAdd.setBounds(957, 11, 127, 23);
		getContentPane().add(btnAdd);
		
		JButton btnDelete = new JButton("Delete Ticket");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				deleteTicket();
			}
		});
		btnDelete.setBounds(957, 45, 127, 23);
		getContentPane().add(btnDelete);
		
		JButton btnView = new JButton("View Ticket");
		btnView.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				openViewWindow();
			}
		});
		btnView.setBounds(957, 79, 127, 23);
		getContentPane().add(btnView);
		
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
		System.out.println(tHolder.toString());
	}
	
	/**
	 * deletes Ticket in currently selected row from both database and TicketHolder.
	 */
	private void deleteTicket() {
		System.out.println(getCurrentRow());
		tHolder.deleteByRow(getCurrentRow());
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
	 * opens window to add tickets then resets add window
	 * @throws SQLException - If tickets window can't connect to database
	 */
	private void openAddWindow() throws SQLException {
		aDialog.setVisible(true);
		aDialog = new AddDialog();
		tHolder = dbTicketHolder.populateFromDB(tHolder);
		repopulateTable();

	}
	
	/**
	 * opens window to view tickets then resets view window
	 */
	private void openViewWindow() {
		if(!table.getSelectionModel().isSelectionEmpty()) { // if any row on the table is selected
			vDialog.setTicket(tHolder.getTicket(getCurrentRow()));
			vDialog.setVisible(true);
			vDialog = new ViewDialog();
		}

	}
}
