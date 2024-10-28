package com.example;

import com.fasterxml.jackson.databind.JsonNode;

import java.io.IOException;

public class MeaningfulUseAnalyzer {

    private final MeaningfulUseFetcher fetcher;

    public MeaningfulUseAnalyzer(MeaningfulUseFetcher fetcher) {
        this.fetcher = fetcher;
    }

    public void analyzeData() throws IOException {
        JsonNode data = fetcher.fetchData();

        // Perform your data analysis here
        // Example: Print some data
        System.out.println("Data fetched: " + data.toString());
    }
}
