package vrbo;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
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
		
		JButton signup = createButton("Sign Up", 25, 215, 550, 200, 50);
		
		JButton exit = createButton("Exit", 15, 20, 20, 50, 50);
	
		exit.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{			
 
				    dispose();
			}
			
		});
		
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
					JOptionPane.showMessageDialog(null, "ERROR: Name field is empty!", "VRBO Sign Up", JOptionPane.WARNING_MESSAGE);
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
		window.add(exit);
		window.add(signup);
	}
}
