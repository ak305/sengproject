import java.awt.*;
import java.io.*;

import javax.swing.*;


public class MainWindow {
	private JFrame mainFrame;
	private JButton newGameButton;
	private Sidebar sidebar;
	private DisplayWindow displayWindow;

	
	/**
	 * Constructor for the main window.
	 * @throws IOException 
	 */
	public MainWindow(FlightScheduler flightScheduler) throws IOException {
		mainFrame = new JFrame("Flight Scheduler");
//		newGameButton = new JButton("NEW GAME");
//		flightScheduler = new Flightscheduler(args)

		// TODO
		// still need to pass in a list of cities and airlines
		sidebar = new Sidebar(flightScheduler);
		displayWindow = new DisplayWindow();
		displayWindow.setBackground(Color.DARK_GRAY);
		sidebar.setSize(550, 700);
		
		mainFrame.add(displayWindow);
		mainFrame.add(sidebar);

		
		
		sidebar.setBackground(Color.lightGray);
		mainFrame.setSize(1150, 700);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public void displayFlights(String flightList){
		displayWindow.displayFlights(flightList);
	}


	/**
	 * Method to display the main window
	 */
	public void display() {
		mainFrame.getContentPane().add(sidebar, BorderLayout.WEST);
		mainFrame.getContentPane().add(displayWindow, BorderLayout.CENTER);
//		sidebar.add(newGameButton,BorderLayout.SOUTH);
//		mainFrame.pack();
        mainFrame.setVisible(true);
	}
}
