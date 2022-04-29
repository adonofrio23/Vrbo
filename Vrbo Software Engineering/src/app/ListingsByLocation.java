package app;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ListingsByLocation {
	//If statement - search by location
	//If location = Tampa
		//populate Tampa listings
	//If location = NY
		//populate with NY listings
	//If location = Denver
		//populate with Denver listings
	//If location = LA
		//populate with LA listings
	
	//variables for:
		//location
		//button - picture of listing
	//construct a 
	
	String location = "";
	
	String listing1ImageURL = "";
	String listing2ImageURL = "";
	String listing3ImageURL = "";
	
	
	
	static JPanel ListingsByLocation = null;
	
	ListingsByLocation(String loc){
		location = loc;
		ListingsByLocation = new JPanel(null);
		ListingsByLocation.setName("Listings");
		createHeaderBar(location);
		createBody(location);
	}

	void createHeaderBar(String loc){
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
		
		ListingsByLocation.add(exitButton);
		ListingsByLocation.add(homeButton);
		ListingsByLocation.add(slogan);
		ListingsByLocation.add(logo);
	}
	
	void createBody(String loc){
		
		String bed;
		String bath;
		String price; 
		
		JButton rental1 = Helper.createButton("", 16, 90, 440, 300, 225);
		Image rental1image = Helper.fetchImage("", 300, 225);
		rental1.setIcon(new ImageIcon(rental1image));
		rental1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
					//bring you to the listing page of that property	
			}
		});
		
		JButton rental2 = Helper.createButton("", 16, 440, 440, 300, 225);
		Image rental2image = Helper.fetchImage("", 300, 225);
		rental2.setIcon(new ImageIcon(rental2image));
		rental2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
					
			}
		});
		
//		JButton rental3 = Helper.createButton("", 16, 790, 440, 300, 225);
//		Image rental3image = Helper.fetchImage("", 300, 225);
//		rental3.setIcon(new ImageIcon(rental2image));
//		rental3image.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e)
//			{
//				
//			}
//		});
		
		JLabel rental1location = Helper.createLabel("Tampa Bay, FL", 16, 90, 640, 300, 100);
		JLabel rental1bedbath = Helper.createLabel("4 Bed, 3.5 Bath", 14, 90, 660, 300, 100);
		JLabel retnal1price = Helper.createLabel("$499/night", 14, 90, 680, 300, 100);
		JLabel rental2location = Helper.createLabel("Denver, Colorado", 16, 440, 640, 300, 100);
		JLabel rental2bedbath = Helper.createLabel("7 Bed, 8.5 Bath", 14, 440, 660, 300, 100);
		JLabel rental2price = Helper.createLabel("$999/night", 14, 440, 680, 300, 100);
		JLabel rental3location = Helper.createLabel("San Diego, CA", 16, 790, 640, 300, 100);
		JLabel rental3bedbath = Helper.createLabel("3 Bed, 2.5 Bath", 14, 790, 660, 300, 100);
		JLabel rental3price = Helper.createLabel("$350/night", 14, 790, 680, 300, 100);
		
		if (loc == "Tampa Bay") {
			
		}
		
		else if (loc == "New York") {
			
		}
		
		else if (loc == "Denver") {
			
		}
		
		else if (loc == "LA") {
			
		}
		else {
		}
		
	}
}
