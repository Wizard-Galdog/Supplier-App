package part02;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.Random;

/**
 * This class is responsible for adding entries to the application
 * @author Michael Gilroy 40203084
 *
 */
public class AddEntry {

	/**
	 * This is the method for the main menu of the add entry process
	 * @param args
	 */
	public static void main(String[] args) {
		String consoleMessage = ("----ADD ENTRY MENU----\nMenu Options: " 
				+ "\n1. Add New Supplier"
				+ "\n2. Add New Product"
				+ "\n3. Back To Main Menu");
		int menuOptionResponse = Validation.consoleMenu(consoleMessage, 3); //Sets integer to the user input for the console menu
		
		switch (menuOptionResponse) { //Switch statement based on the integer value of the menu option input by the user 
		case 1: Part02Tester.supplierList.add(addNewSupplier());System.out.println("\n----SUPPLIER ADDED----\n");break; //Runs the method to add a supplier
		case 2: addNewProduct(Part02Tester.supplierList);System.out.println("\n----PRODUCT ADDED----\n");break; //Runs the method to add a new product to a existing supplier
		case 3: return; //Returns to the main menu
		default: System.out.println("An unexpected error has occured\nThe application will close in:\n3\n2\n1\nClosing...\nClosed");return;
		}

	}
	
	
	/**
	 * Prompts user for new supplier inputs and initiates and fills a supplier object with these details
	 * @return - The new supplier object
	 */
	private static Supplier addNewSupplier() {
		
		System.out.println("----ADD NEW SUPPLIER----"
				+ "\nPlease input the following details for the Supplier ");
		
		/*
		 * Sets up variables that are used inside the loop but mustn't be re-intialised with every iteration of the loop
		 */
		boolean uniqueSupCode = true;  
		boolean loop = true;
		int supCode = 0;

		do {
			Random rndProCode = new Random(); //randomly generates a suppliercode from between 1000 to 9999
			supCode = 1000 + rndProCode.nextInt(8999);
			
			/*
			 * Loops through all the suppliers in the application
			 * Checks if the supplier code generated matches any already existing supplier codes
			 * If it does, unique sup code is set to false  and the do loopwith iterate again
			 */
			for (Supplier supplier: Part02Tester.supplierList) { 
				if (supplier.getSupCode() == supCode) {
					uniqueSupCode = false;
				}
			}
			
			/*
			 * Exits the loop if the supCode generate isnt already used
			 */
			if (uniqueSupCode == true) { //Exits the loop if the supCode generate isnt already used
				loop = false;
			}
			
		} while (loop == true);
		
		String supName = Validation.validateAndStoreString("Input Supplier Name: "); //Sets supName variable equal to next user input
		
		System.out.println("Please input Address details for Supplier: " + supName);
		Address supAddress = newAddress(); //Sets the supAddress variable equal to the variable of data type (Address) thats returned from the newAddress() method
		
		
		System.out.println("Regions:");
		listSupRegionEnum(); //calls the listSupRegionEnum method
		int regionNumber = 0; //Initialises int data type
		int size = EnumSet.allOf(SupRegion.class).size(); //Sets a int variable equal to the size of the enum 
		
		/*
		 * Loops until the int input is between 1 and the number of items in the enum
		 * Sets region number int(Declared outside of loop) equal to next user input
		 */
		while ((regionNumber<1) || (regionNumber>size)) { 
			regionNumber = Validation.validateAndStoreInt("Select a region between 1 and " + size); //Sets region number int(Delcared outside of loop) equal to next user input
		}  
		SupRegion supRegion = SupRegion.values()[regionNumber-1]; //Sets the region equal to the user selected region
		
		ArrayList<Product> newProductList = new ArrayList<Product>(); //Initiates a new product list for the new supplier object
		
		Supplier newSupplier = new Supplier(supCode, supName, supAddress, supRegion, newProductList);//Initialises and fills new supplier object
		return newSupplier; //returns new supplier
	}
	
	/**
	 * This method uses the console to collect user input for supplier address then returns it to the addNewSupplier method
	 * @return - The supplier Address
	 */
	private static Address newAddress() {
		int bldNum = Validation.validateAndStoreInt("Input Building number: ");//Validates and Sets the bldNum variable equal to the next user input
		String bldStreet = Validation.validateAndStoreStringRemoveAllDigits("Input Street Name: "); //Validates and Sets bldStreet variable equal to the next user input
		String bldTown = Validation.validateAndStoreStringRemoveAllDigits("Input Town: "); //Validates and Sets bldTown variable equal to the next user input
		String bldPcode = Validation.validateAndStoreString("Input Postcode: "); //Validates and Sets bldPcode variable equal to next user input
		String bldCountry = Validation.validateAndStoreStringRemoveAllDigits("Input Country: "); //Validates and Sets bldCountry variable equal to next user input
		Address newAddress = new Address(bldNum, bldStreet, bldTown, bldPcode, bldCountry);//Initialises new address object and fills it with user input
		return newAddress; //Returns the new address
	}
	

	
	/**
	 * Prints all the values available with the SupRegion enum
	 */
	private static void listSupRegionEnum() {
		int size = EnumSet.allOf(SupRegion.class).size(); //Sets integer to the size of the enum
		
		/*
		 * this loop runs exactly i times(The size of the enum)
		 * This prints the enum value in its string format
		 */
		for (int i = 0; i < size; i++) { 
			System.out.println((i+1) + ". " + SupRegion.values()[i].getRegionAsString());
		}
	}
	
	/**
	 * This method prints all the supplier names, then prompts the user to select the supplier to which they wish to add the product to
	 * It then calls the method that prompts user for product details
	 * The method adds the product to the supplier's product list
	 * @param supplierList - The list of suppliers
	 */
	private static void addNewProduct(ArrayList<Supplier> supplierList) {
		System.out.println("Select a supplier to add the product to: ");
		
		int i = 1; //intialises a integer that is used as a counter
		
		/*
		 * Loops through all the suppliers in the supplerList
		 * Prints out the supplier name
		 * increments i
		 * Then iterates
		 */
		for (Supplier supplier: supplierList) { 
			System.out.println(i + ": " + supplier.getSupName());
			i+=1; 
		}
		
		int supplierNum;//intialises a integer that stores the user input
		
		/*
		 * Prints out conditions for validation to pass
		 * Sets supplierNum variable to user input
		 * loops until the int input is between 1 and the number of suppliers 
		 */
		do {
			System.out.println("Please input a integer must be between 1 and " + (i-1));
			supplierNum = Validation.validateAndStoreInt();
		}while ((supplierNum < 1) || (supplierNum > i));
		
		Supplier supplierObject = supplierList.get(supplierNum-1); //Fetchs the user desired supplier object from the supplierList 
		supplierObject.addAnotherProduct(addNewProduct()); //calls the add new product method and adds the returned product object to the supplier's product list
	}
	
	/**
	 * This method prompts the user for product details and stores the inputs in a product object
	 * @return - New product object
	 */
	private static Product addNewProduct() {
		System.out.println("----Product Details----"
			+ "\nPlease enter the following details for the new product");
		
		/*
		 * Sets up variables that are used inside the loop but mustn't be re-intialised with every iteration of the loop
		 */
		boolean uniqueProCode = true; 
		boolean loop = true;
		int proCode = 0;
		
		do {
			Random rndProCode = new Random(); //Randomly generates a product code from 10000 to 99999
			proCode = 10000 + rndProCode.nextInt(89999);
			
			/*
			 * Loops from 0 to supplier.size - 1
			 */
			for (int i = 0; i < Part02Tester.supplierList.size(); i++) { 
				/*
				 * Loops through all the products in that supplier
				 * If the proCode generated is equal to a already existing product code, the uniqueProCode variable is set to false
				 */
				for (Product product: Part02Tester.supplierList.get(i).getSupProducts()) { 
					if (product.getProCode() == proCode) { 
						uniqueProCode = false;
					}
				}
				
			}
			
			if (uniqueProCode == true) { //Exits the loop if the proCode generate isnt already used
				loop = false;
			}
			
		} while (loop == true);
		
		String proMake = Validation.validateAndStoreString("Product Make: "); //Sets proMake variable equal to user input
		String proModel = Validation.validateAndStoreString("Product Model: "); //Sets proModel variable equal to user input
		double proPrice = Validation.validateAndStoreDouble("Product Price: "); //Sets proPrice variable equal to the user input
		boolean proInStock = Validation.validateAndStoreBoolean("Product in stock?"); //Sets proInStock variable equal to the user input
		Product newProduct = new Product(proCode, proMake, proModel, proPrice, proInStock); //intialises and fills a product object with user input product details
		
		if (proInStock == true) { //if the user states the product is correct, then the system prompts the user for quantity in stock
			newProduct.setProQtyAvailable(Validation.validateAndStoreInt("Quantity in stock: ")); //sets product quantity in stock to user input
		}else {
			newProduct.setProQtyAvailable(0); //Sets the product quantity in stock to 0 if the user states false
		}
		
		newProduct.setProDiscontinued(Validation.validateAndStoreBoolean("Product discontinued?"));//sets proDiscontinued variable equal to the next user input
		
		return newProduct; //Returns the product object to caller
		}

}
