import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.text.MaskFormatter;
import javax.swing.text.NumberFormatter;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;


public class Sidebar extends JPanel implements ActionListener {
	// background image
	private Image backgroundImage;
	
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
	
	// list of all city names
	private ArrayList<String> cityNames;
	
	// list of all airlines
	private ArrayList<String> airlineList;
	
	// states used by the drag and drop priority order
	private boolean mouseDragging = false;
    private int dragSourceIndex;
	
    private FlightScheduler flightScheduler;
	
    /**
     * Creates instances of all the required labels, combo boxes, textfields and priority order drag and drop
     * @param flightScheduler
     */
	public Sidebar(FlightScheduler flightScheduler){
		this.flightScheduler = flightScheduler;

		try {
			backgroundImage = ImageIO.read(new File("LeftPanel.png"));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		cityNames = flightScheduler.getCityNames();
		airlineList = flightScheduler.getAirlines();
		airlineList.add(0, "None");
		
		startCity = new JComboBox<>(cityNames.toArray());
		startCityLabel = new JLabel();
		endCity = new JComboBox<>(cityNames.toArray());
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
		
		date.add(new JComboBox<>(day));
		date.add(new JComboBox<>(month));
		date.add(new JComboBox<>(year));
		
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
		time.add(new JComboBox<>(hour));
		time.add(new JComboBox<>(minute));
		
//		String[] listAirline = {"None", "Virgin", "Qantas"};
		airlines = new JComboBox<>(airlineList.toArray());
		airlineLabel = new JLabel();
		
		NumberFormat longFormat = NumberFormat.getIntegerInstance();
		NumberFormatter numberFormatter = new NumberFormatter(longFormat);
		numOutput = new JFormattedTextField(numberFormatter) {
			  public void processKeyEvent(KeyEvent ev) {
				    char c = ev.getKeyChar();
				    if (!(Character.isDigit(c) || c == KeyEvent.VK_DELETE || c == KeyEvent.VK_BACK_SPACE)) {
				    	ev.consume();
				    }
				    super.processKeyEvent(ev);
			  }
		};
		
		numOutput.setValue(5);
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
		
		search.addActionListener(this);
		
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
	
	/**
	 * Adds text to all the created labels, and displays them on the gui
	 */
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
		priorityLabel.setText("<html>Flight Priority Order: <b><font color=#B82E00>(?)</font></b></html>");
		
		addTopPanel();
		addMidPanel();
		addBotPanel();
	}
	
	/**
	 * Displays all the elements in the top part of the sidebar
	 */
	public void addTopPanel(){
		JPanel container = new JPanel();
		container.setPreferredSize(new Dimension(330, 220));
		container.setOpaque(false);
		container.setLayout(new GridBagLayout());
		add(container, BorderLayout.NORTH);
		
		GridBagConstraints c = new GridBagConstraints();
		c.anchor = GridBagConstraints.NORTH;
		c.insets = new Insets(10,0,0,0);
		
		// date
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
	}
	
	/**
	 * Displays all the elements in the middle part of the sidebar
	 */
	public void addMidPanel(){
		JPanel container2 = new JPanel();
		container2.setPreferredSize(new Dimension(330, 100));
		container2.setOpaque(false);
		container2.setLayout(new GridBagLayout());
		add(container2, BorderLayout.CENTER);
		
		GridBagConstraints c = new GridBagConstraints();
		c.anchor = GridBagConstraints.NORTH;
		c.insets = new Insets(10,0,0,0);
		
		JScrollPane pane = new JScrollPane();
		pane.setPreferredSize(new Dimension(100, 56));
		pane.getViewport().add(priority);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.5;
		c.gridx = 1;
		c.gridy = 0;
		container2.add(pane, c);

		// priority
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.5;
		c.gridx = 0;
		c.gridy = 0;
		container2.add(priorityLabel, c);
		
		JLabel order = new JLabel();
		order.setText("<html>1 <p>2 <p>3 <p></html>");
		priorityLabel.setToolTipText("Click and drag options to rearrange priority order, "
				+ "with 1 as highest priority");
		c.fill = GridBagConstraints.VERTICAL;
		c.weightx = 0.1;
		c.gridx = 2;
		c.gridy = 0;
		container2.add(order, c);
	}
	
	/**
	 * Displays all the elements in the bottom part of the sidebar
	 */
	public void addBotPanel(){
		JPanel container3 = new JPanel();
		container3.setPreferredSize(new Dimension(330, 150));
		container3.setOpaque(false);
		container3.setLayout(new GridBagLayout());
		add(container3, BorderLayout.SOUTH);
		add(container3, BorderLayout.SOUTH);
		
		GridBagConstraints c = new GridBagConstraints();
		c.anchor = GridBagConstraints.NORTH;
		c.insets = new Insets(10,0,0,0);
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
		
		// search grid 9
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(50,10,0,0);
		c.weightx = 0.5;
		c.gridx = 1;
		c.gridy = 2;
		container3.add(search, c);
	}
	
	/**
	 * Gets the preferred size of the sidebar
	 */
	public Dimension getPreferredSize() {
        return new Dimension(350, 750);
    }
	
	/**
	 * Overrides the paintComponent function to add a background image to the sidebar
	 */
	public void paintComponent(Graphics g) {
        super.paintComponent(g);
    	g.drawImage(backgroundImage, 0, 0, null);
    }	

	@Override
	public void actionPerformed(ActionEvent e) {
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
			
			// checking for invalid input into calendar
			if (departMonth != departTime.get(Calendar.MONTH)){
				System.out.println("Invalid Date");
				JOptionPane.showMessageDialog(this,
					    "Invalid date input.",
					    "Invalid Input",
					    JOptionPane.ERROR_MESSAGE);
				return;
			}
			
			int numPlansToShow;
			try{
				numPlansToShow = Integer.parseInt(numOutput.getText());
			} 
			// checking for invalid input into num routes to display textfield
			catch(NumberFormatException e2){
				numPlansToShow = 0;
				JOptionPane.showMessageDialog(this,
					    "Invalid number of routes input.",
					    "Invalid Input",
					    JOptionPane.ERROR_MESSAGE);
			}
			flightScheduler.addQuery(cityFrom, cityTo, departYear, departMonth, departDay, 
					departHour, departMinute, airline, 
					priorityOrderModel, numPlansToShow);
			
			flightScheduler.printQueries();
		}
	}
}
