package com.unideb.epam;

import java.awt.*;

public class Line extends Rectangle {

    Point p1;
    Point p2;

    Line(Point p1, Point p2){
        this.p1 = p1;
        this.p2 = p2;

    }

    public void draw(Graphics g){
        Graphics2D g2 = (Graphics2D)g;
        g2.setStroke(new BasicStroke(3));
        g2.setColor(new Color(0,0,0));
        g2.drawLine(p1.x,p1.y,p2.x, p2.y);
    }

}
