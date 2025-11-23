package src;

public class Resource {
    public String title;
    public String url;
    public String description;
    public String author;
    public String category;
    public String dateAdded;
    public String status;
    public String rating;

    public Resource(String title, String url, String description, String author, String category, String dateAdded, String status, String rating) {
        this.title = title;
        this.url = url;
        this.description = description;
        this.author = author;
        this.category = category;
        this.dateAdded = dateAdded;
        this.status = status;
        this.rating = rating;
    }
}
