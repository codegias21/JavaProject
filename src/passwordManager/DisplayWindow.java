package passwordManager;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.*;
import java.util.*;

public class DisplayWindow extends JFrame {
    private DefaultTableModel model;

    public DisplayWindow() {
        setTitle("Data Display");
        setSize(300, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        //Columns for table
        String[] columns = {"Business", "User Name", "Password"};
        model = new DefaultTableModel(columns, 0);

        ArrayList<String[]> records = readDataFromFile();

        // Sort alphabetically by business 
        Collections.sort(records, Comparator.comparing(row -> row[0].toLowerCase()));
        //Adding rows to table
        for (String[] row : records) {
            model.addRow(row);
        }
        //Adding DefaultTableModel to table and adding Scroll Pane functionality 
        JTable table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        add(scrollPane, BorderLayout.CENTER);
        setVisible(true);
    }
    //Method for reading data from Data.txt
    private ArrayList<String[]> readDataFromFile() {
        ArrayList<String[]> data = new ArrayList<>();
        File file = new File("Data.txt");

        if (!file.exists()) {
        	  JOptionPane.showMessageDialog(this, "No records found!", "File Error", JOptionPane.ERROR_MESSAGE);
            return data;
        }
        //Reading data and outputting to Array List
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",", 3);
                if (parts.length == 3) {
                    data.add(parts);
                }
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error reading data!", "File Error", JOptionPane.ERROR_MESSAGE);
        }

        return data;
    }
}
