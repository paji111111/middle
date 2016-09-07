package com.jason.myself.framework;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/** 返回结果带有计时功能
 * Created by paji on 16/9/7
 */
public class ResponseFuture<T> implements Future<T> {

    private final long sendTime = System.currentTimeMillis();

    private long receiveTime = sendTime;

    private final Lock lock = new ReentrantLock();

    private final Condition condition = lock.newCondition();

    private volatile boolean done;

    private volatile T response;

    @Override
    public boolean cancel(boolean mayInterruptIfRunning) {
        return false;
    }

    @Override
    public boolean isCancelled() {
        return false;
    }

    @Override
    public boolean isDone() {
        return this.done;
    }

    @Override
    public T get() throws InterruptedException, ExecutionException {
        if (!isDone()) {
            this.lock.lock();
            try {
                condition.await();
            } finally {
                this.lock.unlock();
            }
        }
        return response;
    }

    @Override
    public T get(long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {
        if (!isDone()) {
            this.lock.lock();
            try {
                boolean done = condition.await(timeout, unit);
                if (!done) {
                    throw new TimeoutException("waiting response timeout!");
                }
            } finally {
                this.lock.unlock();
            }
        }
        return response;
    }

    /**
     * 设置接受到的返回信息
     * @param response
     */
    public void responseReceived(T response) {
        this.lock.lock();
        try {
            this.response = response;
            this.done = true;
            this.receiveTime = System.currentTimeMillis();
            this.condition.signalAll();
        } finally {
            this.lock.unlock();
        }
    }

}
