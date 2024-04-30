package orders;

import customer.Customer;
import products.Product;

public class OrderWithShipping extends Order{
	private ShippingDetails shippingDetails;
	
	public OrderWithShipping(String orderCatalogNum, Customer customer, int amount, Product product) {
		super(orderCatalogNum, customer, amount, product);
	}
	
	public OrderWithShipping(String orderCatalogNum, Customer customer, int amount, Product product, ShippingDetails shippingDetails) {
		super(orderCatalogNum, customer, amount, product);
		this.shippingDetails = shippingDetails;
	}


	public ShippingDetails getShippingDetails() {
		return shippingDetails;
	}

	public void setShippingDetails(ShippingDetails shippingDetails) {
		this.shippingDetails = shippingDetails;
	}

	@Override
	public String toString() {
		return super.toString();
	}

	@Override
	public String toStringWithDetails() {
		return super.toString() + shippingDetails.toString();
	}
	
	
}
