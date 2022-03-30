package app;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
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
		createHeaderBar();
		createBody();
	}
	
	private void createHeaderBar() {
		JButton exitButton = Helper.createButton("Exit", 16, 1070, 15, 80, 80);
		exitButton.addActionListener(new Helper.ExitPrompt()); // Pop-Up Window Functionality
		JButton listingButton = Helper.createButton("List Your Property", 16, 870, 15, 150, 80);
		JButton signUpButton = Helper.createButton("Sign Up", 16, 700, 15, 120, 80);
		JButton logInButton = Helper.createButton("Log In", 16, 530, 15, 120, 80);
		
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
		
		JLabel slogan = Helper.createLabel("  Travel Better Together", 20, 250, 15, 230, 80);
		slogan.setBorder(BorderFactory.createLineBorder(Color.black));
		
		JLabel logo = new JLabel();
		Image vrboLogo = Helper.fetchImage("https://images.ctfassets.net/uylld2rxwr0n/7H3jIk9OvCrmuFpSyUrkqq/b07aa3c9796d08f2773bc54c69c666d9/Vrbo_logo_dark.png?w=960&q=50", 150, 80);
		logo.setIcon(new ImageIcon(vrboLogo));
		logo.setBounds(50, 15, 150, 80);
		
		HomePage.add(exitButton);
		HomePage.add(listingButton);
		HomePage.add(signUpButton);
		HomePage.add(logInButton);
		HomePage.add(slogan);
		HomePage.add(logo);
	}
	
	private void createBody() {
		JLabel optionMenu = Helper.createLabel("Where", 20, 150, 120, 250, 200);
		optionMenu.setForeground(Color.WHITE);
		
		String[] options = {"New York City, NY", "Tampa Bay, FL", "Denver, CO", "Los Angeles, CA"};
		JComboBox<String> search = new JComboBox<>(options);
		search.setFont(new Font("Tahoma", Font.PLAIN, 15));
		search.setBounds(150, 150, 250, 200);
		
		JLabel checkInLabel = Helper.createLabel("Check In", 20, 400, 120, 250, 200);
		checkInLabel.setForeground(Color.WHITE);
		JTextField checkInDate = Helper.createTextField(20, 400, 230, 100, 30);
		
		JLabel chooseCheckOut = Helper.createLabel("Check Out", 20, 550, 120, 250, 200);
		chooseCheckOut.setForeground(Color.WHITE);
		JTextField checkOutDate = Helper.createTextField(20, 550, 230, 100, 30);
		
		JLabel numberOfGuests = Helper.createLabel("Number of Guests", 20, 700, 120, 250, 200);
		numberOfGuests.setForeground(Color.WHITE);
		JTextField guestNumber = Helper.createTextField(20, 700, 230, 100, 30);
		
		JButton pressSearch = Helper.createButton("Search", 20, 950, 200, 80, 50);
				
		JLabel searchBG = new JLabel();
		Image searchBGImage = Helper.fetchImage("https://images.unsplash.com/photo-1570304816841-906a17d7b067?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=2064&q=80", 1000, 300);
		searchBG.setIcon(new ImageIcon(searchBGImage));
		searchBG.setBounds(100, 100, 1000, 400);
		
		HomePage.add(optionMenu);
		HomePage.add(search);
		HomePage.add(checkInLabel);
		HomePage.add(checkInDate);
		HomePage.add(chooseCheckOut);
		HomePage.add(checkOutDate);
		HomePage.add(numberOfGuests);
		HomePage.add(guestNumber);
		HomePage.add(pressSearch);
		HomePage.add(searchBG);
		
JLabel popListingLabel = Helper.createLabel("Popular Listings", 24, 502, 450, 165, 30);
		
		JButton tampa = Helper.createButton("", 16, 50, 485, 300, 225);
		Image tampaImage = Helper.fetchImage("https://images.unsplash.com/photo-1506126279646-a697353d3166?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=2070&q=80", 300, 225);
		tampa.setIcon(new ImageIcon(tampaImage));
		tampa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
						
			}
		});
		
		JButton denver = Helper.createButton("", 16, 425, 485, 300, 225);
		Image denverImage = Helper.fetchImage("https://images.unsplash.com/photo-1558036117-15d82a90b9b1?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1470&q=80", 300, 225);
		denver.setIcon(new ImageIcon(denverImage));
		denver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
					
			}
		});
		
		JButton sanDiego = Helper.createButton("", 16, 800, 485, 300, 225);
		Image sanDiegoImage = Helper.fetchImage("https://images.unsplash.com/photo-1512915922686-57c11dde9b6b?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxzZWFyY2h8MjN8fGx1eHVyeSUyMGFwYXJ0bWVudHxlbnwwfHwwfHw%3D&auto=format&fit=crop&w=500&q=60", 300, 225);
		sanDiego.setIcon(new ImageIcon(sanDiegoImage));
		sanDiego.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				
			}
		});
		
		JLabel tampaLocationLabel = Helper.createLabel("Tampa Bay, FL", 16, 150, 680, 300, 100);
		JLabel tampaBedBathLabel = Helper.createLabel("4 Bed, 3.5 Bath", 14, 155, 700, 300, 100);
		JLabel tampaPriceLabel = Helper.createLabel("$499/night", 14, 165, 720, 300, 100);
		JLabel denverLocationLabel = Helper.createLabel("Denver, Colorado", 16, 515, 680, 300, 100);
		JLabel denverBedBathLabel = Helper.createLabel("7 Bed, 8.5 Bath", 14, 530, 700, 300, 100);
		JLabel denverPriceLabel = Helper.createLabel("$999/night", 14, 540, 720, 300, 100);
		JLabel sanDiegoLocationLabel = Helper.createLabel("San Diego, CA", 16, 900, 680, 300, 100);
		JLabel sanDiegoBedBathLabel = Helper.createLabel("3 Bed, 2.5 Bath", 14, 904, 700, 300, 100);
		JLabel sanDiegoPriceLabel = Helper.createLabel("$350/night", 14, 917, 720, 300, 100);
		
		HomePage.add(popListingLabel);
		HomePage.add(tampa);
		HomePage.add(denver);
		HomePage.add(sanDiego);
		HomePage.add(tampaLocationLabel);
		HomePage.add(tampaBedBathLabel);
		HomePage.add(tampaPriceLabel);
		HomePage.add(denverLocationLabel);
		HomePage.add(denverBedBathLabel);
		HomePage.add(denverPriceLabel);
		HomePage.add(sanDiegoLocationLabel);
		HomePage.add(sanDiegoBedBathLabel);
		HomePage.add(sanDiegoPriceLabel);
	}
}
