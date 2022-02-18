package vrbo;

import java.awt.Color;
import java.awt.Image;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class listingPage extends Window {
	public static final long serialVersionUID = 1000L;
	
	listingPage(int width, int height, String name) {
		super.width = width;
		super.height = height;
		super.name = name;
		createHeaderBar();
		addComponents();
		createWindow();
	}
	
	protected void createHeaderBar() {
		JButton exitButton = createButton("Exit", 16, 1070, 15, 80, 80);
		exitButton.addActionListener(new ExitPrompt()); // Pop-Up Window Functionality
		JButton homeButton = createButton("Home Page", 16, 870, 15, 150, 80);
		homeButton.addActionListener(new HomeListener());
		
		window.add(exitButton);
		window.add(homeButton);
		
		JLabel slogan = createLabel("  Travel Better Together", 20, 250, 15, 230, 80);
		slogan.setBorder(BorderFactory.createLineBorder(Color.black));
		
		window.add(slogan);
		
		JLabel logo = new JLabel();
		Image vrboLogo = fetchImage("https://images.ctfassets.net/uylld2rxwr0n/7H3jIk9OvCrmuFpSyUrkqq/b07aa3c9796d08f2773bc54c69c666d9/Vrbo_logo_dark.png?w=960&q=50", 150, 80);
		logo.setIcon(new ImageIcon(vrboLogo));
		logo.setBounds(50, 15, 150, 80);
		
		window.add(logo);
	}
	
	protected void addComponents() {
		JLabel title = createLabel("List Your Property", 40, 500, 80, 500, 200);
		JLabel address = createLabel("Address of Property", 24, 150, 200, 250, 50);
		JTextField addressField = createTextField(24, 400, 200, 200, 50);
		JLabel nBed = createLabel("Number of Beds", 24, 150, 250, 250, 50);
		JTextField nBedField = createTextField(24, 400, 250, 200, 50);
		JLabel nBath = createLabel("Number of Baths", 24, 150, 300, 250, 50);
		JTextField nBathField = createTextField(24, 400, 300, 200, 50);
		JLabel amenities = createLabel("Amenities", 24, 150, 350, 250, 50);
		JTextField amenitiesField = createTextField(24, 400, 350, 200, 50);
		JLabel description = createLabel("Description", 24, 150, 400, 250, 50);
		JTextField descriptionField = createTextField(24, 400, 400, 200, 200);
		JButton picUploadBtn = createButton("Upload Pictures (Minimum 10)", 10, 250, 600, 150, 50);
		picUploadBtn.addActionListener(new UploadFile());
		JButton proofUploadBtn = createButton("Upload Proof of Ownership", 10, 250, 650, 150, 50);
		proofUploadBtn.addActionListener(new UploadFile());
		JButton submitBtn = createButton("Submit", 25, 215, 700, 200, 50);
		
		window.add(address);
		window.add(addressField);
		window.add(nBed);
		window.add(nBedField);
		window.add(nBath);
		window.add(nBathField);
		window.add(amenities);
		window.add(amenitiesField);
		window.add(description);
		window.add(descriptionField);
		window.add(title);
		window.add(picUploadBtn);
		window.add(proofUploadBtn);
		window.add(submitBtn);
	}
}
