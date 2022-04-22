package com.techelevator.view;


import com.techelevator.jghomes.models.Address;
import com.techelevator.jghomes.models.Home;
import com.techelevator.jghomes.models.UserCredentials;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.Scanner;

public class ConsoleService {

	private PrintWriter out;
	private Scanner in;

	public ConsoleService(InputStream input, OutputStream output) {
		this.out = new PrintWriter(output, true);
		this.in = new Scanner(input);
	}

	public Object getChoiceFromOptions(Object[] options) {
		Object choice = null;
		while (choice == null) {
			displayMenuOptions(options);
			choice = getChoiceFromUserInput(options);
		}
		out.println();
		return choice;
	}

	private Object getChoiceFromUserInput(Object[] options) {
		Object choice = null;
		String userInput = in.nextLine();
		try {
			int selectedOption = Integer.valueOf(userInput);
			if (selectedOption > 0 && selectedOption <= options.length) {
				choice = options[selectedOption - 1];
			}
		} catch (NumberFormatException e) {
			// eat the exception, an error message will be displayed below since choice will be null
		}
		if (choice == null) {
			out.println(System.lineSeparator() + "*** " + userInput + " is not a valid option ***" + System.lineSeparator());
		}
		return choice;
	}

	private void displayMenuOptions(Object[] options) {
		out.println();
		for (int i = 0; i < options.length; i++) {
			int optionNum = i + 1;
			out.println(optionNum + ") " + options[i]);
		}
		out.print(System.lineSeparator() + "Please choose an option >>> ");
		out.flush();
	}

	public String getUserInput(String prompt) {
		out.print(prompt+": ");
		out.flush();
		return in.nextLine();
	}

	public Integer getUserInputInteger(String prompt) {
		Integer result = null;
		do {
			out.print(prompt+": ");
			out.flush();
			String userInput = in.nextLine();
			try {
				result = Integer.parseInt(userInput);
			} catch(NumberFormatException e) {
				out.println(System.lineSeparator() + "*** " + userInput + " is not valid ***" + System.lineSeparator());
			}
		} while(result == null);
		return result;
	}



	public UserCredentials collectUserCredentials() {
		String username = getUserInput("Username");
		String password = getUserInput("Password");
		return new UserCredentials(username, password);
	}



	public String getMLSNumberFromUser() {

		System.out.println("\nPlease enter a valid MLS number");
		return in.nextLine();

	}

	public String promptUserForMLSNumberToDelete() {

		System.out.println("\nPlease enter a MLS number for the home you wish to delete:");
		return in.nextLine();

	}

	public void printListOfHomes(Home[] homes) {

		System.out.println("\n*********** Listing Results ************\n");

		if (homes.length == 0) {
			System.out.println("No Results Found!");
			return;
		}

		for (Home home : homes) {

			printHome(home);

		}

	}

	public void printHome(Home home) {

		if (home == null) {
			System.out.println("No results found... Please try again.");
			return;
		}

		System.out.println("MLS Number: " + home.getMlsNumber());

		if (home.getAddress() != null) {
			System.out.println(
					"Street Address: " + " " + home.getAddress().getAddressLine() + " " + home.getAddress().getCity()
							+ " " + home.getAddress().getState() + " " + home.getAddress().getZipCode() + "\n");
		}

		System.out.println(String.format("%-25s %s", "Bedrooms: ", home.getNumberOfBedrooms()));
		System.out.println(String.format("%-25s %s", "Bathrooms: ", home.getNumberOfBathrooms()));
		System.out.println(String.format("%-25s %s", "Description: ", home.getShortDescription()));
		System.out.println(String.format("%-25s $%s", "Price: ", String.format("%.2f", home.getPrice())));

		System.out.println("\n*********** *** *** ***  ************\n");

	}

	public Home getHomeInfo() {

		Home home = new Home();

		System.out.println("Enter the MLS Number this house is being listed under");
		String mls = in.nextLine();
		home.setMlsNumber(mls);

		// call private method below to get the address from user
		Address address = getAddressInfo();
		home.setAddress(address);

		System.out.println("Enter the number of Bedrooms: ");
		String numberOfBedrooms = in.nextLine();
		home.setNumberOfBedrooms(Double.parseDouble(numberOfBedrooms));

		System.out.println("Enter the number of Bathrooms: ");
		String numberOfBathrooms = in.nextLine();
		home.setNumberOfBathrooms(Double.parseDouble(numberOfBathrooms));

		System.out.println("Enter a short description of the property: ");
		home.setShortDescription(in.nextLine());

		System.out.println("What is the listing price of this property?");
		String priceAsString = in.nextLine();
		// home.setPrice(Double.parseDouble(priceAsString));
		home.setPrice(new BigDecimal(priceAsString));

		return home;

	}

	public void printMessage(String message) {
		System.out.println(message);
	}

	public Address getAddressInfo() {
		Address address = new Address();

		System.out.println("\nEnter address Line 1: ");
		address.setAddressLine(in.nextLine());

		System.out.println("\nEnter the city name: ");
		address.setCity(in.nextLine());

		System.out.println("\nEnter the state: ");
		address.setState(in.nextLine());

		System.out.println("\nEnter the zip code: ");
		String zip = in.nextLine();
		address.setZipCode(Integer.parseInt(zip));

		return address;
	}




}
