package part02;

import java.util.Scanner;
/**
 * This class contains all the methods for validating user input for particular data types
 * @author Michael Gilroy 40203084
 *
 */

public class Validation {
	private Validation(){} //this allows for the methods in the class to be assessed without a new instance being made of the class
	
	static Scanner scan; //Makes a static scanner for the class
	
	/**
	 * This method retrieves a string user input and validates it
	 * @param message - The message prompting the user for the input ex) "Input a Supplier Name"
	 * @return - The user String input
	 */
	public static String validateAndStoreString(String message) {
		System.out.println(message); //Prints message
		scan = new Scanner(System.in);
		boolean loop = true; 
		String input = ""; //Initialises string

		//loops while 'loop' variable is true
		while (loop) {
			while (scan.hasNextLine() == false) { //If the scanner doesnt 'hold' the next line, it prints a error. if it does 'hold' the next line, it exits the while loop
			System.out.println("ERROR\nPlease input valid data\n" + message);
			}
			
			input = scan.nextLine().trim(); //Sets input string (declared outside of loop) to user input
			if (input.isEmpty()) { //If the input is empty, then a error is printer. Otherwise 'loop' is set to false and the loop is exited
				System.out.println("ERROR\nInput must contain characters\n" + message);
			} else {
				loop = false; //Causes loop to end
			}
		}
		
		return input; //Returns the input
	}
	
	/**
	 * This method retrieves a int user input and validates it
	 * @paramo
	 * @return- The user int input
	 */
	public static int validateAndStoreInt(String message) {
		System.out.println(message); //Prints message
		scan = new Scanner(System.in);
		boolean loop = true;
		int input = 0;//Initialises int
		
		//loops while 'loop' variable is true
		while (loop) {
			while (scan.hasNextLine() == false) {//If the scanner doesnt 'hold' the next line, it prints a error. if it does 'hold' the next line, it exits the while loop
				System.out.println("ERROR\n" + message);
			}
			
			String stringInput = scan.nextLine().trim();
			if (stringInput.isEmpty()) {
				System.out.println("ERROR\nInput must not be empty\n" + message);
				continue;
			}
			try {
				input = Integer.parseInt(stringInput);
				if (input < 0) {
					System.out.println("ERROR\nInput must not be a negative number\n" + message);
					continue;
				}
				loop = false;
			} catch (Exception e) {
				helpMenu(stringInput);
				System.out.println("ERROR\nPlease remove all charcters that aren't numbers");
			}
			
		}
		return input;	//returns the input
	}
	
	/**
	 * This method is the same o
	 * This is for some circumstances when a message isn't appropriate
	 * @return - the user int input
	 */
	public static int validateAndStoreInt() {
		scan = new Scanner(System.in);
		boolean loop = true;
		int input = 0;//Initialises int
		
		//loops while 'loop' variable is true
		while (loop) {
			while (scan.hasNextLine() == false) {//If the scanner doesnt 'hold' the next line, it prints a error. if it does 'hold' the next line, it exits the while loop{
				System.out.println("ERROR\nPlease input a integer");
			}
			
			String stringInput = scan.nextLine().trim();
			if (stringInput.isEmpty()) {
				System.out.println("ERROR\nInput must not be empty");
				continue;
			}
			try {
				input = Integer.parseInt(stringInput);
				loop = false;
			} catch (Exception e) {
				helpMenu(stringInput);
				System.out.println("ERROR\nPlease remove all characters that aren't numbers");
			}
		}
		return input;	//returns the input
	}
	
	/**
	 * This method validates and stores user input doubles
	 * @param  message - The message prompting the user for the input ex) "Input price"
	 * @return - The user (double) input
	 */
	public static double validateAndStoreDouble(String message) {
		System.out.println(message);
		scan = new Scanner(System.in);
		boolean loop = true;
		double input = 0.00; //intialises the double
		while (loop) { //loops while the 'loop' variable is true
			while (scan.hasNextLine() == false) {//If the scanner doesnt 'hold' the next line, it prints a error. if it does 'hold' the next line, it exits the while loop
				System.out.println("ERROR\nPlease input a double\n" + message);
			}
			
			if (scan.hasNextDouble() == true) {//if the scanner's next line holds a 'double' then it executes this if statement
				input = scan.nextDouble(); //Sets input variable equal to the double input
				loop = false; //Causes loop to end
			} else {
				System.out.println("ERROR\nPlease input a double\n" + message);
				scan.nextLine();
			}
		}
		return input; //returns input
	}
	
	/**
	 * This method validates and stores boolean inputs into the system by the user
	 * @param message - The message prompting the user for the input ex) "Input price"
	 * @return - The user (Boolean) input
	 */
	public static boolean validateAndStoreBoolean(String message) {
		System.out.println(message + "\nPlease enter 'true' or 'false");
		scan = new Scanner(System.in);
		boolean loop = true;
		boolean input = false; //intialises boolean variable
		
		//loops while 'loop' variable is true
		while (loop) {
			while (scan.hasNextLine() == false) {//If the scanner doesnt 'hold' the next line, it prints a error. if it does 'hold' the next line, it exits the while loop{
				System.out.println("ERROR\n" + message + "\nPlease enter 'true' or 'false");
			}
			
			if (scan.hasNextBoolean() == true) {//if the scanner's next line holds a 'boolean' then it executes this if statement
				input = scan.nextBoolean(); //Sets input variable equal to user input
				loop = false;
			} else {
				System.out.println("ERROR\n" + message + "\nPlease enter 'true' or 'false");
				scan.nextLine();
			}
		}
		return input; //Returns the user input
	}
	
	/**
	 * This method prints the console message, then prompts the user to input a menu option, validates that the input is valid then returns it to the calling method
	 * @return - Menu option response number
	 */
	public static int consoleMenu(String consoleMessage, int upperLimit) {
		System.out.println(consoleMessage);
		
		int menuOptionResponse = 0; //Initialises the variable outside of the while loop

		//This while loop while keep running until a valid menu option number is input
		while ((menuOptionResponse <1) || (menuOptionResponse > upperLimit)) {
			menuOptionResponse = Validation.validateAndStoreInt("Please input a integer between 1 and " + upperLimit);
		}
		return menuOptionResponse; //returns the user menu input
	}
	
	public static String validateAndStoreStringRemoveAllDigits(String message) {
		String input = "";
		do {
			input = Validation.validateAndStoreString(message); //Sets bldStreet variable equal to the next user input
			if (input.matches(".*\\d+.*")) {
				System.out.println("ERROR\nRemove all digits from input");
			}
		} while (input.matches(".*\\d+.*"));
		return input;
	}
	
	/**
	 * This method prints the help menu and handles its process if the user types 'HELP'
	 * @param userInput - The input by the user
	 */
	private static void helpMenu(String userInput) {
		
		userInput = userInput.toUpperCase();
		if (userInput.equals("HELP") == false) {
			return;
		}
		
		String consoleMessage = "----HELP MENU----\nMenu Options: "
				+ "\n1. Report Error"
				+ "\n2. Exit Program";

		int menuOptionResponse = Validation.consoleMenu(consoleMessage, 4);
		
		switch (menuOptionResponse) {
		case 1: System.out.println("----REPORTING ERROR----\nDeveloper Email: 40203084@ads.qub.ac.uk");break;
		case 2: System.out.println("Application Closing...\nApplication Closed");System.exit(0);break;
		}
		
		boolean exitProgram = Validation.validateAndStoreBoolean("Continue execution of application?");
		if (exitProgram == false) {
			System.out.println("Application Closing...\nApplication Closed");System.exit(0);
		}
	}
}
