import java.awt.geom.Rectangle2D;

public class BurningShip extends FractalGenerator {
    
    // Константы для удобства
    public static final int MAX_ITERATIONS = 2000;
    public static final double DEFAULT_RANGE = 2.0;
    
    @Override
    public void getInitialRange(Rectangle2D.Double range) {
        // Устанавливаем начальный диапазон для фрактала
        range.x = -2;
        range.y = -2.5;
        range.width = range.height = 4;
    }
    
    @Override
    public int numIterations(double x, double y) {
        // Реализация фрактала "Burning Ship"
        int iterations = 0;
        double re = 0;
        double im = 0;
        
        while (iterations < MAX_ITERATIONS && re * re + im * im < 4) {
            double nextRe = Math.abs(re);
            double nextIm = Math.abs(im);
            nextRe = nextRe * nextRe - nextIm * nextIm + x;
            nextIm = 2 * Math.abs(re * nextIm) + y;
            re = nextRe;
            im = nextIm;
            iterations++;
        }
        
        // Возвращаем количество итераций или -1, если достигнут максимум
        return iterations == MAX_ITERATIONS ? -1 : iterations;
    }
    
    @Override
    public String toString() {
        // Возвращаем название фрактала
        return "Burning Ship";
    }
}

