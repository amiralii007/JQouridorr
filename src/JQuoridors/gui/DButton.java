package JQuoridors.gui;

import javax.swing.*;
import java.awt.*;

public class DButton extends JButton {
    private int id;

    public DButton (int id){
        super();
        this.id = id;
        this.setBackground(Color.orange);
        this.setEnabled(false);
    }
}
