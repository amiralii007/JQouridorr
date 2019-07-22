package JQuoridors.artificial_intelligence;

import JQuoridors.*;
import JQuoridors.gui.GUI;

public class AI_Hard extends Player {
    public AI_Hard(String name, Bead bead) { super(name, bead); }
    public AI_Hard(String name, Bead bead, int firstPlace) {
        super(name, bead, firstPlace);
    }

    private int fStick = 0;
    private int sStick = 0;
    private int nextMove = 0;
    private int opShPath = Integer.MAX_VALUE;
    private int shPath = Integer.MAX_VALUE;

    @Override
    public void logicalComputing(Board board, Player player) {
        int shortestGoal = 0;
        shPath = Integer.MAX_VALUE;
        for (int i = 0; i < 9; i++){
            int temp = board.getField().stepsFromAtoB(this.getBead().getPosition(),this.getGoalPlaces()[i]);
            if (temp < shPath && temp != 0) {
                shPath = temp;
                shortestGoal = this.getGoalPlaces()[i];
            }
        }
        nextMove = board.getField().nextNodeInShortestPath(this.getBead().getPosition(),shortestGoal);
        opShPath = Integer.MAX_VALUE;
        for (int i = 0; i < 9; i++){
            int temp = board.getField().stepsFromAtoB(player.getBead().getPosition(),player.getGoalPlaces()[i]);
            if (temp < opShPath && temp != 0) {
                opShPath = temp;
            }
        }
        putStickLogic(board, player);
    }

//    private int shortesPathValue (Board board){
//        int shortestPath = Integer.MAX_VALUE;
//        for (int j = 0; j < 9; j++) {
//            int temp = board.getField().stepsFromAtoB(this.getBead().getPosition(), this.getGoalPlaces()[j]);
//            if (temp < shortestPath && temp != 0) {
//                shortestPath = temp;
//            }
//        }
//        return shortestPath;
//    }

    private boolean blockCheker (Board board, Player player){
        boolean sIsBlock = true;
        for (int j = 0; j < 9; j++){
            if (board.getField().hasPath(player.getBead().getPosition(),player.getGoalPlaces()[j]))
                sIsBlock = false;
        }
        return sIsBlock;
    }

    public void putStickLogic(Board board, Player player){
        int longestPath = opShPath;
        /// horizontal stick checker
        for (int i = 0; i <= 71; i++){
            Board cBoard = board.copy();
            if ((i % 8) != 0 && !cBoard.getHStick()[i] && !cBoard.getHStick()[i + 1] && !cBoard.getVStick()[i] && !cBoard.getVStick()[i + 9]){
                cBoard.getField().disconnect(i , i + 9);
                cBoard.getField().disconnect(i + 1, i +10);
//                System.out.println("longest path: " + longestPath);
                if (!(blockCheker(cBoard, this) || blockCheker(cBoard, player))){
                    int shortestPath = Integer.MAX_VALUE;
                    for (int j = 0; j < 9; j++) {
                        int temp = cBoard.getField().stepsFromAtoB(player.getBead().getPosition(), player.getGoalPlaces()[j]);
                        if (temp < shortestPath && temp != 0) {
                            shortestPath = temp;
                        }
                    }
//                    System.out.println("shortest path: " + shortestPath);
                    if (longestPath < shortestPath){
                        fStick = i;
                        sStick = i + 1;
                        longestPath = shortestPath;
                    }

//                    System.out.println(i);
//                    System.out.println("stick" + fStick + "   " + sStick);
                }
            }
        }

        for (int i = 0; i < 71; i++){
            Board cBoard = board.copy();
            if (i % 8 != 0 && !cBoard.getVStick()[i] && !cBoard.getVStick()[i + 9] && !cBoard.getHStick()[i] && !cBoard.getHStick()[i + 1]){
                cBoard.getField().disconnect(i , i + 1);
                cBoard.getField().disconnect(i + 9, i +10);
//                System.out.println("Vlongest path: " + longestPath);
                if (!(blockCheker(cBoard, this) || blockCheker(cBoard, player))){
                    int shortestPath = Integer.MAX_VALUE;
                    for (int j = 0; j < 9; j++) {

                        int temp = cBoard.getField().stepsFromAtoB(player.getBead().getPosition(), player.getGoalPlaces()[j]);
//                        System.out.println("steps from " + player.getBead().getPosition() + " to " + player.getGoalPlaces()[j] + " : " + temp);
                        if (temp < shortestPath && temp != 0) {
                            shortestPath = temp;
                        }
                    }
//                    System.out.println("shortest path: " + shortestPath);
                    if (longestPath < shortestPath){
                        fStick = i;
                        sStick = i + 9;
                        longestPath = shortestPath;
                    }

//                    System.out.println("stick" + fStick + "   " + sStick);
//                    System.out.println(i);
                }
            }
        }
    }

    @Override
    public Action play(GUI gui) {
        if (shPath < opShPath || this.getSticks() == 0){
            System.out.println("Moving...");
            return new Move(nextMove);
        }
        else {
            System.out.println("putting Stick");
            return new Block(fStick,sStick);
        }
    }

    public Action forceMove(Board board, Player player){
        logicalComputing(board,player);
        return new Move(nextMove);
    }
}
