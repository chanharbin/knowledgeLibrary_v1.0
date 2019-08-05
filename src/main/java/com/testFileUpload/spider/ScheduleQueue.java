package com.testFileUpload.spider;

import java.util.concurrent.LinkedBlockingDeque;

public class ScheduleQueue {
    private LinkedBlockingDeque<Request> queue = new LinkedBlockingDeque<>();

    public ScheduleQueue(){

    }

    public int getRequestSize(){
        return queue.size();
    }

    public void push(Request request){
        this.queue.push(request);
    }

    public Request poll(){
        return this.queue.poll();
    }

}

