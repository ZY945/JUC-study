package org.example.two.bothOrErr;

import org.example.Task;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * CompletableFuture<Void><br/>
 * CompletableFuture.allOf(taskA,..);<br/>
 * 参数都是CompletableFuture<br/>
 * 如果任务有抛异常,那么get方法会抛出<br/>
 * 如果任务正常进行,那么get方法返回null<br/>
 *
 * @author 伍六七
 * @date 2023/8/4 17:00
 */
public class AllOf {
    public static void main(String[] args) throws Exception {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        // pool-1-thread-1
        CompletableFuture<String> taskA = CompletableFuture.supplyAsync(Task.getSupplierTask("第一次任务"), executorService);
        // pool-1-thread-2
        CompletableFuture<String> taskB = CompletableFuture.supplyAsync(Task.getLongTimeSupplierTask("第二次任务"), executorService);
        CompletableFuture<String> taskC = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            boolean status = true;
            if (status) {
                throw new RuntimeException("任务三抛出异常");
            }
            return "抛异常任务";
        }, executorService);

        // pool-1-thread-1
        // main 当两个任务执行过快时,会使用main线程,当两个任务时常不一致时,使用时常长的线程
        CompletableFuture<Void> future = CompletableFuture.allOf(taskA, taskB, taskC);
        System.out.println("---------result.get()---------");
        System.out.println(future.get());
        // 关闭线程池
        executorService.shutdown();
    }
}
