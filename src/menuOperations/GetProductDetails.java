package menuOperations;

import main.StoreManagement;
import products.Product;

public class GetProductDetails implements ICommandGetProductDetails{
	private static StoreManagement storeManagement = StoreManagement.getStoreManagement();

	@Override
	public void execute(){
		String validProductCatalogNum = getValidProductCatalogNumberFromUser();
		Product product = getProductWithCatalogNumber(validProductCatalogNum);
		
		System.out.println("Product type: " + product.getClass().getSimpleName());
		System.out.println(product.toString());
		System.out.println("Total profit from all orders: " + storeManagement.getTotalProfitFromAllOrderOfProduct(product));
	}
	
	public String getValidProductCatalogNumberFromUser() {
		String productCatalogNumber = "";
		
		do{
			productCatalogNumber = storeManagement.getCatalogNumberFromUser();
		}while(!storeManagement.isCatalogNumberExistInProducts(productCatalogNumber));
		
		return productCatalogNumber;
	}
	
	public Product getProductWithCatalogNumber(String catalogNumber) {
		Product productOfCatalogNumber = null;
		for (Product product : storeManagement.getAllProductsInStore()) {
			if(product.getCatalogNum().equals(catalogNumber)) {
				productOfCatalogNumber = product;
			}
		}
		return productOfCatalogNumber;
	}
}
