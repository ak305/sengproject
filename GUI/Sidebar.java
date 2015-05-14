import java.awt.*;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.Border;


public class Sidebar extends JPanel {
	//City start
	private JComboBox startCity;
	private JLabel startCityLabel;
	
	//City end
	private JComboBox endCity;
	private JLabel endCityLabel;
	
	//flight Date
	private ArrayList<JComboBox> date;
	private JLabel dateLabel;
	
	//Departure Time
	private ArrayList<JComboBox> time;
	private JLabel timeLabel;
	
	//Search priority
	
	private JLabel priorityLabel;
	
	//Airline
	private JComboBox airline;
	private JLabel airlineLabel;
	
	//Num output
	private JTextField numOutput;
	private JLabel numOutputLabel;
	
	//Start
	private JButton search;
	//clear
	private JButton clear;
	
		
	public Sidebar(String[] cities){
		startCity = new JComboBox(cities);
		startCityLabel = new JLabel();
		endCity = new JComboBox(cities);
		endCityLabel = new JLabel();
		date = new ArrayList<JComboBox>();
		dateLabel = new JLabel();
		for(int i = 0; i < 3; i++){
			date.add(new JComboBox());
		}
		time = new ArrayList<JComboBox>();
		timeLabel = new JLabel();
		for(int i = 0; i < 2; i++){
			time.add(new JComboBox());
		}
		airline = new JComboBox();
		airlineLabel = new JLabel();
		numOutput = new JTextField();
		numOutputLabel = new JLabel();
		search = new JButton("Search");
		clear = new JButton("Clear");
		
		
//		add(startCity);
//		add(endCity);
//		for(JComboBox box : date){
//			add(box);
//		}
//		for(JComboBox box : time){
//			add(box);
//		}
//		add(airline);
//		add(numOutput);
//		add(search);
//		add(clear);
		
		display();
		// align elements
//		setLocation();
	}
	
	private void display(){
		setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		startCityLabel.setText("City Start: ");
		endCityLabel.setText("City End: ");
		dateLabel.setText("Flight Date: ");
		timeLabel.setText("Flight Time: ");
		airlineLabel.setText("Airline: ");
		numOutputLabel.setText("No. Routes ");
		
		JPanel container = new JPanel();
		container.setPreferredSize(new Dimension(320, 400));
		container.setBackground(Color.LIGHT_GRAY);
		container.setLayout(new GridBagLayout());
		add(container, BorderLayout.NORTH);
		GridBagConstraints c = new GridBagConstraints();
		c.anchor = GridBagConstraints.NORTH;
		c.insets = new Insets(15,0,0,0);
//		c.weighty = 1.0;
		
		// flight date - Gridy 2
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.5;
		c.gridx = 0;
		c.gridy = 2;
		container.add(dateLabel, c);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.5;
		c.gridx = 1;
		c.gridy = 2;
		container.add(date.get(0), c);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.5;
		c.gridx = 2;
		c.gridy = 2;
		container.add(date.get(1), c);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.5;
		c.gridx = 3;
		c.gridy = 2;
		container.add(date.get(2), c);
		
		//time gridy 3
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.5;
		c.gridx = 0;
		c.gridy = 3;
		container.add(timeLabel, c);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.5;
		c.gridx = 1;
		c.gridy = 3;
		container.add(time.get(0), c);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.5;
		c.gridx = 2;
		c.gridy = 3;
		container.add(time.get(1), c);
		
		// clear grid 7
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.5;
		c.gridx = 0;
		c.gridy = 7;
		container.add(clear, c);
		
		// search grid 7
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.5;
		c.gridx = 2;
		c.gridy = 7;
		c.gridwidth = 2;
		container.add(search, c);
		
		// startCity gridy 0
        c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.5;
		c.gridx = 0;
		c.gridy = 0;
//		c.anchor = GridBagConstraints.NORTH;
		container.add(startCityLabel, c);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.5;
		c.gridx = 1;
		c.gridy = 0;
		c.gridwidth = 3;
		container.add(startCity, c);
		
		//end city gridy 1
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.5;
		c.gridx = 0;
		c.gridy = 1;
		container.add(endCityLabel, c);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.5;
		c.gridx = 1;
		c.gridy = 1;
		c.gridwidth = 3;
		container.add(endCity, c);
		
		//airline grid 4
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.5;
		c.gridx = 0;
		c.gridy = 4;
		container.add(airlineLabel, c);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.5;
		c.gridx = 1;
		c.gridy = 4;
		c.gridwidth = 3;
		container.add(airline, c);
		
		// priority gridy 5
		
		// numOutput gridy 6
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.5;
		c.gridx = 0;
		c.gridy = 6;
		container.add(numOutputLabel, c);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.5;
		c.gridx = 1;
		c.gridy = 6;
		c.gridwidth = 3;
		container.add(numOutput, c);
		
		
		
		
		
	}
	
	
	public Dimension getPreferredSize() {
        return new Dimension(350, 750);
    }
	
	public void paintComponent(Graphics g) {
        super.paintComponent(g);
//    	g.drawImage(backgroundImage, 0, 0, null);
    }
}
