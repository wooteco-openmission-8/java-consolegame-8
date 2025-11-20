package gamebox.swing.panel;

import gamebox.swing.listener.AppListener;
import gamebox.swing.panel.constants.PanelNumber;
import gamebox.swing.swing_util.SwingUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.PaintEvent;

public class MainPanel extends JPanel {
    private final BackgroundPanel backgroundPanel = new BackgroundPanel();
    private final JPanel contentPanel = new JPanel(new BorderLayout());
    private final GameButtonPanel gameButtonPanel = new GameButtonPanel();
    private final HeaderPanel headerPanel = new HeaderPanel();

    public MainPanel() {
        setLayout(null);
        backgroundPanel.setBounds(
                PanelNumber.BACKGROUND_POSITION_X, PanelNumber.BACKGROUND_POSITION_Y,
                PanelNumber.BACKGROUND_WIDTH, PanelNumber.BACKGROUND_HEIGHT
        );
        contentPanel.setBounds(
                PanelNumber.CONTENT_PANEL_POSITION_X, PanelNumber.CONTENT_PANEL_POSITION_Y,
                PanelNumber.CONTENT_PANEL_WIDTH, PanelNumber.CONTENT_PANEL_HEIGHT
        );
        addPanels();
        addListeners();
        setInitialContent();
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

    private void setInitialContent() {
        setContent(gameButtonPanel);
    }

    private void setContent(JPanel newContent) {
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
