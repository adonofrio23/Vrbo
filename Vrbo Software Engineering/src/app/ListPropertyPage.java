package app;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Base64;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ListPropertyPage {
	static JPanel ListPropertyPage = null;
	static byte[] picture = null; 
	
	ListPropertyPage() {
		ListPropertyPage = new JPanel(null);
		ListPropertyPage.setName("List Property");
		createHeaderBar();
		createBody();
	}
	
	private void createHeaderBar() {
		JButton exitButton = Helper.createButton("Exit", 16, 1070, 15, 80, 80);
		exitButton.addActionListener(new Helper.ExitPrompt()); // Pop-Up Window Functionality
		JButton homeButton = Helper.createButton("Home Page", 16, 870, 15, 150, 80);
		homeButton.addActionListener(new Helper.HomeListener());
		
		JLabel slogan = Helper.createLabel("  Travel Better Together", 20, 250, 15, 230, 80);
		slogan.setBorder(BorderFactory.createLineBorder(Color.black));
		
		JLabel logo = new JLabel();
		Image vrboLogo = Helper.fetchImage("https://images.ctfassets.net/uylld2rxwr0n/7H3jIk9OvCrmuFpSyUrkqq/b07aa3c9796d08f2773bc54c69c666d9/Vrbo_logo_dark.png?w=960&q=50", 150, 80);
		logo.setIcon(new ImageIcon(vrboLogo));
		logo.setBounds(50, 15, 150, 80);
		
		ListPropertyPage.add(exitButton);
		ListPropertyPage.add(homeButton);
		ListPropertyPage.add(slogan);
		ListPropertyPage.add(logo);
	}
	
	private void createBody() {
		JLabel title = Helper.createLabel("List Your Property", 40, 500, 80, 500, 200);
		JLabel address = Helper.createLabel("Address of Property", 24, 150, 200, 250, 50);
		JTextField addressField = Helper.createTextField(24, 400, 200, 200, 50);
		JLabel nBed = Helper.createLabel("Number of Beds", 24, 150, 250, 250, 50);
		JTextField nBedField = Helper.createTextField(24, 400, 250, 200, 50);
		JLabel nBath = Helper.createLabel("Number of Baths", 24, 150, 300, 250, 50);
		JTextField nBathField = Helper.createTextField(24, 400, 300, 200, 50);
		JLabel amenities = Helper.createLabel("Amenities", 24, 150, 350, 250, 50);
		JTextField amenitiesField = Helper.createTextField(24, 400, 350, 200, 50);
		JLabel description = Helper.createLabel("Description", 24, 150, 400, 250, 50);
		JTextField descriptionField = Helper.createTextField(24, 400, 400, 200, 200);
		JLabel price = Helper.createLabel("Price", 24, 150, 600, 250, 50);
		JTextField priceField = Helper.createTextField(24, 400, 600, 200, 50);
		JButton picUploadBtn = Helper.createButton("Upload Picture", 10, 250, 650, 150, 50);
		picUploadBtn.addActionListener(new Helper.UploadFile());
		JLabel optionMenu = Helper.createLabel("City", 20, 450, 650, 100, 30);
		optionMenu.setForeground(Color.WHITE);
		
		String[] options = {"New York City, NY", "Tampa Bay, FL", "Denver, CO", "Los Angeles, CA"};
		JComboBox<String> search = new JComboBox<>(options);
		search.setFont(new Font("Tahoma", Font.PLAIN, 15));
		search.setBounds(500, 565, 250, 220);
		
		JButton submitBtn = Helper.createButton("Submit", 25, 215, 700, 200, 50);
		submitBtn.addActionListener(new ActionListener() {
			@Override public void actionPerformed(ActionEvent e) {
				String address = addressField.getText();
				String beds = nBedField.getText();
				String baths = nBathField.getText();
				String amenities = amenitiesField.getText();
				String description = descriptionField.getText();
				String price = priceField.getText();
				String city = search.getSelectedItem().toString();
				
				SocketUtils.sendMessage("LIST=\"address\": " + address + "\", \"beds\": " + beds 
															+ "\", \"baths\": " + baths
															+ "\", \"amenities\": " + amenities
															+ "\", \"description\": " + description
															+ "\", \"price\": " + price
															+ "\", \"city\": " + city
															+ "\", \"picture\": " + Base64.getEncoder().encodeToString(picture));
				String validation = SocketUtils.receiveMessage();
				
				if (validation.equals("FAILED")) {
					String[] options = {"OK"};
					JOptionPane.showOptionDialog(
		 					null, 
		 					"Error: Failed To List Property", // Exit message
		 					"VRBO Client",  // Pop-Up Window title
		 					JOptionPane.PLAIN_MESSAGE,
		                    JOptionPane.QUESTION_MESSAGE,
		                    null,
		                    options,
		                    options[0]
		 			);
				} else if (validation.equals("SUCCESS")) {
					SocketUtils.sendMessage("Property Listed: " + address);
					addressField.setText("");
					nBedField.setText("");
					nBathField.setText("");
					amenitiesField.setText("");
					descriptionField.setText("");
					priceField.setText("");;
				}
			}
		});
		
		ListPropertyPage.add(address);
		ListPropertyPage.add(addressField);
		ListPropertyPage.add(nBed);
		ListPropertyPage.add(nBedField);
		ListPropertyPage.add(nBath);
		ListPropertyPage.add(nBathField);
		ListPropertyPage.add(amenities);
		ListPropertyPage.add(amenitiesField);
		ListPropertyPage.add(description);
		ListPropertyPage.add(descriptionField);
		ListPropertyPage.add(title);
		ListPropertyPage.add(picUploadBtn);
		ListPropertyPage.add(price);
		ListPropertyPage.add(priceField);
		ListPropertyPage.add(submitBtn);
		ListPropertyPage.add(optionMenu);
		ListPropertyPage.add(search);
	}
}
