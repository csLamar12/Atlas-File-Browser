package Controller;

import Model.FileNode;
import Model.NavigationHistory;
import View.AtlasWindow;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class AtlasWindowController {
    private AtlasWindow atlasWindow;
    private NavigationHistory navigationHistory = new NavigationHistory();
    private String fileNodePath, currentDirectory;

    public AtlasWindowController(AtlasWindow atlasWindow, String currentDirectory) {
        this.atlasWindow = atlasWindow;
        this.currentDirectory = currentDirectory;
        bindButtonEvents();
    }

    public void bindButtonEvents(){
        atlasWindow.getFileTable().addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int row = atlasWindow.getFileTable().getSelectedRow();
                FileNode selectedFileNode;
                if(row != -1) {
                    selectedFileNode = atlasWindow.getFileNodeMap().get(row);
                    onSelect(selectedFileNode);
                    if (e.getClickCount() == 2) {
                        onRowDoubleClicked(selectedFileNode);
                        fileNodePath = selectedFileNode.getAbsolutePath();
                    }
                }
            }
        });
        atlasWindow.getBackBtn().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FileNode fileNode = navigationHistory.goBack(currentDirectory);
                if(fileNode == null)
                    return;
                currentDirectory = fileNode.getAbsolutePath();
                atlasWindow.expandFolder(fileNode.getChildren());
            }
        });
        atlasWindow.getForwardBtn().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FileNode fileNode = navigationHistory.goForward(currentDirectory);
                if(fileNode == null)
                    return;
                currentDirectory = fileNode.getAbsolutePath();
                atlasWindow.expandFolder(fileNode.getChildren());
            }
        });
    }


    public void onSelect(FileNode selectedFileNode){
        atlasWindow.showPreviewPane();
        selectedFileNode.getType();
    }

    public void onRowDoubleClicked(FileNode selectedFileNode) {
        if (!selectedFileNode.isFile()) {
            navigationHistory.visitDirectory(currentDirectory);
            currentDirectory = selectedFileNode.getAbsolutePath();
            atlasWindow.expandFolder(selectedFileNode.getChildren());
        } // TODO - else open the file
    }
}
