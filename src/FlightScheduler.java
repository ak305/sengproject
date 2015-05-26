import java.util.ArrayList;

public class FlightScheduler {
    private ArrayList<City> cities;
    private ArrayList<Query> queries;

    public FlightScheduler(ArrayList<City> cities, ArrayList<Query> queries) {
        this.cities = cities;
        this.queries = queries;
    }

    //public Flight getFlightsFrom()
    public static void main (String[] args) {
        FlightScheduler flightScheduler = new FlightScheduler(
                new ArrayList<City>(),
                new ArrayList<Query>()
        );

        // Scan in from file
        // Add info to objects
    }
}
