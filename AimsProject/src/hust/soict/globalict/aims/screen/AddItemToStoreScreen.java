package hust.soict.globalict.aims.screen;

import hust.soict.globalict.aims.store.Store;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public abstract class AddItemToStoreScreen extends JFrame {
    protected Store store;
    protected JTextField tfTitle, tfCategory, tfCost;
    protected JButton btnAdd;

    public AddItemToStoreScreen(Store store, String titleStr) {
        this.store = store;
        
        setTitle(titleStr);
        setSize(400, 300);
        setLayout(new GridLayout(5, 2, 5, 5)); 

        add(new JLabel(" Title: "));
        tfTitle = new JTextField();
        add(tfTitle);

        add(new JLabel(" Category: "));
        tfCategory = new JTextField();
        add(tfCategory);

        add(new JLabel(" Cost: "));
        tfCost = new JTextField();
        add(tfCost);
        
        addSpecificFields();

        btnAdd = new JButton("Add to Store");
        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addItem();
            }
        });
        
        add(new JLabel());
        add(btnAdd);

        setVisible(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    protected abstract void addSpecificFields();
    protected abstract void addItem();
}