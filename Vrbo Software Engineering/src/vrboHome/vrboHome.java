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
			//
			setSize(1170,970);
			
			//
			// panel title
			//
			JPanel contentPane = new JPanel();
			contentPane.setBorder(new TitledBorder(new EtchedBorder(), 
					              "Insert VRBO logo here"));;
			setContentPane(contentPane);
				
			//
			// label for popular listings
			//
			JLabel popListingLabel = new JLabel("Popular Listings");
			popListingLabel.setBounds(650, 20, 150, 23);
			contentPane.add(popListingLabel);	
	 }
}
