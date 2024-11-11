import Controller.AtlasWindowController;
import Model.DriveDetector;
import Model.FileNode;
import View.AtlasWindow;

import java.io.File;
import java.util.List;


public class Main {
    public static void main(String[] args) {
        new AtlasWindowController(new AtlasWindow(), "Volumes");
    }
}