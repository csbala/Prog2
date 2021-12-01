package com.unideb.epam;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.geom.Arc2D;

public class CircleSlider extends Rectangle{

    int id;
    public int progress1 = 50;
    public int progress1_slide_value = progress1*79/100;
    public int progress2 = 50;
    public int progress2_slide_value = progress2*79/100;
    public int progress3 = 50;
    public int progress3_slide_value = progress3*79/100;
    double e = width*0.04;
    int opacity = 255;
    Boolean scroll = false;


    CircleSlider(int x, int y, int slider_width, int slider_height, int id, int opacity){
        super(x,y,slider_width,slider_height);
        this.id=id;
        this.opacity = opacity;
    }

    int firstY = 0;
    int secondY = 0;

    public void mousePressed(MouseEvent e){ firstY = e.getY();  secondY = e.getY();}

    public void mouseDragged(MouseEvent e) {

        switch (id){
            case 1:
                if(scroll){
                    firstY = e.getY();
                    if(firstY > secondY){
                        progress1--;
                        progress1_slide_value = progress1*79/100;
                        if(progress1 < 0){progress1=0;}
                    }
                    if(firstY < secondY){
                        progress1++;
                        progress1_slide_value = progress1*79/100;
                        if(progress1 > 100){progress1=100;}
                    }
                    secondY = e.getY();
                }
                break;
            case 2:
                if(scroll){
                    firstY = e.getY();
                    if(firstY > secondY){
                        progress2--;
                        progress2_slide_value = progress2*79/100;
                        if(progress2 < 0){progress2=0;}

                    }
                    if(firstY < secondY){
                        progress2++;
                        progress2_slide_value = progress2*79/100;
                        if(progress2 > 100){progress2=100;}
                    }
                    secondY = e.getY();
                }
                break;
            case 3:
                if(scroll){
                    firstY = e.getY();
                    if(firstY > secondY){
                        progress3--;
                        progress3_slide_value = progress3*79/100;
                        if(progress3 < 0){progress3=0;}
                    }
                    if(firstY < secondY){
                        progress3++;
                        progress3_slide_value = progress3*79/100;
                        if(progress3 > 100){progress3=100;}
                    }
                    secondY = e.getY();
                }
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + id);
        }
    }

    public void setProgress1(int progress1) {this.progress1 = progress1; progress1_slide_value = progress1*79/100;}
    public void setProgress2(int progress2) {this.progress2 = progress2; progress2_slide_value = progress2*79/100;}
    public void setProgress3(int progress3) {this.progress3 = progress3; progress3_slide_value = progress3*79/100;}


    public void draw(Graphics g){
        double c = e/4;
        GradientPaint newColor = null;

        //outside circle
        g.setColor(new Color(0,0,0,opacity));
       Graphics2D g2 = (Graphics2D)g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.fillOval(x,y,width,height);

        //progressbar
        Arc2D.Float arc = new Arc2D.Float(Arc2D.PIE);
        arc.setFrame((int)(x+e/2),(int)(y+e/2),width-e,height-e);
        arc.setAngleStart(230);

        switch (id) {
            case 1:
                arc.setAngleExtent(-progress1_slide_value * 3.6);
                newColor = new GradientPaint((int) (x + e / 2), (int) ((y + e / 2) + height / 2), new Color(0, 125, 255,opacity), (int) (x + e / 2) + width, (int) ((y + e / 2) + height / 2), new Color(0, 225, 130,opacity));
                drawSliderPercentage(g,progress1);
                break;
            case 2:
                arc.setAngleExtent(-progress2_slide_value * 3.6);
                newColor = new GradientPaint((int) (x + e / 2), (int) ((y + e / 2) + height / 2), new Color(255, 255, 0,opacity), (int) (x + e / 2) + width, (int) ((y + e / 2) + height / 2), new Color(230, 10, 0,opacity));
                drawSliderPercentage(g,progress2);
                break;
            case 3:
                arc.setAngleExtent(-progress3_slide_value * 3.6);
                newColor = new GradientPaint((int) (x + e / 2), (int) ((y + e / 2) + height / 2), new Color(143, 0, 245,opacity), (int) (x + e / 2) + width, (int) ((y + e / 2) + height / 2), new Color(240, 30, 0,opacity));
                drawSliderPercentage(g,progress3);
                break;
        }

        g2.setPaint(newColor);
        g2.fill(arc);

        //inside circle/image
        g.setColor(new Color(41,41,41));
        g2.fillOval((int)(x+e*c),(int)(y+e*c),(int)(width-e*2*c),(int)(height-2*e*c));
    }

    public void drawSliderPercentage(Graphics g, int progress){
        int font_size = 20;
        Font font = new Font("Consoles", Font.PLAIN, font_size);
        FontMetrics metrics = g.getFontMetrics(font);//this allows me draw the string at the middle
        g.setColor(new Color(255,255,255,opacity));
        g.setFont(font);
        g.drawString(progress + "%", x + width / 2 - metrics.stringWidth(progress + "")/2, (int) (y + height + e * 3));
    }



}
