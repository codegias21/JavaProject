package passwordManager;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class FirstWindow extends JFrame {

	public FirstWindow() {
		
		// Creating the main frame
		setTitle("Password Manager");
		setSize(400, 400);
		setLayout(null); 
		setLocationRelativeTo(null);

		// Program Title
		JLabel programLabel = new JLabel("Password Manager", SwingConstants.CENTER);
		programLabel.setFont(new Font("Arial", Font.BOLD, 18));
		programLabel.setBounds(50, 20, 300, 30); // (x, y, width, height)
		add(programLabel);

		// Adding graphics
		ImageIcon icon = new ImageIcon("C:/Users/manas/Downloads/download.png"); // Replace with your image path
		JLabel imageLabel = new JLabel(icon);
		imageLabel.setBounds(100, 80, 225, 200); // Adjust position and size
		add(imageLabel);

		// Create buttons
		JButton cancelButton = new JButton("Cancel");
		cancelButton.setFocusable(false);
		cancelButton.setBounds(30, 320, 100, 30);
		JButton createAccountButton = new JButton("Create Account");
		createAccountButton.setFocusable(false);
		createAccountButton.setBounds(140, 320, 140, 30);
		JButton loginButton = new JButton("Login");
		loginButton.setFocusable(false);
		loginButton.setBounds(290, 320, 80, 30);

		// Add buttons to frame
		add(createAccountButton);
		add(loginButton);
		add(cancelButton);

		// Action Listeners
		createAccountButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new CreateAcctWin(); //Create Account Window
			}
		});

		loginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "Login functionality under development");// Account Creation Window
			}
		});

		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose(); // Close the application
			}
		});
		
		// Visibility and Closure
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
}
