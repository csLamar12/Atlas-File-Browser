package Controller;

import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javax.swing.*;
import java.awt.*;
import java.io.File;

public class VideoBasedPreview extends JPanel {

    private JFXPanel jfxPanel;
    private MediaPlayer mediaPlayer;

    public VideoBasedPreview(String videoPath) {
        setLayout(new BorderLayout());

        // Initialize the JFXPanel
        jfxPanel = new JFXPanel();
        add(jfxPanel, BorderLayout.CENTER);

        // Run JavaFX initialization on the JavaFX Application Thread
        Platform.runLater(() -> initFX(videoPath));
    }

    private void initFX(String videoPath) {
        try {
            // Create a Media object for the video file
            File videoFile = new File(videoPath);
            if (!videoFile.exists()) {
                throw new IllegalArgumentException("Video file does not exist: " + videoPath);
            }
            Media media = new Media(videoFile.toURI().toString());

            // Create a MediaPlayer object and attach it to a MediaView
            mediaPlayer = new MediaPlayer(media);
            MediaView mediaView = new MediaView(mediaPlayer);

            // Scale the video to fit the panel
            mediaView.setFitWidth(400);  // Set width based on your panel size
            mediaView.setFitHeight(300); // Set height based on your panel size

            // Add MediaView to a Scene and set the Scene on the JFXPanel
            Scene scene = new Scene(new javafx.scene.Group(mediaView));
            jfxPanel.setScene(scene);

            // Play the video
            mediaPlayer.play();

            // Add error handling for media player events
            mediaPlayer.setOnError(() -> {
                System.err.println("Error occurred: " + mediaPlayer.getError().getMessage());
            });

        } catch (Exception e) {
            // Handle exceptions and provide feedback
            JOptionPane.showMessageDialog(this, "Error initializing video: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    // Optional: Control to stop video playback when needed
    public void stopVideo() {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
        }
    }
}