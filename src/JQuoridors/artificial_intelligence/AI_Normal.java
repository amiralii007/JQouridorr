package JQuoridors.artificial_intelligence;

import JQuoridors.*;
import JQuoridors.gui.GUI;

import java.util.HashMap;

public class AI_Normal extends Player {
    public int humanPlace = 0;
    private int nextMove = 0;
    private int shortPathValue = Integer.MAX_VALUE;
    private HashMap<String, Integer> humanDir = new HashMap<>();
    

    public AI_Normal(String name, Bead bead, int firstPlace) {
        super(name, bead, firstPlace);
    }
    public AI_Normal(String name, Bead bead) { super(name, bead); }

    @Override
    public void logicalComputing(Board board, Player player) {
        humanPlace = player.getBead().getPosition();
        int shortestGoal = 0;
        for (int i = 0; i < 9; i++){
            int temp = board.getField().stepsFromAtoB(this.getBead().getPosition(),this.getGoalPlaces()[i]);
            if (temp < shortPathValue && temp != 0) {
                shortPathValue = temp;
                shortestGoal = this.getGoalPlaces()[i];
            }
        }
        shortPathValue = Integer.MAX_VALUE;
        nextMove = board.getField().nextNodeInShortestPath(this.getBead().getPosition(),shortestGoal);
        
        if (player.getFirstPlace() == 36) {
            humanDir.put("forward", 0);
            humanDir.put("backward", -1);
            humanDir.put("fSide", -9);
            humanDir.put("sSide", 0);
        }
        else if (player.getFirstPlace() == 44) {
            humanDir.put("forward", -1);
            humanDir.put("backward", 0);
            humanDir.put("fSide", -9);
            humanDir.put("sSide", 0);
        }
        else if (player.getFirstPlace() == 4) {
            humanDir.put("forward", 0);
            humanDir.put("backward", -9);
            humanDir.put("fSide", -1);
            humanDir.put("sSide", 0);
        }
        else if (player.getFirstPlace() == 76) {
            humanDir.put("forward", -9);
            humanDir.put("backward", 0);
            humanDir.put("fSide", -1);
            humanDir.put("sSide", 0);
        }
    }

    @Override
    public Action play(GUI gui) {
        double chance = Math.random() * 1;
        if (this.getSticks() == 0)
            chance = 0;
        if (chance < 0.65){
            System.out.println(this.getName() + " Moving...");
            return new Move(nextMove);
        }
        else if (this.getSticks() != 0){
            double rand = (Math.random() * 10);
            int fStickPlace = 0;
            int sStickPlace = 0;
            while (true){
                System.out.println(this.getName() + "putting stick...");
                if (rand < 8){
                    fStickPlace = humanPlace + humanDir.get("forward");
                    if (Math.random() < 0.5)
                        sStickPlace = fStickPlace + humanDir.get("fSide");
                    else
                        sStickPlace = fStickPlace - humanDir.get("fSide");
                }
                else if (rand < 8.75){
                    fStickPlace = humanPlace + humanDir.get("fSide");
                    if (Math.random() < 0.5)
                        sStickPlace = fStickPlace + (humanDir.get("forward") + humanDir.get("backward"));
                    else
                        sStickPlace = fStickPlace - (humanDir.get("forward") + humanDir.get("backward"));
                }
                else if (rand < 9.5){
                    fStickPlace = humanPlace + humanDir.get("sSide");
                    if (Math.random() < 0.5)
                        sStickPlace = fStickPlace + (humanDir.get("forward") + humanDir.get("backward"));
                    else
                        sStickPlace = fStickPlace - (humanDir.get("forward") + humanDir.get("backward"));
                }
                else{
                    fStickPlace = humanPlace + humanDir.get("backward");
                    if (Math.random() < 0.5)
                        sStickPlace = fStickPlace + humanDir.get("fSide");
                    else
                        sStickPlace = fStickPlace - humanDir.get("fSide");
                }
                return new Block(fStickPlace,sStickPlace);
            }
        }
        return null;
    }
}
