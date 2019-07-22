package JQuoridors;

public class Reporter {

    public static void showBoard(Board board){
        System.out.flush();
        for (int i = 0; i < 9; i++){
            for (int j = 0; j < 9; j++){
                int pos = (i * 9) + j;
                boolean isBead = false;
                for (int b = 0; b < board.getBeads().length; b++){
                    if (board.getBeads()[b].getPosition() == pos) {
                        System.out.print("#" + board.getBeads()[b].getId());
                        isBead = true;
                    }
                }
                if (!isBead)
                    showFormattedNum(pos);
                if (board.getVStick()[pos])
                    System.out.print("|");
                else
                    System.out.print(" ");
            }
            System.out.print("\n");
            for (int j = 0; j < 9; j++){
                int pos = (i * 9) + j;
                if (board.getHStick()[pos])
                    System.out.print("--");
                else
                    System.out.print("  ");
                try {
                    if (board.getVStick()[pos] && board.getVStick()[pos + 9])
                        System.out.print(".");
                    else if (board.getHStick()[pos] && board.getHStick()[pos + 1])
                        System.out.print(".");
                    else
                        System.out.print(" ");
                } catch (Exception e){}
            }
            System.out.print("\n");
        }
        System.out.println("\n\n");
    }

    private static void showFormattedNum (int a){
        if (a < 10)
            System.out.print("0" + a);
        else
            System.out.print(a);
    }

}
