package org.example.ThreadPoolExecutor_learn;

import java.util.concurrent.*;

public class ThreadPoolExecutorFactory {
    public static ThreadPoolExecutor newThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, int queueCapacity, QueueType queueType) {
        BlockingQueue<Runnable> workQueue;

        switch (queueType) {
            //ArrayBlockingQueue：基于数组的有界阻塞队列。需要指定队列的容量，当队列已满时，新的任务将等待队列中的任务被消费掉后才能添加进来。
            case ARRAY_BLOCKING_QUEUE:
                workQueue = new ArrayBlockingQueue<>(queueCapacity);
                break;
            //LinkedBlockingQueue：基于链表的可选有界或无界阻塞队列。如果没有指定容量，它将创建一个无界队列。
            case LINKED_BLOCKING_QUEUE:
                workQueue = new LinkedBlockingQueue<>();
                break;
            //SynchronousQueue：一个不存储元素的阻塞队列，每个插入操作必须等待另一个线程的对应删除操作，反之亦然。它适用于传递性场景，其中一个线程产生任务，而另一个线程消费任务。
            case SYNCHRONOUS_QUEUE:
                workQueue = new SynchronousQueue<>();
                break;
            default:
                throw new IllegalArgumentException("Invalid queue type: " + queueType);
        }

        return new ThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
    }

    public enum QueueType {
        ARRAY_BLOCKING_QUEUE,
        LINKED_BLOCKING_QUEUE,
        SYNCHRONOUS_QUEUE
    }
}