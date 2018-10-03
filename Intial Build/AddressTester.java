package part01;
import part01.Address;
import part01.Validation;

/**
 * This is the address tester class
 * @author Michael Gilroy 40203084
 *
 */
public class AddressTester {
	
	/**
	 * Prints out 2 instances of the address object.
	 * @param args
	 */
	public static void main(String args[]) {
		
		Address addressTester1 = new Address(4, "Antrim Road" , "Belfast", "BT76 7DE", "Northern Ireland"); //Address object made using constructor
		System.out.println(addressTester1.getFullAddress());
		
		Address addressTester2 = newAddress(); //Address object made using user inputs
		System.out.println(addressTester2.getFullAddress());
	}
	
	/**
	 * This method uses the console to collect user input for supplier address then returns it to the addNewSupplier method
	 * @return - The supplier Address
	 */
	private static Address newAddress() {
		System.out.println("----Please input address details----");
		int bldNum = Validation.validateAndStoreInt("Input Building number: ");//Initialises the variable outside of the while loop
		String bldStreet = Validation.validateAndStoreString("Input Street Name: "); //Sets bldStreet variable equal to the next user input
		String bldTown = Validation.validateAndStoreString("Input Town: "); //Sets bldTown variable equal to the next user input
		String bldPcode = Validation.validateAndStoreString("Input Postcode: "); //Sets bldPcode variable equal to next user input
		String bldCountry = Validation.validateAndStoreString("Input Country: "); //Sets bldCountry variable equal to next user input
		Address newAddress = new Address(bldNum, bldStreet, bldTown, bldPcode, bldCountry);//Initialises new address object and fills it with user input
		return newAddress; //Returns the new address
	}
}
