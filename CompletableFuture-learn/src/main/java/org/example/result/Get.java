package org.example.result;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * @author 伍六七
 * @date 2023/8/15 13:38
 */
public class Get {
    public static void main(String[] args) {

        CompletableFuture<Integer> async = CompletableFuture.supplyAsync(() -> {
            System.out.println("自动抛出异常");
            return 1;
        });

        try {
            async.get();
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
    }
}
