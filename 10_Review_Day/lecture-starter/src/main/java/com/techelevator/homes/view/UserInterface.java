package com.techelevator.homes.view;

import com.techelevator.homes.model.Address;
import com.techelevator.homes.model.Home;

import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;

/**
 * Create and display menus, get user input and print any output
 */
public class UserInterface {

    //declaring a scanner for user input later
    private Scanner scanner;


    public UserInterface() {
        //creating/instantiating the scanner
        scanner = new Scanner(System.in);
    }


   public String printMenuOptions() {

        System.out.println("*****************************************");
        System.out.println("******  Vinny's Real Estate Empire  *****");
        System.out.println("*****************************************\n");


        System.out.println("1. Retrieve all Homes");
        System.out.println("2. Add a Home");
        System.out.println("3. Delete a Home");
        System.out.println("4. Search for a Home (By MLS ID)");
        System.out.println("5. Exit \n");

        System.out.println("Please select a choice from the above menu");
        String choice = scanner.nextLine();
        return choice;

    }

    public String askUserForMLSID() {
        System.out.println("Please enter a valid MLS ID: ");
        return scanner.nextLine();
    }

    public void printHomes(List<Home> homes) {

        for (Home home : homes) {
            printHome(home);
        }

    }


    /**
     * This method prints out a single home.  We 'could' have put this up in the for each loop above, but breaking
     * it out on it's own keeps the code above cleaner AND we can also reuse when printing out the home from menu option 2
     *
     * @param home
     */
    public void printHome(Home home) {


        System.out.println("\nMLS Number: " + home.getMlsNumber());

        if (home.getAddress() != null) {
            System.out.println("Street Address: "
                    + home.getAddress().getAddressLine() + " "
                    + home.getAddress().getCity() + " "
                    + home.getAddress().getState() + " "
                    + home.getAddress().getZip() + "\n");
        }

        System.out.println(String.format("%-25s %s", "Bedrooms: ", home.getNumberOfBedrooms()));
        System.out.println(String.format("%-25s %s", "Bathrooms: ", home.getNumberOfBathrooms()));
        System.out.println(String.format("%-25s $%s", "Price: ", String.format("%.2f", home.getPrice())));
        System.out.println("\nDescription: " + home.getShortDescription());

    }

    public void printStatusMessage(String message) {
        System.out.println(message);
    }


    public Home getHomeInfo() {

        Home home = new Home();

        System.out.println("Enter the MLS Number this house is being listed under");
        String mls = scanner.nextLine();
        home.setMlsNumber(mls);

        // call private method below to get the address from user
        Address address = getAddressInfo();
        home.setAddress(address);

        System.out.println("Enter the number of Bedrooms: ");
        String numberOfBedrooms = scanner.nextLine();
        home.setNumberOfBedrooms(Double.parseDouble(numberOfBedrooms));

        System.out.println("Enter the number of Bathrooms: ");
        String numberOfBathrooms = scanner.nextLine();
        home.setNumberOfBathrooms(Double.parseDouble(numberOfBathrooms));

        System.out.println("Enter a short description of the property: ");
        home.setShortDescription(scanner.nextLine());

        System.out.println("What is the listing price of this property?");
        String priceAsString = scanner.nextLine();
        // home.setPrice(Double.parseDouble(priceAsString));
        home.setPrice(new BigDecimal(priceAsString));

        return home;

    }

    private Address getAddressInfo() {
        Address address = new Address();

        System.out.println("\nEnter address Line 1: ");
        address.setAddressLine(scanner.nextLine());

        System.out.println("\nEnter the city name: ");
        address.setCity(scanner.nextLine());

        System.out.println("\nEnter the state: ");
        address.setState(scanner.nextLine());

        System.out.println("\nEnter the zip code: ");
        String zip = scanner.nextLine();
        address.setZip(Integer.parseInt(zip));

        return address;
    }



}
