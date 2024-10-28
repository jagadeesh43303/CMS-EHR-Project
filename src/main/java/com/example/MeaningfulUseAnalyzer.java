package com.example;

import java.io.IOException;
import java.util.List;

public class MeaningfulUseAnalyzer {
    private final MeaningfulUseFetcher fetcher;

    public MeaningfulUseAnalyzer(MeaningfulUseFetcher fetcher) {
        this.fetcher = fetcher;
    }

    public void analyzeData(int year) {
        try {
            List<DataRecord> records = fetcher.fetchData(year);
            int totalHospitals = records.size();
            int eligibleHospitals = 0;

            for (DataRecord record : records) {
                if (record.isMeaningfulUse()) {
                    eligibleHospitals++;
                }
            }

            if (totalHospitals > 0) {
                double percentage = (eligibleHospitals / (double) totalHospitals) * 100;
                System.out.printf("In %d, %.2f%% of hospitals demonstrated Meaningful Use.%n", year, percentage);
            } else {
                System.out.println("No records to analyze for the year: " + year);
            }
        } catch (IOException e) {
            System.err.println("Error fetching data: " + e.getMessage());
        }
    }
}
