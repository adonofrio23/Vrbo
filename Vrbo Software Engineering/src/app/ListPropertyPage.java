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
		ListPropertyPage.setBackground(Color.decode("#0e214b"));
		createHeaderBar();
		createBody();
	}
	
	private void createHeaderBar() {
		JButton exitButton = Helper.createButton("Exit", 16, 1010, 90, 100, 30);		exitButton.addActionListener(new Helper.ExitPrompt()); // Pop-Up Window Functionality
		JButton homeButton = Helper.createButton("Home", 16, 900, 90, 100, 30);

		homeButton.addActionListener(new Helper.HomeListener());
		
		
		JLabel slogan = Helper.createLabel("Travel Better Together", 20, 130, 90, 300, 30);
		slogan.setForeground(Color.WHITE);
		JLabel logo = new JLabel();
		//Image vrboLogo = Helper.fetchImage("https://images.ctfassets.net/uylld2rxwr0n/7H3jIk9OvCrmuFpSyUrkqq/b07aa3c9796d08f2773bc54c69c666d9/Vrbo_logo_dark.png?w=960&q=50", 150, 80);
		Image vrboLogo = Helper.fetchImage("https://csvcus.homeaway.com/rsrcs-crs/cdn-logos/5.1.2/sitename/vrbo/web/image_src.png", 150, 80);
		logo.setIcon(new ImageIcon(vrboLogo));
		logo.setBounds(5, 0, 150, 200);
		
		ListPropertyPage.add(exitButton);
		ListPropertyPage.add(homeButton);
		ListPropertyPage.add(slogan);
		ListPropertyPage.add(logo);
	}
	
	private void createBody() {
		JLabel title = Helper.createLabel("List Your Property", 24, 500, 240, 200, 30);
		JLabel address = Helper.createLabel("Address of Property", 20, 320, 300, 200, 30); 
		JTextField addressField = Helper.createTextField(20, 500, 300, 200, 30);
		JLabel nBed = Helper.createLabel("Number of Beds", 20, 320, 350, 200, 30);
		JTextField nBedField = Helper.createTextField(20,  500, 350, 200, 30);
		JLabel nBath = Helper.createLabel("Number of Baths", 20, 320, 400, 200, 30);
		JTextField nBathField = Helper.createTextField(20, 500, 400, 200, 30);
		JLabel amenities = Helper.createLabel("Amenities", 20, 320, 450, 200, 30);
		JTextField amenitiesField = Helper.createTextField(20, 500, 450, 200, 30);
		JLabel description = Helper.createLabel("Description", 20, 320, 500, 200, 30);
		JTextField descriptionField = Helper.createTextField(20, 500, 500, 200, 100);
		JButton picUploadBtn = Helper.createButton("Upload Pictures (Minimum 10)", 20, 400, 610, 400, 30);
		picUploadBtn.addActionListener(new Helper.UploadFile());
		JButton proofUploadBtn = Helper.createButton("Upload Proof of Ownership", 20, 400, 650, 400, 30);
		proofUploadBtn.addActionListener(new Helper.UploadFile());
		JButton submitBtn = Helper.createButton("Submit", 20, 550, 700, 100, 30);

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
