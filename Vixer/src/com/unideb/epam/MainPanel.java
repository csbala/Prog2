package com.unideb.epam;


import javax.sound.sampled.LineUnavailableException;
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
    CharacterList characterList;
    EffectList effectList;
    EnvironmentList environmentList;
    RMSBar rmsBar;
    Microphone microphone;
    BypassedButton button1;
    BypassedButton button2;
    BypassedButton button3;


    public MainPanel() throws LineUnavailableException {
        newCircleSlider();
        newLabel();
        newLine();
        newList();
        newRMSBar();
        newBypassesButton();
        this.setFocusable(true);
        this.setPreferredSize(SCREEN_SIZE);
        this.addMouseListener(new AL());
        this.addMouseMotionListener(new AL());

        microphone = new Microphone();
        microphone.open();

        mainThread = new Thread(this);
        mainThread.start();
    }

    public void run(){
        microphone.start();
        while(true){
           microphone.sampleRate = circleSlider1.progress1*100;
            microphone.readBytes = microphone.targetLine.read(microphone.data, 0, microphone.data.length);

            microphone.sourceLine.write(microphone.data, 0 ,microphone.readBytes);

            microphone.level = Microphone.calculateRMSLevel(microphone.data);
            rmsBar.level = microphone.level;
            repaint(rmsBar);


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
        characterList = new CharacterList(circleSlider1.x, (circleSlider1.y + (characterLabel.y + characterLabel.font_size)) / 2 - 30, circleSlider1.width, 30, "Character");
        effectList = new EffectList(circleSlider2.x, characterList.y, circleSlider2.width, 30, "Effect");
        environmentList = new EnvironmentList(circleSlider3.x, characterList.y, circleSlider3.width, 30, "Environment");

    }

    public void newRMSBar(){
        rmsBar = new RMSBar(0,(int)(MAIN_HEIGHT-BETWEEN_SLIDERS/2.3),MAIN_WIDTH,(int)(BETWEEN_SLIDERS/2.3));
    }

    public void newBypassesButton(){

        button1 = new BypassedButton(circleSlider1.x,characterLabel.y-characterLabel.font_size,20,20);
        button2 = new BypassedButton(circleSlider2.x,effectLabel.y-effectLabel.font_size,20,20);
        button3 = new BypassedButton(circleSlider3.x,environmentLabel.y-environmentLabel.font_size,20,20);

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
        button1.draw(g);
        button2.draw(g);
        button3.draw(g);
        topLabel.draw(g);
        characterLabel.draw(g);
        effectLabel.draw(g);
        environmentLabel.draw(g);
        line1.draw(g);
        line2.draw(g);
        rmsBar.draw(g);

        characterList.draw(g);
        effectList.draw(g);
        environmentList.draw(g);
        mainList.draw(g);


    }

    public class AL extends MouseAdapter implements MouseMotionListener {

        public void mouseEntered(MouseEvent mouseEvent){}

        public void mousePressed(MouseEvent mouseEvent){
            repaint();
            circleSlider1.mousePressed(mouseEvent);
            circleSlider2.mousePressed(mouseEvent);
            circleSlider3.mousePressed(mouseEvent);
            button1.mousePressed(mouseEvent);
            button2.mousePressed(mouseEvent);
            button3.mousePressed(mouseEvent);
            rmsBar.mousePressed(mouseEvent);
            //if(button1.bypassed){circleSlider1.opacity = 150;}else{circleSlider1.opacity = 255;}
           // if(button2.bypassed){circleSlider2.opacity = 150;}else{circleSlider2.opacity = 255;}
           // if(button3.bypassed){circleSlider3.opacity = 150;}else{circleSlider3.opacity = 255;}

            mainList.mousePressed(mouseEvent);
            if(mainList.list_pressed){
                setCurrentVoiceEffect();
            }
            characterList.mousePressed(mouseEvent);
            if(characterList.list_pressed){
                setCurrentCharacterVoice();
            }
            effectList.mousePressed(mouseEvent);
            if(effectList.list_pressed){
                setCurrentEffectVoice();
            }
            environmentList.mousePressed(mouseEvent);
            if(environmentList.list_pressed){
                setCurrentEnvironmentVoice();
            }

        }

        public void mouseReleased(MouseEvent mouseEvent){
            circleSlider1.scroll=false;
            circleSlider2.scroll=false;
            circleSlider3.scroll=false;
        }

        public void mouseDragged(MouseEvent mouseEvent) {
            repaint();
            circleSlider1.mouseDragged(mouseEvent);
            circleSlider2.mouseDragged(mouseEvent);
            circleSlider3.mouseDragged(mouseEvent);
            if(circleSlider1.contains(mouseEvent.getX(),mouseEvent.getY())){circleSlider1.scroll = true;}
            if(circleSlider2.contains(mouseEvent.getX(),mouseEvent.getY())){circleSlider2.scroll = true;}
            if(circleSlider3.contains(mouseEvent.getX(),mouseEvent.getY())){circleSlider3.scroll = true;}
        }

        public void mouseMoved(MouseEvent mouseEvent){}

    }


    public void setCurrentVoiceEffect(){

         circleSlider1.setProgress1(mainList.current_voice.progressbar1);
         circleSlider2.setProgress2(mainList.current_voice.progressbar2);
         circleSlider3.setProgress3(mainList.current_voice.progressbar3);
         mainList.list_pressed = false;
         mainList.s = mainList.current_voice.name;
    }

    public void setCurrentCharacterVoice(){
        circleSlider1.setProgress1(characterList.current_voice.progressbar1);
        characterList.list_pressed = false;
        characterList.s = characterList.current_voice.name;
    }
    public void setCurrentEffectVoice(){
        circleSlider2.setProgress2(effectList.current_voice.progressbar2);
        effectList.list_pressed = false;
        effectList.s = effectList.current_voice.name;
    }
    public void setCurrentEnvironmentVoice(){
        circleSlider3.setProgress3(environmentList.current_voice.progressbar3);
        environmentList.list_pressed = false;
        environmentList.s = environmentList.current_voice.name;
    }

}
