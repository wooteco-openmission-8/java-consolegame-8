package gamebox.swing.listener;


import javax.swing.*;
import java.awt.*;

public class GameStatusModalListener {
    public static boolean showModal(Component parent, String message, String title) {
        int result = JOptionPane.showConfirmDialog(
                parent,
                message,
                title,
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE
        );

        return result == JOptionPane.YES_OPTION;
    }
}
