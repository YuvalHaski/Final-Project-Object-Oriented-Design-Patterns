package orders;

import customer.Customer;
import products.Product;

public class OrderCreator implements OrderFactoryMethod{

	@Override
	public OrderWithShipping createOrderWithShipping(String orderCatalogNum, Customer customer, int amount,
			Product product) {
		return new OrderWithShipping(orderCatalogNum, customer, amount, product);
	}
	
	@Override
	public OrderWithShipping createOrderWithShipping(String orderCatalogNum, Customer customer, int amount,
			Product product, ShippingDetails shippingDetails) {
		return new OrderWithShipping(orderCatalogNum, customer, amount, product, shippingDetails);
	}

	@Override
	public OrderWithoutShipping createOrderWithoutShipping(String orderCatalogNum, Customer customer, int amount,
			Product product) {
		return new OrderWithoutShipping(orderCatalogNum, customer, amount, product);
	}
}
