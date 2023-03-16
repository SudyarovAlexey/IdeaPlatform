import java.time.LocalTime;

public class FlightStatsApp {


    public static void main(String[] args) {

        FlightDurations flightDurations = new FlightDurations();
        Tickets tickets = TicketParser.parse();

        flightDurations.calcDurationListTotalDuration(tickets);

        LocalTime avgDuration = flightDurations.calcAvgDuration();

        LocalTime percentileA = flightDurations.calcPercentileMethodA();
        LocalTime percentileB = flightDurations.calcPercentileMethodB();

        flightDurations.printResults(avgDuration, percentileA, percentileB);

    }

}



