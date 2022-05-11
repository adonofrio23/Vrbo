package server;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class LoginWindow {
	public static JPanel LoginWindow = null;
	
	LoginWindow() {
		LoginWindow = new JPanel(null);
		LoginWindow.setBackground(Color.decode("#0e214b"));
		LoginWindow.setName("Login");
		createPage();
	}
	
	private void createPage() {
		JLabel login = createLabel("Log In", 40, 400, 100, 400, 100);
		login.setForeground(Color.WHITE);
		JLabel user = createLabel("Username", 30, 150, 200, 200, 80);
		user.setForeground(Color.WHITE);
		JLabel pass = createLabel("Password", 30, 150, 400, 200, 80);
		pass.setForeground(Color.WHITE);
		
		JTextField usernameInput = createTextField(24, 350, 200, 200, 80);
		JTextField passwordInput = createTextField(24, 350, 400, 200, 80);
		
		JButton submit = createButton("Submit", 30, 350, 550, 200, 80);
		submit.addActionListener(new ActionListener() {
			@Override public void actionPerformed(ActionEvent e) {
				String username = usernameInput.getText();
				String password = passwordInput.getText();
				
				AdminLogin.login(username, password);
				
				if (AdminLogin.res) {
					String[] options = {"OK"};
					JOptionPane.showOptionDialog(
		 					null, 
		 					"Error: Invalid Username or Password", // Exit message
		 					"VRBO Server",  // Pop-Up Window title
		 					JOptionPane.PLAIN_MESSAGE,
		                    JOptionPane.QUESTION_MESSAGE,
		                    null,
		                    options,
		                    options[0]
		 			);
				} else {
					System.out.println("[Server] - Admin Successfully Logged In: " + username + " / " + password);
					new ServerWindow();
					Window.loadServer();
				}
			}
		});
		
		LoginWindow.add(login);
		LoginWindow.add(user);
		LoginWindow.add(pass);
		LoginWindow.add(usernameInput);
		LoginWindow.add(passwordInput);
		LoginWindow.add(submit);
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
}
