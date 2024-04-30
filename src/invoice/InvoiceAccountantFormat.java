package invoice;

import orders.Order;
import products.Product;

public class InvoiceAccountantFormat extends Invoice{
	private double profit;

	public InvoiceAccountantFormat(Order order) {
		super(order);
		this.profit = calculateProfitPerOrder();
	}
	
	public double calculateProfitPerOrder() {
		return order.getProduct().getProfitPerUnit() * order.getAmount();
	}
	
	@Override
	public String toString() {
		return  "\nInvoice in accountant format: \n" + 
				super.toString() +
				"\nProfit: " + profit;
	}
}
