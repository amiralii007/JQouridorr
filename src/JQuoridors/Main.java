package JQuoridors;

import JQuoridors.artificial_intelligence.*;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
public static void main(String[] args) throws InterruptedException {
    Bead[] beads = new Bead[2];
    beads[0] = new Bead(0);
    beads[1] = new Bead(1);
    System.out.println("HHHHHHHHHHHHHHHHHH IIIIIIIIIIIIIIIIIIII :)\n" +
            "Do you want :\n" +
            "1.Quik Game \n" +
            "2.Tournoment\n");
    Scanner scanner = new Scanner(System.in);
    while (true) {
        int choose = scanner.nextInt();
        if (choose == 1) {
            System.out.println("Choose :\n" +
                    "1. AI - AI\n" +
                    "2. Human - AI\n" +
                    "3. Human - Human\n ");
            choose = scanner.nextInt();
            if (choose == 1) {
                System.out.println("please choose which game you want to see\n" +
                        "1) insane & insane\n" +
                        "2) insane & hard\n" +
                        "3) hard & hard\n" +
                        "4) insane & normal\n" +
                        "5) hard & normal\n" +
                        "6) normal & normal");
                choose = scanner.nextInt();
                System.out.println("Just look & enjoy");
                Player[] players = new Player[2];
                if(choose ==1){
                    players[0] = new AI_Insane("iman", beads[0], 36);
                    players[1] = new AI_Insane("hasan", beads[1], 44);
                    Board board = new Board(beads);
                    Game game = new Game(board, players);
                    game.run();
                    return;}
                else if(choose ==2){
                    players[0] = new AI_Hard("iman", beads[0], 36);
                    players[1] = new AI_Insane("hasan", beads[1], 44);
                    Board board = new Board(beads);
                    Game game = new Game(board, players);
                    game.run();
                    return;}
                else if(choose ==3){
                    players[0] = new AI_Hard("iman", beads[0], 36);
                    players[1] = new AI_Hard("hasan", beads[1], 44);
                    Board board = new Board(beads);
                    Game game = new Game(board, players);
                    game.run();
                    return;}
                else if(choose ==4){
                    players[0] = new AI_Normal("iman", beads[0], 36);
                    players[1] = new AI_Insane("hasan", beads[1], 44);
                    Board board = new Board(beads);
                    Game game = new Game(board, players);
                    game.run();
                    return;}
                else if(choose ==5){
                    players[0] = new AI_Hard("iman", beads[0], 36);
                    players[1] = new AI_Normal("hasan", beads[1], 44);
                    Board board = new Board(beads);
                    Game game = new Game(board, players);
                    game.run();
                    return;}
                else if(choose ==6){
                    players[0] = new AI_Normal("iman", beads[0], 36);
                    players[1] = new AI_Normal("hasan", beads[1], 44);
                    Board board = new Board(beads);
                    Game game = new Game(board, players);
                    game.run();
                    return;}
                else {
                    System.out.println("wrong try again");
                }
            } else if (choose == 2) {
                System.out.println("please give your name");
                String name = scanner.next();
                Player[] players = new Player[2];
                players[1] = new Human_player(name, beads[1], 44);
                System.out.println("which AI you wanna Play??\n" +
                        "1) Insane" +
                        "\n2) hard \n" +
                        "3) normal ");
                choose = scanner.nextInt();
                if(choose == 1){
                    players[0] = new AI_Insane("iman", beads[0], 36);
                    Board board = new Board(beads);
                    Game game = new Game(board, players);
                    game.run();
                    return;}
                else if(choose == 2){
                    players[0] = new AI_Hard("iman", beads[0], 36);
                    Board board = new Board(beads);
                    Game game = new Game(board, players);
                    game.run();
                    return;}
                else if(choose == 3){
                    players[0] = new AI_Normal("iman", beads[0], 36);
                    Board board = new Board(beads);
                    Game game = new Game(board, players);
                    game.run();
                    return;}
                else {
                    System.out.println("try again");
                }}
            else if (choose == 3) {
                System.out.println("Player 1 ! please give your name ");
                String name = scanner.next();

                System.out.println("Player 2 ! please give player name");
                name = scanner.next();

                Player[] players = new Player[2];
                players[1] = new Human_player(name, beads[1], 36);
                players[0] = new Human_player(name, beads[0], 44);
                Board board = new Board(beads);
                Game game = new Game(board, players);
                game.run();
                return;
            } else {
                System.out.println("Wrong Input . Try Again !");
            }


        } else if (choose == 2) {
            System.out.println("Welcome To Tournoment !!!!!!!!!!!!!!!!!!!!" +
                    "\n if you want choosing  press 1" +
                    "\n if you want random press 2");
            choose = scanner.nextInt();
            System.out.println("How many players join in tournoment ?");
            int numberkol = scanner.nextInt();
            if (choose == 1) {
                Tournoment tournoment = new Tournoment(numberkol/2);
                for (int i = 0; i < numberkol/2; i++) {
                    System.out.println("Please give in pair players & give \"AI\" if you want artificial intelligence\n");
                    Player players[] = new Player[2];
                    Player[] playeres = new Player[2];
                    for (int s = 0; s < players.length; s++) {
                        String t = scanner.next();
                        if (t.equals("AI")) {
                            playeres[s] = new AI_Hard(t, new Bead(s));

                        } else {
                            playeres[s] = new Human_player(t, new Bead(s));
                        }
                    }
                    tournoment.addGame(i, playeres);
                }
                tournoment.run();
            } else if (choose == 2) {
                System.out.println("How many of players are real?\n");
                int numberreal = scanner.nextInt();
                ArrayList<Player> playersTournoment = new ArrayList<>();
                System.out.println("Please Enter name of players :\n");
                Bead[] beads1 = new Bead[numberkol];
                for (int i = 0; i < numberreal; i++) {
                    System.out.println("Please Enter name for player's :\n");
                    if(i%2==0) beads1[i] = new Bead(36);
                    else beads1[i] = new Bead(44);
                    playersTournoment.add(new Human_player(scanner.next(), beads[i]));
                }
                System.out.println("Please Enter name for AI's :\n");
                for (int j = 0; j < (numberkol - numberreal); j++) {
                    beads1[j + numberreal] = new Bead(j + numberreal);
                    playersTournoment.add(new AI_Hard(scanner.next(), beads1[j + numberreal]));
                }

                Tournoment tournoment = new Tournoment(numberkol, playersTournoment);
                tournoment.randome();
                tournoment.run();
            } else {

                System.out.println("Wrong Input . Try Again !");
            }
        } else {

            System.out.println("Wrong Input . Try Again !");
        }
    }
}
}
