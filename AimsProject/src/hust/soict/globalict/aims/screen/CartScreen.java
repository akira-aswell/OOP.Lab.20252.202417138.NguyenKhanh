package hust.soict.globalict.aims.screen;

import hust.soict.globalict.aims.cart.Cart;
import hust.soict.globalict.aims.media.DigitalVideoDisc;
import hust.soict.globalict.aims.media.Book;

import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javax.swing.JFrame;
import java.io.IOException;

public class CartScreen extends JFrame {

    public CartScreen(Cart cart) {
        super();
        
        JFXPanel fxPanel = new JFXPanel();
        this.add(fxPanel);
        
        this.setTitle("Cart");
        this.setVisible(true);
        this.setSize(1024, 768);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/hust/soict/globalict/aims/screen/cart.fxml"));
                    
                    CartScreenController controller = new CartScreenController(cart);
                    loader.setController(controller);
                    
                    Parent root = loader.load();
                    fxPanel.setScene(new Scene(root));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public static void main(String[] args) {
        Cart cart = new Cart();
        
        cart.addMedia(new DigitalVideoDisc("The Lion King", "Animation", "Roger Allers", 87, 19.95f));
        cart.addMedia(new Book("Java Programming", "Education", 25.5f, 300));
        cart.addMedia(new DigitalVideoDisc("Lỗi Độ Dài", "Action", "Director", 0, 15.0f)); 

        new CartScreen(cart);
    }
}