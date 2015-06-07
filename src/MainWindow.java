import java.awt.*;
import java.io.*;

import javax.imageio.ImageIO;
import javax.swing.*;


public class MainWindow {
	private JFrame mainFrame;
	private JButton newGameButton;
	private Sidebar sidebar;
	private DisplayWindow displayWindow;
	private FlightScheduler flightScheduler;
	private JPanel topBanner;
	private Image topBannerImg;
	
	/**
	 * Constructor for the main window.
	 * @throws IOException 
	 */
	public MainWindow(FlightScheduler flightScheduler) throws IOException {
		mainFrame = new JFrame("Flight Scheduler");
		this.flightScheduler = flightScheduler;
		
		displayWindow = new DisplayWindow();
		topBanner = new JPanel(){
			@Override
			public void paintComponent(Graphics g) {

				g.drawImage(topBannerImg, 0, 0, getWidth(), getHeight(), null);
		    }
		};
		try {
			topBannerImg = ImageIO.read(new File("SengBannerUpdated.png"));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	/**
	 * Method to display the returned routes of query on the gui.
	 * @param flightList
	 */
	public void displayFlights(String flightList){
		displayWindow.displayFlights(flightList);
	}


	/**
	 * Method to display the main window
	 */
	public void display() {
		sidebar = new Sidebar(flightScheduler);
		displayWindow.setBackground(Color.DARK_GRAY);
		topBanner.setPreferredSize(new Dimension(1150, 130));
		mainFrame.setSize(1150, 700);
		mainFrame.getContentPane().add(topBanner, BorderLayout.NORTH);
		mainFrame.getContentPane().add(sidebar, BorderLayout.WEST);
		mainFrame.getContentPane().add(displayWindow, BorderLayout.CENTER);
        mainFrame.setVisible(true);
	}
}
