/**************************************************************
* Name        : Data Structures Final Project
* Author      : John Ryan
* Created     : 04/29/2022
* Course      : CIS 152 Data Structures
* Version     : 1.0
* OS          : Windows 10
* Copyright   : This is my own original work based on
*               specifications issued by our instructor
* Description : This program is a computer repair ticket 
* handler that allows for adding, viewing, sorting, and 
* deletion of computer repair tickets. It is meant to 
* demonstrate what a student has learned about data structures 
* throughout the course of this class.
* Academic Honesty: I attest that this is my original work.
* I have not used unauthorized source code, either modified or 
* unmodified. I have not given other fellow student(s) access to
* my program.
***************************************************************/
package GUI;

import java.awt.EventQueue;
import java.sql.SQLException;

import model.Ticket;
import model.TicketHolder;

/**
 * Launches a MainFrame class.
 * @author John Ryan
 * 
 */
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
