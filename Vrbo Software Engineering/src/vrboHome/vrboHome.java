package vrboHome;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Date;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import java.awt.Color;
import java.awt.Font;

import java.awt.BorderLayout;
import java.awt.Image;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

public class vrboHome extends JFrame{
	
	 private static final long serialVersionUID = 1L;
	 
	 public static void main(String[] args){
		 vrboHome frame = new vrboHome();
		 frame.setVisible(true);
	 }
	 
	 public vrboHome()
	 {
	 
			// Frame title display current time
			
			Date  date = new Date();
			String str = String.format("%tc", date);
			
			//title 
			
			String titleString = "VRBO Home Page " + str; 				    
			setTitle(titleString);
			
			
			NumberFormat formatter = new DecimalFormat("#0.00");
			
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			
			
			// size of the frame
			//<<<<<<< HEAD
			setSize(2000,2000);
			setSize(1170,970);

			//branch 'main' of https://github.com/adonofrio23/Vrbo.git
			
			//
			// panel title
			
			//Travis - Header with Login, Sign up, and List your Property Buttons
			
			JPanel contentPane = new JPanel();
			contentPane.setBorder(new TitledBorder(new EtchedBorder(), 
					              "Insert VRBO logo here"));;
			setContentPane(contentPane);
				
			
			//Juliette - Search bar
			
		
			
			
			
			
			
			
			//Anthony - Popular Listings
			//
			// label for popular listings
			//
			JLabel popListingLabel = new JLabel("Popular Listings");
			popListingLabel.setFont(new Font("Tahoma", Font.PLAIN, 24));
			popListingLabel.setBounds(502, 450, 165, 30);
			contentPane.add(popListingLabel);
			
			//Image of Tampa house
			Image tampa = null;
	        try {
	            URL url = new URL("https://images.unsplash.com/photo-1506126279646-a697353d3166?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=2070&q=80");
	            BufferedImage bufferedImage = ImageIO.read(url);
	            tampa = bufferedImage.getScaledInstance(300, 225, Image.SCALE_DEFAULT);
	        } 
	        catch (IOException e) {
	        }

	// Use a label to display the image
	        JLabel tampaImage = new JLabel("Tampa");
	        tampaImage.setIcon(new ImageIcon(tampa));
	        tampaImage.setBounds(50, 500, 300, 225);
			contentPane.add(tampaImage);
			
			//
			// label for Tampa house
			//
			JLabel tampaHouseLabel = new JLabel("Tampa Bay, FL 4 Bed, 3.5 Bath $499/night");
			tampaHouseLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
			tampaHouseLabel.setBounds(50, 700, 300, 100);
			contentPane.add(tampaHouseLabel);
			
			//Image of Denver house
			Image denver = null;
	        try {
	            URL url = new URL("https://images.unsplash.com/photo-1558036117-15d82a90b9b1?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1470&q=80");
	            BufferedImage bufferedImage = ImageIO.read(url);
	            denver = bufferedImage.getScaledInstance(300, 225, Image.SCALE_DEFAULT);
	        } 
	        catch (IOException e) {
	        }

	// Use a label to display the image
	        JLabel denverImage = new JLabel("Denver");
	        denverImage.setIcon(new ImageIcon(denver));
	        denverImage.setBounds(425, 500, 300, 225);
			contentPane.add(denverImage);
			
			//
			// label for Denver house
			//
			JLabel denverHouseLabel = new JLabel("Denver, Colorado 7 Bed, 8.5 Bath $999/night");
			denverHouseLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
			denverHouseLabel.setBounds(425, 700, 300, 100);
			contentPane.add(denverHouseLabel);
			
			//Image of San Diego house
			Image sanD = null;
	        try {
	            URL url = new URL("https://images.unsplash.com/photo-1512915922686-57c11dde9b6b?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxzZWFyY2h8MjN8fGx1eHVyeSUyMGFwYXJ0bWVudHxlbnwwfHwwfHw%3D&auto=format&fit=crop&w=500&q=60");
	            BufferedImage bufferedImage = ImageIO.read(url);
	            sanD = bufferedImage.getScaledInstance(300, 225, Image.SCALE_DEFAULT);
	        } 
	        catch (IOException e) {
	        }

	// Use a label to display the image
	        JLabel sanDImage = new JLabel("San Diego");
	        sanDImage.setIcon(new ImageIcon(sanD));
	        sanDImage.setBounds(800, 500, 300, 225);
			contentPane.add(sanDImage);
			
			//
			// label for San Diego house
			//
			JLabel sanDHouseLabel = new JLabel("San Diego, CA 3 Bed, 2.5 Bath $350/night");
			sanDHouseLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
			sanDHouseLabel.setBounds(800, 700, 300, 100);
			contentPane.add(sanDHouseLabel);
			
			
			// call thread to update date and time
			//
			refreshTitleBar();
			
			
			// user will do the layout
			contentPane.setLayout(null);
			
			// position frame in the middle of the screen
			this.setLocationRelativeTo(null);
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
