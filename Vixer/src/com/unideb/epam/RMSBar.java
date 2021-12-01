package com.unideb.epam;

import java.awt.*;
import java.awt.event.MouseEvent;

public class RMSBar extends Rectangle {
    int x;
    int y;
    int width;
    int height;
    int level = 10;
    public RMSBar(int x, int y, int width, int height){
        super(x,y,width,height);
            this.x=x;
            this.y=y;
            this.width=width;
            this.height=height;
    }

    public void draw(Graphics g){
       g.setColor(Color.white);
       g.fillRect(x,y,width,height);
        g.setColor(new Color(170,170,170));
        int barWidth =(int)(width/1.2);
        g.fillRect((width-barWidth)/2,y+height/2,barWidth,5);
        g.setColor(new Color(40,40,40));
        int percent = level * barWidth / 100;
        g.fillRect((width-barWidth)/2,y+height/2, percent,5);
        g.setColor(new Color(0,200,0));
    }

    public void mousePressed(MouseEvent mouseEvent){


    }


    }

