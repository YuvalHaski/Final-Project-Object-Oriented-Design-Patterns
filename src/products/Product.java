package products;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Stack;

import main.Main;
import orders.Order;

public abstract class Product {
	protected String catalogNum;
	protected String productName; 
	protected int costPrice;
	protected int sellingPrice;
	protected int stock;
	protected String currency;
	protected ArrayList<Order> allOrders;
	protected ArrayList<String> supportedInvoiceFormats;
	protected Stack<Order> ordersByCreationTime;
	protected Stack<Order> temp;
	
	public Product(String catalogNum, String productName, int costPrice, int sellingPrice, int stock) {
		this.catalogNum = catalogNum;
		this.productName = productName;
		this.costPrice = costPrice;
		this.sellingPrice = sellingPrice;
		this.stock = stock;
		this.allOrders = new ArrayList<>();
		this.ordersByCreationTime = new Stack<>();
		this.temp = new Stack<>();
	}
	
	public Product() {}
	
	public ArrayList<String> getSupportedInvoiceFormats() {
		return supportedInvoiceFormats;
	}
	
	public Stack<Order> getOrdersByCreationTime() {
		return ordersByCreationTime;
	}

	public Stack<Order> getTemp() {
		return temp;
	}

	public String getCatalogNum() {
		return catalogNum;
	}

	public void setCatalogNum(String catalogNum) {
		this.catalogNum = catalogNum;
	}
	
	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public int getCostPrice() {
		return costPrice;
	}

	public void setCostPrice(int costPrice) {
		this.costPrice = costPrice;
	}

	public int getSellingPrice() {
		return sellingPrice;
	}

	public void setSellingPrice(int sellingPrice) {
		this.sellingPrice = sellingPrice;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}
	
	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public ArrayList<Order> getAllOrders() {
		return allOrders;
	}
	
	public double getTaxPerUnit() { // return the maam of the  
		return 0.17 * sellingPrice;
	}
	
	public int getProfitPerUnit() {
		return sellingPrice - costPrice;
	}
	
	public boolean addOrderToProduct(Order order) {
		if(order == null) {
			return false;
		}
		addOrderToStack(order);
		return allOrders.add(order);
	}
	
	public void addOrderToStack(Order order) {
		if(order == null) {
			return;
		}
		ordersByCreationTime.push(order);
	}	
		
	public void moveOrdersFromOrdersByCreationTimeStackToTempStack() {
		while(!ordersByCreationTime.isEmpty()) {
			Order poppedOrder = ordersByCreationTime.pop();
			temp.push(poppedOrder);
		}
	}	
	
	public String toStringWithoutAllOrders() {
		return  "Catalog number: " + catalogNum +
				"\nProduct name: " + productName + 
				"\nCost price: " + costPrice + " " + this.getCurrency() + 
				"\nSelling price: " + sellingPrice + " " + this.getCurrency() +
				"\nStock:" + stock;
	}
	
	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		int index = 1;
		sb.append(toStringWithoutAllOrders() + "\n");
		
		Iterator<Order> allOrdersInProductIterator = allOrders.iterator(); 
		while(allOrdersInProductIterator.hasNext()) {
			sb.append("\n" + index++ + ") " + allOrdersInProductIterator.next().toStringWithDetails() + "\n");			
		}
		
		return sb.toString();
	}
}
