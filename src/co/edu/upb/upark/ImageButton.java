package co.edu.upb.upark;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ImageButton extends JButton {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Image img;
    private float opacity;
    private float alpha;

    public ImageButton(String text, String imagePath) {
        super(text);
        this.setContentAreaFilled(false);
        this.setBorderPainted(false);
        this.setFocusPainted(false);
        this.setOpaque(false);
        this.opacity = 1.0f;

        this.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                opacity = 0.5f;
                repaint();
            }

            public void mouseExited(MouseEvent e) {
                opacity = 1.0f;
                repaint();
            }
        });

        this.img = Toolkit.getDefaultToolkit().getImage(imagePath);

        // Wait for the image to load completely
        MediaTracker tracker = new MediaTracker(this);
        tracker.addImage(img, 0);
        try {
            tracker.waitForAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                setCursor(new Cursor(Cursor.HAND_CURSOR));
                setOpacity(0.5f);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                setOpacity(1.0f);
            }
        });
    }
    
    public void setOpacity(float opacity) {
        this.setAlpha(opacity);
        repaint();
    }

    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, opacity));
        g2d.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);
        g2d.dispose();

        super.paintComponent(g);
    }

	public float getAlpha() {
		return alpha;
	}

	public void setAlpha(float alpha) {
		this.alpha = alpha;
	}
} // public class ImageButton extends JButton