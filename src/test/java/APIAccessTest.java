import org.junit.Test;

import javax.ws.rs.core.Response;

import static org.junit.Assert.assertEquals;

public class APIAccessTest {
    private APIAccess apiAccess = new APIAccess();

    @Test
    public void daveHeathrowResponse() {
        String pickup = "51.470020,-0.454295";
        String dropoff = "3.410632,-2.157533";
        String supplier = "dave";

        Response response = apiAccess.getTaxisFromSupplier(pickup, dropoff, supplier);

        System.out.println(response.readEntity(String.class));
        assertEquals(200, response.getStatus());
    }
}
