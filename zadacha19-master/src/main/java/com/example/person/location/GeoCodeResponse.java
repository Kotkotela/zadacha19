package com.example.person.location;

public class GeoCodeResponse {

    private GeoCodeResult[] results;

    public GeoCodeResult[] getResults() {
        return results;
    }

    public void setResults(GeoCodeResult[] results) {
        this.results = results;
    }
}