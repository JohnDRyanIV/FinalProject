package GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import model.TicketHolder;

public class MainFrame extends JFrame {

	private JPanel contentPane;
	private AddFrame aFrame = new AddFrame();
	private ViewFrame vFrame = new ViewFrame();
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
		setBounds(100, 100, 862, 632);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		tHolder.populateFromArray(tickets);
		
		table = new JTable();
		table.setBounds(15, 30, 672, 524);
		getContentPane().add(table);
		
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
		getContentPane().add(btnAdd);
		
		JButton btnDelete = new JButton("Delete Ticket");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println(table.getSelectedRow());
				tHolder.deleteByRow(table.getSelectedRow());
				repopulateTable();
			}
		});
		btnDelete.setBounds(708, 64, 102, 23);
		getContentPane().add(btnDelete);
		
		JButton btnView = new JButton("View Ticket");
		btnView.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// TODO: Open frame with ticket info & two text fields representing assembly and disassembly stacks
				openViewWindow();
			}
		});
		btnView.setBounds(708, 98, 102, 23);
		getContentPane().add(btnView);
		
		
		//   TABLE LABELS   //
		
		JLabel lblDevice = new JLabel("Device");
		lblDevice.setHorizontalAlignment(SwingConstants.LEFT);
		lblDevice.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblDevice.setBounds(22, 4, 87, 23);
		getContentPane().add(lblDevice);
		
		JLabel lblFirstName = new JLabel("First Name");
		lblFirstName.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblFirstName.setBounds(187, -2, 102, 35);
		getContentPane().add(lblFirstName);
		
		JLabel lblLastName = new JLabel("Last Name\r\n");
		lblLastName.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblLastName.setBounds(363, -6, 102, 43);
		getContentPane().add(lblLastName);
		
		JLabel lblDate = new JLabel("Date");
		lblDate.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblDate.setBounds(528, -2, 52, 35);
		getContentPane().add(lblDate);
		table.updateUI();
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
		//this.setVisible(false);
		aFrame.setVisible(true);
		repopulateTable();

		// after aFrame is no longer showing
		//this.setVisible(true);
	}
	
	private void openViewWindow() {
		vFrame.setVisible(true);
	}

}
