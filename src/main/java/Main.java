import javax.ws.rs.core.Response;
import java.util.ArrayList;

public class Main {
    private static ArrayList<String> jsons = new ArrayList<>();
    public static void main(String[] args) {
        if (args.length < 3) {
            System.out.println("Please enter your pickup location, dropoff location and the number of passengers.");
        } else {
            String pickup = args[0];
            String dropoff = args[1];
            int numPassengers = Integer.parseInt(args[2]);

            APIAccess apiAccess = new APIAccess();
            OutputFormatter outputFormatter = new OutputFormatter();

            getJsonFromResponse(apiAccess.getTaxisFromSupplier(pickup, dropoff, "dave"));
            getJsonFromResponse(apiAccess.getTaxisFromSupplier(pickup, dropoff, "eric"));
            getJsonFromResponse(apiAccess.getTaxisFromSupplier(pickup, dropoff, "jeff"));

            String[] jsonArray = new String[jsons.size()];
            System.out.println(outputFormatter.formatForOutput(jsons.toArray(jsonArray), numPassengers));
        }
    }

    private static void getJsonFromResponse(Response response) {
        if (response.getStatus() == 200) {
             jsons.add(response.readEntity(String.class));
        }
    }
}
