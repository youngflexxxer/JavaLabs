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
     public boolean equals(Point3d point) {
        if ((getX() == point.getX()) && (getY() == point.getY()) && (getZ() == point.getZ())) {
            return true;
        }
        return false;
     }
     public double distanceTo(Point3d point) {
         return (Math.round(Math.sqrt(Math.pow(getX() - point.getX(), 2) + Math.pow(getY() - point.getY(), 2) + Math.pow(getZ() - point.getZ(), 2)) * 100)) / 100;
     }
}
