package io.example.scenario.changeAccount;

import java.math.BigDecimal;

/**
 * 保证线程安全需要的准备
 * 1.对需要保证线程安全的方法进行缩小范围,使锁的粒度尽量小一些
 * 2.选择适合的锁---可以根据是单体还是分布式来选择符合条件的锁--分布式一般使用redission分布式锁,java的锁只锁单体
 * 3.执行上锁和释放锁
 *
 * synchronized--类锁,实例锁,
 */
public class Main {
    public static void main(String[] args) {
        Account drawMoney = new Account("A_B_20231001", new BigDecimal(21));

        new DrawThread(drawMoney,"小明").start();
        new DrawThread(drawMoney,"小黄").start();
    }
}
