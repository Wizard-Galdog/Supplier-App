package part02;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.Random;
/**
 * This is the supplier tester class
 * @author Michael Gilroy 40203084
 *
 */
public class SupplierTester {

	/**
	 * Prints out 2 instances of the supplier object
	 * @param args
	 */
	public static void main(String[] args) {
		//Creates instances of Address and Product objects (These are used in the creation of the supplier object)
		Address addressTester1 = new Address(4, "Antrim Road" , "Belfast", "BT76 7DE", "Northern Ireland");
		Product productTester1 = new Product(8, "HTC", "VIVE", 545.59, true);
		ArrayList<Product> arrayListProductTester = new ArrayList<Product>(); //Creates a ArrayList that stores Products
		arrayListProductTester.add(productTester1); //Adds product instance to the ArrayList
		Supplier supplierTester1 = new Supplier(8, "Carphone Warehouse", addressTester1, SupRegion.UNITED_KINGDOM, arrayListProductTester); //Creates instance of supplier using constructor
		System.out.println(supplierTester1.getSupAddress().getFullAddress()); //Prints supplier address
		supplierTester1.printProductsList(); //Prints supplier products list
		
		Supplier supplierTester2 = addNewSupplier(); //Creates a new instance of the supplier object using user inputs
		System.out.println(supplierTester2.getSupAddress().getFullAddress());
	}
	
	/**
	 * Prompts user for new supplier inputs and initiates and fills a supplier object with these details
	 * @return - The new supplier object
	 */
	private static Supplier addNewSupplier() {
		System.out.println("----ADD NEW SUPPLIER----"
				+ "\nPlease input the following details for the Supplier ");
		Random rndProCode = new Random(); //randomly generates a suppliercode from between 1 to 10001
		int supCode = 1 + rndProCode.nextInt(10000);

		String supName = Validation.validateAndStoreString("Input Supplier Name: "); //Sets supName variable equal to next user input
		
		System.out.println("Please input Address details for Supplier: " + supName);
		Address supAddress = newAddress(); //Sets the supAddress variable equal to the variable of data type (Address) thats returned from the newAddress() method
		
		System.out.println("Select a region: ");
		listSupRegionEnum(); //calls the listSupRegionEnum method
		int regionNumber = 0; //Initialises int data type
		int size = EnumSet.allOf(SupRegion.class).size(); //Sets a int variable equal to the size of the enum 
		do {
			regionNumber = Validation.validateAndStoreInt(); //Sets region number int(Delcared outside of loop) equal to next user input
			System.out.println("ERROR\nSelect a region between 1 and " + size);
		} while ((regionNumber<1) || (regionNumber>size)); //Loops until the int input is between 1 and the number of items in the enum
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
}
