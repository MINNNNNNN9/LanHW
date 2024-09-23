
import java.util.Scanner;

public class Hw3 {

    private int money = 50000;  
    private final String studentId = "122214240";
    private final String studentName = "李育民";
    private final int DAY_MAX = 30000;
    private int totalWithdraw = 0;
    Scanner scanner = new Scanner(System.in);

    
    void showBalance() {
        System.out.println("目前餘額為：" + money);
    }

    int depositMoney(int currentBalance) {
        showBalance();
        System.out.println("請輸入存款金額：");
        try {
            String input = scanner.nextLine();
            if (!input.matches("^[-0-9]+$")) {
                System.err.println("請輸入正確的數字");
                return 2;  // 表示存款失敗
            } else {
                int deposit = Integer.parseInt(input);
                if (deposit > 0) {
                    System.out.println("存款成功");
                    currentBalance += deposit;  
                    System.out.println("存款後餘額為：" + currentBalance);
                    money = currentBalance;  
                    return 1;  
                } else {
                    System.err.println("輸入錯誤,請輸入正整數");
                    return 2;  
                }
            }
        } catch (NumberFormatException e) {
            System.err.println("輸入錯誤,你輸入的範圍超過");
            return 2;
        }
    }
    

    int withdrawMoney(int currentBalance) {
        System.out.println("請輸入提款金額：");
        try{
        String input = scanner.nextLine();
        if (!input.matches("^[-0-9]+$")) {
            System.err.println("請輸入正確的數字");
            return -1;  
        } else {
            int withdraw = Integer.parseInt(input);
            if (withdraw > 0 && withdraw <= currentBalance) {  
                totalWithdraw += withdraw;
                if (totalWithdraw <= DAY_MAX) {
                    System.out.println("提款成功");
                    currentBalance -= withdraw;  
                    System.out.println("提款後餘額為：" + currentBalance);
                    money = currentBalance;  
                    return currentBalance;  
                } else if (withdraw < 0) {
                    System.err.println("提款失敗，輸入的數字為非正整數");
                    return -1;
                } else {
                    totalWithdraw -= withdraw;
                    System.err.println("提款失敗,提款已達每日最高上限 " + DAY_MAX + " 元");
                    return -1;  
                }
            } else if (withdraw > currentBalance) {
                System.err.println("餘額不足了");
                return -1;  
            } else {
                System.err.println("輸入錯誤,請輸入正整數");
                return -1;  
            }
        }
        }catch(NumberFormatException e){
            System.err.println("輸入錯誤,輸入的範圍超過");
                return -1;  
        }
    }
    void exitChoice(){
        while(true){
        System.out.println("你確定要離開嗎：(y)離開 （n)返回主畫面");
        String input = scanner.nextLine();
        if(input.matches("^[yY]$")){
            System.out.println("感謝使用");
            System.exit(0);
        }else if(!input.matches("^[yYnN]$")){
            System.err.println("請輸入正確的選擇，(y)離開,(n)返回主畫面");
        }else {
            break;
        }
        }
    }
    void start(){
        checkPassWord();
    }
    void checkPassWord() {
        System.out.println("請輸入你的密碼（學號後三碼）:");
        String passWord = scanner.nextLine();
        if (passWord.equals(studentId.substring(6, 9))) {
            System.out.println("歡迎光臨 " + studentName);
            menu();  
        } else {
            System.err.println("密碼錯誤，結束程式");
        }
    }

    void menu() {
        while (true) {
            System.out.println("＝＝＝＝ATM主畫面＝＝＝＝");
            System.out.println("(1)查詢餘額  (2)存款 (3)提款 (4)離開\n請輸入功能指令:");
            String choice = scanner.nextLine();
            if (choice.matches("^[0-9]$")) {
                if (choice.matches("^1$")) {
                    showBalance();  
                } else if (choice.matches("^2$")) {
                    int result = depositMoney(money);  
                    if (result == 1) {
                        System.out.println("存款操作成功");
                    } else {
                        System.err.println("存款操作失敗");
                    }
                } else if (choice.matches("^3$")) {
                    int result = withdrawMoney(money);  
                    if (result == -1) {
                        System.err.println("提款操作失敗");
                    } else {
                        System.out.println("提款操作成功，當前餘額：" + result);
                    }
                } else if (choice.matches("^4$")) {
                    exitChoice();
                } else {
                    System.err.println("輸入錯誤，請輸入 1~4 之間的選項");
                }
            } else {
                System.err.println("輸入錯誤，請輸入數字");
            }
        }
    }

    public static void main(String[] args) {
        Hw3 atm = new Hw3();
        atm.start();  
    }
}



