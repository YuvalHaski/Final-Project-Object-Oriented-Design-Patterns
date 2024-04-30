package ShippingCompany;

import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Scanner;

import exceptions.GeneralException;
import orders.OrderWithShipping;
import orders.ShippingDetails;
import products.ProductSoldThroughWebsite;
import products.ProductSoldThroughWebsite.ShippingMethods;


public class HandlerShipping implements ICommandHandleShipping, IHandlerShipping{
	private Scanner s = new Scanner(System.in);
	private static ShippingManagement shippingManagement = ShippingManagement.getShippingManagement(); 
	private ProductSoldThroughWebsite productSoldThroughWebsite;
	private HashMap<ObserverShipping, Double> shippingFeeOffers = new HashMap<>();
	private OrderWithShipping order;
	
	public HandlerShipping(ProductSoldThroughWebsite productSoldThroughWebsite, OrderWithShipping order) {
		this.productSoldThroughWebsite = productSoldThroughWebsite;
		this.order = order;
	}
	
	@Override
	public void execute() {
		String shippingMethodStr = getWantedShippingMethodFromUser();
		notifyObservers(shippingMethodStr, productSoldThroughWebsite);
		
		Entry<ObserverShipping, Double> minOffer = getMinimumOffer();
		ShippingDetails shippingDetails = createShippingDetails(minOffer, shippingMethodStr);
		System.out.println(shippingDetails.toString()); // print shipping details
		
		order.setShippingDetails(shippingDetails);
	}
	
	public void printSupportedShippingMethods() {
		for (ShippingMethods shippingType : productSoldThroughWebsite.getSupportedShippingMethods()) {
			System.out.println(shippingType);	
		}		
	}
	
	public boolean checkIfUserShippingMethodChoiceIsValid(String shippingTypeChoice) throws GeneralException {
		if(shippingTypeChoice.equals("e")) {
			if(!productSoldThroughWebsite.checkIfShippingMethodSupported(ShippingMethods.express)) {
				System.out.println("The chosen shipping method is not supported for this product");
				return false;
			}
			return true;
		}
		
		else if(shippingTypeChoice.equals("s")) {
			if(!productSoldThroughWebsite.checkIfShippingMethodSupported(ShippingMethods.standard)) {
				System.out.println("The chosen shipping method is not supported for this product");
				return false;
			}
			return true;
		}
		
		else {
			throw new GeneralException("you must enter (e) or (s) according to the options listed above");
		}
	}
	
	public String getWantedShippingMethodFromUser() {
		System.out.println("The supported shipping methods for the product you chose are: ");
		printSupportedShippingMethods();
		
		int validSupportedShippingType = -1;
		String shippingTypeChoice = "";
		
		while (validSupportedShippingType != 1) {
			try {
				System.out.println(
						"Enter (e) for express shipping\n" + 
						"Enter (s) for standard shipping");
				
				shippingTypeChoice = s.nextLine();
				if(checkIfUserShippingMethodChoiceIsValid(shippingTypeChoice)) {
					validSupportedShippingType = 1;
				}			
			}
			catch (NumberFormatException e) {
				System.out.println("Invalid input");
			}
			catch (GeneralException e) {
				System.out.println(e.getMessage());
			}	
		}
		
		if(shippingTypeChoice.equals("e")) {
			shippingTypeChoice = "express";
		}
		if(shippingTypeChoice.equals("s")) {
			shippingTypeChoice = "standard";
		}
		return shippingTypeChoice;
	}
	
	@Override
	public void addObserver(ObserverShipping observer) {
		shippingManagement.getAllShippingCompaniesObservers().add(observer);
	}

	@Override
	public void removeObserver(ObserverShipping observer) {
		shippingManagement.getAllShippingCompaniesObservers().remove(observer);
	}

	@Override
	public void notifyObservers(String shippingMethod, ProductSoldThroughWebsite product) {
		double feeOffer;
		for (ObserverShipping observer : shippingManagement.getAllShippingCompaniesObservers()) {
			feeOffer = observer.update(shippingMethod, product);
			shippingFeeOffers.put(observer, feeOffer);
        }
	}
	
	public Entry<ObserverShipping, Double> getMinimumOffer() {
		Entry<ObserverShipping, Double> minOffer = null;
        for (Entry<ObserverShipping, Double> offer : shippingFeeOffers.entrySet()) {
            if (minOffer == null || offer.getValue() < minOffer.getValue() && offer.getValue() > -1) {
                minOffer = offer;
            }
        }
        return minOffer;
    }
	
	public ShippingDetails createShippingDetails(Entry<ObserverShipping, Double> minOffer, String shippingMethodStr) {
		ShippingDetails shippingDetails = new ShippingDetails(
				productSoldThroughWebsite.getSellingPrice(), 
				minOffer.getValue(), 
				shippingMethodStr, 
				minOffer.getKey().getClass().getSimpleName(),
				minOffer.getKey());
		
		return shippingDetails;
	}
}
