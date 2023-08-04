package org.example.Then;

import org.example.Task;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * thenApply--Function<br/>
 * 有入参,有返回<br/>
 *
 * @author 伍六七
 * @date 2023/8/4 10:58
 */
public class ThenApply {
    public static void main(String[] args) {
        // 线程池
        ExecutorService executorService = Executors.newFixedThreadPool(10);

        // pool-1-thread-1
        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(Task.getSupplierTask("第一个任务"), executorService);
        // main
        future1.thenApply(Task.getFunctionTask("第二个任务"));
        // ForkJoinPool.commonPool-worker-9
        future1.thenApplyAsync(Task.getFunctionTask("第三个任务"));
        // pool-1-thread-2
        future1.thenApplyAsync(Task.getFunctionTask("第四个任务"), executorService);

    }
}
