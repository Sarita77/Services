package service;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.widget.Toast;

public class MyService extends Service {
    public Context context = this;
    public Handler handler = null;
    public Runnable runnable = null;

    // Constructor
    public MyService(){

    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        Toast.makeText(this, "Service created!", Toast.LENGTH_LONG).show();

        handler = new Handler();
        runnable = new Runnable() {
            public void run() {
                double randomNo = getRandomDoubleBetweenRange(1, 100);
                Toast.makeText(context, "Random no: "+ randomNo, Toast.LENGTH_LONG).show();
                handler.postDelayed(runnable, 1000);
            }

        };
        handler.postDelayed(runnable, 2000);

    }

    private double getRandomDoubleBetweenRange(double min, double max) {
        return (Math.random()*((max-min)+1))+min;
    }

    @Override
    public void onDestroy() {
        handler.removeCallbacks(runnable);
        Toast.makeText(this, "Service Stopped", Toast.LENGTH_LONG).show();
    }
}
