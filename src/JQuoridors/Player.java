package JQuoridors;

import JQuoridors.gui.GUI;

public abstract class Player {
    private String name;
    private int sticks;
    private Bead bead;
    private final int [] goalPlaces = new int[9];
    private int firstPlace;
    public Player(String name, Bead bead) {
        this.name = name;
        this.bead = bead;
    }
    public void setFirstPlace(int firstPlace) {
        this.firstPlace = firstPlace;
    }
    public Player(String name, Bead bead, int firstPlace) {
        this.name = name;
        this.sticks = 10;
        this.bead = bead;
        this.bead.setPosition(firstPlace);
        this.firstPlace = firstPlace;
        if (firstPlace == 36){
            for (int i = 0; i < 9; i++)
                goalPlaces[i] = (i * 9) + 8;
        }
        else if (firstPlace == 44){
            for (int i = 0; i < 9; i++)
                goalPlaces[i] = i * 9;
        }
        else if (firstPlace == 4){
            for (int i = 0; i < 9; i++)
                goalPlaces[i] = i + 72;
        }
        else if (firstPlace == 76){
            for (int i = 0; i < 9; i++)
                goalPlaces[i] = i;
        }
    }

    public abstract void logicalComputing(Board board, Player player);

    public abstract Action play(GUI gui);

    public boolean winCheck (){
        for (int i = 0; i < 9; i++){
            if (this.goalPlaces[i] == this.bead.getPosition()) {
                System.out.println(this.name + " Wins.");
                System.out.println("Game Over :)");
                return true;
            }
        }
        return false;
    }

    public int getFirstPlace() {
        return firstPlace;
    }

    public Bead getBead() {
        return bead;
    }

    public int[] getGoalPlaces() {
        return goalPlaces;
    }

    public String getName() {
        return name;
    }

    public int getSticks() {
        return sticks;
    }

    public void useStick() {
        sticks--;
    }
}
