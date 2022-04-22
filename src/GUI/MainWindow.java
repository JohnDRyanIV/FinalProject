package GUI;

import java.awt.EventQueue;
import java.sql.SQLException;

import model.Ticket;
import model.TicketHolder;

public class MainWindow {

	private MainFrame mFrame = new MainFrame();

	/**
	 * Launch the application.
	 * @throws SQLException 
	 */
	public static void main(String[] args) throws SQLException {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow window = new MainWindow();
					window.mFrame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
		/*TicketHolder th = new TicketHolder();
		th.populateFromDB();
		
		Ticket local = th.getTicket(0);
		String output = local.getDisassemble().toString();
		System.out.println(output);
		System.out.println(local.getDisassemble().size());
		String output2 = local.printDisassembleSteps();
		System.out.println(output2);*/
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
		mFrame.setVisible(true);
	}

}
