package code.com.GUI;

import java.awt.*;

public class Text extends Rectangle {
    String s;
    int font_size;
    int x;
    int y;
    int opacity = 255;
    Color color = new Color(255,255,255,255);

    Text(String s, int x, int y, int font_size,int opacity){
        this.s = s;
        this.x = x;
        this.y =y;
        this.font_size = font_size;
        this.opacity = opacity;
    }

    public void setColor(Color color) {
        this.color = color;
    }
    public Color getColor() {
        return color;
    }

    public void draw(Graphics g){
        Font font = new Font("Consoles", Font.BOLD, font_size);
        FontMetrics metrics = g.getFontMetrics(font);//this allows me draw the string at the middle
        g.setColor(getColor());
        g.setFont(font);

        g.drawString(s,x-metrics.stringWidth(s)/2,y);
    }

}
