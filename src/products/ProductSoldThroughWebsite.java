package products;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import main.Main;

public class ProductSoldThroughWebsite extends Product{
	public enum ShippingMethods {express, standard};
	private String destinationCountry;
	private HashSet<ShippingMethods> supportedShippingMethods; 
	private double productWeight;

	public ProductSoldThroughWebsite(String catalogNum, String productName, int costPrice, int sellingPrice, int stock, 
			String destinationCountry, double productWeight) {
		super(catalogNum, productName, costPrice, sellingPrice, stock);
		super.supportedInvoiceFormats = new ArrayList<>();
		this.destinationCountry = destinationCountry;
		this.supportedShippingMethods = new HashSet<>(Set.of(ShippingMethods.express, ShippingMethods.standard));
		this.productWeight = productWeight;
		this.currency = Main.USD;
	}
	
	public ProductSoldThroughWebsite() {}
	
	public ArrayList<String> getSupportedInvoiceFormats() {
		return supportedInvoiceFormats;
	}
	
	public double getProductWeight() {
		return productWeight;
	}

	public void setProductWeight(double productWeight) {
		this.productWeight = productWeight;
	}

	public String getDestinationCountry() {
		return destinationCountry;
	}

	public void setDestinationCountry(String destinationCountry) {
		this.destinationCountry = destinationCountry;
	}

	public HashSet<ShippingMethods> getSupportedShippingMethods() {
		return supportedShippingMethods;
	}
	
	public boolean addSupportedShippingMethod(ShippingMethods shippingType) {
		if(checkIfShippingMethodSupported(shippingType)) {
			supportedShippingMethods.add(shippingType);
			return true;
		}
		return false; // the shipping method doesn't exist in enum
	}
	
	public boolean removeSupportedShippingMethod(ShippingMethods shippingType) {
		if(supportedShippingMethods.contains(shippingType)) {
			supportedShippingMethods.remove(shippingType);
			return true;
		}
		return false; // the shipping type doesn't exist in the hashset
	}
	
	public boolean checkIfShippingMethodSupported(ShippingMethods shippingType) {
		List<ShippingMethods> enumList = List.copyOf(EnumSet.allOf(ShippingMethods.class));
		return enumList.contains(shippingType);	
	}

	public String getCurrency() {
		return currency;
	}
	
	@Override
	public String toStringWithoutAllOrders() {
		return super.toStringWithoutAllOrders() + 
				"\nDestination country: " + destinationCountry +
				"\nProduct weight: " + productWeight;
	}
}
