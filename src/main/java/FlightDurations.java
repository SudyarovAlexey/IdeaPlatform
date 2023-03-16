import java.text.ParseException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FlightDurations {

    private static final int PERCENTILE = 90;
    private static final double HUNDRED_PERCENTS = 100;
    private static final double MILLISECONDS_TO_MINUTES = 60_000;
    private static final double HOUR = 60;
    private final List<Double> durationList = new ArrayList<>();

    private double totalDuration;

    public void calcDurationListTotalDuration(Tickets tickets) {
        tickets.tickets.forEach(ticket -> {
            try {
                double minutes = (ticket.getArrivalDateAndTime().getTime()
                        - ticket.getDepartureDateAndTime().getTime()) / MILLISECONDS_TO_MINUTES;
                durationList.add(minutes);
                totalDuration += minutes;
            } catch (ParseException e) {
                e.printStackTrace();
            }
        });
        Collections.sort(durationList);
    }

    public LocalTime calcAvgDuration() {
        double avgInt = totalDuration / durationList.size();
        double remainder = avgInt % HOUR;
        return getTimeFormat(avgInt, remainder);
    }

    public LocalTime calcPercentileMethodA() {
        int index = (int) Math.round(PERCENTILE * durationList.size() / HUNDRED_PERCENTS);
        double element = durationList.get(index - 1);
        double remainder = element % HOUR;
        return getTimeFormat(element, remainder);
    }

    public LocalTime calcPercentileMethodB() {
        int index = (int) Math.round((PERCENTILE * (durationList.size() - 1) / HUNDRED_PERCENTS) + 1);
        double element = (durationList.get(index) - durationList.get(index - 1)) * PERCENTILE / HUNDRED_PERCENTS
                + durationList.get(index - 1);
        double remainder = element % HOUR;
        return getTimeFormat(element, remainder);
    }

    private LocalTime getTimeFormat(double intPart, double remainder) {
        int hours = (int) ((intPart - remainder) / HOUR);
        int minutes = (int) remainder;
        return LocalTime.of(hours, minutes);
    }

    public void printResults(LocalTime avgDuration, LocalTime percentileA, LocalTime percentileB) {
        System.out.printf("Среднее время полета между городами Владивосток и Тель-Авив: %s часа(ов) %n", avgDuration);
        System.out.printf("90-й процентиль времени полета между городами Владивосток и Тель-Авив" +
                " по методу A: %s часов%n", percentileA);
        System.out.printf("90-й процентиль времени полета между городами Владивосток и Тель-Авив" +
                " по методу B: %s часов%n", percentileB);
    }

}
