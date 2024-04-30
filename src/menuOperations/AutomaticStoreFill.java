package menuOperations;

import ShippingCompany.DHL;
import ShippingCompany.FedEx;
import customer.Customer;
import customer.CustomerCreator;
import invoice.InvoiceAccountantFormat;
import invoice.InvoiceCustomerFormat;
import main.StoreManagement;
import orders.OrderCreator;
import orders.OrderWithShipping;
import orders.OrderWithoutShipping;
import orders.ShippingDetails;
import products.ProductCreator;
import products.ProductSoldInStore;
import products.ProductSoldThroughWebsite;
import products.ProductSoldToWholesalers;

public class AutomaticStoreFill implements ICommandAutomaticStoreFill{
	private static StoreManagement storeManagement = StoreManagement.getStoreManagement();	
	private ProductCreator productCreator = new ProductCreator();
	private OrderCreator orderCreator = new OrderCreator();
	private CustomerCreator customerCreator = new CustomerCreator();
		
	// 3 ProductSoldInStore hard-coded objects 
	ProductSoldInStore productSoldInStore1 = productCreator.createProductSoldInStore("AAAA", "Book", 10, 20, 50);
	ProductSoldInStore productSoldInStore2 = productCreator.createProductSoldInStore("AAAB", "DVD", 5, 15, 100);
	ProductSoldInStore productSoldInStore3 = productCreator.createProductSoldInStore("AAAC", "Headphones", 30, 60, 30);
	
	// 3 ProductSoldThroughWebsite hard-coded objects
	
	ProductSoldThroughWebsite productSoldThroughWebsite1 = productCreator.createProductSoldThroughWebsite("BBBA", "Mixer", 100, 150, 30, "UK", 5);
	ProductSoldThroughWebsite productSoldThroughWebsite2 = productCreator.createProductSoldThroughWebsite("BBBB", "Laptop", 500, 800, 50, "USA", 2.5);
	ProductSoldThroughWebsite productSoldThroughWebsite3 = productCreator.createProductSoldThroughWebsite("BBBC", "Smartphone", 300, 600, 100, "France", 0.2);
	
	// 3 ProductSoldToWholesalers hard-coded objects
	ProductSoldToWholesalers productSoldToWholesalers1 = productCreator.createProductSoldToWholesalers("CCCA", "T-Shirt", 5, 10, 200);
	ProductSoldToWholesalers productSoldToWholesalers2 = productCreator.createProductSoldToWholesalers("CCCB", "Jeans", 15, 30, 150);
	ProductSoldToWholesalers productSoldToWholesalers3 = productCreator.createProductSoldToWholesalers("CCCC", "Sneakers", 20, 50, 100);
	
	DHL dhl = new DHL("Rom", "05023345");
	FedEx fedex = new FedEx("Kobi", "05467728");
	
	
	// shipping details for orders with productSoldThroughWebsite1
	ShippingDetails shippingDetails1 = new ShippingDetails(
			productSoldThroughWebsite1.getSellingPrice(),
			dhl.standardShippingFees(productSoldThroughWebsite1),
			"standard", dhl.getClass().getSimpleName(), dhl);
	
	ShippingDetails shippingDetails2 = new ShippingDetails(
			productSoldThroughWebsite1.getSellingPrice(),
			dhl.expressShippingFees(productSoldThroughWebsite1),
			"express", dhl.getClass().getSimpleName(), dhl);
	
	ShippingDetails shippingDetails3 = new ShippingDetails(
			productSoldThroughWebsite1.getSellingPrice(),
			fedex.expressShippingFees(productSoldThroughWebsite1),
			"express", fedex.getClass().getSimpleName(), fedex);
	
	// shipping details for orders with productSoldThroughWebsite2
	ShippingDetails shippingDetails4 = new ShippingDetails(
			productSoldThroughWebsite2.getSellingPrice(),
			dhl.standardShippingFees(productSoldThroughWebsite2),
			"standard", dhl.getClass().getSimpleName(), dhl);
	
	ShippingDetails shippingDetails5 = new ShippingDetails(
			productSoldThroughWebsite2.getSellingPrice(),
			dhl.expressShippingFees(productSoldThroughWebsite2),
			"express", dhl.getClass().getSimpleName(), dhl);
	
	ShippingDetails shippingDetails6 = new ShippingDetails(
			productSoldThroughWebsite2.getSellingPrice(),
			fedex.expressShippingFees(productSoldThroughWebsite2),
			"express", fedex.getClass().getSimpleName(), fedex);

	// shipping details for orders with productSoldThroughWebsite3
	ShippingDetails shippingDetails7 = new ShippingDetails(
			productSoldThroughWebsite3.getSellingPrice(),
			dhl.standardShippingFees(productSoldThroughWebsite3),
			"standard", dhl.getClass().getSimpleName(), dhl);
	
	ShippingDetails shippingDetails8 = new ShippingDetails(
			productSoldThroughWebsite3.getSellingPrice(),
			dhl.expressShippingFees(productSoldThroughWebsite3),
			"express", dhl.getClass().getSimpleName(), dhl);
	
	ShippingDetails shippingDetails9 = new ShippingDetails(
			productSoldThroughWebsite3.getSellingPrice(),
			fedex.expressShippingFees(productSoldThroughWebsite3),
			"express", fedex.getClass().getSimpleName(), fedex);

	
	Customer customer1 = customerCreator.createCustomer("Ben", "05232455");
	Customer customer2 = customerCreator.createCustomer("Neta", "05456777");
	Customer customer3 = customerCreator.createCustomer("Roni", "05266711");
	Customer customer4 = customerCreator.createCustomer("Guy", "05589100");
	Customer customer5 = customerCreator.createCustomer("Yuval", "05281566");
	Customer customer6 = customerCreator.createCustomer("Lior", "05021299");
	Customer customer7 = customerCreator.createCustomer("Dan", "05021277");
	Customer customer8 = customerCreator.createCustomer("Shani", "05021291");
	Customer customer9 = customerCreator.createCustomer("Afik", "05023092");

	// for product 1
	OrderWithoutShipping orderWithoutShipping1 = orderCreator.createOrderWithoutShipping("ORDA", customer1, 1, productSoldInStore1);
	OrderWithoutShipping orderWithoutShipping2 = orderCreator.createOrderWithoutShipping("ORDB", customer2, 2, productSoldInStore1);
	OrderWithoutShipping orderWithoutShipping3 = orderCreator.createOrderWithoutShipping("ORDC", customer3, 10, productSoldInStore1);
	
	// for product 2
	OrderWithoutShipping orderWithoutShipping4 = orderCreator.createOrderWithoutShipping("ORDD", customer1, 4, productSoldInStore2);
	OrderWithoutShipping orderWithoutShipping5 = orderCreator.createOrderWithoutShipping("ORDE", customer2, 20, productSoldInStore2);
	OrderWithoutShipping orderWithoutShipping6 = orderCreator.createOrderWithoutShipping("ORDF", customer3, 1, productSoldInStore2);

	// for product 3
	OrderWithoutShipping orderWithoutShipping7 = orderCreator.createOrderWithoutShipping("ORDG", customer1, 15, productSoldInStore3);
	OrderWithoutShipping orderWithoutShipping8 = orderCreator.createOrderWithoutShipping("ORDH", customer2, 5, productSoldInStore3);
	OrderWithoutShipping orderWithoutShipping9 = orderCreator.createOrderWithoutShipping("ORDI", customer3, 2, productSoldInStore3);
	
	OrderWithoutShipping orderWithoutShipping10 = orderCreator.createOrderWithoutShipping("ORDJ", customer1, 1, productSoldToWholesalers1);
	OrderWithoutShipping orderWithoutShipping11 = orderCreator.createOrderWithoutShipping("ORDK", customer2, 3, productSoldToWholesalers1);
	OrderWithoutShipping orderWithoutShipping12 = orderCreator.createOrderWithoutShipping("ORDL", customer3, 7, productSoldToWholesalers1);
	
	OrderWithoutShipping orderWithoutShipping13 = orderCreator.createOrderWithoutShipping("ORDM", customer1, 8, productSoldToWholesalers2);
	OrderWithoutShipping orderWithoutShipping14 = orderCreator.createOrderWithoutShipping("ORDN", customer2, 30, productSoldToWholesalers2);
	OrderWithoutShipping orderWithoutShipping15 = orderCreator.createOrderWithoutShipping("ORDO", customer3, 20, productSoldToWholesalers2);
	
	OrderWithoutShipping orderWithoutShipping16 = orderCreator.createOrderWithoutShipping("ORDP", customer1, 11, productSoldToWholesalers3);
	OrderWithoutShipping orderWithoutShipping17 = orderCreator.createOrderWithoutShipping("ORDQ", customer2, 4, productSoldToWholesalers3);
	OrderWithoutShipping orderWithoutShipping18 = orderCreator.createOrderWithoutShipping("ORDR", customer3, 5, productSoldToWholesalers3);
		
	OrderWithShipping orderWithShipping1 = orderCreator.createOrderWithShipping("ORDAA", customer1, 3, productSoldThroughWebsite1, shippingDetails1);
	OrderWithShipping orderWithShipping2 = orderCreator.createOrderWithShipping("ORDAB", customer2, 5, productSoldThroughWebsite1, shippingDetails2);
	OrderWithShipping orderWithShipping3 = orderCreator.createOrderWithShipping("ORDAC", customer3, 6, productSoldThroughWebsite1, shippingDetails3);
	
	OrderWithShipping orderWithShipping4 = orderCreator.createOrderWithShipping("ORDBA", customer1, 9, productSoldThroughWebsite2, shippingDetails4);
	OrderWithShipping orderWithShipping5 = orderCreator.createOrderWithShipping("ORDBB", customer2, 10, productSoldThroughWebsite2, shippingDetails5);
	OrderWithShipping orderWithShipping6 = orderCreator.createOrderWithShipping("ORDBC", customer3, 12, productSoldThroughWebsite2, shippingDetails6);
	
	OrderWithShipping orderWithShipping7 = orderCreator.createOrderWithShipping("ORDCA", customer1, 3, productSoldThroughWebsite3, shippingDetails7);
	OrderWithShipping orderWithShipping8 = orderCreator.createOrderWithShipping("ORDCB", customer2, 14, productSoldThroughWebsite3, shippingDetails8);
	OrderWithShipping orderWithShipping9 = orderCreator.createOrderWithShipping("ORDCC", customer3, 22, productSoldThroughWebsite3, shippingDetails9);
		
	InvoiceAccountantFormat invoiceAccountantFormat1 = new InvoiceAccountantFormat(orderWithoutShipping1);
	InvoiceAccountantFormat invoiceAccountantFormat2 = new InvoiceAccountantFormat(orderWithoutShipping2);
	InvoiceAccountantFormat invoiceAccountantFormat3 = new InvoiceAccountantFormat(orderWithoutShipping3);
	InvoiceAccountantFormat invoiceAccountantFormat4 = new InvoiceAccountantFormat(orderWithoutShipping4);
	InvoiceAccountantFormat invoiceAccountantFormat5 = new InvoiceAccountantFormat(orderWithoutShipping5);
	InvoiceAccountantFormat invoiceAccountantFormat6 = new InvoiceAccountantFormat(orderWithoutShipping6);
	InvoiceAccountantFormat invoiceAccountantFormat7 = new InvoiceAccountantFormat(orderWithoutShipping7);
	InvoiceAccountantFormat invoiceAccountantFormat8 = new InvoiceAccountantFormat(orderWithoutShipping8);
	InvoiceAccountantFormat invoiceAccountantFormat9 = new InvoiceAccountantFormat(orderWithoutShipping9);
	
	InvoiceCustomerFormat invoiceCustomerFormat1 = new InvoiceCustomerFormat(orderWithoutShipping10);
	InvoiceCustomerFormat invoiceCustomerFormat2 = new InvoiceCustomerFormat(orderWithoutShipping11);
	InvoiceCustomerFormat invoiceCustomerFormat3 = new InvoiceCustomerFormat(orderWithoutShipping12);
	InvoiceCustomerFormat invoiceCustomerFormat4 = new InvoiceCustomerFormat(orderWithoutShipping13);
	InvoiceCustomerFormat invoiceCustomerFormat5 = new InvoiceCustomerFormat(orderWithoutShipping14);
	InvoiceCustomerFormat invoiceCustomerFormat6 = new InvoiceCustomerFormat(orderWithoutShipping15);
	InvoiceCustomerFormat invoiceCustomerFormat7 = new InvoiceCustomerFormat(orderWithoutShipping16);
	InvoiceCustomerFormat invoiceCustomerFormat8 = new InvoiceCustomerFormat(orderWithoutShipping17);
	InvoiceCustomerFormat invoiceCustomerFormat9 = new InvoiceCustomerFormat(orderWithoutShipping18);
	

	
		

	@Override
	public void execute() {
		setHardCodedInvoiceForOrder();
		setHardCodedShippingDetailsForOrder();		
		
		addHardCodedProductsToProductsArray();
		addHardCodedOrdersToOrdersOfProductArray();
		addHardCodedOrdersToOrdersArray();
		addHardCodedOrdersToStack();
		addHardCodedShippingCompaniesToHashset();		
	}
	
	public void setHardCodedInvoiceForOrder() {
		orderWithoutShipping1.setInvoiceForOrder(invoiceAccountantFormat1);
		orderWithoutShipping2.setInvoiceForOrder(invoiceAccountantFormat2);
		orderWithoutShipping3.setInvoiceForOrder(invoiceAccountantFormat3);
		orderWithoutShipping4.setInvoiceForOrder(invoiceAccountantFormat4);
		orderWithoutShipping5.setInvoiceForOrder(invoiceAccountantFormat5);
		orderWithoutShipping6.setInvoiceForOrder(invoiceAccountantFormat6);
		orderWithoutShipping7.setInvoiceForOrder(invoiceAccountantFormat7);
		orderWithoutShipping8.setInvoiceForOrder(invoiceAccountantFormat8);
		orderWithoutShipping9.setInvoiceForOrder(invoiceAccountantFormat9);

		orderWithoutShipping10.setInvoiceForOrder(invoiceCustomerFormat1);
		orderWithoutShipping11.setInvoiceForOrder(invoiceCustomerFormat2);
		orderWithoutShipping12.setInvoiceForOrder(invoiceCustomerFormat3);
		orderWithoutShipping13.setInvoiceForOrder(invoiceCustomerFormat4);
		orderWithoutShipping14.setInvoiceForOrder(invoiceCustomerFormat5);
		orderWithoutShipping15.setInvoiceForOrder(invoiceCustomerFormat6);
		orderWithoutShipping16.setInvoiceForOrder(invoiceCustomerFormat7);
		orderWithoutShipping17.setInvoiceForOrder(invoiceCustomerFormat8);
		orderWithoutShipping18.setInvoiceForOrder(invoiceCustomerFormat9);
	}
	
	public void setHardCodedShippingDetailsForOrder() {
		orderWithShipping1.setShippingDetails(shippingDetails1);
		orderWithShipping2.setShippingDetails(shippingDetails2);
		orderWithShipping3.setShippingDetails(shippingDetails3);
		orderWithShipping4.setShippingDetails(shippingDetails4);
		orderWithShipping5.setShippingDetails(shippingDetails5);
		orderWithShipping6.setShippingDetails(shippingDetails6);
		orderWithShipping7.setShippingDetails(shippingDetails7);
		orderWithShipping8.setShippingDetails(shippingDetails8);
		orderWithShipping9.setShippingDetails(shippingDetails9);
	}
	
	public void addHardCodedProductsToProductsArray() {
		storeManagement.getAllProductsInStore().add(productSoldInStore1);
		storeManagement.getAllProductsInStore().add(productSoldInStore2);
		storeManagement.getAllProductsInStore().add(productSoldInStore3);
		
		storeManagement.getAllProductsInStore().add(productSoldThroughWebsite1);
		storeManagement.getAllProductsInStore().add(productSoldThroughWebsite2);
		storeManagement.getAllProductsInStore().add(productSoldThroughWebsite3);
		
		storeManagement.getAllProductsInStore().add(productSoldToWholesalers1);
		storeManagement.getAllProductsInStore().add(productSoldToWholesalers2);
		storeManagement.getAllProductsInStore().add(productSoldToWholesalers3);
	}
	
	public void addHardCodedOrdersToOrdersArray() {
		storeManagement.getAllOrdersMade().add(orderWithShipping1);
		storeManagement.getAllOrdersMade().add(orderWithShipping2);
		storeManagement.getAllOrdersMade().add(orderWithShipping3);
		storeManagement.getAllOrdersMade().add(orderWithShipping4);
		storeManagement.getAllOrdersMade().add(orderWithShipping5);
		storeManagement.getAllOrdersMade().add(orderWithShipping6);
		storeManagement.getAllOrdersMade().add(orderWithShipping7);
		storeManagement.getAllOrdersMade().add(orderWithShipping8);
		storeManagement.getAllOrdersMade().add(orderWithShipping9);
		
		storeManagement.getAllOrdersMade().add(orderWithoutShipping1);
		storeManagement.getAllOrdersMade().add(orderWithoutShipping2);
		storeManagement.getAllOrdersMade().add(orderWithoutShipping3);
		storeManagement.getAllOrdersMade().add(orderWithoutShipping4);
		storeManagement.getAllOrdersMade().add(orderWithoutShipping5);
		storeManagement.getAllOrdersMade().add(orderWithoutShipping6);
		storeManagement.getAllOrdersMade().add(orderWithoutShipping7);
		storeManagement.getAllOrdersMade().add(orderWithoutShipping8);
		storeManagement.getAllOrdersMade().add(orderWithoutShipping9);
		storeManagement.getAllOrdersMade().add(orderWithoutShipping10);
		storeManagement.getAllOrdersMade().add(orderWithoutShipping11);
		storeManagement.getAllOrdersMade().add(orderWithoutShipping12);
		storeManagement.getAllOrdersMade().add(orderWithoutShipping13);
		storeManagement.getAllOrdersMade().add(orderWithoutShipping14);
		storeManagement.getAllOrdersMade().add(orderWithoutShipping15);
		storeManagement.getAllOrdersMade().add(orderWithoutShipping16);
		storeManagement.getAllOrdersMade().add(orderWithoutShipping17);
		storeManagement.getAllOrdersMade().add(orderWithoutShipping18);
	}
		
	
	public void addHardCodedOrdersToOrdersOfProductArray() {
		productSoldInStore1.addOrderToProduct(orderWithoutShipping1);
		productSoldInStore1.addOrderToProduct(orderWithoutShipping2);
		productSoldInStore1.addOrderToProduct(orderWithoutShipping3);
		
		productSoldInStore2.addOrderToProduct(orderWithoutShipping4);
		productSoldInStore2.addOrderToProduct(orderWithoutShipping5);
		productSoldInStore2.addOrderToProduct(orderWithoutShipping6);
		
		productSoldInStore3.addOrderToProduct(orderWithoutShipping7);
		productSoldInStore3.addOrderToProduct(orderWithoutShipping8);
		productSoldInStore3.addOrderToProduct(orderWithoutShipping9);
		
		productSoldToWholesalers1.addOrderToProduct(orderWithoutShipping10);
		productSoldToWholesalers1.addOrderToProduct(orderWithoutShipping11);
		productSoldToWholesalers1.addOrderToProduct(orderWithoutShipping12);
		
		productSoldToWholesalers2.addOrderToProduct(orderWithoutShipping13);
		productSoldToWholesalers2.addOrderToProduct(orderWithoutShipping14);
		productSoldToWholesalers2.addOrderToProduct(orderWithoutShipping15);
		
		productSoldToWholesalers3.addOrderToProduct(orderWithoutShipping16);
		productSoldToWholesalers3.addOrderToProduct(orderWithoutShipping17);
		productSoldToWholesalers3.addOrderToProduct(orderWithoutShipping18);
		
		productSoldThroughWebsite1.addOrderToProduct(orderWithShipping1);
		productSoldThroughWebsite1.addOrderToProduct(orderWithShipping2);
		productSoldThroughWebsite1.addOrderToProduct(orderWithShipping3);
		
		productSoldThroughWebsite2.addOrderToProduct(orderWithShipping4);
		productSoldThroughWebsite2.addOrderToProduct(orderWithShipping5);
		productSoldThroughWebsite2.addOrderToProduct(orderWithShipping6);
		
		productSoldThroughWebsite3.addOrderToProduct(orderWithShipping7);
		productSoldThroughWebsite3.addOrderToProduct(orderWithShipping8);
		productSoldThroughWebsite3.addOrderToProduct(orderWithShipping9);
	}
	
	public void addHardCodedShippingCompaniesToHashset() {
		storeManagement.getShippingManagementFromStoreManagements().getAllShippingCompaniesObservers().add(fedex);
		storeManagement.getShippingManagementFromStoreManagements().getAllShippingCompaniesObservers().add(dhl);
	}
	
	public void addHardCodedOrdersToStack() {
		storeManagement.getStackOrders().add(orderWithoutShipping1);
		storeManagement.getStackOrders().add(orderWithoutShipping2);
		storeManagement.getStackOrders().add(orderWithoutShipping3);
		storeManagement.getStackOrders().add(orderWithoutShipping4);
		storeManagement.getStackOrders().add(orderWithoutShipping5);
		storeManagement.getStackOrders().add(orderWithoutShipping6);
		storeManagement.getStackOrders().add(orderWithoutShipping7);
		storeManagement.getStackOrders().add(orderWithoutShipping8);
		storeManagement.getStackOrders().add(orderWithoutShipping9);
		storeManagement.getStackOrders().add(orderWithoutShipping10);
		storeManagement.getStackOrders().add(orderWithoutShipping11);
		storeManagement.getStackOrders().add(orderWithoutShipping12);
		storeManagement.getStackOrders().add(orderWithoutShipping13);
		storeManagement.getStackOrders().add(orderWithoutShipping14);
		storeManagement.getStackOrders().add(orderWithoutShipping15);
		storeManagement.getStackOrders().add(orderWithoutShipping16);
		storeManagement.getStackOrders().add(orderWithoutShipping17);
		storeManagement.getStackOrders().add(orderWithoutShipping18);
		
		storeManagement.getStackOrders().add(orderWithShipping1);
		storeManagement.getStackOrders().add(orderWithShipping2);
		storeManagement.getStackOrders().add(orderWithShipping3);
		storeManagement.getStackOrders().add(orderWithShipping4);
		storeManagement.getStackOrders().add(orderWithShipping5);
		storeManagement.getStackOrders().add(orderWithShipping6);
		storeManagement.getStackOrders().add(orderWithShipping7);
		storeManagement.getStackOrders().add(orderWithShipping8);
		storeManagement.getStackOrders().add(orderWithShipping9);
	}	
}
