package io.example.threadLocal;

import java.util.Date;
import java.util.concurrent.*;

/**
 * @author 伍六七
 * @date 2022/11/6 23:13
 */

/**
 * <pre>{@code
 *         public static void main(String[] args) {
 *         ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor();
 *     }
 *     }
 * </pre>
 */
public class learnThreadPool {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask<String> task = new FutureTask<>(new Callable<String>() {
            @Override
            public String call() throws Exception {
                System.out.println(new Date());
                return "";
            }
        });
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.submit(task);
        System.out.println(task.get());
        executorService.shutdown();
    }
}
