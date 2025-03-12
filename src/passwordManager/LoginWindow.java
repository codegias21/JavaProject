package passwordManager;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import javax.swing.*;

public class LoginWindow extends JFrame {
	
	JTextField textFieldUsername;
	JTextField textFieldPassword;
	
	public LoginWindow() {
	setTitle("Login");
    setSize(500, 280);
    setLocationRelativeTo(null);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setLayout(null);
    //Labels  
    JLabel userNameLabel = new JLabel("User Name:");
	userNameLabel.setBounds(80, 90, 200, 50);
	add(userNameLabel);
	
	JLabel passwordLabel = new JLabel("Password:");
	passwordLabel.setBounds(80, 140, 200, 50);
	add(passwordLabel);
	//Buttons
	JButton cancelButton = new JButton("Cancel");
	cancelButton.setFocusable(false);
	cancelButton.setBounds(80,200, 80, 30);
	add(cancelButton);
	cancelButton.addActionListener(e -> dispose());
	
	JButton loginButton = new JButton("Login");
	loginButton.setFocusable(false);
	loginButton.setBounds(250,200, 140, 30);
	add(loginButton);
	loginButton.addActionListener(e -> authenticateUser());
	//Text fields
	textFieldUsername = new JTextField(10);
	textFieldUsername.setBounds(240,110,120,20);
	add(textFieldUsername);
	

	textFieldPassword = new JPasswordField(10);
	textFieldPassword.setBounds(240,160,120,20);
	add(textFieldPassword);
	
	 setVisible(true);
	}
	//Authentication method
	  void authenticateUser() {
	        String username = textFieldUsername.getText().trim();
	        String password = new String(((JPasswordField) textFieldPassword).getPassword());

	        if (checkCredentials(username, password)) {
	            JOptionPane.showMessageDialog(this, "Login successful");
	            dispose();
	            new MainInterface();
	            
	        } else {
	            JOptionPane.showMessageDialog(this, "Invalid username or password.", "Login Failed", JOptionPane.ERROR_MESSAGE);
	        }
	    }
	  //Checking for existence of user name and password
	   boolean checkCredentials(String username, String password) {
	        try (Scanner scanner = new Scanner(new File("Records.txt"))) {
	            while (scanner.hasNextLine()) {
	                String[] credentials = scanner.nextLine().split(",");
	                if (credentials.length == 2 && credentials[0].equals(username) && credentials[1].equals(password)) {
	                    return true;
	                }
	            }
	        } catch (IOException e) {
	            JOptionPane.showMessageDialog(this, "Error reading accounts file!", "File Error", JOptionPane.ERROR_MESSAGE);
	        }
	        return false;
	    }


}
