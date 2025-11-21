package gamebox.swing.panel;

import gamebox.swing.components.GameColors;
import gamebox.swing.components.RoundedButton;
import gamebox.swing.panel.constants.PanelNumber;
import gamebox.swing.panel.constants.PanelString;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class GameButtonPanel extends JPanel{
    private static final RoundedButton game2048Button = new RoundedButton(PanelString.GAME_2048_TITLE);
    private static final RoundedButton gameSamePicButton = new RoundedButton(PanelString.GAME_SAME_PIC_TITLE);

    public GameButtonPanel(){
        setBackground(GameColors.beige);
        setLayout(null);
        setButton();
        addComponents();
    }

    private void setButton() {
        game2048Button.setBounds(
                PanelNumber.GAME_2048_BUTTON_POSITION_X, PanelNumber.GAME_2048_BUTTON_POSITION_Y,
                PanelNumber.BUTTON_WIDTH, PanelNumber.BUTTON_HEIGHT
        );
        gameSamePicButton.setBounds(
                PanelNumber.GAME_SAME_PIC_BUTTON_POSITION_X, PanelNumber.GAME_SAME_PIC_BUTTON_POSITION_Y,
                PanelNumber.BUTTON_WIDTH, PanelNumber.BUTTON_HEIGHT
        );

        game2048Button.setFont(new Font(PanelString.FONT, Font.BOLD, PanelNumber.FONT_SIZE));
        gameSamePicButton.setFont(new Font(PanelString.FONT, Font.BOLD, PanelNumber.FONT_SIZE));
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
