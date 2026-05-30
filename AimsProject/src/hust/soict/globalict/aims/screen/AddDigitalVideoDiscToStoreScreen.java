package hust.soict.globalict.aims.screen;

import hust.soict.globalict.aims.media.DigitalVideoDisc;
import hust.soict.globalict.aims.store.Store;
import javax.swing.*;

public class AddDigitalVideoDiscToStoreScreen extends AddItemToStoreScreen {
    private JTextField tfDirector, tfLength;

    public AddDigitalVideoDiscToStoreScreen(Store store) {
        super(store, "Add DVD to Store");
    }

    @Override
    protected void addSpecificFields() {
        setLayout(new java.awt.GridLayout(7, 2, 5, 5));
        
        add(new JLabel(" Director: "));
        tfDirector = new JTextField();
        add(tfDirector);

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
            int length = Integer.parseInt(tfLength.getText());

            DigitalVideoDisc dvd = new DigitalVideoDisc(title, category, director, length, cost);
            store.addMedia(dvd);
            
            JOptionPane.showMessageDialog(null, "DVD Added Successfully!");
            this.dispose();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Invalid Cost or Length format!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}