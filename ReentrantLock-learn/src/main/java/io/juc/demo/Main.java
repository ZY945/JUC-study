package io.juc.demo;

import java.util.concurrent.*;

public class Main {
    static Integer count = 0;

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        for (int i = 0; i < 5; i++) {
            Demo demo = new Demo();
            executorService.submit(demo);
        }
        System.out.println(count);
    }
     static class Demo implements Runnable {

        @Override
        public void run() {
            while(count<100){
                count++;
                System.out.println(Thread.currentThread().getName()+"count="+count);
            }
        }
    }
}
