package com.unideb.epam;

import javax.sound.sampled.*;


public class Microphone {

    public int sampleRate = 54100;
    public  double pitch =  0.85;
    public AudioFormat format = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED, 64100,16,2,4,sampleRate,false);

    public DataLine.Info info = new DataLine.Info(TargetDataLine.class, format);
    public TargetDataLine targetLine = (TargetDataLine) AudioSystem.getLine(info);

    public AudioFormat format2 = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED,54100,16,2,4,sampleRate,false);
    public  DataLine.Info info2 = new DataLine.Info(SourceDataLine.class, format2);
    public final SourceDataLine sourceLine = (SourceDataLine) AudioSystem.getLine(info2);


    public  byte[] data = new byte[targetLine.getBufferSize() / 5];
    public int readBytes;
    public int level;


    public Microphone() throws LineUnavailableException {

    }

    public void start(){
        targetLine.start();
        sourceLine.start();
    }

    public void open() throws LineUnavailableException {
        targetLine.open();
        sourceLine.open();
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
