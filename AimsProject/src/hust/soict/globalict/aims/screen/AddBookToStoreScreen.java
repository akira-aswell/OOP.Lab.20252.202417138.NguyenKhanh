package hust.soict.globalict.aims.screen;

import hust.soict.globalict.aims.media.Book;
import hust.soict.globalict.aims.store.Store;
import javax.swing.*;
import java.awt.*;

public class AddBookToStoreScreen extends AddItemToStoreScreen {
    private JTextField tfContentLength, tfAuthors;

    public AddBookToStoreScreen(Store store) {
        super(store, "Add Book to Store");
    }

    @Override
    protected void addSpecificFields() {
        setLayout(new GridLayout(7, 2, 5, 5));

        add(new JLabel(" Content Length (pages): "));
        tfContentLength = new JTextField();
        add(tfContentLength);

        add(new JLabel(" Authors (comma-separated): "));
        tfAuthors = new JTextField();
        add(tfAuthors);
    }

    @Override
    protected void addItem() {
        try {
            String title = tfTitle.getText();
            String category = tfCategory.getText();
            float cost = Float.parseFloat(tfCost.getText());
            int contentLength = Integer.parseInt(tfContentLength.getText());
            String authorsStr = tfAuthors.getText();

            Book book = new Book(title, category, cost, contentLength);
            
            if (!authorsStr.trim().isEmpty()) {
                String[] authors = authorsStr.split(",");
                for (String author : authors) {
                    book.addAuthor(author.trim()); 
                }
            }

            store.addMedia(book);
            JOptionPane.showMessageDialog(null, "Book Added Successfully!");
            this.dispose();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Invalid Cost or Content Length format!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}