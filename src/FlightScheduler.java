import java.util.ArrayList;

public class FlightScheduler {
    private ArrayList<City> cities;
    private ArrayList<Flight> flights;
    private ArrayList<ArrayList<Flight>> flightPlans;

    public FlightScheduler(ArrayList<City> cities, ArrayList<Flight> flights, ArrayList<ArrayList<Flight>> flightPlans) {
        this.cities = cities;
        this.flights = flights;
        this.flightPlans = flightPlans;
    }

    public static void main (String[] args) {
        FlightScheduler flightScheduler = new FlightScheduler(
                new ArrayList<City>(),
                new ArrayList<Flight>(),
                new ArrayList<ArrayList<Flight>>()
        );
        System.out.println("Super sperm");
    }

    public static void searchForFlights() {
        // insert paths generated into flightPlans

    }

    public static void sortFlights() {
        // need to take in parameters for sorting? Should we make an enum class for the 3 options
    }
}
