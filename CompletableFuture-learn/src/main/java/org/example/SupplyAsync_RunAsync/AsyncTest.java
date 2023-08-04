package org.example.SupplyAsync_RunAsync;

import org.example.Task;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * CompletableFuture---执行任务<br/>
 * supplyAsync--异步执行任务，有返回值<br/>
 * runAsync--异步执行任务，无返回值<br/>
 *
 * @author 伍六七
 * @date 2023/8/4 10:54
 */
public class AsyncTest {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // 线程池
        ExecutorService executorService = Executors.newFixedThreadPool(10);


        ////////////////supplyAsync和runAsync////////////////


        // 异步执行,需要返回值
        CompletableFuture<String> task1 = CompletableFuture.supplyAsync(Task.getSupplierTask("第一个任务"), executorService);
        // 获取supplyAsync的返回值
        System.out.println(task1.get());


        // 异步执行,无返回值
        CompletableFuture<Void> task2 = CompletableFuture.runAsync(Task.getRunnableTask("第二个任务"), executorService);
        System.out.println(task2.get());
        System.out.println("------------------------------------------");
    }
}
