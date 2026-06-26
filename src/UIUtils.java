import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class UIUtils {

    public static void addHoverEffect(
            JButton button,
            Color normalColor,
            Color hoverColor) {

        button.setBackground(normalColor);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));

        button.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseEntered(MouseEvent e) {

                button.setBackground(hoverColor);

            }

            @Override
            public void mouseExited(MouseEvent e) {

                button.setBackground(normalColor);

            }

        });

    }

}