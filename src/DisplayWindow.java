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
	
	public DisplayWindow(){
		
		setLayout(new BorderLayout());
		
//		panel = new JPanel();
		textArea = new JTextArea(250, 50);
//		textArea.setBackground(Color.LIGHT_GRAY);
		textArea.setForeground(Color.BLACK);
		scroll = new JScrollPane(textArea);
		scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		display();
	}
	
	public void display(){
//		panel.setBackground(Color.darkGray);
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
//		textField.setLocation(50, 50);
		
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
	
	public void displayFlights(String flightList){
		output = flightList;
		textArea.setText(output);
	}
	
}
