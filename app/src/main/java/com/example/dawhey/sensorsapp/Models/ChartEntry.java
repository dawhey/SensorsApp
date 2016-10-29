package com.example.dawhey.sensorsapp.Models;

import com.github.mikephil.charting.data.*;

/**
 * Created by dawhey on 23.09.16.
 */

public class ChartEntry extends com.github.mikephil.charting.data.Entry{

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    private String tag;
    private String timestamp;
}
