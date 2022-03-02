/*
 * package socketServerVrbo;


import java.io.IOException;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.net.BindException;
import java.net.InetAddress;
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

public abstract class socketServer implements Runnable{
	
	Socket csocket;
	String ipString;
	char threadType;
	
	static final String newline = "\n";
	
	static int first_time = 1;
	   
	static int port_num = 3333;
	

   socketServer(Socket csocket, String ip)
   {
      this.csocket  = csocket;
      this.ipString = ip;
   } 
   
   public static void runSockServer()   // throws Exception
   {
     boolean sessionDone = false;
  
     ServerSocket ssock = null;
   
     try
     {
	   ssock = new ServerSocket(port_num);
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
     try 
     {
	     InetAddress ipAddress = InetAddress.getLocalHost();
	     socketServerJava.center.append("IP Address : " + ipAddress.getHostAddress() + newline);
     }
     catch (UnknownHostException e1)
     {
	    // TODO Auto-generated catch block
	    e1.printStackTrace();
     }
 
     socketServerJava.center.append("Listening on port " + port_num + newline);
 
     //
     // initialize the hash table to the following keys or if file hash table data exists, then use it
     //
   
   try
   {
	   ssock = new ServerSocket(port_num);
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
   try 
   {
	     InetAddress ipAddress = InetAddress.getLocalHost();
	     socketServerJava.center.append("IP Address : " + ipAddress.getHostAddress() + newline);
   }
   catch (UnknownHostException e1)
   {
	    // TODO Auto-generated catch block
	    e1.printStackTrace();
   }

   socketServerJava.center.append("Listening on port " + port_num + newline);

   //
   // initialize the hash table to the following keys or if file hash table data exists, then use it
   //
   catch(Exception e2)
   {   
	    e2.printStackTrace(); 
   }		
   
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
	 
	    // update the status text area to show progress of program
      socketServerVrbo.center.append("Client Connected : " + sock.getInetAddress() + newline);
      
      //
      // start a NEW THREAD process
      //
      new Thread(new socketServer(sock, sock.getInetAddress().toString())).start();
   }

   try 
   {
	    ssock.close();
   }
   catch (IOException e) 
   {
	    e.printStackTrace();
   }
}	  


}
*/
