package com.unideb.epam;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MainPanel extends JPanel implements Runnable{

    static final Dimension MY_SCREEN_SIZE = Toolkit.getDefaultToolkit().getScreenSize();
    static final int MAIN_WIDTH = (int)(MY_SCREEN_SIZE.getWidth() - MY_SCREEN_SIZE.getWidth() * 0.3);
    static final int MAIN_HEIGHT = (int)(MY_SCREEN_SIZE.getHeight() - MY_SCREEN_SIZE.getHeight() * 0.3);
    static final Dimension SCREEN_SIZE = new Dimension(MAIN_WIDTH,MAIN_HEIGHT);
    static final int SLIDER_WIDTH = (int)(MAIN_HEIGHT*0.4);
    static final int SLIDER_HEIGHT = (int)(MAIN_HEIGHT*0.4);
    static final int BETWEEN_SLIDERS = (MAIN_WIDTH-3*SLIDER_WIDTH)/4;
    Thread mainThread;
    Image image;
    Graphics graphics;
    CircleSlider circleSlider1;//left
    CircleSlider circleSlider2;//middle
    CircleSlider circleSlider3;//right
    Label topLabel;
    Label environmentLabel;
    Label effectLabel;
    Label characterLabel;
    Line line1;//left line
    Line line2;//right line
    MainList mainList;





    MainPanel(){
        newCircleSlider();
        newLabel();
        newLine();
        newList();
        this.setFocusable(true);
        this.setPreferredSize(SCREEN_SIZE);
        this.addMouseListener(new AL());
        this.addMouseMotionListener(new AL());


        mainThread = new Thread(this);
        mainThread.start();
    }


    public void run(){
        //basic game loop
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        while(true){
            long now = System.nanoTime();
            delta += (now -lastTime) / ns;
            lastTime = now;
            if(delta >= 1){
                repaint();
                delta--;

            }
        }
    }

    public void newCircleSlider(){
        circleSlider1 = new CircleSlider(BETWEEN_SLIDERS, MAIN_HEIGHT - SLIDER_HEIGHT - BETWEEN_SLIDERS, SLIDER_WIDTH, SLIDER_HEIGHT,1,255);
        circleSlider2 = new CircleSlider(2*BETWEEN_SLIDERS+SLIDER_WIDTH,MAIN_HEIGHT - SLIDER_HEIGHT - BETWEEN_SLIDERS,SLIDER_WIDTH, SLIDER_HEIGHT,2,255);
        circleSlider3 = new CircleSlider(3*BETWEEN_SLIDERS+2*SLIDER_WIDTH,MAIN_HEIGHT - SLIDER_HEIGHT - BETWEEN_SLIDERS,SLIDER_WIDTH, SLIDER_HEIGHT,3,255);

    }

    public void newLabel(){
        topLabel = new Label("Voice Changer",MAIN_WIDTH/2,20,15,255);
       characterLabel = new Label("CHARACTER",BETWEEN_SLIDERS+SLIDER_WIDTH/2,MAIN_HEIGHT-BETWEEN_SLIDERS*2-SLIDER_HEIGHT,12,100);
        environmentLabel = new Label("EFFECT",2*BETWEEN_SLIDERS+SLIDER_WIDTH+SLIDER_WIDTH/2,MAIN_HEIGHT-BETWEEN_SLIDERS*2-SLIDER_HEIGHT,12,100);
        effectLabel = new Label("ENVIRONMENT",3*BETWEEN_SLIDERS+2*SLIDER_WIDTH+SLIDER_WIDTH/2,MAIN_HEIGHT-BETWEEN_SLIDERS*2-SLIDER_HEIGHT,12,100);

    }

    public void newLine(){
        line1 = new Line(new Point(BETWEEN_SLIDERS+SLIDER_WIDTH+BETWEEN_SLIDERS/2,MAIN_HEIGHT - SLIDER_HEIGHT - BETWEEN_SLIDERS/2),new Point(BETWEEN_SLIDERS+SLIDER_WIDTH+BETWEEN_SLIDERS/2,MAIN_HEIGHT -BETWEEN_SLIDERS-SLIDER_HEIGHT/8));
        line2 = new Line(new Point(BETWEEN_SLIDERS*2+SLIDER_WIDTH*2+BETWEEN_SLIDERS/2,MAIN_HEIGHT - SLIDER_HEIGHT - BETWEEN_SLIDERS/2),new Point(BETWEEN_SLIDERS*2+SLIDER_WIDTH*2+BETWEEN_SLIDERS/2,MAIN_HEIGHT -BETWEEN_SLIDERS-SLIDER_HEIGHT/8));

    }

    public void newList() {
        mainList = new MainList(MAIN_WIDTH / 2 - (int) (SLIDER_WIDTH * 1.4) / 2, (effectLabel.y + topLabel.y + topLabel.font_size) / 2 - 40, (int) (SLIDER_WIDTH * 1.4), 40, "MAINLIST");
       // characterList = new List(circleSlider1.x, (circleSlider1.y + (characterLabel.y + characterLabel.font_size)) / 2 - 30, circleSlider1.width, 30, "Character");
       // effectList = new List(circleSlider2.x, characterList.y, circleSlider2.width, 30, "Effect");
       // environmentList = new List(circleSlider3.x, characterList.y, circleSlider3.width, 30, "Environment");

    }


    public void paint(Graphics g){
        image = createImage(getWidth(),getHeight());
        graphics = image.getGraphics();
        draw(graphics);
        g.drawImage(image,0,0,this);

    }

    public void draw(Graphics g){
        circleSlider1.draw(g);
        circleSlider2.draw(g);
        circleSlider3.draw(g);
        topLabel.draw(g);
        characterLabel.draw(g);
        effectLabel.draw(g);
        environmentLabel.draw(g);
        line1.draw(g);
        line2.draw(g);
        mainList.draw(g);



    }

    public class AL extends MouseAdapter implements MouseMotionListener {

        public void mouseEntered(MouseEvent mouseEvent){}

        public void mousePressed(MouseEvent mouseEvent){

            circleSlider1.mousePressed(mouseEvent);
            circleSlider2.mousePressed(mouseEvent);
            circleSlider3.mousePressed(mouseEvent);
            mainList.mousePressed(mouseEvent);
            if(mainList.list_pressed){
                setCurrentVoice();
            }


        }

        public void mouseReleased(MouseEvent mouseEvent){}

        public void mouseDragged(MouseEvent mouseEvent) {

            circleSlider1.mouseDragged(mouseEvent);
            circleSlider2.mouseDragged(mouseEvent);
            circleSlider3.mouseDragged(mouseEvent);

        }

        public void mouseMoved(MouseEvent mouseEvent){}

    }


public void setCurrentVoice(){
    circleSlider1.setProgress1(mainList.current_voice.progressbar1);
    circleSlider2.setProgress2(mainList.current_voice.progressbar2);
    circleSlider3.setProgress3(mainList.current_voice.progressbar3);
    mainList.list_pressed = false;
    mainList.s = mainList.current_voice.name;
}


}
