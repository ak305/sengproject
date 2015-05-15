import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.MouseInputAdapter;


public class Sidebar extends JPanel implements ActionListener {
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
	private JList<String> priority;
	private DefaultListModel<String> priorityModel;
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
	
	private boolean mouseDragging = false;
    private int dragSourceIndex;
	
		
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
		
		// do we need clear?
		clear = new JButton("Clear");
		
		search.addActionListener(this);
		clear.addActionListener(this);
		
		priorityLabel = new JLabel();

		priorityModel = new DefaultListModel<String>();
		priorityModel.ensureCapacity(3);
		priorityModel.addElement("Time");
		priorityModel.addElement("Cost");
		priorityModel.addElement("Airline");
		
		priority = new JList<String>(priorityModel);
		// http://stackoverflow.com/questions/3804361/how-to-enable-drag-and-drop-inside-jlist
		priority.addMouseListener(new MouseAdapter() {
	        @Override
	        public void mousePressed(MouseEvent e) {
	            if (SwingUtilities.isLeftMouseButton(e)) {
	                dragSourceIndex = priority.getSelectedIndex();
	                mouseDragging = true;
	            }
	        }
	        
	        @Override
	        public void mouseReleased(MouseEvent e) {
	            mouseDragging = false;
	        }
		});
		priority.addMouseMotionListener(new MouseAdapter() {
	        @Override
	        public void mouseDragged(MouseEvent e) {
	            if (mouseDragging) {
	                int currentIndex = priority.locationToIndex(e.getPoint());
	                if (currentIndex != dragSourceIndex) {
	                    int dragTargetIndex = priority.getSelectedIndex();
	                    String dragElement = priorityModel.get(dragSourceIndex);
	                    priorityModel.remove(dragSourceIndex);
	                    priorityModel.add(dragTargetIndex, dragElement);
	                    dragSourceIndex = currentIndex;
	                }
	            }
	        }
		});
		display();

	}
	
	private void display(){
		setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		startCityLabel.setText("City Start: ");
		endCityLabel.setText("City End: ");
		dateLabel.setText("Flight Date: ");
		timeLabel.setText("Flight Time: ");
		airlineLabel.setText("Airline: ");
		numOutputLabel.setText("No. Routes ");
		priorityLabel.setText("Sort Priority Order: ");
		
		JPanel container = new JPanel();
		container.setPreferredSize(new Dimension(320, 180));
		container.setBackground(Color.lightGray);
		container.setLayout(new GridBagLayout());
		
		JPanel container2 = new JPanel();
		container2.setPreferredSize(new Dimension(320, 100));
		container2.setBackground(Color.lightGray);
		container2.setLayout(new GridBagLayout());
		
		JPanel container3 = new JPanel();
		container3.setPreferredSize(new Dimension(320, 130));
		container3.setBackground(Color.lightGray);
		container3.setLayout(new GridBagLayout());
		
		add(container, BorderLayout.NORTH);
		add(container2, BorderLayout.CENTER);
		add(container3, BorderLayout.SOUTH);
		
		GridBagConstraints c = new GridBagConstraints();
		c.anchor = GridBagConstraints.NORTH;
		c.insets = new Insets(10,0,0,0);
//		c.weighty = 1.0;
		
		// container 2
		// priority
		JScrollPane pane = new JScrollPane();
		pane.setPreferredSize(new Dimension(100, 70));
		pane.getViewport().add(priority);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.5;
		c.gridx = 1;
		c.gridy = 0;
		container2.add(pane, c);

		// priority gridy 4, 5, 6,
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.5;
		c.gridx = 0;
		c.gridy = 0;
		container2.add(priorityLabel, c);
		
		JLabel order = new JLabel();
		order.setText("<html>1 <p>2 <p>3 <p><p></html>");
		c.fill = GridBagConstraints.VERTICAL;
		c.weightx = 0.1;
		c.gridx = 2;
		c.gridy = 0;
		container2.add(order, c);

		//container 1
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
		
		
		// container3
		c.gridwidth = 1;
		//airline grid 0
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.5;
		c.gridx = 0;
		c.gridy = 0;
		container3.add(airlineLabel, c);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.5;
		c.gridx = 1;
		c.gridy = 0;
		container3.add(airline, c);
		
		// numOutput gridy 1
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.5;
		c.gridx = 0;
		c.gridy = 1;
		container3.add(numOutputLabel, c);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.5;
		c.gridx = 1;
		c.gridy = 1;
		container3.add(numOutput, c);
		
		// clear grid 2
//		c.fill = GridBagConstraints.HORIZONTAL;
//		c.weightx = 0.5;
//		c.gridx = 0;
//		c.gridy = 2;
//		container3.add(clear, c);
		
		// search grid 9
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(30,0,0,0);
		c.weightx = 0.5;
		c.gridx = 1;
		c.gridy = 2;
		container3.add(search, c);
	}
	
	
	public Dimension getPreferredSize() {
        return new Dimension(350, 750);
    }
	
	public void paintComponent(Graphics g) {
        super.paintComponent(g);
//    	g.drawImage(backgroundImage, 0, 0, null);
    }

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(search)){
			int index = startCity.getSelectedIndex();
			int num = 0;
			System.out.print("Click search; " + index + " " + "; order: ");
			for(int i = 0; i < 3; i ++){
				System.out.print(priorityModel.get(i) + " ");
			}
			try{
				num = Integer.parseInt(numOutput.getText());
			} catch(NumberFormatException e2){
				num = 0;
				// display error message?
			}
			System.out.print("; numOutput: " + num);
			System.out.println();
		}
	}
}
