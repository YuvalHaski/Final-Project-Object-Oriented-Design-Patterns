package menuOperations;

import java.util.ArrayList;
import java.util.Scanner;

import ShippingCompany.HandlerShipping;
import customer.Customer;
import exceptions.GeneralException;
import invoice.Invoicable;
import main.StoreManagement;
import orders.Order;
import orders.OrderCreator;
import orders.OrderWithShipping;
import products.Product;
import products.ProductSoldInStore;
import products.ProductSoldThroughWebsite;
import products.ProductSoldToWholesalers;

public class AddOrderToProduct implements ICommandAddOrderToProduct {
	private Scanner s = new Scanner(System.in);
	private static StoreManagement storeManagement = StoreManagement.getStoreManagement();
	private OrderCreator orderCreator = new OrderCreator();
	private ArrayList<Product> allProductFromTheSameType = new ArrayList<>();

	@Override
	public void execute() {
		Product wantedProduct = null;
		int index = storeManagement.getIndexOfProductTypeFromUser();
		
		printProductsOfSameProductType(index);
		int wantedProductIndex = pickProductFromChosenProductType(); // index of the product chosen by the user
		wantedProduct = findChosenProductWithIndex(wantedProductIndex);
		
		int amountOfOrder = getAmountFromUser(wantedProduct); // get amount of ordered product
		updateStockAfterOrder(amountOfOrder, wantedProduct); // update stock with new stock amount due to order 
		
		String orderCatalogNumber = checkAndSetOrderCatalogNumber(); // get the order catalog number and check if already exists
		Customer costumer = getCustomerDetails(); // get customer's details from user
		
		Order newOrder = createOrder(orderCatalogNumber, costumer, amountOfOrder, wantedProduct);
		
		if(wantedProduct instanceof ProductSoldThroughWebsite) {
			HandlerShipping handlerShipping = new HandlerShipping((ProductSoldThroughWebsite)wantedProduct, (OrderWithShipping)newOrder);
			handlerShipping.execute();			
		}
		
		wantedProduct.addOrderToProduct(newOrder); // add order to relevant product's array of orders
		storeManagement.getAllOrdersMade().add(newOrder); // add order to array that holds all orders that where created
		storeManagement.getStackOrders().add(newOrder);
		
		if(wantedProduct instanceof Invoicable) {
			storeManagement.printAndAddUserWantedInvoice(newOrder); // will ask the user which invoice format he wants and will print to screen	
		}
	}
	
	public Order createOrder(String orderCatalogNumber, Customer costumer, int amountOfOrder, Product wantedProduct) {
		if(wantedProduct instanceof ProductSoldInStore || wantedProduct instanceof ProductSoldToWholesalers) {
			return orderCreator.createOrderWithoutShipping(orderCatalogNumber, costumer, amountOfOrder, wantedProduct);
		}
		
		else { // the product is of type ProductSoldThroughWebsite
			return orderCreator.createOrderWithShipping(orderCatalogNumber, costumer, amountOfOrder, wantedProduct);
		}		
	}
	
	public Product findChosenProductWithIndex(int index) {
		return allProductFromTheSameType.get(index);
	}
	
	public int pickProductFromChosenProductType() {
		int index = 0;
		
		int amountOfProductsFromSameType = allProductFromTheSameType.size();
		while (index < 1 || index > amountOfProductsFromSameType) {
			try {
				System.out.println("Enter the index of the product you want: ");
				
				index = Integer.parseInt(s.nextLine().trim());
				storeManagement.validateInputIndex(index, 1, amountOfProductsFromSameType);
			} 
			catch (NumberFormatException e) {
				System.out.println("Invalid Input");
			}
			catch (GeneralException e) {
				System.out.println(e.getMessage());
			}
		}
		return index-1;
	}
	
	public boolean printProductsOfSameProductType(int productTypeIndex) {
		Product productType = getProductType(productTypeIndex);
		int i = 1;
		
		// if there are no products in the store
		if (storeManagement.getAllProductsInStore().isEmpty()) {
			System.out.println("There are no products in the store");
			return false;
		}
		
		System.out.println("The products in the store: ");
		for (Product product : storeManagement.getAllProductsInStore()) {
			if(productType.getClass().isInstance(product)) {
				System.out.println("\n" + i + ") " + product.toStringWithoutAllOrders());
				allProductFromTheSameType.add(product);
				i++;
			}
		}
		return true;
	}
	
	public Product getProductType(int chosenProductTypeIndex) {
		switch (chosenProductTypeIndex) {
		case 1:
            return new ProductSoldInStore();
		case 2:
            return new ProductSoldThroughWebsite();
		case 3:
            return new ProductSoldToWholesalers();
		default:
			return null;
		}
	}
	
	public int getAmountFromUser(Product product) {
		int amountInOrder = 0;
		while (!isRequestedAmountValid(product, amountInOrder)) {
			try {
				System.out.println("Enter the amount of the product you would like in the order: ");
				amountInOrder = Integer.parseInt(s.nextLine().trim());
				
				if(amountInOrder <= 0) {
					throw new GeneralException("Invalid product amount: chosen amount should be greater than 0");
				}
				if(amountInOrder > product.getStock()) {
					throw new GeneralException("Invalid product amount: chosen amount should be less or equal to the current stock amount in store");
				}

			} 
			catch (NumberFormatException e) {
				System.out.println("Invalid Input");
			}
			catch (GeneralException e) {
				System.out.println(e.getMessage());
			}
		}
		return amountInOrder;
	}
	
	public boolean isRequestedAmountValid(Product product, int amount){
		return product.getStock() >= amount && amount > 0;
	}
	
	public void updateStockAfterOrder(int amountOrdered, Product product) {
		int currentAmount = product.getStock();
		int newAmount = currentAmount - amountOrdered;
		
		product.setStock(newAmount);
	}
	
	public String checkAndSetOrderCatalogNumber() {
		boolean orderCatalogNumAlreadyExists = true;
		String orderCatalogNum = "";
		
		while(orderCatalogNumAlreadyExists) {
			orderCatalogNum = getOrderCatalogNumberFromUser();
			orderCatalogNumAlreadyExists = isCatalogNumberExistInOrders(orderCatalogNum);
		}
		return orderCatalogNum; 
	}
	
	public String getOrderCatalogNumberFromUser() {
		String orderCatalogNumber = "";
		
		System.out.println("Enter the order's catalog number:");
		orderCatalogNumber = s.nextLine();
		System.out.println("The catalog number you entered is: " + orderCatalogNumber);
		
		return orderCatalogNumber;
	}
				
	public boolean isCatalogNumberExistInOrders(String orderCatalogNumber) {
		for (Order order : storeManagement.getAllOrdersMade()) {
			if(order.getCatalogNum().equals(orderCatalogNumber)) {
				System.out.println("The order catalog number already exists");
				return true;
			}
		}
		return false;
	}
	
	public Customer getCustomerDetails() {
		String customerName = getCustomerNameFromUser();
		String customerMobileNumber = getMobileNumberFromUser();
		
		Customer customer = new Customer(customerName, customerMobileNumber);
		
		return customer;
	}
	
	public String getCustomerNameFromUser() {
		int confirmCustomerName = -1;
		String customerName = "";
		
		while (confirmCustomerName != 1) {
			System.out.println("Enter the customer's name: ");
			customerName = s.nextLine();

			System.out.println("The customer's name you entered is: " + customerName);
			boolean isInvalidInput = false;
			while (!isInvalidInput) {
				// confirm product name
				try {
					System.out.println("Do you confirm the customer's name? Choose 0 or 1:\n"
							+ "0) no\n"
							+ "1) yes");
					confirmCustomerName = Integer.parseInt(s.nextLine().trim());
					storeManagement.validateInputIndex(confirmCustomerName, 0, 1);
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
		return customerName;
	}
	
	public String getMobileNumberFromUser() {
		int validMobileNumber = -1;
		String mobileNumber = "";
		
		while (validMobileNumber != 1) {
			try {
				System.out.println("Enter the customer's mobile number: ");
				mobileNumber = s.nextLine();

				if(!containsOnlyNumbers(mobileNumber)) {
					throw new GeneralException("a mobile number can't contain letters, please try again");
				}
				validMobileNumber = 1;
			}
			catch (NumberFormatException e) {
				System.out.println("Invalid input");
			}
			catch (GeneralException e) {
				System.out.println(e.getMessage());
			}	
		}
		return mobileNumber;
	}
	
	public static boolean containsOnlyNumbers(String inputString) {
        for (char c : inputString.toCharArray()) {
            if (!Character.isDigit(c)) {
                return false;
            }
        }
        return true;
    }
}
