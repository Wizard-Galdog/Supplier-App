package part02;
import java.util.ArrayList;

/**
 * This is the main class of the application
 * @author Michael Gilroy 40203084
 *
 */
public class Part02Tester {
	
	public static ArrayList<Supplier> supplierList = TesterObjects.mainTesterObjects(); //Initiates a list of suppliers, filled with default tester suppliers
	
	public static void main(String[] args) {
		orderSupplierList();
		System.out.println("\n****Type 'HELP' into the console at anytime to access the help menu****\n");
		
		String consoleMessage = "----MAIN MENU----\nMenu Options: "
				+ "\n1. Search"
				+ "\n2. Print Menu"
				+ "\n3. Stock Check"
				+ "\n4. Order Quote"
				+ "\n5. Add New Entry To Application"
				+ "\n6. Edit An Existing Entry"
				+ "\n7. Delete An Existing Entry"
				+ "\n8. Exit Application";
		
		int menuOptionResponse = Validation.consoleMenu(consoleMessage, 8);
		//Switch statement based on the integer value of the menu option input by the user
		switch (menuOptionResponse) {
		case 1: Search.main(args);break;//Runs the Search classes main method
		case 2: Print.main(args);break; //Runs the Print classes main method
		case 3: stockCheck();break; //Runs the stockCheck method
		case 4: produceQuote();break; //Runs the produceQuote method
		case 5: AddEntry.main(args);break; //Runs the AddEntry classes main method
		case 6: EditEntries.main(args);break; //Runs the EditEntries classes main method
		case 7: DeleteEntry.main(args);break; //Runs the DeleteEntry classes main method 
		case 8: System.out.println("Application Closing...\nApplication Closed");return; //Exits the application
		}
		
		main(args); //recursively runs the main method
	}
	
	/**
	 * Orders the supplier's in the supplierList into alphabetical order
	 */
	private static void orderSupplierList() {
		for (int i = Part02Tester.supplierList.size(); i >=0; i--) {
			for (int j = 1; j < i; j++) {
				if (Part02Tester.supplierList.get(j-1).getSupName().charAt(0) > Part02Tester.supplierList.get(j).getSupName().charAt(0)) {
					Supplier temp = Part02Tester.supplierList.get(j-1);
					Part02Tester.supplierList.set(j-1, Part02Tester.supplierList.get(j));
					Part02Tester.supplierList.set(j, temp);
				}
			}
		}
		orderProducts();
	}
	
	/**
	 * Orders the products of each supplier by the proCode
	 */
	private static void orderProducts() {
		for (Supplier supplier: Part02Tester.supplierList) {
			for (int i = supplier.getSupProducts().size(); i >=0; i--) {
				for (int j = 1; j < i; j++) {
					if (supplier.getSupProducts().get(j-1).getProCode() > supplier.getSupProducts().get(j).getProCode()) {
						Product temp = supplier.getSupProducts().get(j-1);
						supplier.getSupProducts().set(j-1, supplier.getSupProducts().get(j));
						supplier.getSupProducts().set(j, temp);
					}
				}
			}
		}
	}
	
	/**
	 * Produce quote when the supplier and product are unknown 
	 */
	private static void produceQuote() {
		int indexs[] = Search.findProduct(Validation.validateAndStoreInt("Input Product Code: ")); //indexs[0] = supIndex, indexs[1] = proIndex
		double price = Part02Tester.supplierList.get(indexs[0]).getSupProducts().get(indexs[1]).getProPrice(); //Sets variable equal to the price of the product
		int quantity = Validation.validateAndStoreInt("Price per one unit: " + price + "\nInput desired quantity: "); //Sets quantity equal to the user input integer of stock
		double quotePrice = price * (double)quantity; //Calculates the price of the order
		System.out.println("£" + quotePrice); //Prints order price
		boolean orderQuote = Validation.validateAndStoreBoolean("Do you wish to place this order? "); //Asks user to confirm order 
		if (orderQuote == true) { //Runs if the user confirmed the order
			Part02Tester.supplierList.get(indexs[0]).getSupProducts().get(indexs[1]).addQuantity(quantity); //Adds the quantity just ordered to the already existing quantity in stock
			System.out.println("----ORDER PLACED----");
		}
	}
	
	/**
	 * Produces quote for the user when the supplier and product are known
	 * @param supIndex - The suppliers index in the supplierList
	 * @param proIndex - The products index in the suppliers productList
	 */
	private static void produceQuote(int supIndex, int proIndex) {
		double price = Part02Tester.supplierList.get(supIndex).getSupProducts().get(proIndex).getProPrice(); //Sets price equal to the product price
		int quantity = Validation.validateAndStoreInt("Price per one unit: " + price + "\nInput desired quantity: "); //Sets variable to the user input
		double quotePrice = price * (double)quantity;
		System.out.println("£" + quotePrice);
		
		boolean orderQuote = Validation.validateAndStoreBoolean("Please confirm order?"); //Prompts user to confirm order
		if (orderQuote == true) { //Runs if user confirmed order
			Part02Tester.supplierList.get(supIndex).getSupProducts().get(proIndex).addQuantity(quantity); //Add's quantity to products existing quantity
			System.out.println("----ORDER PLACED----");
		}
	}
	
	/**
	 * This method allows user to check the stock of a product
	 */
	private static void stockCheck() {
		int indexs[] = Search.findProduct(Validation.validateAndStoreInt("Input Product Code: "));//indexs[0] = supIndex, indexs[1] = proIndex
		System.out.println(Part02Tester.supplierList.get(indexs[0]).getSupProducts().get(indexs[1]).getProMake() //Prints make, model, quantity in stock
				+ " "
				+ Part02Tester.supplierList.get(indexs[0]).getSupProducts().get(indexs[1]).getProModel()
				+ ", Stock = "
				+ Part02Tester.supplierList.get(indexs[0]).getSupProducts().get(indexs[1]).getProQtyAvailable());
		
		boolean orderMoreStock = Validation.validateAndStoreBoolean("Do you wish to place a order quote"); //Asks user if they wish to order morestock
		if (orderMoreStock == true) { //If user wants to order more stock, this code run
			produceQuote(indexs[0], indexs[1]);
		}
	}
	
}
