package wuliuqi._03_ThreadCommunication;

import java.time.LocalDateTime;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author 伍六七
 * @date 2022/12/2 13:26
 */
public class Queue<T> {
    private Object[] elements;
    private Lock lock = new ReentrantLock();
    private Condition notEmpty = lock.newCondition();
    private Condition notFull = lock.newCondition();
    private int length = 0, addIndex = 0, removeIndex = 0;

    public Queue(int size){
        elements = new Object[size];
    }

    public void add(T object){
        lock.lock();
        try {
            while (length== elements.length){
                System.out.println("队列已满，已诸塞，等待删除");
                notFull.await();
            }
            elements[addIndex]=object;
            if(addIndex++ == elements.length){
                addIndex=0;
            }
            length++;
            notEmpty.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
    public T remove(){
        lock.lock();
        try {
            while (0==length){
                System.out.println("队列无法删除，已诸塞，等待添加");
                notEmpty.await();
            }
            Object element = elements[removeIndex];
            if(removeIndex++ == elements.length){
                removeIndex = 0;
            }
            length--;
            notFull.signal();
            return (T) element;
        } catch (InterruptedException e) {
            e.printStackTrace();
            return null;
        } finally {
            lock.unlock();
        }
    }
}
class test{
    public static void main(String[] args) throws InterruptedException {
        Queue<Integer> queue = new Queue<>(2);
        Thread thread1 = new Thread(() ->{
            queue.remove();
        });
        Thread thread2 = new Thread(() ->{
            queue.add(1);
            System.out.println("添加成功");
        });
        thread1.start();
        thread2.start();
    }
}
