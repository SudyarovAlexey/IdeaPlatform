import java.util.ArrayList;
import java.util.List;

public class FlightStats {


    public static void main(String[] args) {

        double avgDuration;
        double percentileA;
        double percentileB;

        FlightDurations flightDurations = new FlightDurations();
        ParserTickets parser = new ParserTickets();
        Root root = parser.parser();
        List<Double> durationList = new ArrayList<>();

        flightDurations.getDurationListTotalDuration(root, durationList);

        avgDuration = flightDurations.getAvgDuration(durationList);

        percentileA = flightDurations.getPercentileMethodA(durationList);
        percentileB = flightDurations.getPercentileMethodB(durationList);

        flightDurations.getResults(avgDuration, percentileA, percentileB);

    }

}



