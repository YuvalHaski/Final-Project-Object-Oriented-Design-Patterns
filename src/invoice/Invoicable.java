package invoice;
import orders.Order;

public interface Invoicable {
	Invoice getInvoice(int indexOfWantedInvoice, Order order);
	String getAllInvoices(Order order);
}
