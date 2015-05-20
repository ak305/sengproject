import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

import javax.swing.*;


public class DisplayWindow extends JPanel {
	private JTextField textField;
	private String output;
	
	public DisplayWindow(String output){
		this.output = output;
		
		setLayout(new BorderLayout());
		
//		panel = new JPanel();
		textField = new JTextField();
		
		display();
	}
	
	public void display(){
//		panel.setBackground(Color.darkGray);
		setPreferredSize(new Dimension(550,600));
		setLocation(50, 50);
		textField.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				textField.setText(textField.getText());
				textField.selectAll();
			}
		});
		textField.setText(output);
		textField.setPreferredSize(new Dimension(400, 400));
//		textField.setLocation(50, 50);
		
		JPanel topPadding = new JPanel();
		JPanel bottomPadding = new JPanel();
		JPanel leftPadding = new JPanel();
		JPanel rightPadding = new JPanel();
		
		topPadding.setPreferredSize(new Dimension(680,20));
		bottomPadding.setPreferredSize(new Dimension(680,20));
		leftPadding.setPreferredSize(new Dimension(20,680));
		rightPadding.setPreferredSize(new Dimension(20,680));
		
		topPadding.setBackground(Color.darkGray);
		bottomPadding.setBackground(Color.darkGray);
		leftPadding.setBackground(Color.darkGray);
		rightPadding.setBackground(Color.darkGray);
		
		add(topPadding, BorderLayout.NORTH);
		add(bottomPadding, BorderLayout.SOUTH);
		add(leftPadding, BorderLayout.WEST);
		add(rightPadding, BorderLayout.EAST);
		add(textField, BorderLayout.CENTER);
	}
	
}
