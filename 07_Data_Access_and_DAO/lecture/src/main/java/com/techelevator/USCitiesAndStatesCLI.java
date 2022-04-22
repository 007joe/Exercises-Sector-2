package com.techelevator;

import com.techelevator.view.UserInterface;
import org.apache.commons.dbcp2.BasicDataSource;

import javax.sql.DataSource;

public class USCitiesAndStatesCLI {

    //user interface class
    private UserInterface ui;

    //data access interfaces defined here



    public static void main(String[] args) {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setUrl("jdbc:postgresql://localhost:5432/UnitedStates");
        dataSource.setUsername("postgres");
        dataSource.setPassword("postgres1");

        USCitiesAndStatesCLI application = new USCitiesAndStatesCLI(dataSource);
        application.run();
    }

    public USCitiesAndStatesCLI(DataSource dataSource) {
        ui = new UserInterface();

        //instantiate daos here...
    }

    private void run() {

        boolean running = true;

        while (running) {
            String choice = ui.displayMenu();

            if (choice.equals("1")) {
                // Retrieve city info by city name and state abbreviation (Retrieve a single object)
                retrieveCityInfo();
            }
            else if (choice.equals("2")) {
                // Retrieve a list of all cities for a state or territory (Retrieve a list of objects)
                retrieveCityList();
            }
            else if (choice.equals("3")) {
                // Retrieve state information by state abbreviation (retrieve a state using a JOIN to get capital name)
                retrieveStateInfo();
            }
            else if (choice.equals("4")) {
              //  Add a new city to a state or territory
                addNewCity();
            }
            else if (choice.equals("5")) {
                //   Update an existing city
                updateCity();
            }
            else if (choice.equals("6")) {
                //   Update an existing city
                deleteCity();
            }
            else if (choice.equals("7")) {
                running = false;
            }
            else {
                ui.displayMessage("Invalid option selected.");
            }
        }

        System.out.println("Goodbye!");
    }

    /**
     * This method should handle a user's request to fetch all the information about a city in a selected state
     * 1. Prompt user for a city name and state
     * 2. Call the city DAO
     * 3. Pass city object to the UI to display
     */
    private void retrieveCityInfo() {

        System.out.println("retrieveCityInfo...");
    }

    /**
     * This method should handle a user's request to fetch a list of cities for a selected state/territory
     * 1. Prompt the user for a state/territory abbreviation
     * 2. Call the city DAO
     * 3. Pass the List<City> objects to the user interface to display
     */
    private void retrieveCityList() {
        System.out.println("retrieveCityList...");
    }

    /**
     * This method should handle a user's request to fetch information about a particular state
     * 1. Prompt the user for a state/territory abbreviation
     * 2. Call the state DAO
     * 3. Pass the State object to the user interface to display
     */
    private void retrieveStateInfo() {
        System.out.println("retrieveStateInfo...");
    }

    /**
     * This method should handle a user's request to add new city information to the database
     * 1. Prompt the user to enter information about the new city
     * 2. Pass the city information to the city DAO to add...
     * 3. Return the city information (with new city id) back to the user to display
     */
    private void addNewCity() {
        System.out.println("addNewCity...");
    }

    /**
     * This method should handle a user's request to update an existing city's information in the database
     * 1. Prompt the user to enter information about the city
     * 2. Pass the updated city information to the city DAO to update..
     * 3. Return the city information (with new updated info) back to the user to display
     */
    private void updateCity() {
        System.out.println("updateCity...");

    }

    /**
     * This method should handle a user's request to delete an existing city from the database
     * 1. Prompt the user to enter the city id they want to delete
     * 2. Call the DAO to delete the city
     * 3. Inform the user that the city has been deleted
     */
    private void deleteCity() {
        System.out.println("deleteCity...");
    }

}
