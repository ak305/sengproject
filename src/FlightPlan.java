import java.util.ArrayList;


public class FlightPlan {
    private ArrayList<Flight> flightPath;
    private int totalCost;
    private int totalTime;
    private int freqFlierHours;
    private City currentCity;

    FlightPlan(City currentCity, ArrayList<Flight> flightPath, int totalCost, int totalTime, int freqFlierHours) {
        this.currentCity = currentCity;
        this.flightPath = flightPath;
        this.totalCost = totalCost;
        this.totalTime = totalTime;
        this.freqFlierHours = freqFlierHours;
    }

    /**
     *
     * @return
     */
    public ArrayList<Flight> getFlightPath() {
        return flightPath;
    }

    /**
     *
     * @return
     */
    public int getTotalCost() {
        return totalCost;
    }

    /**
     *
     * @return
     */
    public int getTotalTime() {
        return totalTime;
    }

    /**
     *
     * @return
     */
    public int getFreqFlierHours() {
        return freqFlierHours;
    }

    public City getCurrentCity() {
        return currentCity;
    }

    public Flight getLastFlight() {
        return flightPath.get(flightPath.size() - 1);
    }
    


   /*private Calendar timeBetween(Calendar departure, Calendar arrival) {
        int departureMonth = departure.get(Calendar.MONTH);
        int arrivalMonth = arrival.get(Calendar.MONTH);
        
        int departureDay = 
        
        
        int monthDiff = arrivalMonth - departureMonth;
        

    }*/
}
