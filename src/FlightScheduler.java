import java.util.ArrayList;

public class FlightScheduler {
    private ArrayList<City> cities;
    private ArrayList<Flight> flights;
    private ArrayList<Query> queries;

    public FlightScheduler(ArrayList<City> cities, ArrayList<Flight> flights, ArrayList<Query> queries) {
        this.cities = cities;
        this.flights = flights;
        this.queries = queries;
    }

    public static void main (String[] args) {
        FlightScheduler flightScheduler = new FlightScheduler(
                new ArrayList<City>(),
                new ArrayList<Flight>(),
                new ArrayList<Query>()
        );
        System.out.println("Super sperm");
    }
}
