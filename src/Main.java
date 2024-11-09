import Controller.FileBrowser;
import Model.FileNode;

import java.io.File;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
//        FileBrowser fileBrowser = new FileBrowser();
        File root = new File("/");
        File[] files = root.listFiles();
        for (File file : files) {
//            System.out.println(file.getName());
            File[] files2 = file.listFiles();
            if (files2 != null) {
                for (File file2 : files2) {
                    System.out.println(file.getName() + "/" + file2.getName());
                }
            }
        }
//        FileNode rootNode = new FileNode(root);
//        fileBrowser.listFilesOf(rootNode);
    }
}