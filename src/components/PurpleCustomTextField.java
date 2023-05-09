package components;
import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author gabriel
 */

public class PurpleCustomTextField extends JTextField {

    private static final int BORDER_THICKNESS = 1;
    private static final Color BORDER_COLOR = new Color(36, 0, 41);

    public PurpleCustomTextField(int columns) {
        super(columns);
        setBorder(new EmptyBorder(0, 0, BORDER_THICKNESS, 0));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(BORDER_COLOR);
        g.fillRect(0, getHeight() - BORDER_THICKNESS, getWidth(), BORDER_THICKNESS);
    }
}

