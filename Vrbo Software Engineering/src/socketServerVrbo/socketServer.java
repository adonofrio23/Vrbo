package socketServerVrbo;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.net.BindException;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.ServerSocket;
import java.net.SocketException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.List;
import java.util.Vector;


import app.fileIO;

import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.PrintWriter;

public class socketServer implements Runnable {	
	static int port = 4545;
	static final String newline = "\n";
	static int first_time = 1;
	private static ServerSocket ssock = null;
	static int numOfConnections = 0;
	static int numOfMessages = 0;
	static int max_connections = 5;
	static int numOfTransactions = 0;
	static String ipString;
	Socket csocket;
	static boolean found = false;
	static String ipAddrOfSocketServer = null;


	socketServer(Socket csocket, String ip)
	{
		this.csocket  = csocket;
		socketServer.ipString = ip;
	} 
	
	
	static void displayInterfaceInformation(NetworkInterface netint) throws SocketException 
    {	
    	String dname = netint.getName();
    	if (dname.startsWith("en") == true && found == false)
    	{
           Enumeration<InetAddress> inetAddresses = netint.getInetAddresses();
           
           int counter= 0;
           for (InetAddress inetAddress : Collections.list(inetAddresses))
           {   
        	   String myAddr = inetAddress.toString();
        	   myAddr = myAddr.replaceFirst("/", "");
        	   
        	   if (counter != 0)
        	   {
                    ipAddrOfSocketServer = myAddr;
                    found = true;
        	   }
        	   
        	   counter++;
           }
    	}
   }
   
	
	
	public static void runSockServer() {
		
		boolean sessionDone = false;	
		
		try {
			ssock = new ServerSocket(port);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		 Enumeration<NetworkInterface> nets = null;
		 try 
		 {
			nets = NetworkInterface.getNetworkInterfaces();
		 }
		 catch (SocketException e3)
		 {
			// TODO Auto-generated catch block
			e3.printStackTrace(); 
		 }
		 
	     for (NetworkInterface netint : Collections.list(nets))
			try {
				displayInterfaceInformation(netint);
			} catch (SocketException e3) {
				// TODO Auto-generated catch block
				e3.printStackTrace();
			}

	   
	     try
	     {
	       InetAddress addr = InetAddress.getByName(ipAddrOfSocketServer);
	       
		   ssock = new ServerSocket(port, 50, addr);
	     }
	     
	     catch (BindException e)
	     {
		    e.printStackTrace();
	     }
	     catch (IOException e)
	     {
		    e.printStackTrace();
	     }
	     
	     JLabel logo = new JLabel();
	     Image vrboLogo = Helper.fetchImage("https://images.ctfassets.net/uylld2rxwr0n/7H3jIk9OvCrmuFpSyUrkqq/b07aa3c9796d08f2773bc54c69c666d9/Vrbo_logo_dark.png?w=960&q=50", 150, 80);
	     logo.setIcon(new ImageIcon(vrboLogo));
	     logo.setBounds(421, 10, 200, 50);
	     socketServerVrbo.top.add(logo);
	     
	     // update the status text area to show progress of program
	     socketServerVrbo.bottomQuadL.append("IP Address : " + ipAddrOfSocketServer + newline);
	     socketServerVrbo.bottomQuadL.setText(ipAddrOfSocketServer + newline);
	     socketServerVrbo.bottomQuadL.append("Port: " + Integer.toString(port) + newline);
		   socketServerVrbo.bottomQuadL.append("Listening on port " + port + newline);
		 
		   socketServerVrbo.top.setBackground(Color.decode("#fbff91"));

	     
	     sessionDone = false;
	     while (sessionDone == false)
	     {
	        Socket sock = null;
		    try
		    {
		       //	
  	     	   // blocking system call
		       //	
			   sock = ssock.accept();
		    }
		    catch (IOException e)
		    {
			   e.printStackTrace();
		    }
		 
	        socketServerVrbo.bottomQuadL.append("Client Connected : " + sock.getInetAddress().toString().replace("/", "") + newline);
	        new Thread(new socketServer(sock, sock.getInetAddress().toString())).start();
	        
	     }
	     
	     endSocketServer();
	}
	     
	 public static void endSocketServer(){
	     try 
	     {
		    ssock.close();
	     }
	     catch (IOException e) 
	     {
		    e.printStackTrace();
	     }
	}
	 
	   

	public void run()
	{
	   long threadId = 0;
	   
	   try
	   {
		  int watchDog = 0;
		  boolean session_done = false;
	      String clientString;
	    
	      threadId = Thread.currentThread().getId();
	      
	      numOfConnections++;
	      
	      socketServerVrbo.bottomQuadL.append("Num of Connections = " + numOfConnections + newline);
	      
	   
	      //
	      // write IP address of the client who just connected to the main screen
	      //
	 
	       
	      PrintStream pstream = new PrintStream (csocket.getOutputStream());
	      BufferedReader rstream = new BufferedReader(new InputStreamReader(csocket.getInputStream()));
	     
	      while (session_done == false)
	      {
	          if (rstream.ready())   // check for any data messages
	          {
	              clientString = rstream.readLine();

	              watchDog = 0;             // reset the watch counter back to zero
	              
	              //
	              // write to transaction log
	              //
	            
	              	              
	              // update the status text area to show progress of program
	   	           socketServerVrbo.bottomQuadL.append("RECV : " + clientString + newline);
	     	       
	     	       // update the status text area to show progress of program
	     	       socketServerVrbo.bottomQuadL.append("RLEN : " + clientString.length() + newline);
	              
	              if (clientString.length() > 128)
	              {
	           	   session_done = true;
	           	   continue;
	              }

	              if (clientString.contains("quit"))
	              {
	                 session_done = true;
	              }
	              else if (clientString.contains("QUIT"))
	              {
	                 session_done = true;
	              }
	              else if (clientString.contains("Quit"))
	              {
	                 session_done = true;
	              }
	             
	              else
	              {
	            	  pstream.println("NACK : ERROR : No such command!");
	              }
	       	   }
	         			    		        	
	           Thread.sleep(500);

	           if (++watchDog >= 40)     // if not messages by this client in 40 seconds, then close connection
	           {
	        	   watchDog = 0;
	        	   session_done = true;
	           }
	        }    // end WHILE LOOP - session_done
	      
	      
	       
	      
	        numOfConnections--;

	        // close client socket
	        csocket.close();
	       
	        // update the status text area to show progress of program
		   
		     socketServerVrbo.bottomQuadL.append("Num of Connections = " + numOfConnections + newline);
		     
		     return;
	     } // end try  
	 
	     catch (SocketException e)
	     {
		  // update the status text area to show progress of program
	      socketServerVrbo.bottomQuadL.append("ERROR : Socket Exception!" + newline);
	     }
	     catch (InterruptedException e)
	     {
		  // update the status text area to show progress of program
	      socketServerVrbo.bottomQuadL.append("ERROR : Interrupted Exception!" + newline);
	     }
	     catch (UnknownHostException e)
	     {
		  // update the status text area to show progress of program
	      socketServerVrbo.bottomQuadL.append("ERROR : Unkonw Host Exception" + newline);
	     }
	     catch (IOException e) 
	     {
	     // update the status text area to show progress of program
	      socketServerVrbo.bottomQuadL.append("ERROR : IO Exception!" + newline);
	     }     
	     catch (Exception e)
	     { 
		  numOfConnections--;
		  
		  // update the status text area to show progress of program
	      socketServerVrbo.bottomQuadL.append("ERROR : Generic Exception!" + newline);
	     }
	   
         
        
         if (numOfConnections > 0)
            numOfConnections--;

         // close client socket
         try 
         { 
			csocket.close();
		 } 
         catch (IOException e)
         {
			// TODO Auto-generated catch block
			e.printStackTrace();
		 }
       
         // update the status text area to show progress of program
	     socketServerVrbo.bottomQuadL.append("Num of Connections = " + numOfConnections + newline);
	     
	  }
}
// end run() thread method
	
	


