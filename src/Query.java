import java.util.ArrayList;

public class Query {
    private ArrayList<Preference> preferenceOrder;      // Order of preference - sorting
    private Flight request;                             // The customer request to be fulfilled (there will not be a direct flight)
    private ArrayList<ArrayList<Flight>> flightPlans;   // The result flights after the search
    private int numPlansToShow;                         // N flight plans to show to customer

    public Query(ArrayList<Preference> preferenceOrder, Flight request, int numPlansToShow) {
        this.preferenceOrder = preferenceOrder;
        this.request = request;
        this.flightPlans = new ArrayList<>();
        this.numPlansToShow = numPlansToShow;
    }

    /**
     * Gets the airline that the request would prefer to use.
     * @return preferred Airline
     */
    public String getPrefferedAirline() {
        return request.getAirline();
    }

    /**
     * Searches for flight paths, adding each to the flightPlans list
     */
    private void searchForFlights() {
        // insert paths generated into flightPlans
    }

    /**
     * Sorts the flightPlans by the preferences the query specifies.
     */
    private void sortFlights() {
        // need to take in parameters for sorting? Should we make an enum class for the 3 options
    }

    /////////////////////
    // PRIVATE HELPERS //
    /////////////////////

}
