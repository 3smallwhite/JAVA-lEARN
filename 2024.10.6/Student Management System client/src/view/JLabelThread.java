package view;

import javax.swing.*;
import java.awt.*;

public class JLabelThread extends Thread {
    private JLabel jLabel;
    private String warning;

    public JLabelThread(JLabel jLabel, String warning) {
        this.jLabel = jLabel;
        this.warning = warning;
        start();
    }

    @Override
    public void run() {
        jLabel.setText(warning);
        jLabel.setForeground(Color.red);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        jLabel.setText("");
        jLabel.repaint();
    }
}
