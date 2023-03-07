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
        // Вычисляем длины сторон треугольника
        double a = p1.distanceTo(p2);
        double b = p2.distanceTo(p3);
        double c = p1.distanceTo(p3);
        // Вычисляем полупериметр
        double s = ((a + b + c) / 2);
        // вычисляем площадь треугольника
        double area = Math.sqrt(s * (s-a) * (s-b) * (s-c));
        return area;
    }    
}
