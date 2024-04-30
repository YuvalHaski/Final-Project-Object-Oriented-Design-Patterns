package orders;

import customer.Customer;
import products.Product;

public abstract class Order {
	protected String orderCatalogNum;
	protected Product product;
	protected Customer customer;
	protected int amount;
	
	public Order(String orderCatalogNum, Customer customer, int amount, Product product) {
		this.orderCatalogNum = orderCatalogNum;
		this.amount = amount;
		this.customer = customer;
		this.product = product;
	}

	public String getCatalogNum() {
		return orderCatalogNum;
	}

	public void setCatalogNum(String orderCatalogNum) {
		this.orderCatalogNum = orderCatalogNum;
	}

	public Customer getCustomer() {
		return customer;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}
	
	public Product getProduct() {
		return product;
	}

	@Override
	public String toString() {
		return "Order catalog number: " + orderCatalogNum +
			   "\nProduct name: " + product.getProductName() + 
			   "\nCustomer " + customer.toString() +
			   "\nAmount ordered: " + amount;
	}
	
	public abstract String toStringWithDetails();
		
}
