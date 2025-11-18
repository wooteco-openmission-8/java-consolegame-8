package gamebox.swing.listener;

import gamebox.swing.panel.*;
import gamebox.swing.swing_util.SwingUtils;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AppListener implements ActionListener {
    private static final String GAME_2048_BUTTON_NAME = "2048";
    private static final String GAME_SAME_PIC_BUTTON_NAME = "같은 그림 찾기";
    private static final String HOME_BUTTON_NAME = "홈으로";

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

        if (selectedButton.equals(GAME_2048_BUTTON_NAME)) {
            openGame2048();
        }
        if (selectedButton.equals(GAME_SAME_PIC_BUTTON_NAME)) {
            openGameSamePic();
        }
        if (selectedButton.equals(HOME_BUTTON_NAME)) {
            goBackToHome();
        }

        SwingUtils.refresh(contentPanel);
    }

    private void openGame2048() {
        mainPanel.remove(backgroundPanel);
        mainPanel.addHeaderPanel();
        mainPanel.set2048Contents();

        contentPanel.removeAll();
        contentPanel.setBounds(0, 100, 1000, 750);
        Game2048Panel game2048Panel = new Game2048Panel();
        game2048Panel.setResetButton(headerPanel.getResetButton());
        contentPanel.add(game2048Panel);

        SwingUtils.refresh(mainPanel);
    }

    private void openGameSamePic() {
        mainPanel.remove(backgroundPanel);
        mainPanel.addHeaderPanel();
        mainPanel.setSamePicContents();

        contentPanel.removeAll();
        contentPanel.setBounds(0, 100, 1000, 750);
        contentPanel.add(new GameSamePicPanel());

        SwingUtils.refresh(mainPanel);
    }

    private void goBackToHome() {
        mainPanel.removeHeader();
        mainPanel.add(backgroundPanel);

        contentPanel.removeAll();
        contentPanel.setBounds(0, 350, 1000, 450);
        contentPanel.add(new GameButtonPanel());
        SwingUtils.refresh(mainPanel);
    }
}
