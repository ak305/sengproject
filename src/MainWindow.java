import java.awt.*;
import java.io.*;

import javax.swing.*;


public class MainWindow {
	private JFrame mainFrame;
	private JButton newGameButton;
	private Sidebar sidebar;
	private DisplayWindow displayWindow;
	private FlightScheduler flightScheduler;

	
	/**
	 * Constructor for the main window.
	 * @throws IOException 
	 */
	public MainWindow(FlightScheduler flightScheduler) throws IOException {
		mainFrame = new JFrame("Flight Scheduler");
//		newGameButton = new JButton("NEW GAME");
//		flightScheduler = new Flightscheduler(args)

		this.flightScheduler = flightScheduler;
		
		displayWindow = new DisplayWindow();
		
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public void displayFlights(String flightList){
		displayWindow.displayFlights(flightList);
	}


	/**
	 * Method to display the main window
	 */
	public void display() {
		sidebar = new Sidebar(flightScheduler);
		displayWindow.setBackground(Color.DARK_GRAY);
		mainFrame.add(displayWindow);
		mainFrame.add(sidebar);		
		sidebar.setBackground(Color.lightGray);
		mainFrame.setSize(1150, 700);
		mainFrame.getContentPane().add(sidebar, BorderLayout.WEST);
		mainFrame.getContentPane().add(displayWindow, BorderLayout.CENTER);
//		sidebar.add(newGameButton,BorderLayout.SOUTH);
//		mainFrame.pack();
        mainFrame.setVisible(true);
	}
}
