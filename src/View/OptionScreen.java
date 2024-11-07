package View;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.ButtonGroup;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JFileChooser;
import java.io.File;

public class OptionScreen extends JFrame implements ActionListener{
    private JButton btnHd;
    private JButton btnFolder;
    private JButton btnSsd;
    private JButton btnNetshare;
    private JButton btnCloud;

    private JPanel pan1, pan2, pan3, pan4, pan5;

    JFileChooser fileChooser = new JFileChooser();
    int userSelection;

    public OptionScreen(){
        super("Resource Options");//JFrame window title
        this.initializeComponents();
        this.setLayout();
        this.addComponentsToPanels();
        this.registerListeners();
        this.addComponentsToWindow();
        this.setWindowProperties();
    }

    public void initializeComponents(){
        this.btnHd = new JButton("Hard Drive");
        this.btnFolder = new JButton("Folder");
        this.btnSsd = new JButton("SSD Drive");
        this.btnNetshare = new JButton("Network Share");
        this.btnCloud = new JButton("Cloud Storage");

        this.pan1 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        this.pan2 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        this.pan3 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        this.pan4 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        this.pan5 = new JPanel(new FlowLayout(FlowLayout.CENTER));
    }

    public void setLayout(){
        this.setLayout(new GridLayout(3,2));
    }

    public void addComponentsToPanels(){
        pan1.add(btnHd); pan2.add(btnFolder);
        pan3.add(btnSsd); pan4.add(btnNetshare);
                pan5.add(btnCloud);
    }

    public void registerListeners(){
        this.btnHd.addActionListener(this);
        this.btnFolder.addActionListener(this);
        this.btnSsd.addActionListener(this);
        this.btnNetshare.addActionListener(this);
        this.btnCloud.addActionListener(this);
    }

    public void addComponentsToWindow(){
        this.add(pan1); this.add(pan2);
        this.add(pan3); this.add(pan4);
                this.add(pan5);
    }

    public void setWindowProperties(){
        this.setSize(500, 500);
        this.setResizable(true);
        this.setVisible(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public void actionPerformed(ActionEvent ae){
        if(ae.getSource().equals(btnHd)){
            fileChooser.setDialogTitle("Hard Drive");
            fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);

            userSelection = fileChooser.showOpenDialog(this);
            if (userSelection == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fileChooser.getSelectedFile();
            }
        }
        else if(ae.getSource().equals(btnFolder)){
            fileChooser.setDialogTitle("Folder");

            userSelection = fileChooser.showOpenDialog(null);
        }
        else if(ae.getSource().equals(btnSsd)){
            fileChooser.setDialogTitle("SSD Drive");

        }
        else if(ae.getSource().equals(btnNetshare)){
            fileChooser.setDialogTitle("Network Share");

        }
        else if(ae.getSource().equals(btnCloud)){
            fileChooser.setDialogTitle("Cloud Storage");

        }
    }

    public static void main(String[] args)
    {
        new OptionScreen();
    }
}
