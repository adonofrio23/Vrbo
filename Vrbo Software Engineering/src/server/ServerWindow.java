package server;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

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
		
		//
		// Top Quadrant Left - Listings
		//
		topQuadL = new JTextArea();
		topQuadL.setEditable(false);
		topQuadL.setBounds(15, 120, 500, 200);
		topQuadL.setBorder(new EtchedBorder(EtchedBorder.RAISED));
		topQuadL.setBackground(Color.WHITE);
		ServerWindow.add(topQuadL);
		
		
		//
		// Top Quadrant Right - Profit
		//
		topQuadR = new JTextArea();
		topQuadR.setEditable(false);
		topQuadR.setBounds(530, 120, 500, 200);
		topQuadR.setBorder(new EtchedBorder(EtchedBorder.RAISED));
		topQuadR.setBackground(Color.WHITE);
		ServerWindow.add(topQuadR);
	
		
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
		//Bottom Quadrant Right - Users
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
	}
}