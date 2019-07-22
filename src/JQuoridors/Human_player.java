package JQuoridors;

import JQuoridors.gui.GUI;

import java.util.Scanner;

public class Human_player extends Player {

    Human_player(String name, Bead bead, int firstPlace) {
        super(name, bead, firstPlace);
    }
    Human_player(String name, Bead bead) { super(name, bead); }


    @Override
    public void logicalComputing(Board board, Player player) {

    }

    @Override
    public Action play(GUI gui) {
        Action action = null;
        while (action == null){
            action = gui.play();
            System.out.print("");
        }
        return action;
//////////////////////////////////////////////////////////////////
//        Scanner input = new Scanner(System.in);
//        while (true){
//            try{
//                System.out.println("Enter \"move\" For Move And \"stick\" For Place a Block:");
//                String c = input.next().toLowerCase();
//                if (c.equals("move")) {
//                    System.out.println("number of cell: ");
//                    return new Move(input.nextInt());
//                }
//                else if (c.equals(("stick"))) {
//                    while (true){
//                        System.out.println("Enter 2 place for putting a stick there: ");
//                        return new Block(input.nextInt(),input.nextInt());
//                    }
//                }
//                else
//                    System.out.println("Wrong command!");
//            }catch (Exception e){}
//        }
    }
}
