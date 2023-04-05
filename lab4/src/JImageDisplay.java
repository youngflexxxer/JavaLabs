import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class JImageDisplay extends javax.swing.JComponent {
    public BufferedImage image;
    public JImageDisplay(int width, int height) {
        image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Dimension dim = new Dimension(width, height);
        setPreferredSize(dim);
        
    }
    @Override
    protected void paintComponent(Graphics g) {
        g.drawImage (image, 0, 0, image.getWidth(), image.getHeight(), null);
    }
    public void clearImage() {
        int width = image.getWidth();
        int height = image.getHeight();

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                image.setRGB(x, y, 0); // set pixel to black (RGB 0)
            }
        }
    }
    public void drawPixel(int x, int y, int rgbColor) {
        image.setRGB(x, y, rgbColor);
    }
}
