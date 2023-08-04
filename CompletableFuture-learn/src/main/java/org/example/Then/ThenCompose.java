package org.example.Then;

import org.example.Task;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * thenCompose----Function---参数(上一个future的结果)<br/>
 * 返回一个新的 CompletableFuture 对象。<br/>
 *
 * @author 伍六七
 * @date 2023/8/4 10:59
 */
public class ThenCompose {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // 线程池
        ExecutorService executorService = Executors.newFixedThreadPool(10);

        // pool-1-thread-1
        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(Task.getSupplierTask("第一个任务"), executorService);
        // main
        CompletableFuture<String> future2 = future1.thenCompose(result -> {
            // 将字符串转换为大写
            System.out.println(Thread.currentThread().getName() + "(future2)");
            return CompletableFuture.supplyAsync(result::toUpperCase);
        });
        // ForkJoinPool.commonPool-worker-9
        CompletableFuture<String> future3 = future2.thenComposeAsync(result -> {
            // 将字符串转换为大写
            System.out.println(Thread.currentThread().getName() + "(future3)");
            return CompletableFuture.supplyAsync(result::toLowerCase);

        });
        // pool-1-thread-2
        CompletableFuture<String> future4 = future1.thenComposeAsync(result -> {
            // 将字符串转换为大写
            System.out.println(Thread.currentThread().getName() + "(future4)");
            return CompletableFuture.supplyAsync(result::toUpperCase);
        }, executorService);

        future2.thenAccept(result -> {
            System.out.println("Result2: " + result);
        });
        future3.thenAccept(result -> {
            System.out.println("Result3: " + result);
        });
        future4.thenAccept(result -> {
            System.out.println("Result4: " + result);
        });
    }
}
