package passwordManager;

import javax.swing.*;

public class SearchWindow extends JFrame{
	public SearchWindow(){
	  setTitle("Search Data");
      setSize(600, 400);
      setLocationRelativeTo(null);
      setLayout(null);
      setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
      
      JLabel searchLabel = new JLabel("Enter the search item:");
      searchLabel.setBounds(150, 50, 200, 20);
      add(searchLabel);
     
      JLabel descriptionLabel = new JLabel("Description:");
      descriptionLabel.setBounds(208, 70, 200, 20);
      add(descriptionLabel);
     
      JLabel usersLabel = new JLabel("User Name:");
      usersLabel.setBounds(208, 90, 200, 20);
      add(usersLabel);
      
      JLabel passwordLabel = new JLabel("Password:");
      passwordLabel.setBounds(214, 110, 200, 20);
      add(passwordLabel);
      
      JTextField searchField = new JTextField();
      searchField.setBounds(300, 50, 200, 20);
      add(searchField);
      
      JButton deleteButton = new JButton("Delete Entry");
      deleteButton.setBounds(150, 180, 120, 20);
      add(deleteButton);

      JButton searchButton = new JButton("Search");
      searchButton.setBounds(300, 180, 100, 20);
      add(searchButton);
      

      JButton doneButton = new JButton("Done");
      doneButton.setBounds(250, 220, 80, 20);
      add(doneButton);
      
      setVisible(true);
	}
}
