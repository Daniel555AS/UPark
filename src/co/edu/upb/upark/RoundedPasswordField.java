package co.edu.upb.upark;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;

public class RoundedPasswordField extends JPasswordField {
    
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public RoundedPasswordField(int columns) {
        super(columns);
        setOpaque(false);
        setBorder(new EmptyBorder(0, 10, 0, 10));
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(getBackground());
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);
        super.paintComponent(g);
    }
    
} // public class RoundedPasswordField extends JPasswordField


