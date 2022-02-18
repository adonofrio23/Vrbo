package vrbo;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URL;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Arrays;
import java.util.Date;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.filechooser.FileSystemView;
import javax.swing.filechooser.FileNameExtensionFilter;

public abstract class Window extends JFrame {
	protected static final long serialVersionUID = 1L;
	protected int width;
	protected int height;
	protected String name;
	protected JPanel window = new JPanel(null);
	
	protected void createWindow() {
		setSize(width, height); // Set size of the window
		setLocationRelativeTo(null); // Position in middle of screen
		setBackground(Color.WHITE); // Set window background
		setContentPane(window); // Set JPanel as container for window
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Allow exit button to actually exit
		setVisible(true); // Make window visible
	}
	
	protected void createClock() {
		Thread refreshTitleBar = new Thread() {
			@Override public void run() {
				while (true) {
					try {
						Date date = new Date();
						String dateStr = String.format("%tc", date);
						String titleStr = name + dateStr;
						setTitle(titleStr);
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
	
	protected class ExitPrompt implements ActionListener {
 		@Override public void actionPerformed(ActionEvent e) {
 			int res = JOptionPane.showConfirmDialog(
 					null, 
 					"Do you really want to exit VRBO Client?", // Exit message
 					"VRBO Client",  // Pop-Up Window title
 					JOptionPane.INFORMATION_MESSAGE // Options
 			);
 			
 			if (res == JOptionPane.OK_OPTION) {
 				dispose();
 				System.exit(0);
 			}
 		}
 	}
	
	protected class HomeListener implements ActionListener {
 		@Override public void actionPerformed(ActionEvent e) {
 			int res = JOptionPane.showConfirmDialog(
 					null, 
 					"Do you really want to return to VRBO Home Page?", // Exit message
 					"VRBO Client",  // Pop-Up Window title
 					JOptionPane.INFORMATION_MESSAGE // Options
 			);
 			
 			if (res == JOptionPane.OK_OPTION) {
 				dispose();
 			}
 		}
 	}
	
	protected class UploadFile implements ActionListener {
		@Override public void actionPerformed(ActionEvent e) {
			JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
			jfc.setDialogTitle("Select Files:");
			jfc.setMultiSelectionEnabled(true);
			jfc.setFileSelectionMode(JFileChooser.FILES_ONLY);
			jfc.setAcceptAllFileFilterUsed(false);
			FileNameExtensionFilter filter = new FileNameExtensionFilter("PNG and GIF images", "png", "gif");
			jfc.addChoosableFileFilter(filter);

			int returnValue = jfc.showOpenDialog(null);
			if (returnValue == JFileChooser.APPROVE_OPTION) {
				File[] files = jfc.getSelectedFiles();
				System.out.println("Files Found\n");
				Arrays.asList(files).forEach(x -> {
					if (x.isFile()) {
						System.out.println(x.getName());
					}
				});
			}
		}
	}
	
	protected JButton createButton(String tag, int fontSize, int x, int y, int w, int h) {
 		JButton btn = new JButton(tag);
		btn.setFont(new Font("Tahoma", Font.PLAIN, fontSize));
		btn.setBounds(x, y, w, h);
		return btn;
 	}
 	
 	protected JLabel createLabel(String tag, int fontSize, int x, int y, int w, int h) {
 		JLabel label = new JLabel(tag);
 		label.setFont(new Font("Tahoma", Font.PLAIN, fontSize));
 		label.setBounds(x, y, w, h);
 		return label;
 	}
 	
 	protected JTextField createTextField(int fontSize, int x, int y, int w, int h) {
 		JTextField field = new JTextField();
 		field.setFont(new Font("Tahoma", Font.PLAIN, fontSize));
 		field.setBounds(x, y, w, h);
 		return field;
 	}
 	
 	protected Image fetchImage(String url, int w, int h) {
 		Image image = null;
 		try {
 			URL u = new URL(url);
 			BufferedImage img = ImageIO.read(u);
 			image = img.getScaledInstance(w, h, Image.SCALE_DEFAULT);
 		} catch (Exception e) {
 
 		}
 		return image;
 	}
	
	protected abstract void createHeaderBar();
	protected abstract void addComponents();
}
