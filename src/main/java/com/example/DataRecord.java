package com.example;

public class DataRecord {
    private String hospitalName;
    private boolean meaningfulUse;

    // Constructor
    public DataRecord(String hospitalName, boolean meaningfulUse) {
        this.hospitalName = hospitalName;
        this.meaningfulUse = meaningfulUse;
    }

    // Getters and Setters
    public String getHospitalName() {
        return hospitalName;
    }

    public void setHospitalName(String hospitalName) {
        this.hospitalName = hospitalName;
    }

    public boolean isMeaningfulUse() {
        return meaningfulUse;
    }

    public void setMeaningfulUse(boolean meaningfulUse) {
        this.meaningfulUse = meaningfulUse;
    }
}
