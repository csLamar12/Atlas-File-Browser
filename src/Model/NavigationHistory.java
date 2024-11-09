package Model;

import java.io.File;
import java.util.Stack;

public class NavigationHistory {
    private Stack<String> backHistory = new Stack<>();
    private Stack<String> forwardHistory = new Stack<>();


    public FileNode goBack(String currentDirectory) {
        if (!backHistory.isEmpty()) {
            forwardHistory.push(currentDirectory);
            String prevDirectory = backHistory.pop();
            return new FileNode(new File(prevDirectory));
        }
        return null;
    }

    public FileNode goForward(String currentDirectory) {
        if (!forwardHistory.isEmpty()) {
            backHistory.push(currentDirectory);
            String nextDirectory = forwardHistory.pop();

            return new FileNode(new File(nextDirectory));
        }
        return null;
    }

    public void visitDirectory(String currentDirectory) {
        backHistory.push(currentDirectory);
        forwardHistory.clear();
    }
}
