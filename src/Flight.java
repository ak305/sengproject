import java.util.Calendar;

public class Flight {   // Need to make the class comparator or comparable
    private City from;
    private City to;
    private Calendar departTime;
    private int travelTime;
    private String airline;
    private int cost;           // Is cost needed? arent travelTime and cost same thing

    public Flight(City from, City to, Calendar departTime, int travelTime, String airline, int cost) {
        this.from = from;
        this.to = to;
        this.departTime = departTime;
        this.travelTime = travelTime;
        this.airline = airline;
        this.cost = cost;
    }
}
