package JQuoridors.artificial_intelligence;

import JQuoridors.*;
import JQuoridors.gui.GUI;

public class AI_Insane extends Player {

    private int fStick = 0;
    private int sStick = 0;
    private int nextMove = 0;
    private int opShPath = Integer.MAX_VALUE;
    private int shPath = Integer.MAX_VALUE;

    public AI_Insane(String name, Bead bead, int firstPlace) {
        super(name, bead, firstPlace);
    }
    public AI_Insane(String name, Bead bead) { super(name, bead); }

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

    private void putStickLogic(Board board, Player player) {
        int difShPathAftrStick = opShPath - shPath;
        /// Horizontal Stick Shecker
        for (int i = 0; i <= 71; i++){
            Board cBoard = board.copy();
            if ((i % 8) != 0 && !cBoard.getHStick()[i] && !cBoard.getHStick()[i + 1] && !cBoard.getVStick()[i] && !cBoard.getVStick()[i + 9]){
                cBoard.getField().disconnect(i , i + 9);
                cBoard.getField().disconnect(i + 1, i +10);
                if (!(blockCheker(cBoard, this) || blockCheker(cBoard, player))){
                    int TempDifShPathAftrStick = shortesPathValue(player,cBoard) - shortesPathValue(this,cBoard);
                    if (TempDifShPathAftrStick > difShPathAftrStick){
                        difShPathAftrStick = TempDifShPathAftrStick;
                        fStick = i;
                        sStick = i + 1;
                    }
                }
            }
        }
        ///Vertical Stick Cheker
        for (int i = 0; i <= 71; i++){
            Board cBoard = board.copy();
            if (i % 8 != 0 && !cBoard.getVStick()[i] && !cBoard.getVStick()[i + 9] && !cBoard.getHStick()[i] && !cBoard.getHStick()[i + 1]){
                cBoard.getField().disconnect(i , i + 1);
                cBoard.getField().disconnect(i + 9, i +10);
                if (!(blockCheker(cBoard, this) || blockCheker(cBoard, player))){
                    int TempDifShPathAftrStick = shortesPathValue(player,cBoard) - shortesPathValue(this,cBoard);
                    if (TempDifShPathAftrStick >= difShPathAftrStick){
                        difShPathAftrStick = TempDifShPathAftrStick;
                        fStick = i;
                        sStick = i + 9;
                    }

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
            System.out.println("putting Stick " + fStick + " " + sStick);
            return new Block(fStick,sStick);
        }
    }

    public Action forceMove(Board board, Player player){
        logicalComputing(board,player);
        return new Move(nextMove);
    }

    private int shortesPathValue (Player player,Board board){
        int shortestPath = Integer.MAX_VALUE;
        for (int j = 0; j < 9; j++) {
            int temp = board.getField().stepsFromAtoB(player.getBead().getPosition(), player.getGoalPlaces()[j]);
            if (temp < shortestPath && temp != 0) {
                shortestPath = temp;
            }
        }
        return shortestPath;
    }

    private boolean blockCheker (Board board, Player player){
        boolean sIsBlock = true;
        for (int j = 0; j < 9; j++){
            if (board.getField().hasPath(player.getBead().getPosition(),player.getGoalPlaces()[j]))
                sIsBlock = false;
        }
        return sIsBlock;
    }

}
