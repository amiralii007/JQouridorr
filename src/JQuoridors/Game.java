package JQuoridors;

import JQuoridors.artificial_intelligence.AI_Hard;
import JQuoridors.artificial_intelligence.AI_Insane;
import JQuoridors.gui.GUI;

import java.util.concurrent.TimeUnit;

public class Game {
    private Board board;
    private Player [] players;
    GUI gui = new GUI();

    public Game(Board board, Player[] players) {
        this.board = board;
        this.players = players;
    }

    /// agar in meghdar e true bargardoone player i ke rahesh baste shode mibare :)
    public boolean blockingCheck(int i){
        if (i == 1)
            i = 0;
        else
            i = 1;
        for (int j = 0; j < 9; j++){
            if (board.getField().hasPath(players[i].getBead().getPosition(), players[i].getGoalPlaces()[j]))
                return false;
        }
        System.out.println("gool khordi :)");
        System.out.println(players[i].getName() + " Wins.");
        System.out.println("GAME OVER");
        return true;
    }

    public void run() throws InterruptedException {
        Reporter.showBoard(board);
        gui.update(board);
        while (true){
            double t = System.currentTimeMillis();
            boolean gameOver = false;
            for (int i = 0; i < players.length; i++){
                try{
                    players[i].logicalComputing(board,players[(i+1) % 2]);
                    if (!doAction(players[i].play(gui),players[i])){
                        if (players[i] instanceof AI_Hard){
                            System.out.print("Force Move ");
                            System.out.println(((Move)((AI_Hard)players[i]).forceMove(board,players[(i+1) % 2])).getPos());
                            doAction(((AI_Hard)players[i]).forceMove(board,players[(i+1) % 2]), players[i]);
                        }
                        else if (players[i] instanceof AI_Insane){
                            System.out.print("Force Move ");
                            System.out.println(((Move)((AI_Insane)players[i]).forceMove(board,players[(i+1) % 2])).getPos());
                            doAction(((AI_Insane)players[i]).forceMove(board,players[(i+1) % 2]), players[i]);
                        }
                        else {
                            i--;
                            continue;
                        }
                    }
                }catch (Exception e){
                    System.out.println("Exeption");
                    i--;
                    continue;
                }
                Reporter.showBoard(board);
                gui.update(board);
                if (players[i].winCheck())
                    gameOver = true;
                if (blockingCheck(i))
                    gameOver = true;
                if (gameOver)
                    break;
                TimeUnit.MILLISECONDS.sleep(300);
            }
            if (gameOver)
                break;
            System.out.println(System.currentTimeMillis() - t);

        }
    }

    private Player secPlayer (Player player){
        if (players[0].getBead() != player.getBead())
            return players[0];
        else
            return players[1];
    }

    private boolean doAction(Action action, Player player){
        if (action instanceof Move){
            Move move = (Move)action;
            return move(move, player);
        }
        else if (action instanceof Block){
            Block block = (Block) action;
            return block(block, player);
        }
        else
            return false;
    }

    private boolean move(Move move, Player player){
        if (board.getField().isConnect(move.getPos(),player.getBead().getPosition())){
            if (move.getPos() == secPlayer(player).getBead().getPosition()){
                if (board.getField().isConnect(move.getPos(), (2 * move.getPos()) - player.getBead().getPosition())){
                    player.getBead().setPosition((2 * move.getPos()) - player.getBead().getPosition());
                    return true;
                }
                else {
                    return false;
                }
            }
            else {
                player.getBead().setPosition(move.getPos());
                return true;
            }
        }
        else
            return false;
    }

    private boolean block(Block block, Player player){
        int fCell = block.getFpos();
        int sCell = block.getSpos();
        int min = Math.min(sCell,fCell);
        if (Math.abs(fCell - sCell) == 1 && min < 72 && Math.max(fCell,sCell)%9 != 0){
            if (!(board.getVStick()[fCell] && board.getVStick()[sCell])&&!board.getHStick()[min]&& !board.getHStick()[min + 9]){
                board.setHStick(fCell,true);
                board.setHStick(sCell,true);
                board.setDots(Math.min(fCell, sCell),true);
                board.getField().disconnect(fCell,fCell + 9);
                board.getField().disconnect(sCell,sCell + 9);
                player.useStick();
                return true;
            }
            return false;
        }
        else if (Math.abs(fCell - sCell) == 9 && min % 9 != 8){
            if (!(board.getHStick()[fCell] && board.getHStick()[fCell+1] && !board.getVStick()[min]&& !board.getVStick()[min + 1])){
                board.setVStick(fCell,true);
                board.setVStick(sCell,true);
                board.setDots(Math.min(fCell, sCell),true);
                board.getField().disconnect(fCell,fCell + 1);
                board.getField().disconnect(sCell,sCell + 1);
                player.useStick();
                return true;
            }
            return false;
        }
        return false;
    }
    @Override
    public String toString() {
        return "Game{" +
                "board=" + board.toString() +
                ", players=" + players +
                '}';}
}
