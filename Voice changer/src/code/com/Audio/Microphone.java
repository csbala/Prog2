package code.com.Audio;

import javax.sound.sampled.*;


public class Microphone {

    private AudioFormat format = null;
    public DataLine.Info info = new DataLine.Info(TargetDataLine.class, format);
    public TargetDataLine targetLine = (TargetDataLine) AudioSystem.getLine(info);


    public AudioFormat format2 = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED,44100,16,2,4,44100,false);
    public  DataLine.Info info2 = new DataLine.Info(SourceDataLine.class, format2);
    public final SourceDataLine sourceLine = (SourceDataLine) AudioSystem.getLine(info2);


    public  byte[] data = new byte[targetLine.getBufferSize() / 5];
    public int readBytes;
    public int level;
    

    public Microphone() throws LineUnavailableException {   //getline

    }

    public void setAudioFormat(AudioFormat.Encoding encoding, int sampleRate, int sampleSizeInBits,int channels,int frameSize,int frameRate, boolean bigEndian){
        format = new AudioFormat(encoding,sampleRate,sampleSizeInBits,channels,frameSize,frameRate,bigEndian);
        Setinfo();
    }

    public void Setinfo(){info = new DataLine.Info(TargetDataLine.class, format);SetTargetDataLine();}

    public void SetTargetDataLine(){

        try {
            targetLine = (TargetDataLine) AudioSystem.getLine(info);
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    public void start(){

        targetLine.start();
        sourceLine.start();
    }

    public void open() throws LineUnavailableException {

        targetLine.open();
        sourceLine.open();
    }

    public void close(){

        targetLine.close();
        sourceLine.close();
    }

    public void flush(){

        targetLine.flush();
        sourceLine.flush();
    }

    public void restart() throws LineUnavailableException {

        flush();
        close();
        open();
        start();
    }

    public static int calculateRMSLevel(byte[] audioData){

        double lSum = 0;
        for (byte audioDatum : audioData) lSum = lSum + audioDatum;

        double dAvg = lSum / audioData.length;
        double sumMeanSquare = 0d;

        for (byte audioDatum : audioData) sumMeanSquare += Math.pow(audioDatum - dAvg, 2d);

        double averageMeanSquare = sumMeanSquare / audioData.length;

        return (int)(Math.pow(averageMeanSquare,0.5d) + 0.5);
    }

}
