package com.example.dawhey.sensorsapp.Utilities;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.dawhey.sensorsapp.Models.Entry;
import com.example.dawhey.sensorsapp.R;

import org.w3c.dom.Text;

import java.text.DecimalFormat;
import java.util.List;

/**
 * Created by dawhey on 21.09.16.
 */

public class EntriesAdapter extends RecyclerView.Adapter<EntriesAdapter.EntryViewHolder> {

    private Context context;
    private List<Entry> entries;

    public EntriesAdapter(Context context, List<Entry> entries) {
        this.context = context;
        this.entries = entries;
    }

    @Override
    public EntryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.entry_item, parent, false);

        return new EntryViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(EntryViewHolder holder, int position) {
        Entry entry = entries.get(position);
        String temperature = String.valueOf(new DecimalFormat("#0.0").format(entry.getTemperature())) + " ℃";
        String humidity = String.valueOf(new DecimalFormat("#0.0").format(entry.getHumidity())) + " %";
        String[] timestampParts = entry.getTimestamp().replace(" GMT", "").split(" ");
        String hour = timestampParts[4];
        String date = timestampParts[0] + " " + timestampParts[1] + " " + timestampParts[2] + " " + timestampParts[3] + " at";

        holder.dateView.setText(date);
        holder.hourView.setText(hour);
        holder.temperatureView.setText(temperature);
        holder.humidityview.setText(humidity);
    }

    public void swap(List<Entry> entries){
        this.entries.clear();
        this.entries.addAll(entries);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return entries.size();
    }

    public class EntryViewHolder extends RecyclerView.ViewHolder {

        public TextView dateView;
        public TextView humidityview;
        public TextView temperatureView;
        public TextView hourView;

        public EntryViewHolder(View itemView) {
            super(itemView);

            dateView = (TextView) itemView.findViewById(R.id.timestampView);
            hourView = (TextView) itemView.findViewById(R.id.hourView);
            humidityview = (TextView) itemView.findViewById(R.id.humidityView);
            temperatureView = (TextView) itemView.findViewById(R.id.temperatureView);
        }
    }
}
