package gamebox.swing.panel;

import gamebox.swing.listener.AppListener;
import gamebox.swing.swing_util.SwingUtils;

import javax.swing.*;
import java.awt.*;

public class MainPanel extends JPanel {
    private final BackgroundPanel backgroundPanel = new BackgroundPanel();
    private final JPanel contentPanel = new JPanel(new BorderLayout());
    private final GameButtonPanel gameButtonPanel = new GameButtonPanel();

    /**
     * MainPanel(BorderLayout) -> backgroundPanel(NORTH) + contentPanel(CENTER)
     * contentPanel(BorderLayout) -> GameButtonPanel(CENTER)
     */
    public MainPanel() {
        setLayout(null);
        backgroundPanel.setBounds(0, 0, 1000, 400);
        contentPanel.setBounds(0, 350, 1000, 450);
        addPanels();
        addListeners();
        setInitialContent();
    }

    private void setInitialContent() {
        setContent(gameButtonPanel);
    }

    private void addPanels() {
        add(backgroundPanel);
        add(contentPanel);
    }

    private void addListeners() {
        AppListener listener = new AppListener(contentPanel,backgroundPanel);
        gameButtonPanel.addGameButtonListener(listener);
        backgroundPanel.addHomeButtonListener(listener);
    }

    public void setContent(JPanel newContent) {
        contentPanel.removeAll();
        contentPanel.add(newContent, BorderLayout.CENTER);
        SwingUtils.refresh(contentPanel);
    }
}
