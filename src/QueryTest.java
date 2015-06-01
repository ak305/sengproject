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


    @Test
    public void testSearchForFlightPlans2() throws Exception {
        City sydney = new City("Sydney");
        City melbourne = new City("Melbourne");
        City adelaide = new City("Adelaide");
        City brisbane = new City("Brisbane");

        //################################################# SYDNEY ----> MELBOURNE
        sydney.addFlight(
                new Flight(
                        sydney,
                        melbourne,
                        new GregorianCalendar(2015, Calendar.MARCH, 13, 0, 0),
                        new GregorianCalendar(2015, Calendar.MARCH, 13, 1, 30),
                        "Qantas",
                        90
                )
        );

        sydney.addFlight(
                new Flight(
                        sydney,
                        melbourne,
                        new GregorianCalendar(2015, Calendar.MARCH, 13, 0, 0),
                        new GregorianCalendar(2015, Calendar.MARCH, 13, 1, 30),
                        "Virgin",
                        90
                )
        );

        sydney.addFlight(
                new Flight(
                        sydney,
                        melbourne,
                        new GregorianCalendar(2015, Calendar.MARCH, 13, 2, 0),
                        new GregorianCalendar(2015, Calendar.MARCH, 13, 3, 30),
                        "Qantas",
                        90
                )
        );

        sydney.addFlight(
                new Flight(
                        sydney,
                        melbourne,
                        new GregorianCalendar(2015, Calendar.MARCH, 13, 2, 0),
                        new GregorianCalendar(2015, Calendar.MARCH, 13, 3, 30),
                        "Virgin",
                        90
                )
        );

        sydney.addFlight(
                new Flight(
                        sydney,
                        melbourne,
                        new GregorianCalendar(2015, Calendar.MARCH, 13, 4, 0),
                        new GregorianCalendar(2015, Calendar.MARCH, 13, 5, 30),
                        "Qantas",
                        90
                )
        );

        sydney.addFlight(
                new Flight(
                        sydney,
                        melbourne,
                        new GregorianCalendar(2015, Calendar.MARCH, 13, 4, 0),
                        new GregorianCalendar(2015, Calendar.MARCH, 13, 5, 30),
                        "Virgin",
                        90
                )
        );

        sydney.addFlight(
                new Flight(
                        sydney,
                        melbourne,
                        new GregorianCalendar(2015, Calendar.MARCH, 13, 6, 0),
                        new GregorianCalendar(2015, Calendar.MARCH, 13, 7, 30),
                        "Qantas",
                        90
                )
        );

        sydney.addFlight(
                new Flight(
                        sydney,
                        melbourne,
                        new GregorianCalendar(2015, Calendar.MARCH, 13, 6, 0),
                        new GregorianCalendar(2015, Calendar.MARCH, 13, 7, 30),
                        "Virgin",
                        90
                )
        );




        //################################################# SYDNEY ----> BRISBANE
        sydney.addFlight(
                new Flight(
                        sydney,
                        brisbane,
                        new GregorianCalendar(2015, Calendar.MARCH, 13, 0, 0),
                        new GregorianCalendar(2015, Calendar.MARCH, 13, 1, 30),
                        "Qantas",
                        90
                )
        );

        sydney.addFlight(
                new Flight(
                        sydney,
                        brisbane,
                        new GregorianCalendar(2015, Calendar.MARCH, 13, 0, 0),
                        new GregorianCalendar(2015, Calendar.MARCH, 13, 1, 30),
                        "Virgin",
                        90
                )
        );

        sydney.addFlight(
                new Flight(
                        sydney,
                        brisbane,
                        new GregorianCalendar(2015, Calendar.MARCH, 13, 2, 0),
                        new GregorianCalendar(2015, Calendar.MARCH, 13, 3, 30),
                        "Qantas",
                        90
                )
        );

        sydney.addFlight(
                new Flight(
                        sydney,
                        brisbane,
                        new GregorianCalendar(2015, Calendar.MARCH, 13, 2, 0),
                        new GregorianCalendar(2015, Calendar.MARCH, 13, 3, 30),
                        "Virgin",
                        90
                )
        );

        sydney.addFlight(
                new Flight(
                        sydney,
                        brisbane,
                        new GregorianCalendar(2015, Calendar.MARCH, 13, 4, 0),
                        new GregorianCalendar(2015, Calendar.MARCH, 13, 5, 30),
                        "Qantas",
                        90
                )
        );

        sydney.addFlight(
                new Flight(
                        sydney,
                        brisbane,
                        new GregorianCalendar(2015, Calendar.MARCH, 13, 4, 0),
                        new GregorianCalendar(2015, Calendar.MARCH, 13, 5, 30),
                        "Virgin",
                        90
                )
        );

        sydney.addFlight(
                new Flight(
                        sydney,
                        brisbane,
                        new GregorianCalendar(2015, Calendar.MARCH, 13, 6, 0),
                        new GregorianCalendar(2015, Calendar.MARCH, 13, 7, 30),
                        "Qantas",
                        90
                )
        );

        sydney.addFlight(
                new Flight(
                        sydney,
                        brisbane,
                        new GregorianCalendar(2015, Calendar.MARCH, 13, 6, 0),
                        new GregorianCalendar(2015, Calendar.MARCH, 13, 7, 30),
                        "Virgin",
                        90
                )
        );




        //################################################# MELBOURNE ----> BRISBANE
        melbourne.addFlight(
                new Flight(
                        melbourne,
                        brisbane,
                        new GregorianCalendar(2015, Calendar.MARCH, 13, 0, 0),
                        new GregorianCalendar(2015, Calendar.MARCH, 13, 2, 0),
                        "Qantas",
                        120
                )
        );

        melbourne.addFlight(
                new Flight(
                        melbourne,
                        brisbane,
                        new GregorianCalendar(2015, Calendar.MARCH, 13, 0, 0),
                        new GregorianCalendar(2015, Calendar.MARCH, 13, 2, 0),
                        "Virgin",
                        120
                )
        );

        melbourne.addFlight(
                new Flight(
                        melbourne,
                        brisbane,
                        new GregorianCalendar(2015, Calendar.MARCH, 13, 3, 0),
                        new GregorianCalendar(2015, Calendar.MARCH, 13, 5, 0),
                        "Qantas",
                        120
                )
        );

        melbourne.addFlight(
                new Flight(
                        melbourne,
                        brisbane,
                        new GregorianCalendar(2015, Calendar.MARCH, 13, 3, 0),
                        new GregorianCalendar(2015, Calendar.MARCH, 13, 5, 0),
                        "Virgin",
                        120
                )
        );

        melbourne.addFlight(
                new Flight(
                        melbourne,
                        brisbane,
                        new GregorianCalendar(2015, Calendar.MARCH, 13, 6, 0),
                        new GregorianCalendar(2015, Calendar.MARCH, 13, 8, 0),
                        "Qantas",
                        120
                )
        );

        melbourne.addFlight(
                new Flight(
                        melbourne,
                        brisbane,
                        new GregorianCalendar(2015, Calendar.MARCH, 13, 6, 0),
                        new GregorianCalendar(2015, Calendar.MARCH, 13, 8, 0),
                        "Virgin",
                        120
                )
        );

        melbourne.addFlight(
                new Flight(
                        melbourne,
                        brisbane,
                        new GregorianCalendar(2015, Calendar.MARCH, 13, 9, 0),
                        new GregorianCalendar(2015, Calendar.MARCH, 13, 11, 0),
                        "Qantas",
                        120
                )
        );

        melbourne.addFlight(
                new Flight(
                        melbourne,
                        brisbane,
                        new GregorianCalendar(2015, Calendar.MARCH, 13, 9, 0),
                        new GregorianCalendar(2015, Calendar.MARCH, 13, 11, 0),
                        "Virgin",
                        120
                )
        );





        //################################################# MELBOURNE ----> ADELAIDE
        melbourne.addFlight(
                new Flight(
                        melbourne,
                        adelaide,
                        new GregorianCalendar(2015, Calendar.MARCH, 13, 0, 0),
                        new GregorianCalendar(2015, Calendar.MARCH, 13, 1, 30),
                        "Qantas",
                        90
                )
        );

        melbourne.addFlight(
                new Flight(
                        melbourne,
                        adelaide,
                        new GregorianCalendar(2015, Calendar.MARCH, 13, 0, 0),
                        new GregorianCalendar(2015, Calendar.MARCH, 13, 1, 30),
                        "Virgin",
                        90
                )
        );

        melbourne.addFlight(
                new Flight(
                        melbourne,
                        adelaide,
                        new GregorianCalendar(2015, Calendar.MARCH, 13, 2, 0),
                        new GregorianCalendar(2015, Calendar.MARCH, 13, 3, 30),
                        "Qantas",
                        90
                )
        );

        melbourne.addFlight(
                new Flight(
                        melbourne,
                        adelaide,
                        new GregorianCalendar(2015, Calendar.MARCH, 13, 2, 0),
                        new GregorianCalendar(2015, Calendar.MARCH, 13, 3, 30),
                        "Virgin",
                        90
                )
        );

        melbourne.addFlight(
                new Flight(
                        melbourne,
                        adelaide,
                        new GregorianCalendar(2015, Calendar.MARCH, 13, 4, 0),
                        new GregorianCalendar(2015, Calendar.MARCH, 13, 5, 30),
                        "Qantas",
                        90
                )
        );

        melbourne.addFlight(
                new Flight(
                        melbourne,
                        adelaide,
                        new GregorianCalendar(2015, Calendar.MARCH, 13, 4, 0),
                        new GregorianCalendar(2015, Calendar.MARCH, 13, 5, 30),
                        "Virgin",
                        90
                )
        );

        melbourne.addFlight(
                new Flight(
                        melbourne,
                        adelaide,
                        new GregorianCalendar(2015, Calendar.MARCH, 13, 6, 0),
                        new GregorianCalendar(2015, Calendar.MARCH, 13, 7, 30),
                        "Qantas",
                        90
                )
        );

        melbourne.addFlight(
                new Flight(
                        melbourne,
                        adelaide,
                        new GregorianCalendar(2015, Calendar.MARCH, 13, 6, 0),
                        new GregorianCalendar(2015, Calendar.MARCH, 13, 7, 30),
                        "Virgin",
                        90
                )
        );

        melbourne.addFlight(
                new Flight(
                        melbourne,
                        adelaide,
                        new GregorianCalendar(2015, Calendar.MARCH, 13, 8, 0),
                        new GregorianCalendar(2015, Calendar.MARCH, 13, 9, 30),
                        "Qantas",
                        90
                )
        );

        melbourne.addFlight(
                new Flight(
                        melbourne,
                        adelaide,
                        new GregorianCalendar(2015, Calendar.MARCH, 13, 8, 0),
                        new GregorianCalendar(2015, Calendar.MARCH, 13, 9, 30),
                        "Virgin",
                        90
                )
        );

        melbourne.addFlight(
                new Flight(
                        melbourne,
                        adelaide,
                        new GregorianCalendar(2015, Calendar.MARCH, 13, 10, 0),
                        new GregorianCalendar(2015, Calendar.MARCH, 13, 11, 30),
                        "Qantas",
                        90
                )
        );

        melbourne.addFlight(
                new Flight(
                        melbourne,
                        adelaide,
                        new GregorianCalendar(2015, Calendar.MARCH, 13, 10, 0),
                        new GregorianCalendar(2015, Calendar.MARCH, 13, 11, 30),
                        "Virgin",
                        90
                )
        );

        melbourne.addFlight(
                new Flight(
                        melbourne,
                        adelaide,
                        new GregorianCalendar(2015, Calendar.MARCH, 13, 12, 0),
                        new GregorianCalendar(2015, Calendar.MARCH, 13, 13, 30),
                        "Qantas",
                        90
                )
        );

        melbourne.addFlight(
                new Flight(
                        melbourne,
                        adelaide,
                        new GregorianCalendar(2015, Calendar.MARCH, 13, 12, 0),
                        new GregorianCalendar(2015, Calendar.MARCH, 13, 13, 30),
                        "Virgin",
                        90
                )
        );

        melbourne.addFlight(
                new Flight(
                        melbourne,
                        adelaide,
                        new GregorianCalendar(2015, Calendar.MARCH, 13, 14, 0),
                        new GregorianCalendar(2015, Calendar.MARCH, 13, 15, 30),
                        "Qantas",
                        90
                )
        );

        melbourne.addFlight(
                new Flight(
                        melbourne,
                        adelaide,
                        new GregorianCalendar(2015, Calendar.MARCH, 13, 14, 0),
                        new GregorianCalendar(2015, Calendar.MARCH, 13, 15, 30),
                        "Virgin",
                        90
                )
        );


        //################################################# BRISBANE ----> MELBOURNE
        brisbane.addFlight(
                new Flight(
                        brisbane,
                        melbourne,
                        new GregorianCalendar(2015, Calendar.MARCH, 13, 1, 0),
                        new GregorianCalendar(2015, Calendar.MARCH, 13, 3, 0),
                        "Qantas",
                        120
                )
        );

        brisbane.addFlight(
                new Flight(
                        brisbane,
                        melbourne,
                        new GregorianCalendar(2015, Calendar.MARCH, 13, 1, 0),
                        new GregorianCalendar(2015, Calendar.MARCH, 13, 3, 0),
                        "Virgin",
                        120
                )
        );

        brisbane.addFlight(
                new Flight(
                        brisbane,
                        melbourne,
                        new GregorianCalendar(2015, Calendar.MARCH, 13, 4, 0),
                        new GregorianCalendar(2015, Calendar.MARCH, 13, 6, 0),
                        "Qantas",
                        120
                )
        );

        brisbane.addFlight(
                new Flight(
                        brisbane,
                        melbourne,
                        new GregorianCalendar(2015, Calendar.MARCH, 13, 4, 0),
                        new GregorianCalendar(2015, Calendar.MARCH, 13, 6, 0),
                        "Virgin",
                        120
                )
        );

        brisbane.addFlight(
                new Flight(
                        brisbane,
                        melbourne,
                        new GregorianCalendar(2015, Calendar.MARCH, 13, 7, 0),
                        new GregorianCalendar(2015, Calendar.MARCH, 13, 9, 0),
                        "Qantas",
                        120
                )
        );

        brisbane.addFlight(
                new Flight(
                        brisbane,
                        melbourne,
                        new GregorianCalendar(2015, Calendar.MARCH, 13, 7, 0),
                        new GregorianCalendar(2015, Calendar.MARCH, 13, 9, 0),
                        "Virgin",
                        120
                )
        );

        brisbane.addFlight(
                new Flight(
                        brisbane,
                        melbourne,
                        new GregorianCalendar(2015, Calendar.MARCH, 13, 10, 0),
                        new GregorianCalendar(2015, Calendar.MARCH, 13, 12, 0),
                        "Qantas",
                        120
                )
        );

        brisbane.addFlight(
                new Flight(
                        brisbane,
                        melbourne,
                        new GregorianCalendar(2015, Calendar.MARCH, 13, 10, 0),
                        new GregorianCalendar(2015, Calendar.MARCH, 13, 12, 0),
                        "Virgin",
                        120
                )
        );

        brisbane.addFlight(
                new Flight(
                        brisbane,
                        melbourne,
                        new GregorianCalendar(2015, Calendar.MARCH, 13, 13, 0),
                        new GregorianCalendar(2015, Calendar.MARCH, 13, 15, 0),
                        "Qantas",
                        120
                )
        );

        brisbane.addFlight(
                new Flight(
                        brisbane,
                        melbourne,
                        new GregorianCalendar(2015, Calendar.MARCH, 13, 13, 0),
                        new GregorianCalendar(2015, Calendar.MARCH, 13, 15, 0),
                        "Virgin",
                        120
                )
        );


        //################################################# BRISBANE ----> ADELAIDE
        brisbane.addFlight(
                new Flight(
                        brisbane,
                        adelaide,
                        new GregorianCalendar(2015, Calendar.MARCH, 13, 1, 0),
                        new GregorianCalendar(2015, Calendar.MARCH, 13, 2, 30),
                        "Qantas",
                        90
                )
        );

        brisbane.addFlight(
                new Flight(
                        brisbane,
                        adelaide,
                        new GregorianCalendar(2015, Calendar.MARCH, 13, 1, 0),
                        new GregorianCalendar(2015, Calendar.MARCH, 13, 2, 30),
                        "Virgin",
                        90
                )
        );

        brisbane.addFlight(
                new Flight(
                        brisbane,
                        adelaide,
                        new GregorianCalendar(2015, Calendar.MARCH, 13, 3, 0),
                        new GregorianCalendar(2015, Calendar.MARCH, 13, 4, 30),
                        "Qantas",
                        90
                )
        );

        brisbane.addFlight(
                new Flight(
                        brisbane,
                        adelaide,
                        new GregorianCalendar(2015, Calendar.MARCH, 13, 3, 0),
                        new GregorianCalendar(2015, Calendar.MARCH, 13, 4, 30),
                        "Virgin",
                        90
                )
        );

        brisbane.addFlight(
                new Flight(
                        brisbane,
                        adelaide,
                        new GregorianCalendar(2015, Calendar.MARCH, 13, 5, 0),
                        new GregorianCalendar(2015, Calendar.MARCH, 13, 6, 30),
                        "Qantas",
                        90
                )
        );

        brisbane.addFlight(
                new Flight(
                        brisbane,
                        adelaide,
                        new GregorianCalendar(2015, Calendar.MARCH, 13, 5, 0),
                        new GregorianCalendar(2015, Calendar.MARCH, 13, 6, 30),
                        "Virgin",
                        90
                )
        );

        brisbane.addFlight(
                new Flight(
                        brisbane,
                        adelaide,
                        new GregorianCalendar(2015, Calendar.MARCH, 13, 7, 0),
                        new GregorianCalendar(2015, Calendar.MARCH, 13, 8, 30),
                        "Qantas",
                        90
                )
        );

        brisbane.addFlight(
                new Flight(
                        brisbane,
                        adelaide,
                        new GregorianCalendar(2015, Calendar.MARCH, 13, 7, 0),
                        new GregorianCalendar(2015, Calendar.MARCH, 13, 8, 30),
                        "Virgin",
                        90
                )
        );

        brisbane.addFlight(
                new Flight(
                        brisbane,
                        adelaide,
                        new GregorianCalendar(2015, Calendar.MARCH, 13, 9, 0),
                        new GregorianCalendar(2015, Calendar.MARCH, 13, 10, 30),
                        "Qantas",
                        90
                )
        );

        brisbane.addFlight(
                new Flight(
                        brisbane,
                        adelaide,
                        new GregorianCalendar(2015, Calendar.MARCH, 13, 9, 0),
                        new GregorianCalendar(2015, Calendar.MARCH, 13, 10, 30),
                        "Virgin",
                        90
                )
        );

        brisbane.addFlight(
                new Flight(
                        brisbane,
                        adelaide,
                        new GregorianCalendar(2015, Calendar.MARCH, 13, 11, 0),
                        new GregorianCalendar(2015, Calendar.MARCH, 13, 12, 30),
                        "Qantas",
                        90
                )
        );

        brisbane.addFlight(
                new Flight(
                        brisbane,
                        adelaide,
                        new GregorianCalendar(2015, Calendar.MARCH, 13, 11, 0),
                        new GregorianCalendar(2015, Calendar.MARCH, 13, 12, 30),
                        "Virgin",
                        90
                )
        );



        Flight request = new Flight(
                sydney,
                adelaide,
                new GregorianCalendar(2015, Calendar.MARCH, 12, 22, 0),
                null,
                "Virgin",
                0
        );



        Query query1 = new Query(
                new ArrayList<Preference>(){{
                    add(Preference.TIME);
                    add(Preference.COST);
                    add(Preference.NAME);
                }},
                request,
                5
        );

        Query query2 = new Query(
                new ArrayList<Preference>(){{
                    add(Preference.TIME);
                    add(Preference.COST);
                    add(Preference.NAME);
                }},
                request,
                100
        );

        query1.searchForFlightPlans();
        printQuery(query1);

        query2.searchForFlightPlans();
        printQuery(query2);
    }

    public void printQuery(Query query) {
        for (FlightPlan fp: query.getFlightPlans()) {
            //System.out
            for (Flight f: fp.getFlightPath()) {
                System.out.printf(
                        "%-8s to %8s\t%-6s\t%3d\t %2d:%-2d\n",
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
                        getArrivalTime(new GregorianCalendar(2000, Calendar.FEBRUARY, 29, 11, 30), 598),
                        "Jal",
                        2100
                )
        );
        
        sydney.addFlight(
                new Flight(
                        sydney,
                        singapore,
                        new GregorianCalendar(2000, Calendar.FEBRUARY, 29, 11, 0),
                        getArrivalTime(new GregorianCalendar(2015, Calendar.FEBRUARY, 29, 11, 0), 481),
                        "Singaporeairlines",
                        1500
                )
        );
        
        adelaide.addFlight(
                new Flight(
                		adelaide,
                        sydney,
                        new GregorianCalendar(2000, Calendar.FEBRUARY, 29, 9, 0),
                        getArrivalTime(new GregorianCalendar(2015, Calendar.FEBRUARY, 29, 9, 0), 110),
                        "Qantas",
                        200
                )
        );
        
        adelaide.addFlight(
                new Flight(
                		adelaide,
                        singapore,
                        new GregorianCalendar(2000, Calendar.FEBRUARY, 29, 8, 45),
                        getArrivalTime(new GregorianCalendar(2015, Calendar.FEBRUARY, 29, 8, 45), 602),
                        "Singaporeairlines",
                        1900
                )
        );
        
        singapore.addFlight(
                new Flight(
                		singapore,
                		frankfurt,
                        new GregorianCalendar(2000, Calendar.FEBRUARY, 29, 22, 0),
                        getArrivalTime(new GregorianCalendar(2015, Calendar.FEBRUARY, 29, 22, 0), 600),
                        "Lufthansa",
                        3900
                )
        );
        
        tokyo.addFlight(
                new Flight(
                		tokyo,
                		frankfurt,
                        new GregorianCalendar(2000, Calendar.FEBRUARY, 29, 23, 30),
                        getArrivalTime(new GregorianCalendar(2015, Calendar.FEBRUARY, 29, 23, 30), 721),
                        "Lufthansa",
                        4500
                )
        );
        
        tokyo.addFlight(
                new Flight(
                		tokyo,
                		frankfurt,
                        new GregorianCalendar(2000, Calendar.FEBRUARY, 29, 23, 0),
                        getArrivalTime(new GregorianCalendar(2015, Calendar.FEBRUARY, 29, 23, 0), 718),
                        "Jal",
                        5001
                )
        );
        
        
        Flight request = new Flight(
                adelaide,
                frankfurt,
                new GregorianCalendar(2015, Calendar.FEBRUARY, 29, 8, 30),
                null,
                "None",
                -1
        );
        
        Query query = new Query(
                new ArrayList<Preference>(){{
                	add(Preference.TIME);
                    add(Preference.COST);
                    add(Preference.NAME);
                }},
                request,
                4
        );
    }
    
    
    private Calendar getArrivalTime(Calendar departTime, int travelTime) {
    	Calendar arrivalTime = (Calendar) departTime.clone();
    	arrivalTime.add(Calendar.MINUTE, travelTime);
    	return arrivalTime;
    }
}