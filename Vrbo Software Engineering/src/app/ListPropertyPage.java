package app;

import java.awt.Color;
import java.awt.Image;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ListPropertyPage {
	static JPanel ListPropertyPage = null;
	
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
		JButton picUploadBtn = Helper.createButton("Upload Pictures (Minimum 10)", 10, 250, 600, 150, 50);
		picUploadBtn.addActionListener(new Helper.UploadFile());
		JButton proofUploadBtn = Helper.createButton("Upload Proof of Ownership", 10, 250, 650, 150, 50);
		proofUploadBtn.addActionListener(new Helper.UploadFile());
		JButton submitBtn = Helper.createButton("Submit", 25, 215, 700, 200, 50);
		
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
		ListPropertyPage.add(proofUploadBtn);
		ListPropertyPage.add(submitBtn);
	}
}
