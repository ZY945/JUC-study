package org.example;

import java.util.concurrent.CompletableFuture;

/**
 * CompletableFuture.supplyAsync()
 * thenApply
 * exceptionally
 * thenAccept
 * thenRun
 * thenCombine
 * thenCompose
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
        cf_Combine_cf();
    }

    /**
     * 单个CF的方法()
     * thenApply:返回CF<T>类比stream流的map方法 参数(Function)
     * exceptionally:返回CF<T>
     * thenAccept:返回CF<Void>类比stream流的forEach方法
     * thenRun:返回CF<Void>
     */
    public static void first_demo(){
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
                    System.out.println("thenAccept,返回CF<Void>类比stream流的foreach方法,dada:"+data);
                })// CompletableFuture<Void>
                .thenAccept(data -> {
                    System.out.println("dada:"+data+",data为null,因为上一个thenAccept返回CF<Void>");//
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
                .thenCompose(data->create(data))
                .thenAccept(data->System.out.println("data:"+data));
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
}
