package code.com.GUI.Presets;

import code.com.GUI.Button;

import java.awt.*;
import java.awt.event.MouseEvent;

public class Woman extends Button {

    public Woman(int x, int y, int width, int height, String text) {
        super(x, y, width, height, text);
    }


    public void draw(Graphics g) {
        super.draw(g);
    }


    public void mousePressed(MouseEvent mouseEvent) {
        pressed = this.contains( mouseEvent.getX(),mouseEvent.getY());
    }
}
