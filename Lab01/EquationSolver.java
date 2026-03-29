import java.util.Scanner;

public class EquationSolver {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int type = sc.nextInt();

        if (type == 1) {
            double a = sc.nextDouble(), b = sc.nextDouble();
            if (a == 0) System.out.println(b == 0 ? "vo so nghiem" : "vo nghiem");
            else System.out.println(-b / a);
        } else if (type == 2) {
            double a11 = sc.nextDouble(), a12 = sc.nextDouble(), b1 = sc.nextDouble();
            double a21 = sc.nextDouble(), a22 = sc.nextDouble(), b2 = sc.nextDouble();
            double D = a11 * a22 - a21 * a12;
            double D1 = b1 * a22 - b2 * a12;
            double D2 = a11 * b2 - a21 * b1;
            if (D == 0) System.out.println(D1 == 0 && D2 == 0 ? "Vo so nghiem" : "Vo nghiem");
            else System.out.println((D1 / D) + " " + (D2 / D));
        } else if (type == 3) {
            double a = sc.nextDouble(), b = sc.nextDouble(), c = sc.nextDouble();
            if (a == 0) {
                if (b == 0) System.out.println(c == 0 ? "vo so nghiem" : "vo nghiem");
                else System.out.println(-c / b);
            } else {
                double delta = b * b - 4 * a * c;
                if (delta < 0) System.out.println("vo nghiem");
                else if (delta == 0) System.out.println(-b / (2 * a));
                else System.out.println((-b + Math.sqrt(delta)) / (2 * a) + " " + (-b - Math.sqrt(delta)) / (2 * a));
            }
        }
        sc.close();
    }
}
