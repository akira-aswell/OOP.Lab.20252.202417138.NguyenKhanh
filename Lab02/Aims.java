public class Aims {
    public static void main(String[] args) {
        Cart anOrder = new Cart();
        
        DigitalVideoDisc dvd1 = new DigitalVideoDisc("JJK", "Anime", "Gaygay", 87, 19.95f);
        anOrder.addDigitalVideoDisc(dvd1);
        
        DigitalVideoDisc dvd2 = new DigitalVideoDisc("CSM", "Anime", "Fraudjimoto", 87, 24.95f);
        anOrder.addDigitalVideoDisc(dvd2);
        
        DigitalVideoDisc dvd3 = new DigitalVideoDisc("Minions", "Animation", 18.99f);
        anOrder.addDigitalVideoDisc(dvd3);
        
        System.out.println("total cost: ");
        System.out.println(anOrder.totalCost());
        
        anOrder.removeDigitalVideoDisc(dvd2);
        
        System.out.println("total cost ");
        System.out.println(anOrder.totalCost());
    }
}