package InfoStroyDmitriyGorobets.MathUtils;

/**
 * Created by Gorobets Dmitriy on 12/5/15.
 */
public class MathUtils {


    /**
     * Returns the greatest common divider of given two numbers
     *
     * @param firstNumber  - positive number
     * @param secondNumber - positive number
     * @return greatest common divider of two numbers
     */
    public int getGreatestCommonDivider(int firstNumber, int secondNumber) {
        long startTime = System.currentTimeMillis();
        int result = 0;
        int counter = (firstNumber <= secondNumber) ? firstNumber : secondNumber;
        for (int i = counter; i > 0; i--) {
            if (firstNumber % i == 0 && secondNumber % i == 0) {
                result = i;
                break;
            }
        }
        long spentTime = System.currentTimeMillis() - startTime;
        System.out.println(spentTime);
        return result;
    }

    /**
     * Returns sum of digits of the given number
     *
     * @param number - positive number
     * @return the sum of digits of the given number
     */
    public int getSumOfDigits(int number) {
        long startTime = System.currentTimeMillis();
        int sum = 0;
        for (int i = number; i > 0; i /= 10) {
            sum += i % 10;
        }
        long spentTime = System.currentTimeMillis() - startTime;
        System.out.println(spentTime);
        return sum;
    }

    /**
     * Checks if the given number is prime or not
     *
     * @param number - random number
     * @return true - if number is prime, if not return false
     */
    public boolean isPrime(int number) {
        long startTime = System.currentTimeMillis();
        if (number < 2) {
            return false;
        }
        for (int i = 2; i < number; i++) {
            if (number % i == 0) {
                return false;
            }
        }
        long spentTime = System.currentTimeMillis() - startTime;
        System.out.println(spentTime);
        return true;
    }

    /**
     * Returns sum of row: 1! - 2! + 3! - 4! + 5! - ... + n!
     *
     * @param n - positive number
     */
    public int getSumOfRow(int n) {
        long startTime = System.currentTimeMillis();
        int result = 0;
        int fact = 1;
        int sign = 1;
        for (int i = 1; i <= n; i++) {
            fact *= i * sign;
            result += fact;
            sign = -1;
        }
        long spentTime = System.currentTimeMillis() - startTime;
        System.out.println(spentTime);
        return result;
    }

    /**
     * Returns Fibonacci series of a specified length
     *
     * @param length - the length of the Fibonacci series
     * @return array filled with Fibonacci series
     */
    public int[] getFibonacciSeries(int length) {
        long startTime = System.currentTimeMillis();
        if (length <= 0) {
            System.out.println("Wrong length of an array");
            return new int[0];
        }
        int array[] = new int[length];
        if (length == 1) {
            return new int[]{0};
        } else if (length == 2) {
            return new int[]{0, 1};
        }
        for (int i = 0; i < length; i++) {

            if (length > 2) {
                array[0] = 0;
                array[1] = 1;
                for (int j = 2; j < length; j++) {
                    array[j] = array[j - 1] + array[j - 2];
                }
            }
        }
        long spentTime = System.currentTimeMillis() - startTime;
        System.out.println(spentTime);
        return array;
    }

    /**
     * Returns array with prime numbers
     *
     * @param length - the length of prime numbers series
     * @return array filled with prime numbers
     */
    public int[] getPrimeSeries(int length) {
        long startTime = System.currentTimeMillis(); // this is only for text analyzer task :-) don't need to do it everywhere
        int array[] = new int[length];
        MathUtils math = new MathUtils(); // hi! you don't need to create a new object. You are in the same class. Just call isPrime() (Anya)
        int number = 1;
        for (int i = 0; i < length; i++) {
            while (!math.isPrime(number)) {//use method isPrime() for checking a prime number
                number++;
            }
            array[i] = number;
            number++;
        }
        long spentTime = System.currentTimeMillis() - startTime;
        System.out.println(spentTime);
        return array;
    }

}
