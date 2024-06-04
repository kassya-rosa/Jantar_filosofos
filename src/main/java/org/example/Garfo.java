package org.example;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Garfo {
    private final Lock lock = new ReentrantLock();

    public void pegar() {
        lock.lock();
    }

    public void soltar() {
        lock.unlock();
    }
}
