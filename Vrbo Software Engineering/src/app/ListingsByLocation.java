package app;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ListingsByLocation {
	String location;
	String data;
	
	static JPanel ListingsByLocation = null;
	
	ListingsByLocation(String loc, String data){
		this.data = data;
		location = loc;
		ListingsByLocation = new JPanel(null);
		ListingsByLocation.setName("Listings");
		ListingsByLocation.setBackground(Color.decode("#0e214b"));
		createHeaderBar(location);
		createBody(location);
	}
	
	void createHeaderBar(String loc){
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
		
		ListingsByLocation.add(exitButton);
		ListingsByLocation.add(homeButton);
		ListingsByLocation.add(slogan);
		ListingsByLocation.add(logo);
		
		
	}
	
	void createBody(String loc){
		String[] dataArr = splitData(data);
		String list1Data = dataArr[0];
		String list2Data = dataArr[1];
		String list3Data = dataArr[2];
		
	
		String listing1ImageURL = parse(list1Data, "link");
		String listing2ImageURL = parse(list2Data, "link");
		String listing3ImageURL = parse(list3Data, "link");
		
		String bed1 = parse(list1Data, "beds");
		String bath1 = parse(list1Data, "baths");
		String price1 = parse(list1Data, "price"); 
		
		String bed2 = parse(list2Data, "beds");
		String bath2 = parse(list2Data, "baths");
		String price2 = parse(list2Data, "price");
		
		String bed3 = parse(list3Data, "beds");
		String bath3 = parse(list3Data, "baths");
		String price3 = parse(list3Data, "price");
		
		JButton rental1 = Helper.createButton("", 16, 90, 300, 300, 225);
		Image rental1image = Helper.fetchImage(listing1ImageURL, 300, 225);
		rental1.setIcon(new ImageIcon(rental1image));
		rental1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				new ListingPageTemplate(list1Data, location);
				ViewManager.switchPage("Listing", ListingPageTemplate.ListingPage);	
			}
		});
		
		JButton rental2 = Helper.createButton("", 16, 440, 300, 300, 225);
		Image rental2image = Helper.fetchImage(listing2ImageURL, 300, 225);
		rental2.setIcon(new ImageIcon(rental2image));
		rental2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
					new ListingPageTemplate(list2Data, location);
					ViewManager.switchPage("Listing", ListingPageTemplate.ListingPage);
			}
		});
		
		
		JButton rental3 = Helper.createButton("", 16, 790, 300, 300, 225);
		Image rental3image = Helper.fetchImage(listing3ImageURL, 300, 225);
		rental3.setIcon(new ImageIcon(rental3image));
		rental3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				new ListingPageTemplate(list3Data, location);
				ViewManager.switchPage("Listing", ListingPageTemplate.ListingPage);
			}
		});
		
		
		
		JLabel rental1location = Helper.createLabel(loc, 16, 90, 540, 300, 100);
		JLabel rental1bedbath = Helper.createLabel(bed1+ " " + bath1, 14, 90, 560, 300, 100);
		JLabel rental1price = Helper.createLabel(price1, 14, 90, 580, 300, 100);
		JLabel rental2location = Helper.createLabel(loc, 16, 440, 540, 300, 100);
		JLabel rental2bedbath = Helper.createLabel(bed2+ " " + bath2, 14, 440, 560, 300, 100);
		JLabel rental2price = Helper.createLabel(price2, 14, 440, 580, 300, 100);
		JLabel rental3location = Helper.createLabel(loc, 16, 790, 540, 300, 100);
		JLabel rental3bedbath = Helper.createLabel(bed3+ " " + bath3, 14, 790, 560, 300, 100);
		JLabel rental3price = Helper.createLabel(price3, 14, 790, 580, 300, 100);
		
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
	
	private static String[] splitData(String input) {
		String[] data = new String[3];
		int cur = 0;
		for (int i = 0, j = 0; i < input.length(); i++) {
			if (input.charAt(i) == '+' && j < 3) {
				data[j] = input.substring(cur, i);
				cur = i + 1;
				j++;
			}
		}
		return data;
	}
	
	public static String parse(String input, String target) {
		int offset = target.length();
		int index = input.indexOf(target) + offset;
		String val = "";

		while (true) {
			if (input.charAt(index) == ',' || index == input.length() - 1)
				break;
			
			if (input.charAt(index) != '=')
				val += input.charAt(index);
			
			index++;
		}
		return val.strip();
	}
}









