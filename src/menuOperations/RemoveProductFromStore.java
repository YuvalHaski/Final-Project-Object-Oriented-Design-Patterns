package menuOperations;

import main.StoreManagement;


public class RemoveProductFromStore implements ICommandRemoveProductFromStore{
	private static StoreManagement storeManagement = StoreManagement.getStoreManagement();

	@Override
	public void execute() {
		if (storeManagement.getAllProductsInStore().isEmpty()) {
			System.out.println("There are no products in the store");
			return;
		}

		int index = storeManagement.pickProduct();
		storeManagement.getAllProductsInStore().remove(index);
	}		
}
