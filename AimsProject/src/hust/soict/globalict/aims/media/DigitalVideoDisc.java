package hust.soict.globalict.aims.media;

public class DigitalVideoDisc extends Disc implements Playable {
    private static int nbDigitalVideoDiscs = 0;

    public DigitalVideoDisc(String title, String category, String director, int length, float cost) {
        super(title, category, cost, length, director);
        nbDigitalVideoDiscs++;
        setId(nbDigitalVideoDiscs);
    }
    
    public DigitalVideoDisc(String title, String category, float cost) {
        super(title, category, cost, 0, "");
        nbDigitalVideoDiscs++;
        setId(nbDigitalVideoDiscs);
    }

    public DigitalVideoDisc(String title) {
        super(title, "", 0.0f, 0, "");
        nbDigitalVideoDiscs++;
        setId(nbDigitalVideoDiscs);
    }

    @Override
    public void play() {
        if (this.getLength() <= 0) {
            System.out.println("The DVD '" + this.getTitle() + "' cannot be played (length <= 0).");
            return;
        }
        System.out.println("Playing DVD: " + this.getTitle());
        System.out.println("DVD length: " + this.getLength());
    }

    @Override
    public String toString() {
        return "DVD - " + getTitle() + " - " + getCategory() + " - " + getDirector() + " - " + getLength() + " mins: " + getCost() + " $";
    }
}