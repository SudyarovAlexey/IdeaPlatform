import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Getter
@Setter
public class Ticket {

    private String origin;

    @JsonProperty("origin_name")
    private String originName;

    private String destination;

    @JsonProperty("destination_name")
    private String destinationName;

    @JsonProperty("departure_date")
    private String departureDate;

    @JsonProperty("departure_time")
    private String departureTime;

    @JsonProperty("arrival_date")
    private String arrivalDate;

    @JsonProperty("arrival_time")
    private String arrivalTime;

    private String carrier;

    private int stops;

    private int price;

    public Date getArrivalDateAndTime() throws ParseException {
        String dateTime = arrivalDate + arrivalTime;
        return new SimpleDateFormat("dd.MM.yyHH:mm").parse(dateTime);
    }

    public Date getDepartureDateAndTime() throws ParseException {
        String dateTime = departureDate + departureTime;
        return new SimpleDateFormat("dd.MM.yyHH:mm").parse(dateTime);
    }

}