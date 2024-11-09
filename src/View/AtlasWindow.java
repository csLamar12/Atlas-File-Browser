package View;

import Model.FileNode;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AtlasWindow extends JFrame {
    private JTable fileTable;
    private DefaultTableModel tableModel;
    private Map<Integer, FileNode> fileNodeMap = new HashMap<>();
    // Header Panel components
    private JPanel headerPanel, previewPanel;
    private JButton backBtn, forwardBtn;
    private JScrollPane scrollPane;
    private JSplitPane splitPane;

    public AtlasWindow() {
        setTitle("Atlas");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);
        setVisible(true);

        // Show drive Panels with available volumes

        // When folders are available for viewing
        initHeader();
        initTable();
        initPreview_Summary();
        initAddressBar();

        revalidate();
        repaint();
    }

    public void initHeader(){
        headerPanel = new JPanel();
        headerPanel.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        headerPanel.setPreferredSize(new Dimension(800, 60));

        backBtn = new JButton("<");
        forwardBtn = new JButton(">");
        backBtn.setFont(new Font("Tahoma", Font.BOLD, 12));
        forwardBtn.setFont(new Font("Tahoma", Font.BOLD, 12));

        c.insets = new Insets(5, 0, 5, 0);
        c.anchor = GridBagConstraints.NORTHWEST;
        c.gridx = 0;
        c.gridy = 0;
        headerPanel.add(backBtn, c);
        c.gridx = 1;
        headerPanel.add(forwardBtn, c);
        add(headerPanel, BorderLayout.NORTH);
    }

    public void initPreview_Summary(){
        previewPanel = new JPanel();
        previewPanel.setPreferredSize(new Dimension(250, 250));
        previewPanel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));
        add(previewPanel, BorderLayout.EAST);

        splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, scrollPane, previewPanel);
        splitPane.setDividerLocation(800); // Initial position of the divider
        splitPane.setResizeWeight(0.5); // Allows both panels to resize proportionally
        splitPane.setOneTouchExpandable(true); // Adds a small arrow to collapse/expand

        // Add split pane to frame
        add(splitPane);
    }

    public void showPreviewPane(){
        splitPane.setDividerLocation(550);
    }

    public void initTable(){
        // Set up the table
        String[] columnNames = {"","Name", "Size", "Type", "Date Modified"};
        tableModel = new DefaultTableModel(columnNames, 0){
            @Override
            public Class<?> getColumnClass(int columnIndex) {
                if(columnIndex == 0) return Icon.class;
                return String.class;
            }
        };
        fileTable = new JTable(tableModel){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        fileTable.setFillsViewportHeight(true);
        fileTable.getTableHeader().setReorderingAllowed(false);

        // Adjust icon column
        TableColumn iconColumn = fileTable.getColumnModel().getColumn(0);
        iconColumn.setPreferredWidth(20);  // Set the column width to fit the icon
        iconColumn.setMaxWidth(20);        // Prevent it from expanding
        iconColumn.setResizable(false);    // Disable resizing by the user

        // Enable sorting of table columns
        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(tableModel);
        fileTable.setRowSorter(sorter);

        // Custom comparator to place "Folder" at the top
        sorter.setComparator(3, (o1, o2) -> {
            String type1 = (String) o1;
            String type2 = (String) o2;

            if (type1.equals("Folder") && !type2.equals("Folder")) {
                return -1; // Folder should come before non-folders
            } else if (!type1.equals("Folder") && type2.equals("Folder")) {
                return 1; // Non-folder should come after folders
            } else {
                return ((String) o1).compareTo((String) o2); // Default comparison for non-folder items
            }
        });

        // Set row selection feature
        fileTable.setRowSelectionAllowed(true);
        fileTable.setColumnSelectionAllowed(false);
        fileTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        // Add the table to a scroll pane and add it to the frame
        scrollPane = new JScrollPane(fileTable);
        add(scrollPane, BorderLayout.CENTER);
    }

    public void initAddressBar(){
        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(800, 20));
        previewPanel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));
        add(panel, BorderLayout.SOUTH);
    }

    public void expandFolder(List<FileNode> fileNodes){
        tableModel.setRowCount(0);
        fileNodeMap.clear();
        int x = 0;

        for(FileNode fileNode : fileNodes){
            Object[] row = {
                    fileNode.getImg(),
                    fileNode.getName(),
                    fileNode.convertSize(),
                    fileNode.getType(),
                    fileNode.getLastModified()
            };
            tableModel.addRow(row);
            fileNodeMap.put(x,fileNode);
            x++;
        }
    }

    public JTable getFileTable() {
        return fileTable;
    }

    public void setFileTable(JTable fileTable) {
        this.fileTable = fileTable;
    }

    public DefaultTableModel getTableModel() {
        return tableModel;
    }

    public void setTableModel(DefaultTableModel tableModel) {
        this.tableModel = tableModel;
    }

    public Map<Integer, FileNode> getFileNodeMap() {
        return fileNodeMap;
    }

    public void setFileNodeMap(Map<Integer, FileNode> fileNodeMap) {
        this.fileNodeMap = fileNodeMap;
    }

    public JPanel getHeaderPanel() {
        return headerPanel;
    }

    public void setHeaderPanel(JPanel headerPanel) {
        this.headerPanel = headerPanel;
    }

    public JButton getBackBtn() {
        return backBtn;
    }

    public void setBackBtn(JButton backBtn) {
        this.backBtn = backBtn;
    }

    public JButton getForwardBtn() {
        return forwardBtn;
    }

    public void setForwardBtn(JButton forwardBtn) {
        this.forwardBtn = forwardBtn;
    }
}