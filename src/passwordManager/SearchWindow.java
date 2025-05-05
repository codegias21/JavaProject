package passwordManager;

import javax.swing.*;
import java.io.*;
import java.util.*;

public class SearchWindow extends JFrame {

	JLabel descriptionLabel;
	JLabel usersLabel;
	JLabel passwordLabel;
	JTextField searchField;
	ArrayList<String[]> records;
	int foundIndex = -1;

	public SearchWindow() {
		setTitle("Search Data");
		setSize(600, 400);
		setLocationRelativeTo(null);
		setLayout(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		JLabel searchLabel = new JLabel("Enter the search item:");
		searchLabel.setBounds(150, 50, 200, 20);
		add(searchLabel);

		descriptionLabel = new JLabel("Description:");
		descriptionLabel.setBounds(208, 70, 200, 20);
		add(descriptionLabel);

		usersLabel = new JLabel("User Name:");
		usersLabel.setBounds(208, 90, 200, 20);
		add(usersLabel);

		passwordLabel = new JLabel("Password:");
		passwordLabel.setBounds(214, 110, 200, 20);
		add(passwordLabel);

		searchField = new JTextField();
		searchField.setBounds(300, 50, 200, 20);
		add(searchField);

		JButton deleteButton = new JButton("Delete Entry");
		deleteButton.setBounds(150, 180, 120, 20);
		deleteButton.addActionListener(e -> deleteRecords());
		add(deleteButton);

		JButton searchButton = new JButton("Search");
		searchButton.setBounds(300, 180, 100, 20);
		searchButton.addActionListener(e -> searchRecords());
		add(searchButton);

		JButton doneButton = new JButton("Done");
		doneButton.setBounds(250, 220, 80, 20);
		doneButton.addActionListener(e -> dispose());
		add(doneButton);

		setVisible(true);
	}
	// Method to search for entry
	void searchRecords() {
		String target = searchField.getText().trim();
		if (target.isEmpty()) {
			JOptionPane.showMessageDialog(this, "Please enter a description to search.");
			return;
		}

		records = loadRecords();
		foundIndex = -1;

		for (int i = 0; i < records.size(); i++) {
			String[] record = records.get(i);
			if (record[0].equalsIgnoreCase(target)) {
				descriptionLabel.setText("Description:       " + record[0]);
				usersLabel.setText("User Name:        " + record[1]);
				passwordLabel.setText("Password:        " + record[2]);
				foundIndex = i;
				break;
			}
		}

		if (foundIndex == -1) {
			JOptionPane.showMessageDialog(this, "No matching description found.");
			clearLabels();
		}
	}

	private ArrayList<String[]> loadRecords() {
		ArrayList<String[]> data = new ArrayList<>();
		try (BufferedReader br = new BufferedReader(new FileReader("Data.txt"))) {
			String line;
			while ((line = br.readLine()) != null) {
				try {
					String decrypted = Encryption.decrypt(line);
					String[] parts = decrypted.split(",", 3);
					if (parts.length == 3) {
						data.add(parts);
					}
				} catch (Exception e) {
					System.err.println("Skipping corrupt or invalid line: " + line);
				}
			}
		} catch (IOException e) {
			JOptionPane.showMessageDialog(this, "Error reading records file.");
		}
		return data;
	}
	// Method for deleting record
	void deleteRecords() {
		if (foundIndex >= 0) {
			int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete this entry?",
					"Delete Confirmation", JOptionPane.YES_NO_OPTION);
			if (confirm == JOptionPane.YES_OPTION) {
				records.remove(foundIndex);
				saveRecords(records);
				JOptionPane.showMessageDialog(this, "Entry deleted.");
				clearLabels();
				foundIndex = -1;
			}
		} else {
			JOptionPane.showMessageDialog(this, "Please search and select a record before deleting.");
		}
	}
	// Method for saving records
	private void saveRecords(ArrayList<String[]> data) {
		try (BufferedWriter bw = new BufferedWriter(new FileWriter("Data.txt"))) {
			for (String[] record : data) {
				String joined = String.join(",", record);
				String encrypted = Encryption.encrypt(joined);
				bw.write(encrypted);
				bw.newLine();
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, "Error saving records.");
		}
	}
	// Method to show updated labels after deletion
	private void clearLabels() {
		descriptionLabel.setText("Description:");
		usersLabel.setText("User Name:");
		passwordLabel.setText("Password:");
	}
}
