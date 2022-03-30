package app;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Date;
import java.awt.HeadlessException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

// Window class that can only be instantiated once
// This will be the recurring window we use
// This window will contain all the panels
public class Window extends JFrame {
	public static JFrame frame = null;
	private static final long serialVersionUID = 1000L;

	public static boolean initializeWindow(int width, int height, String name) {
		try {
			frame = new JFrame();
			System.out.println("[JFrame] - Window Initialized Successfully");
		} catch (HeadlessException e) {
			System.out.println("[JFrame] - Window Failed To Initialize");
			System.exit(1);
		}
		
		new Home();
		ViewManager.initializeView();
		frame.add(ViewManager.Container);
		System.out.println("[JPanel] - Home Page Loaded Successfully");
		
		frame.setSize(width, height);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
		initializeClock(name);
		return true;
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
