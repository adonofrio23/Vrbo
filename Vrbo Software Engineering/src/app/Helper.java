package app;

import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URL;
import java.util.Arrays;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;

public class Helper {
	public static class ExitPrompt implements ActionListener {
 		@Override public void actionPerformed(ActionEvent e) {
 			int res = JOptionPane.showConfirmDialog(
 					null, 
 					"Do you really want to exit VRBO Client?", // Exit message
 					"VRBO Client",  // Pop-Up Window title
 					JOptionPane.INFORMATION_MESSAGE // Options
 			);
 			
 			if (res == JOptionPane.OK_OPTION) {
 				Window.frame.dispose();
 				System.exit(0);
 			}
 		}
 	}
	
	public static class HomeListener implements ActionListener {
 		@Override public void actionPerformed(ActionEvent e) {
 			int res = JOptionPane.showConfirmDialog(
 					null, 
 					"Do you really want to return to VRBO Home Page?", // Exit message
 					"VRBO Client",  // Pop-Up Window title
 					JOptionPane.INFORMATION_MESSAGE // Options
 			);
 			
 			if (res == JOptionPane.OK_OPTION) {
 				ViewManager.switchPage("Home", Home.HomePage);
 			}
 		}
 	}
	
	public static class UploadFile implements ActionListener {
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
	
	public static JButton createButton(String tag, int fontSize, int x, int y, int w, int h) {
 		JButton btn = new JButton(tag);
		btn.setFont(new Font("Tahoma", Font.PLAIN, fontSize));
		btn.setBounds(x, y, w, h);
		return btn;
 	}
 	
 	public static JLabel createLabel(String tag, int fontSize, int x, int y, int w, int h) {
 		JLabel label = new JLabel(tag);
 		label.setFont(new Font("Tahoma", Font.PLAIN, fontSize));
 		label.setBounds(x, y, w, h);
 		return label;
 	}
 	
 	public static JTextField createTextField(int fontSize, int x, int y, int w, int h) {
 		JTextField field = new JTextField();
 		field.setFont(new Font("Tahoma", Font.PLAIN, fontSize));
 		field.setBounds(x, y, w, h);
 		return field;
 	}
 	
 	public static Image fetchImage(String url, int w, int h) {
 		Image image = null;
 		try {
 			URL u = new URL(url);
 			BufferedImage img = ImageIO.read(u);
 			image = img.getScaledInstance(w, h, Image.SCALE_DEFAULT);
 		} catch (Exception e) {
 
 		}
 		return image;
 	}
}
