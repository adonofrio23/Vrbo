package app;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Date;
import java.awt.HeadlessException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Window extends JFrame {
	public static JFrame frame = null;
	private static final long serialVersionUID = 1000L;

	public static void initializeWindow(int width, int height, String name) {
		try {
			frame = new JFrame();
			frame.setSize(width, height);
			frame.setLocationRelativeTo(null);
			
			frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
			frame.addWindowListener(new java.awt.event.WindowAdapter() {
				@Override public void windowClosing(java.awt.event.WindowEvent e) {
					e.getWindow().dispose();
				}
				@Override public void windowClosed(java.awt.event.WindowEvent e) {
					e.getWindow().dispose();
					System.out.println("[System] - VRBO App Terminated");
					
					if (SocketUtils.socketConnected) {
						SocketUtils.Disconnect();
						System.out.println("[Socket] - Disconnected From Socket");
					}
					
					System.exit(0);
				}
			});
			System.out.println("[JFrame] - Window Initialized Successfully");
		} catch (HeadlessException e) {
			System.out.println("[JFrame] - Window Failed To Initialize");
			System.exit(1);
		}
		
		System.out.println("[Socket] - Trying To Connect To \"" + SocketUtils.ip + ":" + SocketUtils.port + "\"");
		if (!SocketUtils.Connect()) {
			System.out.println("[Socket] - Failed To Connect To \"" + SocketUtils.ip + ":" + SocketUtils.port + "\"");
			frame.dispatchEvent(new java.awt.event.WindowEvent(frame, java.awt.event.WindowEvent.WINDOW_CLOSED));
		}
		
		System.out.println("[Socket] - Connected To " + SocketUtils.ip + ":" + SocketUtils.port);
		SocketUtils.socketConnected = true;
		
		new Home();
		ViewManager.initializeView();
		frame.add(ViewManager.Container);
		System.out.println("[JPanel] - Home Page Loaded Successfully");
		
		frame.setVisible(true);
		
		initializeClock(name);
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
