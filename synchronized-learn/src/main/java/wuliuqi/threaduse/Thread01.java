package wuliuqi.threaduse;

/**
 * @author 伍六七
 * @date 2022/8/1 11:52
 */
public class Thread01 {
    public static void main(String[] args) {
        Basketball basketball = new Basketball();
        //Runnable接口无法实现start方法
        basketball.start();
        //通过代理模式
        Thread thread = new Thread(basketball);
        thread.start();
        System.out.println("主线程开启");
        for (int i = 0; i < 5; i++) {
            System.out.println("主线程" + i + Thread.currentThread().getName());
        }
    }
}

class Basketball extends Thread {
    @Override
    public void run() {
        System.out.println("子线程开启");
        int res = 0;
        while (res < 60) {
            System.out.println("zy投中三分" + (++res) + "个" + Thread.currentThread().getName());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
