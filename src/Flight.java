import java.util.Calendar;

public class Flight {   //TODO: Need to make the class comparator or comparable
    private City from;
    private City to;
    private Calendar departTime;
    private int travelTime;
    private String airline;
    private int cost;           //FIXME: Is cost needed? aren't travelTime and cost same thing!?

    public Flight(City from, City to, Calendar departTime, int travelTime, String airline, int cost) {
        this.from = from;
        this.to = to;
        this.departTime = departTime;
        this.travelTime = travelTime;
        this.airline = airline;
        this.cost = cost;
    }

    /**
     * Gets the city that the flight leaves from.
     * @return from
     */
    public City getFrom() {
        return from;
    }

    /**
     * Gets the city that the flight gos to.
     * @return to
     */
    public City getTo() {
        return to;
    }

    /**
     * Gets the departure time of the flight from the start city.
     * @return departTime
     */
    public Calendar getDepartTime() {
        return departTime;
    }

    /**
     * Gets the time it takes to complete the flight from start to finish.
     * @return travelTime
     */
    public int getTravelTime() {
        return travelTime;
    }

    /**
     * Returns the airline name of this flight.
     * @return airline
     */
    public String getAirline() {
        return airline;
    }

    /**
     * Gets the cost of the flight??????
     * @return cost
     */
    public int getCost() {
        return cost;
    }
}
