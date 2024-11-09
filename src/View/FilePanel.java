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

}
