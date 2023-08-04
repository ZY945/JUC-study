package wuliuqi.yeild_join;

/**
 * @author 伍六七
 * @date 2022/8/1 14:31
 */
public class ThreadMethod_join {
    public static void main(String[] args) throws InterruptedException {
        ThreadTest01 threadTest01 = new ThreadTest01();
        threadTest01.setDaemon(true);
        threadTest01.start();
        for (int i = 0; i < 20; i++) {
            Thread.sleep(50);
            System.out.println("主线程");
        }
    }
}

class ThreadTest01 extends Thread {

    @Override
    public void run() {
        int i = 10;
        while (i-- > 0) {
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            String name = Thread.currentThread().getName();
            System.out.println(name + "执行了");
        }
    }
}
