package code.com.GUI;

import code.com.Audio.Microphone;
import code.com.GUI.Presets.*;


import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.LineUnavailableException;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

public class MainPanel extends JPanel implements Runnable {

    static final Dimension MY_SCREEN_SIZE = Toolkit.getDefaultToolkit().getScreenSize();
    static final int MAIN_WIDTH = (int)(MY_SCREEN_SIZE.getWidth() - MY_SCREEN_SIZE.getWidth() * 0.6);
    static final int MAIN_HEIGHT = (int)(MY_SCREEN_SIZE.getHeight() - MY_SCREEN_SIZE.getHeight() * 0.3);
    static final Dimension SCREEN_SIZE = new Dimension(MAIN_WIDTH,MAIN_HEIGHT);
    RussianMic russianMicButton;
    Megaphone megaphoneButton;
    DartWader dartWaderButton;
    CircleSlider circleSlider;
    Chipmunk chipmunkButton;
    Deafult defaultsButton;
    Microphone microphone;
    Custom customButton;
    Radio radioButton;
    Woman womanButton;
    Thread mainThread;
    Graphics graphics;
    Girl girlButton;
    Text text_pitch;
    RMSBar rmsBar;
    Man manButton;
    Boy boyButton;
    Image image;




    public MainPanel() {
        this.setFocusable(true);
        this.addMouseListener(new AL());
        this.setPreferredSize(SCREEN_SIZE);
        this.addMouseMotionListener(new AL());

        newCircleSlider();
        newButton();
        newRMSBar();
        newText();

        try {
            microphone = new Microphone();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }

        mainThread = new Thread(this);
        mainThread.start();
    }

    private void newButton(){
        int width = MAIN_WIDTH/5-13;
        int height = 80;
        manButton = new Man(10,300,width,height,"Man");
        womanButton = new Woman(width+20,300,width,height,"Woman");
        boyButton = new Boy(2*width+30,300,width,height,"Boy");
        girlButton = new Girl(3*width+40,300,width,height,"Girl");
        dartWaderButton = new DartWader(4*width+50,300,width,height,"Wader");
        chipmunkButton = new Chipmunk(10, 390,width,80,"Chipm.");
        russianMicButton = new RussianMic(width+20, 390, width, height,"RMic.");
        radioButton = new Radio(2*width+30, 390,width,height,"Radio");
        megaphoneButton = new Megaphone(3*width+40, 390,width,height,"MPhone.");
        defaultsButton = new Deafult(4*width+50, 390 ,width, height, "Default");
        customButton = new Custom(0,10,MAIN_WIDTH-1,40,"Custom Pitch");
    }

    private void newCircleSlider(){circleSlider = new CircleSlider(MAIN_WIDTH/2-200/2,60,200,200,255);}

    private void newRMSBar(){
        rmsBar = new RMSBar(0,MAIN_HEIGHT-50,MAIN_WIDTH-1,50);
    }

    private void newText(){
        text_pitch = new Text("Pitch", MAIN_WIDTH/2,165,20,255);
    }


    @Override
    public void run() {
        while(true){

            microphone.readBytes = microphone.targetLine.read(microphone.data, 0, microphone.data.length);

            effects();

            microphone.sourceLine.write(microphone.data, 0 ,microphone.readBytes);

            microphone.level = Microphone.calculateRMSLevel(microphone.data);
            rmsBar.level = microphone.level;
            repaint(rmsBar);

        }
    }

    private Boolean pitch =false;
    private Boolean distortion=false;

    void effects(){
        if(pitch){
            try {
                microphone.restart();
            } catch (LineUnavailableException e) {
                e.printStackTrace();
            }
            pitch=false;
        }

        if(distortion){
            for(int i=0;i<microphone.data.length;i++){
                microphone.data[i]=(byte)(microphone.data[i]*1.4);
            }

        }
    }

    public void paint(Graphics g){
        image = createImage(getWidth(),getHeight());
        graphics = image.getGraphics();
        draw(graphics);
        g.drawImage(image,0,0,this);

    }

    public void draw(Graphics g){
        customButton.draw(g);
        circleSlider.draw(g);
        rmsBar.draw(g);
        text_pitch.draw(g);
        manButton.draw(g);
        womanButton.draw(g);
        boyButton.draw(g);
        girlButton.draw(g);
        dartWaderButton.draw(g);
        chipmunkButton.draw(g);
        russianMicButton.draw(g);
        radioButton.draw(g);
        megaphoneButton.draw(g);
        defaultsButton.draw(g);
    }

    public class AL extends MouseAdapter implements MouseMotionListener {
        public void mouseEntered(MouseEvent mouseEvent){}

        public void mousePressed(MouseEvent mouseEvent){
            repaint();
            manButton.mousePressed(mouseEvent);
            if(manButton.pressed){microphone.setAudioFormat(AudioFormat.Encoding.PCM_SIGNED,53100,16,2,4,44100,false);pitch=true;distortion=false;}
            womanButton.mousePressed(mouseEvent);
            if(womanButton.pressed){microphone.setAudioFormat(AudioFormat.Encoding.PCM_SIGNED,39100,16,2,4,44100,false);pitch=true;distortion=false;}
            boyButton.mousePressed(mouseEvent);
            if(boyButton.pressed){microphone.setAudioFormat(AudioFormat.Encoding.PCM_SIGNED,42100,16,2,4,44100,false);pitch=true;distortion=false;}
            girlButton.mousePressed(mouseEvent);
            if(girlButton.pressed){microphone.setAudioFormat(AudioFormat.Encoding.PCM_SIGNED,37100,16,2,4,44100,false);pitch=true;distortion=false;}
            dartWaderButton.mousePressed(mouseEvent);
            if(dartWaderButton.pressed){microphone.setAudioFormat(AudioFormat.Encoding.PCM_SIGNED,64100,16,2,4,44100,false);pitch=true;distortion=true;}
            chipmunkButton.mousePressed(mouseEvent);
            if(chipmunkButton.pressed){microphone.setAudioFormat(AudioFormat.Encoding.PCM_SIGNED,30100,16,2,4,44100,false);pitch=true;distortion=false;}
            russianMicButton.mousePressed(mouseEvent);
            if(russianMicButton.pressed){microphone.setAudioFormat(AudioFormat.Encoding.PCM_SIGNED,49100,16,2,4,44100,false);pitch=true;distortion=true;}
            radioButton.mousePressed(mouseEvent);
            if(radioButton.pressed){microphone.setAudioFormat(AudioFormat.Encoding.PCM_SIGNED,40000,16,2,4,44100,false);pitch=true;distortion=true;}
            megaphoneButton.mousePressed(mouseEvent);
            if(megaphoneButton.pressed){microphone.setAudioFormat(AudioFormat.Encoding.PCM_SIGNED,44100,16,2,4,44100,false);pitch=true;distortion=false;}
            defaultsButton.mousePressed(mouseEvent);
            if(defaultsButton.pressed){microphone.setAudioFormat(AudioFormat.Encoding.PCM_SIGNED,44100,16,2,4,44100,false);pitch=true;distortion=false;}
            circleSlider.mousePressed(mouseEvent);

            customButton.mousePressed(mouseEvent);
            if(customButton.pressed){

              int SampleRate= (44100+(circleSlider.progress-50)*400);

                microphone.setAudioFormat(AudioFormat.Encoding.PCM_SIGNED,SampleRate,16,2,4,44100,false);pitch=true;distortion=false;
            }

        }

        public void mouseReleased(MouseEvent mouseEvent){
            circleSlider.scroll=false;
        }

        public void mouseDragged(MouseEvent mouseEvent) {
            repaint();
            circleSlider.mouseDragged(mouseEvent);
            if(circleSlider.contains(mouseEvent.getX(),mouseEvent.getY())){circleSlider.scroll = true;}
        }

        public void mouseMoved(MouseEvent mouseEvent){}

    }

}
