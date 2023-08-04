package wuliuqi.suo;

/**
 * @author 伍六七
 * @date 2022/10/13 16:13
 */

/**
 * 线程死锁
 */
public class sisuo {
    private static Object r1 = new Object();
    private static Object r2 = new Object();
    public static void main(String[] args) {
        new Thread(() -> {
            synchronized (r1){
                System.out.println(Thread.currentThread() + "get r1");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread()+"wait r2");
                synchronized (r2){
                    System.out.println(Thread.currentThread()+"get r2");
                }
            }
        },"线程1").start();
        new Thread(() -> {
            synchronized (r2){
                System.out.println(Thread.currentThread()+"get r2");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread()+"wait r1");
                synchronized (r1){
                    System.out.println(Thread.currentThread()+"get r1");
                }
            }
        },"线程2")
                .start();


        /**
         * 修改线程2-----破坏请求和保持
         * 预防死锁
         */
        new Thread(() -> {
            synchronized (r1){
                System.out.println(Thread.currentThread()+"get r1");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread() + "wait r2");
                synchronized (r2){
                    System.out.println(Thread.currentThread()+"get r2");
                }
            }
        },"线程2");
//                .start();
    }
}
