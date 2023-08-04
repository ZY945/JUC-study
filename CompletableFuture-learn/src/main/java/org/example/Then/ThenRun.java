package org.example.Then;

import org.example.Task;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * thenRun---Runnable<br/>
 * 无入参,无返回<br/>
 *
 * @author 伍六七
 * @date 2023/8/4 10:52
 */
public class ThenRun {
    public static void main(String[] args) {
        // 线程池
        ExecutorService executorService = Executors.newFixedThreadPool(10);

        // pool-1-thread-1
        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(Task.getSupplierTask("第一个任务"), executorService);
        // main
        future1.thenRun(Task.getRunnableTask("第二个任务"));
        // ForkJoinPool.commonPool-worker-9
        future1.thenRunAsync(Task.getRunnableTask("第三个任务"));
        // pool-1-thread-2
        future1.thenRunAsync(Task.getRunnableTask("第四个任务"), executorService);

    }
}
