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

    public AppListener(MainPanel mainPanel, JPanel contentPanel, BackgroundPanel backgroundPanel){
        this.mainPanel = mainPanel;
        this.contentPanel = contentPanel;
        this.backgroundPanel = backgroundPanel;
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
        contentPanel.removeAll();
        contentPanel.add(new Game2048Panel());
        backgroundPanel.showHomeButton(true); // 홈버튼 보이기, selectGame 숨김
    }

    private void openGameSamePic() {
        backgroundPanel.removeContents();
        backgroundPanel.setBounds(0, 0, 1000, 100);

        contentPanel.removeAll();
        contentPanel.setBounds(0, 100, 1000, 750);
        contentPanel.add(new GameSamePicPanel());
        SwingUtils.refresh(contentPanel);
        backgroundPanel.showHomeButton(true);
    }

    private void goBackToHome() {
        backgroundPanel.setBounds(0, 0, 1000, 350);
        backgroundPanel.setVisibleContents();

        contentPanel.removeAll();
        contentPanel.setBounds(0, 350, 1000, 450);
        contentPanel.add(new GameButtonPanel());
        backgroundPanel.showHomeButton(false);
        SwingUtils.refresh(contentPanel);
    }
}
