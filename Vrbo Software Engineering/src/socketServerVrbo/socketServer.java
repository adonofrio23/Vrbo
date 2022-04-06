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
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.PrintWriter;

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

//	socketServer(Socket csocket, String ip)
//	{
//		this.csocket  = csocket;
//		this.ipString = ip;
//	} 
//	
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
		     
	     socketServerVrbo.bottomQuadL.setText(ipAddrOfSocketServer + newline);
	     socketServerVrbo.bottomQuadL.append("Port: " + Integer.toString(port));
	}
	 
	  //   socketServerVrbo.wrToCenterTA("Listening on port " + port_num + newline);
	 
	     //
	     // initialize the hash table to the following keys or if file hash table data exists, then use it
	     //
	    
	
	public static void endSocketServer() {
		try {
			ssock.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
