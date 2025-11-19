package gamebox.swing.panel;

import gamebox.swing.listener.AppListener;
import gamebox.swing.swing_util.SwingUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.PaintEvent;

public class MainPanel extends JPanel {
    private static final int BACKGROUND_POSITION_X = 0;
    private static final int BACKGROUND_POSITION_Y = 0;
    private static final int BACKGROUND_WIDTH = 1000;
    private static final int BACKGROUND_HEIGHT = 350;
    private static final int CONTENT_PANEL_POSITION_X = 0;
    private static final int CONTENT_PANEL_POSITION_Y = 350;
    private static final int CONTENT_PANEL_WIDTH = 1000;
    private static final int CONTENT_PANEL_HEIGHT = 450;

    private final BackgroundPanel backgroundPanel = new BackgroundPanel();
    private final JPanel contentPanel = new JPanel(new BorderLayout());
    private final GameButtonPanel gameButtonPanel = new GameButtonPanel();
    private final HeaderPanel headerPanel = new HeaderPanel();

    /**
     * MainPanel(BorderLayout) -> backgroundPanel(NORTH) + contentPanel(CENTER)
     * contentPanel(BorderLayout) -> GameButtonPanel(CENTER)
     */
    public MainPanel() {
        setLayout(null);
        backgroundPanel.setBounds(
                BACKGROUND_POSITION_X, BACKGROUND_POSITION_Y, BACKGROUND_WIDTH, BACKGROUND_HEIGHT
        );
        contentPanel.setBounds(
                CONTENT_PANEL_POSITION_X, CONTENT_PANEL_POSITION_Y, CONTENT_PANEL_WIDTH, CONTENT_PANEL_HEIGHT
        );
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
        AppListener listener = new AppListener(this, contentPanel,backgroundPanel, headerPanel);
        gameButtonPanel.addGameButtonListener(listener);
        headerPanel.addHomeButtonListener(listener);
    }

    public void setContent(JPanel newContent) {
        contentPanel.removeAll();
        contentPanel.add(newContent, BorderLayout.CENTER);
        SwingUtils.refresh(contentPanel);
    }

    public void addHeaderPanel() {
        add(headerPanel);
    }

    public void set2048Contents() {
        headerPanel.set2048Contents();
    }

    public void setSelectDifficultyContents() {
        headerPanel.setSelectDifficultyContents();
    }

    public void removeHeader() {
        remove(headerPanel);
    }
}
