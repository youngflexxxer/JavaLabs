public class Point3d extends Point2d {
     // координата Z
     private double zCoord;
     // Конструктор инициализации
     public Point3d (double x, double y, double z) {
         super(x, y);
         zCoord = z;
     }
     public Point3d () {
         this(0,0,0);
     }
     // Возвращение координаты Z
     public double getZ () {
         return zCoord;
     }
     // Установка значения координаты Z
     public void setZ (double val) {
         zCoord = val;
     }
     public static boolean equals(Point3d point1, Point3d point2) {
        double x = point1.getX();
        double y = point1.getY();
        double z = point1.getZ();
        double x2 = point2.getX();
        double y2 = point2.getY();
        double z2 = point2.getZ();
        if ((x == x2) && (y == y2) && (z == z2)) {
            return true;
        }
        return false;
     }
     public double distanceTo(Point3d point) {
         return (Math.round(Math.sqrt(Math.pow(super.getX() - point.getX(), 2) + Math.pow(super.getY() - point.getY(), 2) + Math.pow(getZ() - point.getZ(), 2)) * 100)) / 100;
     }
}
