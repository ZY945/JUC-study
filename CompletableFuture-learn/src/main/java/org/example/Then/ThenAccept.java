package org.example.Then;

import org.example.Task;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * thenAccept---Consumer--有入参无返回的任务<br/>
 * thenAccept() 方法的回调函数在主线程中执行，<br/>
 * thenAcceptAsync() 方法的回调函数则会在新的异步线程池中执行(不设置线程池则使用默认的)。<br/>
 * 有入参,无返回<br/>
 *
 * @author 伍六七
 * @date 2023/8/4 10:59
 */
public class ThenAccept {
    public static void main(String[] args) {
        // 线程池
        ExecutorService executorService = Executors.newFixedThreadPool(10);

        // pool-1-thread-1
        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(Task.getSupplierTask("第一个任务"), executorService);
        // main
        future1.thenAccept(Task.getConsumer("第二个任务"));
        // ForkJoinPool.commonPool-worker-9
        future1.thenAcceptAsync(Task.getConsumer("第三个任务"));
        // pool-1-thread-2
        future1.thenAcceptAsync(Task.getConsumer("第四个任务"), executorService);

    }
}
