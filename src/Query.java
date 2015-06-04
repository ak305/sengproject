import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.*;
import java.util.ArrayList;
import java.util.PriorityQueue;

public class Query {
    private ArrayList<Preference> preferenceOrder;      // Order of preference - sorting
    private Flight request;                             // The customer request to be fulfilled (there will not be a direct flight)
    private ArrayList<FlightPlan> flightPlans;   // The result flights after the search
    private int numPlansToShow;                         // N flight plans to show to customer

    public Query(ArrayList<Preference> preferenceOrder, Flight request, int numPlansToShow) {
        this.preferenceOrder = preferenceOrder;
        this.request = request;
        this.flightPlans = new ArrayList<FlightPlan>();
        this.numPlansToShow = numPlansToShow;
    }

    public ArrayList<FlightPlan> getFlightPlans() { return flightPlans; }
    /**
     * Searches for flight paths, adding each to the flightPlans list
     */
    protected void searchForFlightPlans() {
        // insert paths generated into flightPlans
//    	System.out.println(request.getDepartTime().get(Calendar.YEAR));

        //Searching path, based on the three preferences criteria 

        //Dj algo for min cost, min travel time
        //except with different primary preferences conditions
        //dfs on airlines

        //When searching for flightPaths calculate the totalCost, totalTime, freqFlierHours 
        //before adding into list of FlightPlans
        PriorityQueue<FlightPlan> pQueue = new PriorityQueue<FlightPlan>(
                10,
                new FlightPlanChainedComparator(
                        preferenceOrder.get(0),
                        preferenceOrder.get(1),
                        preferenceOrder.get(2)
                )
        );
        ArrayList<FlightPlan> endPlans = new ArrayList<FlightPlan>();

        pQueue.add(new FlightPlan(request.getFrom(), null, 0, 0, 0));
        while (pQueue.size() > 0) {
            FlightPlan currentPlan = pQueue.poll();

            for (Flight flight : currentPlan.getCurrentCity().getOutgoingFlights()) {
                // If the first sorting preference is the airline and the current flight is not of the specified airline,
                // don't return a flight plan with this flight.
                if (preferenceOrder.get(0).equals(Preference.NAME) &&
                        !flight.getAirline().equals(request.getAirline())) continue;

                // If the flight occurs before the last flight in our path (+60min delay), skip
                if (currentPlan.getFlightPath() != null &&
                        flight.getDepartTime().before(currentPlan.getLastFlight().getArrivalTimePlusDelay())) continue;

                // If the flight leaves before the requested departure time, skip
                if (flight.getDepartTime().before(request.getDepartTime())) continue;

//                if (currentPlan.getFlightPath() != null &&
//                		flight.getDepartTime().after(currentPlan.getLastFlight().getArrivalTime()) && 
//                		request.getArrivalTime().after(flight.getDepartTime())) continue;
                FlightPlan newPlan = createNeighbour(flight, currentPlan);
                pQueue.add(newPlan);
            }

            //Flight plan ends in the requested destination
            if (currentPlan.getCurrentCity().equals(request.getTo())) {
                endPlans.add(currentPlan);
            }
            //endPlans will contain the first N flight plans. Some optimal flight plans may be excluded??
            if (endPlans.size() == numPlansToShow) break;

        }
        flightPlans.addAll(endPlans);
    }

    /////////////////////
    // PRIVATE HELPERS //
    /////////////////////

//    private int calcFreqFlierPoints(String airline) {
//        int points = 0;
//        for (Flight flight : flightPath) {
//            if (flight.getAirline().equals(airline)) {
//                points += flight.getCost();
//            }
//        }
//        return points;
//    }
    private FlightPlan createNeighbour(Flight flight, FlightPlan currentPlan){
        ArrayList<Flight> newPath;
        if (currentPlan.getFlightPath() == null) {
            newPath = new ArrayList<Flight>();
        } else {
            newPath = new ArrayList<Flight>(currentPlan.getFlightPath());
        }

        newPath.add(flight);
        int freqFlierPoints = currentPlan.getFreqFlierHours();
        if (request.getAirline().equals(flight.getAirline())) {
            freqFlierPoints += flight.getTravelTime();
        }
        return new FlightPlan(
                flight.getTo(),
                newPath,
                currentPlan.getTotalCost() + flight.getCost(),
                currentPlan.getTotalTime() + flight.getTravelTime(),   // Changed this to long to handle times better(works nicer with calender)
                freqFlierPoints
        );
    }
    

    public String getFlightPlan(boolean guiOpen){
    	ByteArrayOutputStream outputString = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(outputString);
        
        ByteArrayOutputStream displayString = new ByteArrayOutputStream();
        PrintStream ds = new PrintStream(displayString);

        PrintStream old = System.out;

        System.setOut(ps);

        int qDay = request.getDepartTime().get(Calendar.DAY_OF_MONTH);
		int qMonth = request.getDepartTime().get(Calendar.MONTH)+1;
		int qYear = request.getDepartTime().get(Calendar.YEAR);
		int qHour = request.getDepartTime().get(Calendar.HOUR_OF_DAY);
		int qMinute = request.getDepartTime().get(Calendar.MINUTE);
		String qCityFrom = request.getFrom().getName();
		String qCityTo = request.getTo().getName();

        StringBuilder builder = new StringBuilder();

        for (Preference preference: preferenceOrder) {
            if (builder.length() != 0) {
                builder.append(", ");
            }
            if (preference.equals(Preference.NAME)){
                builder.append(request.getAirline());
            } else {
                builder.append(preference.toString());
            }
        }

        System.out.printf("( [ %s/%s/%s, %02d:%02d, %s, %s, (%s), %d ]\n",
                qDay, qMonth, qYear, qHour, qMinute, qCityFrom, qCityTo, builder.toString(), numPlansToShow
        );
        System.out.print(", [ ");
        
        System.setOut(ds);
        System.out.printf("Query:  %-13s to  %-13s  on  %s/%s/%s  at  %02d:%02d,  (%s),  Max Routes Shown: %d",
				qCityFrom, qCityTo, qDay, qMonth, qYear, qHour, qMinute,  builder.toString(), numPlansToShow);
        System.out.println();
        System.out.println();
        System.out.println();
        System.setOut(ps);

        int flightPlanCount = 0;
        
    	for(FlightPlan fp : flightPlans){
    		if(fp.getFlightPath() == null) continue;
        	int flightCount = 0;
        	
        	if(flightPlanCount == 0){
        		System.out.print("(( ");
        	}else{
        		System.out.print("    (( ");
        	}
        	System.setOut(ds);
			System.out.printf("Route %d:",flightPlanCount+1);
			System.out.println();
			System.setOut(ps);

        	
    		for(Flight f: fp.getFlightPath()){
    			int day = f.getDepartTime().get(Calendar.DAY_OF_MONTH);
    			int month = f.getDepartTime().get(Calendar.MONTH)+1;
    			int year = f.getDepartTime().get(Calendar.YEAR);
    			int hour = f.getDepartTime().get(Calendar.HOUR_OF_DAY);
    			int minute = f.getDepartTime().get(Calendar.MINUTE);
    			String cityFrom = f.getFrom().getName();
    			String cityTo = f.getTo().getName();
    			String airline = f.getAirline();
    			int cost = f.getCost();
    			int duration = f.getTravelTime();

    			if(flightCount != 0){
    				System.out.println();
    				System.out.print("       ");
    			}
    			flightCount++;
                System.out.printf("[ %s/%s/%s, %02d:%02d, %s, %s, %d, %s, %d ]",
                        day, month, year, hour, minute, cityFrom, cityTo, duration, airline, cost
                );
                System.setOut(ds);
                System.out.printf("            %-13s to  %-13s %s/%s/%s,  %02d:%02d,  Duration: %d,  Cost: %d,  Airline: %s",
                		cityFrom, cityTo, day, month, year, hour, minute, duration, cost, airline);
                System.out.println();
                System.setOut(ps);
                
    		}
    		System.out.print("), " + fp.getTotalCost() + ", " + fp.getTotalTime() + ", " + fp.getFreqFlierHours() + ")");
            if (!getFlightPlans().get(getFlightPlans().size()-1).equals(fp)) {
                System.out.println();
            }
            System.setOut(ds);
            System.out.print("            ---------------------------------------------------------------------------------- ");
            System.out.println();
            System.out.printf("            Total Time: %d mins,  Total Cost: $%d,  Frequent Flier Points Earned: %d",
            		fp.getTotalTime(), fp.getTotalCost(), fp.getFreqFlierHours());
            System.out.println();
            System.out.println();
            System.setOut(ps);
    		flightPlanCount++;
    	}
        System.out.print("])");
        System.out.println();

    	System.out.flush();
        System.setOut(old);
        if(guiOpen){
        	return displayString.toString();
        }else{
        	return outputString.toString();
        }
    }
}
