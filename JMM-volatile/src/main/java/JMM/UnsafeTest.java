package JMM;

import sun.misc.Unsafe;

/**
 * 在理论层面，可以把基本的CPU内存屏障分成四种：
 * LoadLoad：禁止读和读的重排序。
 * StoreStore：禁止写和写的重排序。
 * LoadStore：禁止读和写的重排序。
 * StoreLoad：禁止写和读的重排序。
 * Unsafe中的方法：
 * loadFence=LoadLoad+LoadStore
 * storeFence=StoreStore+LoadStore
 * fullFence=loadFence+storeFence+StoreLoad
 * @author 伍六七
 * @date 2023/8/20 13:41
 */
public class UnsafeTest {
    public static void main(String[] args) {
        Unsafe unsafe = Unsafe.getUnsafe();
        unsafe.loadFence();
        unsafe.storeFence();
        unsafe.fullFence();
    }
}
