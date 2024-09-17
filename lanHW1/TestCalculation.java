public class TestCalculation {
    public static void main(String[] args) {
        Calculation calculator = new Calculation();
        boolean allTestsPassed = true;

        for (int hours = -200; hours <= 200; hours++) {
            System.out.println("工時: " + hours);

            boolean threwException = false;

            try {
                calculator.printSalaryDetails(hours);
            } catch (IllegalArgumentException e) {
                threwException = true;
                if (hours >= 0) {
                    System.out.println("程式錯誤，輸入數值: " + hours + "屬於正常工時計算，不應拋出異常。");
                    allTestsPassed = false;
                } else {
                    System.out.println(e.getMessage());
                }
            } catch (Exception e) {
                System.out.println("程式錯誤，輸入數值: " + hours + "，發生未預期的錯誤：" + e.getMessage());
                allTestsPassed = false;
            }

            if (hours < 0 && !threwException) {
                System.out.println("程式錯誤，輸入數值: " + hours + "屬於負數無法計算工程，應該拋出異常但未拋出。");
                allTestsPassed = false;
            }

            System.out.println("----------------------------------");
        }

        if (allTestsPassed) {
            System.out.println("測試成功");
        } else {
            System.out.println("測試失敗");
        }
    }
}
