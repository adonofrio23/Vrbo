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
	
	String location;
	
	static JPanel ListingsByLocation = null;
	
	ListingsByLocation(String loc){
		location = loc;
		ListingsByLocation = new JPanel(null);
		ListingsByLocation.setName("Listings");
		ListingsByLocation.setBackground(Color.decode("#0e214b"));
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
		Image vrboLogo = Helper.fetchImage("https://csvcus.homeaway.com/rsrcs-crs/cdn-logos/5.1.2/sitename/vrbo/web/image_src.png", 150, 80);
		logo.setIcon(new ImageIcon(vrboLogo));
		logo.setBounds(50, 15, 150, 80);
		
		ListingsByLocation.add(exitButton);
		ListingsByLocation.add(homeButton);
		ListingsByLocation.add(slogan);
		ListingsByLocation.add(logo);
	}
	
	void createBody(String loc){
			
		String listing1ImageURL = "https://media.vrbo.com/lodging/27000000/26940000/26935300/26935215/cf1683d9.f10.jpg";
		String listing2ImageURL = "https://media.vrbo.com/lodging/27000000/26940000/26935300/26935215/cf1683d9.f10.jpg";
		String listing3ImageURL = "https://media.vrbo.com/lodging/27000000/26940000/26935300/26935215/cf1683d9.f10.jpg";
		
		
		String bed1 = "1";
		String bath1 = "1";
		String price1 = "1"; 
		
		String bed2 = "1";
		String bath2 = "1";
		String price2 = "1";
		
		String bed3 = "1";
		String bath3 = "1";
		String price3 = "1";
		
		JButton rental1 = Helper.createButton("", 16, 90, 300, 300, 225);
		Image rental1image = Helper.fetchImage(listing1ImageURL, 300, 225);
		rental1.setIcon(new ImageIcon(rental1image));
		rental1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
					//bring you to the listing page of that property	
			}
		});
		
		JButton rental2 = Helper.createButton("", 16, 440, 300, 300, 225);
		Image rental2image = Helper.fetchImage(listing2ImageURL, 300, 225);
		rental2.setIcon(new ImageIcon(rental2image));
		rental2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
					
			}
		});
		
		
		JButton rental3 = Helper.createButton("", 16, 790, 300, 300, 225);
		Image rental3image = Helper.fetchImage(listing3ImageURL, 300, 225);
		rental3.setIcon(new ImageIcon(rental2image));
		rental3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				
			}
		});
		
		
		
		JLabel rental1location = Helper.createLabel(loc, 16, 90, 640, 300, 100);
		JLabel rental1bedbath = Helper.createLabel(bed1+ " " + bath1, 14, 90, 660, 300, 100);
		JLabel rental1price = Helper.createLabel(price1, 14, 90, 680, 300, 100);
		JLabel rental2location = Helper.createLabel(loc, 16, 440, 640, 300, 100);
		JLabel rental2bedbath = Helper.createLabel(bed2+ " " + bath2, 14, 440, 660, 300, 100);
		JLabel rental2price = Helper.createLabel(price2, 14, 440, 680, 300, 100);
		JLabel rental3location = Helper.createLabel(loc, 16, 790, 640, 300, 100);
		JLabel rental3bedbath = Helper.createLabel(bed3+ " " + bath3, 14, 790, 660, 300, 100);
		JLabel rental3price = Helper.createLabel(price3, 14, 790, 680, 300, 100);
		
		ListingsByLocation.add(rental1);
		ListingsByLocation.add(rental2);
		ListingsByLocation.add(rental3);

		ListingsByLocation.add(rental1location);
		ListingsByLocation.add(rental1bedbath);
		ListingsByLocation.add(rental1price);
		ListingsByLocation.add(rental2location);
		ListingsByLocation.add(rental2bedbath);
		ListingsByLocation.add(rental2price);
		ListingsByLocation.add(rental3location);
		ListingsByLocation.add(rental3bedbath);
		ListingsByLocation.add(rental3price);
				
	}
}









