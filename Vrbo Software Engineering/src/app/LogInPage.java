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
import javax.swing.border.Border;

public class LogInPage {
	public static JPanel LogInPage = null;
	LogInPage() {
		LogInPage = new JPanel(null);
		LogInPage.setName("Log In");
		LogInPage.setBackground(Color.decode("#0e214b"));
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
		
		LogInPage.add(exitButton);
		LogInPage.add(homeButton);
		LogInPage.add(slogan);
		LogInPage.add(logo);
	}
	
	private void createBody() {
				
		JLabel login = Helper.createLabel("Log In", 30, 540, 220, 500, 200);
		JLabel user = Helper.createLabel("Username", 20, 400, 400, 200, 30);
		JLabel pass = Helper.createLabel("Password", 20, 400, 460, 200, 30);
		
		JTextField usernameInput = Helper.createTextField(20, 500, 400, 200, 30);
		JTextField passwordInput = Helper.createTextField(20, 500, 460, 200, 30);
		
		JButton submit = Helper.createButton("Submit", 20, 530, 550, 100, 30);

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
					SocketUtils.sendMessage("Hello...testing");
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
