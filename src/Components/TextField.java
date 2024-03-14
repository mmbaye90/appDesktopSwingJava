package Components;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.RoundRectangle2D;

import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class TextField extends JTextField {

	private static final long serialVersionUID = 1L;

	/**
	 * Create the panel.
	 */
	public TextField() {
        super();
      	setBorder(new EmptyBorder(10, 10, 10, 10));
		setBackground(new Color(0, 0, 0));
        setOpaque(false);
        setHorizontalAlignment(JTextField.CENTER);
	}
	@Override
    protected void paintComponent(Graphics grphcs) {
        Graphics2D g2 = (Graphics2D) grphcs.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        int width = getWidth() - 1;
        int height = getHeight() - 1;
        g2.setColor(getBackground());
        g2.fill(new RoundRectangle2D.Double(0, 0, width, height, 20, 20));
        g2.setColor(getForeground());
        super.paintComponent(g2);
        g2.dispose();    }

}
