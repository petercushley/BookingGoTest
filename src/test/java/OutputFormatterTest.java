import org.junit.Test;

import javax.ws.rs.core.Response;

import static org.junit.Assert.assertEquals;

public class OutputFormatterTest {
    OutputFormatter formatter = new OutputFormatter();
    APIAccess apiAccess = new APIAccess();

    @Test
    public void formatForOutputTest() {
        String pickup = "51.470020,-0.454295";
        String dropoff = "3.410632,-2.157533";
        String supplier = "dave";

        Response response = apiAccess.getTaxisFromSupplier(pickup, dropoff, supplier);

        String json = response.readEntity(String.class);

        String output = formatter.formatForOutput(json);

        String expected = "Luxury - 626431\nPeople Carrier - 108671\nMinibus - 987654";
        assertEquals(expected, output);
    }
}
