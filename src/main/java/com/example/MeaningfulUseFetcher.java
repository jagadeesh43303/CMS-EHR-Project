package com.example;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MeaningfulUseFetcher {

    // Use a constant URL for the API endpoint
    private final String url = "https://www.healthit.gov/data/open-api?source=Meaningful-Use-Acceleration-Scorecard.csv"; // Replace with your actual API URL
    private static final int PAGE_SIZE = 10; // Number of entries per page

    public JsonNode fetchData() throws IOException {
        // Fetch data from the API
        try (CloseableHttpClient client = HttpClients.createDefault()) {
            HttpGet request = new HttpGet(url);
            System.out.println("Sending request to: " + url); // Log the URL being requested
            
            HttpResponse response = client.execute(request);
            System.out.println("Response Code: " + response.getStatusLine().getStatusCode()); // Log the response status

            // Read the response content
            String responseBody = EntityUtils.toString(response.getEntity());
            System.out.println("Response Body: " + responseBody); // Log the response body
            
            // Check for successful response
            if (response.getStatusLine().getStatusCode() != 200) {
                throw new IOException("Failed to fetch data: " + response.getStatusLine().getReasonPhrase());
            }

            // Parse JSON response
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readTree(responseBody);
        } catch (IOException e) {
            System.err.println("IOException occurred: " + e.getMessage());
            throw e; // Rethrow the exception to be handled in Main
        } catch (Exception e) {
            System.err.println("Unexpected error: " + e.getMessage());
            throw new IOException("Unexpected error occurred", e); // Wrap the exception
        }
    }

    public void processAndDisplayData(JsonNode data) throws IOException {
        // Check if the data is not null
        if (data != null && data.isArray()) {
            int totalEntries = data.size();
            int currentPage = 0;

            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            boolean continueDisplaying = true;

            while (continueDisplaying) {
                int start = currentPage * PAGE_SIZE;
                int end = Math.min(start + PAGE_SIZE, totalEntries);

                // Display entries for the current page
                for (int i = start; i < end; i++) {
                    JsonNode entry = data.get(i);
                    System.out.println("----------------------------------------------------");
                    System.out.println("Region: " + entry.get("region").asText());
                    System.out.println("Region Code: " + entry.get("region_code").asText());
                    System.out.println("Period: " + entry.get("period").asText());
                    System.out.println("Percentage of MDs, PAs, NPs using MU (AIU): " + entry.get("pct_md_pa_np_mu_aiu").asText());
                    System.out.println("Percentage of MDs, PAs, NPs using MU: " + entry.get("pct_md_pa_np_mu").asText());
                    System.out.println("Percentage of MDs using AIU: " + entry.get("pct_md_mu_aiu").asText());
                    System.out.println("Percentage of MDs using MU: " + entry.get("pct_md_mu").asText());
                    System.out.println("Percentage of NPs using AIU: " + entry.get("pct_np_mu_aiu").asText());
                    System.out.println("Percentage of NPs using MU: " + entry.get("pct_np_mu").asText());
                    System.out.println("Percentage of PAs using AIU: " + entry.get("pct_pa_mu_aiu").asText());
                    System.out.println("Percentage of PAs using MU: " + entry.get("pct_pa_mu").asText());
                    System.out.println("Percentage of Hospitals using AIU: " + entry.get("pct_hospitals_mu_aiu").asText());
                    System.out.println("Percentage of Hospitals using MU: " + entry.get("pct_hospitals_mu").asText());
                    System.out.println("Percentage of CAHs in Small Rural Areas using AIU: " + entry.get("pct_cah_small_rural_mu_aiu").asText());
                    System.out.println("Percentage of CAHs in Small Rural Areas using MU: " + entry.get("pct_cah_small_rural_mu").asText());
                }

                // Check if more data is available
                if (end < totalEntries) {
                    System.out.println("\nDisplayed " + PAGE_SIZE + " entries. Type 'next' for more or 'exit' to quit.");
                    String input = reader.readLine();
                    if (input.equalsIgnoreCase("next")) {
                        currentPage++;
                    } else {
                        continueDisplaying = false; // Exit the loop
                    }
                } else {
                    System.out.println("No more entries to display.");
                    continueDisplaying = false; // Exit the loop
                }
            }
        } else {
            System.out.println("No data available to display.");
        }
    }

    public static void main(String[] args) {
        MeaningfulUseFetcher fetcher = new MeaningfulUseFetcher();
        try {
            JsonNode data = fetcher.fetchData(); // Fetch data from the API
            fetcher.processAndDisplayData(data); // Process and display the data
        } catch (IOException e) {
            System.err.println("Error fetching data: " + e.getMessage());
        }
    }
}
