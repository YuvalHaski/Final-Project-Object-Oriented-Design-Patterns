package orders;

import customer.Customer;
import invoice.Invoicable;
import invoice.Invoice;
import products.Product;
import products.ProductSoldInStore;

public class OrderWithoutShipping extends Order{
	private Invoice invoiceForOrder;

	public OrderWithoutShipping(String orderCatalogNum, Customer customer, int amount, Product product) {
		super(orderCatalogNum, customer, amount, product); 
	}

	public Invoice getInvoiceForOrder() {
		return invoiceForOrder;
	}

	public void setInvoiceForOrder(Invoice invoiceForOrder) {
		this.invoiceForOrder = invoiceForOrder;
	}

	@Override
	public String toString() {
		return super.toString();
	}
	
	@Override
	public String toStringWithDetails() {
		StringBuffer sb = new StringBuffer();
		sb.append(super.toString() + "\n" + ((Invoicable)(this.product)).getAllInvoices(this));
		return sb.toString();
	}
}
