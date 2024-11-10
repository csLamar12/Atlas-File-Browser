package Model;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

public class FileNode {
    private ImageIcon img;
    private String name, type = null, lastModified;
    private long size;
    private SimpleDateFormat dateFormat;
    private boolean isFile;
    private Map<String, Object> metadata = new HashMap<>();
    private List<FileNode> children = new ArrayList<>();
    private File file;


    public FileNode(File file) {
        this.file = file;
        this.name = file.getName();
        this.isFile = file.isFile();
        this.size = file.length();
        this.type = getType();
        this.lastModified = getLastModified();
    }

   // Todo - Add Summary
    public void setMetadata(){
        this.metadata.put("Name", this.name);
        this.metadata.put("Type", this.type);
        this.metadata.put("Size", this.convertSize());
        this.metadata.put("LastModified", this.lastModified);
    }

    public String convertSize(){
        this.size = this.file.length();
        if (this.size >= 1024 && this.size < Math.pow(1024, 2)) {
            this.size = this.size / 1024;
            return size + "KBs";
        } else if (this.size >= Math.pow(1024, 2) && this.size < Math.pow(1024, 3)) {
            this.size = (this.size / 1024) / 1024;
            return size + "MBs";
        } else if (this.size >= Math.pow(1024, 3)) {
            this.size = ((this.size / 1024) / 1024)/1024;
            return size + "GBs";
        }
        return size + "Bytes";
    }

    public String getLastModified(){
        setLastModified();
        return this.lastModified;
    }
    public void setLastModified(){
        this.dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        this.lastModified = this.dateFormat.format(this.file.lastModified());
    }

    public String getType() {
        if (type == null) detectFileType();

        return type;
    }

    public void detectFileType(){
        // Todo - use AI to read the MIME File Type and provide String of exact type
        if (!this.isFile){
            img = new ImageIcon("src/Resources/folder.png");
            this.type = "Folder";
            return;
        }
        try{
            img = new ImageIcon("src/Resources/file.png");
            FileSignatureReader fsr = new FileSignatureReader(this.file);
            this.type = fsr.readFileSignature();
        } catch (IOException e){
            e.printStackTrace();
            this.type = "File";
        }
    }

    public List<FileNode> getChildren() {
        File[] files = file.listFiles();
        children.clear();
        if (files != null) {
            for (File f : files) {
                if (f.getName().startsWith("."))
                    continue;
                children.add(new FileNode(f));
            }
        }
        return children;
    }

    public String getAbsolutePath(){
        return file.getAbsolutePath();
    }

    public boolean isFile() {
        return isFile;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setLastModified(String lastModified) {
        this.lastModified = lastModified;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public SimpleDateFormat getDateFormat() {
        return dateFormat;
    }

    public void setDateFormat(SimpleDateFormat dateFormat) {
        this.dateFormat = dateFormat;
    }

    public void setFile(boolean file) {
        isFile = file;
    }

    public Map<String, Object> getMetadata() {
        return metadata;
    }

    public void setMetadata(Map<String, Object> metadata) {
        this.metadata = metadata;
    }

    public void setChildren(List<FileNode> children) {
        this.children = children;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public ImageIcon getImg() {
        return img;
    }

    public void setImg(ImageIcon img) {
        this.img = img;
    }

    @Override
    public String toString() {
        return this.name + " " + this.type + " " + convertSize() + " " + this.lastModified;
    }
}
