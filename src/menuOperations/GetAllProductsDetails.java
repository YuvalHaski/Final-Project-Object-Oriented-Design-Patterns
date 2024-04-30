package menuOperations;

import main.StoreManagement;
import products.Product;

public class GetAllProductsDetails implements ICommandGetAllProductsDetails{
	private static StoreManagement storeManagement = StoreManagement.getStoreManagement();

	@Override
	public void execute() {
		double totalProfitFromAllProductsInStore = 0;
		
		for (Product product : storeManagement.getAllProductsInStore()) {
			System.out.println("Product type: " + product.getClass().getSimpleName());
			System.out.println(product.toString());
			System.out.println("Total profit from all orders: " + storeManagement.getTotalProfitFromAllOrderOfProduct(product)+ "\n");
			
			totalProfitFromAllProductsInStore += storeManagement.getTotalProfitFromAllOrderOfProduct(product);
		}
		
		System.out.println("Total profit from all products and orders in store: " + totalProfitFromAllProductsInStore);
	}
}
