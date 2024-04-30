package products;

import java.util.ArrayList;
import java.util.List;

import invoice.Invoicable;
import invoice.Invoice;
import invoice.InvoiceAccountantFormat;
import invoice.InvoiceCustomerFormat;
import main.Main;
import orders.Order;

public class ProductSoldToWholesalers extends Product implements Invoicable{

	public ProductSoldToWholesalers(String catalogNum, String productName, int costPrice, int sellingPrice, int stock) {
		super(catalogNum, productName, costPrice, sellingPrice, stock);
		super.supportedInvoiceFormats = new ArrayList<>(List.of("Invoice in accountant format"));
		this.currency = Main.ILS;
	}
	
	public ProductSoldToWholesalers() {}
	
	public ArrayList<String> getSupportedInvoiceFormats() {
		return supportedInvoiceFormats;
	}
	
	@Override
	public String getAllInvoices(Order order) {
		Invoice accountantInvoice = new InvoiceAccountantFormat(order);
		return accountantInvoice.toString();

	}
	
	@Override
	public Invoice getInvoice(int indexOfWantedInvoice, Order order) {
		Invoice invoice = null;
		switch (indexOfWantedInvoice) {
		case 1:
			invoice = new InvoiceAccountantFormat(order);
			break;
		default:
            throw new IllegalArgumentException("This invoice type is not supported for the product");
		}
		return invoice;
	}
	
	@Override
	public String toStringWithoutAllOrders() {
		return super.toStringWithoutAllOrders();
	}
}
