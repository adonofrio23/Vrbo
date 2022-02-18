package vrbo;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class logIn extends Window {
	public static final long serialVersionUID = 10L;
	private String username;
	private String password;
	
	logIn(int width, int height, String name) {
		super.width = width;
		super.height = height;
		super.name = name;
		createHeaderBar();
		addComponents();
		createWindow();
	}
	
	protected void createHeaderBar() {
		JButton exitButton = createButton("Exit", 16, 1070, 15, 80, 80);
		exitButton.addActionListener(new ExitPrompt()); // Pop-Up Window Functionality
		JButton homeButton = createButton("Home Page", 16, 870, 15, 150, 80);
		homeButton.addActionListener(new HomeListener());
		
		window.add(exitButton);
		window.add(homeButton);
		
		JLabel slogan = createLabel("  Travel Better Together", 20, 250, 15, 230, 80);
		slogan.setBorder(BorderFactory.createLineBorder(Color.black));
		
		window.add(slogan);
		
		JLabel logo = new JLabel();
		Image vrboLogo = fetchImage("https://images.ctfassets.net/uylld2rxwr0n/7H3jIk9OvCrmuFpSyUrkqq/b07aa3c9796d08f2773bc54c69c666d9/Vrbo_logo_dark.png?w=960&q=50", 150, 80);
		logo.setIcon(new ImageIcon(vrboLogo));
		logo.setBounds(50, 15, 150, 80);
		
		window.add(logo);
	}
	
	protected void addComponents() {
		JLabel login = createLabel("Log In", 40, 545, 150, 500, 200);
		JLabel user = createLabel("Username", 30, 250, 300, 300, 80);
		JLabel pass = createLabel("Password", 30, 250, 500, 300, 80);
		
		JTextField usernameInput = createTextField(24, 450, 300, 300, 80);
		JTextField passwordInput = createTextField(24, 450, 500, 300, 80);
		
		JButton submit = createButton("Submit", 30, 450, 650, 300, 80);
		submit.addActionListener(new ActionListener() {
			@Override public void actionPerformed(ActionEvent e) {
				username = usernameInput.getText();
				password = passwordInput.getText();
				
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
		
		window.add(login);
		window.add(user);
		window.add(pass);
		window.add(usernameInput);
		window.add(passwordInput);
		window.add(submit);
	}
}
