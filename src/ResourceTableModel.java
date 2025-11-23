package src;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class ResourceTableModel extends AbstractTableModel {
    private final List<Resource> resources = new ArrayList<>();
    private final String[] columnNames = {"Title", "URL", "Description", "Author", "Category", "Date Added", "Status", "Rating"};

    public ResourceTableModel() {
    }

    @Override
    public int getRowCount() {
        return resources.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Resource resource = resources.get(rowIndex);
        return switch (columnIndex) {
            case 0 -> resource.title;
            case 1 -> resource.url;
            case 2 -> resource.description;
            case 3 -> resource.author;
            case 4 -> resource.category;
            case 5 -> resource.dateAdded;
            case 6 -> resource.status;
            case 7 -> resource.rating;
            default -> null;
        };
    }

    public void addResource(Resource resource) {
        resources.add(resource);
        fireTableRowsInserted(resources.size() - 1, resources.size() - 1);
    }

    public void removeResource(int rowIndex) {
        resources.remove(rowIndex);
        fireTableRowsDeleted(rowIndex, rowIndex);
    }

    public List<Resource> getResources() {
        return new ArrayList<>(resources);
    }

    public void setResources(List<Resource> resources) {
        this.resources.clear();
        this.resources.addAll(resources);
        fireTableDataChanged();
    }

    public Resource getResourceAt(int rowIndex) {
        return resources.get(rowIndex);
    }

    public void updateResource(int rowIndex, Resource resource) {
        resources.set(rowIndex, resource);
        fireTableRowsUpdated(rowIndex, rowIndex);
    }
}
