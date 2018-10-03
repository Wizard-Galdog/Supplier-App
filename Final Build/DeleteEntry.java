package part02;

import java.util.ArrayList;
/**
 * This class is responsible for deleting entries from the application
 * @author Michael Gilroy 40203084
 *
 */
public class DeleteEntry {

	/**
	 * This method is the main menu for the delete entry process of the application
	 * @param args
	 */
	public static void main(String[] args) {
		String consoleMessage = "----DELETING MENU----\nSelect An Entry Option To Delete: "
				+ "\n1. Delete Supplier"
				+ "\n2. Delete Product"
				+ "\n3. Back To Main Menu";
		
		int menuOptionResponse = Validation.consoleMenu(consoleMessage, 3); //Sets  integer equal to the user input for the 1st level of the edit entry menu
		
		switch (menuOptionResponse) { //Switch statement based on the integer value of the menu option input by the user 
		case 1:  deleteSupplier();break; //Runs the method to delete a supplier
		case 2: deleteProduct();break; //Runs the method to edit a product to a existing supplier
		case 3: return; //Returns to the main menu
		default: System.out.println("An unexpected error has occured\nThe application will close in:\n3\n2\n1\nClosing...\nClosed");return;
		}

	}
	
	/**
	 * This method is the main method responsible for deleting a supplier object from the application
	 */
	private static void deleteSupplier() {
		boolean deletionResponse = false; //Initialises boolean variable,declared outside loop because it is used as a condition for the loop
		
		do {
			int supplierIndex = printAndSelectSuppliers(); //Sets supplierIndex variable equal to the value returned by the printAndSelectSuppliers() Method
			
			System.out.println("---Supplier Selected---\n" //prints selected suppliers details
					+ Part02Tester.supplierList.get(supplierIndex).supplierDetails()); 
			
			deletionResponse = Validation.validateAndStoreBoolean("Do you wish to delete this supplier?\nThis will remove all products associated with the supplier");//Prompts user to confirm the deletion process
			
			if (deletionResponse == true) { //DeletionResponse is true, if the user decided to continue with the deletion
				Part02Tester.supplierList.remove(supplierIndex); //Deletes the supplier
				System.out.println("SUPPLIER DELETED"); //Prints message confirming the supplier was deleted
				return; //Used to stop execution of the method
			} else { //This code runs if the DeletionResponse is false, this means the deletion process is cancelled
				System.out.println("SUPPLIER DELETION PROCESS CANCELLED"); //Prints message confirming the supplier wasnt deleted
				int changeSupplier = Validation.consoleMenu("Do you wish to: " //Prompts user to choose a option
						+ "\n1. Change supplier"
						+ "\n2. Cancel deletion process", 2);
				
				switch (changeSupplier) {
				case 1: continue; //Loop runs again
				case 2: return; //Used to stop execution of the method
				}
			}
		}while (deletionResponse == false);
	}
	
	/**
	 * This method prints a list of the suppliers in the application
	 * The user then selects a supplier they want to work with(In this case, delete)
	 * @return - The index of the supplier in the supplier arrayList
	 */
	private static int printAndSelectSuppliers() {
		System.out.println("Select a supplier: ");
		
		for (int i = 0; i < Part02Tester.supplierList.size();i++) { //Loops from 0 to the size of the supplierList-1
			System.out.println((i+1) + ". " + Part02Tester.supplierList.get(i).getSupName()); //Prints the suppliers name
		}
		
		int supplierIndex = 0; //Initialises int outside of loop
		
		/*
		 * Loops until the supplierIndex is between 1 and the supplierList size
		 */
		while ((supplierIndex < 1) || (supplierIndex > Part02Tester.supplierList.size())) { 
			supplierIndex = Validation.validateAndStoreInt("Input a integer between 1 and " + Part02Tester.supplierList.size()); //Sets supplierIndex equal to validated user input
		}
		return supplierIndex - 1; //Subtracts 1 since the index of an ArrayList starts at 0, returns the answer(index in the ArrayList)
	}

	/**
	 * This is the main method responsible for deleting a product instance from the application
	 */
	private static void deleteProduct() {
		int supplierIndex = printAndSelectSuppliers(); //Sets supplierIndex (This is needed to know which supplier the product belongs to) to the value returned by the printAndSelectSuppliers() method
		System.out.println("Products by : " + Part02Tester.supplierList.get(supplierIndex).getSupName()); //Prints all the products by the user selected supplier
		
		int productIndex = printAndSelectProducts(Part02Tester.supplierList.get(supplierIndex).getSupProducts()); //Sets productIndex equal to the value returned by the printAndSelectProducts() method
		
		Part02Tester.supplierList.get(supplierIndex).getSupProducts().remove(productIndex); //Removes the product from the application
		System.out.println("PRODUCT DELETED"); //Prints confirmation to the user that the product was deleted
	}
	
	/**
	 * This method prints a list of the products in the arrayList passed to it
	 * The user then selects a product they want to work with(In this case, delete)
	 * @param arrayListProducts - The arrayList of the products for the supplier being working with
	 * @return = The index of the product in the product arrayList
	 */
	private static int printAndSelectProducts(ArrayList<Product> arrayListProducts) {
		
		for (int i = 0; i < arrayListProducts.size(); i++) { //Loops from 0 to the size of the supplier's product list - 1
			System.out.println((i+1) + ". " + arrayListProducts.get(i).getProductDetails()); //Prints the products details
		}
		
		int productIndex = 0; //intialises the variable outside of the while loop
		
		/*
		 * Loops until the productIndex is between 1 and the supplier's product list
		 */
		while ((productIndex < 1) || (productIndex > arrayListProducts.size())) {
			productIndex = Validation.validateAndStoreInt("Select a product to delete\nInput a integer between 1 and " + arrayListProducts.size());//Sets productIndex equal to the validated user input
		}
		
		return productIndex - 1; //Subtracts 1 since the indexs of an ArrayList starts at 0, returns the answer(index in the ArrayList)
	}
}
