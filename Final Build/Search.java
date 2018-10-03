package part02;

import java.util.EnumSet;
/**
 * This class is responsible for searching through the application
 * @author Michael Gilroy 40203084
 *
 */
public class Search {

	/**
	 * This is the main method of the Search class and displays the Search menu
	 * @param args
	 */
	public static void main(String[] args) {
		String consoleMessage = "----SEARCH MENU----\nMenu Options: " 
				+ "\n1. Search for Supplier"
				+ "\n2. Search for Product"
				+ "\n3. Back To Main Menu";
		
		int menuOptionResponse = Validation.consoleMenu(consoleMessage, 3); //Sets int to the validated user input
		
		switch (menuOptionResponse) { //Switch statement based on the integer value of the menu option input by the user 
		case 1:  searchForSupplier();break; //Runs the method to add a supplier
		case 2: searchForProduct();break; //Runs the method to add a new product to a existing supplier
		case 3: return; //Returns to the main menu
		default: System.out.println("An unexpected error has occured\nThe application will close in:\n3\n2\n1\nClosing...\nClosed");return;
		}
	}

	/**
	 * This is the main method responsible for searching for a supplier
	 */
	private static void searchForSupplier() {
		String consoleMessage = "Select a option to search for supplier by:"
				+ "\n1. Supplier Code"
				+ "\n2. Supplier Name"
				+ "\n3. Supplier Address"
				+ "\n4. Supplier Region"
				+ "\n5. Supplier Products";
				
		supplierSwitch(Validation.consoleMenu(consoleMessage, 5));	
	}
	
	/**
	 * This is the method that contains the switch case for supplier variables
	 * In its own method for recursion
	 * @param supplierVariable - The number that the user inputs for the switch case
	 */
	private static void supplierSwitch(int supplierVariable) {
		switch (supplierVariable) {
		case 1: int supCode = Validation.validateAndStoreInt("Input Supplier Code: "); //Sets supCode to the validated user input
				for (Supplier supplier: Part02Tester.supplierList) { //Loops through every supplier in the application
					if (supplier.getSupCode() == supCode) { //Compares every existing supplier's supplier code to the user's input supplier code
						System.out.println("----SUPPLIER FOUND----"); //Prints that the supplier was found
						System.out.println(supplier.supplierDetails()); //Prints the supplier's details
						return; //Used to stop execution of this method
					}
				};
				System.out.println("No Supplier Found with the supplier code: " + supCode); //Prints that no supplier was found
				supplierSwitch(1); //Calls method recursively
				break; //Exits the switch
		case 2: String supName = Validation.validateAndStoreString("Input Supplier Name: "); //Sets supName to the validated user input
				for (Supplier supplier: Part02Tester.supplierList) { //Loops through every supplier in the application
					if (supplier.getSupName().equals(supName)) { //Compares every existing supplier's supplier name to the user input supplier name
							System.out.println("----SUPPLIER FOUND----"); //Prints that the supplier was found
							System.out.println(supplier.supplierDetails());// Prints the supplier details
							return; //Used to stop execution of this method
					}
				};
				System.out.println("No Supplier Found with the supplier name: " + supName); //Prints that no suppliers details
				supplierSwitch(2); //Calls method recursively
				break; //Exits the switch
		case 3: addressMenu();break; //Runs the addressMenu() method, This allows the user to search by supplier address variables
		case 4: System.out.println("Select region to search for: ");
				listSupRegionEnum(); //calls the listSupRegionEnum method
				int regionNumber = 0; //Initialises int data type outside of loop
				int size = EnumSet.allOf(SupRegion.class).size(); //Sets a int variable equal to the size of the enum 
				while ((regionNumber<1) || (regionNumber>size)) { //Loops until the int input is between 1 and the number of items in the enum
					regionNumber = Validation.validateAndStoreInt("Select a region between 1 and " + size); //Sets region number int(Declared outside of loop) equal to next user input
				}; 
				
				SupRegion supRegion = SupRegion.values()[regionNumber-1]; //Sets the region equal to the user selected region
				for (Supplier supplier: Part02Tester.supplierList) { //Loops through every supplier in the application
					if (supplier.getSupRegion() == supRegion) { //If the supplier has a supRegion matching the user input, then this if statement's code is run
						System.out.println("----A SUPPLIER HAS BEEN FOUND----"); //Prints that the supplier was found
						System.out.println(supplier.supplierDetails()); //Prints that supplier's details
						return; //Used to stop execution of this method
					}
				};
				System.out.println("No Supplier Found with region: " + supRegion); //Prints that no supplier details were found
				supplierSwitch(4);//Calls method recursively
				break; //Exits the switch
		case 5: searchForProduct();break; //Runs the searchForProduct() method, This allows for the user to search for a supplier using a product variable
		}
	}

	/**
	 * This is the method responsible for searching for a supplier using a address
	 */
	private static void addressMenu() {
		String consoleMessage = "----SEARCH FOR ADDRESS MENU----\nSearch by: "
				+ "\n1. Town"
				+ "\n2. Post Code"
				+ "\n3. Country";
		
		addressSwitch(Validation.consoleMenu(consoleMessage, 3)); //Sets int equal to the user input value 
		
		afterAddressSearchProcessIsComplete(); //Runs the 'next stage' of searching for address after suppliers have been listed		
		
	}
	
	/**
	 * This is the method that contains the switch case for the address variables
	 * In its own method for recursion
	 * @param addressVariable - The number that the user inputs for the switch case
	 */
	private static void addressSwitch(int addressVariable) {
		int i = 1; //Used as both a counter and a conditional checks
		
		switch (addressVariable) {
		case 1: String town = Validation.validateAndStoreStringRemoveAllDigits("Input Town: "); //Sets town to validated used input
				for (Supplier supplier: Part02Tester.supplierList) { //Loops through every supplier in the application
					if (supplier.getSupAddress().getBldTown().equals(town)) { //If that supplier's town is equal to the user input then supplier details are printed out
						System.out.println(i + ". " + supplier.supplierDetails());
						i += 1; //Increments i
					}
				}
				if (i == 1) { //Checks that a supplier was printed, if one wasnt, then this message is printed
					System.out.println("No Suppliers in town : " + town + " were found");
					addressSwitch(1); //Calls method recursively
				};break; //Exits switch
		case 2: String postCode = Validation.validateAndStoreString("Input Post Code: "); //Sets postCode variable to validated user input
				for (Supplier supplier: Part02Tester.supplierList) { //Loops through every supplier in the application
					if (supplier.getSupAddress().getBldPcode().equals(postCode)) { //IF the supplier's postCode is equal to the user input, then the supplier details are printed out
						System.out.println(i + ". " + supplier.supplierDetails());
						i += 1; // Increments i
					}
				}
				if (i == 1) { //Checks that a supplier was printed, if one wasnt, then this message is printed
					System.out.println("No Suppliers in Post Code : " + postCode + " were found");  
					addressSwitch(2); //Calls method recursively
				};break; //Exits switch
		case 3: String country = Validation.validateAndStoreStringRemoveAllDigits("Input Country : "); //Sets country variable to validated user input
			for (Supplier supplier: Part02Tester.supplierList) { //Loops through every supplier in the application
				if (supplier.getSupAddress().getBldCountry().equals(country)) { //If the suppliers country is equal to the user input then the supplier's details are printed
					System.out.println(i + ". " + supplier.supplierDetails()); 
					i += 1; //Increments i
				}
			}
			if (i == 1) { //Checks that a supplier was printed, if one wasnt, then thisqqqqq message if printed
				System.out.println("No Suppliers in Country : " + country + " were found");
				addressSwitch(3); //Calls method recursively
			};break; //Exits switch
		}
		
		afterAddressSearchProcessIsComplete(); //Runs the 'next stage' of searching for address after suppliers have been listed		
	}
	
	/**
	 * This is the menu for after the address search method has run
	 */
	private static void afterAddressSearchProcessIsComplete() {
		String consoleMessage = "Select An Option: "
				+ "\n1. Edit Address"
				+ "\n2. Edit Supplier"
				+ "\n3. Back to main menu";
		
		int menuOptionResponse = Validation.consoleMenu(consoleMessage, 3); //Sets menuOptionResponse to the user input int
		
		switch (menuOptionResponse) {
		case 1: EditEntries.editAddressFromSearch(findSupplier(Validation.validateAndStoreInt("Input Supplier Code: ")));;break; //EDIT ADDRESS
		case 2: EditEntries.editSupplierFromSearch(findSupplier(Validation.validateAndStoreInt("Input Supplier Code: ")));break; //EDIT SUPPLIER
		case 3: return;
		}
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
	 * This is the method for the main menu for searching products
	 */
	private static void searchForProduct() {
		String consoleMessage = "----SEARCH FOR PRODUCT MENU----\nSearch by: "
				+ "\n1. Product Code"
				+ "\n2. Product Make"
				+ "\n3. Product Model"
				+ "\n4. Product Price"
				+ "\n5. Products In Stock"
				+ "\n6. Products Not In Stock"
				+ "\n7. Discontinued Products";
		
		int productVariable = Validation.consoleMenu(consoleMessage, 7); //Sets productVariable equal to user input int

		/*
		 * This variables are declared outside of switch case so they are accessible outside of its bounds
		 */
		int proCode = 0;
		String proMake = "" , proModel = "";
		double proPrice = 0;
		
		switch (productVariable) {
		case 1: proCode = Validation.validateAndStoreInt("Input Product Code: ");break; //Sets proCode to validated user input
		case 2: proMake = Validation.validateAndStoreString("Input Product Make: ");break; //Sets proMake to validated user input
		case 3: proModel = Validation.validateAndStoreString("Input Product Model: ");break; //Sets proModel to validated user input
		case 4: proPrice = Validation.validateAndStoreDouble("Input Product Price: ");break; //Sets proPrice to validated user input
		}

		int supplierCounter = 0; //Used as counter in the for statement
		for (Supplier supplier: Part02Tester.supplierList) { //Loops through each supplier in the application
			switch (productVariable) {
			case 1:  //proCode
					for (Product product: supplier.getSupProducts()) {
						if (product.getProCode() == proCode) {
							int productCounter = -1;
							printSupplierAndProductDetails(supplierCounter, productCounter, supplier, product);
							supplierCounter += 1;
						}
					};
					break;
			case 2:  //proMake
					for (Product product: supplier.getSupProducts()) {
						int productCounter = 0;
						if (product.getProMake().equals(proMake)) {
							productCounter += 1;
							printSupplierAndProductDetails(supplierCounter, productCounter, supplier, product);
							supplierCounter += 1;
						}
					}
					break;
			case 3:  //proModel
					for (Product product: supplier.getSupProducts()) {
						if (product.getProModel().equals(proModel)) {
							int productCounter = -1;
							printSupplierAndProductDetails(supplierCounter, productCounter, supplier, product);
							supplierCounter += 1;
						}
					};
					break;
			case 4:  //proPrice
					for (Product product: supplier.getSupProducts()) {
						int productCounter = 0;
						if (product.getProPrice() == proPrice) {
							productCounter += 1;
							printSupplierAndProductDetails(supplierCounter, productCounter, supplier, product);
							supplierCounter += 1;
						}
					};
					break;
			case 5:   //In Stock
					for (Product product: supplier.getSupProducts()) {
						int productCounter = 0;
						if (product.getProQtyAvailable() != 0) {
							productCounter += 1;
							printSupplierAndProductDetails(supplierCounter, productCounter, supplier, product);
							supplierCounter += 1;
						}
					};
					break;
			case 6:  //out of stock
					for (Product product: supplier.getSupProducts()) {
						int productCounter = 0;
						if (product.getProQtyAvailable() == 0) {
							productCounter += 1;
							printSupplierAndProductDetails(supplierCounter, productCounter, supplier, product);
							supplierCounter += 1;
						}
					};
					break;
			case 7:  //Discontinued
					for (Product product: supplier.getSupProducts()) {
						int productCounter = 0;
						if (product.isProDiscontinued() == true) {
							productCounter += 1;
							printSupplierAndProductDetails(supplierCounter, productCounter, supplier, product);
							supplierCounter += 1;
						}
					};
					break;
			}
		}
		
		if (supplierCounter == 0) {
			switch (productVariable) {
			case 1: System.out.println("No Product with product code: " + proCode + " was found");;break;
			case 2: System.out.println("No Product with make: " + proMake + " was found");;break;
			case 3: System.out.println("No Product with model: " + proModel + " was found");;break;
			case 4: System.out.println("No Product with price: " + proPrice + " was found");break;
			case 5: System.out.println("No products in stock were found");break;
			case 6: System.out.println("All products are in stock");break;
			case 7: System.out.println("No discontinued products were found");break;
			}
		}
		afterSearchProcessIsComplete();
		
	}
	
	/**
	 * Prints the suppliers details and the products details
	 * @param supplierCounter 
	 * @param productCounter
	 * @param supplier
	 * @param product
	 */
	private static void printSupplierAndProductDetails(int supplierCounter, int productCounter, Supplier supplier, Product product) {
		if (productCounter == 1) {
			System.out.println("-----------------\n" + (supplierCounter + 1) + ". " + supplier.supplierDetails());
		}
		
		if (supplierCounter == 0) {
			System.out.println("\n\t----PRODUCTS BY: " + supplier.getSupName().toUpperCase() + "----");
		}
		if (productCounter != -1) {
			System.out.println("\n\t" + productCounter + ". " + product.getProductDetailsIndented() + "\n\t---------------------------");
		} else {
		System.out.println("\n\t" + product.getProductDetailsIndented() + "\n\t---------------------------");
		}
	}
	
	/**
	 * Menu for after a user has performed a search
	 */
	private static void afterSearchProcessIsComplete() {
		String consoleMessage = "Select An Option: " 
				+ "\n1. Open Supplier Options"
				+ "\n2. Open Product Options"
				+ "\n3. Back To Main Menu";
		
		int menuOptionResponse = Validation.consoleMenu(consoleMessage, 3); //Sets variable equal to validated uer input
		
		switch (menuOptionResponse) {
		case 1: supplierMenu(Validation.validateAndStoreInt("Input Supplier Code: "));break; //Supplier options
		case 2: productMenu(Validation.validateAndStoreInt("Input Product Code: "));break; //Product options
		case 3: return;
		}
	}
	
	
	/*
	 * This is product's after search methods
	 */
	
	/**
	 * This is the menu for after a product has been successfully searched and selected
	 * @param proCode - The product code
	 */
	private static void productMenu(int proCode) {
		int[] indexs = findProduct(proCode); //indexs[0] = supIndex, indexs[1] = proIndex
		
		String consoleMessage = "----PRODUCT MENU----"
				+ "\nWorking with: " + Part02Tester.supplierList.get(indexs[0]).getSupProducts().get(indexs[1]).getProMake() 
				+ " " + Part02Tester.supplierList.get(indexs[0]).getSupProducts().get(indexs[1]).getProModel()
				+ "\nMenu Options: "
				+ "\n1. Produce Quote"
				+ "\n2. Order More Stock"
				+ "\n3. Edit Product"
				+ "\n4. Delete Product"
				+ "\n5. Back To Main Menu";
		
		int menuOptionResponse = Validation.consoleMenu(consoleMessage, 5); //Sets variable equal to user input
		
		switch (menuOptionResponse) { 
		case 1:  produceQuote(indexs[0], indexs[1]);break; //Runs the method to add a supplier
		case 2: orderMoreStock(indexs[0], indexs[1]);break; //Runs the method to add a new product to a existing supplier
		case 3: EditEntries.editProductFromSearch(indexs[0], indexs[1]);break; //Runs method to edit productr
		case 4: deleteProduct(indexs[0], indexs[1]);break; //Runs method to deleted product
		case 5: return; 
		}
	}
	
	/**
	 * Finds the indexs of a product
	 * @param proCode - the products unique code
	 * @return indexs[0] = supIndex, indexs[1] = proIndex
	 */
	public static int[] findProduct(int proCode) {
		int[] indexs = new int[2];
		indexs[0] = 0; //This is the supplier index
		indexs[1] = 0; //This is the product index
		boolean loop = true;
		do {
		for (Supplier supplier: Part02Tester.supplierList) { //Loops through each supplier in the application
			for (Product product: supplier.getSupProducts()) { //Loops through each product in thet supplier
				indexs[1] = 0; 
				if (product.getProCode() == proCode) { //When the product code input by user is matching to a product, its indexs are return
					return indexs;
				}
				indexs[1] += 1;
			}
			indexs[0] += 1;
		}
		System.out.println("No product matching product code: " + proCode + " was found");
		proCode = Validation.validateAndStoreInt("Please input a valid product code");
		} while (loop == true);
		return null;
	}
	
	/**
	 * Produce quote when supplier and product are knoew
	 * @param supIndex - Supplier index in supplierList
	 * @param proIndex - Product index in the supplier's product list
	 */
	private static void produceQuote(int supIndex, int proIndex) {
		double price = Part02Tester.supplierList.get(supIndex).getSupProducts().get(proIndex).getProPrice(); //Sets price equal to the products price
		int quantity = Validation.validateAndStoreInt("Price per one unit: " + price + "\nInput desired quantity: ");
		double quotePrice = price * (double)quantity;
		System.out.println(quotePrice);
		boolean orderQuote = Validation.validateAndStoreBoolean("Do you wish to place this order? ");
		if (orderQuote == true) {
			Part02Tester.supplierList.get(supIndex).getSupProducts().get(proIndex).addQuantity(quantity);
			System.out.println("----ORDER PLACED----");
		}
	}
	
	private static void orderMoreStock(int supIndex, int proIndex) {
		double price = Part02Tester.supplierList.get(supIndex).getSupProducts().get(proIndex).getProPrice();
		int quantity = Validation.validateAndStoreInt("Price per one unit: " + price + "\nInput desired quantity: ");
		Part02Tester.supplierList.get(supIndex).getSupProducts().get(proIndex).addQuantity(quantity);
		System.out.println("ORDER COST: " + (price * (double)quantity) +  "\n----ORDER PLACED----");

	}
	
	private static void deleteProduct(int supIndex, int ProIndex) {
		boolean deleteProductRequest = Validation.validateAndStoreBoolean("----CONFIRM DELETION OF PRODUCT----" + Part02Tester.supplierList.get(supIndex).getSupProducts().get(ProIndex).getProductDetailsIndented());
		if (deleteProductRequest == true) {
			Part02Tester.supplierList.get(supIndex).getSupProducts().remove(ProIndex);
			System.out.println("----PRODUCT DELETED----");
		} else {
			System.out.println("----DELETION PROCESS CANCELLED----");
		}
	}
	
	
	/*
	 * This is supplier methods
	 */
	private static void supplierMenu(int supCode) {
		int supIndex = findSupplier(supCode);
		
		String consoleMessage = "----SUPPLIER MENU----" 
				+ "\nWorking with: " + Part02Tester.supplierList.get(supIndex).getSupName()
				+ "\nMenu Options: "
				+ "\n1. Edit Supplier"
				+ "\n2. Delete Supplier"
				+ "\n3. Back To Main Menu";
		
		int menuOptionResponse = Validation.consoleMenu(consoleMessage, 3);
		switch (menuOptionResponse) {
		case 1: EditEntries.editSupplierFromSearch(supIndex);break;
		case 2: deleteSupplier(supIndex);break;
		case 3: return;
		}
	}

	private static int findSupplier(int supCode) {
		boolean loop = true;
		int i = 0;
		do {
		for (Supplier supplier: Part02Tester.supplierList) {
			if (supplier.getSupCode() == supCode) {
				return i;
			}
			i += 1;
		}
		System.out.println("No supplier matching supplier code: " + supCode + " was found");
		supCode = Validation.validateAndStoreInt("Please input a valid supplier code");
		} while (loop == true);
		return -1;
	}
	
	private static void deleteSupplier(int supIndex) {
		boolean deleteSupplierRequest = Validation.validateAndStoreBoolean("----CONFIRM DELETION OF SUPPLIER----" + Part02Tester.supplierList.get(supIndex).supplierDetails());
		if (deleteSupplierRequest == true) {
			Part02Tester.supplierList.remove(supIndex);
			System.out.println("----SUPPLIER DELETED----");
		} else {
			System.out.println("----DELETION PROCESS CANCELLED----");
		}
	}
}
