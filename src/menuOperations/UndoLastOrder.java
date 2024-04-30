package menuOperations;

import main.StoreManagement;
import orders.Order;
import products.Product;

public class UndoLastOrder implements ICommandUndo{
	private static StoreManagement storeManagement = StoreManagement.getStoreManagement();

	@Override
	public void execute() {
		if(!checkIfAllOrdersMadeIsEmpty()) {
			System.out.println("There are no orders to remove");
			return;
		}

		Order removedOrder = storeManagement.removeLastAddedOrderFromDataBaseOrders(); // remove last order from orders database
		updateStock(removedOrder); // update new stock due to undo operation
		removeOrderFromProductOrdersArray(removedOrder); // remove order from the product's orders array
		printCancellationMessageToCustomer(removedOrder);
	}
	
	public boolean checkIfAllOrdersMadeIsEmpty() {
		if(storeManagement.getAllOrdersMade().isEmpty()) {
			return false; // empty
		}
		return true; // not empty
	}
		
	public void updateStock(Order removedOrder) {
		Product product = removedOrder.getProduct();
		int stockBeforeUpdate = product.getStock();
		int stockInOrder = removedOrder.getAmount();
		
		product.setStock(stockBeforeUpdate + stockInOrder);
	}
	
	public void removeOrderFromProductOrdersArray(Order removedOrder) {
		Product product = removedOrder.getProduct();
		product.getAllOrders().remove(removedOrder);
	}
	
	public void printCancellationMessageToCustomer(Order removedOrder) {
		System.out.println("The following order you have made was cancelled due to storage issues!");
		System.out.println(removedOrder.toString());
	}
}
