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
                if(e.getClickCount() == 2){
                    int row = atlasWindow.getFileTable().getSelectedRow();
                    if(row != -1){
                        FileNode selectedFileNode = atlasWindow.getFileNodeMap().get(row);
                        if(selectedFileNode != null){
                            onRowDoubleClicked(selectedFileNode);
                            fileNodePath = selectedFileNode.getAbsolutePath();
                        }
                    }
                }
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                atlasWindow.showPreviewPane();
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



    public void onRowDoubleClicked(FileNode selectedFileNode) {
        if (!selectedFileNode.isFile()) {
            navigationHistory.visitDirectory(currentDirectory);
            currentDirectory = selectedFileNode.getAbsolutePath();
            atlasWindow.expandFolder(selectedFileNode.getChildren());
        }
    }
}
