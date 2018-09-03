import org.junit.Test;

import javax.ws.rs.core.Response;

import static org.junit.Assert.assertEquals;

public class OutputFormatterTest {
    OutputFormatter formatter = new OutputFormatter();
    String mockJson = "{\"supplier_id\":\"DAVE\",\"pickup\":\"51.470020,-0.454295\",\"dropoff\":\"3.410632,-2.157533\",\"options\":[{\"car_type\":\"EXECUTIVE\",\"price\":544426},{\"car_type\":\"PEOPLE_CARRIER\",\"price\":549569},{\"car_type\":\"LUXURY_PEOPLE_CARRIER\",\"price\":469991},{\"car_type\":\"MINIBUS\",\"price\":550808}]}";

    @Test
    public void formatForOutputTest() {
        String output = formatter.formatForOutput(mockJson, 16);
        String expected = "EXECUTIVE - 544426\n" +
                "PEOPLE_CARRIER - 549569\n" +
                "LUXURY_PEOPLE_CARRIER - 469991\n" +
                "MINIBUS - 550808\n";
        assertEquals(expected, output);
    }

    @Test
    public void numberOfPassengersTest() {
        String output = formatter.formatForOutput(mockJson, 6);
        String expected = "PEOPLE_CARRIER - 549569\n" +
                "LUXURY_PEOPLE_CARRIER - 469991\n" +
                "MINIBUS - 550808\n";
        assertEquals(expected, output);

        String output2 = formatter.formatForOutput(mockJson, 18);
        String expected2 = "";
        assertEquals(expected2, output2);
    }
}
