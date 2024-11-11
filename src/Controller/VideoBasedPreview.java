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
        // Create a Media object for the video file
        File videoFile = new File(videoPath);
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
    }

    // Optional: Control to stop video playback when needed
    public void stopVideo() {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
        }
    }

}
