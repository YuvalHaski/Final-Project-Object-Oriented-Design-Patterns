package menuOperations;

import java.util.Scanner;

import exceptions.GeneralException;
import main.StoreManagement;
import products.Product;

public class UpdateStockOfProduct implements ICommandUpdateStockOfProduct{
	private Scanner s = new Scanner(System.in);
	private static StoreManagement storeManagement = StoreManagement.getStoreManagement();
	
	@Override
	public void execute() {
		int productIndex = storeManagement.pickProduct();
		int newStock = getNewStockFromUser();
		
		Product product = storeManagement.getAllProductsInStore().get(productIndex);
		product.setStock(newStock);
	}
	
	public int getNewStockFromUser() {
		int stock = -1;
		while (stock < 0) {
			try {
				System.out.println("Enter the new stock of the product: ");
				stock = Integer.parseInt(s.nextLine().trim());
				
				if(stock < 0) {
					throw new GeneralException("Invalid stock amount");
				}
			} 
			catch (NumberFormatException e) {
				System.out.println("Invalid Input");
			}
			catch (GeneralException e) {
				System.out.println(e.getMessage());
			}
		}
		return stock;
	}
}
