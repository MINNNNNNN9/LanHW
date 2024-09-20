import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Calculation calculator = new Calculation();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            try {
                System.out.print("請輸入工時: ");
                String input = scanner.nextLine();

                double hoursInput = Double.parseDouble(input);

                int hours = (int) Math.round(hoursInput);

                calculator.printSalaryDetails(hours);
                break; 
            } catch (NumberFormatException e) {
                System.out.println("輸入錯誤，請輸入一個數值。");
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
   // scanner.close();
    }
}
