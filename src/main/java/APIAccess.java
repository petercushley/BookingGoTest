import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

public class APIAccess {

    private Client client = ClientBuilder.newClient();
    private String targetURI = "https://techtest.rideways.com/";
    private WebTarget target = client.target(targetURI);

    public Response getTaxisFromSupplier(String pickup, String dropoff, String supplier) {
        return null;
    }
}