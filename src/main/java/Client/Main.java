package Client;

import Server.ServerAPI;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

import javax.ws.rs.core.Response;
import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;

public class Main {
    private static ArrayList<String> jsons = new ArrayList<>();
    private static final String BASE_URI = "http://localhost:8080/myapp/";
    public static void main(String[] args) throws IOException {
        if (args.length < 3) {
            if (args.length==1) {
                if (args[0].equals("server")) {
                    final HttpServer server = startServer();
                    System.out.println(String.format("Jersey app started with WADL available at "
                            + "%sapplication.wadl\nHit enter to stop it...", BASE_URI));
                    System.in.read();
                    server.stop();
                }
            } else {
                System.out.println("Please enter your pickup location, dropoff location and the number of passengers.");
            }
        } else {
            String pickup = args[0];
            String dropoff = args[1];
            int numPassengers = Integer.parseInt(args[2]);

            OutputFormatter outputFormatter = new OutputFormatter();

            System.out.println(outputFormatter.formatForOutput(getTaxiPrices(pickup, dropoff), numPassengers));
        }
    }

    public static String[] getTaxiPrices(String pickup, String dropoff) {
        APIAccess apiAccess = new APIAccess();

        getJsonFromResponse(apiAccess.getTaxisFromSupplier(pickup, dropoff, "dave"));
        getJsonFromResponse(apiAccess.getTaxisFromSupplier(pickup, dropoff, "eric"));
        getJsonFromResponse(apiAccess.getTaxisFromSupplier(pickup, dropoff, "jeff"));

        String[] jsonArray = new String[jsons.size()];
        return jsons.toArray(jsonArray);
    }

    public static void getJsonFromResponse(Response response) {
        if (response.getStatus() == 200) {
             jsons.add(response.readEntity(String.class));
        }
    }

    private static HttpServer startServer() {
        // create a resource config that scans for JAX-RS resources and providers
        final ResourceConfig rc = new ResourceConfig(ServerAPI.class);

        // create and start a new instance of grizzly http server
        // exposing the Jersey application at BASE_URI
        return GrizzlyHttpServerFactory.createHttpServer(URI.create(BASE_URI), rc);
    }
}
