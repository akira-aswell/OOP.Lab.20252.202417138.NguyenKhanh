package hust.soict.globalict.aims.screen;

import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;

import hust.soict.globalict.aims.media.DigitalVideoDisc;
import hust.soict.globalict.aims.media.Book;
import hust.soict.globalict.aims.media.Media;
import hust.soict.globalict.aims.store.Store;
import hust.soict.globalict.aims.cart.Cart;

public class StoreScreen extends JFrame {
    private Store store;
    private Cart cart;

    public StoreScreen(Store store, Cart cart) {
        this.store = store;
        this.cart = cart;
        Container cp = getContentPane();
        cp.setLayout(new BorderLayout());

        cp.add(createNorth(), BorderLayout.NORTH);

        cp.add(createCenter(), BorderLayout.CENTER);

        setVisible(true);
        setTitle("Store");
        setSize(1024, 768);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    JPanel createNorth() {
        JPanel north = new JPanel();
        north.setLayout(new BoxLayout(north, BoxLayout.Y_AXIS));
        north.add(createMenuBar());
        north.add(createHeader());
        return north;
    }

    JMenuBar createMenuBar() {
        JMenu menu = new JMenu("Options");

        JMenu smUpdateStore = new JMenu("Update Store");
        
        JMenuItem menuAddBook = new JMenuItem("Add Book");
        JMenuItem menuAddCd = new JMenuItem("Add CD");
        JMenuItem menuAddDvd = new JMenuItem("Add DVD");

        menuAddBook.addActionListener(e -> new AddBookToStoreScreen(store));
        menuAddCd.addActionListener(e -> new AddCompactDiscToStoreScreen(store));
        menuAddDvd.addActionListener(e -> new AddDigitalVideoDiscToStoreScreen(store));

        smUpdateStore.add(menuAddBook);
        smUpdateStore.add(menuAddCd);
        smUpdateStore.add(menuAddDvd);

        menu.add(smUpdateStore);
        menu.add(new JMenuItem("View store"));
        menu.add(new JMenuItem("View cart"));

        JMenuBar menuBar = new JMenuBar();
        menuBar.setLayout(new FlowLayout(FlowLayout.LEFT));
        menuBar.add(menu);

        return menuBar;
    }

    JPanel createHeader() {
        JPanel header = new JPanel();
        header.setLayout(new BoxLayout(header, BoxLayout.X_AXIS));

        JLabel title = new JLabel("AIMS");
        title.setFont(new Font(title.getFont().getName(), Font.PLAIN, 50));
        title.setForeground(Color.CYAN);

        JButton cart = new JButton("View cart");
        cart.setPreferredSize(new Dimension(100, 50));
        cart.setMaximumSize(new Dimension(100, 50));

        header.add(Box.createRigidArea(new Dimension(10, 10)));
        header.add(title);
        header.add(Box.createHorizontalGlue());
        header.add(cart);
        header.add(Box.createRigidArea(new Dimension(10, 10)));

        return header;
    }

    JPanel createCenter() {
        JPanel center = new JPanel();
        center.setLayout(new GridLayout(3, 3, 2, 2));

        ArrayList<Media> mediaInStore = store.getItemsInStore();
        for (int i = 0; i < mediaInStore.size(); i++) {

            MediaStore cell = new MediaStore(mediaInStore.get(i), this.cart);
            center.add(cell);
        }
        
        return center;
    }

    public static void main(String[] args) {
        Store store = new Store();
        Cart cart = new Cart();

        store.addMedia(new DigitalVideoDisc("The Lion King", "Animation", "Roger Allers", 87, 19.95f));
        store.addMedia(new DigitalVideoDisc("Star Wars", "Science Fiction", "George Lucas", 124, 24.95f));
        store.addMedia(new DigitalVideoDisc("Aladdin", "Animation", 18.99f));

        store.addMedia(new DigitalVideoDisc("Lỗi Độ Dài", "Action", "Director", 0, 15.0f)); 

        Book book = new Book("Java OOP", "Education", 25.5f, 300);
        store.addMedia(book);

        new StoreScreen(store, cart);
    }
}