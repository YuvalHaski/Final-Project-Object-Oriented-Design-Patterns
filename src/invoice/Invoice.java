package invoice;

import customer.Customer;
import orders.Order;

public abstract class Invoice {
	protected Order order;
	protected String productName;
	protected Customer customer;
	protected int amount;

	
	public Invoice(Order order) {
		this.order = order;
		this.productName = order.getProduct().getProductName();
		this.customer = order.getCustomer();
		this.amount = order.getAmount();
	}
	
	public String getProductName() {
		return productName;
	}

	public Customer getCustomer() {
		return customer;
	}

	public int getAmount() {
		return amount;
	}

	@Override
	public String toString() {
		return  "- Product Name: " + productName + "\n" +
				"- Customer: " + customer.toString() + "\n" +
				"- Amount: " + amount; 
	}
}
