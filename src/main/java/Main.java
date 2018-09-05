import javax.ws.rs.core.Response;

public class Main {
    public static void main(String[] args) {
        //TODO handle bad response and timeout
        if (args.length < 3) {
            System.out.println("Please enter your pickup location, dropoff location and the number of passengers.");
        } else {
            String pickup = args[0];
            String dropoff = args[1];
            int numPassengers = Integer.parseInt(args[2]);

            APIAccess apiAccess = new APIAccess();
            OutputFormatter outputFormatter = new OutputFormatter();

            Response daveResponse = apiAccess.getTaxisFromSupplier(pickup, dropoff, "dave");

            Response ericResponse = apiAccess.getTaxisFromSupplier(pickup, dropoff, "eric");
            Response jeffResponse = apiAccess.getTaxisFromSupplier(pickup, dropoff, "jeff");


        }
    }

    public String getJsonFromResponse(Response response) {
        if (response.getStatus() == 200) {
            return response.readEntity(String.class);
        }
        return null;
    }
}
