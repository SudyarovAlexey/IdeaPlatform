import java.text.ParseException;
import java.util.Collections;
import java.util.List;

public class FlightDurations {

    private static final int PERCENTILE = 90;
    private static final double HUNDRED_PERCENTS = 100;
    private static final double MILLISECONDS_TO_HOURS = 3_600_000;

    private double totalDuration;

    public void getDurationListTotalDuration(Root root, List<Double> durationList) {
        root.tickets.forEach(ticket -> {
            try {
                double hours = (ticket.getArrivalDateAndTime().getTime()
                        - ticket.getDepartureDateAndTime().getTime()) / MILLISECONDS_TO_HOURS;
                durationList.add(hours);
                totalDuration = totalDuration + hours;
            } catch (ParseException e) {
                e.printStackTrace();
            }
        });
        Collections.sort(durationList);
    }

    public double getAvgDuration(List<Double> durationList) {
        int count = durationList.size();
        return totalDuration / count;
    }

    public Double getPercentileMethodA(List<Double> durationList) {
        int index = (int) Math.round(PERCENTILE * durationList.size() / HUNDRED_PERCENTS);
        return durationList.get(index - 1);
    }

    public Double getPercentileMethodB(List<Double> durationList) {
        int index = (int) Math.round((PERCENTILE * (durationList.size() - 1) / HUNDRED_PERCENTS) + 1);
        return (durationList.get(index) - durationList.get(index - 1)) * PERCENTILE / HUNDRED_PERCENTS
                + durationList.get(index - 1);
    }

    public void getResults(double avgDuration, double percentileA, double percentileB) {
        System.out.printf("Среднее время полета между городами Владивосток и Тель-Авив: %.2f часа(ов) %n", avgDuration);
        System.out.printf("90-й процентиль времени полета между городами Владивосток и Тель-Авив" +
                " по методу A: %.2f часов%n", percentileA);
        System.out.printf("90-й процентиль времени полета между городами Владивосток и Тель-Авив" +
                " по методу B: %.2f часов%n", percentileB);
    }

}
