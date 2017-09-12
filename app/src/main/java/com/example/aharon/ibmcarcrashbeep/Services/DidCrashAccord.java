package com.example.aharon.ibmcarcrashbeep.Services;

/**
 * Created by Aharon on 11/09/2017.
 */

public class DidCrashAccord extends Thread {

    @Override
    public void run() {
        super.run();
        try {
            sleep(180000);
            //send SMS
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    boolean fineStop(float lastAcc, float newAcc){
        return true;
    }
}
