package Client;

import org.glassfish.jersey.client.ClientProperties;

import javax.ws.rs.ProcessingException;
import javax.ws.rs.QueryParam;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.SocketTimeoutException;

public class APIAccess {

    private Client client = ClientBuilder.newClient()
            .property(ClientProperties.CONNECT_TIMEOUT, 2000)
            .property(ClientProperties.READ_TIMEOUT, 2000);
    private String targetURI = "https://techtest.rideways.com/";
    private WebTarget target = client.target(targetURI);

    public Response getTaxisFromSupplier(String pickup, String dropoff, String supplier) {


        WebTarget taxiTarget = target.path(supplier + "/")
                .queryParam("pickup", pickup)
                .queryParam("dropoff", dropoff);

        Invocation.Builder invocationBuilder = taxiTarget.request();

        try {
            //TODO handle timeouts better
            return invocationBuilder.get();
        } catch (ProcessingException e) {
            //time out
            //will send an outbound request so cannot read entity
            return Response.status(Response.Status.REQUEST_TIMEOUT).build();
        }
    }
}
