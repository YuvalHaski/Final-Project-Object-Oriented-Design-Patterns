package menuOperations;

import java.util.Scanner;

import ShippingCompany.CountryAndTax;
import exceptions.GeneralException;
import main.StoreManagement;
import products.Product;
import products.ProductCreator;
import products.ProductSoldInStore;
import products.ProductSoldThroughWebsite;
import products.ProductSoldToWholesalers;

public class AddProductToStore implements ICommandAddProductToStore{
	private Scanner s = new Scanner(System.in);
	private static StoreManagement storeManagement = StoreManagement.getStoreManagement();
	private ProductCreator productCreator = new ProductCreator();

	@Override
	public boolean execute() {
		Product productToAdd = null;
		int index = storeManagement.getIndexOfProductTypeFromUser();
		String catalogNum = checkAndSetCatalogNumber();
		String productName = getProductNameFromUser();
		int costPrice = getCostPriceFromUser();
		int sellingPrice = getSellingPriceFromUser(costPrice);
		int stock = getStockFromUser();
		
		switch (index) {
		case 1:
			productToAdd = createProductSoldInStore(catalogNum, productName, costPrice, sellingPrice, stock);
			break;
		case 2: 
			productToAdd = createProductSoldThroughWebsite(catalogNum, productName, storeManagement.changeCurrencyToDollar(costPrice), storeManagement.changeCurrencyToDollar(sellingPrice), stock);
			break;
		case 3:
			productToAdd = createProductSoldToWholesalers(catalogNum, productName, costPrice, sellingPrice, stock);
			break;
		default:
			break;
		}
		
		if(productToAdd == null) {
			return false;
		}

		return storeManagement.getAllProductsInStore().add(productToAdd);
	}
	
	public ProductSoldInStore createProductSoldInStore(String catalogNum, String productName, int costPrice, int sellingPrice, int stock) {
		return (ProductSoldInStore)productCreator.createProductSoldInStore(catalogNum, productName, costPrice, sellingPrice, stock);
	}
	
	public ProductSoldThroughWebsite createProductSoldThroughWebsite(String catalogNum, String productName, int costPrice, int sellingPrice, int stock) {
		String destinationCountry = getDestinationCountryFromUser();
		CountryAndTax countryAndTax = new CountryAndTax(destinationCountry);
		countryAndTax = checkForUpdateCountryAndTax(countryAndTax);		
		addChosenDestinationCountryToDatabase(countryAndTax); // add the destination country to the supported countries if necessary 
		
		double productWeight = getProductWeightFromTheUser();
		
		return (ProductSoldThroughWebsite)productCreator.createProductSoldThroughWebsite(catalogNum, productName, costPrice, sellingPrice, stock, destinationCountry, productWeight);
	}
	
	public ProductSoldToWholesalers createProductSoldToWholesalers(String catalogNum, String productName, int costPrice, int sellingPrice, int stock) {
		return (ProductSoldToWholesalers)productCreator.createProductSoldToWholesalers(catalogNum, productName, costPrice, sellingPrice, stock);
	}
			
	public String getProductNameFromUser() {
		int confirmProductName = -1;
		String productName = "";
		
		while (confirmProductName != 1) {
			System.out.println("Enter the product name: ");
			productName = s.nextLine();

			System.out.println("The product name you entered is: " + productName);
			boolean isInvalidInput = false;
			while (!isInvalidInput) {
				// confirm product name
				try {
					System.out.println("Do you confirm the product name? Choose 0 or 1:\n"
							+ "0) no\n"
							+ "1) yes");
					confirmProductName = Integer.parseInt(s.nextLine().trim());
					storeManagement.validateInputIndex(confirmProductName, 0, 1);
					isInvalidInput = true;
				}
				catch (NumberFormatException e) {
					System.out.println("Invalid input, you need to enter 0 or 1");
				}
				catch (GeneralException e) {
					System.out.println(e.getMessage());
					
				}	
			}
		}
		return productName;
	}
	
	
	public String checkAndSetCatalogNumber() {
		boolean catalogNumAlreadyExists = true;
		String catalogNum = "";
		
		while(catalogNumAlreadyExists) {
			catalogNum = storeManagement.getCatalogNumberFromUser();
			catalogNumAlreadyExists = storeManagement.isCatalogNumberExistInProducts(catalogNum); 
		}
		
		return catalogNum; 
	}
		
	public int getCostPriceFromUser() {
		int costPrice = 0;
		while (costPrice <= 0) {
			try {
				System.out.println("Enter the product's cost price: ");
				costPrice = Integer.parseInt(s.nextLine().trim());
				
				if(costPrice <= 0) {
					throw new GeneralException("Invalid cost price");
				}
			} 
			catch (NumberFormatException e) {
				System.out.println("Invalid Input");
			}
			catch (GeneralException e) {
				System.out.println(e.getMessage());
			}
		}
		return costPrice;
	}
	
	public int getSellingPriceFromUser(int costPrice) {
		int sellingPrice = 0;
		while (sellingPrice <= 0 || sellingPrice <= costPrice) {
			try {
				System.out.println("Enter the product's selling price: ");
				sellingPrice = Integer.parseInt(s.nextLine().trim());
				
				if(sellingPrice <= 0 || sellingPrice <= costPrice) {
					throw new GeneralException("Invalid selling price");
				}
			} 
			catch (NumberFormatException e) {
				System.out.println("Invalid Input");
			}
			catch (GeneralException e) {
				System.out.println(e.getMessage());
			}
		}
		return sellingPrice;
	}
	
	public int getStockFromUser() {
		int stock = -1;
		while (stock < 0) {
			try {
				System.out.println("Enter the stock of the product: ");
				stock = Integer.parseInt(s.nextLine().trim());
				
				if(stock < 0) {
					throw new GeneralException("Invalid stock amount");
				}
			} 
			catch (NumberFormatException e) {
				System.out.println("Invalid Input");
			}
			catch (GeneralException e) {
				System.out.println(e.getMessage());
			}
		}
		return stock;
	}
		
	public String getDestinationCountryFromUser() {
		int validDestinationCountry = -1;
		String destinationCountry = "";
		
		while (validDestinationCountry != 1) {
			try {
				System.out.println("Enter the destination country name: ");
				destinationCountry = s.nextLine();

				if(!containsOnlyLetters(destinationCountry)) {
					throw new GeneralException("a country can't contain integers, please try again");
				}
				validDestinationCountry = 1;
			}
			catch (NumberFormatException e) {
				System.out.println("Invalid input");
			}
			catch (GeneralException e) {
				System.out.println(e.getMessage());
			}	
		}
		return destinationCountry;
	}
	
	public boolean addChosenDestinationCountryToDatabase(CountryAndTax countryAndTax) {
		return storeManagement.getShippingManagementFromStoreManagements().addCountryAndTax(countryAndTax);
	}
	
	public CountryAndTax checkForUpdateCountryAndTax(CountryAndTax countryAndTax) {
		printCurrentImportTaxForDestination(countryAndTax);
		
		if(checkIfChangeIsNeededInCountryAndTax() == 0) {
			return countryAndTax; // user doesn't want to change import tax
		}
		
		int indexOfTheWantedChange = getIndexOfWantedImportTaxChange();
		
		switch (indexOfTheWantedChange) {
		case 1:
			countryAndTax.setExpressTax(getImportTaxFromUser());
			break;
		case 2:
			countryAndTax.setStandardTax(getImportTaxFromUser());
			break;
		case 3:
			System.out.println("For express import tax: ");
			countryAndTax.setExpressTax(getImportTaxFromUser());
			System.out.println("For standard import tax: ");
			countryAndTax.setStandardTax(getImportTaxFromUser());
			break;
		default:
			break;
		}
		return countryAndTax;
	}
	
	public double getImportTaxFromUser() {
		double importTax = -1.0;
		while (importTax < 0.0) {
			try {
				System.out.println("Enter the new import tax: ");
				importTax = Integer.parseInt(s.nextLine().trim());
				
				if(importTax < 0.0) {
					throw new GeneralException("Invalid tax");
				}
			} 
			catch (NumberFormatException e) {
				System.out.println("Invalid Input");
			}
			catch (GeneralException e) {
				System.out.println(e.getMessage());
			}
		}
		return importTax;
	}
	
	public int getIndexOfWantedImportTaxChange() {
		int index = -1; 
		
		while (index != 1 && index != 2 && index != 3) {
			try {
				System.out.println("Choose the index of the option that represents the tax you want to change: \n"
						+ "1) Express\n"
						+ "2) Standard\n"
						+ "3) Express + Standard"
						);
				 index = Integer.parseInt(s.nextLine().trim());
				storeManagement.validateInputIndex(index, 1, 3);
			}
			catch (NumberFormatException e) {
				System.out.println("Invalid input, you need to enter 1, 2 or 3");
			}
			catch (GeneralException e) {
				System.out.println(e.getMessage());
			}	
		}
		return index;
	}
	
	public int checkIfChangeIsNeededInCountryAndTax() {
		int isChangeWanted = -1; 
		
		while (isChangeWanted != 0 && isChangeWanted != 1) {
			try {
				System.out.println("Do you want to edit the country import tax? Choose 0 or 1:\n"
						+ "0) no\n"
						+ "1) yes");
				 isChangeWanted = Integer.parseInt(s.nextLine().trim());
				storeManagement.validateInputIndex(isChangeWanted, 0, 1);
			}
			catch (NumberFormatException e) {
				System.out.println("Invalid input, you need to enter 0 or 1");
			}
			catch (GeneralException e) {
				System.out.println(e.getMessage());
			}	
		}
		return isChangeWanted;
	}
	
	public void printCurrentImportTaxForDestination(CountryAndTax countryAndTax) {
		System.out.println("This is the current import tax for the country "+ countryAndTax.getDestinationCountry());
		System.out.println(
				"Express: " + countryAndTax.getExpressTax() +
				"\nStandard: " + countryAndTax.getStandardTax()
				);
	}	
	
	public double getProductWeightFromTheUser() {
		double weight = 0;
		while (weight <= 0) {
			try {
				System.out.println("Enter the weight of the product: ");
				weight = Double.parseDouble(s.nextLine().trim());
				
				if(weight <= 0) {
					throw new GeneralException("Invalid weight");
				}
			} 
			catch (NumberFormatException e) {
				System.out.println("Invalid Input");
			}
			catch (GeneralException e) {
				System.out.println(e.getMessage());
			}
		}
		return weight;
	}
	
	public static boolean containsOnlyLetters(String inputString) {
        for (char c : inputString.toCharArray()) {
            if (Character.isDigit(c)) {
                return false;
            }
        }
        return true;
    }
}
