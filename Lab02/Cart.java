public class Cart {
    public static final int MAX_ORDER = 20;
    private DigitalVideoDisc items[] = new DigitalVideoDisc[MAX_ORDER];
    private int qtyOrdered = 0;

    public void addDigitalVideoDisc(DigitalVideoDisc disc) {
        if (qtyOrdered < MAX_ORDER) {
            items[qtyOrdered] = disc;
            qtyOrdered++;
            System.out.println("disc added");
        } else {
            System.out.println("cant");
        }
    }

    public void removeDigitalVideoDisc(DigitalVideoDisc disc) {
        for (int i = 0; i < qtyOrdered; i++) {
            if (items[i] == disc) {
                for (int j = i; j < qtyOrdered - 1; j++) {
                    items[j] = items[j + 1];
                }
                items[qtyOrdered - 1] = null;
                qtyOrdered--;
                break;
            }
        }
    }

    public float totalCost() {
        float total = 0;
        for (int i = 0; i < qtyOrdered; i++) {
            total += items[i].getCost();
        }
        return total;
    }
}