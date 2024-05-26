package juc.util.cyclicBarrier_study;

import java.util.concurrent.CyclicBarrier;

/**
 * CyclicBarrier--屏障，常用await进行阻塞，屏障
 * @author 伍六七
 * @date 2022/12/3 11:36
 */
public class _02_CyclicBarrier {

    private static final int num = 12;
    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(num, () -> {
            System.out.println("集齐十二张卡片合成红包");
        });

        for(int i = 0; i < 12; i ++){
            new Thread(()->{
                try {
                    System.out.println(Thread.currentThread().getName()+"已获得");
                    cyclicBarrier.await();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            },String.valueOf(i)).start();
        }
    }
}
