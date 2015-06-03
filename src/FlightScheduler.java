import javax.swing.*;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class FlightScheduler {
	private ArrayList<City> cities;
	private ArrayList<Flight> flights;
	private ArrayList<Query> queries;
	private static MainWindow mainWindow;
	private boolean guiOpen;

	public FlightScheduler(ArrayList<City> cities, ArrayList<Flight> flights,
			ArrayList<Query> queries) throws IOException {
		this.cities = cities;
		this.flights = flights;
		this.queries = queries;
		guiOpen = false;
		try {
			this.mainWindow = new MainWindow(this);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws IOException {
		FlightScheduler flightScheduler = new FlightScheduler(
				new ArrayList<City>(), new ArrayList<Flight>(),
				new ArrayList<Query>());

		if (args.length != 0) {
            Scanner flightdata = null;
            try {
                // scanner for flightdata file -> EXAMPLE DATA:   [ 29/2/2000, 9:00, Adelaide, Sydney, 110, Qantas, 200 ]
                flightdata = new Scanner(new FileReader(args[0]));
                ArrayList<String> parseresults = new ArrayList<String>();
                flightdata.useDelimiter("/|,|:|\\]|\\n|\\[|\\s+");
                while (flightdata.hasNext()) {

                    String nextChar = flightdata.next();
                    if (!nextChar.equals("")) {
                        parseresults.add(nextChar);
                    }
                }
                //		    for (String j : parseresults){
                //		    	System.out.println(j);
                //		    }
                int i = 0;
                while (i < parseresults.size()) {
                    try {
                        int day = Integer.parseInt(parseresults.get(i++));
                        int month = Integer.parseInt(parseresults.get(i++));
                        int year = Integer.parseInt(parseresults.get(i++));
                        int hourTime = Integer.parseInt(parseresults.get(i++));
                        int minuteTime = Integer.parseInt(parseresults.get(i++));

                        String cityFrom = parseresults.get(i++);
                        String cityTo = parseresults.get(i++);

                        int travelTime = Integer.parseInt(parseresults.get(i++));
                        String airline = parseresults.get(i++);
                        int cost = Integer.parseInt(parseresults.get(i++));

                        Calendar departTime = new GregorianCalendar(year, month,
                                day, hourTime, minuteTime);

                        if (month != departTime.get(Calendar.MONTH) && hourTime != departTime.get(Calendar.HOUR_OF_DAY) && minuteTime != departTime.get(Calendar.MINUTE)) {
                            System.out.println("Invalid date/time in entry: " + "[" + day + "/" + month + "/" + year + ", " + hourTime + ":" + minuteTime + ", " + cityFrom + ", " + cityTo + ", " + travelTime + ", " + airline + ", " + cost + "]");
                        } else {
                            City from = flightScheduler.addCity(cityFrom);
                            City to = flightScheduler.addCity(cityTo);

                            flightScheduler.addFlight(from, to, departTime, travelTime,
                                    airline, cost);
                        }

                    } catch (NumberFormatException e) {
                        System.out.println("incorrectly formatted flight data");
                        return;
                    }
                }

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } finally {
                if (flightdata != null)
                    flightdata.close();
            }

            if (args.length == 2) {
                // Scan for query
                Scanner querydata = null;
                try {
                    // scanner for querydata file -> EXAMPLEDATA: [29/2/2000, 08:40, Adelaide, Singapore, (Qantas, Cost, Time), 1]
                    querydata = new Scanner(new FileReader(args[1]));
                    querydata.useDelimiter("/|,|:|\\]|\\n|\\[|\\s+|\\(|\\)");
                    ArrayList<String> parseresults = new ArrayList<String>();
                    while (querydata.hasNext()) {

                        String nextChar = querydata.next();
                        if (!nextChar.equals("")) {
                            parseresults.add(nextChar);
                        }

                    }
                    //			    for (String j : parseresults){
                    //			    	System.out.println(j);
                    //			    }
                    int i = 0;
                    while (i < parseresults.size()) {
                        try {
                            int day = Integer.parseInt(parseresults.get(i++));
                            int month = Integer.parseInt(parseresults.get(i++));
                            int year = Integer.parseInt(parseresults.get(i++));
                            int hourTime = Integer.parseInt(parseresults.get(i++));
                            int minuteTime = Integer.parseInt(parseresults.get(i++));

                            String cityFrom = parseresults.get(i++);
                            String cityTo = parseresults.get(i++);

                            DefaultListModel<String> pOrder = new DefaultListModel<String>();
                            String airline = null;

                            for (int k = 0; k < 3; k++) {
                                String pData = parseresults.get(i++);
                                // whatever the string is that isn't 'cost' or 'time' is the airline we need.
                                if (!pData.equals("Cost") && !pData.equals("Time")) {
                                    airline = pData;
                                }
                                pOrder.add(k, pData);
                            }

                            int numFlights = Integer.parseInt(parseresults.get(i++));
                            flightScheduler.addQuery(cityFrom, cityTo, year, month, day, hourTime, minuteTime, airline, pOrder, numFlights);

                        } catch (NumberFormatException e) {
                            System.out.println("incorrectly formatted query data");
                            return;
                        }
                    }
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } finally {
                    if (querydata != null)
                        querydata.close();
                }
                flightScheduler.printQueries();
            }

            if (args.length == 1) {
                flightScheduler.guiOpen = true;
                SwingUtilities.invokeLater(new Runnable() {
                    public void run() {
                        mainWindow.display();
                    }
                });
            }
        } else {
            System.out.println("No input files specified as parameter/s in the format:\n" +
                    "\t<flight data> <optional query data>");
        }
		System.out.println("Super sperm");
	}

	public void addQuery(String cityFrom, String cityTo, int year, int month,
			int day, int hour, int minute, String airline,
			DefaultListModel<String> preferenceOrder, int numFlights) {

		Calendar departTime = new GregorianCalendar(year, month, day, hour,
				minute);

		// traveltime & cost not inputted (leave as -1)
		Flight request = new Flight(this.getCity(cityFrom),	this.getCity(cityTo), departTime, -1, airline, -1);

		ArrayList<Preference> pOrder = new ArrayList<Preference>();
		for (int i = 0; i < 3; i++) {
			if (preferenceOrder.get(i).equals("Cost")) {
				pOrder.add(Preference.COST);
			} else if (preferenceOrder.get(i).equals("Time")) {
				pOrder.add(Preference.TIME);
			} else {
				pOrder.add(Preference.NAME);
			}
		}

		Query newQ = new Query(pOrder, request, numFlights);
		this.queries.add(newQ);

	}

	public City addCity(String cityName) {
		// search through existing cities to see if we already have a city of this name
		for (City c : this.cities) {
			if (c.getName().equals(cityName))
				return c;
		}

		City newCity = new City(cityName);
		this.cities.add(newCity);
		return newCity;
	}

	public City getCity(String cityName) {
		for (City c : this.cities) {
			if (c.getName().equals(cityName))
				return c;
		}
		System.out.println("City not found!");
		return null;
	}

	public void addFlight(City from, City to, Calendar departTime, int travelTime, String airline, int cost) {
		Flight newFlight = new Flight(from, to, departTime, travelTime, airline, cost);
		this.flights.add(newFlight);
		from.getOutgoingFlights().add(newFlight);
	}
	
	public ArrayList<String> getAirlines() {
		ArrayList<String> airlines = new ArrayList<>();
		for (Flight flight : flights) {
			if (!airlines.contains(flight.getAirline())) {
				airlines.add(flight.getAirline());
			}
		}
		Collections.sort(airlines);
		return airlines;
	}
	
	public ArrayList<String> getCityNames() {
    	ArrayList<String> cityNames = new ArrayList<>();
    	for (City city: cities) {
    		if (!cityNames.contains(city.getName())) {
    			cityNames.add(city.getName());
    		}
    	}
    	Collections.sort(cityNames);
    	return cityNames;
    }
	
	public void printQueries(){
		for(Query q : queries){
			q.searchForFlightPlans();
			String output = q.getFlightPlan(guiOpen);
			System.out.println(output);
			if(guiOpen){
				 mainWindow.displayFlights(output);
			}
		}
	}
}
