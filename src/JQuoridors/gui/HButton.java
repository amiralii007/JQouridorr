package JQuoridors.gui;

import JQuoridors.Action;
import JQuoridors.Block;
import JQuoridors.Move;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HButton extends JButton implements ActionListener {
    private int id;
    private static Action action = null;
    private static int fStick = Integer.MIN_VALUE;
    private static int sStick = Integer.MIN_VALUE;

    public HButton (int id){
        super();
        this.id = id;
        this.setBackground(Color.orange);
        this.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        this.setBackground(Color.red);
        if (fStick <= 0){
            fStick = id;
        }
        else {
            sStick = id;
        }
    }

    public static Action play(){
        if (fStick >= 0 && sStick >= 0)
            action = new Block(fStick, sStick);
        return action;
    }

    public static void reset(){
        action = null;
        fStick = Integer.MIN_VALUE;
        sStick = Integer.MIN_VALUE;
    }
}
