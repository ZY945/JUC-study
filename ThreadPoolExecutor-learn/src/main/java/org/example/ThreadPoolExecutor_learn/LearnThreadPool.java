package org.example.ThreadPoolExecutor_learn;

import java.util.Date;
import java.util.concurrent.*;

/**
 * <pre>{@code
 *         public static void main(String[] args) {
 *         ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor();
 *     }
 *     }
 * </pre>
 * @author 伍六七
 * @date 2022/11/6 23:13
 */
public class LearnThreadPool {
    public static void main(String[] args) throws InterruptedException {
        CachedThreadPool();
    }

    public static void FixedThreadPool(){
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        ThreadPoolExecutor executorService1 = (ThreadPoolExecutor) executorService;
        System.out.println("CorePoolSize:"+executorService1.getCorePoolSize());
        System.out.println("PoolSize:"+executorService1.getPoolSize());
        executorService.shutdown();
    }

    /**
     * CachedThreadPool内都是非核心线程
     * 初始化是0,每天一个任务,线程数+1,默认60秒没使用的话，就会被回收
     */
    public static void CachedThreadPool() throws InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        ThreadPoolExecutor executorService1 = (ThreadPoolExecutor) executorService;
        System.out.println("CorePoolSize:"+executorService1.getCorePoolSize());
        System.out.println("PoolSize:"+executorService1.getPoolSize());
        for (int i = 0; i < 5; i++) {

            executorService.execute(()->{
                try {
                    Thread.sleep(2000L);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            });
            System.out.println("CorePoolSize:"+executorService1.getCorePoolSize());
            System.out.println("PoolSize:"+executorService1.getPoolSize());
        }
        Thread.sleep(5000L);
        System.out.println("CorePoolSize:"+executorService1.getCorePoolSize());
        System.out.println("PoolSize:"+executorService1.getPoolSize());
        executorService.shutdown();
    }
}
