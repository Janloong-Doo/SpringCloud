package com.janloong.javabase.therad.synchronize;


/**
 * @author <a href ="mailto: janloongdoo@gmail.com">Janloong</a>
 * @date 2018-03-04 21:39
 */
public class BankOperate {

    /**
     * 卡类
     *
     * @author <a href ="mailto: janloongdoo@gmail.com">Janloong</a>
     * @date 2018/3/4 22:09
     **/
    class Card {

        /*余额初始化*/
        private double balance;

        Card(double balance) {
            this.balance = balance;
        }

        /*获取余额方法*/
        double Get_balance() {
            return this.balance;
        }

        /*存款方法*/
        public void deposit(double count) throws InterruptedException {
            synchronized (this){
                System.out.println("存钱线程:存入金额=" + count);
                double now = balance + count;
                Thread.sleep(100);
                balance = now;
                System.out.println("存钱线程:当前金额=" + balance);
            }
        }

        /*取款方法*/
        public void withdraw(double count) throws InterruptedException {
            synchronized (this){
                System.out.println("取钱线程:取出金额=" + count);
                double now = balance - count;
                Thread.sleep(200);
                balance = now;
                System.out.println("取钱线程:当前金额=" + balance);
            }
        }
    }

    public class DepositThread extends Thread {
        private Card card;

        DepositThread(Card card) {
            this.card = card;
        }

        @Override
        public void run() {
            try {
                card.deposit(100);
            } catch (Exception e) {
                System.out.println(e.toString());
            }
        }
    }

    public class WithdrawThread extends Thread {
        private Card card;

        public WithdrawThread(Card card) {
            this.card = card;
        }

        @Override
        public void run() {
            try {
                card.withdraw(50);
            } catch (Exception e) {
                System.out.println(e.toString());
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        new BankOperate().initTest();
    }

    private void initTest() throws InterruptedException {
        Card card = new Card(100);
        System.out.println("操作前余额：" + card.Get_balance());
        DepositThread depositThread = new DepositThread(card);
        WithdrawThread withdrawThread = new WithdrawThread(card);
        depositThread.start();
        withdrawThread.start();
        Thread.sleep(2000);
        System.out.println("最终余额：" + card.Get_balance());
    }
}
