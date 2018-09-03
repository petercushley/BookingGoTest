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
        System.out.println(output);
//        String expected = "STANDARD - 366634\n" +
//                "LUXURY - 294581\n" +
//                "PEOPLE_CARRIER - 336096\n" +
//                "LUXURY_PEOPLE_CARRIER - 734659\n" +
//                "MINIBUS - 287235";
//        assertEquals(expected, output);
    }
}
