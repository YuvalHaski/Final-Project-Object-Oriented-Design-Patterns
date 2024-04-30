package main;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

import ShippingCompany.CountryAndTax;
import ShippingCompany.ShippingManagement;
import comparators.LexicographicComparatorProduct;
import exceptions.GeneralException;
import invoice.Invoice;
import menuOperations.AddOrderToProduct;
import menuOperations.AddProductToStore;
import menuOperations.AutomaticStoreFill;
import menuOperations.GetAllProductsDetails;
import menuOperations.GetOrdersOfProduct;
import menuOperations.GetProductDetails;
import menuOperations.RemoveProductFromStore;
import menuOperations.UndoLastOrder;
import menuOperations.UpdateStockOfProduct;
import orders.Order;
import orders.OrderWithoutShipping;
import products.Product;
import products.ProductSoldInStore;
import products.ProductSoldThroughWebsite;
import products.ProductSoldToWholesalers;

public class StoreManagement {	
	private Scanner s = new Scanner(System.in);
	private static StoreManagement _instance = null;
	private ArrayList<Product> allProductsInStore;
	private ArrayList<Order> allOrdersMade;
	private List<String> productTypesInString = new ArrayList<>(List.of("Sold in store", "Sold through website", "Sold to wholesalers"));
	private LexicographicComparatorProduct lexicographicComparator = new LexicographicComparatorProduct();
	private ShippingManagement shippingManagement = ShippingManagement.getShippingManagement();
	private Stack<Order> stackOrders;
	
	private StoreManagement() {
		this.allProductsInStore = new ArrayList<>();
		this.allOrdersMade = new ArrayList<>();
		this.stackOrders = new Stack<>();
	}
	
	public static StoreManagement getStoreManagement () {
		if(_instance == null) {
			_instance = new StoreManagement();	
		}
		return _instance;
	}
	
	public Stack<Order> getStackOrders() {
		return stackOrders;
	}

	public ArrayList<Order> getAllOrdersMade() {
		return allOrdersMade;
	}

	public ArrayList<Product> getAllProductsInStore() {
		return allProductsInStore;
	}

	public List<String> getProductTypesInString() {
		return productTypesInString;
	}

	public ShippingManagement getShippingManagementFromStoreManagements() {
		return shippingManagement;
	}
	
	// 4.1
	public void automaticStoreFill() {
		AutomaticStoreFill automaticStoreFill = new AutomaticStoreFill();
		automaticStoreFill.execute(); 
	}

	// 4.2
	public void addProductToStore() {
		AddProductToStore createAndAddProduct = new AddProductToStore();
		createAndAddProduct.execute(); // creates and adds the new product
		sortLexicographicallyAllProducts(); // sorts the new product in lexicographical way in products array		
	}
	
	// 4.3
	public void removeProductFromStore() {
		RemoveProductFromStore removeProduct = new RemoveProductFromStore();
		removeProduct.execute(); // remove product from all product ArrayList
		sortLexicographicallyAllProducts(); // sorts the ArrayList after product removal
	}
	
	// 4.4
	public void updateStockOfProductFromStore() {
		UpdateStockOfProduct updateStockOfProduct = new UpdateStockOfProduct();
		updateStockOfProduct.execute(); // update stock of product
	}
	
	// 4.5
	public void addOrderToProduct() {
		AddOrderToProduct addOrderToProduct = new AddOrderToProduct();
		addOrderToProduct.execute();
	}
	
	// 4.6
	public void undoLastOrder() {
		UndoLastOrder undoLastOrder = new UndoLastOrder();
		undoLastOrder.execute(); // removes order from order database and orders array of product and updates stock		
	}
	
	// 4.7
	public void getProductDetails() {
		GetProductDetails getProductDetails = new GetProductDetails();
		getProductDetails.execute();
	}
	
	// 4.8
	public void getAllProductsDetails() {
		GetAllProductsDetails getAllProductsDetails = new GetAllProductsDetails();
		getAllProductsDetails.execute();
	}
	
	// 4.9
	public void getOrdersOfProduct() {
		GetOrdersOfProduct getOrdersOfProduct = new GetOrdersOfProduct();
		getOrdersOfProduct.execute();
	}
	
	public void sortLexicographicallyAllProducts() {
		allProductsInStore.sort(lexicographicComparator);
	}
	
	public void validateInputIndex(int index, int minValue, int maxValue) throws GeneralException {
		if (index < minValue || index > maxValue) {
			throw new GeneralException("Index not in range, please try again");
        }
	}
	
	public boolean printProductsInStore() {
		int i = 1;
		
		// if there are no products in the store
		if (allProductsInStore.isEmpty()) {
			System.out.println("There are no products in the store");
			return false;
		}
		
		System.out.println("The products in the store:");
		for (Product product : allProductsInStore) {
		    System.out.println("\n" + i + ") " + product.toStringWithoutAllOrders());
		    i++;
		}
		return true;
	}
	
	public int pickProduct() {
		int index = 0;
		while (index < 1 || index > allProductsInStore.size()) {
			try {
				System.out.println("Enter the index of the product you want: ");
				printProductsInStore();
				
				index = Integer.parseInt(s.nextLine().trim());
				validateInputIndex(index, 1, allProductsInStore.size());
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
	
	public int getIndexOfProductTypeFromUser() {
		int index = 0;
		while (index < 1 || index > productTypesInString.size()) {
			try {
				System.out.println("Enter the index of the product type you want: ");
				printProductTypesInString();
				
				index = Integer.parseInt(s.nextLine().trim());
				validateInputIndex(index, 1, productTypesInString.size());
			} 
			catch (NumberFormatException e) {
				System.out.println("Invalid Input");
			}
			catch (GeneralException e) {
				System.out.println(e.getMessage());
			}
		}
		return index;
	}
	
	public void printAndAddUserWantedInvoice(Order order) {
		int index = getWantedInvoiceTypeFromUser(order);
		String productTypeStr = order.getProduct().getClass().getSimpleName();
		Product product = order.getProduct();
		Invoice invoice = null;
		
		switch (productTypeStr) {
		case "ProductSoldInStore":
			ProductSoldInStore productSoldInStore = (ProductSoldInStore) product;
			invoice = productSoldInStore.getInvoice(index, order);
			((OrderWithoutShipping)order).setInvoiceForOrder(invoice);
			System.out.println(invoice.toString());
			break;
		case "ProductSoldToWholesalers":
			ProductSoldToWholesalers productSoldToWholesalers = (ProductSoldToWholesalers) product;
			invoice = productSoldToWholesalers.getInvoice(index, order);
			((OrderWithoutShipping)order).setInvoiceForOrder(invoice);
			System.out.println(invoice.toString());
			break;
		default:
			break;
		}
	}
	
	public boolean printSupportedInvoiceTypesForPorduct(Order order) {
		ArrayList<String> supportedInvoiceFormats = order.getProduct().getSupportedInvoiceFormats();
		
		if(supportedInvoiceFormats.isEmpty()) {
			System.out.println("This product has no supported invoice formats");
			return false;
		}
		
		int i = 1;
		for (String str : supportedInvoiceFormats) {
			System.out.println(i + ") " + str);
			i++;
		}
		return true;
	}
	
	public int getWantedInvoiceTypeFromUser(Order order) {
		int invoiceTypeIndex = 0;
		while (invoiceTypeIndex <= 0) {
			try {
				System.out.println("Enter the invoice format you would like to get: ");
				printSupportedInvoiceTypesForPorduct(order);
				invoiceTypeIndex = Integer.parseInt(s.nextLine().trim());
				validateInputIndex(invoiceTypeIndex, 1, order.getProduct().getSupportedInvoiceFormats().size());				
			} 
			catch (NumberFormatException e) {
				System.out.println("Invalid Input");
			}
			catch (GeneralException e) {
				System.out.println(e.getMessage());
			}
		}
		return invoiceTypeIndex;
	}
		
	public void printProductTypesInString() {
		int index = 0;
		for (String productTypeStr : productTypesInString) {
			System.out.println(++index + ")" + productTypeStr);
		}
	}
	
	public String getCatalogNumberFromUser() {
		String catalogNumber = "";
		
		System.out.println("Enter the catalog number:");
		catalogNumber = s.nextLine();
		System.out.println("The catalog number you entered is: " + catalogNumber);
		
		return catalogNumber;
	}
	
	public boolean isCatalogNumberExistInProducts(String catalogNumber) {
		for (Product product : allProductsInStore) {
			if(product.getCatalogNum().equals(catalogNumber)) {
				return true;
			}
		}
		return false;
	}
	
	public double getTotalProfitFromAllOrderOfProduct(Product product) {
		int profitPerUnit = product.getProfitPerUnit();
		if(product instanceof ProductSoldThroughWebsite) {
			profitPerUnit = changeCurrencyToILS(profitPerUnit);
		}
		
		double totalProfit = 0, profitPerOrder;
		
		for (Order order : product.getAllOrders()) {
			profitPerOrder = order.getAmount()* profitPerUnit; 
			totalProfit += profitPerOrder;
		}
		return totalProfit;
	}
	
	public int changeCurrencyToDollar(int priceInILS) {
		 return (int) (priceInILS/Main.DOLLAR_RATE);
	}
	
	public int changeCurrencyToILS(int priceInUSD) {
		 return priceInUSD * Main.DOLLAR_RATE;
	}
	
	public Order removeLastAddedOrderFromDataBaseOrders() {
		int index = allOrdersMade.indexOf(stackOrders.pop());
		
		return allOrdersMade.remove(index); 
	}
	
	public Memento createMemento() {
		// Create copies of the lists and sets to avoid modifying the memento state later
	    ArrayList<Product> productsCopy = new ArrayList<>(allProductsInStore);
	    ArrayList<Order> ordersCopy = new ArrayList<>(allOrdersMade);
	    HashSet<CountryAndTax> countriesCopy = new HashSet<>(shippingManagement.getSupportedShippingCountries());
	    return new Memento(productsCopy, ordersCopy, countriesCopy); // creates backup
	}
	
	public void setMemento(Memento memento) {
		this.allProductsInStore = memento.allProductsInStore;
		this.allOrdersMade = memento.allOrdersMade;
		this.shippingManagement.setSupportedShippingCountries(memento.supportedShippingCountries);
	} 
	
	/* subclass inside StoreManagement class */
	public static class Memento {
		private final ArrayList<Product> allProductsInStore;
		private final ArrayList<Order> allOrdersMade;
		private final HashSet<CountryAndTax> supportedShippingCountries;
		
		private Memento(ArrayList<Product> allProductsInStore, ArrayList<Order> allOrdersMade, HashSet<CountryAndTax> supportedShippingCountries) {
			this.allProductsInStore = allProductsInStore;
			this.allOrdersMade = allOrdersMade;
			this.supportedShippingCountries = supportedShippingCountries;
		}
	}
}
