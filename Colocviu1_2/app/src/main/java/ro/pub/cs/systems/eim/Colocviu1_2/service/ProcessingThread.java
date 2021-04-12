package ro.pub.cs.systems.eim.Colocviu1_2.service;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import java.util.Date;

import ro.pub.cs.systems.eim.Colocviu1_2.general.Constants;

public class ProcessingThread extends Thread {
    private Context context;
    private int sum;

    public ProcessingThread(Context context, int sum) {
        this.context = context;
        this.sum = sum;
    }

    @Override
    public void run() {
        Log.d(Constants.PROCESSING_THREAD_TAG, "Thread has started! PID: " + android.os.Process.myPid() + " TID: " + android.os.Process.myTid());
        sleep();
        sendMessage();

        Log.d(Constants.PROCESSING_THREAD_TAG, "Thread has stopped!");
    }

    private void sendMessage() {
        Intent broadcastIntent = new Intent();
        broadcastIntent.setAction(Constants.BROADCAST_ACTION);
        broadcastIntent.putExtra(Constants.BROADCAST_EXTRA, new Date(System.currentTimeMillis()) + " " + sum);
        context.sendBroadcast(broadcastIntent);
    }

    private void sleep() {
        try {
            Thread.sleep(Constants.SLEEP_TIME);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
