package app;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class ListingPageTemplate {
public static JPanel ListingPage = null;
private static String data;
private static String location;
	
	ListingPageTemplate(String data, String location) {
		ListingPage = new JPanel(null);
		ListingPage.setName("Listing Page");
		ListingPage.setBackground(Color.decode("#0e214b"));
		ListingPageTemplate.data = data;
		ListingPageTemplate.location = location;

		createHeaderBar();
		createBody();
	}
	
	private void createHeaderBar() {
		JButton exitButton = Helper.createButton("Exit", 16, 1010, 90, 100, 30);		
		exitButton.addActionListener(new Helper.ExitPrompt()); // Pop-Up Window Functionality
		JButton homeButton = Helper.createButton("Home", 16, 900, 90, 100, 30);

		homeButton.addActionListener(new Helper.HomeListener());
		
		
		JLabel slogan = Helper.createLabel("Travel Better Together", 20, 130, 90, 300, 30);
		slogan.setForeground(Color.WHITE);
		JLabel logo = new JLabel();
		//Image vrboLogo = Helper.fetchImage("https://images.ctfassets.net/uylld2rxwr0n/7H3jIk9OvCrmuFpSyUrkqq/b07aa3c9796d08f2773bc54c69c666d9/Vrbo_logo_dark.png?w=960&q=50", 150, 80);
		Image vrboLogo = Helper.fetchImage("https://csvcus.homeaway.com/rsrcs-crs/cdn-logos/5.1.2/sitename/vrbo/web/image_src.png", 150, 80);
		logo.setIcon(new ImageIcon(vrboLogo));
		logo.setBounds(5, 0, 150, 200);
		
		ListingPage.add(exitButton);
		ListingPage.add(homeButton);
		ListingPage.add(slogan);
		ListingPage.add(logo);
	}
	
	private void createBody() {		
		JLabel locationLabel = Helper.createLabel(ListingPageTemplate.location, 36, 100, 180, 400, 50);
		
		JLabel locationPic = Helper.createLabel("", 20, 100, 250, 450, 350);
		Image locationImage = Helper.fetchImage(ListingsByLocation.parse(data, "link"), 300, 225).getScaledInstance(450, 350, Image.SCALE_DEFAULT);;
		locationPic.setIcon(new ImageIcon(locationImage));
		
		JLabel address = Helper.createLabel(ListingsByLocation.parse(data, "address"), 28, 100, 580, 300, 100);
		JLabel beds = Helper.createLabel(ListingsByLocation.parse(data, "beds") + " Beds", 24, 100, 620, 300, 100);
		JLabel baths = Helper.createLabel(ListingsByLocation.parse(data, "baths") + " Baths", 24, 100, 660, 300, 100);
		JLabel price = Helper.createLabel("$" + ListingsByLocation.parse(data, "price"), 24, 100, 700, 300, 100);
		
		JLabel about = Helper.createLabel("About This Rental", 28, 650, 250, 300, 40);
		JTextArea description = Helper.createTextArea(ListingsByLocation.parse(data, "description"), 20, 650, 325, 420, 150);
		description.setEditable(false);	
		description.setLineWrap(true);
		description.setWrapStyleWord(true);
		description.setBackground(Color.WHITE);
		
		
		JButton book = Helper.createButton("Book", 40, 800, 500, 150, 100);

		
		book.addActionListener(new ActionListener() {
			@Override public void actionPerformed(ActionEvent e) {
				
			SocketUtils.sendBookMessage("BOOKING=\"location\":" + "\"" + location + "\"");
			String validation = SocketUtils.receiveMessage();
				
				if (validation.equals("INVALID")) {
					String[] options = {"OK"};
					JOptionPane.showOptionDialog(
		 					null, 

		 					"Error: Invalid booking", // Exit message

		 					"VRBO Client",  // Pop-Up Window title
		 					JOptionPane.PLAIN_MESSAGE,
		                    JOptionPane.QUESTION_MESSAGE,
		                    null,
		                    options,
		                    options[0]
		 			);
				} else if (validation.equals("BOOKED")) {
					String[] options = {"OK"};
					int res = JOptionPane.showOptionDialog(
		 					null, 

		 					"Vacation Booked Successfully!", // Exit message

		 					"VRBO Client",  // Pop-Up Window title
		 					JOptionPane.PLAIN_MESSAGE,
		                    JOptionPane.QUESTION_MESSAGE,
		                    null,
		                    options,
		                    options[0]
		 			);
					
					if (res == JOptionPane.YES_OPTION) {
						ViewManager.switchPage("Home", Home.HomePage);
					}
				}
			} 


		//ListingPage.add(location);

		}); 

		ListingPage.add(locationLabel);
		ListingPage.add(locationPic);
		ListingPage.add(address);
		ListingPage.add(beds);
		ListingPage.add(baths);
		ListingPage.add(price);
		ListingPage.add(about);
		ListingPage.add(description);
		ListingPage.add(book);
	}
}
