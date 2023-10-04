package wuliuqi.ReadAndWriter;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class _01_ReadWriteLockTest {
    public static void main(String[] args) throws InterruptedException {
        //读写锁
//        ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
//        lock.writeLock().lock();
//        new Thread(lock.writeLock()::lock).start();



        //可重入锁
//        ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
//        lock.writeLock().lock();
//        lock.writeLock().lock();
//        new Thread(() -> {
//            lock.writeLock().lock();
//            System.out.println("成功获取到写锁！");
//        }).start();
//        System.out.println("释放第一层锁！");
//        lock.writeLock().unlock();
//        TimeUnit.SECONDS.sleep(1);
//        System.out.println("释放第二层锁！");
//        lock.writeLock().unlock();




        //公平锁
//        ReentrantReadWriteLock lock = new ReentrantReadWriteLock(true);
//
//        Runnable action = () -> {
//            System.out.println("线程 "+Thread.currentThread().getName()+" 将在1秒后开始获取锁...");
//            lock.writeLock().lock();
//            System.out.println("线程 "+Thread.currentThread().getName()+" 成功获取锁！");
//            lock.writeLock().unlock();
//        };
//        for (int i = 0; i < 10; i++) {   //建立10个线程
//            new Thread(action, "T"+i).start();
//        }


        //锁降级
        ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
        lock.writeLock().lock();
        lock.readLock().lock();
        System.out.println("成功加读锁！");

        //锁降级
//        ReentrantReadWriteLock lock = new ReentrantReadWriteLock(true);
//        lock.readLock().lock();
//        lock.writeLock().lock();
//        for (int i = 0; i < 5; i++) {
//            final int num = i;
//            new Thread(() -> {
//                System.out.println(num+"申请读锁！");
//                lock.readLock().lock();
//                System.out.println(num+"读锁添加成功！");
//            }).start();
//        }
//        TimeUnit.SECONDS.sleep(1);
//        lock.readLock().unlock();    //如果释放写锁，会怎么样？
//

        //锁升级
//        ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
//        lock.readLock().lock();
//        lock.writeLock().lock();
//        System.out.println("锁升级成功！");
    }
}
