

package src;

import javax.swing.*;
import java.awt.*;

public class TechLibrary extends JFrame {

    private final ResourceTableModel tableModel;
    private final JTable table;
    private final FileManager fileManager;
    private final NetworkManager networkManager;

    public TechLibrary() {
        setTitle("TechLibrary");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        fileManager = new FileManager("src/resources/tech_library.csv");
        networkManager = new NetworkManager();

        tableModel = new ResourceTableModel();
        tableModel.setResources(fileManager.loadResources());

        table = new JTable(tableModel);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel(new BorderLayout());
        add(bottomPanel, BorderLayout.SOUTH);

        JPanel actionPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        bottomPanel.add(actionPanel, BorderLayout.SOUTH);

        JButton addButton = new JButton("Add Resource");
        actionPanel.add(addButton);

        JButton deleteButton = new JButton("Delete Selected");
        actionPanel.add(deleteButton);

        JButton updateButton = new JButton("Update Selected");
        actionPanel.add(updateButton);

        JButton checkUrlButton = new JButton("Check URL");
        actionPanel.add(checkUrlButton);

        addButton.addActionListener(event -> {
            JTextField titleField = new JTextField();
            JTextField urlField = new JTextField();
            JTextField descriptionField = new JTextField();
            JTextField authorField = new JTextField();
            JTextField categoryField = new JTextField();
            JTextField dateAddedField = new JTextField();
            JTextField statusField = new JTextField();
            JTextField ratingField = new JTextField();

            JPanel panel = new JPanel(new GridLayout(0, 1));
            panel.add(new JLabel("Title:"));
            panel.add(titleField);
            panel.add(new JLabel("URL:"));
            panel.add(urlField);
            panel.add(new JLabel("Description:"));
            panel.add(descriptionField);
            panel.add(new JLabel("Author:"));
            panel.add(authorField);
            panel.add(new JLabel("Category:"));
            panel.add(categoryField);
            panel.add(new JLabel("Date Added:"));
            panel.add(dateAddedField);
            panel.add(new JLabel("Status:"));
            panel.add(statusField);
            panel.add(new JLabel("Rating:"));
            panel.add(ratingField);

            int result = JOptionPane.showConfirmDialog(null, panel, "Add Resource",
                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

            if (result == JOptionPane.OK_OPTION) {
                String title = titleField.getText();
                String url = urlField.getText();
                String description = descriptionField.getText();
                String author = authorField.getText();
                String category = categoryField.getText();
                String dateAdded = dateAddedField.getText();
                String status = statusField.getText();
                String rating = ratingField.getText();
                if (!title.isEmpty() && !url.isEmpty()) {
                    tableModel.addResource(new Resource(title, url, description, author, category, dateAdded, status, rating));
                }
            }
        });

        deleteButton.addActionListener(event -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow >= 0) {
                tableModel.removeResource(selectedRow);
            }
        });

        updateButton.addActionListener(event -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow >= 0) {
                Resource resource = tableModel.getResourceAt(selectedRow);

                JTextField titleFieldPopup = new JTextField(resource.title);
                JTextField urlFieldPopup = new JTextField(resource.url);
                JTextField descriptionFieldPopup = new JTextField(resource.description);
                JTextField authorFieldPopup = new JTextField(resource.author);
                JTextField categoryFieldPopup = new JTextField(resource.category);
                JTextField dateAddedFieldPopup = new JTextField(resource.dateAdded);
                JTextField statusFieldPopup = new JTextField(resource.status);
                JTextField ratingFieldPopup = new JTextField(resource.rating);

                JPanel panel = new JPanel(new GridLayout(0, 1));
                panel.add(new JLabel("Title:"));
                panel.add(titleFieldPopup);
                panel.add(new JLabel("URL:"));
                panel.add(urlFieldPopup);
                panel.add(new JLabel("Description:"));
                panel.add(descriptionFieldPopup);
                panel.add(new JLabel("Author:"));
                panel.add(authorFieldPopup);
                panel.add(new JLabel("Category:"));
                panel.add(categoryFieldPopup);
                panel.add(new JLabel("Date Added:"));
                panel.add(dateAddedFieldPopup);
                panel.add(new JLabel("Status:"));
                panel.add(statusFieldPopup);
                panel.add(new JLabel("Rating:"));
                panel.add(ratingFieldPopup);

                int result = JOptionPane.showConfirmDialog(null, panel, "Update Resource",
                        JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

                if (result == JOptionPane.OK_OPTION) {
                    String newTitle = titleFieldPopup.getText();
                    String newUrl = urlFieldPopup.getText();
                    String newDescription = descriptionFieldPopup.getText();
                    String newAuthor = authorFieldPopup.getText();
                    String newCategory = categoryFieldPopup.getText();
                    String newDateAdded = dateAddedFieldPopup.getText();
                    String newStatus = statusFieldPopup.getText();
                    String newRating = ratingFieldPopup.getText();
                    tableModel.updateResource(selectedRow, new Resource(newTitle, newUrl, newDescription, newAuthor, newCategory, newDateAdded, newStatus, newRating));
                }
            }
        });

        checkUrlButton.addActionListener(event -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow >= 0) {
                String url = (String) tableModel.getValueAt(selectedRow, 1);
                networkManager.checkUrl(url);
            }
        });

        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                fileManager.saveResources(tableModel.getResources());
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new TechLibrary().setVisible(true));
    }
}

