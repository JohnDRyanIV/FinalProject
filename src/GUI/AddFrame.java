package GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import model.Ticket;

import javax.swing.JLabel;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Time;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.SwingConstants;
import java.awt.Component;
import javax.swing.JEditorPane;

public class AddFrame extends JFrame {

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
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddFrame frame = new AddFrame();
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
	public AddFrame() {
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 665, 384);
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
		editRepairSteps.setBounds(110, 126, 529, 146);
		contentPane.add(editRepairSteps);
		
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
		lblErrorRepairSteps.setBounds(282, 283, 357, 51);
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
		btnAddTicket.setBounds(110, 283, 89, 23);
		contentPane.add(btnAddTicket);
	}
	
	public void addTicketClick(List<JLabel> errorLabels, List<JTextField> textFields, JEditorPane repairSteps) {
		setErrorLabelsInvisible(errorLabels);
		// if every field is valid
		if(isValidDeviceName(textFields, errorLabels) && isValidFirstName(textFields, errorLabels) && isValidLastName(textFields, errorLabels)
				&& isValidProblem(textFields, errorLabels) && isValidRepairSteps(repairSteps, errorLabels)) {
			// initialize ticket to be added to database
			Ticket toAdd = new Ticket(textFields.get(DEVICE_NAME).getText(), textFields.get(FIRST_NAME).getText(), 
					textFields.get(LAST_NAME).getText(), LocalDate.now(), textFields.get(PROBLEM).getText(), repairSteps.getText());
			toAdd.insertTicket();	// add ticket to database
			
		}
			
	}
	
	public boolean isValidDeviceName(List<JTextField> textFields, List<JLabel> errorLabels) {
		if(1 == 1) {
			return true;
		}
		errorLabels.get(DEVICE_NAME).setText("Invalid Device Name");
		errorLabels.get(DEVICE_NAME).setVisible(true);
		return false;
	}
	
	public boolean isValidFirstName(List<JTextField> textFields, List<JLabel> errorLabels) {
		if(true == true) {
			return true;
		}
		errorLabels.get(FIRST_NAME).setText("Invalid First Name");
		errorLabels.get(FIRST_NAME).setVisible(true);
		return false;
	}
	
	public boolean isValidLastName(List<JTextField> textFields, List<JLabel> errorLabels) {
		if(true == true) {
			return true;
		}
		errorLabels.get(LAST_NAME).setText("Invalid Last Name");
		errorLabels.get(LAST_NAME).setVisible(true);
		return false;
	}
	
	public boolean isValidProblem(List<JTextField> textFields, List<JLabel> errorLabels) {
		if(true == true) {
			return true;
		}
		errorLabels.get(PROBLEM).setText("Invalid Problem");
		errorLabels.get(PROBLEM).setVisible(true);
		return false;
	}
	
	public boolean isValidRepairSteps(JEditorPane repairSteps, List<JLabel> errorLabels) {
		if(true == true) {
			return true;
		}
		errorLabels.get(REPAIR_STEPS).setText("Invalid Problem");
		errorLabels.get(REPAIR_STEPS).setVisible(true);
		return false;
		
	}
	
	public void setErrorLabelsInvisible(List<JLabel> errorLabels) {
		for(int i = 0; i < errorLabels.size(); i++) {
			errorLabels.get(i).setVisible(false);
		}
	}
}
