package GUI;

import java.awt.EventQueue;
import java.awt.Label;

import javax.swing.JFrame;
import javax.swing.table.DefaultTableModel;

import model.TicketHolder;

import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;

public class MainWindow {

	private JFrame frame;
	private JTable table;
	private DefaultTableModel dTable;
	
	private TicketHolder tHolder = new TicketHolder();
	// Initialize ticket holder here
	String[][] tickets = { 
			{"A", "A", "A", "A"},
			{"A", "A", "A", "A"},
			{"A", "A", "A", "A"},
			{"A", "A", "A", "A"},
			{"A", "A", "A", "A"},
			{"A", "A", "A", "A"},
			{"A", "A", "A", "A"},
			{"A", "A", "A", "A"},
	};

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow window = new MainWindow();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 862, 632);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		tHolder.populateFromArray(tickets);
		
		table = new JTable();
		table.setBounds(15, 30, 672, 524);
		frame.getContentPane().add(table);
		
		dTable = (DefaultTableModel) table.getModel();
		dTable.addColumn("Device");
		dTable.addColumn("First Name");
		dTable.addColumn("Last Name");
		dTable.addColumn("Date");
		populateTable();

		
		
		/**
		 * Buttons & such
		 */
		JButton btnAdd = new JButton("Add Ticket");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// TODO: Open Add Ticket Frame & update table after frame is filled out
				openAddWindow();
			}
		});
		btnAdd.setBounds(708, 30, 102, 23);
		frame.getContentPane().add(btnAdd);
		
		JButton btnDelete = new JButton("Delete Ticket");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println(table.getSelectedRow());
				tHolder.deleteByRow(table.getSelectedRow());
				repopulateTable();
			}
		});
		btnDelete.setBounds(708, 64, 102, 23);
		frame.getContentPane().add(btnDelete);
		
		JButton btnView = new JButton("View Ticket");
		btnView.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// TODO: Open frame with ticket info & two text fields representing assembly and disassembly stacks
			}
		});
		btnView.setBounds(708, 98, 102, 23);
		frame.getContentPane().add(btnView);
		
		/**
		 * Labels that I've been using for testing
		 */
		Label lab1 = new Label();
		lab1.setText("This is a test");
		Label lab2 = new Label();
		lab2.setText("This is another test");

		table.add(lab1);
		table.add(lab2);
		
		JLabel lblDevice = new JLabel("Device");
		lblDevice.setHorizontalAlignment(SwingConstants.LEFT);
		lblDevice.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblDevice.setBounds(15, 4, 87, 23);
		frame.getContentPane().add(lblDevice);
		
		JLabel lblFirstName = new JLabel("First Name");
		lblFirstName.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblFirstName.setBounds(175, -2, 102, 35);
		frame.getContentPane().add(lblFirstName);
		
		JLabel lblLastName = new JLabel("Last Name\r\n");
		lblLastName.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblLastName.setBounds(348, -6, 102, 43);
		frame.getContentPane().add(lblLastName);
		
		JLabel lblDate = new JLabel("Date");
		lblDate.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblDate.setBounds(516, -2, 52, 35);
		frame.getContentPane().add(lblDate);
		table.updateUI();
		Label temp = new Label();
		


	}
	
	// Populates the table with tickets
	private void populateTable() { 
		for(int i = 0; i < tHolder.getNumTickets(); i++) {
			dTable.addRow(tHolder.displayLineOnGraph(i));
		}
	}
	
	private void resetTable() {
		int rowCount = dTable.getRowCount();
		while(dTable.getRowCount() > 0)
			dTable.removeRow(0);
	}
	
	private void repopulateTable() {
		resetTable();
		populateTable();
		System.out.println(tHolder.toString());
	}
	
	private void openAddWindow() {

	}
}
