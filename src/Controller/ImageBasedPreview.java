package Controller;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class ImageBasedPreview {
    private String filePath;

    public ImageBasedPreview(String filePath) {
        this.filePath = filePath;
    }

    public Image showPreview() {
        Image image = null;
        try {
            // Check if the file exists
            File imageFile = new File(filePath);
            if (!imageFile.exists()) {
                throw new IOException("File not found: " + filePath);
            }

            // Load the image
            ImageIcon imageIcon = new ImageIcon(imageFile.getAbsolutePath());
            image = imageIcon.getImage();

            // Scale the image
            image = image.getScaledInstance(220, 250, Image.SCALE_SMOOTH);
        } catch (IOException e) {
            // Handle exceptions and provide feedback
            JOptionPane.showMessageDialog(null, "Error loading image: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        } catch (Exception e) {
            // Catch any other unexpected exceptions
            JOptionPane.showMessageDialog(null, "An unexpected error occurred: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
        return image;
    }
}