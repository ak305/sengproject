import java.util.ArrayList;

public class City {
    private String name;
    private ArrayList<Flight> outgoingFlights;

    public City(String name) {
        this.name = name;
        outgoingFlights = new ArrayList<Flight>();
    }

    /**
     * Gets the name of the city.
     * @return name
     */
    public String getName() {
        return name;
    }


    public ArrayList<Flight> getOutgoingFlights() {
        return outgoingFlights;
    }

    /**
     * Sets the name of the city.
     * @param name of the city
     */
    public void setName(String name) {
        this.name = name;
    }

    public void addFlight(Flight flight) {
        outgoingFlights.add(flight);
    }
}
