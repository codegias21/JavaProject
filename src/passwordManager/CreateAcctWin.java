package passwordManager;

import java.io.FileWriter;
import java.io.IOException;

import javax.swing.*;;

public class CreateAcctWin extends JFrame {

	JTextField textFieldUsername;
	JTextField textFieldPassword;

	public CreateAcctWin() {
		// Set up the GUI for Account Creation
		setTitle("Account Creation");
		setSize(500, 280);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(null);
		setVisible(true);

		// Labels
		JLabel userNameLabel = new JLabel("User Name:");
		userNameLabel.setBounds(80, 90, 200, 50);
		add(userNameLabel);

		JLabel passwordLabel = new JLabel("Password:");
		passwordLabel.setBounds(80, 140, 200, 50);
		add(passwordLabel);

		// Buttons
		JButton cancelButton = new JButton("Cancel");
		cancelButton.setFocusable(false);
		cancelButton.setBounds(80, 200, 80, 30);
		add(cancelButton);
		cancelButton.addActionListener(e -> dispose());

		JButton createAcctButton = new JButton("Create Account");
		createAcctButton.setFocusable(false);
		createAcctButton.setBounds(250, 200, 140, 30);
		add(createAcctButton);
		createAcctButton.addActionListener(e -> createAccount());

		// Fields for entry
		textFieldUsername = new JTextField(10);
		textFieldUsername.setBounds(240, 110, 120, 20);
		add(textFieldUsername);

		textFieldPassword = new JPasswordField(10);
		textFieldPassword.setBounds(240, 160, 120, 20);
		add(textFieldPassword);
	}

	void createAccount() {
		String username = textFieldUsername.getText().trim();
		String password = new String(((JPasswordField) textFieldPassword).getPassword());
		// Alert of empty fields
		if (username.isEmpty() || password.isEmpty()) {
			JOptionPane.showMessageDialog(this, "Username and password cannot be empty!", "Error",
					JOptionPane.ERROR_MESSAGE);
			return;
		}
		// Check for duplicate user name
		if (isUsernameTaken(username)) {
			JOptionPane.showMessageDialog(this, "Username already exists! Please choose a different username.",
					"Duplicate Username", JOptionPane.ERROR_MESSAGE);
			return;
		}
		// Check validity of password
		if (!isValidPassword(password)) {
			JOptionPane.showMessageDialog(this,
					"Password must be at least 9 characters long and include an uppercase letter, a lowercase letter, and a digit.",
					"Invalid Password", JOptionPane.ERROR_MESSAGE);
			return;
		}
		saveAccount(username, password);
		JOptionPane.showMessageDialog(this, "Password Accepted\nAccount Created");
		dispose();
		new FirstWindow();
	}
	//Checking for duplicate user names 
	boolean isUsernameTaken(String username) {
		try {
			java.io.File file = new java.io.File("Records.txt");
			if (!file.exists()) {
				return false;
			}
			java.io.BufferedReader reader = new java.io.BufferedReader(new java.io.FileReader(file));
			String line;
			while ((line = reader.readLine()) != null) {
				String[] parts = line.split(",");
				if (parts.length > 0 && parts[0].equals(username)) {
					reader.close();
					return true;
				}
			}
			reader.close();
			return false;
		} catch (IOException e) {
			JOptionPane.showMessageDialog(this, "Error checking username!", "File Error", JOptionPane.ERROR_MESSAGE);
			return false;
		}
	}

	private boolean isValidPassword(String password) {
		return password.length() >= 9 && password.matches(".*[A-Z].*") && password.matches(".*[a-z].*")
				&& password.matches(".*\\d.*");
	}

	// Method for checking validity of password
	void saveAccount(String username, String password) {
		try (FileWriter writer = new FileWriter("Records.txt", true)) {
			writer.write(username + "," + password + "\n");
		} catch (IOException e) {
			JOptionPane.showMessageDialog(this, "Error saving account!", "File Error", JOptionPane.ERROR_MESSAGE);
		}

	}
}
