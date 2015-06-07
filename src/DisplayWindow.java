import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

import javax.swing.*;


public class DisplayWindow extends JPanel {
	private JTextArea textArea;
	private String output;
	private JScrollPane scroll;
	
	/**
	 * Constructor for the display window that displays the return flights from the query
	 */
	public DisplayWindow(){
		setLayout(new BorderLayout());
		textArea = new JTextArea(250, 50);
		textArea.setForeground(Color.BLACK);
		scroll = new JScrollPane(textArea);
		scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		display();
	}
	
	/**
	 * Displays the output window onto the gui
	 */
	public void display(){
		setPreferredSize(new Dimension(550,600));
		setLocation(50, 50);
		textArea.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				textArea.setText(textArea.getText());
				textArea.selectAll();
			}
		});
		textArea.setText(output);
		textArea.setPreferredSize(new Dimension(400, 400));
		textArea.setEditable(false);
		
		JPanel topPadding = new JPanel();
		JPanel bottomPadding = new JPanel();
		JPanel leftPadding = new JPanel();
		JPanel rightPadding = new JPanel();
		
		topPadding.setPreferredSize(new Dimension(680,20));
		bottomPadding.setPreferredSize(new Dimension(680,20));
		leftPadding.setPreferredSize(new Dimension(20,680));
		rightPadding.setPreferredSize(new Dimension(20,680));
		
		Color paddingColour = Color.gray;
		topPadding.setBackground(paddingColour);
		bottomPadding.setBackground(paddingColour);
		leftPadding.setBackground(paddingColour);
		rightPadding.setBackground(paddingColour);
		
		add(topPadding, BorderLayout.NORTH);
		add(bottomPadding, BorderLayout.SOUTH);
		add(leftPadding, BorderLayout.WEST);
		add(rightPadding, BorderLayout.EAST);
		add(scroll, BorderLayout.CENTER);
	}
	
	/**
	 * Outputs the string of routes to the display window
	 * @param flightList A string containing all the returned routes
	 */
	public void displayFlights(String flightList){
		output = flightList;
		textArea.setText(output);
	}
	
}
