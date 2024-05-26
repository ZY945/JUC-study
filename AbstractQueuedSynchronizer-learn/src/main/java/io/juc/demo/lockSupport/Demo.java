package io.juc.demo.lockSupport;

import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.LockSupport;

public class Demo  {
    private void printA(Thread thread){
        try {
            Thread.sleep(1000L);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("A");
        LockSupport.unpark(thread);
    }
    private void printB(Thread thread){
        //可以用于延时队列
        LockSupport.parkUntil(100L);
        System.out.println("B");
        LockSupport.unpark(thread);
    }
    private void printC(){
        LockSupport.park();
        System.out.println("C");
    }

    public static void main(String[] args) {
        Demo demo = new Demo();
        Thread C = new Thread(demo::printC);
        Thread B = new Thread(()-> demo.printB(C));
        Thread A = new Thread(()-> demo.printA(B));
        B.start();
        A.start();
        C.start();
    }
}
