package com.unideb.epam;

import java.awt.*;

import java.awt.event.MouseEvent;
import java.util.Vector;

public class MainList extends Rectangle{

    String s;
    int x;
    int y;
    int width;
    int height;
    Boolean pressed = false;
    Boolean list_pressed = false;
    Voice current_voice;
    Vector<Voice> voice = new Vector<>();

    MainList(int x, int y, int width, int height, String s){
        super(x,y,width,height);
        this.x=x;
        this.y=y;
        this.width=width;
        this.height=height;
        this.s = s;
        basic_add();
    }


    public void draw(Graphics g){
        int font_size=12;
        Font font = new Font("Consoles", Font.BOLD, font_size);
        FontMetrics metrics = g.getFontMetrics(font);//this allows me draw the string at the middle
        if(pressed){
            g.setColor(new Color(170,170,170));
            g.fillRoundRect(x,y,width,(height/2+font_size/2)*(voice.size()+2),height,height);
            g.setColor(Color.white);
            g.fillRoundRect(x,y,width,height,height,height);
            g.setColor(new Color(10,10,10));
            g.drawString(s,x+width/2-metrics.stringWidth(s)/2,y+height/2+font_size/2);
            draw_list(g);
        }else{
            g.setColor(Color.white);
            g.drawRoundRect(x,y,width,height,height,height);
            g.setColor(Color.white);
            g.drawString(s,x+width/2-metrics.stringWidth(s)/2,y+height/2+font_size/2);

        }

    }

    public void draw_list(Graphics g){
        int font_size=12;
        Font font = new Font("Consoles", Font.BOLD, font_size);
        FontMetrics metrics = g.getFontMetrics(font);
        for(int i=0; i<voice.size();i++){

            g.setColor(new Color(170,170,170));
            voice.get(i).x = x+width/2-metrics.stringWidth(voice.get(i).name)/2;
            voice.get(i).y = y+height/2+(int)(font_size*1.5)+(height/2+font_size/2)*(i+1)-font_size;
            voice.get(i).width =  metrics.stringWidth(voice.get(i).name);
            voice.get(i).height = font_size;

            g.fillRect(voice.get(i).x,voice.get(i).y,voice.get(i).width,voice.get(i).height);
            g.setColor(new Color(10,10,10));
            g.drawString(voice.get(i).name,x+width/2-metrics.stringWidth(voice.get(i).name)/2,y+height/2+(int)(font_size*1.5)+(height/2+font_size/2)*(i+1));
        }
    }


    public void basic_add(){
        voice.add(new Voice("New",0,0,0));
        voice.add(new Voice("Robot Voice",100,10,90));
        voice.add(new Voice("Baby Voice",40,30,80));
        voice.add(new Voice("Radio",30,40,70));
        voice.add(new Voice("Female",20,50,60));
        voice.add(new Voice("Male",10,60,60));
    }


    public void mousePressed(MouseEvent mouseEvent) {
        pressed = this.contains( mouseEvent.getX(),mouseEvent.getY());

        for (Voice value : voice) {
            if (value.contains(mouseEvent.getX(), mouseEvent.getY())) {
                System.out.println("set to " + value.name);
                list_pressed = true;
                current_voice = value;
            }
            value.x=0;
            value.y=0;
        }
    }

}
