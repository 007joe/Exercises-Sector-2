package com.techelevator;

import com.techelevator.homes.view.UserInterface;

import org.apache.commons.dbcp2.BasicDataSource;

import javax.sql.DataSource;

public class Application {

    private UserInterface ui;

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

    }

    private void run() {

        while (true) {

            String choice = ui.printMenuOptions();

            //TODO: Remove next line. Here to test looping only
            System.out.println("You picked " + choice);

            if (choice.equals("1")) {    // retrieve all homes
                retrieveHomes();
            }
            else if (choice.equals("2")) {    //add a home
                addHome();
            }
            else if (choice.equals("3")) {   //delete a home
                deleteHome();
            }
            else if (choice.equals("4")) {    //search for a home
                searchForHomeByMLSId();
            }
            else if (choice.equals("5")) {    // exit program
                break;
            }
            else {   // not a valid choice. let user know
                ui.printStatusMessage("Not a valid choice");
            }

        }

    }


    private void searchForHomeByMLSId() {

        //get mlsid from the user

        //call dao...

        //send list to ui to print...

    }

    private void retrieveHomes() {

        // call DAO to get list of homes

        // send to ui to print...

    }

    private void addHome() {

        //get home info from user

        //send to DAO to add

        // notify ui that home has been added or send actual home object back with id...

    }

    private void deleteHome() {

        //prompt user to enter mls Id

        //send to DAO to delete

        //confirm home was deleted to user.

    }


}
