package com.vtitan.patnershipcricketleague.util;

import android.content.Context;
import android.icu.text.SimpleDateFormat;
import android.icu.util.TimeZone;
import android.os.Build;
import android.widget.ArrayAdapter;

import androidx.annotation.RequiresApi;

import com.vtitan.patnershipcricketleague.R;

import java.util.Date;
import java.util.List;

public class BasicFunction {

   /* public static ArrayAdapter<String> getArrayAdapterSpinner(Context context, List<String> stringArray) {
        ArrayAdapter<String> adapter = null;
        try {
            adapter = new ArrayAdapter<String>(context,
                    R.layout.sp,
                    stringArray);
            adapter.setDropDownViewResource(R.layout.spinner_item);
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        return adapter;
    }

    public static ArrayAdapter<String> getArrayAdapterunitSpinner(Context context, List<String> stringArray) {
        ArrayAdapter<String> adapter = null;
        try {
            adapter = new ArrayAdapter<String>(context,
                    R.layout.unit_spinner_items,
                    stringArray);
            adapter.setDropDownViewResource(R.layout.unit_spinner_items);
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        return adapter;
    }*/

    public static String getDuration(int duration)
    {
        int hours = (int)duration / 3600;
        int remainder = (int) duration - hours * 3600;
        int mins = remainder / 60;
        remainder = remainder - mins * 60;
        int secs = remainder;
      return ""+String.format("%02d",hours)+":"+String.format("%02d",mins)+":"+String.format("%02d",secs);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public static String secondsToDateTime(long Seconds)
    {
        long lastupdatedTime = Seconds * 1000;
        Date date = new Date(lastupdatedTime);
        SimpleDateFormat formatter_time = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss aa");
        formatter_time.setTimeZone(TimeZone.getTimeZone("UTC"));
        return formatter_time.format(date);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public static String longTohms(long matchtime)
    {
       // long lastupdatedTime = Seconds * 1000;
        Date date = new Date(matchtime);
        final SimpleDateFormat formatter = new SimpleDateFormat("hh:mm aa");
        formatter.setTimeZone(TimeZone.getTimeZone("IST"));
        return formatter.format(date);
    }



}
