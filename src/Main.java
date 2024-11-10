import Controller.AtlasWindowController;
import Model.FileNode;
import View.AtlasWindow;

import java.io.File;


public class Main {
    public static void main(String[] args) {
        File[] file = File.listRoots();
        FileNode root = new FileNode(file[0]);
        System.out.println(root);
        AtlasWindow browser = new AtlasWindow();
        new AtlasWindowController(browser, root.getAbsolutePath());
        browser.expandFolder(root.getChildren());

    }
}