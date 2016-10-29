package com.example.dawhey.sensorsapp.Utilities;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dawhey.sensorsapp.Models.ChartEntry;
import com.example.dawhey.sensorsapp.R;
import com.github.mikephil.charting.components.MarkerView;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.utils.MPPointF;

import java.text.DecimalFormat;

/**
 * Created by dawhey on 23.09.16.
 */

public class EntryMarkerView extends MarkerView {

    private TextView value;
    private ImageView icon;
    private TextView timestamp;

    public EntryMarkerView(Context context, int layoutResource) {
        super(context, layoutResource);

        // find your layout components
        value = (TextView) findViewById(R.id.markerValue);
        icon = (ImageView) findViewById(R.id.markerIcon);
        timestamp = (TextView) findViewById(R.id.markerTimestamp);
    }

    @Override
    public void refreshContent(Entry e, Highlight highlight) {
        if (e instanceof ChartEntry) {
            ChartEntry entry = (ChartEntry) e;
            String valueText = String.valueOf(new DecimalFormat("#0.0").format(entry.getY()));
            timestamp.setText(entry.getTimestamp());
            if (entry.getTag().equalsIgnoreCase("temperature")) {
                icon.setBackgroundResource(R.drawable.ic_thermometer);
                valueText += " ℃";
                value.setText(valueText);
            } else if (entry.getTag().equalsIgnoreCase("humidity")) {
                icon.setBackgroundResource(R.drawable.ic_raindrop);
                valueText += " %";
                value.setText(valueText);
            }
        }
    }

    @Override
    public int getXOffset(float xpos) {
        if (xpos < 250.0f) {
            return -getWidth() + 250;
        } else {
            return -getWidth() - 40;
        }
    }

    @Override
    public int getYOffset(float ypos) {
        if (ypos > 130.0f) {
            return -getHeight() - 50;
        } else {
            return -getHeight() + 125;
        }
    }
}
