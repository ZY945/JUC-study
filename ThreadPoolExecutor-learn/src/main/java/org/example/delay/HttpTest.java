package org.example.delay;

import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author 张洋
 * @description TODO
 * @date 2023-10-16 23:13
 */
public class HttpTest {
    static AtomicInteger atomicInteger = new AtomicInteger(11);
    public static void main(String[] args) throws InterruptedException {
        while (true){
            DelaySave delaySave = new DelaySave();
            for (int i = 0; i < 10; i++) {
                System.out.println("一次请求");
                delaySave.delay(3000L,(record)-> atomicInteger.decrementAndGet());
                System.out.println("响应成功");
            }
            Thread.sleep(4000L);
        }

    }
}
