package components;
import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPasswordField;

/**
 *
 * @author gabriel
 */

public class CustomPinkPasswordField extends JPasswordField {

    private Color borderColor = new Color(235, 81, 96);

    public CustomPinkPasswordField(int columns) {
        super(columns);
        setOpaque(false);
    }

    public void setBorderColor(Color borderColor) {
        this.borderColor = borderColor;
        repaint();
    }

    public Color getBorderColor() {
        return borderColor;
    }

    @Override
    protected void paintComponent(Graphics g) {
        g.setColor(borderColor);
        g.fillRect(0, getHeight() - 1, getWidth(), 1);
        super.paintComponent(g);
    }

    @Override
    public void setForeground(Color fg) {
        super.setForeground(new Color(235, 81, 96));
    }
}

