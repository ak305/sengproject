import java.util.Comparator;

public enum Preference implements Comparator<FlightPlan>{
    //Depending on the enum, a different compare method is selected for the Flight comparator
    COST {
        @Override
        public int compare(FlightPlan planA, FlightPlan planB) {
            return planA.getTotalCost() - planB.getTotalCost();
        }
        @Override
        public String toString() {
            return "Cost";
        }
    },
    NAME {
        @Override
        public int compare(FlightPlan planA, FlightPlan planB) {
            return planB.getFreqFlierHours() - planA.getFreqFlierHours();
        }
        @Override
        public String toString() {
            return "Name";
        }
    },
    TIME {
        @Override
        public int compare(FlightPlan planA, FlightPlan planB) {
            return (planA.getTotalTime() - planB.getTotalTime());
        }
        @Override
        public String toString() {
            return "Time";
        }
    }

}
