package socketServerVrbo;

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

public class socketServer {	
	static int port = 4545;
	private static ServerSocket ssock = null;
	
	public static void runSockServer() {
		try {
			ssock = new ServerSocket(port);
		} catch (BindException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		try {
			InetAddress ipAddr = InetAddress.getLocalHost();
			socketServerVrbo.bottomQuadL.append("Host IP Address: " + ipAddr.getHostAddress() + "\n");
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		
		socketServerVrbo.bottomQuadL.append("Listening on port: " + port + "\n");
	}
	
	public static void endSocketServer() {
		try {
			ssock.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
