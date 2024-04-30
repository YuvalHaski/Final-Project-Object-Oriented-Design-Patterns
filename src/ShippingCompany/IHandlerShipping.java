package ShippingCompany;

import products.ProductSoldThroughWebsite;

public interface IHandlerShipping {
	void addObserver(ObserverShipping observer);
    void removeObserver(ObserverShipping observer);
    void notifyObservers(String shippingMethod, ProductSoldThroughWebsite product);
}
