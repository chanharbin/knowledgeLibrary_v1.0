package com.testFileUpload.spider;

import com.testFileUpload.pojo.SpiderUrl;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Spider {
    private ConcurrentHashMap<String, Object> urlMap = new ConcurrentHashMap<>();
    private BlockingDeque queue;
    private UserThreadFactory f1;
    private UserRejectHandler handler;
    private ThreadPoolExecutor threadPool;
    private int sleepTime;
    private ArrayList<String> urls;
    private ReentrantLock newUrlLock = new ReentrantLock();
    private Condition newUrlCondition;

    public Spider() {
        sleepTime = 1;
        newUrlCondition = newUrlLock.newCondition();
        queue = new LinkedBlockingDeque(1000);
        f1 = new UserThreadFactory("download");
        handler = new UserRejectHandler();
        urls = new ArrayList<>();
        threadPool = new ThreadPoolExecutor(2, 2, 60, TimeUnit.SECONDS
                , queue, f1, handler);
    }
    private void signalNewUrl() {
        try {
            newUrlLock.lock();
            newUrlCondition.signalAll();
        } finally {
            newUrlLock.unlock();
        }
    }

    private void waitNewUrl() {
        newUrlLock.lock();
        try {
            newUrlCondition.await((long) sleepTime, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            newUrlLock.unlock();
        }
    }

    public void runSpider(List<SpiderUrl> list) {
        for(SpiderUrl spiderUrl:list){
            this.threadPool.execute(new Runnable() {
                @Override
                public void run() {
                    taskDealUrl(spiderUrl);
                }
            });
        }
        this.threadPool.shutdown();
        try{
            this.threadPool.awaitTermination((long)2,TimeUnit.HOURS);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
    private void taskDealUrl(SpiderUrl spiderUrl){
        DealUrl dealUrl = new DealUrl();
        try {
            dealUrl.dealWithUrl(spiderUrl);
        }catch (Exception e){
            e.printStackTrace();
        }
    }


}

