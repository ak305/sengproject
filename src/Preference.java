import java.util.ArrayList;
import java.util.Comparator;

public enum Preference implements Comparator<ArrayList<Flight>>{
    //Depending on the enum, a different compare method is selected for the Flight comparator
    COST {
        @Override
        public int compare(ArrayList<Flight> flightPath1, ArrayList<Flight> flightPath2) {
            int f1Cost = 0;
            for (Flight f1: flightPath1) f1Cost += f1.getCost();

            int f2Cost = 0;
            for (Flight f2: flightPath2) f1Cost += f2.getCost();

            return f1Cost - f2Cost;
        }
    },
    NAME{
        //What happens if we don't know the name of the airline to sort the flight plan with
        @Override //Change to flights with greatest time with selected airline
        public int compare(ArrayList<Flight> flightPath1, ArrayList<Flight> flightPath2) {

            int f1AirlineCost = 0;
            int f2AirlineCost = 0;
            for (Flight f1: flightPath1) {
                //if (f1.getAirline())
            }
            //return f1.getFrom().getName().compareTo(f2.getFrom().getName());
        }
    },
    TIME {
        @Override
        public int compare(ArrayList<Flight> flightPath1, ArrayList<Flight> flightPath2) {
            int f1TravelTime = 0;
            int f2TravelTime = 0;

            for (Flight f1: flightPath1) {
                f1TravelTime = f1.getTravelTime()
            }

            for (int i = 0; i < flightPath2.size(); i++) {
                f1TravelTime += flightPath2.get(i).getTravelTime();

            }
            //return f1.getTravelTime() - f2.getTravelTime();
        }
    }
}
