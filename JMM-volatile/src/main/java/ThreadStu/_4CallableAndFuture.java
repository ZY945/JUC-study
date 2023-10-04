package ThreadStu;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

/**
 * @author 伍六七
 * @date 2022/12/2 15:12
 */
public class _4CallableAndFuture {
    public static void main(String[] args) throws Exception {
        new Thread(new MyThread(),"run").start();
        //FutureTask
        FutureTask<Integer> futureTask1 = new FutureTask<>(new MyThread2());
        //lam表达式
        FutureTask<Integer> futureTask2 = new FutureTask<>(()-> {
            System.out.println(Thread.currentThread().getName() + " come in callable");
            return 1024;
        });
        new Thread(futureTask1,"thread").start();
        new Thread(futureTask2,"Lambda").start();

        System.out.println(futureTask1.get());
        System.out.println(futureTask1.get().getClass().getTypeName());
        System.out.println(futureTask2.get());
        System.out.println(Thread.currentThread().getName()+" come over");

        MyThread2 thread2 = new MyThread2();
        Object call = thread2.call();
        System.out.println(call.getClass().getTypeName());//java.lang.String cannot be cast to java.lang.Integer
        System.out.println(call);
    }
}
// 创建新类MyThread实现runnable接口
class MyThread implements Runnable{
    @Override public void run() {

    }
}

// 新类MyThread2实现callable接口
class MyThread2<T> implements Callable{
    @Override public T call() throws Exception {
        System.out.println(Thread.currentThread().getName()+" come in callable");
        return (T)"ss200";
    }
}
