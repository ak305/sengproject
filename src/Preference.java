import java.util.Comparator;

public enum Preference implements Comparator<FlightPlan>{
    //Depending on the enum, a different compare method is selected for the Flight comparator
    COST {
        @Override
        public int compare(FlightPlan planA, FlightPlan planB) {
            return planA.getTotalCost() - planB.getTotalCost();
        }
    },
    NAME{
        //What happens if we don't know the name of the airline to sort the flight plan with
        @Override //Change to flights with greatest time with selected airline
        public int compare(FlightPlan planA, FlightPlan planB) {
            return planB.getFreqFlierHours() - planA.getFreqFlierHours();
        }
    },
    TIME {
        @Override
        public int compare(FlightPlan planA, FlightPlan planB) {
            return (int)(planA.getTotalTime() - planB.getTotalTime());
        }
    }

}
