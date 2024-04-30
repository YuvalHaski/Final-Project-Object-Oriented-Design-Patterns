package invoice;

import orders.Order;

public class InvoiceCustomerFormat extends Invoice{
	private double tax;
		
	public InvoiceCustomerFormat(Order order) {
		super(order);
		this.tax = calculateTaxPerOrder();
	}

	public double calculateTaxPerOrder() {
		return order.getProduct().getTaxPerUnit() * order.getAmount();
	}
	
	@Override
	public String toString() {
		return  "\nInvoice in customer format: \n" +
				super.toString() +
				"\nTax: " + tax; 
	}
}
