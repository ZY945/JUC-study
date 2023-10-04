package io.example.thread;

import java.io.IOException;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 打印0-100按线程顺序
 */
public class Main {
    public static volatile int i = 0;
    public static ReentrantLock lock = new ReentrantLock();
    public static  Condition condition1 = lock.newCondition();
    public static  Condition condition2 = lock.newCondition();
    public static  Condition condition3 = lock.newCondition();
    public static void main(String[] args) throws IOException {


        Thread thread1 = new Thread(() -> {
            while (i <= 100) {

                try {
                    lock.lock();
                    if(i%3==0){
                        System.out.println(Thread.currentThread().getName()+"i的值:"+i);
                        i++;
                        condition2.signal();
                    }else{
                        condition1.await();
                    }
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                } finally {
                    lock.unlock();
                }
            }
        }, "thread1");

        Thread thread2 = new Thread(() -> {
            while (i <= 100) {

                try {
                    try {
                        lock.lock();
                        if(i%3==1){
                            System.out.println(Thread.currentThread().getName()+"i的值:"+i);
                            i++;
                            condition3.signal();
                        }else{
                            condition2.await();
                        }
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                } finally {
                    lock.unlock();
                }
            }
        }, "thread2");

        Thread thread3 = new Thread(() -> {
            while (i <= 100) {

                try {
                    lock.lock();
                    if(i%3==2){
                        System.out.println(Thread.currentThread().getName()+"i的值:"+i);
                        i++;
                        condition1.signal();
                    }else{
                        condition3.await();
                    }
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                } finally {
                    lock.unlock();
                }
            }
        }, "thread3");

        thread1.start();
        thread2.start();
        thread3.start();
    }
}
