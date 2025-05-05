package passwordManager;

import java.io.BufferedReader;
import java.io.FileReader;
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
        
        ImageIcon icon = new ImageIcon("C:/Users/manas/Downloads/download1.jpg");
		JLabel imageLabel = new JLabel(icon);
		imageLabel.setBounds(100, 35, 225, 100); // Adjust position and size
		add(imageLabel);

        JLabel descLabel = new JLabel("Enter the Description/Company:");
        descLabel.setBounds(50, 170, 200, 20);
        add(descLabel);
        
        descField = new JTextField();
        descField.setBounds(250, 170, 160, 20);
        add(descField);

        JLabel userLabel = new JLabel("Enter the User Name:");
        userLabel.setBounds(50, 220, 150, 20);
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
        displayButton.addActionListener(e -> displayRecords());
        add(displayButton);

        JButton searchButton = new JButton("Search");
        searchButton.setBounds(270, 360, 100, 20);
        searchButton.addActionListener(e -> searchRecords());
        add(searchButton);
        

        JButton updateButton = new JButton("Update");
        updateButton.setBounds(200, 400, 100, 20);
        updateButton.addActionListener(e -> updateRecords());
        add(updateButton);

        setVisible(true);
       
    }
    // Method to update records
    void updateRecords(){
    	String description = descField.getText();
    	String username = userField.getText();
    	String password = passField.getText();
    	// Check for empty description, password and user name fields
    	if(description.isEmpty() || password.isEmpty() || username.isEmpty()) {
			JOptionPane.showMessageDialog(this, "Description, username or password fields can not be empty! ", "Error",
					JOptionPane.ERROR_MESSAGE);
			return;
    	}
    	//Check for description duplication
    	if(isDescriptionTaken(description)) {
    		JOptionPane.showMessageDialog(this,"You can not have duplicate descriptions.",
					"Duplicate Description", JOptionPane.ERROR_MESSAGE);
    		return;
    	}//Save data and display success prompt
    	saveData(description, username, password);
    		JOptionPane.showMessageDialog(this, "The data sets for " + description + " has been added.", "Update Success", JOptionPane.INFORMATION_MESSAGE );
    	
    		
    }
    boolean isDescriptionTaken(String description) {
        try (BufferedReader reader = new BufferedReader(new FileReader("Data.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                try {
                    String decrypted = Encryption.decrypt(line);
                    String[] parts = decrypted.split(",", 3);
                    if (parts.length > 0 && parts[0].equalsIgnoreCase(description)) {
                        return true;
                    }
                } catch (Exception e) {
                    // Skip invalid or corrupt lines
                    System.err.println("Skipping invalid or corrupt line: " + line);
                }
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error checking description!", "File Error", JOptionPane.ERROR_MESSAGE);
        }
        return false;
    
		
}	//Method to save data when Update button is clicked
    void saveData(String description, String username, String password) {
        try (FileWriter writer = new FileWriter("Data.txt", true)) {
            String plainText = description + ',' + username + ',' + password;
            String encrypted = Encryption.encrypt(plainText);
            writer.write(encrypted);
            writer.write(System.lineSeparator()); // More platform-independent than "\n"
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error saving encrypted account: " + e.getMessage(),
                    "File Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace(); // Helpful for debugging during development
        }
    }
//Method to display records when Display All is clicked
void displayRecords() {
	new DisplayWindow();
}

void searchRecords() {
	new SearchWindow();
}


}


