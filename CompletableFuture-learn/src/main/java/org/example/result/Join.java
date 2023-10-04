package org.example.result;

import java.util.concurrent.CompletableFuture;

/**
 * @author 伍六七
 * @date 2023/8/15 13:38
 */
public class Join {
    public static void main(String[] args) {
        CompletableFuture<Integer> async = CompletableFuture.supplyAsync(() -> {
            System.out.println("自动抛出异常");
            int i = 1 / 0;
            return 1;
        });

        async.join();
    }
}
