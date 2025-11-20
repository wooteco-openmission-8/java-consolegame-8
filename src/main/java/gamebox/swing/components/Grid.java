package gamebox.swing.components;


import gamebox.swing.components.constants.ComponentsNumber;

import javax.swing.*;
import java.awt.*;

public class Grid {

    public static JPanel createGridPanel(int rows, int cols) {
        JPanel panel = new JPanel(new GridLayout(
                rows, cols, ComponentsNumber.GRID_GAP, ComponentsNumber.GRID_GAP
        ));
        panel.setBorder(BorderFactory.createEmptyBorder(
                ComponentsNumber.PADDING_SIZE, ComponentsNumber.PADDING_SIZE,
                ComponentsNumber.PADDING_SIZE, ComponentsNumber.PADDING_SIZE
        ));
        return panel;
    }
}
