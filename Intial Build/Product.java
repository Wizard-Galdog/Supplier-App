package part01;
/**
 * USING OLD SPEC
 * This class models a product object
 * @author Michael Gilroy 40203084
 *
 */
public class Product {

	private int proCode;
	private String proMake;
	private String proModel;
	private double proPrice;
	private int proQtyAvailable;
	private boolean proDiscontinued;
	
	/**
	 * This is the constructor method for the product object
	 * It sets up the product code, make, model, price and weather its in stock
	 * @param proCode - Product code
	 * @param proMake - Product make
	 * @param proModel - Product model
	 * @param proPrice - Product price
	 * @param proInStock - Is product in stock
	 */
	public Product(int proCode, String proMake, String proModel, double proPrice, boolean proInStock) {
		this.setProCode(proCode);
		this.setProMake(proMake);
		this.setProModel(proModel);
		this.setProPrice(proPrice);
		this.setProQtyAvailable(proQtyAvailable);
		this.setProDiscontinued(proDiscontinued);
	}

	/**
	 * Returns the all the details for a given product object instance
	 * @return - Product details in string format
	 */
	public String getProductDetails() {
		String productDetails = "Product Code: " + proCode
				+ "\nProduct Make: " + proMake
				+ "\nProduct Model: " + proModel
				+ "\nProduct Price:" + String.format("£%.2f",proPrice)
				+ "\nProduct Quantity: " + proQtyAvailable
				+ "\nProduct Discontinued: " + proDiscontinued
				+ "\n";
		return productDetails;
	}
	
	/**
	 * This method returns the product code assigned to the product
	 * @return - Product code
	 */
	public int getProCode() {
		return proCode;
	}
	
	/**
	 * This method returns the product make assigned to the product
	 * @return - Product make
	 */
	public String getProMake() {
		return proMake;
	}
	
	/**
	 * This method returns the product model assigned to the product
	 * @return - Product model
	 */
	public String getProModel() {
		return proModel;
	}
	
	/**
	 * This method returns the product price assigned to the product
	 * @return - Product price
	 */
	public double getProPrice() {
		return proPrice;
	}
	
	/**
	 * This method returns the product quantity still in stock
	 * @return - Product quantity
	 */
	public int getProQtyAvailable() {
		return proQtyAvailable;
	}
	
	/**
	 * This method returns weather or not the product is discontinued
	 * @return - Product discontinued status
	 */
	public boolean isProDiscontinued() {
		return proDiscontinued;
	}

	
	/**
	 * This method sets a new product code for the product object instance
	 * @param proCode - The new product code
	 */
	public void setProCode(int proCode) {
		this.proCode = proCode;
	}

	/**
	 * This method sets a new product make for the product object instance
	 * @param proMake - The new product make
	 */
	public void setProMake(String proMake) {
		this.proMake = proMake;
	}

	/**
	 * This method sets a new product model for the product object instance
	 * @param proModel - The new product model
	 */
	public void setProModel(String proModel) {
		this.proModel = proModel;
	}

	/**
	 * This method sets a new product price for the product object instance
	 * @param proPrice - The new product price
	 */
	public void setProPrice(double proPrice) {
		this.proPrice = proPrice;
	}

	/**
	 * This method sets a new quantity in stock for the product object instance
	 * @param proQtyAvailable - The new quantity in stock
	 */
	public void setProQtyAvailable(int proQtyAvailable) {
		this.proQtyAvailable = proQtyAvailable;
	}

	/**
	 * This method sets a new true or false value for the discontinued status of product object instance
	 * @param proDiscontinued - The new product discontinued status
	 */
	public void setProDiscontinued(boolean proDiscontinued) {
		this.proDiscontinued = proDiscontinued;
	}
	
	
}
