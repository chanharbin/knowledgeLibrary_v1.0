package com.testFileUpload.spider;

import org.springframework.beans.factory.annotation.Value;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Spider {
    private ConcurrentHashMap<String, Object> urlMap = new ConcurrentHashMap<>();
    protected List<Request> startRequest;
    private ScheduleQueue sQueue;
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
        sQueue = new ScheduleQueue();
        queue = new LinkedBlockingDeque(1000);
        f1 = new UserThreadFactory("download");
        handler = new UserRejectHandler();
        urls = new ArrayList<>();
        threadPool = new ThreadPoolExecutor(2, 3, 60, TimeUnit.SECONDS
                , queue, f1, handler);
    }

    public void setUrls(ArrayList<String> list) {
        urls.addAll(list);
    }

    public Spider addUrl(ArrayList<String> ids) {
        int curUrlMapSize = urlMap.size();
        for (String url : ids) {
            if (urlMap.containsKey(url)) {
                continue;
            } else {
                this.addRequest(new Request(url));
                urlMap.put(url, 1);
            }
        }
        if (urlMap.size() > curUrlMapSize) {
            this.signalNewUrl();
        }
        return this;
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

    private void addRequest(Request request) {
        sQueue.push(request);
    }



    public void run(List<String> urlList) {
        System.out.println(urlList.size());
        for(String curUrl:urlList){
            Task task = new Task(curUrl);
            this.threadPool.execute(task);
        }
//        while(!Thread.currentThread().isInterrupted()){
//            final Request request = this.sQueue.poll();
//            if(request == null){
//                this.waitNewUrl();
//            } else{
//                Runnable task = new Task(request,sQueue);
//                this.threadPool.execute(task);
//            }
//            if(this.threadPool.getCompletedTaskCount()>10000){
//                break;
//            }
//        }
        this.threadPool.shutdown();
        try{
            this.threadPool.awaitTermination((long)2,TimeUnit.HOURS);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }



}

