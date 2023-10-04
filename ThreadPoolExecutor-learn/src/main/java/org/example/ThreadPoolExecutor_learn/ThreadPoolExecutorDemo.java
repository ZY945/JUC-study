package org.example.ThreadPoolExecutor_learn;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author 伍六七
 * @date 2023/8/6 18:21
 */
public class ThreadPoolExecutorDemo {
    public static void main(String[] args) {
        extracted();
    }

    private static void extracted() {
        int corePoolSize = 1;
        int maximumPoolSize = 5;
        long keepAliveTime = 1;
        TimeUnit unit = TimeUnit.SECONDS;
        int queueCapacity = 2; // 队列容量

        ArrayBlockingQueue<Runnable> workQueue = new ArrayBlockingQueue<>(queueCapacity);

        ThreadPoolExecutor executor = new ThreadPoolExecutor(
                corePoolSize,
                maximumPoolSize,
                keepAliveTime,
                unit,
                workQueue,
                new ThreadPoolExecutor.AbortPolicy()
        );

        // 提交任务到线程池执行...
        for (int i = 0; i < 5; i++) {

            executor.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println(Thread.currentThread().getName() + "正在执行...");
                    try {
                        Thread.sleep(10000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            });
        }

        // 关闭线程池
        executor.shutdown();
    }
}
