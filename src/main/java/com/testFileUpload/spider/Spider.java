package com.testFileUpload.spider;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Spider{
    private ConcurrentHashMap<String,Object> urlMap = new ConcurrentHashMap<>();
    protected List<Request> startRequest;
    private ScheduleQueue sQueue;
    private BlockingDeque queue;
    private UserThreadFactory f1;
    private UserRejectHandler handler;
    private ThreadPoolExecutor threadPool;
    private int sleepTime;

    private ReentrantLock newUrlLock = new ReentrantLock();
    private Condition newUrlCondition;

    public Spider(){
        sleepTime=1;
        newUrlCondition = newUrlLock.newCondition();
        sQueue = new ScheduleQueue();
        queue =new LinkedBlockingDeque(1000);
        f1 = new UserThreadFactory("download");
        handler = new UserRejectHandler();
        threadPool = new ThreadPoolExecutor(4,8,60, TimeUnit.SECONDS
                ,queue,f1,handler);
    }

    public Spider addUrl(ArrayList<String> ids){
        int curUrlMapSize=urlMap.size();
        for(String url:ids){
            if(urlMap.containsKey(url)) {
                continue;
            }
            else {
                this.addRequest(new Request(url));
                urlMap.put(url,1);
            }
        }
        if(urlMap.size()>curUrlMapSize){
            this.signalNewUrl();
        }
        return this;
    }

    private void signalNewUrl(){
        try{
            newUrlLock.lock();
            newUrlCondition.signalAll();
        }finally {
            newUrlLock.unlock();
        }
    }

    private void waitNewUrl(){
        newUrlLock.lock();
        try{
            newUrlCondition.await((long)sleepTime,TimeUnit.SECONDS);
        }catch (InterruptedException e){
            e.printStackTrace();
        } finally {
            newUrlLock.unlock();
        }
    }

    private void addRequest(Request request){
        sQueue.push(request);
    }

    protected  void initCompoment(){

    }


    public void run() {
        this.initCompoment();
        while(!Thread.currentThread().isInterrupted()){
            final Request request = this.sQueue.poll();
            if(request == null){
                this.waitNewUrl();
            } else{
                Runnable task = new Task(request,sQueue);
                this.threadPool.execute(task);
            }
            if(this.threadPool.getCompletedTaskCount()>10000){
                break;
            }
        }
        this.threadPool.shutdown();
    }
}

