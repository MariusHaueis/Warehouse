package CompanyParts;
import java.util.LinkedList;
import java.util.Queue;

import Items.Item;
/**
 * @author Marius Haueis
 * @version 23.03.2021
 */
/**
 * Represents a buffer for temporary storage of items.
 * The waiting List stores all received but not edited orders.
 * @author Marius Haueis
 * @version 01.02.2021
 */
public final class Buffer {
	private Queue<Item> waitingList;

	public Buffer() {
		waitingList = new LinkedList<Item>();		
	}
/**
 * This method adds a given item to the buffer.
 * @requires Object of the typ item
 * @requires item!= null
 * @ensures item has been added
 * @param item that should be send to the customer. Parametertype = Item
 */
	public void bufferItem(final Item item) {
		waitingList.add(item);		
	}
	/**
	 * This method releases one item of the buffer.
	 * @requires waitingList != null
	 * @requires Queue has minimum of one item
	 * @ensures /result == queue.isEmpty();
	 * @return the first item in the waiting List. Returntype = Item
	 */
	public Item releaseItem() {
		if(waitingList.isEmpty()) {
		throw new IllegalStateException("The Queue for shipping is empty.");
		}
		return waitingList.poll();		
	}
	/**
	 * Checks whether the waiting List is empty or not.
	 * @requires waitingList != null
	 * @return whether the list has objects in it or not. Returntype = boolean
	 */
	public boolean isEmpty() {		
		return waitingList.isEmpty();
	}

}
