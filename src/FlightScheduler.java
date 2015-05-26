import java.io.IOException;
import java.util.ArrayList;

import javax.swing.SwingUtilities;

public class FlightScheduler {
    private ArrayList<City> cities;
    private ArrayList<Flight> flights;
    private ArrayList<Query> queries;
    private static MainWindow mainWindow;

    public FlightScheduler(ArrayList<City> cities, ArrayList<Flight> flights, ArrayList<Query> queries) throws IOException {
        this.cities = cities;
        this.flights = flights;
        this.queries = queries;
        mainWindow = new MainWindow(this);
    }

    public static void main (String[] args) throws IOException {
        FlightScheduler flightScheduler = new FlightScheduler(
                new ArrayList<City>(),
                new ArrayList<Flight>(),
                new ArrayList<Query>()
        );
		
		// display the main window in a different thread.
		SwingUtilities.invokeLater(new Runnable() {
            public void run() {
            	mainWindow.display();
            }
        });
        System.out.println("Super sperm");
        
        mainWindow.displayFlights("DISPLAYING A TEST INPUT STRING");
    }
}
