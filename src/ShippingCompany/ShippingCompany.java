package ShippingCompany;

import products.ProductSoldThroughWebsite;

public abstract class ShippingCompany implements ObserverShipping{
	protected String contactName;
	protected String whatsApp;

	public ShippingCompany(String contactName, String whatsApp) {
		this.contactName = contactName;
		this.whatsApp = whatsApp;
	}
	
	public String getContactName() {
		return contactName;
	}
	public void setContactName(String contactName) {
		this.contactName = contactName;
	}
	public String getWhatsApp() {
		return whatsApp;
	}
	public void setWhatsApp(String whatsApp) {
		this.whatsApp = whatsApp;
	}
	
	public abstract double getShippingFeeOffer(String shippingMethod, ProductSoldThroughWebsite product);

	@Override
	public String toString() {
		return "Contact name: " + contactName +
			   "\nWhatsApp number: " + whatsApp;
	}
}
