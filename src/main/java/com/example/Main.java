package com.example;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        try {
            MeaningfulUseFetcher fetcher = new MeaningfulUseFetcher();
            MeaningfulUseAnalyzer analyzer = new MeaningfulUseAnalyzer(fetcher);
            analyzer.analyzeData();
        } catch (IOException e) {
            System.err.println("Error fetching data: " + e.getMessage());
        }
    }
}
