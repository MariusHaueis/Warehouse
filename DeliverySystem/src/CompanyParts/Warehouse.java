package CompanyParts;

import java.util.ArrayList;
import java.util.HashMap;

import java.util.Optional;

import Items.Item;
import Items.ItemIdentification;

/**
 * Represents a warehouse that can hold a fixed number of items.
 * 
 * @author Marius Haueis
 * @version 02.02.2021
 */
public final class Warehouse {
	// @ private instance invariant capacity > 0;
	// @ private instance invariant numberOfItems >= 0;
	// @ private instance invariant numberOfItems <= capacity;
	private final int capacity;
	private int numberOfItems;
	private final ArrayList<Optional<Item>> stock;
	private final HashMap<ItemIdentification, Integer> compartement;


	/*
	 * @
	 * 
	 * @ requires capacity > 0;
	 * 
	 * @ ensures this.capacity == capacity;
	 * 
	 * @
	 */
	/**
	 * Creates a new warehouse with the given capacity.
	 * 
	 * @param capacity Capacity of the warehouse in items.
	 * @throws IllegalArgumentException If the preconditions are not satisfied.
	 */
	public Warehouse(final int capacity) {
		if (capacity <= 0) {
			throw new IllegalArgumentException("A warehouse must have a minimum capacity of 1.");
		}
		this.capacity = capacity;
		numberOfItems = 0;
		stock = new ArrayList<Optional<Item>>();
		/*
		 * @
		 * 
		 * @ loop_invariant adds i empty objects to the stock;
		 * 
		 * @ decreasing capacity - i;
		 */
		for (int i = 0; i < capacity; i++) {
			stock.add(Optional.empty());
		}
		compartement = new HashMap<ItemIdentification, Integer>();
	}
	

	/**
	 * This method add items to the Warehouse.
	 * 
	 * @requires item != null
	 * @ensures number of items +1 
	 * @param The item that shall be added to the Warehouse. Parametertype = item
	 */
	public final void addItem(final Item item) {
		if(!stock.contains(Optional.empty())) {
			throw new IllegalArgumentException("The stock has been filled to the brim.");
		}
			int freeCompartement = stock.indexOf(Optional.empty());			
			stock.set(freeCompartement, Optional.of(item));
			compartement.put(item.getIdentification(), freeCompartement);
			numberOfItems++;
			System.out.println("Item has been added.");
			
		}	
	/**
	 * This method removes on item from the Warehouse.
	 * 
	 * @requires item != null
	 * @requires 0 <= compartmentNumber < capacity
	 * @ensures stock.get(compartmentNumber).isEmpty(); 
	 * @ensures index does not contain a mapping for the removed items identification
	 * 
	 * @param compartmentNumber The compartment number to make empty
	 * @throws
	 */
	public final void removeItem(final int compartmentNumber) {
		if (stock.get(compartmentNumber).equals(Optional.empty())) {
			throw new IllegalArgumentException("There is no such item.");
		}
		compartement.remove(stock.get(compartmentNumber).get().getIdentification());
		stock.set(compartmentNumber, Optional.empty());
		numberOfItems--;
		System.out.println("Item has been sended.");
	}
	/**
	 * This method lets you see which item is at any given compartmentNumber.
	 * 
	 * @requires stock != null
	 * @requires item at the comparmentNumber != null
	 * @ensures item at given departmentNumber has been returned
	 * @param compartmentNumber
	 * @return the item at the given compartmentNumber
	 */
	public final Optional<Item> getItem(final int compartmentNumber) {
		if (stock.get(compartmentNumber).equals(Optional.empty()) || stock.isEmpty()) {
			throw new IllegalArgumentException("There is no such item.");
		}
		Optional<Item> selectedItem = stock.get(compartmentNumber);
		return selectedItem;
	}

	/**
	 * This method let you see the number where any given item is.
	 * 
	 * @requires
	 * @ensures Compartement number of item is shown
	 * @param identification
	 * @return The number where any given item is.
	 */
	public final Optional<Integer> getCompartmentNumberOf(final ItemIdentification identification) {
		if (identification == null) {
			throw new IllegalArgumentException("There has to be an identification.");
		} else {
			return Optional.of(compartement.get(identification));
		}
	}
	/**
	 * One item of the warehouse. 
	 * @return the item object
	 */
	public final Item copyItem() {
		Optional<Item> clone = stock.get(getNumberOfItems()-1);	
		return clone.get();
	}

	/*
	 * @
	 * 
	 * @ ensures \result == capacity;
	 * 
	 * @
	 */
	/**
	 * @return The capacity of this warehouse in items.
	 */
	public /* @ pure @ */ int getCapacity() {
		return capacity;
	}

	/*
	 * @
	 * 
	 * @ ensures \result == numberOfItems;
	 * 
	 * @
	 */
	/**
	 * @return The number of items in this warehouse.
	 */
	public /* @ pure @ */ int getNumberOfItems() {
		return numberOfItems;
	}
	
}
