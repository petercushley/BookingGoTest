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

    @Test
    public void allSuppliersResponse() {
        String pickup = "51.470020,-0.454295";
        String dropoff = "3.410632,-2.157533";

        Response daveResponse = apiAccess.getTaxisFromSupplier(pickup, dropoff, "dave");

        System.out.println(daveResponse.readEntity(String.class));
        assertEquals(200, daveResponse.getStatus());

        Response ericResponse = apiAccess.getTaxisFromSupplier(pickup, dropoff, "eric");

        System.out.println(ericResponse.readEntity(String.class));
        assertEquals(200, ericResponse.getStatus());

        Response jeffResponse = apiAccess.getTaxisFromSupplier(pickup, dropoff, "jeff");

        System.out.println(jeffResponse.readEntity(String.class));
        assertEquals(200, jeffResponse.getStatus());
    }
}
