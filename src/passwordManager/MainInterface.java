package passwordManager;

import java.io.FileWriter;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class MainInterface extends JFrame {
	
	JTextField descField;
	JTextField userField;
	JTextField passField;
	
    public MainInterface() {
        setTitle("Password Manager");
        setSize(500, 480);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
      
        JLabel programLabel = new JLabel("Password Management System", SwingConstants.CENTER);
        programLabel.setBounds(100, 10, 300, 20);
        add(programLabel);
        
        ImageIcon icon = new ImageIcon("C:/Users/manas/Downloads/download1.jpg"); // Replace with your image path
		JLabel imageLabel = new JLabel(icon);
		imageLabel.setBounds(100, 35, 225, 100); // Adjust position and size
		add(imageLabel);

        JLabel descLabel = new JLabel("Enter the Description/Company:");
        descLabel.setBounds(50, 170, 180, 20);
        add(descLabel);
        
        descField = new JTextField();
        descField.setBounds(250, 170, 160, 20);
        add(descField);

        JLabel userLabel = new JLabel("Enter the User Name:");
        userLabel.setBounds(50, 220, 120, 20);
        add(userLabel);

        userField = new JTextField();
        userField.setBounds(250, 220, 160, 20);
        add(userField);

        JLabel passLabel = new JLabel("Enter the Password:");
        passLabel.setBounds(50, 270, 120, 20);
        add(passLabel);

        passField = new JPasswordField();
        passField.setBounds(250, 270, 160, 20);
        add(passField);

        JButton displayButton = new JButton("Display All");
        displayButton.setBounds(120, 360, 120, 20);
        add(displayButton);

        JButton searchButton = new JButton("Search");
        searchButton.setBounds(270, 360, 100, 20);
        add(searchButton);

        JButton updateButton = new JButton("Update");
        updateButton.setBounds(200, 400, 100, 20);
        updateButton.addActionListener(e -> updateRecords());
        add(updateButton);

        setVisible(true);
        

      
    }
    
    void updateRecords(){
    	String description = descField.getText();
    	String username = userField.getText();
    	String password = passField.getText();
    	
    	if(description.isEmpty() || password.isEmpty() || username.isEmpty()) {
			JOptionPane.showMessageDialog(this, "Description, username or password fields can not be empty! ", "Error",
					JOptionPane.ERROR_MESSAGE);
    	}
    	
    	if(isDescriptionTaken(description)) {
    		JOptionPane.showMessageDialog(this,"You can not have duplicate descriptions.",
					"Duplicate Description", JOptionPane.ERROR_MESSAGE);
    	}
    	saveData(description, username, password);
    		JOptionPane.showMessageDialog(this, "Records has been successfully updated");
    	
    		
    }
    boolean isDescriptionTaken(String description){
		try {
			java.io.File file = new java.io.File("Data.txt");
			if (!file.exists()) {
				return false;
			}
			java.io.BufferedReader reader = new java.io.BufferedReader(new java.io.FileReader(file));
			String line;
			while ((line = reader.readLine()) != null) {
				String[] parts = line.split(",");
				if (parts.length > 0 && parts[0].equals(description)) {
					reader.close();
					return true;
				}
			}
			reader.close();
			return false;
		} catch (IOException e) {
			JOptionPane.showMessageDialog(this, "Error checking description!", "File Error", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		
}
    void saveData(String description, String username, String password) {
		try (FileWriter writer = new FileWriter("Data.txt", true)) {
			writer.write(description + ',' + username + "," + password + "\n");
		} catch (IOException e) {
			JOptionPane.showMessageDialog(this, "Error saving account!", "File Error", JOptionPane.ERROR_MESSAGE);
		}

	}
}
