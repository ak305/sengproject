import java.util.Calendar;

public class Flight {   //TODO: Need to make the class comparator or comparable
    private City from;
    private City to;
    private Calendar departTime;
    private Calendar arrivalTime;
    private String airline;
    private int cost;           //FIXME: Is cost needed? aren't travelTime and cost same thing!?

    public Flight(City from, City to, Calendar departTime, Calendar arrivalTime, String airline, int cost) {
        this.from = from;
        this.to = to;
        this.departTime = departTime;
        this.arrivalTime = arrivalTime;
        this.airline = airline;
        this.cost = cost;
    }

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
