package app;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class SignUpPage {
	public static JPanel SignUpPage = null;
	
	SignUpPage() {
		SignUpPage = new JPanel(null);
		SignUpPage.setName("Sign Up");
		SignUpPage.setBackground(Color.decode("#0e214b"));
		createHeaderBar();
		createBody();
	}
	
	private void createHeaderBar() {
		JButton exitButton = Helper.createButton("Exit", 16, 1010, 90, 100, 30);		exitButton.addActionListener(new Helper.ExitPrompt()); // Pop-Up Window Functionality
		JButton homeButton = Helper.createButton("Home", 16, 900, 90, 100, 30);

		homeButton.addActionListener(new Helper.HomeListener());
		
		
		JLabel slogan = Helper.createLabel("Travel Better Together", 20, 130, 90, 300, 30);
		slogan.setForeground(Color.WHITE);
		JLabel logo = new JLabel();
		//Image vrboLogo = Helper.fetchImage("https://images.ctfassets.net/uylld2rxwr0n/7H3jIk9OvCrmuFpSyUrkqq/b07aa3c9796d08f2773bc54c69c666d9/Vrbo_logo_dark.png?w=960&q=50", 150, 80);
		Image vrboLogo = Helper.fetchImage("https://csvcus.homeaway.com/rsrcs-crs/cdn-logos/5.1.2/sitename/vrbo/web/image_src.png", 150, 80);
		logo.setIcon(new ImageIcon(vrboLogo));
		logo.setBounds(5, 0, 150, 200);
		
		SignUpPage.add(exitButton);
		SignUpPage.add(homeButton);
		SignUpPage.add(slogan);
		SignUpPage.add(logo);
	}
	
	private void createBody() {
		
		JLabel signUpHeader = Helper.createLabel("Sign Up", 30, 540, 220, 500, 100);

		
		JLabel username = Helper.createLabel("Username", 20, 400, 300, 200, 30); 
		JTextField usernameR = Helper.createTextField(24, 500, 300, 200, 30);
		
		JLabel password = Helper.createLabel("Password", 20, 400, 350, 200, 30);
		JTextField passwordR = Helper.createTextField(20, 500, 350, 200, 30);
		
		JLabel fname = Helper.createLabel("First Name", 20, 400, 400, 200, 30);
		JTextField fnameR = Helper.createTextField(20, 500, 400, 200, 30);
		
		JLabel lname = Helper.createLabel("Last Name", 20, 400, 450, 200, 30);
		JTextField lnameR = Helper.createTextField(20, 500, 450, 200, 30);
		
		JLabel email = Helper.createLabel("Email", 20, 400, 500, 200, 30);
		JTextField emailR = Helper.createTextField(20, 500, 500, 200, 30);
		
		JLabel phone = Helper.createLabel("Phone", 20, 400, 550, 200, 30);
		JTextField phoneR = Helper.createTextField(20, 500, 550, 200, 30);
		
		JLabel cc = Helper.createLabel("Credit Card", 20, 400, 600, 200, 30);
		JTextField ccR = Helper.createTextField(20, 500, 600, 200, 30);
		
		JButton signup = Helper.createButton("Sign Up", 20, 550, 650, 100, 30);

		signup.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				String usernameField = usernameR.getText();
				String passwordField = passwordR.getText();
				String fnameField = fnameR.getText(); 
				String lnameField = lnameR.getText();
				String emailField = emailR.getText();
				String phoneField = phoneR.getText();
				String ccField = ccR.getText();
				
				if (usernameField == "" || usernameField == null || usernameField.length() == 0)
				{
					JOptionPane.showMessageDialog(null, "ERROR: Username field is empty!", "VRBO Sign Up", JOptionPane.WARNING_MESSAGE);
					return;
				}
				if (passwordField == "" || passwordField == null || passwordField.length() == 0)
				{
					JOptionPane.showMessageDialog(null, "ERROR: Password field is empty!", "VRBO Sign Up", JOptionPane.WARNING_MESSAGE);
					return;
				}
				if (fnameField == "" || fnameField == null || fnameField.length() == 0)
				{
					JOptionPane.showMessageDialog(null, "ERROR: First Name field is empty!", "VRBO Sign Up", JOptionPane.WARNING_MESSAGE);
					return;
				}
				if (lnameField == "" || lnameField == null || lnameField.length() == 0)
				{
					JOptionPane.showMessageDialog(null, "ERROR: Last Name field is empty!", "VRBO Sign Up", JOptionPane.WARNING_MESSAGE);
					return;
				}
				
				if (emailField == "" || emailField == null || emailField.length() == 0)
				{
					JOptionPane.showMessageDialog(null, "ERROR: Email field is empty!", "VRBO Sign Up", JOptionPane.WARNING_MESSAGE);
					return;
				}
				
				if (phoneField == "" || phoneField == null || phoneField.length() == 0)
				{
					JOptionPane.showMessageDialog(null, "ERROR: Phone number field is empty!", "VRBO Sign Up", JOptionPane.WARNING_MESSAGE);
					return;
				}
				if (ccField == "" || ccField == null || ccField.length() == 0)
				{
					JOptionPane.showMessageDialog(null, "ERROR: Credit Card number field is empty!", "VRBO Sign Up", JOptionPane.WARNING_MESSAGE);
					return;
				}
				SocketUtils.sendMessage("CREATE=\"username\": \"" + usernameField + "\", "
										+ "\"password\": \"" + passwordField + "\", "
										+ "\"firstName\": \"" + fnameField + "\", "
										+ "\"lastName\": \"" + lnameField + "\", "
										+ "\"email\": \"" + emailField + "\", "
										+ "\"phone\": \"" + phoneField + "\", "
										+ "\"cc\": \"" + ccField + "\", ");
				
				String validation = SocketUtils.receiveMessage();
				
				if (validation.equals("SUCCESS")) {
					SocketUtils.sendMessage("User Successfully Created: " + usernameField + " / " + passwordField);
				} else if (validation.equals("ERROR")) {
					Window.frame.dispose();
				}
				
				usernameR.setText("");
				passwordR.setText("");
				fnameR.setText("");
				lnameR.setText("");
				emailR.setText("");
				phoneR.setText("");
				ccR.setText("");
			}
			
			
		});
		
		SignUpPage.add(signUpHeader);
		SignUpPage.add(username);
		SignUpPage.add(usernameR);
		SignUpPage.add(password);
		SignUpPage.add(passwordR);
		SignUpPage.add(fname);
		SignUpPage.add(fnameR);
		SignUpPage.add(lname);
		SignUpPage.add(lnameR);
		SignUpPage.add(email);
		SignUpPage.add(emailR);
		SignUpPage.add(phone);
		SignUpPage.add(phoneR);
		SignUpPage.add(cc);
		SignUpPage.add(ccR);
		SignUpPage.add(signup);
	}
}
