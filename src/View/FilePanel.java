package View;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.File;
import java.text.SimpleDateFormat;

public class FilePanel extends JFrame {
    private JTable fileTable;
    private DefaultTableModel tableModel;

    public FilePanel(String directoryPath) {
        // Set up the frame
        setTitle("File Browser");
        setSize(600, 400);
        setLocationRelativeTo(null); // Center the frame
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Set up the table
        String[] columnNames = {"Name", "Size", "Type", "Date Modified"};
        tableModel = new DefaultTableModel(columnNames, 0);
        fileTable = new JTable(tableModel);
        fileTable.setFillsViewportHeight(true);

        // Set row selection feature
        fileTable.setRowSelectionAllowed(true);
        fileTable.setColumnSelectionAllowed(false);
        fileTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        // Add the table to a scroll pane and add it to the frame
        JScrollPane scrollPane = new JScrollPane(fileTable);
        add(scrollPane, BorderLayout.CENTER);

        // Load files from the specified directory
        loadFiles(new File(directoryPath));

        setVisible(true);
    }

    // Method to populate the table with files from a directory
    private void loadFiles(File directory) {
        tableModel.setRowCount(0); // Clear existing rows

        File[] files = directory.listFiles();
        if (files != null) {
            SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy HH:mm");
            for (File file : files) {
                String name = file.getName();
                String size = file.isFile() ? file.length() / 1024 + " KB" : "";
                String type = file.isDirectory() ? "Folder" : "File";
                String modifiedDate = sdf.format(file.lastModified());

                tableModel.addRow(new Object[]{name, size, type, modifiedDate});
            }
        }
    }

    // Main method to run the application
    public static void main(String[] args) {
        String directoryPath = "path/to/your/directory"; // Replace with the directory you want to view
        SwingUtilities.invokeLater(() -> new FilePanel(directoryPath));
    }
}
