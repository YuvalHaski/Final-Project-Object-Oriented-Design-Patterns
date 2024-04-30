package ShippingCompany;

import products.ProductSoldThroughWebsite;

public interface ObserverShipping {
	double update(String shippingMethodStr, ProductSoldThroughWebsite product);
}
