package hust.soict.globalict.test.store;
import hust.soict.globalict.aims.store.Store;
import hust.soict.globalict.aims.disc.DigitalVideoDisc;

public class StoreTest {
    public static void main(String[] args) {
        Store store = new Store();
        DigitalVideoDisc dvd1 = new DigitalVideoDisc("The Lion King", "Animation", "Roger Allers", 87, 19.95f);
        store.addDVD(dvd1);
        store.removeDVD(dvd1);
    }
}