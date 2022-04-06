package socketServerVrbo;

import java.io.IOException;
import java.net.BindException;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.ServerSocket;
import java.net.SocketException;
import java.util.Collections;
import java.util.Enumeration;

public class socketServer {	
	static int port = 4545;
	static final String newline = "\n";
	static int first_time = 1;
	private static ServerSocket ssock = null;
	static int numOfConnections = 0;
	static int numOfMessages = 0;
	static int max_connections = 5;
	static int numOfTransactions = 0;
	
	static boolean found = false;
	static String ipAddrOfSocketServer = null;

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
	 
	     // update the status text area to show progress of program
	     socketServerVrbo.bottomQuadL.append("IP Address : " + ipAddrOfSocketServer + newline);
	     socketServerVrbo.bottomQuadL.append("Port: " + Integer.toString(port));
	     String titleString = "Vrbo Socket Server - " + "Address: " + ipAddrOfSocketServer + ":" + port;
	     socketServerVrbo.frame.setTitle(titleString);
	}   
	
	public static void endSocketServer() {
		try {
			ssock.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
