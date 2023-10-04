package org.example.interview;

import java.util.concurrent.*;

/**
 * @author 伍六七
 * @date 2023/8/31 21:05
 */
public class SequentialExecution_01 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        testBySemaphore();
    }

    /**
     * 通过CompletableFuture的thenRun实现顺序执行
     * @throws ExecutionException
     * @throws InterruptedException
     */
    private static void testByCompletableFuture() throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        CompletableFuture<Void> future = CompletableFuture.runAsync(new Thread1(), executorService).thenRun(new Thread2()).thenRun(new Thread3());
        future.get();
        executorService.shutdown();
    }

    /**
     *  通过线程的join方法实现顺序执行
     * @throws ExecutionException
     * @throws InterruptedException
     */
    private static void testByJoin() throws ExecutionException, InterruptedException {
        Thread thread1 = new Thread(() -> {
            System.out.println("线程1");
        });
        Thread thread2 = new Thread(() -> {
            try {
                thread1.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("线程2");
        });
        Thread thread3 = new Thread(() -> {
            try {
                thread2.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("线程3");
        });

        thread1.start();
        thread2.start();
        thread3.start();
    }

    /**
     *  通过Semaphore实现顺序执行
     * @throws InterruptedException
     */
    private static void testBySemaphore() throws InterruptedException {
        Semaphore semaphore1 = new Semaphore(1);
        Semaphore semaphore2 = new Semaphore(1);

        //获取
        semaphore1.acquire();
        Thread thread1 = new Thread(() -> {
            System.out.println("线程1");
            semaphore1.release();
        });
        semaphore2.acquire();
        Thread thread2 = new Thread(() -> {
            try {
                semaphore1.acquire();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("线程2");
            semaphore2.release();
        });
        Thread thread3 = new Thread(() -> {
            try {
                semaphore2.acquire();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("线程3");
        });

        thread1.start();
        thread2.start();
        thread3.start();
    }
}
class Thread1 implements Runnable{

    @Override
    public void run() {
        System.out.println("线程1");
    }
}
class Thread2 implements Runnable{

    @Override
    public void run() {
        System.out.println("线程2");
    }
}
class Thread3 implements Runnable{

    @Override
    public void run() {
        System.out.println("线程3");
    }
}