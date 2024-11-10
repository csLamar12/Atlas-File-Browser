package Model;


import java.io.File;

public class DriveDetector {

    public DriveDetector(){
        File[] files = File.listRoots();
        for (File file : files) {
            FileNode fileNode = new FileNode(file);
            System.out.println(fileNode);
        }
    }
}