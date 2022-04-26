package server;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Date;
import java.awt.CardLayout;
import java.awt.HeadlessException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Window extends JFrame {
	public static JFrame frame = null;
	static JPanel Container = null;
	private static final CardLayout cards = new CardLayout();
	private static final long serialVersionUID = 1000L;

	public static void initializeWindow(int width, int height, String name) {
		try {
			frame = new JFrame();
			System.out.println("[Server] - Window Initialized Successfully");
		} catch (HeadlessException e) {
			System.out.println("[Server] - Window Failed To Initialize");
			System.exit(1);
		}
		
		frame.setSize(width, height);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
		new LoginWindow();
		initializeView();
		frame.add(Container);
		System.out.println("[Server] - LogIn Page Loaded Successfully");
		
		frame.revalidate();
		startServer();
		
		initializeClock(name);
	}
	
	private static void startServer() {
		 new Thread() {
		    public void run() { 	
				  Server.runServer();
		     }
		 }.start();
	}
	
	private static void initializeView() {
		Container = new JPanel(cards);
		System.out.println("[Server] - Container Initialized Successfully");
		Container.add("Login", LoginWindow.LoginWindow);
		cards.show(Container, "Login");
	}
	
	public static void loadServer() {
		Container.add("Server", ServerWindow.ServerWindow);
		cards.show(Container, "Server");
		System.out.println("[Server] - User Validated...Switching To Server View");
	}
	
	private static void initializeClock(String name) {
		Thread refreshTitleBar = new Thread() {
			@Override public void run() {
				while (true) {
					try {
						Date date = new Date();
						String dateStr = String.format("%tc", date);
						String titleStr = name + " - " + dateStr;
						frame.setTitle(titleStr);
						@SuppressWarnings("unused") NumberFormat format = new DecimalFormat("#0.00");
						sleep(5000L);
					} catch(InterruptedException e) {
						JOptionPane.showMessageDialog(null, 
	                              "ERROR. Interrupt Exception! Check Internet Connection!",
	                              "Title Top Bar",
	                              JOptionPane.WARNING_MESSAGE);
			        	 
			        	 continue;
					} finally {}
				}
			}
		};
		refreshTitleBar.run();
	}
}
