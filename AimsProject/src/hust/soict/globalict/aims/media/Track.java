package hust.soict.globalict.aims.media;

public class Track implements Playable {
    private String title;
    private int length;

    public Track(String title, int length) {
        this.title = title;
        this.length = length;
    }

    public String getTitle() { return title; }
    public int getLength() { return length; }

    @Override
    public void play() {
        if (this.getLength() <= 0) {
            System.out.println("The track '" + this.getTitle() + "' cannot be played (length <= 0).");
            return;
        }
        System.out.println("Playing track: " + this.getTitle());
        System.out.println("Track length: " + this.getLength());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || !(o instanceof Track)) return false;
        Track track = (Track) o;
        return this.length == track.getLength() && 
               this.title != null && 
               this.title.equalsIgnoreCase(track.getTitle());
    }
}