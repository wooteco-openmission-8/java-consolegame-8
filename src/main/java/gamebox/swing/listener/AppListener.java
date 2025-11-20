package gamebox.swing.listener;

import gamebox.swing.listener.constants.ListenerNumber;
import gamebox.swing.listener.constants.ListenerString;
import gamebox.swing.panel.*;
import gamebox.swing.panel.constants.PanelString;
import gamebox.swing.swing_util.SwingUtils;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AppListener implements ActionListener {
    private final MainPanel mainPanel;
    private final JPanel contentPanel;
    private final BackgroundPanel backgroundPanel; // 추가
    private final HeaderPanel headerPanel;

    public AppListener(MainPanel mainPanel, JPanel contentPanel, BackgroundPanel backgroundPanel, HeaderPanel headerPanel){
        this.mainPanel = mainPanel;
        this.contentPanel = contentPanel;
        this.backgroundPanel = backgroundPanel;
        this.headerPanel = headerPanel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton source = (JButton) e.getSource();
        String selectedButton = source.getText();

        if (selectedButton.equals(PanelString.GAME_2048_TITLE)) {
            openGame2048();
        }
        if (selectedButton.equals(PanelString.GAME_SAME_PIC_TITLE)) {
            openGameSamePic();
        }
        if (selectedButton.equals(PanelString.HOME_BUTTON_TITLE)) {
            goBackToHome();
        }
        if (selectedButton.equals(PanelString.DIFFICULTY_BUTTON_TITLE)) {
            addListener(e);
        }

        SwingUtils.refresh(contentPanel);
    }

    private void openGame2048() {
        mainPanel.remove(backgroundPanel);
        mainPanel.addHeaderPanel();
        mainPanel.set2048Contents();

        contentPanel.removeAll();
        setGamePanelBounds();
        Game2048Panel game2048Panel = new Game2048Panel();
        game2048Panel.setResetButton(headerPanel.getResetButton());
        contentPanel.add(game2048Panel);

        SwingUtils.refresh(mainPanel);
    }

    private void openGameSamePic() {
        mainPanel.remove(backgroundPanel);
        mainPanel.addHeaderPanel();
        mainPanel.setSelectDifficultyContents();

        contentPanel.removeAll();
        setGamePanelBounds();
        contentPanel.add(new GameSamePicPanel(headerPanel));

        SwingUtils.refresh(mainPanel);
    }

    private void setGamePanelBounds() {
        contentPanel.setBounds(
                ListenerNumber.GAME_PANEL_POSITION_X, ListenerNumber.GAME_PANEL_POSITION_Y,
                ListenerNumber.GAME_PANEL_WIDTH, ListenerNumber.GAME_PANEL_HEIGHT
        );
    }

    private void goBackToHome() {
        mainPanel.removeHeader();
        mainPanel.add(backgroundPanel);

        contentPanel.removeAll();
        contentPanel.setBounds(
                ListenerNumber.GAME_BUTTON_PANEL_POSITION_X, ListenerNumber.GAME_BUTTON_PANEL_POSITION_Y,
                ListenerNumber.GAME_BUTTON_PANEL_WIDTH, ListenerNumber.GAME_BUTTON_PANEL_HEIGHT
        );
        contentPanel.add(new GameButtonPanel());
        SwingUtils.refresh(mainPanel);
    }

    private void addListener(ActionEvent e) {
        GameListener listener = new GameListener(
                mainPanel,
                ListenerString.GO_BACK_TO_SELECT_DIFFICULTY_MESSAGE,
                ListenerString.CONFIRM_MESSAGE,
                this::goBackToSelectDifficulty
        );
        listener.actionPerformed(e);
    }

    private void goBackToSelectDifficulty() {
        mainPanel.setSelectDifficultyContents();

        contentPanel.removeAll();
        contentPanel.add(new GameSamePicPanel(headerPanel));

        SwingUtils.refresh(mainPanel);
    }
}
