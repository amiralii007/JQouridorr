package JQuoridors;

public class Block extends Action {
    private int fpos;
    private int spos;

    public Block(int fpos, int spos) {
        this.fpos = fpos;
        this.spos = spos;
    }

    public int getFpos() {
        return fpos;
    }

    public int getSpos() {
        return spos;
    }
}
