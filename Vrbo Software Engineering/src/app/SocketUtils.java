package app;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;

public class SocketUtils {
	static Socket clientSocket = null;
    static DataOutputStream outToServer = null;
    static BufferedReader inFromServer = null;
    static final String ip = "10.88.11.12";
	static final int port = 4545;
	static boolean socketConnected = false;
    
    public static boolean Connect() {
    	try {
    		clientSocket = new Socket(ip, port);
            outToServer = new DataOutputStream(clientSocket.getOutputStream());
            inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            socketConnected = true;
            return true;
    	} catch (SecurityException e) {
    		System.out.println("[Error] - " + e.getMessage());
    		return false;
    	} catch (UnknownHostException e) {
    		System.out.println("[Error] - " + e.getMessage());
    		return false;
    	} catch (IOException e) {
    		System.out.println("[Error] - " + e.getMessage());
    		return false;
    	} catch (IllegalArgumentException e) {
    		System.out.println("[Error] - " + e.getMessage());
    		return false;
    	}
    }
    
    public static boolean Disconnect() {
    	try {
    		clientSocket.close();
    		socketConnected = false;
    		return true;
    	} catch (IOException e) {
    		System.out.println("[Error] - " + e.getMessage());
    		return false;
    	}
    }
    
    public static boolean sendMessage(String m) {
    	try {
    		outToServer.writeBytes(m + "\r\n");
    		return true;
    	} catch (IOException e) {
    		System.out.println("[Error] - " + e.getMessage());
    		return false;
    	}
    }
    
    public static boolean sendBookMessage(String b) {
    	try {
    		outToServer.writeBytes(b + "\r\n");
    		return true;
    	} catch (IOException e) {
    		System.out.println("[Error] - " + e.getMessage());
    		return false;
    	}
    }
    
    
    
    public static String receiveMessage() {
    	String message = null;
    	
    	try {
    		message = inFromServer.readLine();
    	} catch (IOException e) {
    		System.out.println("[Error] - " + e.getMessage());
    	}
    	
    	return message;
    }
}
