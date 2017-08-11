package com.tbeh.ninjaclicker;

import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Looper;

public class GameTimer {

    private CountDownTimer timer;
    private long currentTime;

    private boolean finished;

    public GameTimer(){}

    public void startTimer(int duration) {
        finished=false;
        final int durationNew = duration;
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                timer = new CountDownTimer(durationNew, 100) {
                    public void onTick(long millisUntilFinished) {
                        setCurrentTime(millisUntilFinished);
                    }
                    public void onFinish() {
                        finished=true;
                    }
                };
                timer.start();
            }
        });
    }

    public boolean isFinished(){
        return finished;
    }

    private void setCurrentTime(long currentTime) {
        this.currentTime = currentTime;
    }

    public void cancelTimer() {
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                if (timer != null) {
                    timer.cancel();
                }
            }
        });
    }

    public long getCurrentTime() {
        return currentTime;
    }

    public String getFormattedCurrentTime(){
        String timeString = String.valueOf(getCurrentTime());
        char second = '0';
        char milli1 = '0';
        char milli2 = '0';
        char milli3 = '0';
        if(timeString.equals(0)){
        }
        else{
            if(timeString.length()==4){
                second = timeString.charAt(0);
                milli1 = timeString.charAt(1);
                milli2 = timeString.charAt(2);
                milli3 = timeString.charAt(3);
            }
            else if(timeString.length()==3){
                second = '0';
                milli1 = timeString.charAt(0);
                milli2 = timeString.charAt(1);
                milli3 = timeString.charAt(2);
            }
            else if(timeString.length()==2){
                second = '0';
                milli1 = '0';
                milli2 = timeString.charAt(0);
                milli3 = timeString.charAt(1);
            }
            else if(timeString.length()==1){
                second = '0';
                milli1 = '0';
                milli2 = '0';
                milli3 = timeString.charAt(0);
            }
        }
        return second+":"+milli1+milli2+milli3;
    }
}
