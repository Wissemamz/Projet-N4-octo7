package model;

import javax.swing.*;

public class RobotGraph extends JLabel {

    public RobotGraph(ImageIcon icon) {
        super(icon);
    }

    public void setPosition(int x, int y) {
        setLocation(x, y);
    }
}
