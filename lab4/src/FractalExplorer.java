import java.awt.*;
import javax.swing.*;
import javax.swing.filechooser.*;
import java.awt.event.*;
import java.awt.geom.Rectangle2D;
import java.io.IOException;

import javax.imageio.ImageIO;

public class FractalExplorer {
    private int size;
    private JImageDisplay display;
    private FractalGenerator fractal;
    private Rectangle2D.Double range;
    JComboBox<FractalGenerator> comboBox;
    
    private void drawFractal() {
        for (int x = 0; x < size; x++) {
            for (int y = 0; y < size; y++) {
                // get the coordinates in the fractal space corresponding to this pixel
                double xCoord = FractalGenerator.getCoord(range.x, range.x + range.width, size, x);
                double yCoord = FractalGenerator.getCoord(range.y, range.y + range.height, size, y);
                
                // compute the number of iterations for the coordinates
                int numIters = fractal.numIterations(xCoord, yCoord);
                
                // set the pixel color based on the number of iterations
                if (numIters == -1) {
                    display.drawPixel(x, y, 0); // black
                } else {
                    float hue = 0.7f + (float) numIters / 200f;
                    int rgbColor = Color.HSBtoRGB(hue, 1f, 1f);
                    display.drawPixel(x, y, rgbColor);
                }
            }
        }
        display.repaint();
    }
    
    private void reset() {
        fractal.getInitialRange(range);
        drawFractal();
    }

    public void createAndShowGUI() {
        JFrame frame = new JFrame("Fractal");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container contentPane = frame.getContentPane();
        contentPane.setLayout(new BorderLayout());
        JPanel southPanel = new JPanel();
        JPanel northPanel = new JPanel();
        JLabel fractalname = new JLabel("Fractal:");
        JButton resetButton = new JButton("Reset");
        JButton saveButton = new JButton("Save image");
        resetButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) { reset(); }
        });
        saveButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) { save(); }
        });
        comboBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) { changeGenerator(); }
        });
        display.addMouseListener(new FractalMouseListener(this));
        
        contentPane.add(display, BorderLayout.CENTER);

        southPanel.add(saveButton);
        southPanel.add(resetButton);
        contentPane.add(southPanel, BorderLayout.SOUTH);

        northPanel.add(fractalname);
        northPanel.add(comboBox);
        contentPane.add(northPanel, BorderLayout.NORTH);
        
        frame.pack();
        frame.setVisible(true);
        frame.setResizable (false);
    }

    private void changeGenerator() {
        FractalGenerator generator = (FractalGenerator) comboBox.getSelectedItem();
        fractal = generator;
        generator.getInitialRange(range);
        drawFractal();
    }

    private void save() {
        JFileChooser chooser = new JFileChooser();
        FileFilter filter = new FileNameExtensionFilter("PNG Images", "*.png", "png");
        chooser.setFileFilter(filter);
        chooser.setAcceptAllFileFilterUsed(false);
        int r = chooser.showSaveDialog(chooser);
        if(r == JFileChooser.APPROVE_OPTION) {
            try
            {
            ImageIO.write(display.image, "png", chooser.getSelectedFile());
            }
            catch (IOException e) {
                JOptionPane.showMessageDialog(chooser, e, "Cannot save image", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void start()
    {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() { createAndShowGUI(); }
        });
    }

    public FractalExplorer(int x) {
        this.size = x;
        this.fractal = new Mandelbrot();
        this.range = new Rectangle2D.Double();
        this.fractal.getInitialRange(range);
        this.display = new JImageDisplay(size, size);
        comboBox = new JComboBox<FractalGenerator>();
        comboBox.addItem(fractal);
        Tricorn tricorn = new Tricorn();
        comboBox.addItem(tricorn);
        BurningShip burningShip = new BurningShip();
        comboBox.addItem(burningShip);
    }

    public static void main(String[] args) {
        FractalExplorer app = new FractalExplorer(800);
        app.start();
        app.drawFractal();
    }
    public class FractalMouseListener extends MouseAdapter {
        private FractalExplorer fractalExplorer;
    
        public FractalMouseListener(FractalExplorer fractalExplorer) {
            this.fractalExplorer = fractalExplorer;
        }
    
        @Override
        public void mouseClicked(MouseEvent e) {
            // Получаем координаты клика
            int x = e.getX();
            int y = e.getY();
    
            // Получаем координаты фрактала, соответствующие координатам клика
            double xCoord = FractalGenerator.getCoord(range.x, range.x + range.width, size, x);
            double yCoord = FractalGenerator.getCoord(range.y, range.y + range.height, size, y);
    
            // Масштабируем фрактал и центрируем его на точке клика
            fractal.recenterAndZoomRange(range, xCoord, yCoord, 0.5);
    
            // Перерисовываем фрактал
            fractalExplorer.drawFractal();
        }
    }
}