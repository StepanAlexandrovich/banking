package com.example.banking.transfer;

import java.util.ArrayList;
import java.util.List;

public class ThreadsHelper {
    public void waiting(List<Thread> threads){
        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void startFinish(Process process, int numberOfIterations){
        List<Thread> threads = new ArrayList<>();
        for (int i = 0; i < numberOfIterations; i++) {
            threads.add(new Thread(() -> process.process()));
        }
        for (Thread thread : threads) {
            thread.start();
        }
        new ThreadsHelper().waiting(threads);
    }
}
