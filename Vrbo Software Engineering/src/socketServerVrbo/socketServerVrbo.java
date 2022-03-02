package socketServerVrbo;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Date;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

public class socketServerVrbo extends JFrame{
	
	private static final long serialVersionUID = 1L;
	
	
	// global variables
	public static JTextArea top;
	public static JTextArea topQuadL;
	public static JTextArea topQuadR;
	public static JTextArea bottomQuadL;
	public static JTextArea bottomQuadR;
	public static JTextArea bottom;
	
	//
	// main method starts here
	//
	public static void main(String[] args)
	{
		socketServerVrbo frame = new socketServerVrbo();
		frame.setVisible(true);
	}

	public socketServerVrbo() {
		
		InetAddress ipAddress = null;
		try
		{
			ipAddress = InetAddress.getLocalHost();
		}
		catch (UnknownHostException el)
		{
			el.printStackTrace();
		}
		
		String titleString = "Vrbo Socket Server" + "IP: " + ipAddress.getHostAddress() + "Port# : 3333";
		setTitle(titleString);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		setTitle(titleString);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		//
		// set the Frame size
		//
		
		setSize(1050, 700);
		
		// 
		// panel title
		//
		JPanel contentPane = new JPanel();
		contentPane.setBorder(new TitledBorder(new EtchedBorder(), "Vrbo Socket Server"));
		
		
		setContentPane(contentPane);
		
		//
		// TOP - available text area - has the real-time clock display for now
		//
		top = new JTextArea();
		top.setEditable(false);
		top.setBounds(15, 15, 1015, 90);
		top.setBorder(new EtchedBorder(EtchedBorder.RAISED));
		top.setBackground(Color.WHITE);
		contentPane.add(top);
		
		//
		// Top Quadrant Left - Listings
		//
		topQuadL = new JTextArea();
		topQuadL.setEditable(false);
		topQuadL.setBounds(15, 120, 500, 200);
		topQuadL.setBorder(new EtchedBorder(EtchedBorder.RAISED));
		topQuadL.setBackground(Color.WHITE);
		contentPane.add(topQuadL);
		
		
		//
		// Top Quadrant Right - Profit
		//
		topQuadR = new JTextArea();
		topQuadR.setEditable(false);
		topQuadR.setBounds(530, 120, 500, 200);
		topQuadR.setBorder(new EtchedBorder(EtchedBorder.RAISED));
		topQuadR.setBackground(Color.WHITE);
		contentPane.add(topQuadR);
	
		
		//
		// Bottom Quadrant Left - Messages
		//
		bottomQuadL = new JTextArea();
		bottomQuadL.setEditable(false);
		bottomQuadL.setBounds(15, 335, 500, 200);
		bottomQuadL.setBorder(new EtchedBorder(EtchedBorder.RAISED));
		bottomQuadL.setBackground(Color.WHITE);
		contentPane.add(bottomQuadL);
		
		//
		//Bottom Quadrant Right - Users
		//
		bottomQuadR = new JTextArea();
		bottomQuadR.setEditable(false);
		bottomQuadR.setBounds(530, 335, 500, 200);
		bottomQuadR.setBorder(new EtchedBorder(EtchedBorder.RAISED));
		bottomQuadR.setBackground(Color.WHITE);
		contentPane.add(bottomQuadR);
		
		
		//
		// BOTTOM - available text area
		//
		bottom = new JTextArea();
		bottom.setEditable(false);
		bottom.setBounds(15, 550, 1015, 35);
		bottom.setBorder(new EtchedBorder(EtchedBorder.RAISED));
		bottom.setBackground(Color.WHITE);
		contentPane.add(bottom);
		
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
					//socketServer.writeHashTableData();
					
					dispose();
					System.exit(0);
				}		
			}
		});
		exitButton.setBounds(4, 620, 133, 30);;
		contentPane.add(exitButton);
		
		// start all threads  for the GUI screen here
				startRealTimeClock();
						
				// start the socket server thread - will start to accept client connections
				startSockServer();
				
				//
				// lights, camera, action
				//
				contentPane.setLayout(null);
				
				this.setLocationRelativeTo(null);
			}
		
/*
 * Thread to update weather info for NYC and Boston    
 */     
private void startSockServer()
{	
	 Thread refreshWeatherThread = new Thread()
	 {
	    public void run()
		  { 	
			  socketServer.runSockServer();
	      }
	 };

  refreshWeatherThread.start();
}
	

/*
 * Thread to update real-time clock
 */     
private void startRealTimeClock()
{	
	   Thread refreshClock = new Thread()
	   {
		  public void run()
		  {  
			 while (true)
			 {	 			      
				   Date   date = new Date();
				   String str = String.format("\n    %tc", date);
					 
				   top.setText("");
				   top.setText(str);
				   
			    	try
				    {
					   sleep(5000L);
				    }
				    catch (InterruptedException e)
				   {
					   // TODO Auto-generated catch block
					  e.printStackTrace();
				   }
           } // end while true 
	     }
	  };

  refreshClock.start();
}
}
