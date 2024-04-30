package menuOperations;

import main.StoreManagement;
import orders.Order;
import products.Product;
import products.ProductSoldThroughWebsite;

public class GetOrdersOfProduct implements ICommandGetOrdersOfProduct{
	private static StoreManagement storeManagement = StoreManagement.getStoreManagement();

	@Override
	public void execute() {
		int index = storeManagement.pickProduct();
		Product chosenProduct = storeManagement.getAllProductsInStore().get(index);
		printAllOrdersForProduct(chosenProduct);
	}
	
	public void printAllOrdersForProduct(Product product) {
		int totalProfitFromAllOrders = 0;
		int profitPerUnit = checkIfCurrencyOfProductIsDollar(product);
		
		product.moveOrdersFromOrdersByCreationTimeStackToTempStack();
				
		while(!product.getTemp().isEmpty()) {
			Order order = product.getTemp().pop();
			product.addOrderToStack(order);
			int profitFromOrder = calculateProfitFromOrder(profitPerUnit, order);
			System.out.println("\n" + order.toString());
			System.out.println("\nProfit from order: " + profitFromOrder);
			totalProfitFromAllOrders += profitFromOrder;
		}
		
		
		System.out.println("Total profit from all orders of product: "+ totalProfitFromAllOrders);
	}
	
	public int calculateProfitFromOrder(int profitPerUnit, Order order) {
		return profitPerUnit * order.getAmount();
	}
	
	public int checkIfCurrencyOfProductIsDollar(Product product) {
		if(product instanceof ProductSoldThroughWebsite) {
			return storeManagement.changeCurrencyToILS(product.getProfitPerUnit());
		}
		return product.getProfitPerUnit();
	}
}
