package Task1.TriangleUtils;

/**
 * Created by Gorobets Dmitriy on 12/5/15.
 */
public class TriangleUtils {
    /**
     * Задача о треугольнике
     * <p>
     * Вам даны длинны трех отрезков: a, b, c. Нужно вернуть true, если они
     * могут быть сторонами треугольника и false, если не могут.
     */

    public boolean isTriangle(int a, int b, int c) throws IllegalArgumentException {
        if (a<0 || b<0 || c<0) {
            throw new IllegalArgumentException();
        }
        if (a + b > c && a + c > b && c + b > a) {
            return true;
        } else {
            return false;
        }

    }

    /**
     * Вам даны длинны трех сторон треугольника: a, b, c. Необходимо вычислить
     * площадь треугольника.
     */

    public double getTriangleArea(int a, int b, int c) throws IllegalArgumentException {
        if (isTriangle(a, b, c)) {
            double semiPerimeter = (a + b + c) / 2;
            return Math.sqrt(semiPerimeter * (semiPerimeter - a) * (semiPerimeter - b) * (semiPerimeter - c));

        } else {
            System.out.println("Illegal Arguments");
            return 0d;
        }
    }
}