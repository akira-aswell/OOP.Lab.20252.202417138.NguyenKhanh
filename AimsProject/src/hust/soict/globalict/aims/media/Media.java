package hust.soict.globalict.aims.media;
import java.util.Comparator;

public abstract class Media implements Comparable<Media> {
    private int id;
    private String title;
    private String category;
    private float cost;

    public static final Comparator<Media> COMPARE_BY_TITLE_COST = new MediaComparatorByTitleCost();
    public static final Comparator<Media> COMPARE_BY_COST_TITLE = new MediaComparatorByCostTitle();

    public Media() {}

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }
    public float getCost() { return cost; }
    public void setCost(float cost) { this.cost = cost; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || !(o instanceof Media)) return false;
        Media media = (Media) o;
        return this.title != null && this.title.equalsIgnoreCase(media.getTitle());
    }

    @Override
    public int compareTo(Media other) {
        int titleCompare = this.title.compareToIgnoreCase(other.getTitle());
        if (titleCompare != 0) return titleCompare;
        return Float.compare(other.getCost(), this.getCost());
    }
}