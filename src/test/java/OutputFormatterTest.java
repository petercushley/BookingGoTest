import Client.OutputFormatter;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class OutputFormatterTest {
    private OutputFormatter formatter = new OutputFormatter();
    private String mockDaveJson = "{\"supplier_id\":\"DAVE\",\"pickup\":\"51.470020,-0.454295\",\"dropoff\":\"3.410632,-2.157533\",\"options\":[{\"car_type\":\"EXECUTIVE\",\"price\":544426},{\"car_type\":\"PEOPLE_CARRIER\",\"price\":549569},{\"car_type\":\"LUXURY_PEOPLE_CARRIER\",\"price\":469991},{\"car_type\":\"MINIBUS\",\"price\":550808}]}";
    private String mockEricJson = "{\"supplier_id\":\"ERIC\",\"pickup\":\"51.470020,-0.454295\",\"dropoff\":\"3.410632,-2.157533\",\"options\":[{\"car_type\":\"STANDARD\",\"price\":946569},{\"car_type\":\"EXECUTIVE\",\"price\":562379},{\"car_type\":\"LUXURY\",\"price\":109378},{\"car_type\":\"PEOPLE_CARRIER\",\"price\":334477}]}";
    private String mockJeffJson = "{\"supplier_id\":\"JEFF\",\"pickup\":\"51.470020,-0.454295\",\"dropoff\":\"3.410632,-2.157533\",\"options\":[{\"car_type\":\"LUXURY\",\"price\":162172},{\"car_type\":\"PEOPLE_CARRIER\",\"price\":269857},{\"car_type\":\"LUXURY_PEOPLE_CARRIER\",\"price\":207322},{\"car_type\":\"MINIBUS\",\"price\":851638}]}";

    @Test
    public void formatForOutputTest() {
        String[] responses = {mockDaveJson};
        String output = formatter.formatForOutput(responses, 4);
        String expected = "EXECUTIVE - DAVE - 544426\n" +
                "PEOPLE_CARRIER - DAVE - 549569\n" +
                "LUXURY_PEOPLE_CARRIER - DAVE - 469991\n" +
                "MINIBUS - DAVE - 550808\n";
        assertEquals(expected, output);
    }

    @Test
    public void numberOfPassengersTest() {
        String[] responses = {mockDaveJson};
        String output = formatter.formatForOutput(responses, 6);
        String expected = "PEOPLE_CARRIER - DAVE - 549569\n" +
                "LUXURY_PEOPLE_CARRIER - DAVE - 469991\n" +
                "MINIBUS - DAVE - 550808\n";
        assertEquals(expected, output);

        String output2 = formatter.formatForOutput(responses, 18);
        String expected2 = "";
        assertEquals(expected2, output2);
    }

    @Test
    public void priceFilterTest() {
        String[] responses = {mockDaveJson, mockEricJson, mockJeffJson};
        String output = formatter.formatForOutput(responses, 6);
        System.out.println(output);
        String expected = "PEOPLE_CARRIER - JEFF - 269857\n" +
                "LUXURY_PEOPLE_CARRIER - JEFF - 207322\n" +
                "MINIBUS - DAVE - 550808\n";
        assertEquals(expected, output);
    }
}
