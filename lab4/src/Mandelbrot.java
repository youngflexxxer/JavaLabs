import java.awt.geom.Rectangle2D;

public class Mandelbrot extends FractalGenerator{
    public static final int MAX_ITERATIONS = 2000;
    public void getInitialRange (Rectangle2D.Double range) {
        range.setRect(-2, -1.5, 3, 3);
    }
    public int numIterations(double x, double y) {
        // Начальные значения
        double real = 0;
        double imaginary = 0;
        int iteration = 0;
        
        // Итерационная функция для фрактала Мандельброта
        while (iteration < MAX_ITERATIONS && real*real + imaginary*imaginary < 4) {
            double nextReal = real*real - imaginary*imaginary + x;
            double nextImaginary = 2*real*imaginary + y;
            real = nextReal;
            imaginary = nextImaginary;
            iteration++;
        }
        
        // Возвращение числа итераций
        if (iteration == MAX_ITERATIONS) {
            return -1;
        } else {
            return iteration;
        }
    }

    @Override
    public String toString() {
        return "Mandelbrot";
    }
}
