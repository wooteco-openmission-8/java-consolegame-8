package gamebox.swing.frame;

import gamebox.swing.frame.constants.FrameNumber;
import gamebox.swing.frame.constants.FrameString;
import gamebox.swing.panel.MainPanel;
import javax.swing.*;
import java.awt.*;

public class GameBoxFrame extends JFrame {
    private final MainPanel mainPanel = new MainPanel();

    public GameBoxFrame(){
        setInit();
        add(mainPanel);
        setVisible(true);
    }

    private void setInit(){
        setTitle(FrameString.APP_NAME);
        setSize(FrameNumber.WINDOW_WIDTH, FrameNumber.WINDOW_HEIGHT);
        setResizable(false);
        setBackground(Color.white);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
}