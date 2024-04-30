package customer;

public class CustomerCreator implements CustomerFactoryMethod{

	@Override
	public Customer createCustomer(String customerName, String mobileNumber) {
		return new Customer(customerName, mobileNumber);
	}
}
