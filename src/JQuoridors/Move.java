package JQuoridors;

public class Move extends Action {
    private int pos;

    public Move(int pos) {
        this.pos = pos;
    }

    public int getPos() {
        return pos;
    }
}
