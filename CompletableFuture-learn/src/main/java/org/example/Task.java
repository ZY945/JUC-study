package org.example;

import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * Consumer---入参,无返回
 *
 * @author 伍六七
 * @date 2023/8/4 10:11
 */
public class Task {
    public static Runnable getRunnableTask(String taskName) {
        return () -> {
            System.out.println(Thread.currentThread().getName() + "(任务名)" + taskName + "----Runnable----无入参无返回值：");
        };
    }

    public static Consumer<String> getConsumer(String taskName) {
        return result -> {
            System.out.println(Thread.currentThread().getName() + "(任务名)" + taskName + "----Consumer----入参无返回值：");
            System.out.println("入参为" + result);
        };
    }

    public static Supplier<String> getSupplierTask(String taskName) {
        return () -> {
            System.out.println(Thread.currentThread().getName() + "(任务名)" + taskName + "----Supplier----无入参有返回值：");
            return "(Supplier返回值：)" + taskName;
        };
    }

    public static Function<String, String> getFunctionTask(String taskName) {
        return result -> {
            System.out.println(Thread.currentThread().getName() + "(任务名)" + taskName + "----Function----有入参有返回值：");
            return "(Function返回值：)" + taskName + "--参数:" + result;
        };
    }

    public static Runnable getLongTimeRunnableTask(String taskName) {
        return () -> {
            sleep(5);
            System.out.println(Thread.currentThread().getName() + "(任务名)" + taskName + "----Runnable----无入参无返回值：");
        };
    }

    public static Consumer<String> getLongTimeConsumer(String taskName) {
        return result -> {
            sleep(5);
            System.out.println(Thread.currentThread().getName() + "(任务名)" + taskName + "----Consumer----入参无返回值：");
            System.out.println("入参为" + result);
        };
    }

    public static Supplier<String> getLongTimeSupplierTask(String taskName) {
        return () -> {
            sleep(5);
            System.out.println(Thread.currentThread().getName() + "(任务名)" + taskName + "----Supplier----无入参有返回值：");
            return "(Supplier返回值：)" + taskName;
        };
    }

    public static Function<String, String> getLongTimeFunctionTask(String taskName) {
        return result -> {
            sleep(5);
            System.out.println(Thread.currentThread().getName() + "(任务名)" + taskName + "----Function----有入参有返回值：");
            return "(Function返回值：)" + taskName + "--参数:" + result;
        };
    }

    /**
     * 模拟耗时的任务
     *
     * @param seconds 耗时
     */
    public static void sleep(Integer seconds) {
        try {
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}