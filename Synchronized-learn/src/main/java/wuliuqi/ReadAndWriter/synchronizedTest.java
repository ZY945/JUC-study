package wuliuqi.ReadAndWriter;

public class synchronizedTest {

    public static void main(String[] args) { 
        final synchronizedTest synchronizedTest = new synchronizedTest();
        
        new Thread(){ 
            public void run() {
                synchronizedTest.get(Thread.currentThread());
            }; 
        }.start(); 
        
        new Thread(){ 
            public void run() { 
                synchronizedTest.get(Thread.currentThread());
            }; 
        }.start(); 
    } 
    
    public synchronized void get(Thread thread) {
        long start = System.currentTimeMillis(); 
        while(System.currentTimeMillis() - start <= 1) { 
            System.out.println(thread.getName()+"正在进行读操作"); 
        } 
        System.out.println(thread.getName()+"读操作完毕"); 
    } 
}
