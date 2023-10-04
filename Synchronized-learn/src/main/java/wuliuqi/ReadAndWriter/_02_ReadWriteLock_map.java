package wuliuqi.ReadAndWriter;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author 伍六七
 * @date 2022/12/3 17:56
 */
public class _02_ReadWriteLock_map {
    //创建map集合
    private volatile Map<String,Object> map = new HashMap<>();

    //创建读写锁对象
    private ReadWriteLock rwLock = new ReentrantReadWriteLock();
    //放数据
    public void put(String key,Object value) {
        //添加写锁
        rwLock.writeLock().lock();
        try {
            System.out.println(Thread.currentThread().getName()+" 正在写操作"+key);
            //暂停一会
            TimeUnit.MICROSECONDS.sleep(300);
            //放数据
            map.put(key,value);
            System.out.println(Thread.currentThread().getName()+" 写完了"+key);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            //释放写锁
            rwLock.writeLock().unlock();
        }
    }

    //取数据
    public Object get(String key) {
        //添加读锁
        rwLock.readLock().lock();
        Object result = null;
        try {
            System.out.println(Thread.currentThread().getName() + " 正在读取操作" + key);
            //暂停一会
            TimeUnit.MICROSECONDS.sleep(300);
            result = map.get(key);
            System.out.println(Thread.currentThread().getName() + " 取完了" + key);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            //释放读锁
            rwLock.readLock().unlock();
        }
        return result;
    }
}
class demo{

    public static void main(String[] args) throws InterruptedException {
        _02_ReadWriteLock_map readWriteLock_map = new _02_ReadWriteLock_map();
        for(int i = 0; i <=5; i ++){
            final int num = i;
            new Thread(() -> {
                readWriteLock_map.put(num+"",num+"");
            },String.valueOf(i)).start();
        }
        TimeUnit.MICROSECONDS.sleep(300);
        //创建线程取数据
        for (int i = 1; i <= 5; i++) {
            final int num = i;
            new Thread(()->{
                readWriteLock_map.get(num+"");
            },String.valueOf(i)).start();
        }

    }
}
