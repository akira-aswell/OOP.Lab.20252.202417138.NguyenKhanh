package hust.soict.globalict.aims.screen;

import hust.soict.globalict.aims.cart.Cart;
import hust.soict.globalict.aims.media.Media;
import hust.soict.globalict.aims.media.Playable;
import hust.soict.globalict.aims.exception.PlayerException;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javax.swing.JOptionPane;

public class CartScreenController {
    private Cart cart;

    @FXML private TableView<Media> tblMedia;
    @FXML private TableColumn<Media, String> colMediaTitle;
    @FXML private TableColumn<Media, String> colMediaCategory;
    @FXML private TableColumn<Media, Float> colMediaCost;
    @FXML private Button btnPlay;
    @FXML private Button btnRemove;
    @FXML private Button btnPlaceOrder;
    @FXML private Label costLabel;

    public CartScreenController(Cart cart) {
        this.cart = cart;
    }

    @FXML
    private void initialize() {
        colMediaTitle.setCellValueFactory(new PropertyValueFactory<Media, String>("title"));
        colMediaCategory.setCellValueFactory(new PropertyValueFactory<Media, String>("category"));
        colMediaCost.setCellValueFactory(new PropertyValueFactory<Media, Float>("cost"));
        
        updateTableData();

        btnPlay.setVisible(false);
        btnRemove.setVisible(false);

        tblMedia.getSelectionModel().selectedItemProperty().addListener(
            new ChangeListener<Media>() {
                @Override
                public void changed(ObservableValue<? extends Media> observable, Media oldValue, Media newValue) {
                    if (newValue != null) {
                        btnRemove.setVisible(true);
                        btnPlay.setVisible(newValue instanceof Playable);
                    } else {
                        btnRemove.setVisible(false);
                        btnPlay.setVisible(false);
                    }
                }
            }
        );

        btnRemove.setOnAction(e -> {
            Media media = tblMedia.getSelectionModel().getSelectedItem();
            cart.removeMedia(media);
            updateTableData();
        });

        btnPlay.setOnAction(e -> {
            Media media = tblMedia.getSelectionModel().getSelectedItem();
            try {
                ((Playable) media).play();
                JOptionPane.showMessageDialog(null, "Playing: " + media.getTitle(), "Media Player", JOptionPane.INFORMATION_MESSAGE);
            } catch (PlayerException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Illegal Length", JOptionPane.ERROR_MESSAGE);
            }
        });

        btnPlaceOrder.setOnAction(e -> {
            JOptionPane.showMessageDialog(null, "Order created successfully!\nTotal paid: " + cart.totalCost() + " $");
            cart.getItemsOrdered().clear();
            updateTableData();
        });
    }

    private void updateTableData() {
        ObservableList<Media> observableCart = FXCollections.observableArrayList(cart.getItemsOrdered());
        tblMedia.setItems(observableCart);
        costLabel.setText(cart.totalCost() + " $");
    }
}