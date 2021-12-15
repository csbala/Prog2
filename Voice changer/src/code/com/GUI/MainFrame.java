package code.com.GUI;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    MainPanel panel;

    public MainFrame() {

        panel = new MainPanel();
        this.add(panel);
        this.setTitle("<Voice changer>");
        this.setResizable(false);
        this.setBackground(new Color(40,40,40));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();//adjust to MainPanel
        this.setVisible(true);
        this.setLocationRelativeTo(null);
    }
}
