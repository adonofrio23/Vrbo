package server;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import app.Helper;
import app.ListPropertyPage;
import app.ViewManager;

public class ServerWindow {
	public static JPanel ServerWindow = null;
	
	
	// global variables
	public static JTextArea top;
	public static JTextArea topQuadL;
	public static JTextArea topQuadR;
	public static JTextArea bottomQuadL;
	public static JTextArea bottomQuadR;
	public static JTextArea bottom;
	
	public ServerWindow() {
		ServerWindow = new JPanel(null);
		ServerWindow.setBorder(new TitledBorder(new EtchedBorder(), "Vrbo Socket Server"));
		//
		// TOP - available text area - has the real-time clock display for now
		//
		top = new JTextArea();
		top.setEditable(false);
		top.setBounds(15, 15, 1015, 90);
		top.setBorder(new EtchedBorder(EtchedBorder.RAISED));
		top.setBackground(Color.WHITE);
		ServerWindow.add(top);
		
		// Instantiating Total Listings Bar Chart
		
		JButton listings = new JButton("Total Listings Per Month");
		listings.setFont(new Font("Tahoma", Font.PLAIN, 20));
		listings.setForeground(Color.decode("#0e214b"));
		listings.setBounds(115, 150, 300, 50);
		Border emptyBorder = BorderFactory.createEmptyBorder();
		listings.setBorder(emptyBorder);
		listings.setBorder(BorderFactory.createLineBorder(Color.decode("#0e214b")));
		listings.setForeground(Color.decode("#0e214b"));
		
		listings.addMouseListener(new MouseAdapter() {
	         Color color = listings.getForeground();
	         public void mouseEntered(MouseEvent me) {
	            color = listings.getForeground();
	            listings.setForeground(Color.GRAY); // change the color to green when mouse over a button
	            listings.setBorder(BorderFactory.createLineBorder(Color.GRAY));
	         }
	         public void mouseExited(MouseEvent me) {
	            listings.setForeground(color);
	            listings.setBorder(BorderFactory.createLineBorder(Color.WHITE));
	         }
	      });
		
		listings.addActionListener(new ActionListener() {
			@Override public void actionPerformed(ActionEvent e) {
				JFrame frame = new JFrame();
				frame.setSize(1050, 1000);
				int[] value = new int[5];
				String[] months = new String[5];
				
				value[0] = 7;
				months[0] = ("January: " + value[0]*100);
				
				value[1] = 5;
				months[1] = ("February: " + value[1]*100);
				
				value[2] = 6;
				months[2] = ("March: " + value[2]*100);
				
				value[3] = 9;
				months[3] = ("April: " + value[3]*100);
				
				// Add number of listings dynamically to May
				value[4] = 0;
				months[4] = ("May: " + value[4]*100);
				  
				frame.getContentPane().add(new BarChart(value, months, "Total Listings Per Month for 2022"));
				
				
				WindowListener winListener = new WindowAdapter() 
				{
				
					public void windowClosing(WindowEvent event) 
					{

					}}; 
					frame.addWindowListener(winListener);
					frame.setVisible(true);
				} 
		});  
		ServerWindow.add(listings); 
		
		// Instantiating Total Listings Bar Chart
		JButton profit = new JButton("Total Profit Per Month");
		profit.setFont(new Font("Tahoma", Font.PLAIN, 20));
		profit.setForeground(Color.decode("#0e214b"));
		profit.setBounds(115, 250, 300, 50);
		profit.setBorder(emptyBorder);
		profit.setBorder(BorderFactory.createLineBorder(Color.decode("#0e214b")));
		profit.setForeground(Color.decode("#0e214b"));
		
		profit.addMouseListener(new MouseAdapter() {
			Color color = profit.getForeground();
			public void mouseEntered(MouseEvent me) {
				color = profit.getForeground();
				profit.setForeground(Color.GRAY); // change the color to green when mouse over a button
				profit.setBorder(BorderFactory.createLineBorder(Color.GRAY));
				}
			public void mouseExited(MouseEvent me) {
				profit.setForeground(color);
				profit.setBorder(BorderFactory.createLineBorder(Color.WHITE));
				}
			});
		
		profit.addActionListener(new ActionListener() {
			@Override public void actionPerformed(ActionEvent e) {
				JFrame frame = new JFrame();
				frame.setSize(1050, 1000);
				int[] value = new int[5];
				String[] months = new String[5];
				
				value[0] = 9;
				months[0] = ("January: $" + value[0]*1000);
						
				value[1] = 4;
				months[1] = ("February: $" + value[1]*1000);
						
				value[2] = 5;
				months[2] = ("March: $" + value[2]*1000);
						
				value[3] = 8;
				months[3] = ("April: $" + value[3]*1000);
						
				// Add profit dynamically to May?
				value[4] = 7;
				months[4] = ("May: $" + value[4]*1000);
						  
				frame.getContentPane().add(new BarChart(value, months, "Total Profit Per Month for 2022"));
						
						
				WindowListener winListener = new WindowAdapter() {
					public void windowClosing(WindowEvent event) {

					}}; 
					frame.addWindowListener(winListener);
					frame.setVisible(true);
					} 
			});  
		ServerWindow.add(profit);
		
		
		// Label for Total Users
		int users = 0;
		JLabel totalUsers = new JLabel("Total Users: " + users);
		totalUsers.setFont(new Font("Tahoma", Font.PLAIN, 40));
 		totalUsers.setForeground(Color.decode("#0e214b"));
 		totalUsers.setBounds(660, 200, 300, 50);
 		ServerWindow.add(totalUsers);
		
		
		//
		// Top Quadrant Left - Total Listings and Total Profit Bar Graphs
		//
		topQuadL = new JTextArea();
		topQuadL.setEditable(false);
		topQuadL.setBounds(15, 120, 500, 200);
		topQuadL.setBorder(new EtchedBorder(EtchedBorder.RAISED));
		topQuadL.setBackground(Color.WHITE);
		ServerWindow.add(topQuadL);
		
	
		//
		// Bottom Quadrant Left - Messages
		//
		bottomQuadL = new JTextArea();
		bottomQuadL.setEditable(false);
		bottomQuadL.setBounds(15, 335, 500, 200);
		bottomQuadL.setBorder(new EtchedBorder(EtchedBorder.RAISED));
		bottomQuadL.setBackground(Color.WHITE);
		ServerWindow.add(bottomQuadL);
		
		
		//
		//Bottom Quadrant Right - Real Time Bookings
		//
		bottomQuadR = new JTextArea();
		bottomQuadR.setEditable(false);
		bottomQuadR.setBounds(530, 335, 500, 200);
		bottomQuadR.setBorder(new EtchedBorder(EtchedBorder.RAISED));
		bottomQuadR.setBackground(Color.WHITE);
		ServerWindow.add(bottomQuadR);

		//
		// BOTTOM - available text area
		//
		bottom = new JTextArea();
		bottom.setEditable(false);
		bottom.setBounds(15, 550, 1015, 35);
		bottom.setBorder(new EtchedBorder(EtchedBorder.RAISED));
		bottom.setBackground(Color.WHITE);
		ServerWindow.add(bottom);
		
		//
		// define all BUTTONS
		//
		JButton exitButton = new JButton("EXIT");
		exitButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		exitButton.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				int result = JOptionPane.showConfirmDialog(null,
						     "Do you really want to exit the Socket Server?",
						     "Socket Server Backend",
						     JOptionPane.INFORMATION_MESSAGE);
				
				if (result == JOptionPane.OK_OPTION)
				{
					Server.closeServer();
				}		
			}
		});
		exitButton.setBounds(4, 620, 133, 30);;
		ServerWindow.add(exitButton);
		bottomQuadL.append("Server started on: " + Server.ip + ":" + Server.port + "\n");
		bottomQuadR.append("Real Time Bookings\n");
		top.setBackground(Color.decode("#0e214b"));
		JLabel logo = new JLabel();
	    Image vrboLogo = Helper.fetchImage("https://images.ctfassets.net/uylld2rxwr0n/7H3jIk9OvCrmuFpSyUrkqq/b07aa3c9796d08f2773bc54c69c666d9/Vrbo_logo_dark.png?w=960&q=50", 150, 80);
	    logo.setIcon(new ImageIcon(vrboLogo));
	    logo.setBounds(421, 18, 200, 50);
	    top.add(logo);
	}
}