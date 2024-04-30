package orders;

import ShippingCompany.ObserverShipping;

public class ShippingDetails {
	private int sellingPrice;
	private double shippingPrice;
	private String shippingMethod;
	private String shippingCompany;
	private ObserverShipping observerCompany;
	
	public ShippingDetails(int sellingPrice, double shippingPrice, String shippingMethod, String shippingCompany, ObserverShipping observerCompany) {
		this.sellingPrice = sellingPrice;
		this.shippingPrice = shippingPrice;
		this.shippingMethod = shippingMethod;
		this.shippingCompany = shippingCompany;
		this.observerCompany = observerCompany;
	}

	public int getSellingPrice() {
		return sellingPrice;
	}

	public double getShippingPrice() {
		return shippingPrice;
	}
	
	public void setShippingPrice() {
		observerCompany.update(shippingMethod, null);
	}

	public String getShippingMethod() {
		return shippingMethod;
	}
	

	public String getShippingCompany() {
		return shippingCompany;
	}

	public ObserverShipping getCompanyDetails() {
		return observerCompany;
	}

	@Override
	public String toString() {
		return 	"\nShipping Details: " +
				"\nProduct selling price: " + sellingPrice + " USD" +
				"\nTotal shipping fee price: " + shippingPrice + " USD" +
				"\nShipping method: " + shippingMethod +
				"\nShipping company: " + shippingCompany +
				"\n" + observerCompany.toString();
	}
}
