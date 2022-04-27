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
		setBounds(100, 100, 798, 585);
		getContentPane().setLayout(null);
		// set text areas so they can't be edited by users
		
		final JScrollPane scrollDisassemble = new JScrollPane(viewDisassemble);
		final JScrollPane scrollReassemble = new JScrollPane(viewReassemble);
		viewDisassemble.setEditable(false);
		viewReassemble.setEditable(false);

		
		JButton btnIncrementDisassemble = new JButton("\u2192");
		btnIncrementDisassemble.setFont(new Font("Tahoma", Font.PLAIN, 30));
		btnIncrementDisassemble.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				incrementDisassemble();
			}
		});
		btnIncrementDisassemble.setBounds(345, 83, 89, 58);
		getContentPane().add(btnIncrementDisassemble);
		
		JButton btnIncrementReassemble = new JButton("\u2190");
		btnIncrementReassemble.setFont(new Font("Tahoma", Font.PLAIN, 30));
		btnIncrementReassemble.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				incrementReassemble();
			}
		});
		btnIncrementReassemble.setBounds(345, 154, 89, 58);
		getContentPane().add(btnIncrementReassemble);
		
		scrollDisassemble.setBounds(10, 75, 328, 143);
		// JScrollPane scrollDisassemble = new JScrollPane(viewDisassemble);
		getContentPane().add(scrollDisassemble);
		
		scrollReassemble.setBounds(444, 75, 328, 143);
		// JScrollPane scrollReassemble = new JScrollPane(viewReassemble);
		getContentPane().add(scrollReassemble);
		
		
		lblDeviceName.setBounds(10, 11, 328, 14);
		getContentPane().add(lblDeviceName);
		
		
		lblProblem.setBounds(10, 36, 328, 14);
		getContentPane().add(lblProblem);
		

		lblName.setBounds(444, 11, 328, 14);
		getContentPane().add(lblName);
		

		lblDate.setBounds(444, 36, 328, 14);
		getContentPane().add(lblDate);
		
	}
	
	/**
	 * Updates text areas in ViewDialog. Used after popping from one stack to another
	 */
	public void updateviews() {
		viewDisassemble.setText(ticket.printDisassembleSteps());
		System.out.println(ticket.getDisassemble().toString());
		viewReassemble.setText(ticket.printReassembleSteps());
		System.out.println(ticket.getReassemble().toString());
		viewReassemble.setCaretPosition(0);
		viewDisassemble.setCaretPosition(0);
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
		this.ticket = null;
		this.ticket = ticket;
		updateviews();
		setLabels();
	}
}
