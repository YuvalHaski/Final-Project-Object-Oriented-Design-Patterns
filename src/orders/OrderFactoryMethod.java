package orders;

import customer.Customer;
import products.Product;

public interface OrderFactoryMethod {
	OrderWithShipping createOrderWithShipping(String orderCatalogNum, Customer customer, int amount, Product product);
	OrderWithShipping createOrderWithShipping(String orderCatalogNum, Customer customer, int amount, Product product, ShippingDetails shippingDetails);
	OrderWithoutShipping createOrderWithoutShipping(String orderCatalogNum, Customer customer, int amount, Product product);
}
