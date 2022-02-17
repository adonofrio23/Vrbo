package vrbo;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class SignUp extends Window{

	public static final long serialVersionUID = 10L;
		
		SignUp(int width, int height, String name) {
			super.width = width;
			super.height = height;
			super.name = name;
			createHeaderBar();
			addComponents();
			createWindow();
		}
		
	protected void createHeaderBar() {
		
		JLabel logo = new JLabel();
		Image vrboLogo = fetchImage("https://images.ctfassets.net/uylld2rxwr0n/7H3jIk9OvCrmuFpSyUrkqq/b07aa3c9796d08f2773bc54c69c666d9/Vrbo_logo_dark.png?w=960&q=50", 150, 80);
		logo.setIcon(new ImageIcon(vrboLogo));
		logo.setBounds(240, 40, 150, 80);
		
		window.add(logo);
	}
		
		
	protected void addComponents() {
		
		JLabel username = createLabel("Username", 24, 150, 200, 200, 50);
		JTextField usernameR = createTextField(24, 300, 200, 200, 50);
		
		JLabel password = createLabel("Password", 24, 150, 250, 200, 50);
		JTextField passwordR = createTextField(24, 300, 250, 200, 50);
		
		JLabel fname = createLabel("First Name", 24, 150, 300, 200, 50);
		JTextField fnameR = createTextField(24, 300, 300, 200, 50);
		
		JLabel lname = createLabel("Last Name", 24, 150, 350, 200, 50);
		JTextField lnameR = createTextField(24, 300, 350, 200, 50);
		
		JLabel email = createLabel("Email", 24, 150, 400, 200, 50);
		JTextField emailR = createTextField(24, 300, 400, 200, 50);
		
		JLabel phone = createLabel("Phone", 24, 150, 450, 200, 50);
		JTextField phoneR = createTextField(24, 300, 450, 200, 50);
		
		JLabel cc = createLabel("Credit Card", 24, 150, 500, 200, 50);
		JTextField ccR = createTextField(24, 300, 500, 200, 50);
		
		JButton signup = createButton("Sign Up", 24, 215, 550, 200, 50);
		
		window.add(username);
		window.add(usernameR);
		window.add(password);
		window.add(passwordR);
		window.add(fname);
		window.add(fnameR);
		window.add(lname);
		window.add(lnameR);
		window.add(email);
		window.add(emailR);
		window.add(phone);
		window.add(phoneR);
		window.add(cc);
		window.add(ccR);
		window.add(signup);
	}
}
