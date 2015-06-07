import java.util.Calendar;

public class Flight {
    private City from;
    private City to;
    private Calendar departTime;
    private int travelTime;
    private String airline;
    private int cost;

    public Flight(City from, City to, Calendar departTime, int travelTime, String airline, int cost) {
        this.from = from;
        this.to = to;
        this.departTime = departTime;
        this.travelTime = travelTime;
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
     * Returns the time taken to travel from A to B.
     * @return travel time
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
     * Gets the cost of the flight
     * @return cost
     */
    public int getCost() {
        return cost;
    }

    public Calendar getArrivalTime() {
        Calendar arrivalTime = (Calendar)departTime.clone();
        arrivalTime.add(Calendar.MINUTE, travelTime + 60);
        
        return arrivalTime;
    }

    public Calendar getArrivalTimePlusDelay() {
        Calendar arrivalTime = (Calendar)departTime.clone();
        arrivalTime.add(Calendar.MINUTE, travelTime + 60);
        return arrivalTime;
    }

}
