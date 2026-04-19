import java.util.Arrays;
import java.util.Scanner;

public class ArrayOperations {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        double[] arr = new double[n];
        double sum = 0;

        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextDouble();
            sum += arr[i];
        }

        Arrays.sort(arr);
        System.out.println(Arrays.toString(arr));
        System.out.println(sum);
        System.out.println(sum / n);
        sc.close();
    }
}
