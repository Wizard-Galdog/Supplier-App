package part02;

import java.util.ArrayList;
import java.util.EnumSet;

/**
 * This is the editing class
 * @author Michael Gilroy 40203084
 *
 */
public class EditEntries {

	/**
	 * This is the main method of the edit entries class
	 * @param args
	 */
	public static void main(String[] args) {
		String consoleMessage = "Select An Option: " 
				+ "\n1. Edit Supplier"
				+ "\n2. Edit Product"
				+ "\n3. Back To Main Menu";
		int menuOptionResponse = Validation.consoleMenu(consoleMessage, 3);
		
		switch (menuOptionResponse) { //Switch statement based on the integer value of the menu option input by the user 
		case 1:  editSupplier();break; //Runs the method to edit a supplier
		case 2: editProduct();break; //Runs the method to edit a product to a existing supplier
		case 3: return; //Returns to the main menu
		default: System.out.println("An unexpected error has occured\nThe application will close in:\n3\n2\n1\nClosing...\nClosed");return;
		}

	}
	
	
	/**
	 * This is the parent method for the edit supplier process
	 */
	private static void editSupplier() {
		int supplierIndex = printAndSelectSuppliers(); //Sets int equal to the selected suppliers index 
		System.out.println("---Supplier Selected---\n" + Part02Tester.supplierList.get(supplierIndex).supplierDetails()); //prints selected suppliers details
		
		String consoleMessage = "---Select a record to edit---"
				+ "\n1. Supplier Name" 
				+ "\n2. Supplier Address"
				+ "\n3. Supplier Region"
				+ "\n4. Supplier Products";
		int switchIndex = Validation.consoleMenu(consoleMessage, 4); //Sets int equal to a value representing selected record that the user wants to edit
		switch (switchIndex) {
		case 1: Part02Tester.supplierList.get(supplierIndex).setSupName(Validation.validateAndStoreString("Input new supplier name:"));break; //Edits supplier name 
		case 2: Part02Tester.supplierList.get(supplierIndex).setSupAddress(editAddress(Part02Tester.supplierList.get(supplierIndex).getSupAddress()));break; //Calls the parent method for the edit address process and sets its return value to the new address 
		case 3: Part02Tester.supplierList.get(supplierIndex).setSupRegion(selectNewSupRegion());break;//Calls the parent method for the change SupRegion process and sets its return value to the new region
		case 4: Part02Tester.supplierList.get(supplierIndex).setSupProducts(editProduct(Part02Tester.supplierList.get(supplierIndex).getSupProducts()));break;//Calls one of the parent methods for the edit product process and sets its return value to the new product list
		}
		
	}
	
	/**
	 * This method prompts prints all suppliers, prompts the user to select a supplier, then validates that the input is valid then returns it to the calling method
	 * @return - The supplier index
	 */
	private static int printAndSelectSuppliers() {
		System.out.println("Select a supplier: ");
		for (int i = 0; i < Part02Tester.supplierList.size();i++) { //Prints all the suppliers in the application
			System.out.println((i+1) + ". " + Part02Tester.supplierList.get(i).getSupName());
		}
		
		int supplierIndex = 0; //intialises int outside of loop
		while ((supplierIndex < 1) || (supplierIndex > Part02Tester.supplierList.size())) {
			supplierIndex = Validation.validateAndStoreInt("Input a integer between 1 and " + Part02Tester.supplierList.size()); //Sets supplierIndex equal to validated user input
		}
		return supplierIndex - 1; //
	}
	
	
	/**
	 * This is the parent method for the edit address process
	 * @param address - The original(unedited) version of the address instance
	 * @return - The edited version of the address instance
	 */
	private static Address editAddress(Address address) {
		String consoleMessage = "---Select a record to edit---"
				+ "\n1. Building Number" 
				+ "\n2. Street Name"
				+ "\n3. Town"
				+ "\n4. Post Code"
				+ "\n5. Country";
		int menuOptionResponse = Validation.consoleMenu(consoleMessage, 5);
		switch (menuOptionResponse) {
		case 1: address.setBldNum(Validation.validateAndStoreInt("Input Building Number: "));break;
		case 2: address.setBldStreet(Validation.validateAndStoreString("Input Street Name: "));break;
		case 3: address.setBldTown(Validation.validateAndStoreString("Input Town: "));break;
		case 4: address.setBldPcode(Validation.validateAndStoreString("Input Post Code: "));break;
		case 5: address.setBldCountry(Validation.validateAndStoreString("Input Country: "));break;
		}
		return address;
	}
	
	
	/**
	 * Method responsible for editing SupRegion
	 * @return - The new SupRegion selected by the user
	 */
	private static SupRegion selectNewSupRegion() {
		System.out.println("Regions: ");
		int size = EnumSet.allOf(SupRegion.class).size(); //Sets integer to the size of the enum
		for (int i = 0; i < size; i++) { //this loop runs exactly i times
			System.out.println((i+1) + ". " + SupRegion.values()[i].getRegionAsString());//This prints the enum value in its string format
		}
		
		int regionNumber = 0; //Initialises int data type 
		while ((regionNumber<1) || (regionNumber>size)) { //Loops until the int input is between 1 and the number of items in the enum
			regionNumber = Validation.validateAndStoreInt("Select a region between 1 and " + size); //Sets region number int(Declared outside of loop) equal to next user input
		}
		return SupRegion.values()[regionNumber-1]; //Returns SupRegion
	}
	
	
	/**
	 * This is the parent method of the edit product process
	 * NOTE: this method is used to edit a product whenever a supplier hasnt been selected yet. 
	 */
	private static void editProduct() {
		int supplierIndex = printAndSelectSuppliers(); //Sets supplierIndex equal to the index of the supplier selected by the user
		System.out.println("Products by : " + Part02Tester.supplierList.get(supplierIndex).getSupName());
		Part02Tester.supplierList.get(supplierIndex).setSupProducts(editProduct(Part02Tester.supplierList.get(supplierIndex).getSupProducts())); //Changes the products variable instance of the supplier to a new ArrayList<Product> with the edited data
	}
	
	/**
	 * Edits the ArrayList<Product> passed into the method
	 * Returns the ArrayList<Product> (Edited) to the user
	 * @param products - The ArrayList<Product> that the user passes in (This is the list of products that the suppleir chooses from to edit)
	 * @return - Edited ArrayList<Product> 
	 */
	private static ArrayList<Product> editProduct(ArrayList<Product> products) {
		int productIndex = printAndSelectProducts(products); //Gets the index of the product the user selected
		products.set(productIndex, editProductRecords(products.get(productIndex))); //Replaces the product stored in index productIndex with the new edited product object
		return products;
	}
	
	/**
	 * Prints the list of products passed to the method
	 * User selects a product to work with
	 * @param arrayListProducts - The ArrayList<Product> that the user passes in
	 * @return - The index of the selected product in the ArrayList
	 */
	private static int printAndSelectProducts(ArrayList<Product> arrayListProducts) {
		for (int i = 0; i < arrayListProducts.size(); i++) { //Loops through the arrayList
			System.out.println((i+1) + ". " + arrayListProducts.get(i).getProductDetails()); //Prints product details
		}
		
		int menuOptionResponse = 0; //Declared outside loop because its need for the while loop
		while ((menuOptionResponse < 1) || (menuOptionResponse > arrayListProducts.size())) { 
			if (arrayListProducts.size() != 1) {
				menuOptionResponse = Validation.validateAndStoreInt("Select a product to edit\nInput a integer between 1 and " + arrayListProducts.size()); //Sets int to the user validated input
			} else {
				menuOptionResponse = 1;
			}
			
		}
		
		return menuOptionResponse - 1; //Subtracts one because index starts at 0
	}
	
	/**
	 * This is the edit product main menu
	 * @param product - The product object that is being worked with(edited)
	 * @return - The edited product object
	 */
	private static Product editProductRecords(Product product) {
		String consoleMessage = "---Select a record to edit---"
				+ "\n1. Product Make" 
				+ "\n2. Product Model"
				+ "\n3. Product Price"
				+ "\n4. Quantity Available"
				+ "\n5. Product Discontinued";
		
		int recordToEdit = Validation.consoleMenu(consoleMessage, 5); //Sets recordToEdit variable equal to user input
		
		switch (recordToEdit) {
		case 1: product.setProMake(Validation.validateAndStoreString("Input new product make: "));break; //Sets proMake to user input
		case 2: product.setProModel(Validation.validateAndStoreString("Input a new product model: "));break; //Sets proModel to user input
		case 3: product.setProPrice(Validation.validateAndStoreDouble("Input a new product price: "));break; //Sets proPrice to user input
		case 4: product.setProQtyAvailable(Validation.validateAndStoreInt("Input a valid integer: "));break; //Sets proQtyAvailable to user input
		case 5: product.setProDiscontinued(Validation.validateAndStoreBoolean("Is product discontinued?"));break; //Sets proDiscontinued to user input
		}
		
		return product; //Returns the product object
	}
	
	/**
	 * Method used to edit a product
	 * Used by the Search Class(Thats why its public)
	 * @param supIndex - The suppliers index in the ArrayList<Supplier>
	 * @param proIndex - The products index in the supplier's ArrayList<Product>
	 */
	public static void editProductFromSearch(int supIndex, int proIndex) {
		ArrayList<Product> tempList = Part02Tester.supplierList.get(supIndex).getSupProducts(); //Temporary storage for product list
		Product product = tempList.get(proIndex);//Declares product object equal to the product from tempList at index proIndex
		product = editProductRecords(product); // Sets product equal to the returned edited product by editProductRecords method
		tempList.set(proIndex, product); //Updates product at proIndex to the new edited product object
		Part02Tester.supplierList.get(supIndex).setSupProducts(tempList); //Updates arrayList of the supplier
	}
	
	/**
	 * This is the parent method for the edit supplier process when called from search class
	 * @param supplierIndex - The supplier index in the ArrayList<Supplier>
	 */
	public static void editSupplierFromSearch(int supplierIndex) {
		System.out.println("---Supplier Selected---\n" + Part02Tester.supplierList.get(supplierIndex).supplierDetails()); //prints selected suppliers details
			
		String consoleMessage = "---Select a record to edit---"
				+ "\n1. Supplier Name" 
				+ "\n2. Supplier Address"
				+ "\n3. Supplier Region"
				+ "\n4. Supplier Products";
		int switchIndex = Validation.consoleMenu(consoleMessage, 4); //Sets int equal to a value representing selected record that the user wants to edit
		switch (switchIndex) {
		case 1: Part02Tester.supplierList.get(supplierIndex).setSupName(Validation.validateAndStoreString("Input new supplier name:"));break; //Edits supplier name 
		case 2: Part02Tester.supplierList.get(supplierIndex).setSupAddress(editAddress(Part02Tester.supplierList.get(supplierIndex).getSupAddress()));break; //Calls the parent method for the edit address process and sets its return value to the new address 
		case 3: Part02Tester.supplierList.get(supplierIndex).setSupRegion(selectNewSupRegion());break;//Calls the parent method for the change SupRegion process and sets its return value to the new region
		case 4: Part02Tester.supplierList.get(supplierIndex).setSupProducts(editProduct(Part02Tester.supplierList.get(supplierIndex).getSupProducts()));break;//Calls one of the parent methods for the edit product process and sets its return value to the new product list
		}
	}
	
	/**
	 * Method used to edit address
	 * Used by the search class (Thats why its public)
	 * @param supplierIndex - The supplier index in the ArrayList<Supplier>
	 */
	public static void editAddressFromSearch(int supplierIndex) {
		Part02Tester.supplierList.get(supplierIndex).setSupAddress(editAddress(Part02Tester.supplierList.get(supplierIndex).getSupAddress()));
	}
}
