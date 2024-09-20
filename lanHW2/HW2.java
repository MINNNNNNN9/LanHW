import java.util.Scanner;


class NegativeNumberException extends Exception {
    public NegativeNumberException(String message) {
        super(message);
    }
}

class ATM {
    String ID, PWD;

    ATM(String gID, String gPWD) {
        this.ID = gID;
        this.PWD = gPWD;
    }

    int login(String gID, String gPWD) {
        if (this.ID.equals(gID) && this.PWD.equals(gPWD)) {
            return 1; // 登入成功
        } else if (this.ID.equals(gID)) {
            return 0; // 帳號對, 密碼錯
        } else {
            return -1; // 帳號密碼都錯誤
        }
    }
}

public class HW2 {
    static Scanner scanner = new Scanner(System.in);
    static ATM Mygrade = new ATM("122214240", "4240");

       public static void main(String[] args) {
        int i = 3, money = 0;
        boolean success = false;

        OUTER:
        while (i > 0 && !success) {
            try {
                System.out.print("請輸入帳號: ");
                String userID = scanner.nextLine().trim(); 

                
                if (userID.isEmpty()) {
                    throw new IllegalArgumentException("帳號不能為空或包含空格，請重新輸入");
                }

                System.out.print("請輸密碼: ");
                String userPWD = scanner.nextLine().trim(); 

      
                if (userPWD.isEmpty()) {
                    throw new IllegalArgumentException("密碼不能為空或包含空格，請重新輸入");
                }

                int result = Mygrade.login(userID, userPWD);
                switch (result) {
                    case 1 -> {
                        System.err.println("登入成功");
                        break OUTER;
                    }
                    case 0 -> System.err.println("密碼錯誤");
                    default -> System.err.println("帳號或密碼輸入錯誤");
                }
                i--;
                System.out.println("您還剩下 " + i + " 次機會");

            } catch (IllegalArgumentException e) {
                System.err.println(e.getMessage());
            }
        }

        if (i > 0) {
            while (true) {
                System.out.println("歡迎光臨國北護ATM,請輸入要使用的功能:");
                System.out.println("請輸入選項: (1)查詢餘額 (2)存款 (3)提款 (e)離開");
                String choice = scanner.nextLine().trim(); 

                try {
                    
                    if (choice.isEmpty()) {
                        throw new IllegalArgumentException("請勿輸入空白選項，請重新輸入");
                    }

                    if ("e".equalsIgnoreCase(choice)) {
                        break; 
                    }

                    int choiceConvert = Integer.parseInt(choice);
                    switch (choiceConvert) {
                        case 1 -> System.out.println("您目前帳戶的餘額為：" + money);
                        case 2 -> {
                            System.out.println("歡迎使用存款功能");
                            try {
                                System.out.print("請輸入您要存款的金額：");
                                String inPutMoney = scanner.nextLine().trim();
                                if (inPutMoney.isEmpty()) {
                                    throw new IllegalArgumentException("存款金額不能為空或包含空格，請重新輸入");
                                }
                                int setMoney = Integer.parseInt(inPutMoney);
                                if (setMoney > 0) {
                                    money += setMoney;
                                    System.out.println("存款成功,目前餘額為：" + money);
                                } else if (setMoney < 0) {
                                    throw new NegativeNumberException("輸入的數字不能為負數");
                                } else {
                                    System.out.println("存款失敗,你輸入的是 0 金額!");
                                }
                            } catch (NumberFormatException e) {
                                System.err.println("輸入的不是有效的數字，請重新輸入");
                            } catch (NegativeNumberException e) {
                                System.err.println(e.getMessage());
                            }
                        }
                        case 3 -> {
                            System.out.println("歡迎使用提款功能");
                            try {
                                System.out.print("請輸入提款的金額：");
                                String inPutMoney = scanner.nextLine().trim();
                                if (inPutMoney.isEmpty()) {
                                    throw new IllegalArgumentException("提款金額不能為空或包含空格，請重新輸入");
                                }
                                int drawMoney = Integer.parseInt(inPutMoney);
                                if (drawMoney > 0 && drawMoney <= money) {
                                    money -= drawMoney;
                                    System.out.println("提款成功,目前餘額為：" + money);
                                } else if (drawMoney < 0) {
                                    throw new NegativeNumberException("輸入的數字不能為負數");
                                } else {
                                    System.out.println("提款失敗,你輸入的金額超過餘額!");
                                }
                            } catch (NumberFormatException e) {
                                System.err.println("輸入的不是有效的數字，請重新輸入");
                            } catch (NegativeNumberException e) {
                                System.err.println(e.getMessage());
                            }
                        }
                        default -> System.out.println("無效的選項，請重新輸入");
                    }

                } catch (NumberFormatException e) {
                    System.err.println("你輸入的並非有效的數字選項,請重新輸入");
                } catch (IllegalArgumentException e) {
                    System.err.println(e.getMessage());
                }
            }
        } 
        else {
            System.err.println("輸入三次失敗,請查詢後再重新輸入");
        }
        System.out.print("感謝使用");
        scanner.close();
    }
}