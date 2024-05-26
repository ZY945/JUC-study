package juc.util.cyclicBarrier_study.demo;

import java.util.Random;
import java.util.concurrent.CyclicBarrier;

public class Aced implements Runnable{
    private String player;
    private CyclicBarrier barrier;
    public Aced(String player, CyclicBarrier barrier) {
        this.player = player;
        this.barrier = barrier;
    }

    public String getPlayer() {
        return player;
    }

    @Override
    public void run() {
        try {
            System.out.println(this.getPlayer() + " 开始打团");
            Thread.sleep(new Random().nextInt(3)*1000);
            System.out.println(this.getPlayer() +"阵亡了");
            //调用await方法进行阻塞,当CyclicBarrier满足条件后才会往下走
            barrier.await();
            Thread.sleep(new Random().nextInt(3)*1000);
            System.out.println(this.getPlayer() + " 复活后,去小龙团");
            Thread.sleep(new Random().nextInt(3)*1000);

            System.out.println(this.getPlayer() + "到达小龙坑,等待其他玩家...");
            barrier.await();

            System.out.println(this.getPlayer() + " 开始小龙团");
            Thread.sleep(new Random().nextInt(3)*1000);

            System.out.println(this.getPlayer() + " 阵亡");
            barrier.await();

            Thread.sleep(1);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
