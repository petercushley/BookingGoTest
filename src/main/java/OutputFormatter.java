import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class OutputFormatter {
    private Gson gson = new Gson();
    private HashMap<String, Integer> capacities;

    public String formatForOutput(String[] jsonResponses, int numPassengers) {
        ArrayList<OutputOption> allOptions = new ArrayList<>();
        for (int i = 0; i < jsonResponses.length; i++) {
            OutputOption[] capacityOptions = filterResultsByCapacity(getOptionsFromJson(jsonResponses[i]), numPassengers);
            allOptions.addAll(Arrays.asList(capacityOptions));
        }

        OutputOption[] toPriceFilter = new OutputOption[allOptions.size()];
        allOptions.toArray(toPriceFilter);

        OutputOption[] finalOutput = filterResultsByPrice(toPriceFilter);

        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < finalOutput.length; i++) {
            builder.append(finalOutput[i].car_type)
                    .append(" - ")
                    .append(finalOutput[i].supplier)
                    .append(" - ")
                    .append(finalOutput[i].price)
                    .append("\n");
        }

        return builder.toString();
    }

    private OutputOption[] getOptionsFromJson(String json) {
        //TODO rewrite
        SupplierResponse response = null;
        try {
            //System.out.println(json);
            response = gson.fromJson(json, SupplierResponse.class);
        } catch (IllegalStateException e) {
            //500 internal server error
            //HTML returned not JSON
            System.out.println(json);
        }
        Option[] options = response.options;
        OutputOption[] output = new OutputOption[options.length];
        for (int i = 0; i < options.length; i++) {
            output[i] = new OutputOption(options[i].car_type, options[i].price, response.supplier_id);
        }
        return output;
    }

    private OutputOption[] filterResultsByCapacity(OutputOption[] options, int numPassengers) {
        ArrayList<OutputOption> output = new ArrayList<>();

        for (int i = 0; i < options.length; i++) {
            if (numPassengers <= capacities.get(options[i].car_type)) { //returns all options that can fit the number of passengers
                output.add(options[i]);
            }
        }
        OutputOption[] returnArray = new OutputOption[output.size()];
        return output.toArray(returnArray);
    }

    private OutputOption[] filterResultsByPrice(OutputOption[] options) {
        ArrayList<OutputOption> output = new ArrayList<>();
        for (int i = 0; i < options.length; i++) {
            boolean added = false;
            for (int j = 0; j < output.size(); j++) {
                if (output.get(j).car_type.equals(options[i].car_type)) {
                    if (options[i].price < output.get(j).price) {
                        output.set(j, options[i]);
                    }
                    added = true;
                    break;
                }
            }
            if (!added)
                output.add(options[i]);
        }

        OutputOption[] returnArray = new OutputOption[output.size()];
        return output.toArray(returnArray);
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
