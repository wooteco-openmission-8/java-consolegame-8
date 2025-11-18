package gamebox.swing.panel;

import gamebox.swing.listener.AppListener;
import gamebox.swing.swing_util.SwingUtils;

import javax.swing.*;
import java.awt.*;

public class MainPanel extends JPanel {
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
        backgroundPanel.setBounds(0, 0, 1000, 350);
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

    public void setSamePicContents() {
        headerPanel.setSamePicContents();
    }

    public void removeHeader() {
        remove(headerPanel);
    }
}
