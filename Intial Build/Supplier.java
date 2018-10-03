package part01;
import java.util.ArrayList;
/**
 * USING OLD SPEC
 * This class models a supplier
 * @author Michael Gilroy 40203084
 *
 */
public class Supplier {
	private int supCode;
	private String supName;
	private Address supAddress;
	private SupRegion supRegion;
	private ArrayList<Product> supProducts;
	
	/**
	 * This is the constructor for the Supplier object
	 * It sets up the supplier code, supplier name, supplier address, supplier region, supplier products into a supplier object
	 * @param supCode - Supplier code
	 * @param supName - Supplier name
	 * @param supAddress - Supplier address
	 * @param supRegion - Supplier region
	 * @param supProducts - Supplier products
	 */
	public Supplier(int supCode, String supName, Address supAddress, SupRegion supRegion,
			ArrayList<Product> supProducts) {
		this.setSupCode(supCode);
		this.setSupName(supName);
		this.setSupAddress(supAddress);
		this.setSupRegion(supRegion);
		this.setSupProducts(supProducts);
	}
	
	/**
	 * Prints all the products sold by a supplier
	 */
	public void printProductsList() {	
		System.out.println("----PRODUCTS BY " + supName.toUpperCase() + "----");
		//Loops through all the items in the supProducts object instance and prints them
		System.out.println();
		
		if (supProducts.size() == 0) { //Checks there is products to print for this supplier
			System.out.println("THIS SUPPLIER HAS NO PRODUCTS");
			return;
		}
		
		for (Product item : supProducts) {
			System.out.println(item.getProductDetails());
		}
	}
	
	/**
	 * This method adds a new product to the ArrayList<product> that stores all the existing products for a supplier
	 * @param newProduct - The new product to be added to the suppplier's product list
	 */
	public void addAnotherProduct(Product newProduct) {
		supProducts.add(newProduct);
	}
	
	/**
	 * This method returns the supplier's address in full string format
	 * @param supplier - the supplier object
	 * @return - Supplier address in string format
	 */
	public String getSupAddress(Supplier supplier) {
		return supplier.supAddress.getFullAddress();
	}
	
	/**
	 * This method returns the supplier code in the supplier object instance
	 * @return - Supplier code
	 */
	public int getSupCode() {
		return supCode;
	}
	
	/**
	 * This method returns the supplier name in the supplier object instance
	 * @return - Supplier Name
	 */
	public String getSupName() {
		return supName;
	}
	
	/**
	 * This method returns the supplier address object in the supplier object instance
	 * @return - Supplier Address
	 */
	public Address getSupAddress() {
		return supAddress;
	}
	
	/**
	 * This method returns the supplier region in the supplier object instance
	 * @return - Supplier Region
	 */
	public SupRegion getSupRegion() {
		return supRegion;
	}
	
	/**
	 * This method returns a array list filled with product objects that are sold by this supplier instance
	 * @return - Products sold by the Supplier
	 */
	public ArrayList<Product> getSupProducts() {
		return supProducts;
	}

	
	/**
	 * This method sets a new supplier code for the supCode instance variable of the supplier object
	 * @param supCode - The new supplier code
	 */
	public void setSupCode(int supCode) {
		this.supCode = supCode;
	}

	/**
	 * This method sets a new supplier name for the supName instance variable of the supplier object
	 * @param supName - The new supplier name
	 */
	public void setSupName(String supName) {
		this.supName = supName;
	}

	/**
	 * This method sets a new supplier Address for the supAddress instance variable of the supplier object
	 * @param supAddress - The new supplier address object
	 */
	public void setSupAddress(Address supAddress) {
		this.supAddress = supAddress;
	}

	/**
	 * This method sets a new supplier region for the supRegion instance variable of the supplier object
	 * @param supRegion - The new supplier region
	 */
	public void setSupRegion(SupRegion supRegion) {
		this.supRegion = supRegion;
	}

	/**
	 * This method sets a new supplier product list to the supProducts instance variable of the supplier object
	 * @param supProducts - The new list of products sold by the supplier
	 */
	public void setSupProducts(ArrayList<Product> supProducts) {
		this.supProducts = supProducts;
	}
	
	
	
	
}
