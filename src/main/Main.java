package main;

import java.util.Scanner;
import java.util.Stack;

import ShippingCompany.CountryAndTax;
import ShippingCompany.ShippingManagement;

public class Main {
	public final static int DOLLAR_RATE = 4;
	public final static String USD = "USD";
	public final static String ILS = "ILS";
	
	private static Scanner s = new Scanner(System.in);
	private static StoreManagement storeManagement = StoreManagement.getStoreManagement();
	private static Stack<StoreManagement.Memento> backupStack = new Stack<>();
	
	private static CountryAndTax countryAndTax1 = new CountryAndTax("UK");
	private static CountryAndTax countryAndTax2 = new CountryAndTax("USA");
	private static CountryAndTax countryAndTax3 = new CountryAndTax("France");

	
	public static void main(String[] args) throws Exception {
		char choice = 0;		
		while (choice != 'e' && choice !=  'E'){
			try {
				printMenu();
				choice = s.next().charAt(0);
				
				switch (Character.toLowerCase(choice)) {
				case 'a':
					addHardCodedCountryAndTaxToArray();
					storeManagement.automaticStoreFill();
					saveStoreManagement(storeManagement);
					System.out.println("The store was automatically filled succssefully!");
					break;
				case 'b':
					saveStoreManagement(storeManagement);
					storeManagement.addProductToStore();
					System.out.println("The process of adding a product to store has finished!");
					break;
				case 'c':
					saveStoreManagement(storeManagement);
					storeManagement.removeProductFromStore();
					System.out.println("The process of removing a product from store has finished!");
					break;
				case 'd':
					saveStoreManagement(storeManagement);
					storeManagement.updateStockOfProductFromStore();
					System.out.println("The process of updating the stock of a product from store has finished!");
					break;
				case 'f':
					saveStoreManagement(storeManagement);
					storeManagement.addOrderToProduct();
					System.out.println("The process of adding an order to a product has finished!");
					break;
				case 'g':
					saveStoreManagement(storeManagement);
					storeManagement.undoLastOrder();
					System.out.println("The process of cancelling an order has finished!");
					break;
				case 'h':
					storeManagement.getProductDetails();
					System.out.println("The process of getting all product's details has finished!");
					break;
				case 'i':
					storeManagement.getAllProductsDetails();
					System.out.println("The process of presenting all products in store and threir details has finished!");
					break;
				case 'j':
					storeManagement.getOrdersOfProduct();
					System.out.println("The process of presenting all order of a product has finished!");
					break;
				case 'k':
					restoreLastBackup(storeManagement);
					System.out.println("The process of returning to the previous backed up version has finished!");
					break;
				case 'e':
					System.out.println("Exiting from program :(");
					break;
				default:
					System.out.println("Wrong option");
				}
			}
			catch (NumberFormatException e) {
				System.out.println("Invalid input");
			}
			// catch for general exception cases 
			catch (Exception e) { 
				System.out.println(e.getMessage());
			}
		}
}
		
	public static void printMenu() {
		System.out.println("---------------------------------------------------");
		System.out.println("Choose option:");
		System.out.println("a)  4.1 - Fill store with initial data");
		System.out.println("b)  4.2 - Add product to store");
		System.out.println("c)  4.3 - Remove product from store");
		System.out.println("d)  4.4 - Update stock for product");
		System.out.println("f)  4.5 - Add order for product");
		System.out.println("g)  4.6 - Undo last order");
		System.out.println("h)  4.7 - Get all product details by catalog number");
		System.out.println("i)  4.8 - Present all products in store and their details");
		System.out.println("j)  4.9 - Print all orders of specified product");
		System.out.println("k) 4.10 - Set system's data to last backup");
		System.out.println("e/E)  Exit");
	}
	
	public static void addHardCodedCountryAndTaxToArray() {
		ShippingManagement shippingManagement = storeManagement.getShippingManagementFromStoreManagements();
		shippingManagement.addCountryAndTax(countryAndTax1);
		shippingManagement.addCountryAndTax(countryAndTax2);
		shippingManagement.addCountryAndTax(countryAndTax3);
	}

	
	public static void restoreLastBackup(StoreManagement storeManagement) {
		if(!backupStack.isEmpty()) {
			storeManagement.setMemento(backupStack.pop());
		}
	}
	
	public static void saveStoreManagement(StoreManagement storeManagement) {
		backupStack.push(storeManagement.createMemento());
	}
}
