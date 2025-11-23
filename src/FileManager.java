package src;

import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileManager {
    private final String filename;

    public FileManager(String filename) {
        this.filename = filename;
    }

    public List<Resource> loadResources() {
        List<Resource> resources = new ArrayList<>();
        File file = new File(filename);
        if (!file.exists()) {
            return resources;
        }
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",", -1);
                if (parts.length == 8) {
                    resources.add(new Resource(parts[0], parts[1], parts[2], parts[3], parts[4], parts[5], parts[6], parts[7]));
                }
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error loading resources: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        return resources;
    }

    public void saveResources(List<Resource> resources) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (Resource resource : resources) {
                writer.write(String.format("%s,%s,%s,%s,%s,%s,%s,%s\n", resource.title, resource.url, resource.description, resource.author, resource.category, resource.dateAdded, resource.status, resource.rating));
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error saving resources: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
