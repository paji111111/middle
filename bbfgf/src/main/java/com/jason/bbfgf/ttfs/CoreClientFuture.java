package com.jason.bbfgf.ttfs;

import com.jason.bbfgf.entity.Request;
import com.jason.bbfgf.entity.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by paji on 16/9/5
 */
public class CoreClientFuture implements Future<Object> {
    private static final Logger logger = LoggerFactory.getLogger(CoreClientFuture.class);

    private Response response;
    private Request request;

    private boolean done;
    private ReentrantLock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    private CoreClientCallBack coreClientCallBack;

    private long startTime;
    private long responseTimeThreshold = 5000;

    public CoreClientFuture(Request request) {
        this.request = request;
        this.startTime = System.currentTimeMillis();
        this.done = false;
    }

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
        return done;
    }

    @Override
    public Object get() throws InterruptedException, ExecutionException {
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
    public Object get(long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {
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

    public void done(Response response) {
        this.response = response;
        this.done = true;
        runCallback(coreClientCallBack);

        long responseTime = System.currentTimeMillis() - startTime;
        if (responseTime > this.responseTimeThreshold) {
            logger.warn("Service response time is too slow. Request id = " + response.getRequestId() + ". Response Time = " + responseTime + "ms");
        }
    }

    private void runCallback(final CoreClientCallBack callback) {
        final Response res = this.response;
        CoreClient.submit(new Runnable() {
            @Override
            public void run() {
                if (!res.isError()) {
                    callback.success(res.getResult());
                } else {
                    callback.fail(new RuntimeException("Response error", new Throwable(res.getError())));
                }
            }
        });
    }
}
