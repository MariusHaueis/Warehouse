package CompanyParts;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;
import Items.*;
/**
 * Represents the company from homework exercise 2.
 * 
 * @author Marius Haueis
 * @version 02.02.2021
 */
public  class Company {
	private final Warehouse penWarehouse;
	private final Warehouse triangleRulerWarehouse;
	private final Warehouse compassWarehouse;
	private final Buffer orderBuffer;
	private final Set<Customer> customerData;
	private final int penWarehouseCapacity;
	private final int triangleRulerWarehouseCapacity;
	private final int compassWarehouseCapacity;

	public Company(final int penWarehouseCapacity, final int triangleRulerWarehouseCapacity, final int compassWarehouseCapacity) {
		orderBuffer = new Buffer();
		customerData = new HashSet<Customer>();
		penWarehouse = new Warehouse(this.penWarehouseCapacity = penWarehouseCapacity);
		triangleRulerWarehouse = new Warehouse(this.triangleRulerWarehouseCapacity = triangleRulerWarehouseCapacity);
		compassWarehouse = new Warehouse(this.compassWarehouseCapacity = compassWarehouseCapacity);
	}

	/**
	 * Adds items to their Warehouses.
	 * 
	 * @requires compassWarehouse && triangleRulerWarehouse && penWarehouse != null
	 * @ensures item has been added to the Warehouse or an Exception has been
	 *          handeld
	 * @param item
	 */
	public final void handleIncomingItem(final Item item) {
		try {
			itemDistribution(item);
		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
	}

	/**
	 * Adds the items to the fitting warehouse.
	 * 
	 * @requires item != null
	 * @ensures he matching warehouse object has been attached
	 * @param item
	 */
	private final void itemDistribution(final Item item) {
		switch (item.getIdentification().getType()) {
		case COMPASS:
			compassWarehouse.addItem(item);
			break;
		case TRIANGLE_RULER:
			triangleRulerWarehouse.addItem(item);
			break;
		case PEN:
			penWarehouse.addItem(item);
			break;
		case PRESENT:
			System.out.println("This item cant be ordered");
		}
	}

	/**
	 * This method manages the order process.
	 * 
	 * @requires identification && customer != null
	 * @ensures item has been transfered to the sending List or an Exception has
	 *          been handeld
	 * @param identification
	 * @param customer
	 */
	public final void processIncomingOrder(final Item item, final Customer customer) {
		try {
			warehouseDistribution(item.getIdentification());
		} catch (IllegalArgumentException ex) {
			System.out.println(ex.getMessage());
		}catch(NullPointerException e) {
			
		}
		if (!customerData.contains(customer) && customer != null) {
			orderBuffer.bufferItem(getPresent());
			customerData.add(customer);
		}
	}
	
	/**
	 * Items are sorted based by there type.
	 * 
	 * @requires compassWarehouse && triangleRulerWarehouse && penWarehouse != null
	 * @ensures the matching warehouse object has been attached
	 * @param identification
	 */
	private final void warehouseDistribution(final ItemIdentification identification) {
		switch (identification.getType()) {
		case COMPASS:
			sorting(identification, compassWarehouse);
			break;
		case TRIANGLE_RULER:
			sorting(identification, triangleRulerWarehouse);
			break;
		case PEN:
			sorting(identification, penWarehouse);
			break;
		case PRESENT:
			System.out.println(
					"Ey man shit doesnt work like this, you cant order a present. You have to create a new customer ID and register yourself again so that you can trick my system into thinking you are a new customer.");
		}
	}

	/**
	 * Changes the collection of a item
	 * 
	 * @requires At least one item in the warehouse
	 * @ensures One item less in the warehouse
	 * @ensures one more item orderBuffer
	 * @param identification
	 * @param warehouse
	 */
	private final void sorting(final ItemIdentification identification, Warehouse warehouse) {
		int compartementInteger = 0;
		try {
			compartementInteger = (warehouse.getCompartmentNumberOf(identification)).get();
			orderBuffer.bufferItem(warehouse.getItem(compartementInteger).get());
			warehouse.removeItem(compartementInteger);
		} catch (NullPointerException e) {
			System.out.println("The item is not contained in the warehouse.");
		}		
	}

	/*
	 * @
	 * 
	 * @ ensures \result != null;
	 * 
	 * @ ensures \result.getIdentification().getType() == ItemType.PRESENT;
	 * 
	 * @
	 */
	/**
	 * Generates a marketing present item for exercise 2i.
	 * 
	 * @return A marketing present item.
	 */
	private /* @ pure @ */ Item getPresent() {
		System.out.println("Geschenk versendet.");
		ItemType type = ItemType.PRESENT;
		int itemNumber = new Random().nextInt();
		ItemIdentification identification = new ItemIdentification(type, itemNumber);
		return new Item(identification);
	}

	/*
	 * @
	 * 
	 * @ ensures \old(orderBuffer.isEmpty()) <==> \result.isEmpty();
	 * 
	 * @
	 */
	/**
	 * Takes the next available item for packaging and returns it.
	 * 
	 * @return Optional next available item for packaging, or an empty Optional if
	 *         there is none.
	 */
	public Optional<Item> takeItemForPackaging() {
		if (orderBuffer.isEmpty()) {
			return Optional.empty();
		} else {
			return Optional.of(orderBuffer.releaseItem());
		}
	}
	/**
	 * Returns a item within the associated Warehouse. The item is just returned not removed!!
	 * @param The type of the wanted item. Parametertype = ItemType
	 * @return The item that is in Warehouse. ReturnType = Item
	 */
	public Item copyWarehouse(final ItemType itemType) {
		try {
		switch (itemType) {
		case COMPASS:
			return compassWarehouse.copyItem();
		case TRIANGLE_RULER:			
			return triangleRulerWarehouse.copyItem();
		case PEN:			
			return penWarehouse.copyItem();
		}		
		}catch(IndexOutOfBoundsException e) {
			System.out.println("The stock is empty.");
		}
		return null;
	}
	/**
	 * Checks if a customer is in the list.
	 * 
	 * @param A name. Parametertype = String
	 * @return whether a customer is saved in the customer database. Returntype = boolean
	 */
	public boolean newCustomer(String name) {
		if(customerData.stream().map(cust -> cust.getName() == name).collect(Collectors.toList()).isEmpty()) {		
				return true;
		}			
		return false;
	}	
	/**
	 * Counts the customers.
	 * 
	 * @return number of customers. Returntype = Integer
	 */
	public Integer customerSize() {
		return customerData.size();		
	}	
	/**
	 * TODO check if this method is necessary
	 * Returns all of the customers on by one.
	 * 
	 * @return The customers
	 */
	public Customer returnSortedCustomerData() {
		while(customerData.iterator().hasNext()) {
		return customerData.iterator().next();
		}
		return null;
	}
}
