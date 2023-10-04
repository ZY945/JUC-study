package org.example.CAS;

import java.util.concurrent.atomic.AtomicInteger;

public class CAS_ABA {
    public static void main(String[] args) {
        AtomicInteger account = new AtomicInteger(1000);
        System.out.println("初始金额"+account.get());


        int afterAdd = account.getAndAdd(2000);

        account.compareAndSet(account.get(),afterAdd);
        System.out.println("发工资了,账户目前余额:"+account.get());


        int afterDel = account.addAndGet(-1000);
        account.compareAndSet(account.get(),afterDel);
        System.out.println("交费用了,账户目前余额:"+account.get());

        
    }
}
