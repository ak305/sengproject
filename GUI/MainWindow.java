import java.awt.*;
import java.io.*;

import javax.swing.*;


public class MainWindow {

	private JFrame mainFrame;
	private JButton newGameButton;
	private Sidebar sidebar;
	private JPanel outputScreen;

	/**
	 * Method to bootstrap the main frame
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		final MainWindow mw = new MainWindow();
		
		// display the main window in a different thread.
		SwingUtilities.invokeLater(new Runnable() {
            public void run() {
            	mw.display();
            }
        });
	}
	
	/**
	 * Constructor for the main window.
	 * @throws IOException 
	 */
	public MainWindow() throws IOException {
		mainFrame = new JFrame("Flight Scheduler");
//		newGameButton = new JButton("NEW GAME");
		
		// cities = get(cities-from-sams-back-end);
		String[] cities = {"a","b","c","d"};
		sidebar = new Sidebar(cities);
		outputScreen = new JPanel();
		
		outputScreen.setSize(600, 700);
		sidebar.setSize(550, 700);
		
		mainFrame.add(outputScreen);
		mainFrame.add(sidebar);

		
		
		sidebar.setBackground(Color.lightGray);
		outputScreen.setBackground(Color.DARK_GRAY);
		mainFrame.setSize(1150, 700);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}


	/**
	 * Method to display the main window
	 */
	private void display() {
		mainFrame.getContentPane().add(sidebar, BorderLayout.WEST);
		mainFrame.getContentPane().add(outputScreen, BorderLayout.CENTER);
//		sidebar.add(newGameButton,BorderLayout.SOUTH);
//		mainFrame.pack();
        mainFrame.setVisible(true);
	}
}
