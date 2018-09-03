import javax.ws.rs.QueryParam;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class APIAccess {

    private Client client = ClientBuilder.newClient();
    private String targetURI = "https://techtest.rideways.com/";
    private WebTarget target = client.target(targetURI);

    public Response getTaxisFromSupplier(String pickup, String dropoff, String supplier) {
        WebTarget taxiTarget = target.path(supplier + "/")
                .queryParam("pickup", pickup)
                .queryParam("dropoff", dropoff);

        Invocation.Builder invocationBuilder = taxiTarget.request();

        return invocationBuilder.get();
    }
}
