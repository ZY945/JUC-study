package wuliuqi.ticket;

/**
 * @author 伍六七
 * @date 2022/8/1 13:56
 */
public class SellTicket {
    public static void main(String[] args) {
        //Runnable
        SellTicket02 sellTicket = new SellTicket02();
        new Thread(sellTicket).start();
        new Thread(sellTicket).start();
        new Thread(sellTicket).start();
    }
}

//Runnable接口
class SellTicket02 implements Runnable {

    private static int ticketNum = 100;
    boolean Loop = true;
    private static final Object lock = new Object(); //必须是静态的。

    public void Sell() {
        synchronized (lock) {
            if (ticketNum <= 0) {
                Loop = false;
                System.out.println("没票了");
                return;
            }
            System.out.println("窗口" + Thread.currentThread().getName() + "抢到了票" + "剩余票" + (--ticketNum));
        }
    }

    @Override
    public void run() {
        while (Loop) {
            Sell();
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}