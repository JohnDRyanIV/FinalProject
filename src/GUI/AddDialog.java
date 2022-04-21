package GUI;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

import inputValidation.TicketValidator;
import model.Ticket;

public class AddDialog extends JDialog {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtDeviceName;
	private JTextField txtFirstName;
	private JTextField txtLastName;
	private JTextField txtProblem;
	
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
		List<JTextField> textFields = new ArrayList<JTextField>();
		textFields.add(txtDeviceName);
		textFields.add(txtFirstName);
		textFields.add(txtLastName);
		textFields.add(txtProblem);
		
		// repair step needs jeditor pane because it's longer than others
		JEditorPane editRepairSteps = new JEditorPane();
		
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
		List<JLabel> errorLabels = new ArrayList<JLabel>();
		errorLabels.add(lblErrorDeviceName);
		errorLabels.add(lblErrorFirstName);
		errorLabels.add(lblErrorLastName);
		errorLabels.add(lblErrorProblem);
		errorLabels.add(lblErrorRepairSteps);
		
		//--- Buttons ---//
		
		JButton btnAddTicket = new JButton("Add Ticket");
		btnAddTicket.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addTicketClick(errorLabels, textFields, editRepairSteps);
			}
		});
		btnAddTicket.setBounds(110, 282, 115, 23);
		contentPane.add(btnAddTicket);
	}
	
	public void addTicketClick(List<JLabel> errorLabels, List<JTextField> textFields, JEditorPane repairSteps) {
		TicketValidator tv = new TicketValidator();
		setErrorLabelsInvisible(errorLabels);
		boolean validInput = true;
		// check fields in separate if statements so each error label can be checked 
		if(!isValidDeviceName(textFields, errorLabels, tv))
			validInput = false;
		if(!isValidFirstName(textFields, errorLabels, tv))
			validInput = false;
		if(!isValidLastName(textFields, errorLabels, tv))
			validInput = false;
		if(!isValidProblem(textFields, errorLabels, tv))
			validInput = false;
		if(!isValidRepairSteps(repairSteps, errorLabels, tv))
			validInput = false;
		if(validInput) {
			Ticket toAdd = new Ticket(textFields.get(DEVICE_NAME).getText(), textFields.get(FIRST_NAME).getText(), 
					textFields.get(LAST_NAME).getText(), LocalDate.now(), textFields.get(PROBLEM).getText(), repairSteps.getText());
			toAdd.insertTicket();	// add ticket to database
		}
		
		

		// if every field is valid
		/*if(isValidDeviceName(textFields, errorLabels, tv) && isValidFirstName(textFields, errorLabels, tv) && isValidLastName(textFields, errorLabels, tv)
				&& isValidProblem(textFields, errorLabels, tv) && isValidRepairSteps(repairSteps, errorLabels, tv)) {
			// initialize ticket to be added to database
			Ticket toAdd = new Ticket(textFields.get(DEVICE_NAME).getText(), textFields.get(FIRST_NAME).getText(), 
					textFields.get(LAST_NAME).getText(), LocalDate.now(), textFields.get(PROBLEM).getText(), repairSteps.getText());
			toAdd.insertTicket();	// add ticket to database
		}*/
			
	}
	
	// below methods use a TicketValidator class to ensure that each field is valid & update error messages for every invalid field
	
	public boolean isValidDeviceName(List<JTextField> textFields, List<JLabel> errorLabels, TicketValidator tv) {
		if(tv.isValidDeviceName(textFields.get(DEVICE_NAME).getText()))
			return true;
		// if false notify user with error label
		errorLabels.get(DEVICE_NAME).setText("Invalid Device Name");
		errorLabels.get(DEVICE_NAME).setVisible(true);
		return false;
	}
	
	public boolean isValidFirstName(List<JTextField> textFields, List<JLabel> errorLabels, TicketValidator tv) {
		if(tv.isValidFirstName(textFields.get(FIRST_NAME).getText())) {
			return true;
		}
		errorLabels.get(FIRST_NAME).setText("Invalid First Name");
		errorLabels.get(FIRST_NAME).setVisible(true);
		return false;
	}
	
	public boolean isValidLastName(List<JTextField> textFields, List<JLabel> errorLabels, TicketValidator tv) {
		if(tv.isValidLastName(textFields.get(LAST_NAME).getText())) {
			return true;
		}
		errorLabels.get(LAST_NAME).setText("Invalid Last Name");
		errorLabels.get(LAST_NAME).setVisible(true);
		return false;
	}
	
	public boolean isValidProblem(List<JTextField> textFields, List<JLabel> errorLabels, TicketValidator tv) {
		if(tv.isValidProblem(textFields.get(PROBLEM).getText())) {
			return true;
		}
		errorLabels.get(PROBLEM).setText("Invalid Problem");
		errorLabels.get(PROBLEM).setVisible(true);
		return false;
	}
	
	public boolean isValidRepairSteps(JEditorPane repairSteps, List<JLabel> errorLabels, TicketValidator tv) {
		if(tv.isValidRepairSteps(repairSteps.getText())) {
			return true;
		}
		errorLabels.get(REPAIR_STEPS).setText("Invalid Repair Steps");
		errorLabels.get(REPAIR_STEPS).setVisible(true);
		return false;
		
	}
	
	public void setErrorLabelsInvisible(List<JLabel> errorLabels) {
		for(int i = 0; i < errorLabels.size(); i++) {
			errorLabels.get(i).setVisible(false);
		}
	}

}
