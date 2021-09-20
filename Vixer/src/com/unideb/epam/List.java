package com.unideb.epam;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;


public class List extends Rectangle{

    int font_size=12;
    String s;
    Boolean pressed = false;
    Label label;

    List(int x, int y, int width, int height, String s){
        super(x,y,width,height);
        this.s=s;

    }

    public void draw(Graphics g){
        Graphics2D g2 = (Graphics2D) g;
        label = new Label(s,x+width/2,(y+height/2+font_size/2),font_size,255);

        if(pressed){
          label.setColor(new Color(0,0,0));
          g2.setColor(new Color(255, 255, 255));
          g2.fillRoundRect(x, y, width, height, height, height);
      }else{
          label.setColor(new Color(255,255,255));
          g2.setStroke(new BasicStroke(2));
          g2.setColor(new Color(255, 255, 255));
          g2.drawRoundRect(x, y, width, height, height, height);
      }
        label.draw(g);
    }



    public void mousePressed(MouseEvent e) {
        if(this.contains(new Point(e.getX(),e.getY()))){
            pressed=true;
        }else{
            pressed=false;
        }

    }



}
