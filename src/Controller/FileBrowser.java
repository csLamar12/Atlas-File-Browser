package Controller;

import Model.FileNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class FileBrowser {

    public List<FileNode> listFilesOf(FileNode root) {
        List<FileNode> files = new ArrayList<>();

        Queue<FileNode> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            FileNode currentFile = queue.poll();

            // Return a list of FileNode
            for (FileNode child : currentFile.getChildren()) {
                if (child.isFile()){
                    // get metadata info
                    // set the metadata
                } else {
                    // get metadata info
                    // set metadata
                }
                files.add(child);
            }
        }
        return files;
    }
}
