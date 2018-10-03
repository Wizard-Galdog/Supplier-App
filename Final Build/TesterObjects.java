package part02;
import java.util.ArrayList;
/**
 * This class create tester instances of the supplier object and the objects within it
 * The instances are then returned to the calling method in a ArrayList<Supplier> data type
 * @author Michael Gilroy 40204084
 *
 */
public class TesterObjects {

	public static ArrayList<Supplier> mainTesterObjects() {
		Supplier supplierTester1 = createSupplierTester1();
		Supplier supplierTester2 = createSupplierTester2();
		ArrayList<Supplier> supplierListTester = new ArrayList<Supplier>();
		supplierListTester.add(supplierTester1);
		supplierListTester.add(supplierTester2);
		return supplierListTester;
	}
	
	/**
	 * This method creates a supplier tester instance
	 * @return - the first supplier tester instance
	 */
	private static Supplier createSupplierTester1() {
		Address addressTester1 = new Address(5, "Walton Drive", "Ascot", "SL5 7PG", "England");
		
		Product productTester1 = new Product(18967, "Samsung", "Galaxy S8", 669.00, true);
		Product productTester2 = new Product(17750, "Samsung", "Galaxy J3", 149.99, false);
		Product productTester3 = new Product(18265, "Samsung", "Galaxy J5", 239.99, true);
		Product productTester4 = new Product(28659, "Samsung", "Galaxy Note8", 869.00, true);
		
		/*
		 * The qty is only set here because during user creation of a product, the user is prompted for the quantity in stock
		 * Where as this is tester objects and hence quantity in stock must be set by the code
		 */
		productTester1.setProQtyAvailable(0);
		productTester2.setProQtyAvailable(8);
		productTester3.setProQtyAvailable(9);
		productTester4.setProQtyAvailable(2);
		
		ArrayList<Product> productListTester1 = new ArrayList<Product>();
		productListTester1.add(productTester1);
		productListTester1.add(productTester2);
		productListTester1.add(productTester3);
		productListTester1.add(productTester4);
		
		Supplier supplierTester1 = new Supplier(9034, "Samsung", addressTester1, SupRegion.UNITED_KINGDOM, productListTester1);
		return supplierTester1;
	}
	
	/**
	 * This method creates a supplier tester instance
	 * @return - the second supplier tester instance
	 */
	private static Supplier createSupplierTester2() {
		Address addressTester2 = new Address(1, "Victoria Square", "Belfast", "BT1 4QG", "Northern Ireland");
		
		Product productTester1 = new Product(17687, "Iphone", "7", 579.97, true);
		Product productTester2 = new Product(37934, "Iphone", "8+", 799.00, true);
		Product productTester3 = new Product(17546, "Iphone", "X", 999.00, true);
		Product productTester4 = new Product (27560, "Apple Watch", "Series 3", 329.00, true);
		
		/*
		 * The qty is only set here because during user creation of a product, the user is prompted for the quantity in stock
		 * Where as this is tester objects and hence quantity in stock must be set by the code
		 */
		productTester1.setProQtyAvailable(18);
		productTester2.setProQtyAvailable(11);
		productTester3.setProQtyAvailable(9);
		productTester4.setProQtyAvailable(22);
		
		ArrayList<Product> productListTester2 = new ArrayList<Product>();
		productListTester2.add(productTester1);
		productListTester2.add(productTester2);
		productListTester2.add(productTester3);
		productListTester2.add(productTester4);
		
		Supplier supplierTester2 = new Supplier(8475, "Apple", addressTester2, SupRegion.UNITED_KINGDOM, productListTester2);
		return supplierTester2;
	}
}
