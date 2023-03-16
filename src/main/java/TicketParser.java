import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class TicketParser {

    public static Tickets parse() {

        ObjectMapper mapper = new ObjectMapper();

        try {
            return mapper.readValue(TicketParser.class.getResourceAsStream("tickets.json"), Tickets.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new Tickets();
    }

}
