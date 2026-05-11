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
        int choice;
        do {
            showMenu();
            choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1: viewStore(); break;
                case 2: updateStore(); break;
                case 3: viewCart(); break;
                case 0: break;
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
        Media media = null;
        for (Media m : store.getItemsInStore()) {
            if (m.getTitle().equalsIgnoreCase(title)) {
                media = m;
                break;
            }
        }
        if (media != null) {
            System.out.println(media.toString());
            mediaDetailsMenu(media);
        } else {
            System.out.println("Media not found.");
        }
    }

    public static void mediaDetailsMenu(Media media) {
        int choice;
        do {
            System.out.println("Options: ");
            System.out.println("--------------------------------");
            System.out.println("1. Add to cart");
            if (media instanceof Playable) System.out.println("2. Play");
            System.out.println("0. Back");
            System.out.println("--------------------------------");
            System.out.print("Please choose a number: 0-1-2: ");
            choice = scanner.nextInt();
            scanner.nextLine();
            if (choice == 1) cart.addMedia(media);
            else if (choice == 2 && media instanceof Playable) ((Playable) media).play();
        } while (choice != 0);
    }

    public static void addMediaToCart() {
        System.out.print("Enter title to add: ");
        String title = scanner.nextLine();
        for (Media m : store.getItemsInStore()) {
            if (m.getTitle().equalsIgnoreCase(title)) {
                cart.addMedia(m);
                return;
            }
        }
        System.out.println("Not found.");
    }

    public static void playMedia() {
        System.out.print("Enter title to play: ");
        String title = scanner.nextLine();
        for (Media m : store.getItemsInStore()) {
            if (m.getTitle().equalsIgnoreCase(title) && m instanceof Playable) {
                ((Playable) m).play();
                return;
            }
        }
        System.out.println("Cannot play this media.");
    }

    public static void updateStore() {
        System.out.println("1. Add Media\n2. Remove Media");
        int c = scanner.nextInt(); scanner.nextLine();
        if (c == 1) {
            System.out.print("Enter title: ");
            String t = scanner.nextLine();
            store.addMedia(new DigitalVideoDisc(t)); // Demo adding a DVD
        }
    }

    public static void viewCart() {
        cart.print();
        int choice;
        do {
            cartMenu();
            choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1: /* Filter logic */ break;
                case 2: 
                    System.out.println("1. By Title\n2. By Cost");
                    int s = scanner.nextInt();
                    if (s == 1) cart.sortByTitleCost(); else cart.sortByCostTitle();
                    cart.print();
                    break;
                case 3: 
                    System.out.print("Title to remove: ");
                    String t = scanner.nextLine();
                    for (Media m : cart.getItemsOrdered()) {
                        if (m.getTitle().equalsIgnoreCase(t)) { cart.removeMedia(m); break; }
                    }
                    break;
                case 4: playMedia(); break;
                case 5: 
                    System.out.println("Order placed!");
                    cart.getItemsOrdered().clear();
                    break;
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
}