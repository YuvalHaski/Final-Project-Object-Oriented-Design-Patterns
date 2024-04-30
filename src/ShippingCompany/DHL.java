package ShippingCompany;

import main.Main;
import products.ProductSoldThroughWebsite;

public class DHL extends ShippingCompany implements Standard, Express{
	private double expressShippingPrice = 100;
	private double standardShippingPrice;
	private static ShippingManagement shippingManagement = ShippingManagement.getShippingManagement();
	private String currency = Main.USD;

	public DHL(String contactName, String whatsApp) {
		super(contactName, whatsApp);
	}
	
	public double getExpressShippingPrice() {
		return expressShippingPrice;
	}

	public void setExpressShippingPrice(double expressShippingPrice) {
		this.expressShippingPrice = expressShippingPrice;
	}

	public double getStandardShippingPrice() {
		return standardShippingPrice;
	}

	public void setStandardShippingPrice(double standardShippingPrice) {
		this.standardShippingPrice = standardShippingPrice;
	}

	public String getCurrency() {
		return currency;
	}

	@Override
	public double standardShippingFees(ProductSoldThroughWebsite product) {
		standardShippingPrice = product.getSellingPrice()*0.1;
		if(standardShippingPrice >= 100) {
			standardShippingPrice = 100;
		}
		double destinationCountryTax = shippingManagement.getTaxOfDestinationCountry(product, "standard");
	 
		if(destinationCountryTax == -1) {
			 return destinationCountryTax; // destination country is not supported
		}
		 
		return standardShippingPrice + destinationCountryTax; // return price in dollar
	}

	@Override
	public double expressShippingFees(ProductSoldThroughWebsite product) {
		 double destinationCountryTax = shippingManagement.getTaxOfDestinationCountry(product, "express");
		 
		 if(destinationCountryTax == -1) {
			 return destinationCountryTax; // destination country is not supported
		 }
		 
		return expressShippingPrice + destinationCountryTax; // return price in dollar
	}

	@Override
	public double getShippingFeeOffer(String shippingMethod, ProductSoldThroughWebsite product) {
		switch (shippingMethod) {
		case "standard":
			return standardShippingFees(product);
		case "express":
			return expressShippingFees(product);
		default:
			break;
		}
		return -1.0; 
	}

	@Override
	public double update(String shippingMethodStr, ProductSoldThroughWebsite product) {
        return getShippingFeeOffer(shippingMethodStr, product);
	}
}
