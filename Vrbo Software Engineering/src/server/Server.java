package server;

import java.io.IOException;
import java.io.BufferedReader;
import java.net.BindException;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.ServerSocket;
import java.net.SocketException;
import java.net.Socket;
import java.util.Collections;
import java.util.Enumeration;

import java.io.InputStreamReader;
import java.io.PrintStream;

public class Server implements Runnable {
	public static final int port = 4545;
	public static String ip = null;
	public static Socket socket = null;
	
	private static ServerSocket _sSocket = null;
	private int _connections = 0;
	
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
				ServerWindow.bottomQuadL.append("Client Connected: " + socket.getInetAddress().toString().replace("/", "") + "\n");
			_connections++;
			ServerWindow.bottomQuadL.append("Current # of connections: " + _connections + "\n");
			
			BufferedReader rstream = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			
			while (!sessionDone) {
				if (rstream.ready()) {
					String message = readMessage(rstream);
					ServerWindow.bottomQuadL.append(message + "\n");
					
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
