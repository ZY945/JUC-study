package wuliuqi.suo.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author 伍六七
 * @date 2022/12/2 12:21
 */
public class test {
    public static void main(String[] args) {
        Lock lock = new ReentrantLock();
        lock.lock();
    }
}
