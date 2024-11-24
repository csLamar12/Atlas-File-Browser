package Controller;

import Model.FileNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class FileBrowser {

    public List<FileNode> listFilesOf(FileNode root) {
        List<FileNode> files = new ArrayList<>();

        // Check if the root is null
        if (root == null) {
            System.err.println("Error: The root FileNode is null.");
            return files; // Return an empty list if root is null
        }

        Queue<FileNode> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            // Set working directory
            FileNode currentFile = queue.poll();

            // Check if currentFile is null
            if (currentFile == null) {
                System.err.println("Warning: Encountered a null FileNode in the queue.");
                continue; // Skip to the next iteration
            }

            // Check if children are not null before adding
            List<FileNode> children = currentFile.getChildren();
            if (children != null) {
                files.addAll(children);
                queue.addAll(children); // Add children to the queue for further processing
            } else {
                System.err.println("Warning: No children found for FileNode: " + currentFile);
            }
        }
        return files;
    }
}