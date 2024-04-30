package products;

public interface ProductFactoryMethod {
	Product createProductSoldInStore(String catalogNum, String productName, int costPrice, int sellingPrice, int stock);
	Product createProductSoldThroughWebsite(String catalogNum, String productName, int costPrice, int sellingPrice, int stock,
			String destinationCountry, double productWeight);
	Product createProductSoldToWholesalers(String catalogNum, String productName, int costPrice, int sellingPrice, int stock);
}
