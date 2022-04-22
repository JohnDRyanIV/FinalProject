package GUI;

import javax.swing.JButton;
import javax.swing.JDialog;
import model.Ticket;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;

public class ViewDialog extends JDialog {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Ticket ticket = new Ticket();
	private JTextArea textAreaDisassemble = new JTextArea();
	private JTextArea textAreaReassemble = new JTextArea();

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
		setBounds(100, 100, 798, 258);
		getContentPane().setLayout(null);
		
		JButton btnIncrementDisassemble = new JButton("-->");
		btnIncrementDisassemble.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				incrementDisassemble();
			}
		});
		btnIncrementDisassemble.setBounds(348, 82, 89, 23);
		getContentPane().add(btnIncrementDisassemble);
		
		JButton btnIncrementReassemble = new JButton("<--");
		btnIncrementReassemble.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				incrementReassemble();
			}
		});
		btnIncrementReassemble.setBounds(348, 114, 89, 23);
		getContentPane().add(btnIncrementReassemble);
		
		textAreaDisassemble.setBounds(10, 53, 328, 143);
		// JScrollPane scrollDisassemble = new JScrollPane(textAreaDisassemble);
		getContentPane().add(textAreaDisassemble);
		
		textAreaReassemble.setBounds(444, 53, 328, 143);
		// JScrollPane scrollReassemble = new JScrollPane(textAreaReassemble);
		getContentPane().add(textAreaReassemble);
		
	}
	
	public void setTicket(Ticket ticket) {
		this.ticket = ticket;
		updateTextFields();
	}
	
	public void updateTextFields() {
		textAreaDisassemble.setText(ticket.printDisassembleSteps());
		System.out.println(ticket.getDisassemble().toString());
		textAreaReassemble.setText(ticket.printReassembleSteps());
		System.out.println(ticket.getReassemble().toString());
	}
	
	public void incrementDisassemble() {
		ticket.incrementDisassemble();
		updateTextFields();
	}
	
	public void incrementReassemble() {
		ticket.incrementReassemble();
		updateTextFields();
	}
	
	public Ticket getTicket() {
		return this.ticket;
	}
}
