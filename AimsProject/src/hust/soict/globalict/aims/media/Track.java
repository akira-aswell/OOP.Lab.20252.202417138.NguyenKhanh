package hust.soict.globalict.aims.media;

import hust.soict.globalict.aims.exception.PlayerException;

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
    public void play() throws PlayerException {
        if (this.getLength() > 0) {
            System.out.println("Playing: " + this.getTitle());
            System.out.println("Length: " + this.getLength());
        } else {
            throw new PlayerException("ERROR: DVD/Track length is non-positive!");
        }
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