package part02;
/**
 * USING OLD SPEC
 * This class models a address 
 * @author Michael Gilroy 40203084
 *
 */
public class Address {
	private int bldNum;
	private String bldStreet;
	private String bldTown;
	private String bldPcode;
	private String bldCountry;
	
	/**
	 * This is the constructor method for the address object
	 * It sets up the house number, street, town, postcode and country into a address object
	 * @param bldNum - House number
	 * @param bldStreet - Street name
	 * @param bldTown - Town name
	 * @param bldPcode - Postcode
	 * @param bldCountry - Country
	 */
	public Address(int bldNum, String bldStreet, String bldTown, String bldPcode, String bldCountry) {
		this.setBldNum(bldNum);
		this.setBldStreet(bldStreet);
		this.setBldTown(bldTown);
		this.setBldPcode(bldPcode);
		this.setBldCountry(bldCountry);
	}
	
	/**
	 * Returns a string that is in the format of the full address, containing the variables of the object instances
	 * @return - Full address
	 */
	public String getFullAddress() {
		String fullAddress = "Address Details: \n"
				+ bldNum + " " + bldStreet
				+ "\n" + bldTown
				+ "\n" + bldPcode
				+ "\n"  + bldCountry;
		return fullAddress;
	}
	
	
	/**
	 * This method returns the house number in the address
	 * @return - House number
	 */
	public int getBldNum() {
		return bldNum;
	}
	
	/**
	 * This method returns the street address assigned to the address
	 * @return - Street address
	 */
	public String getBldStreet() {
		return bldStreet;
	}
	
	/**
	 * This method returns the town assigned to the address
	 * @return - Town
	 */
	public String getBldTown() {
		return bldTown;
	}
	
	/**
	 * This method returns the postcode in the address
	 * @return - Address postcode
	 */
	public String getBldPcode() {
		return bldPcode;
	}
	public String getBldCountry() {
		return bldCountry;
	}

	/**
	 * This method sets a new house number for the bldNum instance variable of the address object
	 * @param bldNum - The new house number
	 */
	public void setBldNum(int bldNum) {
		this.bldNum = bldNum;
	}

	/**
	 * This method sets a new street for the bldStreet instance variable of the address object
	 * @param bldStreet - the new street name
	 */
	public void setBldStreet(String bldStreet) {
		this.bldStreet = bldStreet;
	}

	/**
	 * This method sets a new town for the bldTown instance variable of the address object
	 * @param bldTown - the new town name
	 */
	public void setBldTown(String bldTown) {
		this.bldTown = bldTown;
	}

	/**
	 * This method sets a new postcode for the bldPcode instance variable of the address object
	 * @param bldPcode - the new postcode
	 */
	public void setBldPcode(String bldPcode) {
		this.bldPcode = bldPcode;
	}

	/**
	 * This method sets a new country for the bldCountry instance variable of the address object
	 * @param bldCountry - the new address
	 */
	public void setBldCountry(String bldCountry) {
		this.bldCountry = bldCountry;
	}
	
	
	
	
}
