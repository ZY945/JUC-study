package org.example.AtomicReference;

import java.util.concurrent.atomic.AtomicReference;

/**
 * 多线程环境下，需要对某个对象进行原子性的读取和修改操作时<br/>
 * 场景: 银行资金变化<br/>
 * 方法一:compareAndSet(V expect, V update)<br/>
 * 如果atomicReference==expect，就把update赋给atomicReference，否则不做任何处理。<br/>
 *
 * @author 伍六七
 * @date 2023/8/17 17:57
 */
public class AtomicReferenceDemo {
    public static void main(String[] args) {
        // 初始金额
        DebitCard before = new DebitCard("张三", 100);
        AtomicReference<DebitCard> atomicReference = new AtomicReference<>();
        atomicReference.set(before);
        System.out.println(atomicReference.get());// 初始
        DebitCard after1 = new DebitCard("张三", 90);
        // 两笔转账,
        // 第一次转账,
        atomicReference.compareAndSet(before, after1);
        System.out.println(atomicReference.get());// 第一次转账成功
        // 第二次转账
        DebitCard after2 = new DebitCard("张三", 80);
        atomicReference.compareAndSet(before, after2);
        System.out.println(atomicReference.get());// 第二次转账失败,因为get的内容和第一个参数不一样
    }
}