package code.com.GUI;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.geom.Arc2D;

public class CircleSlider extends Rectangle{

    public int progress = 50;
    public int progress_slide_value = progress*79/100;
    double e = width*0.04;
    int opacity;
    Boolean scroll = false;


    CircleSlider(int x, int y, int slider_width, int slider_height, int opacity){
        super(x,y,slider_width,slider_height);
        this.opacity = opacity;
    }

    int firstY = 0;
    int secondY = 0;

    public void mousePressed(MouseEvent e){ firstY = e.getY();  secondY = e.getY();}

    public void mouseDragged(MouseEvent e) {

                if(scroll){
                    firstY = e.getY();
                    if(firstY > secondY){
                        progress--;
                        progress_slide_value = progress*79/100;
                        if(progress < 0){progress=0;}
                    }
                    if(firstY < secondY){
                        progress++;
                        progress_slide_value = progress*79/100;
                        if(progress > 100){progress=100;}
                    }
                    secondY = e.getY();
                }

    }

    public void draw(Graphics g){
        double c = e/4;
        GradientPaint newColor;

        //outside circle
        g.setColor(new Color(0,0,0,opacity));
        Graphics2D g2 = (Graphics2D)g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.fillOval(x,y,width,height);

        //progressbar
        Arc2D.Float arc = new Arc2D.Float(Arc2D.PIE);
        arc.setFrame((int)(x+e/2),(int)(y+e/2),width-e,height-e);
        arc.setAngleStart(230);

        arc.setAngleExtent(-progress_slide_value * 3.6);
        newColor = new GradientPaint((int) (x + e / 2), (int) ((y + e / 2) + height / 2), new Color(0, 125, 255,opacity), (int) (x + e / 2) + width, (int) ((y + e / 2) + height / 2), new Color(0, 225, 130,opacity));
        drawSliderPercentage(g,progress);


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
