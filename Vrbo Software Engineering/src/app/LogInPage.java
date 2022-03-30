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

public class LogInPage {
	public static JPanel LogInPage = null;
	LogInPage() {
		LogInPage = new JPanel(null);
		LogInPage.setName("Log In");
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
		
		LogInPage.add(exitButton);
		LogInPage.add(homeButton);
		LogInPage.add(slogan);
		LogInPage.add(logo);
	}
	
	private void createBody() {
		JLabel login = Helper.createLabel("Log In", 40, 545, 150, 500, 200);
		JLabel user = Helper.createLabel("Username", 30, 250, 300, 300, 80);
		JLabel pass = Helper.createLabel("Password", 30, 250, 500, 300, 80);
		
		JTextField usernameInput = Helper.createTextField(24, 450, 300, 300, 80);
		JTextField passwordInput = Helper.createTextField(24, 450, 500, 300, 80);
		
		JButton submit = Helper.createButton("Submit", 30, 450, 650, 300, 80);
		submit.addActionListener(new ActionListener() {
			@Override public void actionPerformed(ActionEvent e) {
				String username = usernameInput.getText();
				String password = passwordInput.getText();
				
				if (username.length() == 0 || password.length() == 0) {
					String[] options = {"OK"};
					JOptionPane.showOptionDialog(
		 					null, 
		 					"Error: Invalid Username or Password", // Exit message
		 					"VRBO Client",  // Pop-Up Window title
		 					JOptionPane.PLAIN_MESSAGE,
		                    JOptionPane.QUESTION_MESSAGE,
		                    null,
		                    options,
		                    options[0]
		 			);
				} else {
					
				}
			}
		});

		LogInPage.add(login);
		LogInPage.add(user);
		LogInPage.add(pass);
		LogInPage.add(usernameInput);
		LogInPage.add(passwordInput);
		LogInPage.add(submit);
	}
}
