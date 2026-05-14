package hust.soict.globalict.aims.media;
import java.util.ArrayList;
import java.util.List;

public class Book extends Media {
    private List<String> authors = new ArrayList<>();
    private int contentLength;

    public Book(String title, String category, float cost, int contentLength) {
        setTitle(title);
        setCategory(category);
        setCost(cost);
        this.contentLength = contentLength;
    }

    public void addAuthor(String authorName) {
        if (!authors.contains(authorName)) {
            authors.add(authorName);
        }
    }
    
    public void removeAuthor(String authorName) {
        authors.remove(authorName);
    }
    
    @Override
    public String toString() {
        return "Book - " + getTitle() + " - " + getCategory() + " - Authors: " + authors + " - " + contentLength + " pages: " + getCost() + " $";
    }
}