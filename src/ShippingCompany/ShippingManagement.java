package ShippingCompany;

import java.util.HashSet;
import java.util.Iterator;

import products.ProductSoldThroughWebsite;

public class ShippingManagement {
	private static ShippingManagement _instance = null;
	private HashSet<CountryAndTax> supportedShippingCountries;
	private HashSet<ObserverShipping> allShippingCompaniesObservers;

	private ShippingManagement() {
		this.supportedShippingCountries = new HashSet<>();
		this.allShippingCompaniesObservers = new HashSet<>();
	}
	
	public static ShippingManagement getShippingManagement() {
		if(_instance == null) {
			_instance = new ShippingManagement();	
		}
		return _instance;
	}

	public HashSet<CountryAndTax> getSupportedShippingCountries() {
		return supportedShippingCountries;
	}
	
	public void setSupportedShippingCountries(HashSet<CountryAndTax> supportedShippingCountries) {
		this.supportedShippingCountries = supportedShippingCountries;
	}

	public HashSet<ObserverShipping> getAllShippingCompaniesObservers() {
		return allShippingCompaniesObservers;
	}

	public boolean addCountryAndTax(CountryAndTax countryAndTax) {
		if(countryAndTax == null) {
			return false;
		}
				
		return supportedShippingCountries.add(countryAndTax); // will return true if doesn't exist in hashset and false otherwise
	}
	
	public boolean updateCountryAndTax(CountryAndTax countryAndTax) {
		if(countryAndTax == null) {
			return false;
		}
		
		if(supportedShippingCountries.contains(countryAndTax)) {
			// remove the old object before updating the hashset
			removeCountry(countryAndTax);
			
			// update hashset with new object
			supportedShippingCountries.add(countryAndTax);
		}
		return true;
	}
	
	// Method to remove the country from the HashSet
    public void removeCountry(CountryAndTax countryAndTax) {
        Iterator<CountryAndTax> iterator = supportedShippingCountries.iterator();
        while (iterator.hasNext()) {
            CountryAndTax current = iterator.next();
            if (current.getDestinationCountry().equals(countryAndTax.getDestinationCountry())) {
                iterator.remove();
                break; // Found and removed the country, so exit loop
            }
        }
    }
    
    public double getTaxOfDestinationCountry(ProductSoldThroughWebsite product, String shippingType) {
    	double theTax = -1.0;
        Iterator<CountryAndTax> iterator = supportedShippingCountries.iterator();
        while (iterator.hasNext()) {
            CountryAndTax current = iterator.next();
            if (current.getDestinationCountry().equals(product.getDestinationCountry())) {
            	switch(shippingType) {
            		case "express":
            			theTax = current.getExpressTax();
            			break;
            		case "standard":
            			theTax = current.getStandardTax();
            			break;
            		default:
            			break;
            	}
            	break;
            }
        }
        return theTax; // return -1 if the destination country of the product doesn't exist in the hashset
    }    
}
