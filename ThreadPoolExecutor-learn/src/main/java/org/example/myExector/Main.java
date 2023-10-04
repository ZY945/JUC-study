package org.example.myExector;

import org.example.myExector.MyThreadPoolExecutor;

/**
 * @author 伍六七
 * @date 2023/8/6 17:01
 */
public class Main {
    public static void main(String[] args) {
        MyThreadPoolExecutor threadPool = new MyThreadPoolExecutor(5);
// 提交任务
        threadPool.submit(() -> {
            // 任务逻辑
            // Thread-0
            System.out.println(Thread.currentThread().getName()+"执行中...");
            while (true){
            }
        });

        threadPool.submit(() -> {
            // 任务逻辑
            // Thread-1
            System.out.println(Thread.currentThread().getName()+"执行中...");
            while (true){
            }
        });

        System.out.println(threadPool.getActiveThreadCount());

// 关闭线程池
//        threadPool.shutdown();
    }
}