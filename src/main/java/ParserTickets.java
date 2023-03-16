import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class ParserTickets {

    public Root parser() {

        ObjectMapper mapper = new ObjectMapper();
        Root root = new Root();

        try {
            return mapper.readValue(ParserTickets.class.getResourceAsStream("tickets.json"), Root.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return root;
    }

}
