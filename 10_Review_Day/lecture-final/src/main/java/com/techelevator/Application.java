package com.techelevator;

import com.techelevator.homes.dao.HomeDAO;
import com.techelevator.homes.dao.JdbcHomeDao;
import com.techelevator.homes.dao.MockHomeDAO;
import com.techelevator.homes.exception.HomeNotFoundException;
import com.techelevator.homes.model.Home;
import com.techelevator.homes.view.UserInterface;

import org.apache.commons.dbcp2.BasicDataSource;

import javax.sql.DataSource;
import java.util.List;

public class Application {

    private UserInterface ui;
    private HomeDAO homeDAO;

    private final String GET_LIST_OF_HOMES = "1";
    private final String ADD_HOME = "2";
    private final String DELETE_HOME = "3";
    private final String SEARCH_FOR_HOME_BY_MLS = "4";
    private final String EXIT_PROGRAM = "5";



    /**
     * The main entry point in the application
     */
    public static void main(String[] args) {

        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setUrl("jdbc:postgresql://localhost:5432/jghomes");
        dataSource.setUsername("postgres");
        dataSource.setPassword("postgres1");

        Application app = new Application(dataSource);
        app.run();

    }

    public Application(DataSource dataSource) {
        ui = new UserInterface();

        //add DAOs here ...
        homeDAO = new JdbcHomeDao(dataSource);

    }

    private void run() {

        while (true) {

            String choice = ui.printMenuOptions();

            //TODO: Remove next line. Here to test looping only
            System.out.println("You picked " + choice);

            if (choice.equals(GET_LIST_OF_HOMES)) {    // retrieve all homes
                retrieveHomes();
            }
            else if (choice.equals(ADD_HOME)) {    //add a home
                addHome();
            }
            else if (choice.equals(DELETE_HOME)) {   //delete a home
                deleteHome();
            }
            else if (choice.equals(SEARCH_FOR_HOME_BY_MLS)) {    //search for a home
                searchForHomeByMLSId();
            }
            else if (choice.equals(EXIT_PROGRAM)) {    // exit program
                break;
            }
            else {   // not a valid choice. let user know
                ui.printStatusMessage("Not a valid choice");
            }

        }

    }


    private void searchForHomeByMLSId() {

        //get mlsid from the user
        String mls = ui.askUserForMLSID();

        //call dao...
        try {
            Home home = homeDAO.retrieveHomeByMLSId(mls);
            ui.printHome(home);
        } catch (HomeNotFoundException e) {
            ui.printStatusMessage("Home " + mls + " was not found.");
        }



    }

    private void retrieveHomes() {

        // call DAO to get list of homes
        List<Home> homes = homeDAO.retrieveHomesForSale();

        // send list to ui to print...
        ui.printHomes(homes);


        //ui.printHomes(homeDAO.retrieveHomesForSale());

    }

    private void addHome() {

        //get home info from user
        Home home = ui.getHomeInfo();

        //send to DAO to add
        home = homeDAO.addHome(home);

        // notify ui that home has been added or send actual home object back with id...
        ui.printStatusMessage("Home " + home.getMlsNumber() + " has been added with a confirmation id: " + home.getHomeId());
        ui.printHome(home);

    }

    private void deleteHome() {

        //prompt user to enter mls Id
        String mlsId = ui.askUserForMLSID();

        //send to DAO to delete
        try {
            homeDAO.deleteHome(mlsId);

            //confirm home was deleted to user.
            ui.printStatusMessage("Home " + mlsId + " was deleted");


        } catch (HomeNotFoundException e) {
            ui.printStatusMessage("Home " + mlsId + " was not found.");
        }



    }


}
