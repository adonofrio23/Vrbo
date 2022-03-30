package app;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
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
		createHeaderBar();
		createBody();
	}
	
	private void createHeaderBar() {
		JButton exitButton = Helper.createButton("Exit", 16, 1070, 15, 80, 80);
		exitButton.addActionListener(new Helper.ExitPrompt()); // Pop-Up Window Functionality
		JButton homeButton = Helper.createButton("Home Page", 16, 870, 15, 150, 80);
		homeButton.addActionListener(new Helper.HomeListener());
		
		JLabel slogan = Helper.createLabel("  Travel Better Together", 20, 250, 15, 230, 80);
		slogan.setBorder(BorderFactory.createLineBorder(Color.black));
		
		JLabel logo = new JLabel();
		Image vrboLogo = Helper.fetchImage("https://images.ctfassets.net/uylld2rxwr0n/7H3jIk9OvCrmuFpSyUrkqq/b07aa3c9796d08f2773bc54c69c666d9/Vrbo_logo_dark.png?w=960&q=50", 150, 80);
		logo.setIcon(new ImageIcon(vrboLogo));
		logo.setBounds(50, 15, 150, 80);
		
		SignUpPage.add(exitButton);
		SignUpPage.add(homeButton);
		SignUpPage.add(slogan);
		SignUpPage.add(logo);
	}
	
	private void createBody() {
		JLabel username = Helper.createLabel("Username", 24, 150, 200, 200, 50);
		JTextField usernameR = Helper.createTextField(24, 300, 200, 200, 50);
		
		JLabel password = Helper.createLabel("Password", 24, 150, 250, 200, 50);
		JTextField passwordR = Helper.createTextField(24, 300, 250, 200, 50);
		
		JLabel fname = Helper.createLabel("First Name", 24, 150, 300, 200, 50);
		JTextField fnameR = Helper.createTextField(24, 300, 300, 200, 50);
		
		JLabel lname = Helper.createLabel("Last Name", 24, 150, 350, 200, 50);
		JTextField lnameR = Helper.createTextField(24, 300, 350, 200, 50);
		
		JLabel email = Helper.createLabel("Email", 24, 150, 400, 200, 50);
		JTextField emailR = Helper.createTextField(24, 300, 400, 200, 50);
		
		JLabel phone = Helper.createLabel("Phone", 24, 150, 450, 200, 50);
		JTextField phoneR = Helper.createTextField(24, 300, 450, 200, 50);
		
		JLabel cc = Helper.createLabel("Credit Card", 24, 150, 500, 200, 50);
		JTextField ccR = Helper.createTextField(24, 300, 500, 200, 50);
		
		JButton signup = Helper.createButton("Sign Up", 25, 215, 550, 200, 50);
		
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
		
				String customerData[] = new String[7];
				customerData[0] = usernameField;
				customerData[1] = passwordField;
				customerData[2] = fnameField;
				customerData[3] = lnameField;
				customerData[4] = emailField;
				customerData[5] = phoneField;
				customerData[6] = ccField;
				
				fileIO fio = new fileIO("customerData.txt");
				try {
					fio.wrSignUpData(customerData);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
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
