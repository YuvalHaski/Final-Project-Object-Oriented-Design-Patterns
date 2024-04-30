package products;

import java.util.ArrayList;
import java.util.List;

import invoice.Invoicable;
import invoice.Invoice;
import invoice.InvoiceAccountantFormat;
import invoice.InvoiceCustomerFormat;
import main.Main;
import orders.Order;

public class ProductSoldInStore extends Product implements Invoicable{
	
	public ProductSoldInStore(String catalogNum, String productName, int costPrice, int sellingPrice, int stock) {
		super(catalogNum, productName, costPrice, sellingPrice, stock);
		super.supportedInvoiceFormats = new ArrayList<>(List.of("Invoice in customer format", "Invoice in accountant format"));
		this.currency = Main.ILS;
	}
	
	public ProductSoldInStore() {}
	
	public ArrayList<String> getSupportedInvoiceFormats() {
		return supportedInvoiceFormats;
	}
	
	@Override
	public String getAllInvoices(Order order) {
		Invoice accountantInvoice = new InvoiceAccountantFormat(order);
		Invoice customerInvoice = new InvoiceCustomerFormat(order);
		return accountantInvoice.toString() + customerInvoice.toString();
	}

	@Override
	public Invoice getInvoice(int indexOfWantedInvoice, Order order) {
		Invoice invoice = null;
		switch (indexOfWantedInvoice) {
		case 1:
			invoice = new InvoiceCustomerFormat(order);
			break;				
		case 2:
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
