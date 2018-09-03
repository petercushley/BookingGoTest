import com.google.gson.Gson;

public class OutputFormatter {
    private Gson gson = new Gson();

    public String formatForOutput(String jsonResponse, int numPassengers) {
        Option[] options = getOptionsFromJson(jsonResponse);

        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < options.length; i++) {
            String type = options[i].car_type;
            String price = Long.toString(options[i].price);

            stringBuilder.append(type).append(" - ").append(price).append("\n");
        }

        return stringBuilder.toString();
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
}
