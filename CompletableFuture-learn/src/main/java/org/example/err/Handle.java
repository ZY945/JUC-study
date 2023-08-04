package org.example.err;

import java.util.concurrent.*;

/**
 * @author 伍六七
 * @date 2023/8/4 15:53
 */
public class Handle {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        // pool-1-thread-1
        CompletableFuture<Integer> cf1 = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName() + " cf1 do something....");
            String res = "zy";
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            if (res.equals("zy")) {
                throw new RuntimeException("抛出异常");
            }
            return 1;
        }, executorService);

        // pool-1-thread-2
        CompletableFuture<String> cf2 = cf1.handle((result, e) -> {
            if (result != null) {
                System.out.println("上个任务结果：" + result);
                System.out.println(Thread.currentThread().getName() + " cf2 do something....");
                return "result";
            } else if (e != null) {
                System.out.println("上个任务抛出异常：" + e);
                System.out.println("执行针对异常的处理");
                return "err";
            }
            return null;
        });
        // ForkJoinPool.commonPool-worker-9
        CompletableFuture<String> cf3 = cf1.handleAsync((result, e) -> {
            if (result != null) {
                System.out.println("上个任务结果：" + result);
                System.out.println(Thread.currentThread().getName() + " cf3 do something....");
                return "result";
            } else if (e != null) {
                System.out.println("上个任务抛出异常：" + e);
                System.out.println("执行针对异常的处理");
                return "err";
            }
            return null;
        });
        // pool-1-thread-2
        CompletableFuture<String> cf4 = cf1.handleAsync((result, e) -> {
            if (result != null) {
                System.out.println("上个任务结果：" + result);
                System.out.println(Thread.currentThread().getName() + " cf4 do something....");
                return "result";
            } else if (e != null) {
                System.out.println("上个任务抛出异常：" + e);
                System.out.println("执行针对异常的处理");
                return "err";
            }
            return null;
        }, executorService);

//        //等待任务1执行完成
//        System.out.println("cf1结果->" + cf1.get());
//        //等待任务2执行完成
        System.out.println("cf2结果->" + cf2.get());
        System.out.println("cf3结果->" + cf3.get());
        System.out.println("cf4结果->" + cf4.get());
    }
}
