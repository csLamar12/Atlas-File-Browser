import Model.DriveDetector;
import Model.FileNode;
import Model.FileSignatureReader;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class Main2 {
    public static void main(String[] args) {
//        File file = new File("/Users/lamar/Movies/TV/Media.localized/Movies/Naruto Shippuden Episode 035 An Unnecessary Addition.mkv");
//        FileSignatureReader fsr = new FileSignatureReader(file);
//        try {
//            System.out.println(fsr.readFileSignature());
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }

//        List<FileNode> drives = new ArrayList<>();
//        File[] roots = File.listRoots(); // Get the root drives on the system
//
//        for (File root : roots) {
//            System.out.println(root.getAbsolutePath());
//            if (root.canRead()) {
//                FileNode driveNode = new FileNode(root);
//                drives.add(driveNode);
//            }
//        }

        DriveDetector driveDetector = new DriveDetector();

    }
}