package hust.soict.globalict.aims;

import hust.soict.globalict.aims.cart.Cart;
import hust.soict.globalict.aims.media.*;
import hust.soict.globalict.aims.store.Store;
import java.util.Scanner;

public class Aims {
    private static Store store = new Store();
    private static Cart cart = new Cart();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        DigitalVideoDisc dvd1 = new DigitalVideoDisc("The Lion King", "Animation", "Roger Allers", 87, 19.95f);
        store.addMedia(dvd1);
        DigitalVideoDisc dvd2 = new DigitalVideoDisc("Star Wars", "Science Fiction", "George Lucas", 124, 24.95f);
        store.addMedia(dvd2);
        Book book1 = new Book("Java Programming", "Education", 29.99f, 500);
        book1.addAuthor("James Gosling");
        store.addMedia(book1);

        int choice;
        do {
            showMenu();
            choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1: viewStore(); break;
                case 2: updateStore(); break;
                case 3: viewCart(); break;
                case 0: System.out.println("Exiting AIMS. Goodbye!"); break;
                default: System.out.println("Invalid choice. Please try again."); break;
            }
        } while (choice != 0);
    }

    public static void showMenu() {
        System.out.println("AIMS: ");
        System.out.println("--------------------------------");
        System.out.println("1. View store");
        System.out.println("2. Update store");
        System.out.println("3. See current cart");
        System.out.println("0. Exit");
        System.out.println("--------------------------------");
        System.out.print("Please choose a number: 0-1-2-3: ");
    }

    public static void viewStore() {
        store.print();
        int choice;
        do {
            storeMenu();
            choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1: seeMediaDetails(); break;
                case 2: addMediaToCart(); break;
                case 3: playMedia(); break;
                case 4: viewCart(); break;
                case 0: break;
                default: System.out.println("Invalid choice."); break;
            }
        } while (choice != 0);
    }

    public static void storeMenu() {
        System.out.println("Options: ");
        System.out.println("--------------------------------");
        System.out.println("1. See a media's details");
        System.out.println("2. Add a media to cart");
        System.out.println("3. Play a media");
        System.out.println("4. See current cart");
        System.out.println("0. Back");
        System.out.println("--------------------------------");
        System.out.print("Please choose a number: 0-1-2-3-4: ");
    }

    public static void seeMediaDetails() {
        System.out.print("Enter media title: ");
        String title = scanner.nextLine();
        Media media = store.searchByTitle(title);
        if (media != null) {
            System.out.println(media.toString());
            mediaDetailsMenu(media);
        } else {
            System.out.println("Media not found in store.");
        }
    }

    public static void mediaDetailsMenu(Media media) {
        int choice;
        do {
            System.out.println("Options: ");
            System.out.println("--------------------------------");
            System.out.println("1. Add to cart");
            if (media instanceof Playable) System.out.println("2. Play"); // Chỉ CD/DVD mới có nút Play
            System.out.println("0. Back");
            System.out.println("--------------------------------");
            System.out.print("Please choose a number: 0-1-2: ");
            choice = scanner.nextInt();
            scanner.nextLine();
            if (choice == 1) {
                cart.addMedia(media);
                System.out.println("Added to cart. Items in cart: " + cart.getItemsOrdered().size());
            } else if (choice == 2 && media instanceof Playable) {
                ((Playable) media).play();
            }
        } while (choice != 0);
    }

    public static void addMediaToCart() {
        System.out.print("Enter title: ");
        String title = scanner.nextLine();
        Media media = store.searchByTitle(title);
        if (media != null) {
            cart.addMedia(media);
            System.out.println("Current cart has " + cart.getItemsOrdered().size() + " items.");
        } else {
            System.out.println("Media not found.");
        }
    }

    public static void playMedia() {
        System.out.print("Enter title: ");
        String title = scanner.nextLine();
        Media media = store.searchByTitle(title);
        if (media != null && media instanceof Playable) {
            ((Playable) media).play();
        } else {
            System.out.println("Media is either not in store or not playable.");
        }
    }

    public static void updateStore() {
        int choice;
        do {
            System.out.println("Update Store Options: ");
            System.out.println("--------------------------------");
            System.out.println("1. Add a media");
            System.out.println("2. Remove a media");
            System.out.println("0. Back");
            System.out.println("--------------------------------");
            System.out.print("Please choose a number: 0-1-2: ");
            choice = scanner.nextInt();
            scanner.nextLine();
            if (choice == 1) addMediaToStore();
            else if (choice == 2) removeMediaFromStore();
        } while (choice != 0);
    }

    private static void addMediaToStore() {
        System.out.println("Type: 1. Book, 2. CD, 3. DVD");
        int type = scanner.nextInt(); scanner.nextLine();
        System.out.print("Title: "); String title = scanner.nextLine();
        System.out.print("Category: "); String category = scanner.nextLine();
        System.out.print("Cost: "); float cost = scanner.nextFloat(); scanner.nextLine();

        if (type == 1) {
            System.out.print("Content length: "); int len = scanner.nextInt(); scanner.nextLine();
            Book book = new Book(title, category, cost, len);
            System.out.print("Authors (comma separated): "); String authors = scanner.nextLine();
            for (String a : authors.split(",")) book.addAuthor(a.trim());
            store.addMedia(book);
        } else if (type == 2) {
            System.out.print("Artist: "); String artist = scanner.nextLine();
            CompactDisc cd = new CompactDisc(title, category, cost, 0, "Director Name", artist);
            store.addMedia(cd);
        } else if (type == 3) {
            store.addMedia(new DigitalVideoDisc(title, category, cost));
        }
        System.out.println("Added to store.");
    }

    private static void removeMediaFromStore() {
        System.out.print("Enter title: ");
        String title = scanner.nextLine();
        Media m = store.searchByTitle(title);
        if (m != null) { store.removeMedia(m); System.out.println("Removed."); }
        else System.out.println("Not found.");
    }

    public static void viewCart() {
        cart.print();
        int choice;
        do {
            cartMenu();
            choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1: filterCart(); break;
                case 2: sortCart(); break;
                case 3: removeMediaFromCart(); break;
                case 4: playMediaFromCart(); break;
                case 5: placeOrder(); break;
                case 0: break;
            }
        } while (choice != 0);
    }

    public static void cartMenu() {
        System.out.println("Options: ");
        System.out.println("--------------------------------");
        System.out.println("1. Filter medias in cart");
        System.out.println("2. Sort medias in cart");
        System.out.println("3. Remove media from cart");
        System.out.println("4. Play a media");
        System.out.println("5. Place order");
        System.out.println("0. Back");
        System.out.println("--------------------------------");
        System.out.print("Please choose a number: 0-1-2-3-4-5: ");
    }

    private static void filterCart() {
        System.out.println("1. By ID, 2. By Title");
        int opt = scanner.nextInt(); scanner.nextLine();
        if (opt == 1) { System.out.print("ID: "); cart.searchById(scanner.nextInt()); scanner.nextLine(); }
        else { System.out.print("Title: "); cart.searchByTitle(scanner.nextLine()); }
    }

    private static void sortCart() {
        System.out.println("1. By Title, 2. By Cost");
        if (scanner.nextInt() == 1) cart.sortByTitleCost(); else cart.sortByCostTitle();
        scanner.nextLine(); cart.print();
    }

    private static void removeMediaFromCart() {
        System.out.print("Title: "); String t = scanner.nextLine();
        for (Media m : cart.getItemsOrdered()) {
            if (m.getTitle().equalsIgnoreCase(t)) { cart.removeMedia(m); break; }
        }
    }

    private static void playMediaFromCart() {
        System.out.print("Title: "); String t = scanner.nextLine();
        for (Media m : cart.getItemsOrdered()) {
            if (m.getTitle().equalsIgnoreCase(t) && m instanceof Playable) { 
                ((Playable) m).play(); return; 
            }
        }
        System.out.println("Cannot play.");
    }

    private static void placeOrder() {
        System.out.println("An order is created!");
        cart.getItemsOrdered().clear();
    }
}