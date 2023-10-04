import java.util.concurrent.atomic.AtomicInteger;

/**
 * 类名称:IncorrectVolatile
 * 类描述:volatile 不适用的场景
 *
 *
 * Volatile不能做到synchronized 那样的原子保护,
 * 不适合a++的场景
 *
 * 解决方法加锁
 *
 * @author 伍六七
 * @date 2022/10/14 10:14
 */
public class IncorrectVolatile implements Runnable {
     AtomicInteger a = new AtomicInteger();

    //原子类，统计执行了多少次
    AtomicInteger atomicInteger = new AtomicInteger();


    public static void main(String[] args) throws InterruptedException {
        IncorrectVolatile runnable = new IncorrectVolatile();
        Thread thread1 = new Thread(runnable);
        Thread thread2 = new Thread(runnable);

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();

        System.out.println("a++的结果"+runnable.a);
        System.out.println("运行a++的次数"+runnable.atomicInteger.get());
    }
    @Override
    public  void run() {
            for (int i = 0; i < 10000; i++) {
                a.getAndIncrement();
                atomicInteger.incrementAndGet();
            }
    }
}