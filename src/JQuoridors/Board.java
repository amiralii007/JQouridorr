package JQuoridors;


public class Board {
    private Bead [] beads;
    private MyGraph field = new MyGraph(81);
    private boolean [] HStick = new boolean[81];
    private boolean [] VStick = new boolean[81];
    private boolean [] dots = new boolean[81];

    public Board(Bead[] beads) {
        this.beads = beads;
        for (int i = 0; i < 81; i++){
            if (!(i % 9 == 0))
                field.connect(i,i - 1);
            if (!((i + 1) % 9 == 0))
                field.connect(i,i + 1);
            if (i - 9 >= 0)
                field.connect(i,i - 9);
            if (i + 9 < 81)
                field.connect(i,i + 9);
        }
        for (int i = 0; i < 81; i++){
            VStick[i] = false;
            HStick[i] = false;
            dots[i] = false;
        }
    }

    public boolean[] getDots() {
        return dots;
    }

    public void setDots(int index, boolean state) {
        this.dots[index] = state;
    }

    public Bead[] getBeads() {
        return beads;
    }

    public MyGraph getField() {
        return field;
    }

    public boolean[] getHStick() {
        return HStick;
    }

    public void setHStick(int n, boolean b) {
        this.HStick [n] = b;
    }

    public boolean[] getVStick() {
        return VStick;
    }

    public void setVStick(int n, boolean b) {
        this.VStick [n] = b;
    }

    public void setField(MyGraph field) {
        this.field = field;
    }

    public void setHStick(boolean[] HStick) {
        this.HStick = HStick;
    }

    public void setVStick(boolean[] VStick) {
        this.VStick = VStick;
    }

    public Board copy (){
        Board board = new Board(this.beads.clone());
        board.setHStick(this.HStick);
        board.setVStick(this.VStick);
        board.setField(this.field.copy());
        return board;
    }

}
