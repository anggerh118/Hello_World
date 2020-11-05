package com.example.helloworld;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.viewpager.widget.ViewPager;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;

import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

public class HomeActivity2 extends AppCompatActivity {
    private Switch wifiSwitch;
    private WifiManager wifiManager;

//    private static final String TAG = HomeActivity2;
//    private Button btnStartJob;
//    private Button btnStopJob;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);
        hideTitleBar();
        TabLayout tabLayout = findViewById(R.id.tabLayout);
        TabItem layoutOne  = findViewById(R.id.Fragment1);
        TabItem layoutTwo  = findViewById(R.id.Fragment2);
        TabItem layoutThree = findViewById(R.id.Fragment3);
        final ViewPager viewPager = findViewById(R.id.viewPager);
        PagerAdapter pagerAdapter = new PagerAdapter(getSupportFragmentManager(), tabLayout.getTabCount() );
        viewPager.setAdapter(pagerAdapter);

//        btnStartJob = findViewById(R.id.start);
//        btnStartJob = findViewById(R.id.start);


        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        wifiSwitch = findViewById(R.id.wifiSwitcher);
        wifiManager = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);

        initBroadcastReceiver();

        if(wifiManager.isWifiEnabled()) {
            wifiSwitch.setChecked(true);
            wifiSwitch.setText("WiFi is On");
        }
        else{
            wifiSwitch.setChecked(false);
            wifiSwitch.setText("WiFi is Off");
        }
    }

    private void hideTitleBar(){
        if(getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
    }

    @Override
    protected void onStart(){
        super.onStart();
        IntentFilter intentFilter = new IntentFilter(WifiManager.WIFI_STATE_CHANGED_ACTION);
        registerReceiver(wifiStateReceiver, intentFilter);
    }

    @Override
    protected void onStop() {
        super.onStop();
        unregisterReceiver(wifiStateReceiver);
    }

    private BroadcastReceiver wifiStateReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            int wifiStateExtra = intent.getIntExtra(WifiManager.EXTRA_WIFI_STATE, WifiManager.WIFI_STATE_UNKNOWN);
            switch (wifiStateExtra){
                case WifiManager.WIFI_STATE_ENABLED:
                    wifiSwitch.setChecked(true);
                    wifiSwitch.setText("WiFi is On");
                    notification("WiFi Tersambung"); break;
                case WifiManager.WIFI_STATE_DISABLED:
                    wifiSwitch.setChecked(false);
                    wifiSwitch.setText("WiFi is Off");
                    notification("WiFi Terputus"); break;
            }
        }
    };

    public void notification(String message){
        String CHANNEL_ID = "NOTIF";
        NotificationChannel mChannel = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            mChannel = new NotificationChannel(CHANNEL_ID, "NOTIF", NotificationManager.IMPORTANCE_HIGH);
            NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            notificationManager.createNotificationChannel(mChannel);
            android.app.Notification notification = new NotificationCompat.Builder(HomeActivity2.this, CHANNEL_ID)
                    .setSmallIcon(R.drawable.notification)
                    .setContentTitle("WIFI")
                    .setContentText(message)
                    .build();
            int notificationID = 1;
            notificationManager.notify(notificationID, notification);
        }
    }

    private void initBroadcastReceiver() {
        final WifiManager wifiManager = (WifiManager)getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        wifiSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked && !wifiManager.isWifiEnabled()) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                        Intent panelIntent = new Intent(Settings.Panel.ACTION_WIFI);
                        HomeActivity2.this.startActivityForResult(panelIntent, 1);
                    } else {
                        wifiManager.setWifiEnabled(true);
                    }
                } else if (!isChecked && wifiManager.isWifiEnabled()) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                        Intent panelIntent = new Intent(Settings.Panel.ACTION_WIFI);
                        HomeActivity2.this.startActivityForResult(panelIntent, 1);
                    } else {
                        wifiManager.setWifiEnabled(false);
                    }
                }
            }
        });
    }
//    public void scheduleJob(View view){
//        ComponentName componentName = new ComponentName(getApplicationContext(), MyJobService.class);
//        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.P) {
//            JobInfo info = new JobInfo.Builder(123, componentName)
//                    .setRequiredNetworkType(JobInfo.NETWORK_TYPE_UNMETERED)
//                    .setPersisted(true)
//                    .setPeriodic(15*60*1000)
//                    .build();
//
//            JobScheduler scheduler = (JobScheduler) getSystemService(JOB_SCHEDULER_SERVICE);
//            int resultCode = scheduler.schedule(info);
//            if(resultCode == JobScheduler.RESULT_SUCCESS){
//                Log.i(TAG, "scheduleJob: Job Scheduler");
//            }
//            else{
//                Log.i(TAG, "scheduleJob: Job Scheduling Failed");
//            }
//        }
//    }
//
//    public void canceledJob(View view){
//        JobScheduler scheduler = (JobScheduler) getSystemService(JOB_SCHEDULER_SERVICE);
//        scheduler.cancel(123);
//        Log.i(TAG, "canceledJob: cancleJob");
//    }
}