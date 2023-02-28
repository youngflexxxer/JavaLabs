import java.util.Scanner;

public class Lab1 {
    public static void main(String[] args) {
        Point3d point1 = null, point2 = null, point3 = null;
        Scanner in = new Scanner(System.in);
        for(int i = 1; i < 4; i++) {
            System.out.print("Введите координаты точки " + i + ": ");
            int[] coords = new int[3];
            for(int j = 0; j < 3; j++) {
                coords[j] = in.nextInt();
            }
            switch(i) {
                case 1:
                point1 = new Point3d(coords[0], coords[1], coords[2]);
                case 2:
                point2 = new Point3d(coords[0], coords[1], coords[2]);
                case 3:
                point3 = new Point3d(coords[0], coords[1], coords[2]);
            }           
        }
        if (Point3d.equals(point1, point2) || Point3d.equals(point2, point3) || Point3d.equals(point1, point3)) {
            System.out.println("Имеются одинаковые точки!");
        }
        else {
        System.out.println("Площадь треугольника: " + computeArea(point1, point2, point3));
        }
    }
    public static double computeArea(Point3d p1, Point3d p2, Point3d p3) {
        double x1 = p1.getX(), y1 = p1.getY(), z1 = p1.getZ();
        double x2 = p2.getX(), y2 = p2.getY(), z2 = p2.getZ();
        double x3 = p3.getX(), y3 = p3.getY(), z3 = p3.getZ();
        // вычисляем векторы AB и AC
            double ABx = x2 - x1;
            double ABy = y2 - y1;
            double ABz = z2 - z1;
            double ACx = x3 - x1;
            double ACy = y3 - y1;
            double ACz = z3 - z1;
            // вычисляем векторное произведение AB и AC
            double crossProductX = ABy * ACz - ABz * ACy;
            double crossProductY = ABz * ACx - ABx * ACz;
            double crossProductZ = ABx * ACy - ABy * ACx;
    
            // вычисляем площадь треугольника
            double area = 0.5 * Math.sqrt(crossProductX * crossProductX +
                                           crossProductY * crossProductY +
                                           crossProductZ * crossProductZ);
            return area;
    }    
}
