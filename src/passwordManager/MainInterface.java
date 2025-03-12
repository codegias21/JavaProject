package passwordManager;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class MainInterface extends JFrame {

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
        
        JTextField descField = new JTextField();
        descField.setBounds(250, 170, 160, 20);
        add(descField);

        JLabel userLabel = new JLabel("Enter the User Name:");
        userLabel.setBounds(50, 220, 120, 20);
        add(userLabel);

        JTextField userField = new JTextField();
        userField.setBounds(250, 220, 160, 20);
        add(userField);

        JLabel passLabel = new JLabel("Enter the Password:");
        passLabel.setBounds(50, 270, 120, 20);
        add(passLabel);

        JPasswordField passField = new JPasswordField();
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
        add(updateButton);

        setVisible(true);
        

      
    }
}
