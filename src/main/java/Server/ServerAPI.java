package Server;

import Client.APIAccess;
import Client.Main;
import Client.OutputFormatter;
import Client.OutputOption;
import com.google.gson.Gson;

import javax.ws.rs.*;

@Path("/server")
public class ServerAPI {
    private OutputFormatter outputFormatter = new OutputFormatter();
    private Gson gson = new Gson();

    @GET
    //@Path("{pickup}/{dropoff}/{numPassengers}")
    @Path("/taxis")
    @Produces("text/plain")
    public String getTaxiPrices(@QueryParam("pickup") String pickup, @QueryParam("dropoff") String dropoff,
                                @QueryParam("numPassengers") int numPassengers) {

        String[] jsonString = Main.getTaxiPrices(pickup, dropoff);
        OutputOption[] output = outputFormatter.filterResults(jsonString, numPassengers);

        return gson.toJson(output);
    }
}
