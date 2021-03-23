package User;
/**
 * @author Marius Haueis
 * @version 23.03.2021
 */
import CompanyParts.*;
import Items.*;

public class WarehouseApp {
	private int numberOfIdentitys;
	private int number;
	private String name;
	Company company;

	public WarehouseApp(final int penWarehouseCapacity, final int triangleRulerWarehouseCapacity,
			final int compassWarehouseCapacity) {
		company = new Company(penWarehouseCapacity, triangleRulerWarehouseCapacity, compassWarehouseCapacity);
	}

	/**
	 * Sorts items that have been delivered to the company.
	 * 
	 * @param Type of the item. Parametertype = ItemType
	 * @param the count of the delivered items. Parametertype = Integer
	 */
	public void delivery(final ItemType type, final Integer number) {
		for (int i = 0; i < number; i++) {
			company.handleIncomingItem(new Item(new ItemIdentification(type, numberOfIdentitys)));
			numberOfIdentitys++;
		}
	}

	/**
	 * Manages outgoing products and creates new Customer objects when the customer is new.
	 * 
	 * @param Type of the item. Parametertype = ItemType
	 * @param the count of the ordered items. Parametertype = Integer
	 * @param name of the customer. Parametertype = String
	 */
	public void exits(final ItemType type, final Integer number, final String name) {
		if (company.newCustomer(name)) {
			company.processIncomingOrder(company.copyWarehouse(type), new Customer(name, company.customerSize()));
		} else {
			company.processIncomingOrder(company.copyWarehouse(type), null);
		}
		for (int i = 0; i < number - 1; i++) {
			company.processIncomingOrder(company.copyWarehouse(type), null);
		}
	}
}