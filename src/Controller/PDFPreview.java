package Controller;

import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.PDFRenderer;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * PDF Preview Class
 */
public class PDFPreview {
    private String filePath;

    public PDFPreview(String filePath) {
        this.filePath = filePath;
    }

    public void showPreview() {
        JFrame frame = new JFrame("PDF Preview - First Page");
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        try {
            // Load the PDF document and render only the first page
            File pdfFile = new File(filePath);
            if (!pdfFile.exists()) {
                throw new IOException("File not found: " + filePath);
            }

            PDDocument document = Loader.loadPDF(pdfFile);
            PDFRenderer pdfRenderer = new PDFRenderer(document);
            BufferedImage firstPageImage = pdfRenderer.renderImage(0); // Render first page at 100 DPI
            document.close();

            // Add the rendered image to a JLabel and display it
            JLabel label = new JLabel(new ImageIcon(firstPageImage));
            JScrollPane scrollPane = new JScrollPane(label); // Make it scrollable in case the image is large

            frame.add(scrollPane);
            frame.setVisible(true);
        } catch (IOException e) {
            // Handle exceptions and provide feedback
            JOptionPane.showMessageDialog(frame, "Error loading PDF: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        } catch (Exception e) {
            // Catch any other unexpected exceptions
            JOptionPane.showMessageDialog(frame, "An unexpected error occurred: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new PDFPreview("/Users/lamar/Downloads/MPHil Computer Science.pdf").showPreview();
        });
    }
}