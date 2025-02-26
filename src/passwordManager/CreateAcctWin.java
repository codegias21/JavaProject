/**
 * 
 */
package passwordManager;

import javax.swing.*;
;

/**
 * 
 */
public class CreateAcctWin extends JFrame {

	public CreateAcctWin() {
		// Set up the GUI for Account Creation
		setTitle("Account Creation");
		setSize(500, 280);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(null);
		setVisible(true);
		
		
		JLabel userNameLabel = new JLabel("User Name:");
		userNameLabel.setBounds(80, 90, 200, 50);
		add(userNameLabel);
		
		JLabel passwordLabel = new JLabel("Password:");
		passwordLabel.setBounds(80, 140, 200, 50);
		add(passwordLabel);
		
		JButton cancelButton = new JButton("Cancel");
		cancelButton.setFocusable(false);
		cancelButton.setBounds(80,200, 80, 30);
		add(cancelButton);
		
		JButton createAcctButton = new JButton("Create Account");
		createAcctButton.setFocusable(false);
		createAcctButton.setBounds(250,200, 140, 30);
		add(createAcctButton);
		
		JTextField textFieldUsername = new JTextField(10);
		textFieldUsername.setBounds(240,110,120,20);
		add(textFieldUsername);
		

		JTextField textFieldPassword = new JTextField(10);
		textFieldPassword.setBounds(240,160,120,20);
		add(textFieldPassword);
		
		
	}
}
