package Model;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FileNode {
    private String name;
    private boolean isFile;
    private Map<String, Object> metadata = new HashMap<>();
    private List<FileNode> children = new ArrayList<>();
    private File file;


    public FileNode(String name, boolean isFile) {
        this.name = name;
        this.isFile = isFile;
    }

    public FileNode(File file) {
        this.name = file.getName();
        this.isFile = file.isFile();
        this.file = file;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isFile() {
        return isFile;
    }

    public void setFile(boolean file) {
        isFile = file;
    }

    public Map<String, Object> getMetadata() {
        return metadata;
    }
    public Object getMetadata(String key) {
        return metadata.get(key);
    }

    public void setMetadata(Map<String, Object> metadata) {
        this.metadata = metadata;
    }

    public void setMetadata(String key, Object value) {
        metadata.put(key, value);
    }

    public List<FileNode> getChildren() {
        return children;
    }

    public File[] getChildrenFiles() {
        return file.listFiles();
    }

    public void setChildren(List<FileNode> children) {
        this.children = children;
    }

    public void addChild(FileNode child) {
        if (!isFile) children.add(child);
    }
}
