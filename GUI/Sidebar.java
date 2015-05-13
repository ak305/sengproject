import java.awt.*;
import java.util.ArrayList;

import javax.swing.*;


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
		for(int i = 0; i < 2; i++){
			time.add(new JComboBox());
		}
		airline = new JComboBox();
		numOutput = new JTextField();
		search = new JButton("Search");
		clear = new JButton("Clear");
		
		
		add(startCity);
		add(endCity);
		for(JComboBox box : date){
			add(box);
		}
		for(JComboBox box : time){
			add(box);
		}
		add(airline);
		add(numOutput);
		add(search);
		add(clear);
		
		display();
		// align elements
//		setLocation();
	}
	
	private void display(){
		startCityLabel.setText("City Start: ");
		endCityLabel.setText("City End: ");
		dateLabel.setText("Flight Date: ");

		
		
		
		GroupLayout layout = new GroupLayout(this);
		this.setLayout(layout);
		layout.setAutoCreateContainerGaps(true);
		layout.setAutoCreateGaps(true);
		layout.setHorizontalGroup(layout.createSequentialGroup()
			    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING))
			    	.addComponent(startCityLabel)
			    	.addComponent(startCity)
			    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING))
			    	.addComponent(endCityLabel)
			    	.addComponent(endCity)
		);
		
		layout.setVerticalGroup(layout.createSequentialGroup()
			    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE))
			    	.addComponent(startCityLabel)
			    	.addComponent(endCityLabel)
			    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING))
			    	.addComponent(startCity)
			    	.addComponent(endCity)
		);

				 
		
		startCity.setPreferredSize(new Dimension(50,50));
//		numOutput.setSize(50, 50);
	}
	
	
	public Dimension getPreferredSize() {
        return new Dimension(350, 750);
    }
	
	public void paintComponent(Graphics g) {
        super.paintComponent(g);
//    	g.drawImage(backgroundImage, 0, 0, null);
    }
}
