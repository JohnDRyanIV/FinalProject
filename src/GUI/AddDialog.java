package GUI;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import database.DBTicket;
import inputValidation.TicketValidator;
import model.Ticket;

/**
 * Responsible for providing the GUI component to add a Ticket to a
 * database defined in DBInfo. Involves some input validation so no
 * empty or invalid Tickets are entered into the database.
 * @author John Ryan
 *
 */
public class AddDialog extends JDialog {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	
	List<JLabel> errorLabels = new ArrayList<JLabel>();	// List containing every error label
	List<JTextField> textFields = new ArrayList<JTextField>(); // List containing every text area
	// repair step field needs JEditorPane because it's longer than others & needs newline functionality
	JEditorPane editRepairSteps = new JEditorPane();

	TicketValidator tv = new TicketValidator(); // used to validate user inputs
	
	private final int DEVICE_NAME = 0; 	// location in array where device name elements are stored
	private final int FIRST_NAME = 1;	// location in array where first name elements are stored
	private final int LAST_NAME = 2;	// location in array where last name elements are stored
	private final int PROBLEM = 3;		// location in List where problem elements are stores
	private final int REPAIR_STEPS = 4;	// location in LABEL list where repair steps is stored

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			AddDialog dialog = new AddDialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public AddDialog() {
		super();
		setModal(true);
		this.setTitle("Add a Ticket");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 665, 358);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//--- Textbox labels ---//
		
		JLabel lblDeviceName = new JLabel("Device Name:");
		lblDeviceName.setBounds(10, 24, 90, 14);
		contentPane.add(lblDeviceName);
		
		JLabel lblFirstName = new JLabel("First Name:");
		lblFirstName.setBounds(10, 49, 90, 14);
		contentPane.add(lblFirstName);
		
		JLabel lblLastName = new JLabel("Last Name:");
		lblLastName.setBounds(10, 74, 90, 14);
		contentPane.add(lblLastName);
		
		JLabel lblProblem = new JLabel("Problem:");
		lblProblem.setBounds(10, 100, 90, 14);
		contentPane.add(lblProblem);
		
		JLabel lblRepairSteps = new JLabel("Repair Steps:");
		lblRepairSteps.setBounds(10, 126, 90, 14);
		contentPane.add(lblRepairSteps);
		
		//--- Textbox input fields ---//
		JTextField txtDeviceName;
		JTextField txtFirstName;
		JTextField txtLastName;
		JTextField txtProblem;
		
		txtDeviceName = new JTextField();
		txtDeviceName.setBounds(110, 21, 319, 20);
		contentPane.add(txtDeviceName);
		txtDeviceName.setColumns(10);
		
		txtFirstName = new JTextField();
		txtFirstName.setBounds(110, 46, 319, 20);
		contentPane.add(txtFirstName);
		txtFirstName.setColumns(10);
		
		txtLastName = new JTextField();
		txtLastName.setBounds(110, 71, 319, 20);
		contentPane.add(txtLastName);
		txtLastName.setColumns(10);
		
		txtProblem = new JTextField();
		txtProblem.setBounds(110, 97, 319, 20);
		contentPane.add(txtProblem);
		txtProblem.setColumns(10);
		
		// adding text fields to a list for easy management

		textFields.add(txtDeviceName);
		textFields.add(txtFirstName);
		textFields.add(txtLastName);
		textFields.add(txtProblem);
		
		// make jeditorpane scrollable
		final JScrollPane editScrollPane = new JScrollPane(editRepairSteps);
		editScrollPane.setBounds(110, 125, 529, 146);
		contentPane.add(editScrollPane);
		
		//--- Error labels ---//
		
		JLabel lblErrorDeviceName = new JLabel("New label");
		lblErrorDeviceName.setVisible(false);
		lblErrorDeviceName.setForeground(Color.RED);
		lblErrorDeviceName.setBounds(439, 24, 115, 14);
		contentPane.add(lblErrorDeviceName);
		
		JLabel lblErrorFirstName = new JLabel("New label");
		lblErrorFirstName.setVisible(false);
		lblErrorFirstName.setForeground(Color.RED);
		lblErrorFirstName.setBounds(439, 49, 115, 14);
		contentPane.add(lblErrorFirstName);
		
		JLabel lblErrorLastName = new JLabel("New label");
		lblErrorLastName.setVisible(false);
		lblErrorLastName.setForeground(Color.RED);
		lblErrorLastName.setBounds(439, 74, 115, 14);
		contentPane.add(lblErrorLastName);
		
		JLabel lblErrorProblem = new JLabel("New label");
		lblErrorProblem.setVisible(false);
		lblErrorProblem.setForeground(Color.RED);
		lblErrorProblem.setBounds(439, 100, 115, 14);
		contentPane.add(lblErrorProblem);
		
		JLabel lblErrorRepairSteps = new JLabel("New label");
		lblErrorRepairSteps.setVisible(false);
		lblErrorRepairSteps.setVerticalAlignment(SwingConstants.TOP);
		lblErrorRepairSteps.setForeground(Color.RED);
		lblErrorRepairSteps.setBounds(235, 282, 404, 23);
		contentPane.add(lblErrorRepairSteps);
		
		// Adding error labels to a list for easy management
		errorLabels.add(lblErrorDeviceName);
		errorLabels.add(lblErrorFirstName);
		errorLabels.add(lblErrorLastName);
		errorLabels.add(lblErrorProblem);
		errorLabels.add(lblErrorRepairSteps);
		
		//--- Buttons ---//
		
		JButton btnAddTicket = new JButton("Add Ticket");
		btnAddTicket.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addTicketClick();
			}
		});
		btnAddTicket.setBounds(110, 282, 115, 23);
		contentPane.add(btnAddTicket);
	}
	
	/**
	 * Checks if every field is valid and, if so, adds ticket to database & closes window.
	 */
	public void addTicketClick() {
		setErrorLabelsInvisible();
		boolean validInput = true;
		// check fields in separate if statements so each error label can be activated as needed
		if(!isValidDeviceName())
			validInput = false;
		if(!isValidFirstName())
			validInput = false;
		if(!isValidLastName())
			validInput = false;
		if(!isValidProblem())
			validInput = false;
		if(!isValidRepairSteps())
			validInput = false;
		if(validInput) {	// if every field for ticket is valid, add ticket to database
			DBTicket dbTicket = new DBTicket();
			Ticket toAdd = new Ticket(textFields.get(DEVICE_NAME).getText(), textFields.get(FIRST_NAME).getText(), 
					textFields.get(LAST_NAME).getText(), LocalDate.now(), textFields.get(PROBLEM).getText(), editRepairSteps.getText());
			dbTicket.insertTicket(toAdd);	// add ticket to database
			this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
		}
			
	}
	
	/**
	 * returns true if device name is valid, sets error labels & returns false if invalid
	 * @return - Boolean representing if device name input is valid
	 */
	public boolean isValidDeviceName() {
		if(tv.isValidDeviceName(textFields.get(DEVICE_NAME).getText()))
			return true;
		// if false notify user with error label
		errorLabels.get(DEVICE_NAME).setText("Invalid Device Name");
		errorLabels.get(DEVICE_NAME).setVisible(true);
		return false;
	}
	
	/**
	 * returns true if first name is valid, sets error labels & returns false if invalid
	 * @return - Boolean representing if first name is valid
	 */
	public boolean isValidFirstName() {
		if(tv.isValidFirstName(textFields.get(FIRST_NAME).getText())) {
			return true;
		}
		errorLabels.get(FIRST_NAME).setText("Invalid First Name");
		errorLabels.get(FIRST_NAME).setVisible(true);
		return false;
	}
	
	/**
	 * returns true if last name is valid, sets error labels & returns false if last name is invalid
	 * @return - Boolean representing if last name is valid
	 */
	public boolean isValidLastName() {
		if(tv.isValidLastName(textFields.get(LAST_NAME).getText())) {
			return true;
		}
		errorLabels.get(LAST_NAME).setText("Invalid Last Name");
		errorLabels.get(LAST_NAME).setVisible(true);
		return false;
	}
	
	/**
	 * returns true if problem is valid, sets error labels & returns false is problem is invalid
	 * @return - Boolean representing if problem is valid
	 */
	public boolean isValidProblem() {
		if(tv.isValidProblem(textFields.get(PROBLEM).getText())) {
			return true;
		}
		errorLabels.get(PROBLEM).setText("Invalid Problem");
		errorLabels.get(PROBLEM).setVisible(true);
		return false;
	}
	
	/**
	 * returns true if repair steps are valid, sets error labels & returns false if repair steps are invalid
	 * @return - Boolean representing if repair steps are valid
	 */
	public boolean isValidRepairSteps() {
		if(tv.isValidRepairSteps(editRepairSteps.getText())) {
			return true;
		}
		errorLabels.get(REPAIR_STEPS).setText("Invalid Repair Steps");
		errorLabels.get(REPAIR_STEPS).setVisible(true);
		return false;	
	}
	
	public void setErrorLabelsInvisible() {
		for(int i = 0; i < errorLabels.size(); i++) {
			errorLabels.get(i).setVisible(false);
		}
	}

}
