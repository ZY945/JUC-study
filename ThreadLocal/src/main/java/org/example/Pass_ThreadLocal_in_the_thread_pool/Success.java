package org.example.Pass_ThreadLocal_in_the_thread_pool;

import com.alibaba.ttl.TransmittableThreadLocal;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 使用transmittable-thread-local
 *
 * @author dongfeng
 * 2024-02-23 11:48
 */
public class Success {
    public static void main(String[] args) {
        TransmittableThreadLocal<String> context = new TransmittableThreadLocal<>();

        context.set("Hello, World!");

        ExecutorService executorService = Executors.newFixedThreadPool(2);

        executorService.submit(() -> {
            System.out.println(Thread.currentThread().getName() + "get threadLocal value: " + context.get());
        });
        executorService.submit(() -> {
            System.out.println(Thread.currentThread().getName() + "get threadLocal value: " + context.get());
        });
        // pool-1-thread-2get threadLocal value: Hello, World!
        // pool-1-thread-1get threadLocal value: Hello, World!
        executorService.shutdown();
    }
}
