package products;

public class ProductCreator implements ProductFactoryMethod {

	@Override
	public ProductSoldInStore createProductSoldInStore(String catalogNum, String productName, int costPrice, int sellingPrice, int stock) {
		return new ProductSoldInStore(catalogNum, productName, costPrice, sellingPrice, stock);
	}

	@Override
	public ProductSoldThroughWebsite createProductSoldThroughWebsite(String catalogNum, String productName, int costPrice, int sellingPrice, int stock,
			String destinationCountry, double productWeight) {		
		return new ProductSoldThroughWebsite(catalogNum, productName, costPrice, sellingPrice, stock, destinationCountry, productWeight);
	}

	@Override
	public ProductSoldToWholesalers createProductSoldToWholesalers(String catalogNum, String productName, int costPrice, int sellingPrice, int stock) {
		return new ProductSoldToWholesalers(catalogNum, productName, costPrice, sellingPrice, stock);
	}
}
