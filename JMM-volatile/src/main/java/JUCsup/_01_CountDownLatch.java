package JUCsup;

import java.util.concurrent.CountDownLatch;

/**
 * @author 伍六七
 * @date 2022/12/3 10:57
 */
public class _01_CountDownLatch {
    public static void main(String[] args) throws InterruptedException {
        //创建CountDownLatch对象，设置初始值
        CountDownLatch countDownLatch = new CountDownLatch(6);

        //6个同学陆续离开教室之后
        for (int i = 1; i <=6; i++) {
            new Thread(()->{
                System.out.println(Thread.currentThread().getName()+" 号同学离开了教室");
                //计数  -1
                countDownLatch.countDown();
            },String.valueOf(i)).start();
        }

        //等待
        countDownLatch.await();
        System.out.println(Thread.currentThread().getName()+" 班长锁门走人了");
    }
}
