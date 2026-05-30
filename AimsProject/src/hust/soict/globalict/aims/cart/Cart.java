package hust.soict.globalict.aims.cart;

import hust.soict.globalict.aims.media.Media;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.util.Collections;

public class Cart {
    public static final int MAX_NUMBERS_ORDERED = 20;
    private ObservableList<Media> itemsOrdered = FXCollections.observableArrayList();

    public void addMedia(Media media) {
        if (itemsOrdered.size() < MAX_NUMBERS_ORDERED) {
            if (!itemsOrdered.contains(media)) {
                itemsOrdered.add(media);
                System.out.println("The media has been added to the cart.");
            } else {
                System.out.println("The media is already in the cart.");
            }
        } else {
            System.out.println("The cart is almost full.");
        }
    }

    public void removeMedia(Media media) {
        if (itemsOrdered.remove(media)) {
            System.out.println("The media has been removed from the cart.");
        } else {
            System.out.println("The media was not found in the cart.");
        }
    }
    public float totalCost() {
        float sum = 0;
        for (Media media : itemsOrdered) {
            sum += media.getCost();
        }
        return sum;
    }

    public void print() {
        System.out.println("***********************CART***********************");
        System.out.println("Ordered Items:");
        for (int i = 0; i < itemsOrdered.size(); i++) {
            System.out.println((i + 1) + ". " + itemsOrdered.get(i).toString());
        }
        System.out.println("Total cost: " + totalCost() + " $");
        System.out.println("***************************************************");
    }
    public void searchById(int id) {
        boolean found = false;
        for (Media media : itemsOrdered) {
            if (media.getId() == id) {
                System.out.println("Found: " + media.toString());
                found = true;
                break;
            }
        }
        if (!found) System.out.println("No match found for ID: " + id);
    }
    public void searchByTitle(String title) {
        boolean found = false;
        for (Media media : itemsOrdered) {
            if (media.getTitle().equalsIgnoreCase(title)) {
                System.out.println("Found: " + media.toString());
                found = true;
            }
        }
        if (!found) System.out.println("No match found for title: " + title);
    }
    public void sortByTitleCost() {
        Collections.sort(itemsOrdered, Media.COMPARE_BY_TITLE_COST);
    }
    public void sortByCostTitle() {
        Collections.sort(itemsOrdered, Media.COMPARE_BY_COST_TITLE);
    }

    public ObservableList<Media> getItemsOrdered() {
        return itemsOrdered;
    }
}