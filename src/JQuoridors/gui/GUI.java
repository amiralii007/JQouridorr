package JQuoridors.gui;

import JQuoridors.Action;
import JQuoridors.Board;

import javax.swing.*;
import java.awt.*;

public class GUI extends JFrame{
    JPanel panel = new JPanel();
    private PButton [] place = new PButton[81];
    private HButton [] hStick = new HButton[81];
    private VButton [] vStick = new VButton[81];
    private DButton [] dots = new DButton[81];

    public GUI (){
        super("JQuoridor");
        setSize(800,800);
        setResizable(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        panel.setLayout(new GridLayout(17, 17));
        for (int j = 0 ; j < 9; j++){
            for (int i = 0 ; i < 9; i++){
                place[(j * 9) + i] = new PButton((j * 9) + i);
                panel.add(place[(j * 9) + i]);
                if (i == 8)
                    break;
                vStick[(j * 9) + i] = new VButton((j * 9) + i);
                panel.add(vStick[(j * 9) + i]);
            }
            for (int i = 0 ; i < 9; i++){
                if (j == 8)
                    break;
                hStick[(j * 9) + i] = new HButton((j * 9) + i);
                panel.add(hStick[(j * 9) + i]);
                if (i == 8)
                    break;
                dots[(j * 9) + i] = new DButton((j * 9) + i);
                panel.add(dots[(j * 9) + i]);
            }
        }
        for (int i = 0; i < 81; i++){
            if (hStick[i] == null)
                hStick[i] = new HButton(-1);
            if (vStick[i] == null)
                vStick[i] = new VButton(-1);
            if (dots[i] == null)
                dots[i] = new DButton(-1);
        }
        add(panel);
        setVisible(true);
    }

    public void update(Board board){
        for (int i = 0; i < 81; i++){
            if (board.getBeads()[0].getPosition() == i)
                place[i].setBackground(Color.green);
            else if (board.getBeads()[1].getPosition() == i)
                place[i].setBackground(Color.magenta);
            else
                place[i].setBackground(Color.white);

            if (board.getVStick()[i])
                vStick[i].setBackground(Color.black);
            else
                vStick[i].setBackground(Color.orange);

            if (board.getHStick()[i])
                hStick[i].setBackground(Color.black);
            else
                hStick[i].setBackground(Color.orange);

            if (board.getDots()[i])
                dots[i].setBackground(Color.black);
            else
                dots[i].setBackground(Color.orange);
        }
    }

    public Action play(){
        Action action = null;
        if (PButton.play() != null) {
            action = PButton.play();
            this.reset_input();
            return action;
        }
        else if (VButton.play() != null) {
            action = VButton.play();
            this.reset_input();
            return action;
        }
        else if (HButton.play() != null) {
            action = HButton.play();
            this.reset_input();
            return action;
        }
        else {
            return null;
        }
    }

    public void reset_input (){
        PButton.reset();
        VButton.reset();
        HButton.reset();
    }
}
