package JQuoridors;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Tournoment {
    private Game[] games;
    private int counter;
    ArrayList<Player> players = new ArrayList<>();

    public Tournoment(int counter, ArrayList<Player> players) {
        this.counter = counter;
        games = new Game[counter];

    }

    public Tournoment(int counter) {
        this.counter = counter;
        games = new Game[counter];
    }

    static  ArrayList shuffleArray(ArrayList<Player> ar) {
        // If running on Java 6 or older, use `new Random()` on RHS here
        Random rnd = ThreadLocalRandom.current();
        for (int i = ar.size() - 1; i > 0; i--) {
            int index = rnd.nextInt(i + 1);
            // Simple swap
            Player a = ar.get(index);
            ar.set(index, ar.get(i));
            ar.set(i, a);
        }
        return ar;
    }



    public void setPlayers(ArrayList<Player> players) {
        this.players = players;
    }

    public void addPlayer(Player p) {
        this.players.add(p);
    }


    public void randome() {
        ArrayList random = shuffleArray(players);
        for (int i =0 ; i < random.size(); i++){
            Player[] playerrs = {(Player)random.get(i) ,  (Player) random.get(++i)};
            playerrs[0].setFirstPlace(36);
            playerrs[1].setFirstPlace(44);
            Bead bead[] = new Bead[2];
            bead[0] = playerrs[0].getBead();
            bead[1] = playerrs[1].getBead();
            Board board1 = new Board(bead);
            games[i] = new Game(board1 , playerrs);
        }
    }

    public void run() {
        for (int i =0; i<games.length; i++) {
            System.out.println("start game of" + games[i].toString());
            try {
                games[i].run();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("finish Game between "+games[i].toString());
        }
    }

    public void addGame(int shomar , Player[] players) {
        players[0].setFirstPlace(36);
        players[1].setFirstPlace(44);

        Bead bead[] = new Bead[2];
        bead[0] = players[0].getBead();
        bead[1] = players[1].getBead();
        Board board1 = new Board(bead);
        games[shomar] = new Game(board1 , players);
    }

}
