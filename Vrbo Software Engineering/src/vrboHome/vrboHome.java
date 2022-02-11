import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
@@ -67,17 +68,29 @@ public vrboHome()
			setContentPane(contentPane);

			//Travis - Header with Login, Sign up, List your Property, and Exit Buttons
			JButton exitButton = createButton("Exit", 1070, 15, 80, 80);
			JButton exitButton = createButton("Exit", 16, 1070, 15, 80, 80);
			exitButton.addActionListener(new ExitListener()); // Pop-Up Window Functionality
			JButton listingButton = createButton("List Your Property", 870, 15, 150, 80);
			JButton signUpButton = createButton("Sign Up", 700, 15, 120, 80);
			JButton logInButton = createButton("Log In", 530, 15, 120, 80);
			JButton listingButton = createButton("List Your Property", 16, 870, 15, 150, 80);
			JButton signUpButton = createButton("Sign Up", 16, 700, 15, 120, 80);
			JButton logInButton = createButton("Log In", 16, 530, 15, 120, 80);

			contentPane.add(exitButton);
			contentPane.add(listingButton);
			contentPane.add(signUpButton);
			contentPane.add(logInButton);

			JLabel slogan = createLabel("  Travel Better Together", 20, 250, 15, 230, 80);
			slogan.setBorder(BorderFactory.createLineBorder(Color.black));

			contentPane.add(slogan);

			JLabel logo = new JLabel();
			Image vrboLogo = fetchImage("https://images.ctfassets.net/uylld2rxwr0n/7H3jIk9OvCrmuFpSyUrkqq/b07aa3c9796d08f2773bc54c69c666d9/Vrbo_logo_dark.png?w=960&q=50", 150, 80);
			logo.setIcon(new ImageIcon(vrboLogo));
			logo.setBounds(50, 15, 150, 80);

			contentPane.add(logo);

			//Juliette - Search bar
			
			// TEST

			//Search Location in drop down 
			//Label for "Where"
			JLabel chooseOption = new JLabel("Where");
			chooseOption.setFont(new Font("Tahoma", Font.PLAIN, 14));
			chooseOption.setBounds(697, 424, 100, 23);
			contentPane.add(chooseOption); 
			
			
			//dropdown
			
			String[] options = {"New York City, NY", "Tampa Bay, FL", "Denver, CO", "Los Angeles, CA"};
			//JPanel dropdownSearch = new JPanel(); 
			JComboBox<String> search = new JComboBox<>(options);
			search.setFont(new Font("Tahoma", Font.PLAIN, 20));
			search.setBounds(150, 150, 250, 200);
			
			
			JButton pressSearch = new JButton("Search");
			search.setFont(new Font("Tahoma", Font.PLAIN, 20));
	        pressSearch.setBounds(150, 150, 80, 50);
			
			contentPane.add(pressSearch);
			contentPane.add(search);
			
			contentPane.setVisible(true);
			


@@ -197,13 +210,32 @@ public vrboHome()
	 		}
	 	}

	 	private JButton createButton(String tag, int x, int y, int width, int height) {
	 	private JButton createButton(String tag, int fontSize, int x, int y, int width, int height) {
	 		JButton btn = new JButton(tag);
			btn.setFont(new Font("Tahoma", Font.PLAIN, 16));
			btn.setFont(new Font("Tahoma", Font.PLAIN, fontSize));
			btn.setBounds(x, y, width, height);
			return btn;
	 	}

	 	private JLabel createLabel(String tag, int fontSize, int x, int y, int width, int height) {
	 		JLabel label = new JLabel(tag);
	 		label.setFont(new Font("Tahoma", Font.PLAIN, fontSize));
	 		label.setBounds(x, y, width, height);
	 		return label;
	 	}

	 	private Image fetchImage(String url, int width, int height) {
	 		Image image = null;
	 		try {
	 			URL u = new URL(url);
	 			BufferedImage img = ImageIO.read(u);
	 			image = img.getScaledInstance(width, height, Image.SCALE_DEFAULT);
	 		} catch (Exception e) {

	 		}
	 		return image;
	 	}

	    //
	    // Thread to update TITLE BAR, date, and time     
	    //     
	    private void refreshTitleBar()
	    {	
		   Thread refreshAllTitleBar = new Thread()
		   {
			  public void run()
			  { 
				 while (true)
				 {
					 try 
					 {
					   //
					   // display current time
					   //
					   Date  date = new Date();
					   String str = String.format("%tc", date);
	              	   
					   String titleString = "VRBO Home Page " + str; 				 
					   
					   setTitle(titleString);
						 
					   sleep(5000L);                   // sleep for 5 seconds or 5,000 milliseconds
					   
	                 } // end try block
				  
			         catch (InterruptedException e) 
			         {
			        	 JOptionPane.showMessageDialog(null, 
	                              "ERROR. Interrupt Exception! Check Internet Connection!",
	                              "Title Top Bar",
	                              JOptionPane.WARNING_MESSAGE);
			        	 
			        	 continue;
				     }
			         finally
			         {
				   
			         }
				 } // end while true
		     }
		  };
	      refreshAllTitleBar.start();
	 }
}








