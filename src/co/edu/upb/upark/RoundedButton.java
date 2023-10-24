package co.edu.upb.upark;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.Timer;

public class RoundedButton extends JButton {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Color startColor;
    private Color endColor;
    private int animationDuration; // In milliseconds

    public RoundedButton(String text, Color startColor, Color endColor, int animationDuration) {
        super(text);
        this.setStartColor(startColor);
        this.setEndColor(endColor);
        this.setAnimationDuration(animationDuration);
        setBorderPainted(false);
        setContentAreaFilled(false);
        setOpaque(false);

        addMouseListener(new MouseAdapter() {
            private Timer timer;
            private int alpha = 0;

            @Override
            public void mouseEntered(MouseEvent e) {
                if (timer != null && timer.isRunning()) {
                    timer.stop();
                }
                alpha = 0;
                timer = new Timer(animationDuration / 100, evt -> {
                    alpha += 10;
                    if (alpha >= 255) {
                        alpha = 255;
                        timer.stop();
                    }
                    Color color = new Color(
                            (int) (startColor.getRed() * (1 - alpha / 255.0) + endColor.getRed() * (alpha / 255.0)),
                            (int) (startColor.getGreen() * (1 - alpha / 255.0) + endColor.getGreen() * (alpha / 255.0)),
                            (int) (startColor.getBlue() * (1 - alpha / 255.0) + endColor.getBlue() * (alpha / 255.0))
                    );
                    setBackground(color);
                });
                timer.start();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                if (timer != null && timer.isRunning()) {
                    timer.stop();
                }
                alpha = 0;
                timer = new Timer(animationDuration / 100, evt -> {
                    alpha += 10;
                    if (alpha >= 255) {
                        alpha = 255;
                        timer.stop();
                    }
                    Color color = new Color(
                            (int) (endColor.getRed() * (1 - alpha / 255.0) + startColor.getRed() * (alpha / 255.0)),
                            (int) (endColor.getGreen() * (1 - alpha / 255.0) + startColor.getGreen() * (alpha / 255.0)),
                            (int) (endColor.getBlue() * (1 - alpha / 255.0) + startColor.getBlue() * (alpha / 255.0))
                    );
                    setBackground(color);
                });
                timer.start();
            }
        });
        
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }
        });
    } // public RoundedButton(String text, Color startColor, Color endColor, int animationDuration)

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(getBackground());
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);
        super.paintComponent(g);
    }

	public Color getStartColor() {
		return startColor;
	}

	public void setStartColor(Color startColor) {
		this.startColor = startColor;
	}

	public Color getEndColor() {
		return endColor;
	}

	public void setEndColor(Color endColor) {
		this.endColor = endColor;
	}

	public int getAnimationDuration() {
		return animationDuration;
	}

	public void setAnimationDuration(int animationDuration) {
		this.animationDuration = animationDuration;
	}
} // public class RoundedButton extends JButton