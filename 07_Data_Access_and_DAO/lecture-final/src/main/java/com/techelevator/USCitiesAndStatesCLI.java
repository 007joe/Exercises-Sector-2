package com.techelevator;

import com.techelevator.dao.*;
import com.techelevator.model.*;
import com.techelevator.view.UserInterface;
import org.apache.commons.dbcp2.BasicDataSource;

import javax.sql.DataSource;
import java.util.List;

public class USCitiesAndStatesCLI {

    //user interface class
    private UserInterface ui;

    //data access interfaces
    private StateDao stateDao;
    private CityDao cityDao;



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
        stateDao = new JdbcStateDao(dataSource);
        cityDao = new JdbcCityDao(dataSource);
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

        //todo
        String cityName = ui.promptForCityName();
        String state = ui.promptForStateAbbreviation();

        City city = cityDao.getCityByNameAndState(cityName, state);
        ui.displayCity(city);


    }

    /**
     * This method should handle a user's request to fetch a list of cities for a selected state/territory
     * 1. Prompt the user for a state/territory abbreviation
     * 2. Call the city DAO
     * 3. Pass the List<City> objects to the user interface to display
     */
    private void retrieveCityList() {

        //todo
        String state = ui.promptForStateAbbreviation();
        List<City> cities = cityDao.getCityListByStateAbbreviation(state);
        ui.displayCityList(cities);

    }

    /**
     * This method should handle a user's request to fetch information about a particular state
     * 1. Prompt the user for a state/territory abbreviation
     * 2. Call the state DAO
     * 3. Pass the State object to the user interface to display
     */
    private void retrieveStateInfo() {

        //todo
        String stateAbbreviation = ui.promptForStateAbbreviation();
        State state = stateDao.getStateInformation(stateAbbreviation);
        ui.displayState(state);

    }

    /**
     * This method should handle a user's request to add new city information to the database
     * 1. Prompt the user to enter information about the new city
     * 2. Pass the city information to the city DAO to add...
     * 3. Return the city information (with new city id) back to the user to display
     */
    private void addNewCity() {
        City cityToAdd = ui.promptForAddCityInformation();
        cityToAdd = cityDao.createCity(cityToAdd);
        ui.displayMessage(cityToAdd.getCityName() + " (id: " + cityToAdd.getCityId() + ") has been added");
        ui.displayCity(cityToAdd);
    }

    /**
     * This method should handle a user's request to update an existing city's information in the database
     * 1. Prompt the user to enter information about the city
     * 2. Pass the updated city information to the city DAO to update..
     * 3. Return the city information (with new updated info) back to the user to display
     */
    private void updateCity() {

        // first lets ask the user what city to update
        String cityName = ui.promptForCityName();
        String state = ui.promptForStateAbbreviation();

        //next lets retrieve the current city data
        City cityToUpdate = cityDao.getCityByNameAndState(cityName, state);

        //let's pass the city we found to show the user so they can see what they would like to change
        cityToUpdate = ui.promptForUpdateCityInformation(cityToUpdate);

        //now pass the updated city object to the DAO to update in the database
        cityDao.updateCity(cityToUpdate);

        //now just for giggles, let's re-select the same city from the database to just make sure (unnecessary code, but sure, why not)
        City myUpdatedCity = cityDao.getCityByNameAndState(cityToUpdate.getCityName(), cityToUpdate.getStateAbbreviation());

        //let's print to verify
        ui.displayMessage(myUpdatedCity.getCityName() + " (id: " + myUpdatedCity.getCityId() + ") has been updated");
        ui.displayCity(myUpdatedCity);

    }

    /**
     * This method should handle a user's request to delete an existing city from the database
     * 1. Prompt the user to enter the city id they want to delete
     * 2. Call the DAO to delete the city
     * 3. Inform the user that the city has been deleted
     */
    private void deleteCity() {
        // first lets ask the user what city to delete
        String cityName = ui.promptForCityName();
        String state = ui.promptForStateAbbreviation();

        //next lets retrieve the current city data to verify with user we have the correct city
        City cityToDelete= cityDao.getCityByNameAndState(cityName, state);

        long cityIdToDelete = ui.promptForDeleteConfirmation(cityToDelete);
        if (cityIdToDelete != -1) {
            cityDao.deleteCity(cityIdToDelete);
        }
        //we could modify the dao's delete method to return a boolean or we could just reselect it.

        cityToDelete = cityDao.getCityByNameAndState(cityName, state);
        if (cityToDelete == null) {
            ui.displayMessage("City: " + cityName + " (state) has been deleted" );
        }
        else {
            ui.displayMessage("City: " + cityName + " (state) could not be deleted. Probably would be better off hiring a C# developer.. lol just kidding!" );
        }



    }

}
