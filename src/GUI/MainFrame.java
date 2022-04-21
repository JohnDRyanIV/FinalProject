package GUI;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import model.TicketHolder;

public class MainFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private AddDialog aDialog = new AddDialog();
	private ViewDialog vDialog = new ViewDialog();
	private JTable table;
	private DefaultTableModel dTable;
	
	private TicketHolder tHolder = new TicketHolder();
	// Initialize ticket holder here
	/*String[][] tickets = { 
			{"A", "A", "A", "A"},
			{"A", "A", "A", "A"},
			{"A", "A", "A", "A"},
			{"A", "A", "A", "A"},
			{"A", "A", "A", "A"},
			{"A", "A", "A", "A"},
			{"A", "A", "A", "A"},
			{"A", "A", "A", "A"},
	};*/

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
		setBounds(100, 100, 1107, 648);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		// tHolder.populateFromArray(tickets);

		try {
			tHolder.populateFromDB();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		table = new JTable();
		table.setDefaultEditor(Object.class, null);
		final JScrollPane scrollTable = new JScrollPane(table);
		scrollTable.setBounds(10, 11, 937, 587);
		getContentPane().add(scrollTable);
		
		dTable = (DefaultTableModel) table.getModel();
		dTable.addColumn("Device");
		dTable.addColumn("Problem");
		dTable.addColumn("First Name");
		dTable.addColumn("Last Name");
		dTable.addColumn("Date");
		populateTable();
		
		//--- Labels ---//
		
		JLabel lblOrganize = new JLabel("Organize by...");
		lblOrganize.setBounds(957, 113, 127, 23);
		getContentPane().add(lblOrganize);
		
		//--- Buttons ---//
		
		JButton btnAdd = new JButton("Add Ticket");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// TODO: Open Add Ticket Frame & update table after frame is filled out
				openAddWindow();
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
				// TODO: Open frame with ticket info & two text fields representing assembly and disassembly stacks
				openViewWindow();
			}
		});
		btnView.setBounds(957, 79, 127, 23);
		getContentPane().add(btnView);
		
		JButton btnDevice = new JButton("Device");
		btnDevice.setBounds(957, 147, 127, 23);
		getContentPane().add(btnDevice);
		
		JButton btnFirstName = new JButton("First Name");
		btnFirstName.setBounds(957, 181, 127, 23);
		getContentPane().add(btnFirstName);
		
		JButton btnLastName = new JButton("Last Name");
		btnLastName.setBounds(957, 215, 127, 23);
		getContentPane().add(btnLastName);
		
		JButton btnProblem = new JButton("Problem");
		btnProblem.setBounds(957, 249, 127, 23);
		getContentPane().add(btnProblem);
		
		JButton btnDate = new JButton("Date");
		btnDate.setBounds(957, 283, 127, 23);
		getContentPane().add(btnDate);
		table.updateUI();
	}
	
	// Populates the table with tickets
	private void populateTable() { 
		for(int i = 0; i < tHolder.getNumTickets(); i++) {
			dTable.addRow(tHolder.displayLineOnGraph(i));
		}
	}
	
	// removes all rows from table
	private void resetTable() {
		while(dTable.getRowCount() > 0)
			dTable.removeRow(0);
	}
	
	private void repopulateTable() {
		resetTable();
		populateTable();
		System.out.println(tHolder.toString());
	}
	
	// deletes ticket from both database and ticketholder
	private void deleteTicket() {
		System.out.println(table.getSelectedRow());
		System.out.println(table.getSelectedRows);
		tHolder.deleteByRow(table.getSelectedRow());
		repopulateTable();
	}
	
	private void openAddWindow() {
		//this.setVisible(false);
		aDialog.setVisible(true);
		tHolder = new TicketHolder();
		try {
			tHolder.populateFromDB();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		repopulateTable();

		// after aFrame is no longer showing
		//this.setVisible(true);
	}
	
	private void openViewWindow() {
		vDialog.setVisible(true);
	}
}
