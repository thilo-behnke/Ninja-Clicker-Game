package com.example.janth.ninjaclicker.handler;

import android.util.Log;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

// TODO: Use ThreadPoolExecutor
public class ThreadHandler {

    private List<Thread> activeThreads;

    public ThreadHandler() {
        activeThreads = new ArrayList<>();
    }

    public void addThread(Thread thread) {
        activeThreads.add(thread);
    }

    public void pauseThreads() {
        try {
            for(Thread t : activeThreads){
                t.join();
            }
        } catch (InterruptedException e) {
            Log.d("InterruptedException", e.getLocalizedMessage());
        }
    }

    public void resumeThreads() {

        for(Iterator<Thread> it = activeThreads.iterator(); it.hasNext();) {
            Thread t = it.next();
            if(t.getState().equals(Thread.State.TERMINATED)){
                it.remove();
            } else {
                t.start();
            }
        }
    }

}
