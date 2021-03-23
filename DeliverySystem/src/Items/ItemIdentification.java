package Items;
/**
 * @author Marius Haueis
 * @version 23.03.2021
 */
/**
 * Identification object for an individual item.
 */
public final class ItemIdentification {
	//@ private instance invariant type != null;
	
	private final ItemType type;
	private final int itemNumber;
	
	/*@
	  @ requires type != null;
	  @ ensures this.type == type;
	  @ ensures this.itemNumber == itemNumber;
	  @*/
	/**
	 * Creates an item identification object with the given information.
	 * @param type Type of the item.
	 * @param itemNumber Unique number of the item.
	 * @throws IllegalArgumentException If the preconditions are not satisfied.
	 */
	public ItemIdentification(final ItemType type, final int itemNumber) {
		if (type == null) {
			throw new IllegalArgumentException("Item type may not be null.");
		}
		this.type = type;
		this.itemNumber = itemNumber;
	}
	
	/*@
	  @ ensures \result == type;
	  @*/
	/**
	 * @return The item type of the identified item.
	 */
	public /*@ pure @*/ ItemType getType() {
		return type;
	}
	
	/*@
	  @ ensures \result == itemNumber;
	  @*/
	/**
	 * @return The unique number of the identified item.
	 */
	public /*@ pure @*/ int getItemNumber() {
		return itemNumber;
	}

}
