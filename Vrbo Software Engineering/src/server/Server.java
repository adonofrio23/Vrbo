package server;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.net.BindException;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.ServerSocket;
import java.net.SocketException;
import java.net.Socket;
import java.util.Base64;
import java.util.Collections;
import java.util.Enumeration;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.Border;

import java.awt.event.*;
import java.io.InputStreamReader;
import java.io.PrintStream;

public class Server implements Runnable {
	public static final int port = 4545;
	public static String ip = null;
	public static Socket socket = null;
	
	private static ServerSocket _sSocket = null;
	private int _connections = 0;
	
	private int numLines = 1;
	
	Server(Socket sock) {
		socket = sock;
	}
	
	public static void getInterfaceIP(NetworkInterface net) throws SocketException {
		if (net.getName().startsWith("en")) {
			Collections.list(net.getInetAddresses()).forEach(a -> {
				if (a.isSiteLocalAddress())
					ip = a.toString().replaceFirst("/", "");
			});
		}
	}
	
	public static void runServer() {
		if (!Firebase.init())
			Server.closeServer();;
		boolean sessionDone = false;
		try {
			_sSocket = new ServerSocket(port);
		} catch (IOException e) {
			System.out.println("[Server] - " + e.getMessage());
		}
		
		Enumeration<NetworkInterface> networks = null;
		try {
			networks = NetworkInterface.getNetworkInterfaces(); 
		} catch (SocketException e) {
			System.out.println("[Server] - " + e.getMessage());
		}
		
		for (NetworkInterface net : Collections.list(networks)) {
			try {
				getInterfaceIP(net);
			} catch (SocketException e) {
				System.out.println("[Server] - " + e.getMessage());
			}
		}
		
		try {
			InetAddress address = InetAddress.getByName(ip);
			_sSocket = new ServerSocket(port, 50, address);
		} catch (BindException e) {
			System.out.println("[Server] - " + e.getMessage());
		} catch (IOException e) {
			System.out.println("[Server] - " + e.getMessage());
		}
		
		while (!sessionDone) {
			Socket sock = null;
			
			try {
				sock = _sSocket.accept();
			} catch (IOException e) {
				System.out.println("[Server] - " + e.getMessage());
			}
			
			new Thread(new Server(sock)).start();
		}
	
		closeServer();
	}
	
	public static void closeServer() {
		try {
			_sSocket.close();
			System.exit(0);
		} catch(IOException e) {
			System.out.println("[Server] - " + e.getMessage());
		}
	}
	
	private static String parse(String searchParam, String msg) {
		int offset = searchParam.length();
		int index = msg.indexOf(searchParam) + offset;
		String val = "";

		while (true) {
			if (msg.charAt(index) == ',' || index == msg.length() - 1)
				break;
			
			if (msg.charAt(index) != ':' && msg.charAt(index) != '"')
				val += msg.charAt(index);
			
			index++;
		}
		return val.strip();
	}
	
	private static String readMessage(BufferedReader rstream) {
		String message = "";
		try {
			message = rstream.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return message;
	}
	
	public static void sendMessage(String message) {
		PrintStream pstream = null;
		try {
			pstream = new PrintStream(socket.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
		pstream.println(message);
	}
	
	
	
	
	public void run() {
		try {
			boolean sessionDone = false;
			if (socket != null && socket.isConnected())
				
				if (numLines >= 12)
				{
					ServerWindow.bottomQuadL.setText(""); 
					numLines = 0;
				}
		
			
			
			//Show Bookings when Book is clicked on client side
			
			
			
			// Checking Users logged in 
			
			if (numLines >= 12)
			{
				ServerWindow.bottomQuadL.setText(""); 
				numLines = 0;
			}
			
			ServerWindow.bottomQuadL.append("Client Connected: " + socket.getInetAddress().toString().replace("/", "") + "\n");
			_connections++;
			ServerWindow.bottomQuadL.append("Current # of connections: " + _connections + "\n");
			numLines = numLines + 2;
			
			if (numLines >= 12)
			{
				ServerWindow.bottomQuadL.setText(""); 
				numLines = 0;
			}
			
			BufferedReader rstream = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			DataInputStream dIn = new DataInputStream(socket.getInputStream());
			
			while (!sessionDone) {
				if (rstream.ready()) {
					String message = readMessage(rstream);
					
					ServerWindow.bottomQuadL.append(message + "\n");
					numLines++;
					
					if (numLines >= 12)
					{
						ServerWindow.bottomQuadL.setText(""); 
						numLines = 0;
					}
					
					if (message.toLowerCase().contains("quit"))
						sessionDone = true;
					
					if (message.startsWith("LOGIN=")) {
						String username = parse("username", message);
						String password = parse("password", message);
						
						UserLogin.login(username, password);
					}
					
					if (message.startsWith("CREATE=")) {
						String username = parse("username", message);
						String password = parse("password", message);
						String firstName = parse("firstName", message);
						String lastName = parse("lastName", message);
						String email = parse("email", message);
						String phone = parse("phone", message);
						String cc = parse("cc", message);
						
						CreateUser.create(username, password, firstName, lastName, email, phone, cc);
					}
					
					if (message.startsWith("LIST=")) {
						String address = parse("address", message);
						String beds = parse("beds", message);
						String baths = parse("baths", message);
						String amenities = parse("amenities", message);
						String description = parse("description", message);
						String price = parse("price", message);
						String city = parse("city", message);
						String picture = parse("picture", message);
						/*int len = dIn.readInt();
						byte[] pic = null;
						if(len > 0) {
							System.out.println("Hi");
						    pic = new byte[len];
						    dIn.readNBytes(pic, 0, pic.length);
						    System.out.println("Hi 2");
						}*/
						byte[] pic = Base64.getDecoder().decode(picture);
						
						ListProperty.list(address, beds, baths, amenities, description, price, city, pic);
					}
					

					if (message.startsWith("BOOKING=")) {
						String location = parse("location", message);
						String address = parse("address", message);
						ServerWindow.bottomQuadR.append("New Booking in " + location + ": " + address + "\n");
						sendMessage("BOOKED");
					}
					if (message.startsWith("FETCHALL=")) {
						String location = parse("city", message);
						GetListings.getListing(location);
					}
					
				}
				
				if (numLines >= 12)
				{
					ServerWindow.bottomQuadL.setText(""); 
					numLines = 0;
				}
				
				Thread.sleep(500);
			}
			
			
			_connections--;
			socket.close();
			ServerWindow.bottomQuadL.append("Current # of connections: " + _connections + "\n");
			return;
		} catch (SocketException e) {
			System.out.println("[Server] - " + e.getMessage());
		} catch (IOException e) {
			System.out.println("[Server] - " + e.getMessage());
		} catch (InterruptedException e) {
			System.out.println("[Server] - " + e.getMessage());
		}
		
		if (_connections > 0)
			_connections--;
		
		try {
			socket.close();
		} catch (IOException e) {
			System.out.println("[Server] - " + e.getMessage());
		}
	}
}
