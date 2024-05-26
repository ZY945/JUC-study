package juc.util.cyclicBarrier_study.demo;


import java.io.IOException;
import java.util.Random;
import java.util.concurrent.CyclicBarrier;

/**
 * java.util.concurrent包下面的一个工具类<br/>
 * 可循环使用的屏障<br/>
 * 一组线程到达一个屏障时阻塞,知道最后一个线程到达屏障,所有线程才会执行<br/>
 * 案例:团灭提示
 * 与CountDownLatch区别,CountDownLatch是一次的
 */
public class CyclicBarrierDemo {
    public static void main(String[] args) throws IOException, InterruptedException {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(5,()->{
            System.out.println("--------所有人集齐了-------");
        });
        System.out.println("团队人数"+cyclicBarrier.getParties());
        Thread thread1 = new Thread(new Aced("1", cyclicBarrier));
        Thread thread2 = new Thread(new Aced("2", cyclicBarrier));
        Thread thread3 = new Thread(new Aced("3", cyclicBarrier));
        Thread thread4 = new Thread(new Aced("4", cyclicBarrier));
        Thread thread5 = new Thread(new Aced("5", cyclicBarrier));
        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();
        Thread.sleep(new Random().nextInt(3)*1000);
        thread5.start();
        System.in.read();
    }
}
