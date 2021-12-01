package com.unideb.epam;

import java.awt.*;
import java.awt.event.MouseEvent;

public class BypassedButton extends Rectangle {
    int x;
    int y;
    int width;
    int height;
    Boolean bypassed = false;
    BypassedButton(int x, int y, int width, int height){
        super(x,y,width,height);
        this.x=x;
        this.y=y;
        this.width=width;
        this.height=height;
    }

    public void draw(Graphics g){
        g.setColor(new Color(255,255,255));
        g.fillRoundRect(x,y,width,height,height,height);
        if(bypassed){
            Font font = new Font("Consoles", Font.BOLD, 20);
            g.setFont(font);
            g.setColor(new Color(30,30,30,200));
            g.drawString("BYPASSED", (int)(x+width*2.4), (int)(y*2.3));
        }

    }

    public void mousePressed(MouseEvent mouseEvent){
        bypassed = this.contains( mouseEvent.getX(),mouseEvent.getY());

    }


}
