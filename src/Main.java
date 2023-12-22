import java.util.Random;

public class Main {
   static int money = 0;

    public static void main(String[] args) {
        MyThread inTread = new MyThread('+');
        MyThread outTread = new MyThread('-');

        inTread.start();
        outTread.start();
    }

    public static final Object KEY = new Object();

    public static void moneyOperation(int amount) throws InterruptedException {
        synchronized (KEY) {
            if (-amount <= money) {
                money += amount;
                if (amount > 0) System.out.println("Успешно! На вашем счету теперь " + money + " рублей");
                else System.out.println("Успешно! На вашем счету осталось " + money + " рублей");
            }
            else System.out.println("Не хватает!");
            Thread.sleep(1300);
        }

    }
}

class MyThread extends Thread {

    Random random;
    char sign;
    public boolean flag;

    public MyThread(char sign) {
        this.sign = sign;
        random = new Random();
        flag = true;
    }

    @Override
    public void run() {
        while (flag) {
            int amount = random.nextInt(1, 30) * 100 * (sign == '+' ? 1 : -1);
            try {
                Main.moneyOperation(amount);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
