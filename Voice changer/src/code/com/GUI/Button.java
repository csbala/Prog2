package code.com.GUI;

import java.awt.*;


public class Button extends Rectangle {

    private final int x;
    private final int y;
    private final int width;
    private final int height;
    private final String text;
    protected Boolean pressed = false;
    protected int font_size=20;

    public Button(int x, int y, int width, int height, String text) {
        super(x,y,width,height);
        this.x=x;
        this.y=y;
        this.width=width;
        this.height=height;
        this.text=text;
    }

    public void draw(Graphics g){
        Font font = new Font("Consoles", Font.BOLD, font_size);
        FontMetrics metrics = g.getFontMetrics(font);//this allows me draw the string at the middle
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
        g.setFont(font);
        g.drawString(text,x+width/2-metrics.stringWidth(text)/2,y+height/2+font_size/2);
    }
}
