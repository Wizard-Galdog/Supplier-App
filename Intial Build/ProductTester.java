package part01;

import java.util.Random;

/**
 * This is the product tester class
 * @author Michael Gilroy 40203084
 *
 */
public class ProductTester {

	/**
	 * Prints out 2 instances of the product object
	 * @param args
	 */
	public static void main(String[] args) {
		Product productTester1 = new Product(8, "HTC", "VIVE", 545.59, true); //creates instance of product object using constructor
		System.out.println(productTester1.getProductDetails());

		Product productTester2 = addNewProduct();//Creates instance of product object using user inputs
		System.out.println(productTester2.getProductDetails());
	}
	
	/**
	 * This method prompts the user for product details and stores the inputs in a product object
	 * @return - New product object
	 */
	private static Product addNewProduct() {
		System.out.println("----Product Details----"
			+ "\nPlease enter the following details for the new product");
		Random rndProCode = new Random(); //Randomly generates a product code from 1 to 50001
		int proCode = 1 + rndProCode.nextInt(50000);
		
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
		
		newProduct.setProDiscontinued(Validation.validateAndStoreBoolean("Product discontinued?\nPlease enter 'true' or 'false'")); //Sets product discounted boolean value equal to user input
		
		return newProduct; //Returns the product object to caller
		}

}
