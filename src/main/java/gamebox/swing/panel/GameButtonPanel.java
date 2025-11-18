package gamebox.swing.panel;

import gamebox.swing.components.RoundedButton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class GameButtonPanel extends JPanel{
    private static final String FONT_TITLE = "맑은 고딕";
    private static final int FONT_SIZE = 48;
    private static final int BUTTON_WIDTH = 560;
    private static final int BUTTON_HEIGHT = 134;
    private static final int GAME_2048_BUTTON_POSITION_X = 220;
    private static final int GAME_2048_BUTTON_POSITION_Y = 0;
    private static final int GAME_SAME_PIC_BUTTON_POSITION_X = 220;
    private static final int GAME_SAME_PIC_BUTTON_POSITION_Y = 164;

    private static final RoundedButton game2048Button = new RoundedButton("2048");
    private static final RoundedButton gameSamePicButton = new RoundedButton("같은 그림 찾기");

    public GameButtonPanel(){
        setBackground(Color.white);
        setLayout(null);
        setButton();
        addComponents();
    }

    private void setButton() {
        game2048Button.setBounds(
                GAME_2048_BUTTON_POSITION_X, GAME_2048_BUTTON_POSITION_Y,
                BUTTON_WIDTH, BUTTON_HEIGHT
        );
        gameSamePicButton.setBounds(
                GAME_SAME_PIC_BUTTON_POSITION_X, GAME_SAME_PIC_BUTTON_POSITION_Y,
                BUTTON_WIDTH, BUTTON_HEIGHT
        );

        game2048Button.setFont(new Font(FONT_TITLE, Font.BOLD, FONT_SIZE));
        gameSamePicButton.setFont(new Font(FONT_TITLE, Font.BOLD, FONT_SIZE));
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
