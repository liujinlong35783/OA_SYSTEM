package cn.gson.oasys.test;

import org.junit.Test;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class UnsafeSequence implements ReturnMultiParamTask {

    private int value;

    public synchronized int getNext() {
        return value++;
    }

    public static void main(String[] args) {
        UnsafeSequence unsafeSequence = new UnsafeSequence();
        Executor executors = Executors.newFixedThreadPool(8);
        for (int i = 0; i < 200; i++) {
            executors.execute(()-> System.out.println( unsafeSequence.getNext())
            );
        }
    }
    @Test
    public void test1(){
        UnsafeSequence unsafeSequence = new UnsafeSequence();
        Executor pool = Executors.newFixedThreadPool(32);
        for (int i = 0; i < 200; i++) {
            pool.execute(
                    ()-> System.out.println(unsafeSequence.getNext())
                    );
        }
    }
    @Test
    public void test2(){
        ReturnMultiParam lambda5 = (a, b) -> a+b;
        System.out.println(lambda5.method(1, 1));
    }
}

