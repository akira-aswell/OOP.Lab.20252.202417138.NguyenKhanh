package hust.soict.globalict.aims.screen;

import hust.soict.globalict.aims.media.CompactDisc;
import hust.soict.globalict.aims.store.Store;
import javax.swing.*;
import java.awt.*;

public class AddCompactDiscToStoreScreen extends AddItemToStoreScreen {
    private JTextField tfDirector, tfLength, tfArtist;

    public AddCompactDiscToStoreScreen(Store store) {
        super(store, "Add CD to Store");
    }

    @Override
    protected void addSpecificFields() {
        setLayout(new GridLayout(8, 2, 5, 5));

        add(new JLabel(" Director: "));
        tfDirector = new JTextField();
        add(tfDirector);

        add(new JLabel(" Artist: "));
        tfArtist = new JTextField();
        add(tfArtist);

        add(new JLabel(" Length: "));
        tfLength = new JTextField();
        add(tfLength);
    }

    @Override
    protected void addItem() {
        try {
            String title = tfTitle.getText();
            String category = tfCategory.getText();
            float cost = Float.parseFloat(tfCost.getText());
            String director = tfDirector.getText();
            String artist = tfArtist.getText();
            int length = Integer.parseInt(tfLength.getText());

            CompactDisc cd = new CompactDisc(title, category, cost, length, director, artist);
            
            store.addMedia(cd);
            JOptionPane.showMessageDialog(null, "CD Added Successfully!");
            this.dispose();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Invalid Cost or Length format!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}