package part02;
/**
 * This class holds the print main menu and all the methods assocaited with printing functions
 * @author Michael Gilroy 40203084
 *
 */
public class Print {

	public static void main(String[] args) {
		String consoleMessage = "\n----PRINT MENU----\nMenu Options: "
				+ "\n1. Print All Suppliers"
				+ "\n2. Print All Products"
				+ "\n3. Print All Products Low In Stock"
				+ "\n4. Print All Discontinued Products"
				+ "\n5. Return To Main Menu";
		
		int menuOptionResponse = Validation.consoleMenu(consoleMessage, 5);
		
		switch (menuOptionResponse) {
		case 1: printAllSuppliers();break;
		case 2: printAllProducts();break;
		case 3: printLowOnStock();break;
		case 4: printDiscontinued();break;
		case 5: return;
		}
		
		main(args);

	}
	
	private static void printAllSuppliers() {
		System.out.println("\n----SUPPLIER LIST----");
		for (Supplier supplier: Part02Tester.supplierList) {
			System.out.println();
			System.out.println(supplier.supplierDetails());
		}
	}
	
	/**
	 * Prints all the products offered by a supplier
	 */
	private static void printAllProducts() {
		//Loops through each supplier in the ArrayList supplierList
		for (Supplier supplier: Part02Tester.supplierList) {
			System.out.println(); //Prints a empty line on the console
			supplier.printProductsList(); //Runs a method in the supplier object instance that prints the suppliers product details
		}
	}
	
	private static void printLowOnStock() {
		int counter = 0;
		
		//Loops through each supplier in the ArrayList supplierList
		for (Supplier supplier: Part02Tester.supplierList) {
			System.out.println(); //Prints a empty line on the console
			for (Product product: supplier.getSupProducts()) {
				if (product.getProQtyAvailable() <= 5) {
					if (counter == 0) {
						System.out.println("----LOW ON STOCK PRODUCTS----");
					}
					counter += 1;
					
					if (product.getProQtyAvailable() == 0) {
						System.out.println("\n*OUT OF STOCK*\n" + counter + ". " + product.getProductDetails());
						continue;
					}
					System.out.println("\n" + counter + ". " + product.getProductDetails());
				}
			}
		}
	}
	
	private static void printDiscontinued() {
		int counter = 0;
		
		//Loops through each supplier in the ArrayList supplierList
		for (Supplier supplier: Part02Tester.supplierList) {
			System.out.println(); //Prints a empty line on the console
			for (Product product: supplier.getSupProducts()) {
				if (product.isProDiscontinued() == true) {
					if (counter == 0) {
						System.out.println("----DISCONTINUED PRODUCTS----");
					}
					counter += 1;
					System.out.println("\n" +  counter + ". " + product.getProductDetails());
				}
			}
		}
		
		if (counter == 0) {
			System.out.println("\n----NO DISCONTINUED PRODUCTS FOUND----\n");
		}
	}
}
