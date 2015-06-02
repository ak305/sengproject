import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.GregorianCalendar;
import java.util.Scanner;

import javax.swing.DefaultListModel;
import javax.swing.DefaultListModel;
import javax.swing.SwingUtilities;

public class FlightScheduler {
	private ArrayList<City> cities;
	private ArrayList<Flight> flights;
	private ArrayList<Query> queries;
	private static MainWindow mainWindow;

	public FlightScheduler(ArrayList<City> cities, ArrayList<Flight> flights,
			ArrayList<Query> queries) {
		this.cities = cities;
		this.flights = flights;
		this.queries = queries;
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
		
	//	mainWindow.displayFlights("DISPLAYING A TEST INPUT STRING");

		Scanner flightdata = null;
		Scanner newLine = null;
		try {
			// scanner for flightdata file
			flightdata = new Scanner(new FileReader(args[0]));

			while (flightdata.hasNextLine()) {
				// scanner for each new line
				newLine = new Scanner(flightdata.nextLine());
				String command = newLine.next();

				if (command.equals("Flight")) {
					// Flight -> [Date, Time, Name, Name, Duration, Name,
					// Number] *Note last Number=cost
					// assumes input is (for example):
					// Flight 29/3/2015 22:30 Sydney Melbourne 300 Qantas 500

					int day = flightdata.nextInt();
					int month = flightdata.nextInt();
					int year = flightdata.nextInt();
					int hourTime = flightdata.nextInt();
					int minuteTime = flightdata.nextInt();

					String cityFrom = flightdata.next();
					String cityTo = flightdata.next();

					int travelTime = flightdata.nextInt();
					String airline = flightdata.next();
					int cost = flightdata.nextInt();

					Calendar departTime = new GregorianCalendar(year, month,
							day, hourTime, minuteTime);

					City from = flightScheduler.addCity(cityFrom);
					City to = flightScheduler.addCity(cityTo);

					flightScheduler.addFlight(from, to, departTime, travelTime,
							airline, cost);

				} else {
					System.out.println("incorrectly formatted flight data");
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally {
			if (flightdata != null)
				flightdata.close();
			if (newLine != null)
				newLine.close();
		}
		
		if (args.length == 2) {
			// Scan for query
			Scanner querydata = null;
			newLine = null;
			try {
				// scanner for querydata file
				querydata = new Scanner(new FileReader(args[1]));
				while (querydata.hasNextLine()) {
					// scanner for each new line
					newLine = new Scanner(querydata.nextLine());
					String command = newLine.next();
	
					if (command.equals("Query")) {
						// Query => [Date, Time, Name, Name, PreferenceOrder, Number] *Note last Number = how many results to display.
						// assumes input is (for example):
						// Query 29/3/2015 22:30 Sydney Melbourne Cost Time Name 10
	
						int day = querydata.nextInt();
						int month = querydata.nextInt();
						int year = querydata.nextInt();
						int hourTime = querydata.nextInt();
						int minuteTime = querydata.nextInt();
	
						String cityFrom = querydata.next();
						String cityTo = querydata.next();
	
						DefaultListModel<String> pOrder = new DefaultListModel<String>();
	
						String airline = null;
						for (int i = 0; i < 3; i++){
							String pData = querydata.next();
							// whatever the string is that isn't 'cost' or 'time' is the airline we need.
							if (!pData.equals("Cost") && !pData.equals("Time")){
								airline = pData;
							}
							pOrder.add(i, pData);
						}
	
						int numFlights = querydata.nextInt();
						flightScheduler.addQuery(cityFrom, cityTo, year, month, day, hourTime, minuteTime, airline, pOrder, numFlights);
	
					} else {
						System.out.println("incorrectly formatted query data");
					}
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} finally {
				if (querydata != null)
					querydata.close();
				if (newLine != null)
					newLine.close();
			}
		}
		
		if (args.length == 1) {
			SwingUtilities.invokeLater(new Runnable() {
	    	    public void run() {
	    	    	mainWindow.display();
	    	    }
	    	});
		}
		
		System.out.println("Super sperm");
	}

	public void addQuery(String cityFrom, String cityTo, int year, int month,
			int day, int hour, int minute, String airline,
			DefaultListModel<String> preferenceOrder, int numFlights) {

		Calendar departTime = new GregorianCalendar(year, month, day, hour,
				minute);

		// traveltime & cost not inputted (leave as -1)
		Flight request = new Flight(this.getCity(cityFrom),
				this.getCity(cityTo), departTime, null, 0, airline, -1);

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

	public void addFlight(City from, City to, Calendar departTime,
			int travelTime, String airline, int cost) {
		
		Calendar arrivalTime = (Calendar) departTime.clone();
		arrivalTime.add(Calendar.MINUTE, travelTime);
		Flight newFlight = new Flight(from, to, departTime, arrivalTime, travelTime, airline, cost);
		this.flights.add(newFlight);
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
}
