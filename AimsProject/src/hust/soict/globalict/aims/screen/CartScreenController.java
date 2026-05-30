package hust.soict.globalict.aims.screen;

import hust.soict.globalict.aims.cart.Cart;
import hust.soict.globalict.aims.media.Media;
import hust.soict.globalict.aims.media.Playable;
import hust.soict.globalict.aims.exception.PlayerException;
import javafx.collections.transformation.FilteredList;
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
    @FXML private TextField tfFilter;
    @FXML private RadioButton radioBtnFilterId;
    @FXML private RadioButton radioBtnFilterTitle;

    public CartScreenController(Cart cart) {
        this.cart = cart;
    }

    @FXML
    private void initialize() {
        colMediaTitle.setCellValueFactory(new PropertyValueFactory<Media, String>("title"));
        colMediaCategory.setCellValueFactory(new PropertyValueFactory<Media, String>("category"));
        colMediaCost.setCellValueFactory(new PropertyValueFactory<Media, Float>("cost"));
        
        FilteredList<Media> filteredList = new FilteredList<>(cart.getItemsOrdered(), p -> true);
        tblMedia.setItems(filteredList);

        tfFilter.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredList.setPredicate(media -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();
                if (radioBtnFilterId.isSelected()) {
                    return String.valueOf(media.getId()).contains(lowerCaseFilter);
                } else if (radioBtnFilterTitle.isSelected()) {
                    return media.getTitle().toLowerCase().contains(lowerCaseFilter);
                }
                return false;
            });
        });

        updateTableData();

        btnPlay.setVisible(false);
        btnRemove.setVisible(false);

        tblMedia.getSelectionModel().selectedItemProperty().addListener(
            (observable, oldValue, newValue) -> {
                if (newValue != null) {
                    btnRemove.setVisible(true);
                    btnPlay.setVisible(newValue instanceof Playable);
                } else {
                    btnRemove.setVisible(false);
                    btnPlay.setVisible(false);
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
                JOptionPane.showMessageDialog(null, "Playing: " + media.getTitle());
            } catch (PlayerException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        btnPlaceOrder.setOnAction(e -> {
            JOptionPane.showMessageDialog(null, "Order created! Total: " + cart.totalCost() + " $");
            cart.getItemsOrdered().clear();
            updateTableData();
        });
    }

    private void updateTableData() {
        costLabel.setText(cart.totalCost() + " $");
    }
}