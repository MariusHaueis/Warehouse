package CompanyParts;
/**
 * @author Marius Haueis
 * @version 23.03.2021
 */
/**
 * Represents a customer who can order items.
 */
public final class Customer {
	//@ private instance invariant name != null && name.length() > 0;
	
	private final String name;
	private final int customerNumber;
	
	/*@
	  @ requires name != null && name.length() > 0;
	  @ ensures this.name == name;
	  @*/
	/**
	 * Creates a new customer with the given name.
	 * @param name Name of the customer.
	 * @throws IllegalArgumentException If the preconditions are not satisfied.
	 */
	public Customer(final String name, final int customerNumber) {
		if (name == null || name.length() == 0) {
			throw new IllegalArgumentException("Customers may not have null or empty names.");
		}
		this.name = name;
		this.customerNumber = customerNumber;
	}
	
	/*@
	  @ ensures \result == name;
	  @*/
	/**
	 * @return This customer's name.
	 */
	public /*@ pure @*/ String getName() {
		return name;
	}
	/*@
	  @ ensures \result == customerNumber;
	  @*/
	/**
	 * 
	 * @return the specified customerNumber
	 */
	public Integer getCustomerNumber() {
		return customerNumber;
	}

}
