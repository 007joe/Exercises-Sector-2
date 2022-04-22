package com.techelevator.jghomes;

import java.util.Scanner;

import com.techelevator.jghomes.exceptions.HomeServiceException;
import com.techelevator.jghomes.models.AuthenticatedUser;
import com.techelevator.jghomes.models.Home;
import com.techelevator.jghomes.models.UserCredentials;
import com.techelevator.jghomes.services.AuthenticationService;
import com.techelevator.jghomes.exceptions.AuthenticationServiceException;
import com.techelevator.jghomes.services.HomeService;
import com.techelevator.view.ConsoleService;

public class App {

	private static final String API_BASE_URL = "http://localhost:8080/";

	private static final String MENU_OPTION_EXIT = "Exit";
	private static final String LOGIN_MENU_OPTION_REGISTER = "Register";
	private static final String LOGIN_MENU_OPTION_LOGIN = "Login";
	private static final String[] LOGIN_MENU_OPTIONS = { LOGIN_MENU_OPTION_REGISTER, LOGIN_MENU_OPTION_LOGIN,
			MENU_OPTION_EXIT };

	private static final String MAIN_MENU_OPTION_LIST_ALL_HOMES = "List all homes";
	private static final String MAIN_MENU_OPTION_GET_HOME_BY_ID = "Retrieve home by MLS identifier";
	private static final String MAIN_MENU_OPTION_GET_HOMES_BY_ZIP = "Retrieve homes by zip code";

	private static final String MAIN_MENU_OPTION_ADD_HOME = "Add a home";
	private static final String MAIN_MENU_OPTION_UPDATE_HOME = "Update all home information";



	private static final String MAIN_MENU_OPTION_DELETE_HOME = "Delete a home";

	private static final String MAIN_MENU_OPTION_LOGIN = "Login as different user";
	private static final String[] MAIN_MENU_OPTIONS = { MAIN_MENU_OPTION_LIST_ALL_HOMES,
			MAIN_MENU_OPTION_GET_HOME_BY_ID, MAIN_MENU_OPTION_GET_HOMES_BY_ZIP, MAIN_MENU_OPTION_ADD_HOME, MAIN_MENU_OPTION_UPDATE_HOME, MAIN_MENU_OPTION_DELETE_HOME,
			MAIN_MENU_OPTION_LOGIN, MENU_OPTION_EXIT };

	//Hint:  I have all the user info you may need after you login .. ;-)
	private AuthenticatedUser currentUser;

	// user interface - console service
	private ConsoleService console;

	// client services
	private AuthenticationService authenticationService;
	private HomeService homeService;

	private Scanner scanner;

	public static void main(String[] args) throws HomeServiceException {
		App app = new App(new ConsoleService(System.in, System.out), new AuthenticationService(API_BASE_URL),
				new HomeService(API_BASE_URL));
		app.run();
	}

	public App(ConsoleService console, AuthenticationService authenticationService, HomeService homeService) {
		this.console = console;
		this.authenticationService = authenticationService;
		this.homeService = homeService;
		scanner = new Scanner(System.in);
	}

	public void run() throws HomeServiceException {
		System.out.println("*********************");
		System.out.println("* Welcome to JG Homes! *");
		System.out.println("*********************");

		registerAndLogin();
		mainMenu();
	}

	private void mainMenu() throws HomeServiceException {
		while (true) {
			String choice = (String) console.getChoiceFromOptions(MAIN_MENU_OPTIONS);
			if (MAIN_MENU_OPTION_LIST_ALL_HOMES.equals(choice)) {
				listAllHomes();
			} else if (MAIN_MENU_OPTION_GET_HOME_BY_ID.equals(choice)) {
				getHomeByMLSID();
			}
			else if (MAIN_MENU_OPTION_GET_HOMES_BY_ZIP.equals(choice)) {
				getHomesByZipCode();
			}
			else if (MAIN_MENU_OPTION_ADD_HOME.equals(choice)) {
				addHome();
			}
			else if (MAIN_MENU_OPTION_UPDATE_HOME.equals(choice)) {
				updateHome();
			}
			else if (MAIN_MENU_OPTION_DELETE_HOME.equals(choice)) {
				deleteHome();
			} else if (MAIN_MENU_OPTION_LOGIN.equals(choice)) {
				login();
			} else {
				// the only other option on the main menu is to exit
				exitProgram();
			}
		}
	}

	private void listAllHomes() throws HomeServiceException {
		//TODO:
		// 1.  Call the home service class
		       Home[] homes = homeService.retrieveListOfHomes();
		// 2.  Send results to the console service
		       console.printListOfHomes(homes);
	}

	private void getHomeByMLSID() throws HomeServiceException {
		//TODO:
		// 1.  Call the home service class
		// 2.  Send results to the console service
	}


	private void addHome() throws HomeServiceException {
		//TODO:
		// 1.  Call the console service to get user input
		       Home homeToAdd = console.getHomeInfo();
		// 2.  Send to the home service
		       homeToAdd = homeService.addHome(homeToAdd);
		// 3.  Send results to the console service to print out results
		       console.printHome(homeToAdd);
	}

	private void deleteHome() throws HomeServiceException {
		//TODO:
		// 1.  Call the console service to get user input
		// 2.  Send to the home service
	}


	private void getHomesByZipCode() throws HomeServiceException {
		//TODO: Implement me to practice ...We did not implement on server
	}

	private void updateHome() throws HomeServiceException {
		//TODO: Implement me to practice... We did not implement on server
		//   updates any field in the home object including address
	}


	private void exitProgram() {
		System.exit(0);
	}

	private void registerAndLogin() {
		while (!isAuthenticated()) {
			String choice = (String) console.getChoiceFromOptions(LOGIN_MENU_OPTIONS);
			if (LOGIN_MENU_OPTION_LOGIN.equals(choice)) {
				login();
			} else if (LOGIN_MENU_OPTION_REGISTER.equals(choice)) {
				register();
			} else {
				// the only other option on the login menu is to exit
				exitProgram();
			}
		}
	}

	/*
	 * Current user is not null IF they have been authenticated.
	 */
	private boolean isAuthenticated() {
		return currentUser != null;
	}

	private void register() {
		System.out.println("Please register a new user account");
		boolean isRegistered = false;
		while (!isRegistered) // will keep looping until user is registered
		{
			UserCredentials credentials = console.collectUserCredentials();
			try {
				authenticationService.register(credentials);
				isRegistered = true;
				System.out.println("Registration successful. You can now login.");
			} catch (AuthenticationServiceException e) {
				System.out.println("REGISTRATION ERROR: " + e.getMessage());
				System.out.println("Please attempt to register again.");
			}
		}
	}

	private void login() {
		System.out.println("Please log in");
		currentUser = null;
		while (currentUser == null) // will keep looping until user is logged in
		{
			UserCredentials credentials = console.collectUserCredentials();
			try {
				currentUser = authenticationService.login(credentials);

				// BIG HINT HERE FOR THE CAPSTONE....
				//Grab the AUTH TOKEN from current user and set it on the home service
				//so the home service has the token for it's requests.
				homeService.setAUTH_TOKEN(currentUser.getToken());

				// LOOK ABOVE... DON'T MISS THE BIG HINT....


			} catch (AuthenticationServiceException e) {
				System.out.println("LOGIN ERROR: " + e.getMessage());
				System.out.println("Please attempt to login again.");
			}
		}
	}




}
