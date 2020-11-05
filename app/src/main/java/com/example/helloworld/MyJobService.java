package com.example.helloworld;

import android.app.job.JobParameters;
import android.content.Context;
import android.nfc.Tag;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

public class MyJobService extends android.app.job.JobService {
    private static final String TAG = MyJobService.class.getSimpleName();
    private boolean jobCancelled = false;
    Context context;

    @Override
    public boolean onStartJob(JobParameters params) {
        Log.i(TAG, "onStartJob: ");
        context = getApplicationContext();
        doBackground(params);
        return true;
    }

    @Override
    public boolean onStopJob(JobParameters params) {
        Log.i(TAG, "onStopJob: Cancel");
        jobCancelled = true;
        return true;
    }

    private void doBackground(final JobParameters parameters){
        //mensimulasikan sebuah proses berjalan secara async
        new Thread(
                //membuat anonymous object
                new Runnable() {
            @Override
            public void run() {
                for(int i=0; i<10; i++){
                    Log.i(TAG, "run: "+ i);

                    final int finali = i;
                    Handler newHandler = new Handler(getMainLooper());

                    //mensimulasikan sebuah proses yang berjalan selama 3 detik
                    newHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            Log.i(TAG, "handler run: "+finali);
                        }
                    });

                    if (jobCancelled){
                        return;
                    }
                    try{
                        Thread.sleep(3000);
                    } catch (InterruptedException e){
                        Log.e(TAG, "InterruptedException: ", e.getCause());
                    }
                }
                Log.i(TAG, "run: JOB FINISHED");
            }
        }).start();
    }
}
