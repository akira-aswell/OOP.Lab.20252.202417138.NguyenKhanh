import java.util.Scanner;

public class DaysInMonth {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int year = -1, month = 0;
        while (true) {
            System.out.print("nhap thang: ");
            String input = sc.next().toLowerCase();
            System.out.print("nhap nam: ");
            if (sc.hasNextInt()) {
                year = sc.nextInt();
            } else {
                sc.next();
                System.out.println("loi, nhap lai");
                continue;
            }
            if (year < 0) {
                System.out.println("nam khong chuan, nhap lai");
                continue;
            }
            if (input.equals("january") || input.equals("jan.") || input.equals("jan") || input.equals("1")) month = 1;
            else if (input.equals("february") || input.equals("feb.") || input.equals("feb") || input.equals("2")) month = 2;
            else if (input.equals("march") || input.equals("mar.") || input.equals("mar") || input.equals("3")) month = 3;
            else if (input.equals("april") || input.equals("apr.") || input.equals("apr") || input.equals("4")) month = 4;
            else if (input.equals("may") || input.equals("5")) month = 5;
            else if (input.equals("june") || input.equals("jun") || input.equals("6")) month = 6;
            else if (input.equals("july") || input.equals("jul") || input.equals("7")) month = 7;
            else if (input.equals("august") || input.equals("aug.") || input.equals("aug") || input.equals("8")) month = 8;
            else if (input.equals("september") || input.equals("sept.") || input.equals("sep") || input.equals("9")) month = 9;
            else if (input.equals("october") || input.equals("oct.") || input.equals("oct") || input.equals("10")) month = 10;
            else if (input.equals("november") || input.equals("nov.") || input.equals("nov") || input.equals("11")) month = 11;
            else if (input.equals("december") || input.equals("dec.") || input.equals("dec") || input.equals("12")) month = 12;
            if (month != 0) {
                int days = 0;
                boolean isLeap = (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
                switch (month) {
                    case 1: case 3: case 5: case 7: case 8: case 10: case 12: days = 31; break;
                    case 4: case 6: case 9: case 11: days = 30; break;
                    case 2: days = isLeap ? 29 : 28; break;
                }
                System.out.println("thang " + month + " nam " + year + " co " + days + " ngay");
                break;
            } else {
                System.out.println("thang khong chuan, nhap lai");
            }
        }
        sc.close();
    }
}
