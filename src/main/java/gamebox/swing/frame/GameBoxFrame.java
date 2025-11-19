package gamebox.swing.frame;

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
        setTitle(FrameString.APP_NAME.getValue());
        setSize(FrameNumber.WINDOW_WIDTH.getValue(), FrameNumber.WINDOW_HEIGHT.getValue());
        setResizable(false);
        setBackground(Color.white);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
}