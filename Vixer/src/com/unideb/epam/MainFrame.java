package com.unideb.epam;

import javax.sound.sampled.*;
import javax.sound.sampled.Line;
import javax.swing.*;
import java.awt.*;


public class MainFrame extends JFrame {

    MainPanel panel;

    MainFrame() throws LineUnavailableException {
        panel = new MainPanel();
        this.add(panel);
        this.setTitle(getTitle());
        this.setResizable(false);
        this.setBackground(new Color(40,40,40));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();//adjust to MainPanel
        this.setVisible(true);
        this.setLocationRelativeTo(null);
    }

    public String getTitle(){
        //Get the current microphone name for the tittle
        Mixer.Info[] infoMixers = AudioSystem.getMixerInfo();
        String tittle_name = "";
        for (Mixer.Info infoMixer : infoMixers) {
            // Get mixer for each info
            Mixer mixer = AudioSystem.getMixer(infoMixer);

            // Check if is input device
            Line.Info[] lines = mixer.getTargetLineInfo();
            if (lines.length > 0) {
                for (Line.Info line : lines) {
                    if (line.getLineClass().equals(TargetDataLine.class)) {
                        // System.out.println("LINE INFO: " + line + ".............. " + infoMixer.getName());
                        tittle_name = infoMixer.getName();
                    }
                }
            }
        }
        return tittle_name;
    }
}

