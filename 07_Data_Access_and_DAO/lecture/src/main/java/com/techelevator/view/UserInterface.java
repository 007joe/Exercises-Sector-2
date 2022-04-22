package com.techelevator.view;

import com.techelevator.model.City;
import com.techelevator.model.State;

import java.util.List;
import java.util.Scanner;

public class UserInterface {

    private final Scanner scanner;

    public UserInterface() {
        scanner = new Scanner(System.in);
    }


    public String displayMenu() {
        System.out.println("----------------------------------------------------");
        System.out.println("|  US States, Territories and City Data Management  |");
        System.out.println("----------------------------------------------------\n");
        System.out.println("1. Search for city by city name and state/territory");
        System.out.println("2. Retrieve list of cities for state/territory");
        System.out.println("3. Retrieve state information by state abbreviation");
        System.out.println("4. Add a new city");
        System.out.println("5. Update an existing city");
        System.out.println("6. Delete a city");
        System.out.println("7. Exit\n");
        return scanner.nextLine();
    }

    public void displayMessage(String message) {
        System.out.println();
        System.out.println("***" + message + "***");
        System.out.println();
    }

    public String promptForCityName() {
        System.out.println("\nPlease enter a city name: ");
        return scanner.nextLine();
    }

    public String promptForStateAbbreviation() {
        System.out.println("\nPlease enter a two character state abbreviation code: ");
        return scanner.nextLine();
    }



    public City promptForAddCityInformation() {

        City city = new City();
        System.out.println("Enter the city name you would like to add: ");
        city.setCityName(scanner.nextLine());

        System.out.println("Enter the 2 digit state code for this city: ");
        city.setStateAbbreviation(scanner.nextLine());

        System.out.println("Enter the city population: ");
        city.setPopulation(Long.parseLong(scanner.nextLine()));

        System.out.println("Enter the city area in square km: ");
        city.setArea(Double.parseDouble(scanner.nextLine()));

        return city;

    }

    public City promptForUpdateCityInformation(City city) {

        //let's display the current city information first
        displayCity(city);
        String choice = null;

        //city name
        System.out.println("Would you like to update the city name? (Y/N)");
        choice = scanner.nextLine();
        if (choice.equalsIgnoreCase("Y") || choice.equalsIgnoreCase("YES")) {
            System.out.println("Please enter the updated city name: ");
            city.setCityName(scanner.nextLine());
        }

        //state code
        System.out.println("Would you like to update the state code abbreviation? (Y/N)");
        choice = scanner.nextLine();
        if (choice.equalsIgnoreCase("Y") || choice.equalsIgnoreCase("YES")) {
            System.out.println("Please enter the updated two digit state code: ");
            city.setStateAbbreviation(scanner.nextLine());
        }

        //population
        System.out.println("Would you like to update the city's population? (Y/N)");
        choice = scanner.nextLine();
        if (choice.equalsIgnoreCase("Y") || choice.equalsIgnoreCase("YES")) {
            System.out.println("Please enter the updated city population: ");
            city.setPopulation(Long.parseLong(scanner.nextLine()));
        }

        //area
        System.out.println("Would you like to update the city's area (square kms)? (Y/N)");
        choice = scanner.nextLine();
        if (choice.equalsIgnoreCase("Y") || choice.equalsIgnoreCase("YES")) {
            System.out.println("Please enter the updated city area in square kms: ");
            city.setArea(Double.parseDouble(scanner.nextLine()));
        }

        return city;

    }


    public long promptForDeleteConfirmation(City city) {

        //let's display the current city information first
        displayCity(city);
        String choice = null;
        long cityId = -1;

        //city name
        System.out.println("Is this the city you would like to delete? (Y/N)");
        choice = scanner.nextLine();
        if (choice.equalsIgnoreCase("Y") || choice.equalsIgnoreCase("YES")) {
            return city.getCityId();
        }
        return cityId;    // if we get here we want to return -1

    }


    public void displayCityList(List<City> cityList) {

        for (City city: cityList) {
            displayCity(city);
        }

    }

    public void displayCity(City city) {
        if (city != null) {
            System.out.println(city);
            System.out.format("Population: %d Area: %.1f sq. km\n\n", city.getPopulation(), city.getArea());
        }
        else {
            System.out.println("City info unavailable");
        }

    }

    public void displayState(State state) {
        System.out.println(state.toString());
    }





}
