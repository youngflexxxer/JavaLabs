public class Point3d {
     // координата X
     private double xCoord;
     // координата Y
     private double yCoord;
     // координата Z
     private double zCoord;
     // Конструктор инициализации
     public Point3d (double x, double y, double z) {
         xCoord = x;
         yCoord = y;
         zCoord = z;
     }
     public Point3d () {
         this(0,0,0);
     }
     // Возвращение координаты X
     public double getX () {
         return xCoord;
     }
     // Возвращение координаты Y
     public double getY () {
         return yCoord;
     }
     // Возвращение координаты Z
     public double getZ () {
         return zCoord;
     }
     // Установка значения координаты X
     public void setX (double val) {
         xCoord = val;
     }
     // Установка значения координаты Y
     public void setY (double val) {
         yCoord = val;
     }
     // Установка значения координаты Z
     public void setZ (double val) {
         zCoord = val;
     }
     public static boolean equals(Point3d point) {
         return true;
     }
     public double distanceTo(Point3d point) {
         return Math.sqrt(Math.pow(xCoord - point.xCoord, 2) + Math.pow(yCoord - point.yCoord, 2) + Math.pow(zCoord - point.zCoord, 2));
     }
}
