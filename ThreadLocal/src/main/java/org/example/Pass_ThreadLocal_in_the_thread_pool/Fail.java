package org.example.Pass_ThreadLocal_in_the_thread_pool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * threadLocal是线程私有的
 * 每个线程都会有一个独立的threadLocal副本，不会干扰其他线程的threadLocal副本。
 * 因此，在多线程环境下，可以使用threadLocal来存储线程相关的数据，避免线程安全问题。
 * @author dongfeng
 * 2024-02-23 11:42
 */
public class Fail {
    public static void main(String[] args) {
        ThreadLocal<String> threadLocal = new ThreadLocal<>();

        threadLocal.set("Hello, World!");

        ExecutorService executorService = Executors.newFixedThreadPool(2);

        executorService.submit(()->{
            System.out.println(Thread.currentThread().getName()+"get threadLocal value: " + threadLocal.get());
        });
        executorService.submit(()->{
            System.out.println(Thread.currentThread().getName()+"get threadLocal value: " + threadLocal.get());
        });
        // pool-1-thread-1get threadLocal value: null
        // pool-1-thread-2get threadLocal value: null
        executorService.shutdown();
    }
}