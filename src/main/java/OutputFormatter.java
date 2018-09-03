import com.google.gson.Gson;

import java.util.HashMap;

public class OutputFormatter {
    private Gson gson = new Gson();
    private HashMap<String, Integer> capacities;

    public String formatForOutput(String jsonResponse, int numPassengers) {
        Option[] options = getOptionsFromJson(jsonResponse);

        return filterResults(options, numPassengers);
    }

    private Option[] getOptionsFromJson(String json) {
        //TODO rewrite
        SupplierResponse response = null;
        try {
            System.out.println(json);
            response = gson.fromJson(json, SupplierResponse.class);
        } catch (IllegalStateException e) {
            System.out.println(json);
        }
        return response.options;
    }

    public String filterResults(Option[] options, int numPassengers) {
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < options.length; i++) {
            String type = options[i].car_type;
            String price = Long.toString(options[i].price);

            if (numPassengers <= capacities.get(type)) { //returns all options that can fit the number of passengers
                stringBuilder.append(type).append(" - ").append(price).append("\n");
            }
        }
        return stringBuilder.toString();
    }

    public OutputFormatter() {
        this.capacities = new HashMap<>();
        capacities.put("STANDARD", 4);
        capacities.put("EXECUTIVE", 4);
        capacities.put("LUXURY", 4);
        capacities.put("PEOPLE_CARRIER", 6);
        capacities.put("LUXURY_PEOPLE_CARRIER", 6);
        capacities.put("MINIBUS", 16);
    }
}
