package hust.soict.globalict.aims.media;

import hust.soict.globalict.aims.exception.PlayerException;

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
    public void play() throws PlayerException {
        if (this.getLength() > 0) {
            System.out.println("Playing: " + this.getTitle());
            System.out.println("Length: " + this.getLength());
        } else {
            throw new PlayerException("ERROR: DVD/Track length is non-positive!");
        }
    }

    @Override
    public String toString() {
        return "DVD - " + getTitle() + " - " + getCategory() + " - " + getDirector() + " - " + getLength() + " mins: " + getCost() + " $";
    }
}