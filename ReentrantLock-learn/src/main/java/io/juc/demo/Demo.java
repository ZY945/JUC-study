package io.juc.demo;


import java.util.concurrent.ConcurrentHashMap;

public class Demo implements Runnable {
    public static void main(String[] args) {
        ConcurrentHashMap<Integer, Integer> map = new ConcurrentHashMap<>();
    }

    @Override
    public void run() {

    }
}
