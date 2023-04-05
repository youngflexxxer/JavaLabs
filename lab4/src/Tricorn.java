import java.awt.geom.Rectangle2D;

public class Tricorn extends FractalGenerator {
    public static final int MAX_ITERATIONS = 2000;

    /**
     * Устанавливает начальный диапазон для фрактала.
     */
    public void getInitialRange(Rectangle2D.Double range) {
        range.setRect(-2, -2, 4, 4);
    }

    /**
     * Основная функция для фрактального генератора Tricorn.
     */
    public int numIterations(double x, double y) {
        int iterations = 0;
        double real = 0;
        double imaginary = 0;

        while (iterations < MAX_ITERATIONS && real * real + imaginary * imaginary < 4) {
            double tempReal = real * real - imaginary * imaginary + x;
            double tempImaginary = -2 * real * imaginary + y;

            real = tempReal;
            imaginary = tempImaginary;

            iterations++;
        }

        if (iterations == MAX_ITERATIONS) {
            return -1;
        }

        return iterations;
    }

    @Override
    public String toString() {
        return "Tricorn";
    }
}

