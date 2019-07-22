package JQuoridors;

public class Bead {
    private int position;
    private static int idCounter = 1;
    private int id;

    public Bead(int position) {
        this.position = position;
        id = idCounter;
        idCounter++;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public int getId() {
        return id;
    }
}
