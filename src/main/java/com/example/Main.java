package com.example;

public class Main {
    public static void main(String[] args) {
        MeaningfulUseFetcher fetcher = new MeaningfulUseFetcher();
        MeaningfulUseAnalyzer analyzer = new MeaningfulUseAnalyzer(fetcher);
        
        // Call analyzeData for the year 2014
        int year = 2014;
        analyzer.analyzeData(year);
    }
}
