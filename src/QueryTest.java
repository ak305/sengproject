import org.junit.Test;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class QueryTest {

    @Test
    public void testSearchForFlightPlans() throws Exception {
        City sydney = new City("Sydney");
        City melbourne = new City("Melbourne");
        City adelaide = new City("Adelaide");
        City brisbane = new City("Brisbane");

        sydney.addFlight(
                new Flight(
                        sydney,
                        melbourne,
                        new GregorianCalendar(2015, Calendar.MARCH, 13, 12, 0),
                        new GregorianCalendar(2015, Calendar.MARCH, 13, 15, 0),
                        "Qantas",
                        180
                )
        );

        sydney.addFlight(
                new Flight(
                        sydney,
                        brisbane,
                        new GregorianCalendar(2015, Calendar.MARCH, 13, 12, 0),
                        new GregorianCalendar(2015, Calendar.MARCH, 13, 14, 0),
                        "Qantas",
                        120
                )
        );

        sydney.addFlight(
                new Flight(
                        sydney,
                        melbourne,
                        new GregorianCalendar(2015, Calendar.MARCH, 13, 13, 0),
                        new GregorianCalendar(2015, Calendar.MARCH, 13, 16, 0),
                        "Virgin",
                        180
                )
        );

        brisbane.addFlight(
                new Flight(
                        brisbane,
                        melbourne,
                        new GregorianCalendar(2015, Calendar.MARCH, 13, 12, 0),
                        new GregorianCalendar(2015, Calendar.MARCH, 13, 16, 0),
                        "Virgin",
                        240
                )
        );

        brisbane.addFlight(
                new Flight(
                        brisbane,
                        adelaide,
                        new GregorianCalendar(2015, Calendar.MARCH, 13, 13, 0),
                        new GregorianCalendar(2015, Calendar.MARCH, 13, 18, 0),
                        "Virgin",
                        300
                )
        );

        melbourne.addFlight(
                new Flight(
                        melbourne,
                        adelaide,
                        new GregorianCalendar(2015, Calendar.MARCH, 13, 13, 0),
                        new GregorianCalendar(2015, Calendar.MARCH, 13, 14, 0),
                        "Qantas",
                        60
                )
        );

        melbourne.addFlight(
                new Flight(
                        melbourne,
                        adelaide,
                        new GregorianCalendar(2015, Calendar.MARCH, 13, 15, 0),
                        new GregorianCalendar(2015, Calendar.MARCH, 13, 16, 0),
                        "Virgin",
                        60
                )
        );

        melbourne.addFlight(
                new Flight(
                        melbourne,
                        adelaide,
                        new GregorianCalendar(2015, Calendar.MARCH, 13, 20, 0),
                        new GregorianCalendar(2015, Calendar.MARCH, 13, 21, 0),
                        "Virgin",
                        60
                )
        );

        melbourne.addFlight(
                new Flight(
                        melbourne,
                        brisbane,
                        new GregorianCalendar(2015, Calendar.MARCH, 13, 16, 0),
                        new GregorianCalendar(2015, Calendar.MARCH, 13, 20, 0),
                        "Qantas",
                        240
                )
        );

        Flight request = new Flight(
                sydney,
                adelaide,
                new GregorianCalendar(2015, Calendar.MARCH, 13, 9, 0),
                null,
                "Virgin",
                0
        );
        Query query = new Query(
                new ArrayList<Preference>(){{
                    add(Preference.COST);
                    add(Preference.NAME);
                    add(Preference.TIME);
                }},
                request,
                10
        );

        query.searchForFlightPlans();
        for (FlightPlan fp: query.getFlightPlans()) {
            //System.out
            for (Flight f: fp.getFlightPath()) {
                System.out.println(f.getFrom().getName() + " to " + f.getTo().getName() + " " + f.getAirline());
            }
            System.out.println(">>>>>>>");
        }
        System.out.println("I am awesome");
    }
}