package part01;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.Random;

/**
 * This is the main class of the application
 * @author Michael Gilroy 40203084
 *
 */
public class part01Tester {
	
	static ArrayList<Supplier> supplierList = TesterObjects.mainTesterObjects(); //Initiates a list of suppliers, filled with default tester suppliers
	
	public static void main(String[] args) {
		
		int menuOptionResponse = consoleMenu(); // integer set equal to variable returned from consoleMenu method
		//Switch statement based on the integer value of the menu option input by the user
		switch (menuOptionResponse) {
		case 1: printAllProducts();break; //Runs the printAllProducts method
		case 2: supplierList.add(addNewSupplier());System.out.println("\nSupplier added\n");break; //Runs the addNewSupplier method, prints successfully added line 
		case 3: addNewProduct(supplierList);System.out.println("\nProduct added\n");break; //Runs the addNewProduct method, prints successfully added line
		case 4: System.out.println("Application Closing...\nApplication Closed");return; //Exits the application
		default: System.out.println("An unexpected error has occured\nThe application will close in:\n3\n2\n1\nClosing...\nClosed");return;
		}
		
		main(args); //recursively runs the main method
	}
	

	/**
	 * This method prompts user to input a menu option, validates that the input is valid then returns it to the calling method
	 * @return - Menu option response number
	 */
	private static int consoleMenu() {
		System.out.println("----MAIN MENU----\nMenu Options: " //Outputs the number and corresponding menu option
				+ "\n1. Print All Products"
				+ "\n2. Add New Supplier"
				+ "\n3. Add New Product"
				+ "\n4. Exit Application");
		
		int menuOptionResponse = 0; //Initialises the variable outside of the while loop

		//This while loop while keep running until a valid menu option number is input
		while ((menuOptionResponse <1) || (menuOptionResponse > 4)) {
			menuOptionResponse = Validation.validateAndStoreInt("Please input a integer between 1 and 4");
		}
		return menuOptionResponse; //returns the user menu input
	}
	
	/**
	 * Prints all the products offered by a supplier
	 */
	private static void printAllProducts() {
		//Loops through each supplier in the ArrayList supplierList
		for (Supplier supplier: supplierList) {
			System.out.println(); //Prints a empty line on the console
			supplier.printProductsList(); //Runs a method in the supplier object instance that prints the suppliers product details
		}
	}
	
	/**
	 * Prompts user for new supplier inputs and initiates and fills a supplier object with these details
	 * @return - The new supplier object
	 */
	private static Supplier addNewSupplier() {
		
		System.out.println("----ADD NEW SUPPLIER----"
				+ "\nPlease input the following details for the Supplier ");


		boolean uniqueSupCode = true; 
		boolean loop = true;
		int supCode = 0;//intialises variable outside of do loop

		do {
			Random rndProCode = new Random(); //randomly generates a suppliercode from between 1000 to 9999
			supCode = 1000 + rndProCode.nextInt(8999);
			
			for (Supplier supplier: supplierList) { //Loops through all the suppliers in the application
				if (supplier.getSupCode() == supCode) {
					uniqueSupCode = false;
				}
			}
			
			if (uniqueSupCode == true) { //Exits the loop if the supCode generate isnt already used
				loop = false;
			}
			
		} while (loop == true);
		
		String supName = Validation.validateAndStoreString("Input Supplier Name: "); //Sets supName variable equal to next user input
		
		System.out.println("Please input Address details for Supplier: " + supName);
		Address supAddress = newAddress(); //Sets the supAddress variable equal to the variable of data type (Address) thats returned from the newAddress() method
		
		System.out.println("Select a region: ");
		listSupRegionEnum(); //calls the listSupRegionEnum method
		int regionNumber = 0; //Initialises int data type
		int size = EnumSet.allOf(SupRegion.class).size(); //Sets a int variable equal to the size of the enum 
		while ((regionNumber<1) || (regionNumber>size)){
			regionNumber = Validation.validateAndStoreInt("Select a region between 1 and " + size); //Sets region number int(Delcared outside of loop) equal to next user input
		}  //Loops until the int input is between 1 and the number of items in the enum
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
		int bldNum = Validation.validateAndStoreInt("Input Building number: ");//Initialises the variable outside of the while loop
		String bldStreet = Validation.validateAndStoreString("Input Street Name: "); //Sets bldStreet variable equal to the next user input
		String bldTown = Validation.validateAndStoreString("Input Town: "); //Sets bldTown variable equal to the next user input
		String bldPcode = Validation.validateAndStoreString("Input Postcode: "); //Sets bldPcode variable equal to next user input
		String bldCountry = Validation.validateAndStoreString("Input Country: "); //Sets bldCountry variable equal to next user input
		Address newAddress = new Address(bldNum, bldStreet, bldTown, bldPcode, bldCountry);//Initialises new address object and fills it with user input
		return newAddress; //Returns the new address
	}
	
	
	/**
	 * Prints all the values available with the SupRegion enum
	 */
	private static void listSupRegionEnum() {
		int size = EnumSet.allOf(SupRegion.class).size(); //Sets integer to the size of the enum
		for (int i = 0; i < size; i++) { //this loop runs exactly i times
			System.out.println((i+1) + ". " + SupRegion.values()[i].getRegionAsString());//This prints the enum value in its string format
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
		for (Supplier supplier: supplierList) { //Loops through all the suppliers in the supplerList
			System.out.println(i + ": " + supplier.getSupName());
			i+=1; //increments i
		}
		
		int supplierNum;//intialises a integer that stores the user input
		do {
			System.out.println("Please input a integer must be between 1 and " + (i-1));
			supplierNum = Validation.validateAndStoreInt(); //Sets supplierNum variable to user input
		}while ((supplierNum < 1) || (supplierNum > i));//loops until the int input is between 1 and the number of suppliers 
		
		Supplier supplierObject = supplierList.get(supplierNum-1); //Fetchs the user desired supplier object from the supplierList 
		supplierObject.addAnotherProduct(addNewProduct()); //calls the add onew product method and adds the returned product object to the supplier's product list
	}
	
	/**
	 * This method prompts the user for product details and stores the inputs in a product object
	 * @return - New product object
	 */
	private static Product addNewProduct() {
		System.out.println("----Product Details----"
			+ "\nPlease enter the following details for the new product");
		
		boolean uniqueProCode = true; 
		boolean loop = true;
		int proCode = 0;//intialises variable outside of do loop

		do {
			Random rndProCode = new Random(); //Randomly generates a product code from 10000 to 99999
			proCode = 10000 + rndProCode.nextInt(89999);
			
			for (int i = 0; i < supplierList.size(); i++) { //Loops from 0 to supplier.size - 1
				for (Product product: supplierList.get(i).getSupProducts()) { //Loops through all the products in the supplier
					if (product.getProCode() == proCode) { //If the proCode generate is equal to a already existing product code, the uniqueProCode variable is set to false
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
		
		if (proInStock == true) { //if the user states the product is correct, the the system prompts the user for quantity in stock
			newProduct.setProQtyAvailable(Validation.validateAndStoreInt("Quantity in stock: ")); //sets product quantity in stock to user input
		}else {
			newProduct.setProQtyAvailable(0); //Sets the product quantity in stock to 0 if the user states false
		}
		
		newProduct.setProDiscontinued(Validation.validateAndStoreBoolean("Product discontinued?"));
		
		return newProduct; //Returns the product object to caller
		}
	
}
