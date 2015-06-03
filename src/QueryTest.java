import org.junit.Test;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import static org.junit.Assert.*;

public class QueryTest {
    
    @Test
    public void testSearchAlgorithm() throws Exception {
    	City sydney = new City("Sydney");
        City adelaide = new City("Adelaide");
        City singapore = new City("Singapore");
        City tokyo = new City("Tokyo");
        City frankfurt = new City("Frankfurt");

        sydney.addFlight(
                new Flight(
                        sydney,
                        tokyo,
                        new GregorianCalendar(2000, Calendar.FEBRUARY, 29, 11, 30),
                        598,
                        "Jal",
                        2100
                )
        );
        
        sydney.addFlight(
                new Flight(
                        sydney,
                        singapore,
                        new GregorianCalendar(2000, Calendar.FEBRUARY, 29, 11, 0),
                        481,
                        "Singaporeairlines",
                        1500
                )
        );
        
        adelaide.addFlight(
                new Flight(
                		adelaide,
                        sydney,
                        new GregorianCalendar(2000, Calendar.FEBRUARY, 29, 9, 0),
                        110,
                        "Qantas",
                        200
                )
        );
        
        adelaide.addFlight(
                new Flight(
                		adelaide,
                        singapore,
                        new GregorianCalendar(2000, Calendar.FEBRUARY, 29, 8, 45),
                        602,
                        "Singaporeairlines",
                        1900
                )
        );
        
        singapore.addFlight(
                new Flight(
                		singapore,
                		frankfurt,
                        new GregorianCalendar(2000, Calendar.FEBRUARY, 29, 22, 0),
                        600,
                        "Lufthansa",
                        3900
                )
        );
        
        tokyo.addFlight(
                new Flight(
                		tokyo,
                		frankfurt,
                        new GregorianCalendar(2000, Calendar.FEBRUARY, 29, 23, 30),
                        721,
                        "Lufthansa",
                        4500
                )
        );
        
        tokyo.addFlight(
                new Flight(
                		tokyo,
                		frankfurt,
                        new GregorianCalendar(2000, Calendar.FEBRUARY, 29, 23, 0),
                        718,
                        "Jal",
                        5001
                )
        );
        
        
        Flight request1 = new Flight(
                adelaide,
                frankfurt,
                new GregorianCalendar(2000, Calendar.FEBRUARY, 29, 8, 30),
                -1,
                "None",
                -1
        );
        
        Query query1 = new Query(
                new ArrayList<Preference>(){{
                	add(Preference.TIME);
                    add(Preference.COST);
                    add(Preference.NAME);
                }},
                request1,
                4
        );
        query1.searchForFlightPlans();
        printQuery(query1);


        Flight request2 = new Flight(
                adelaide,
                singapore,
                new GregorianCalendar(2000, Calendar.FEBRUARY, 29, 8, 40),
                -1,
                "Qantas",
                -1
        );

        Query query2 = new Query(
                new ArrayList<Preference>(){{
                    add(Preference.NAME);
                    add(Preference.COST);
                    add(Preference.TIME);
                }},
                request2,
                1
        );
        query2.searchForFlightPlans();
        printQuery(query2);


        Flight request3 = new Flight(
                sydney,
                frankfurt,
                new GregorianCalendar(2000, Calendar.FEBRUARY, 29, 10, 0),
                -1,
                "Jal",
                -1
        );

        Query query3 = new Query(
                new ArrayList<Preference>(){{
                    add(Preference.NAME);
                    add(Preference.TIME);
                    add(Preference.COST);
                }},
                request3,
                5
        );
        query3.searchForFlightPlans();
        printQuery(query3);

        assert query1.getFlightPlans().size() == 1;
        assertEquals(frankfurt, query1.getFlightPlans().get(0).getCurrentCity());
        assertEquals(5800, query1.getFlightPlans().get(0).getTotalCost());
        assertEquals(1202, query1.getFlightPlans().get(0).getTotalTime());
        assertEquals(0, query1.getFlightPlans().get(0).getFreqFlierHours());
        assertEquals(singapore.getOutgoingFlights().get(0), query1.getFlightPlans().get(0).getLastFlight());

        assert query2.getFlightPlans().size() == 0;

        assert  query3.getFlightPlans().size() == 1;
        assertEquals(frankfurt, query3.getFlightPlans().get(0).getCurrentCity());
        assertEquals(7101, query3.getFlightPlans().get(0).getTotalCost());
        assertEquals(1316, query3.getFlightPlans().get(0).getTotalTime());
        assertEquals(7101, query3.getFlightPlans().get(0).getFreqFlierHours());

        System.out.println("ALL TESTS PASSED!!!\nYOU ARE AWESOME");
    }

    private void printQuery(Query query) {
        for (FlightPlan fp: query.getFlightPlans()) {
            for (Flight f: fp.getFlightPath()) {
                System.out.printf(
                        "%-10s to %10s,\t%-18s\t%3d\t %2d:%-2d\n",
                        f.getFrom().getName(),
                        f.getTo().getName(),
                        f.getAirline(),
                        f.getCost(),
                        f.getDepartTime().getTime().getHours(),
                        f.getDepartTime().getTime().getMinutes()
                );
            }
            System.out.println(">>>>>>>>>>>>>>>>>>>>>");
        }
        System.out.printf("\n###########################################################\n\n");
    }
}