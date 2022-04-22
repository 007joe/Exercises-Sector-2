package com.techelevator.model;

public class City {

    private long cityId;
    private String cityName;
    private String stateAbbreviation;
    private long population;
    private double area;

    public City() {

    }

    public City(long cityId, String cityName, String stateAbbreviation, long population, double area) {
        this.cityId = cityId;
        this.cityName = cityName;
        this.stateAbbreviation = stateAbbreviation;
        this.population = population;
        this.area = area;
    }

    public long getCityId() {
        return cityId;
    }

    public void setCityId(long cityId) {
        this.cityId = cityId;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getStateAbbreviation() {
        return stateAbbreviation;
    }

    public void setStateAbbreviation(String stateAbbreviation) {
        this.stateAbbreviation = stateAbbreviation;
    }

    public long getPopulation() {
        return population;
    }

    public void setPopulation(long population) {
        this.population = population;
    }

    public double getArea() {
        return area;
    }

    public void setArea(double area) {
        this.area = area;
    }

    @Override
    public String toString() {
        return String.format("%s, %s (ID: %d)", getCityName(), getStateAbbreviation(), getCityId());
    }
}
