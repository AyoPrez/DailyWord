package com.ayoprez.notification;

import android.app.AlarmManager;

import java.util.Calendar;

/**
 * Created by Ayoze on 23/02/15.
 */
public class TimeCalculator {

    public long getTimeFromNowUntilUserChoiceTime(String time){

        int hour = Integer.valueOf(time.substring(0, 2).toString());
        int minutes = Integer.valueOf(time.substring(3, 5).toString());
        long finalTime = 0;

        Calendar c = Calendar.getInstance();
        c.set(Calendar.HOUR_OF_DAY, hour);
        c.set(Calendar.MINUTE, minutes);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);

        if(c.getTimeInMillis() <= Calendar.getInstance().getTimeInMillis())
            finalTime = (c.getTimeInMillis() + (AlarmManager.INTERVAL_DAY+1)) - System.currentTimeMillis();
        else
            finalTime = c.getTimeInMillis() - System.currentTimeMillis();
        return finalTime;
    }

    public long getMillisFromNowTo20MinutesLater(){

        Calendar c = Calendar.getInstance();
        c.set(Calendar.HOUR, c.get(Calendar.HOUR));
        c.set(Calendar.MINUTE, c.get(Calendar.MINUTE) + 2);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);
        return c.getTimeInMillis();
    }

    //Use google analytics here to see which button is more pressed
    public long getMillisFromNowTo1HourLater(){
        Calendar c = Calendar.getInstance();
        c.set(Calendar.HOUR_OF_DAY, 1);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);

        return c.getTimeInMillis();
    }

}
