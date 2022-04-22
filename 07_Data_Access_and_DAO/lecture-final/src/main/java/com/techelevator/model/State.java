package com.techelevator.model;

public class State {

    private String stateAbbreviation;
    private String stateName;
    private long population;
    private long area;
    private int capital_id;
    private String capitalName;
    private double stateSalesTax;
    private String stateNickName;
    private String censusRegion;



    public String getStateAbbreviation() {
        return stateAbbreviation;
    }

    public void setStateAbbreviation(String stateAbbreviation) {
        this.stateAbbreviation = stateAbbreviation;
    }

    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }

    public long getPopulation() {
        return population;
    }

    public void setPopulation(long population) {
        this.population = population;
    }

    public long getArea() {
        return area;
    }

    public void setArea(long area) {
        this.area = area;
    }

    public int getCapital_id() {
        return capital_id;
    }

    public void setCapital_id(int capital_id) {
        this.capital_id = capital_id;
    }

    public String getCapitalName() {
        return capitalName;
    }

    public void setCapitalName(String capitalName) {
        this.capitalName = capitalName;
    }

    public double getStateSalesTax() {
        return stateSalesTax;
    }

    public void setStateSalesTax(double stateSalesTax) {
        this.stateSalesTax = stateSalesTax;
    }

    public String getStateNickName() {
        return stateNickName;
    }

    public void setStateNickName(String stateNickName) {
        this.stateNickName = stateNickName;
    }

    public String getCensusRegion() {
        return censusRegion;
    }

    public void setCensusRegion(String censusRegion) {
        this.censusRegion = censusRegion;
    }

    public String toString() {
        return stateName + "(" + stateAbbreviation + "),"
                   + "\npopulation: " + population + ", area: " + area
                   + "\ncapital: " + capitalName + ", census region: " + censusRegion
                   + "\nstate nickname: " + stateNickName + ", state sales tax: " + stateSalesTax;
    }
}
