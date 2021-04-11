package User;
/**
 * @author Marius Haueis
 * @version 23.03.2021
 */
import CompanyParts.*;
import Items.*;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class WarehouseConsole {
	private WarehouseApp warehouseApp;
	private JTextField numberE;
	private JTextField productE;
	private JTextField nameE;
	private JTextField number;
	private JTextField product;
	private JButton exitButton;
	private JButton deliveryButton;
	private ItemType itemType;
	private JFrame frame;
	private JPanel panel;
	private boolean hasTakenAction = false;

	public WarehouseConsole() {
		// Object creation(SetUp)
		frame = new JFrame();
		panel = new JPanel();
		warehouseApp = new WarehouseApp(10, 20, 30);
	}
	public void showGui(){		
		exitButton = new JButton("Order");
		exitButton.setFont(new Font("SansSerif", Font.BOLD, 35));
		deliveryButton = new JButton("Delivery");
		deliveryButton.setFont(new Font("SansSerif", Font.BOLD, 35));
		numberE = new JTextField("number");
		numberE.setFont(new Font("TimesNewRoman", Font.PLAIN, 25));
		productE = new JTextField("product name");
		productE.setFont(new Font("SansSerif", Font.PLAIN, 25));
		nameE = new JTextField("your name");
		nameE.setFont(new Font("SansSerif", Font.PLAIN, 25));
		number = new JTextField("number");
		number.setFont(new Font("SansSerif", Font.PLAIN, 25));
		product = new JTextField("product name");
		product.setFont(new Font("SansSerif", Font.PLAIN, 25));

		// Inserts the object into the window.
		panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));
		panel.setLayout(new GridLayout(0, 1));
		panel.add(exitButton);
		panel.add(numberE);
		panel.add(productE);
		panel.add(nameE);
		panel.add(deliveryButton);
		panel.add(number);
		panel.add(product);
		
		// Creates the window.
				frame.setSize(2000, 1000);
				frame.add(panel, BorderLayout.CENTER);
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setTitle("Amazon");
				frame.setVisible(true);
	}
public void takeAction() {
		// ActionListeners
		exitButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == exitButton) {
					String productNameE = productE.getText().trim().toLowerCase();
					if (productNameE.contains("compass")) {
						itemType = ItemType.COMPASS;
					} else if (productNameE.contains("pen")) {
						itemType = ItemType.PEN;
					} else if (productNameE.contains("triangleruler")) {
						itemType = ItemType.TRIANGLE_RULER;
					} else {
						System.out.println("Please insert a valide value into the Producttype field.");
					}
					hasTakenAction=true;
					warehouseApp.exits(itemType, Integer.parseInt(numberE.getText()),
							String.valueOf(nameE.getAccessibleContext()));
					
				}
			}
		});

		deliveryButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == deliveryButton) {
					String productName = product.getText().trim().toLowerCase();
					if (productName.contains("compass")) {
						itemType = ItemType.COMPASS;
					} else if (productName.contains("pen")) {
						itemType = ItemType.PEN;
					} else if (productName.contains("triangleruler")) {
						itemType = ItemType.TRIANGLE_RULER;
					} else {
						System.out.println("Please insert a valide value into the Producttype field.");
					}
					hasTakenAction=true;
					warehouseApp.delivery(itemType, Integer.parseInt(number.getText()));
					
				}
			}
		});

		

		//TODO
		// Ausgabefenster bzw Bestellbestätigung
	//JOptionPane.showMessageDialog(null, "Vielen Dank für ihre Bestellung" , "Ihre Bestellung", JOptionPane.PLAIN_MESSAGE);
	}
public boolean getHasTakenAction() {
	return hasTakenAction;
}
}
