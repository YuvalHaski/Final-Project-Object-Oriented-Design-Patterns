package ShippingCompany;

import main.Main;
import products.ProductSoldThroughWebsite;

public class FedEx extends ShippingCompany implements Standard, Express{
	private double expressShippingPrice = 50;
	private double standardShippingPrice = 10;
	private String currency = Main.USD;

	public FedEx(String contactName, String whatsApp) {
		super(contactName, whatsApp);
	}

	@Override
	public double standardShippingFees(ProductSoldThroughWebsite product) {
		ShippingManagement shippingManagement = ShippingManagement.getShippingManagement();
		double destinationCountryTax = shippingManagement.getTaxOfDestinationCountry(product, "standard");
	 
		if(destinationCountryTax == -1) {
			 return destinationCountryTax; // destination country is not supported
		}
		 
		return standardShippingTax(product) + destinationCountryTax; // return price in dollar
	}

	@Override
	public double expressShippingFees(ProductSoldThroughWebsite product) {
		 ShippingManagement shippingManagement = ShippingManagement.getShippingManagement();
		 double destinationCountryTax = shippingManagement.getTaxOfDestinationCountry(product, "express");
		 
		 if(destinationCountryTax == -1) {
			 return destinationCountryTax; // destination country is not supported
		 }
		 
		return expressShippingTax(product) + destinationCountryTax; // return price in dollar
	}
	
	public double expressShippingTax(ProductSoldThroughWebsite product) {
		double amountOfWeightPer10Kg = product.getProductWeight()/10;
		return expressShippingPrice * amountOfWeightPer10Kg;
	}
	
	public double standardShippingTax(ProductSoldThroughWebsite product) {
		double amountOfWeightPer10Kg = product.getProductWeight()/10;
		return standardShippingPrice * amountOfWeightPer10Kg;
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
