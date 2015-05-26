import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.MouseInputAdapter;
import javax.swing.text.NumberFormatter;


public class Sidebar extends JPanel implements ActionListener {
	//City start
	private JComboBox startCity;
	private JLabel startCityLabel;
	
	//City end
	private JComboBox endCity;
	private JLabel endCityLabel;
	
	//flight Date
	private ArrayList<JComboBox> date;
	private Integer[] year;
	private String[] month = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
	private Integer[] day;
	private JLabel dateLabel;
	private JLabel yearLabel;
	private JLabel monthLabel;
	private JLabel dayLabel;
	
	//Departure Time
	private ArrayList<JComboBox> time;
	private Integer[] hour;
	private Integer[] minute;
	private JLabel timeLabel;
	private JLabel hourLabel;
	private JLabel minuteLabel;
	
	//Search priority
	private JList<String> priority;
	private DefaultListModel<String> priorityOrderModel;
	private JLabel priorityLabel;

	
	//Airline
	private JComboBox airlines;
	private JLabel airlineLabel;
	
	//Num output
	private JFormattedTextField numOutput;
	private JLabel numOutputLabel;
	private NumberFormat numberFormat;
	
	//Start
	private JButton search;
	//clear
	private JButton clear;
	
	private String[] cities;
	private String[] airlineList;
	
	private boolean mouseDragging = false;
    private int dragSourceIndex;
	
    private FlightScheduler flightScheduler;
		
	public Sidebar(FlightScheduler flightScheduler){
		this.flightScheduler = flightScheduler;
		String[] cities = {"a","b","c","d"};
		startCity = new JComboBox(cities);
		startCityLabel = new JLabel();
		endCity = new JComboBox(cities);
		endCityLabel = new JLabel();
		date = new ArrayList<JComboBox>();
		dateLabel = new JLabel();

		year = new Integer[501];
		day = new Integer[31];
		year[0] = 2000;
		for(int i = 1; i <= 500; i++){
			if(i <= 31){
				day[i-1] = i;
			}
			year[i] = 2000 + i;
		}
		yearLabel = new JLabel();
		monthLabel = new JLabel();
		dayLabel = new JLabel();
		
		date.add(new JComboBox(day));
		date.add(new JComboBox(month));
		date.add(new JComboBox(year));
		
		time = new ArrayList<JComboBox>();
		timeLabel = new JLabel();
		hour = new Integer[24];
		minute = new Integer[60];
		for(int i = 0; i < 60; i++){
			if(i < 24){
				hour[i] = i;
			}
			minute[i] = i;
		}
		hourLabel = new JLabel();
		minuteLabel = new JLabel();
		time.add(new JComboBox(hour));
		time.add(new JComboBox(minute));
		
		String[] listAirline = {"None", "Virgin", "Qantas"};
		airlines = new JComboBox(listAirline);
		airlineLabel = new JLabel();
		numberFormat.getIntegerInstance();
		numOutput = new JFormattedTextField(numberFormat);
		numOutput.setValue(10);
		numOutput.setFocusLostBehavior(JFormattedTextField.PERSIST);
		numOutput.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				numOutput.setText(numOutput.getText());
				numOutput.selectAll();
			}
		});
		numOutputLabel = new JLabel();
		search = new JButton("Search");
		
		// do we need clear?
		clear = new JButton("Clear");
		
		search.addActionListener(this);
		clear.addActionListener(this);
		
		priorityLabel = new JLabel();

		priorityOrderModel = new DefaultListModel<String>();
		priorityOrderModel.ensureCapacity(3);
		priorityOrderModel.addElement("Time");
		priorityOrderModel.addElement("Cost");
		priorityOrderModel.addElement("Airline");
		
		priority = new JList<String>(priorityOrderModel);
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
	                    String dragElement = priorityOrderModel.get(dragSourceIndex);
	                    priorityOrderModel.remove(dragSourceIndex);
	                    priorityOrderModel.add(dragTargetIndex, dragElement);
	                    dragSourceIndex = currentIndex;
	                }
	            }
	        }
		});
		display();

	}
	
	private void display(){
		setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		startCityLabel.setText("From: ");
		endCityLabel.setText("To: ");
		dateLabel.setText("Depart Date: ");
		yearLabel.setText("Year:");
		monthLabel.setText("Month:");
		dayLabel.setText("Day:");
		timeLabel.setText("Depart Time: ");
		hourLabel.setText("Hour:");
		minuteLabel.setText("Min:");
		airlineLabel.setText("Airline Preference: ");
		numOutputLabel.setText("No. Routes: ");
		priorityLabel.setText("Flight Priority Order: ");
		
		
		JPanel container = new JPanel();
		container.setPreferredSize(new Dimension(330, 220));
		container.setBackground(Color.lightGray);
		container.setLayout(new GridBagLayout());
		
		JPanel container2 = new JPanel();
		container2.setPreferredSize(new Dimension(330, 100));
		container2.setBackground(Color.lightGray);
		container2.setLayout(new GridBagLayout());
		
		JPanel container3 = new JPanel();
		container3.setPreferredSize(new Dimension(330, 130));
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

		// priority gridy
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.5;
		c.gridx = 0;
		c.gridy = 0;
		container2.add(priorityLabel, c);
		
		JLabel order = new JLabel();
		order.setText("<html>1 <p>2 <p>3 <p><b><font color=#B82E00>(?)</font></b><p></html>");
		order.setToolTipText("Click and drag options to rearrange priority order, "
				+ "with 1 as highest priority");
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
		c.gridy = 3;
		container.add(dateLabel, c);
		
		c.insets = new Insets(10,15,0,0);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.5;
		c.gridx = 1;
		c.gridy = 2;
		container.add(dayLabel, c);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.5;
		c.gridx = 2;
		c.gridy = 2;
		container.add(monthLabel, c);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.5;
		c.gridx = 3;
		c.gridy = 2;
		container.add(yearLabel, c);
		
		c.insets = new Insets(10,0,0,0);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.5;
		c.gridx = 1;
		c.gridy = 3;
		container.add(date.get(0), c);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.5;
		c.gridx = 2;
		c.gridy = 3;
		container.add(date.get(1), c);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.5;
		c.gridx = 3;
		c.gridy = 3;
		container.add(date.get(2), c);
		
		//time gridy 3
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.5;
		c.gridx = 0;
		c.gridy = 5;
		c.gridwidth = 2;
		container.add(timeLabel, c);
		
		c.gridwidth = 1;
		c.insets = new Insets(10,20,0,0);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.5;
		c.gridx = 2;
		c.gridy = 4;
		container.add(hourLabel, c);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.5;
		c.gridx = 3;
		c.gridy = 4;
		container.add(minuteLabel, c);
		
		c.insets = new Insets(10,0,0,0);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.5;
		c.gridx = 2;
		c.gridy = 5;
		container.add(time.get(0), c);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.5;
		c.gridx = 3;
		c.gridy = 5;
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
		c.insets = new Insets(10,0,0,25);
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
		container3.add(airlines, c);
		
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
		c.insets = new Insets(30,0,0,22);
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
	
//	public Flight makeFlightRequest(){
//		Flight request = new Flight(City from, City to, Calendar departTime, Calendar arrivalTime,
//				String airline, int cost);
//	}
//	
//	public Query makeQuery(){
//		Query query = new Query(Arraylist<Preference> preferenceOrder, Flight request, int numPlansToShow);
//	}
//	
//	public City makeCity(){
//		City city = new City(String name);
//	}
//	
//	public Calendar makeCalendar(){
//		Calendar departTime = new GregorianCalendar(int year, int month, int dayOfMonth, int hourOfDay, int min);
//	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// Check date
		// If valid pass values in FlightScheduler object to create new query
		if(e.getSource().equals(search)){
			
			String cityFrom = (String) startCity.getItemAt(startCity.getSelectedIndex());
			String cityTo = (String) endCity.getItemAt(endCity.getSelectedIndex());
			int departYear = (int) date.get(2).getItemAt(date.get(2).getSelectedIndex());
			int departMonth = date.get(1).getSelectedIndex();
			int departDay = (int) date.get(0).getItemAt(date.get(0).getSelectedIndex());

			int departHour = (int) time.get(0).getItemAt(time.get(0).getSelectedIndex());
			int departMinute = (int) time.get(1).getItemAt(time.get(1).getSelectedIndex());
			
			Calendar departTime = new GregorianCalendar(departYear, departMonth, departDay,
					departHour, departMinute);
			String airline = (String) airlines.getItemAt(airlines.getSelectedIndex());
			
			
			// proper dates from calendar.
			System.out.println(departTime.get(Calendar.DAY_OF_MONTH) + " " + departTime.get(Calendar.MONTH) + " " + departTime.get(Calendar.YEAR));
			
			System.out.println(departDay + " " + departMonth + " " + departYear + "; " + departHour 
					+ " " + departMinute  + "; " + airline);
			
			if (departMonth != departTime.get(Calendar.MONTH)){
				System.out.println("Invalid Date");
				JOptionPane.showMessageDialog(this,
					    "Invalid date input.",
					    "Invalid Input",
					    JOptionPane.ERROR_MESSAGE);
				return;
			}

//			Flight request = new Flight(cityFrom, cityTo, departTime, null, airline, 0);
			
//			ArrayList<Preference> preferenceOrder = new ArrayList<Preference>();
//			for(int i = 0; i < 3; i ++){
//				if(priorityModel.get(i).equals("Cost")){
//					preferenceOrder.add(Preference.COST);
//				}else if(priorityModel.get(i).equals("Time")){
//					preferenceOrder.add(Preference.TIME);
//				}else if(priorityModel.get(i).equals("Airline")){
//					preferenceOrder.add(Preference.NAME);
//				}
//			}
			
			int numPlansToShow;
			try{
				numPlansToShow = Integer.parseInt(numOutput.getText());
			} catch(NumberFormatException e2){
				numPlansToShow = 0;
				JOptionPane.showMessageDialog(this,
					    "Invalid number of routes input.",
					    "Invalid Input",
					    JOptionPane.ERROR_MESSAGE);
				// display error message?
				// or default 10?
			}
			
			// TODO uncomment this when function added.
//			flightScheduler.addQuery(cityFrom, cityTo, year, month, day, hour, minute, airline, 
//					priorityOrderModel, numPlansToShow);
			
//			Query query = new Query(preferenceOrder, request, numPlansToShow);
			

			for(int i = 0; i < 3; i ++){
				System.out.print(priorityOrderModel.get(i) + " ");
			}
			System.out.print("; numOutput: " + numPlansToShow);
			System.out.println();
		}
	}
}
