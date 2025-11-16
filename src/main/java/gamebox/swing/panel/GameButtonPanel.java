package gamebox.swing.panel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class GameButtonPanel extends JPanel{
    private static final int BUTTON_WIDTH = 150;
    private static final int BUTTON_HEIGHT = 80;
    private static final int GAME_2048_BUTTON_POSITION_X = 425;
    private static final int GAME_2048_BUTTON_POSITION_Y = 100;
    private static final int GAME_SAME_PIC_BUTTON_POSITION_X = 425;
    private static final int GAME_SAME_PIC_BUTTON_POSITION_Y = 200;

    private static final JButton game2048Button = new JButton("2048");
    private static final JButton gameSamePicButton = new JButton("같은 그림 찾기");

    public GameButtonPanel(){
        setBackground(Color.white);
        setLayout(null);
        setButtonSize();
        setButtonLocation();
        addComponents();
    }

    private void setButtonSize(){
        game2048Button.setSize(BUTTON_WIDTH, BUTTON_HEIGHT);
        gameSamePicButton.setSize(BUTTON_WIDTH, BUTTON_HEIGHT);
    }

    private void setButtonLocation(){
        game2048Button.setLocation(GAME_2048_BUTTON_POSITION_X, GAME_2048_BUTTON_POSITION_Y);
        gameSamePicButton.setLocation(GAME_SAME_PIC_BUTTON_POSITION_X, GAME_SAME_PIC_BUTTON_POSITION_Y);
    }

    private void addComponents(){
        add(game2048Button);
        add(gameSamePicButton);
    }

    public void addGameButtonListener(ActionListener listener){
        game2048Button.addActionListener(listener);
        gameSamePicButton.addActionListener(listener);
    }
}
