package CODSOFT;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class CurrencyConverter {

    private static final String API_KEY = "2e66b9e88da37fb0b3945dc1"; // Replace with your API key
    private static final String API_URL = "https://v6.exchangerate-api.com/v6/2e66b9e88da37fb0b3945dc1/latest/USD";

    public static void main(String[] args) {
        try {
            // Step 1: Allow the user to choose the base and target currencies
            String baseCurrency = getUserInput("Enter the base currency code (e.g., USD): ");
            String targetCurrency = getUserInput("Enter the target currency code (e.g., EUR): ");

            // Step 2: Fetch real-time exchange rates from the API
            double exchangeRate = getExchangeRate(baseCurrency, targetCurrency);

            if (exchangeRate == -1) {
                System.out.println("Failed to fetch exchange rates. Exiting.");
                return;
            }

            // Step 3: Take input from the user for the amount they want to convert
            double amountToConvert = getDoubleUserInput("Enter the amount to convert: ");

            // Step 4: Convert the amount from the base to the target currency
            double convertedAmount = convertCurrency(amountToConvert, exchangeRate);

            // Step 5: Display the result to the user
            System.out.printf("%.2f %s is equal to %.2f %s%n",
                    amountToConvert, baseCurrency, convertedAmount, targetCurrency);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String getUserInput(String message) throws IOException {
        System.out.print(message);
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        return reader.readLine().trim().toUpperCase();
    }

    private static double getDoubleUserInput(String message) throws IOException {
        System.out.print(message);
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        return Double.parseDouble(reader.readLine().trim());
    }

    private static double getExchangeRate(String baseCurrency, String targetCurrency) throws IOException {
        String apiUrl = API_URL + baseCurrency + "?apikey=" + API_KEY;

        URL url = new URL(apiUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        int responseCode = connection.getResponseCode();

        if (responseCode == HttpURLConnection.HTTP_OK) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            StringBuilder response = new StringBuilder();

            while ((line = reader.readLine()) != null) {
                response.append(line);
            }

            reader.close();

            // Parse the JSON response to get the exchange rate for the target currency
            String jsonResponse = response.toString();
            String targetRateKey = "\"" + targetCurrency + "\":";
            int targetRateIndex = jsonResponse.indexOf(targetRateKey);

            if (targetRateIndex != -1) {
                int endIndex = jsonResponse.indexOf(",", targetRateIndex);
                String targetRateValue = jsonResponse.substring(targetRateIndex + targetRateKey.length(), endIndex);
                return Double.parseDouble(targetRateValue);
            } else {
                System.out.println("Invalid target currency code. Exiting.");
                return -1;
            }
        } else {
            System.out.println("Failed to fetch exchange rates. Exiting.");
            return -1;
        }
    }

    private static double convertCurrency(double amount, double exchangeRate) {
        return amount * exchangeRate;
    }
}
