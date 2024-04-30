package customer;

public class Customer {
	private String customerName;
	private String mobileNumber;
	
	public Customer(String customerName, String mobileNumber) {
		this.customerName = customerName;
		this.mobileNumber = mobileNumber;
	}
	
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	@Override
	public String toString() {
		return "name: " + customerName + "\nMobile number: " + mobileNumber;
	}
}
