import Controller.AtlasWindowController;
import Model.FileNode;
import View.AtlasWindow;

import java.io.File;


public class Main {
    public static void main(String[] args) {
        File file = new File("/Users/lamar");
        FileNode root = new FileNode(file);
        AtlasWindow browser = new AtlasWindow();
        new AtlasWindowController(browser, file.getAbsolutePath());
        browser.expandFolder(root.getChildren());
    }
}