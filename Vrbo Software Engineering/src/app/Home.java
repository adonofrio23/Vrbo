package app;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Home {
	public static JPanel HomePage = null;
	
	Home() {
		HomePage = new JPanel(null);
		HomePage.setName("Home Page");
		HomePage.setBackground(Color.decode("#0e214b"));
		createHeaderBar();
		createBody();
	}
	
	private void createHeaderBar() {
		JButton exitButton = Helper.createButton("Exit", 16, 1010, 90, 100, 30);
		exitButton.addActionListener(new Helper.ExitPrompt()); // Pop-Up Window Functionality
		JButton listingButton = Helper.createButton("List Your Property", 16, 750, 90, 200, 30);
		JButton signUpButton = Helper.createButton("Sign Up", 16, 640, 90, 100, 30);
		JButton logInButton = Helper.createButton("Log In", 16, 530, 90, 100, 30);
		
		listingButton.addActionListener(new ActionListener() {
			@Override public void actionPerformed(ActionEvent e) {
				if (ViewManager.Pages.containsKey("List Property")) {
					ViewManager.switchPage("List Property", ViewManager.Pages.get("List Property"));
				} else {
					new ListPropertyPage();
					ViewManager.switchPage("List Property", ListPropertyPage.ListPropertyPage);
				}
			}
		});
		
		
		logInButton.addActionListener(new ActionListener() {
			@Override public void actionPerformed(ActionEvent e) {
				if (ViewManager.Pages.containsKey("Log In")) {
					ViewManager.switchPage("Log In", ViewManager.Pages.get("Log In"));
				} else {
					new LogInPage();
					ViewManager.switchPage("Log In", LogInPage.LogInPage);
				}
			}
		});
		
		signUpButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (ViewManager.Pages.containsKey("Sign Up")) {
					ViewManager.switchPage("Sign Up", ViewManager.Pages.get("Sign Up"));
				} else {
					new SignUpPage();
					ViewManager.switchPage("Sign Up", SignUpPage.SignUpPage);
				}
			}
		});
		
		JLabel slogan = Helper.createLabel("Travel Better Together", 20, 130, 90, 300, 30);
		slogan.setForeground(Color.WHITE);
		JLabel logo = new JLabel();
		//Image vrboLogo = Helper.fetchImage("https://images.ctfassets.net/uylld2rxwr0n/7H3jIk9OvCrmuFpSyUrkqq/b07aa3c9796d08f2773bc54c69c666d9/Vrbo_logo_dark.png?w=960&q=50", 150, 80);
		Image vrboLogo = Helper.fetchImage("https://csvcus.homeaway.com/rsrcs-crs/cdn-logos/5.1.2/sitename/vrbo/web/image_src.png", 150, 80);
		logo.setIcon(new ImageIcon(vrboLogo));
		logo.setBounds(5, 0, 150, 200);
		
		HomePage.add(exitButton);
		HomePage.add(listingButton);
		HomePage.add(signUpButton);
		HomePage.add(logInButton);
		HomePage.add(slogan);
		HomePage.add(logo);
	}
	//x y w h
	
	private void createBody() {
		JLabel optionMenu = Helper.createLabel("Where", 20, 160, 240, 100, 30);
		optionMenu.setForeground(Color.WHITE);
		
		String[] options = {"New York City, NY", "Tampa Bay, FL", "Denver, CO", "Los Angeles, CA"};
		JComboBox<String> search = new JComboBox<>(options);
		search.setFont(new Font("Tahoma", Font.PLAIN, 15));
		search.setBounds(150, 185, 250, 220);
		
		JLabel bedsLabel = Helper.createLabel("Beds", 20, 420, 240, 100, 30);
		JTextField beds = Helper.createTextField(15, 420, 280, 100, 30);
		
		JLabel bathsLabel = Helper.createLabel("Baths", 20, 570, 240, 100, 30);
		JTextField baths = Helper.createTextField(15, 570, 280, 100, 30);
		
		JLabel priceLabel = Helper.createLabel("Price", 20, 720, 240, 200, 30);
		JTextField price = Helper.createTextField(15, 720, 280, 100, 30);
		
		JButton pressSearch = Helper.createButton("Search", 20, 950, 280, 100, 30);
		JLabel searchBG = new JLabel();
		
//		Image searchBGImage = Helper.fetchImage("https://images.unsplash.com/photo-1570304816841-906a17d7b067?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=2064&q=80", 1000, 300);
//		searchBG.setIcon(new ImageIcon(searchBGImage));
//		searchBG.setBounds(90, 90, 1000, 300);
		
		pressSearch.addActionListener(new ActionListener() {
			@Override public void actionPerformed(ActionEvent e) {
				String location = search.getSelectedItem().toString();
				if (ViewManager.Pages.containsKey("Listings")) {
					ViewManager.switchPage("Listings", ViewManager.Pages.get("Listings"));
				} else {
					SocketUtils.sendMessage("FETCHALL=\"city\": \"" + search.getSelectedItem().toString()
											+ "\", \"beds\": \"" + beds.getText() + "\"");
					String data = SocketUtils.receiveMessage();
					new ListingsByLocation(location, data);
					ViewManager.switchPage("Listings", ListingsByLocation.ListingsByLocation);
				}
			}
		});
		
		HomePage.add(optionMenu);
		HomePage.add(search);
		HomePage.add(bedsLabel);
		HomePage.add(beds);
		HomePage.add(bathsLabel);
		HomePage.add(baths);
		HomePage.add(priceLabel);
		HomePage.add(price);
		HomePage.add(pressSearch);
		HomePage.add(searchBG);
		
		// To do - Once at least 3 listings per location are uploaded, add randLoc back to the query
		String randLoc = options[(int)(System.currentTimeMillis() % options.length)];
		SocketUtils.sendMessage("FETCHALL=\"city\": \"" + "Tampa Bay, FL" + "\"");
		String data = SocketUtils.receiveMessage();
		
		String[] listData = ListingsByLocation.splitData(data);
		String list1Data = listData[0];
		String list2Data = listData[1];
		String list3Data = listData[2];
		
		JLabel popListingLabel = Helper.createLabel("Popular Listings In " + "Tampa Bay, FL", 20, 450, 390, 500, 35);
		
		JButton list1 = Helper.createButton("", 16, 90, 440, 300, 225);
		Image list1pic = Helper.fetchImage(ListingsByLocation.parse(list1Data, "link"), 300, 225);
		list1.setIcon(new ImageIcon(list1pic));
		list1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				new ListingPageTemplate(list1Data, "Tampa Bay, FL");
				ViewManager.switchPage("Listing", ListingPageTemplate.ListingPage);
			}
		});
		
		JButton list2 = Helper.createButton("", 16, 440, 440, 300, 225);
		Image list2pic = Helper.fetchImage(ListingsByLocation.parse(list2Data, "link"), 300, 225);
		list2.setIcon(new ImageIcon(list2pic));
		list2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				new ListingPageTemplate(list2Data, "Tampa Bay, FL");
				ViewManager.switchPage("Listing", ListingPageTemplate.ListingPage);
			}
		});
		
		JButton list3 = Helper.createButton("", 16, 790, 440, 300, 225);
		Image list3pic = Helper.fetchImage(ListingsByLocation.parse(list3Data, "link"), 300, 225);
		list3.setIcon(new ImageIcon(list3pic));
		list3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				new ListingPageTemplate(list3Data, "Tampa Bay, FL");
				ViewManager.switchPage("Listing", ListingPageTemplate.ListingPage);
			}
		});
		
		JLabel list1loc = Helper.createLabel(ListingsByLocation.parse(list1Data, "address"), 16, 90, 640, 300, 100);
		JLabel list1bedbath = Helper.createLabel(ListingsByLocation.parse(list1Data, "beds") + ", " + ListingsByLocation.parse(list1Data, "baths"), 14, 90, 660, 300, 100);
		JLabel list1price = Helper.createLabel("$" + ListingsByLocation.parse(list1Data, "price"), 14, 90, 680, 300, 100);
		JLabel list2loc = Helper.createLabel(ListingsByLocation.parse(list2Data, "address"), 16, 440, 640, 300, 100);
		JLabel list2bedbath = Helper.createLabel(ListingsByLocation.parse(list2Data, "beds") + " beds, " + ListingsByLocation.parse(list2Data, "baths") + " baths", 14, 440, 660, 300, 100);
		JLabel list2price = Helper.createLabel("$" + ListingsByLocation.parse(list2Data, "price"), 14, 440, 680, 300, 100);
		JLabel list3loc = Helper.createLabel(ListingsByLocation.parse(list3Data, "address"), 16, 790, 640, 300, 100);
		JLabel list3bedbath = Helper.createLabel(ListingsByLocation.parse(list3Data, "beds") + " beds, " + ListingsByLocation.parse(list3Data, "baths") + " baths", 14, 790, 660, 300, 100);
		JLabel list3price = Helper.createLabel("$" + ListingsByLocation.parse(list3Data, "price"), 14, 790, 680, 300, 100);
		
		HomePage.add(popListingLabel);
		HomePage.add(list1);
		HomePage.add(list1loc);
		HomePage.add(list1bedbath);
		HomePage.add(list1price);
		HomePage.add(list2);
		HomePage.add(list2loc);
		HomePage.add(list2bedbath);
		HomePage.add(list2price);
		HomePage.add(list3);
		HomePage.add(list3loc);
		HomePage.add(list3bedbath);
		HomePage.add(list3price);
	}
}
