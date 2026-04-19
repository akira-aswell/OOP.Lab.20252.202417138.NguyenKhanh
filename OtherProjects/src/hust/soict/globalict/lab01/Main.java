import javax.swing.JOptionPane;
public class Main {
    public static void main(String[] args) {
        String input1 = JOptionPane.showInputDialog("nhap so 1:");
        double num1 = Double.parseDouble(input1);
        String input2 = JOptionPane.showInputDialog("nhap so 2:");
        double num2 = Double.parseDouble(input2);
        String result = "tong: " + (num1 + num2) + "\n" + "hieu: " + (num1 - num2) + "\n" + "tich: " + (num1 * num2) + "\n" + "thuong: " + (num1 / num2);
        JOptionPane.showMessageDialog(null, result);
    }
}

