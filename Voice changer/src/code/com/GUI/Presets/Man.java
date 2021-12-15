package code.com.GUI.Presets;

import code.com.Audio.Microphone;
import code.com.GUI.Button;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.LineUnavailableException;
import java.awt.*;
import java.awt.event.MouseEvent;

public class Man extends Button {

    Microphone microphone;

    {
        try {
            microphone = new Microphone();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    public Man(int x, int y, int width, int height, String text) {
        super(x, y, width, height, text);
    }


    public void draw(Graphics g) {
        super.draw(g);
    }


    public void mousePressed(MouseEvent mouseEvent) {
        pressed = this.contains( mouseEvent.getX(),mouseEvent.getY());

    }
}
