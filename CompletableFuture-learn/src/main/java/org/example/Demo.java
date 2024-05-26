package org.example;

import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * CompletableFuture.supplyAsync()
 * thenApply
 * exceptionally
 * thenAccept
 * thenRun
 * thenCombine
 * thenCompose
 *
 * @author dongfeng
 * 2024-03-02 18:28
 */
public class Demo {
    public static int compute(int n) {
        return 2 * n;
    }

    /**
     * CompletableFuture.supplyAsync()来创建CF
     */
    public static CompletableFuture<Integer> create(int n) {
        return CompletableFuture.supplyAsync(() -> compute(n));
    }

    public static void main(String[] args) {
//        cf_Combine_cf();
//        data_or_cf();
//        cf_Combine_cf();
        cf_all_of();

    }

    /**
     * 单个CF的方法()
     * thenApply:返回CF<T>类比stream流的map方法 参数(Function)
     * exceptionally:返回CF<T>
     * thenAccept:返回CF<Void>类比stream流的forEach方法
     * thenRun:返回CF<Void>
     */
    public static void first_demo() {
        create(0)
                .thenApply(data -> {
                    System.out.println("thenApply,返回CF<Integer>类比stream流的map方法");
                    return data + 1.0;
                })
                .exceptionally(throwable -> {
                    System.out.println("捕获到异常:" + throwable.getMessage() + "返回默认值1.0(返回值的类型是exceptionally方法前的CF<>来决定");
                    return 1.0;
                })// CompletableFuture<Integer>
                .thenAccept(data -> {
                    System.out.println("thenAccept,返回CF<Void>类比stream流的foreach方法,dada:" + data);
                })// CompletableFuture<Void>
                .thenAccept(data -> {
                    System.out.println("dada:" + data + ",data为null,因为上一个thenAccept返回CF<Void>");//
                })
                .thenRun(() -> {
                    System.out.println("thenRun,返回CF<Void>");
                });
    }

    /**
     * thenApply return data
     * thenCompose return cf
     */
    public static void data_or_cf() {
        create(2)
//                .thenApply(data->compute(data))
                .thenCompose(data -> create(data))
                .thenAccept(data -> System.out.println("data:" + data));
    }

    /**
     * 第一个场景,三个服务请求的响应值,最后进行复合计算
     * 消耗的总时间是三个服务请求时间中最长的那个
     */
    public static void cf_Combine_cf() {
        CompletableFuture<Integer> cf1 = create(1);
        CompletableFuture<Integer> cf2 = create(2);
        CompletableFuture<Integer> cf3 = create(3);

        cf1.thenCombine(cf2, (v1, v2) -> v1 + v2)
                .thenCombine(cf3, (v1, v2) -> {
                    try {
                        Thread.sleep(1000L);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    return v1 + v2;
                })
                .thenAccept(System.out::println);
        System.out.println("-main-");
    }

    /**
     * 多个任务的应用场景--Allof
     */
    public static void cf_all_of() {
        ExecutorService executorService = Executors.newFixedThreadPool(4);
        ArrayList<String> list = new ArrayList<>();
        long start = System.currentTimeMillis();
        CompletableFuture.allOf(
                CompletableFuture.runAsync(() -> {
                    list.add(sql_task(1000));
                },executorService),
                CompletableFuture.runAsync(() -> {
                    list.add(sql_task(2000));
                },executorService),
                CompletableFuture.runAsync(() -> {
                    list.add(sql_task(3000));
                },executorService),
                CompletableFuture.runAsync(() -> {
                    list.add(sql_task(4000));
                },executorService)).join();
        long end = System.currentTimeMillis();
        System.out.println(list);
        System.out.println("花费时间"+(end - start));
        executorService.shutdown();
        try {
            executorService.awaitTermination(10, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }


    public static String sql_task(long time) {
        try {
            Thread.sleep(time);
            return String.valueOf(time);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
