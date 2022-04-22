package com.techelevator.jghomes.models;

import java.math.BigDecimal;

public class Home {

    /**
     * These are member variables. Each home instance in memory will have it's own
     * copy of this data. All instance variable should be private
     */

    private String mlsNumber;
    private Address address;
    private double numberOfBedrooms;
    private double numberOfBathrooms;
    private BigDecimal price;
    private String shortDescription;



    /*
     * The following are getters/setters. I removed the setter for mlsNUmber and Address as they are fixed values only created
     * at time of listing.
     */

    public void setMlsNumber(String mlsNumber) {
        this.mlsNumber = mlsNumber;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getMlsNumber() {
        return mlsNumber;
    }

    public Address getAddress() {
        return address;
    }

    public double getNumberOfBedrooms() {
        return numberOfBedrooms;
    }

    public void setNumberOfBedrooms(double numberOfBedrooms) {
        this.numberOfBedrooms = numberOfBedrooms;
    }

    public double getNumberOfBathrooms() {
        return numberOfBathrooms;
    }

    public void setNumberOfBathrooms(double numberOfBathrooms) {
        this.numberOfBathrooms = numberOfBathrooms;
    }



    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }



}
