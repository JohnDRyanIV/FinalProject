package GUI;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JEditorPane;

import model.Ticket;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JScrollPane;

/**
 * Allows for the viewing of a Ticket object, including the ability
 * to visually pop a disassembly or assembly instruction from one stack into 
 * another
 * @author John Ryan
 *
 */
public class ViewDialog extends JDialog {

	private static final long serialVersionUID = 1L;
	private Ticket ticket = new Ticket();
	
	private JEditorPane viewDisassemble = new JEditorPane();
	private JEditorPane viewReassemble = new JEditorPane();
	
	JLabel lblDeviceName = new JLabel("New label");
	JLabel lblProblem = new JLabel("New label");
	JLabel lblName = new JLabel("New label");
	JLabel lblDate = new JLabel("New label");
	
	JButton btnIncrementDisassemble = new JButton("\u2192");
	JButton btnIncrementReassemble = new JButton("\u2190");

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ViewDialog dialog = new ViewDialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ViewDialog() {
		super();
		setModal(true);
		this.setTitle("Viewing Ticket");
		setBounds(100, 100, 798, 322);
		getContentPane().setLayout(null);
		// set text areas so they can't be edited by users
		
		final JScrollPane scrollDisassemble = new JScrollPane();
		final JScrollPane scrollReassemble = new JScrollPane();

		btnIncrementDisassemble.setFont(new Font("Tahoma", Font.PLAIN, 30));
		btnIncrementDisassemble.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				incrementDisassemble();
			}
		});
		btnIncrementDisassemble.setBounds(346, 33, 89, 58);
		getContentPane().add(btnIncrementDisassemble);
		
		btnIncrementReassemble.setFont(new Font("Tahoma", Font.PLAIN, 30));
		btnIncrementReassemble.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				incrementReassemble();
			}
		});
		btnIncrementReassemble.setBounds(346, 103, 89, 58);
		getContentPane().add(btnIncrementReassemble);
		
		scrollDisassemble.setBounds(8, 26, 328, 143);
		// JScrollPane scrollDisassemble = new JScrollPane(viewDisassemble);
		getContentPane().add(scrollDisassemble);
		scrollDisassemble.setViewportView(viewDisassemble);
		viewDisassemble.setEditable(false);
		
		scrollReassemble.setBounds(445, 26, 328, 143);
		// JScrollPane scrollReassemble = new JScrollPane(viewReassemble);
		getContentPane().add(scrollReassemble);
		scrollReassemble.setViewportView(viewReassemble);
		viewReassemble.setEditable(false);
		
		
		lblDeviceName.setBounds(8, 180, 764, 14);
		getContentPane().add(lblDeviceName);
		
		
		lblProblem.setBounds(8, 205, 764, 14);
		getContentPane().add(lblProblem);
		

		lblName.setBounds(8, 230, 764, 14);
		getContentPane().add(lblName);
		

		lblDate.setBounds(8, 255, 764, 14);
		getContentPane().add(lblDate);
		
		JLabel lblDisassemblySteps = new JLabel("Disassembly Steps");
		lblDisassemblySteps.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblDisassemblySteps.setBounds(10, 5, 326, 19);
		getContentPane().add(lblDisassemblySteps);
		
		JLabel lblReassemblySteps = new JLabel("Reassembly Steps");
		lblReassemblySteps.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblReassemblySteps.setBounds(445, 5, 326, 19);
		getContentPane().add(lblReassemblySteps);
		
	}
	
	/**
	 * Updates text areas in ViewDialog. Used after popping from one stack to another
	 */
	public void updateviews() {
		viewDisassemble.setText(ticket.printDisassembleSteps());
		viewReassemble.setText(ticket.printReassembleSteps());
		viewReassemble.setCaretPosition(0);
		viewDisassemble.setCaretPosition(0);
		updateButtons();
	}
	
	/**
	 * Updates the buttons in ViewDialog. If the disassembly or reassembly lists are empty,
	 * it will ensure that the associated button cannot be pressed.
	 */
	public void updateButtons()  {
		if(ticket.getReassemble().isEmpty()) // if there are no reassembly steps
			btnIncrementReassemble.setEnabled(false);
		else
			btnIncrementReassemble.setEnabled(true);
		if(ticket.getDisassemble().isEmpty())  // if there are no disassembly steps
			btnIncrementDisassemble.setEnabled(false);
		else
			btnIncrementDisassemble.setEnabled(true);
	}
	
	/**
	 * Sets labels in ViewDialog. Used after setting Ticket object.
	 */
	public void setLabels() {
		lblDeviceName.setText("Device: " + ticket.getdName());
		lblProblem.setText("Problem: " + ticket.getProblem());
		lblName.setText("Name: " + ticket.getfName() + " " + ticket.getlName());
		lblDate.setText("Created: " + ticket.getCreation().toString());
	}
	
	/**
	 * Calls ticket method that pops from disassemble stack into reassemble stack.
	 */
	public void incrementDisassemble() {
		ticket.incrementDisassemble();
		updateviews();
	}
	
	/**
	 * Calls ticket method that pops from reassemble stack into disassemble stack.
	 */
	public void incrementReassemble() {
		ticket.incrementReassemble();
		updateviews();
	}
	
	/**
	 * Getter for Ticket object in ViewDialog class.
	 * @return - Ticket object in ViewDialog class.
	 */
	public Ticket getTicket() {
		return this.ticket;
	}
	
	/**
	 * Sets ticket in ViewDialog window before opening window.
	 * @param ticket - ticket to be passed to ViewDialog
	 */
	public void setTicket(Ticket ticket) {
		this.ticket = ticket;
		ticket.resetStacks(); // reset stacks in case window was exited and reopened
		updateviews();
		setLabels();
		updateButtons();
	}
}
