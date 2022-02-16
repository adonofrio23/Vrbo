package vrbo;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class logIn extends Window {
	public static final long serialVersionUID = 10L;
	
	logIn(int width, int height, String name) {
		super.width = width;
		super.height = height;
		super.name = name;
		createHeaderBar();
		addComponents();
		createWindow();
	}
	
	protected void createHeaderBar() {
		/*
		 * Header Bar
		 * Travis Taylor
		 */
		JButton logInButton = createButton("Log In", 16, 530, 15, 120, 80);
		
		window.add(logInButton);
		
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
		/*
		 * Search Bar
		 * Juliette Garvey
		 */
		JLabel optionMenu = createLabel("Where", 20, 150, 120, 250, 200);
		optionMenu.setForeground(Color.WHITE);
		
		String[] options = {"New York City, NY", "Tampa Bay, FL", "Denver, CO", "Los Angeles, CA"};
		JComboBox<String> search = new JComboBox<>(options);
		search.setFont(new Font("Tahoma", Font.PLAIN, 15));
		search.setBounds(150, 150, 250, 200);
		
		JLabel checkInLabel = createLabel("Check In", 20, 400, 120, 250, 200);
		checkInLabel.setForeground(Color.WHITE);
		JTextField checkInDate = createTextField(20, 400, 230, 100, 30);
		
		JLabel chooseCheckOut = createLabel("Check Out", 20, 550, 120, 250, 200);
		chooseCheckOut.setForeground(Color.WHITE);
		JTextField checkOutDate = createTextField(20, 550, 230, 100, 30);
		
		JLabel numberOfGuests = createLabel("Number of Guests", 20, 700, 120, 250, 200);
		numberOfGuests.setForeground(Color.WHITE);
		JTextField guestNumber = createTextField(20, 700, 230, 100, 30);
		
		JButton pressSearch = createButton("Search", 20, 950, 200, 80, 50);
				
		JLabel searchBG = new JLabel();
		Image searchBGImage = fetchImage("https://images.unsplash.com/photo-1570304816841-906a17d7b067?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=2064&q=80", 1000, 300);
		searchBG.setIcon(new ImageIcon(searchBGImage));
		searchBG.setBounds(100, 100, 1000, 400);
		
		window.add(optionMenu);
		window.add(search);
		window.add(checkInLabel);
		window.add(checkInDate);
		window.add(chooseCheckOut);
		window.add(checkOutDate);
		window.add(numberOfGuests);
		window.add(guestNumber);
		window.add(pressSearch);
		window.add(searchBG);
		
		/*
		 * Popular Listings
		 * Anthony D'Onofrio
		 */
		JLabel popListingLabel = createLabel("Popular Listings", 24, 502, 450, 165, 30);
		
		JLabel tampa = new JLabel("Tampa");
		Image tampaImage = fetchImage("https://images.unsplash.com/photo-1506126279646-a697353d3166?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=2070&q=80", 300, 225);
		tampa.setIcon(new ImageIcon(tampaImage));
		tampa.setBounds(50, 485, 300, 225);
		
		JLabel denver = new JLabel("Denver");
		Image denverImage = fetchImage("https://images.unsplash.com/photo-1558036117-15d82a90b9b1?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1470&q=80", 300, 225);
		denver.setIcon(new ImageIcon(denverImage));
		denver.setBounds(425, 485, 300, 225);
		
		JLabel sanDiego = new JLabel("San Diego");
		Image sanDiegoImage = fetchImage("https://images.unsplash.com/photo-1512915922686-57c11dde9b6b?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxzZWFyY2h8MjN8fGx1eHVyeSUyMGFwYXJ0bWVudHxlbnwwfHwwfHw%3D&auto=format&fit=crop&w=500&q=60", 300, 225);
		sanDiego.setIcon(new ImageIcon(sanDiegoImage));
		sanDiego.setBounds(800, 485, 300, 225);
		
		JLabel tampaLocationLabel = createLabel("Tampa Bay, FL", 16, 150, 680, 300, 100);
		JLabel tampaBedBathLabel = createLabel("4 Bed, 3.5 Bath", 14, 155, 700, 300, 100);
		JLabel tampaPriceLabel = createLabel("$499/night", 14, 165, 720, 300, 100);
		JLabel denverLocationLabel = createLabel("Denver, Colorado", 16, 515, 680, 300, 100);
		JLabel denverBedBathLabel = createLabel("7 Bed, 8.5 Bath", 14, 530, 700, 300, 100);
		JLabel denverPriceLabel = createLabel("$999/night", 14, 540, 720, 300, 100);
		JLabel sanDiegoLocationLabel = createLabel("San Diego, CA", 16, 900, 680, 300, 100);
		JLabel sanDiegoBedBathLabel = createLabel("3 Bed, 2.5 Bath", 14, 904, 700, 300, 100);
		JLabel sanDiegoPriceLabel = createLabel("$350/night", 14, 917, 720, 300, 100);
		
		window.add(popListingLabel);
		window.add(tampa);
		window.add(denver);
		window.add(sanDiego);
		window.add(tampaLocationLabel);
		window.add(tampaBedBathLabel);
		window.add(tampaPriceLabel);
		window.add(denverLocationLabel);
		window.add(denverBedBathLabel);
		window.add(denverPriceLabel);
		window.add(sanDiegoLocationLabel);
		window.add(sanDiegoBedBathLabel);
		window.add(sanDiegoPriceLabel);
	}
}
