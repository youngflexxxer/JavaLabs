public class Point2d {
    // координата X
    private double xCoord;
    // координата Y
    private double yCoord;
    // Конструктор инициализации
    public Point2d (double x, double y) {
        xCoord = x;
        yCoord = y;
    }
    // Конструктор по умолчанию.
    public Point2d () {
    // Вызов конструктора с двумя параметрами и определение источника.
        this(0, 0);
    }
    // Возвращение координаты X
    public double getX () {
        return xCoord;
    }
    // Возвращение координаты Y
    public double getY () {
        return yCoord;
    }
    // Значение координаты X
    public void setX ( double val) {
        xCoord = val;
    }
    // Значение координаты Y
    public void setY ( double val) {
        yCoord = val;
    }
}
