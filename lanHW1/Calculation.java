public class Calculation {
    private static final int TIME_MONEY = 180;   
    private static final int WEEK_OVERTIME = 60;      
    private static final double RATE1 = 0.8;     
    private static final double RATE2 = 1.2;
    private static final double RATE3 = 1.6;     
     
    // double TimeMoney1 = TIME_MONEY * 0.8, TimeMoney2 = TIME_MONEY * 1.2, TimeMoney3 = TIME_MONEY * 1.6;
    public int calculateSalary(int hours) {
        if (hours < 0) {
            throw new IllegalArgumentException("輸入錯誤，工時不該為負數。");
        }

        if (hours > WEEK_OVERTIME) {
            hours = WEEK_OVERTIME;  
        }

        int salary = 0;

        if (hours <= 40) {
            salary = (int)(hours * RATE1 * TIME_MONEY);
        } else if (hours <= 50) {
            salary = (int)((40 * RATE1 * TIME_MONEY) + ((hours - 40) * RATE2 * TIME_MONEY));
        } else {
            salary = (int)((40 * RATE1 * TIME_MONEY) + (10 * RATE2 * TIME_MONEY) + ((hours - 50) * RATE3 * TIME_MONEY));
        }

        return (int) Math.round(salary);
    }

    public void printSalaryDetails(int hours) {
        if (hours < 0) {
            throw new IllegalArgumentException("輸入錯誤，工時不該為負數。");
        }

        if (hours > WEEK_OVERTIME) {
            System.out.println("已超過規定工時上限，共超過 " + (hours - WEEK_OVERTIME) + " 小時，超過時數不列為薪資計算。");
            hours = WEEK_OVERTIME;
        }

        int salary = calculateSalary(hours);
        System.out.println("計算總薪資為: " + salary);
        System.out.println("薪資明細:");

        int h1 = 0, h2 = 0, h3 = 0;
        double pay1 = 0, pay2 = 0, pay3 = 0;

        if (hours <= 40) {
            h1 = hours;
            pay1 = h1 * RATE1 * TIME_MONEY;
        } else if (hours <= 50) {
            h1 = 40;
            h2 = hours - 40;
            pay1 = h1 * RATE1 * TIME_MONEY;
            pay2 = h2 * RATE2 * TIME_MONEY;
        } else {
            h1 = 40;
            h2 = 10;
            h3 = hours - 50;
            pay1 = h1 * RATE1 * TIME_MONEY;
            pay2 = h2 * RATE2 * TIME_MONEY;
            pay3 = h3 * RATE3 * TIME_MONEY;
        }

        if (h1 > 0) {
            System.out.println("1. 薪資: " + h1 + " x " + RATE1 + " x " + TIME_MONEY + " = " + Math.round(pay1));
        }
        if (h2 > 0) {
            System.out.println("2. 薪資: " + h2 + " x " + RATE2 + " x " + TIME_MONEY + " = " + Math.round(pay2));
        }
        if (h3 > 0) {
            System.out.println("3. 薪資: " + h3 + " x " + RATE3 + " x " + TIME_MONEY + " = " + Math.round(pay3));
        }
    }
}
