package org.example.myExector;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * 自定义线程池执行器
 */
public class MyThreadPoolExecutor {
    private final int poolSize; // 线程池大小
    private final BlockingQueue<Runnable> taskQueue; // 任务队列，用于存储待执行的任务
    private final WorkerThread[] workers; // 工作线程数组

    public MyThreadPoolExecutor(int poolSize) {
        this.poolSize = poolSize;
        taskQueue = new LinkedBlockingQueue<>();
        workers = new WorkerThread[poolSize];

        for (int i = 0; i < poolSize; i++) {
            workers[i] = new WorkerThread();
            workers[i].start();
        }
    }

    public void submit(Runnable task) {
        try {
            taskQueue.put(task);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public void shutdown() {
        for (WorkerThread worker : workers) {
            worker.interrupt();
        }
    }

    public int getActiveThreadCount() {
        int count = 0;
        for (WorkerThread worker : workers) {
            if (worker.isTaskRunning()) {
                count++;
            }
        }
        return count;
    }

    private class WorkerThread extends Thread {
        private boolean taskRunning = false;

        public void run() {
            while (!Thread.currentThread().isInterrupted()) {
                try {
                    Runnable task = taskQueue.take();
                    taskRunning = true;
                    task.run();
                    taskRunning = false;
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }

        public boolean isTaskRunning() {
            return taskRunning;
        }
    }
}