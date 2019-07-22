package JQuoridors.gui;

import JQuoridors.Action;
import JQuoridors.Move;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PButton extends JButton implements ActionListener {
    private int id;
    private static Action action = null;

    public PButton (int id){
        super();
        this.id = id;
        this.setBackground(new Color(0xE6E6E6));
        this.setEnabled(true);
        this.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        this.setBackground(Color.red);
        action = new Move(id);
    }

    public static Action play(){
        return action;
    }

    public static void reset (){
        action = null;
    }
}
