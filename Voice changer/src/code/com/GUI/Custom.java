package code.com.GUI;

import code.com.Audio.Microphone;

import javax.sound.sampled.LineUnavailableException;
import java.awt.*;
import java.awt.event.MouseEvent;

public class Custom extends Rectangle {
    private int x;
    private int y;
    private int width;
    private int height;
    private String text;
    protected Boolean pressed = false;
    protected int font_size=20;
    Microphone microphone;

    {
        try {
            microphone = new Microphone();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    public Custom(int x, int y, int width, int height, String text) {
        super(x,y,width,height);
        this.x=x;
        this.y=y;
        this.width=width;
        this.height=height;
        this.text=text;

    }


    public void draw(Graphics g) {
        if(pressed){
            g.setColor(new Color(170,170,170));
            g.setColor(Color.white);
            g.fillRect(x,y,width,height);
            g.setColor(new Color(10,10,10));
        }else{
            g.setColor(Color.white);
            g.drawRect(x,y,width,height);
            g.setColor(Color.white);

        }
        Font font = new Font("Consoles", Font.BOLD, font_size);
        FontMetrics metrics = g.getFontMetrics(font);//this allows me draw the string at the middle
        g.setFont(font);
        g.drawString(text,x+width/2-metrics.stringWidth(text)/2,y+height/2+font_size/2);

    }


    public void mousePressed(MouseEvent mouseEvent) {
        pressed = this.contains( mouseEvent.getX(),mouseEvent.getY());
    }
}
