package com.example.dawhey.sensorsapp.Utilities;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dawhey.sensorsapp.Models.Entry;
import com.example.dawhey.sensorsapp.R;

import org.w3c.dom.Text;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class EntriesAdapter extends RecyclerView.Adapter<EntriesAdapter.EntryViewHolder> implements Filterable {

    private Context context;
    private List<Entry> entries;
    private List<Entry> filterEntries;
    private DateFilter filter;

    public EntriesAdapter(Context context, List<Entry> entries) {
        this.context = context;
        this.entries = entries;
        this.filterEntries = entries;
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

    @Override
    public Filter getFilter() {
        if (filter == null) {
            filter = new DateFilter();
        }
        return filter;
    }

    class EntryViewHolder extends RecyclerView.ViewHolder {

        TextView dateView;
        TextView humidityview;
        TextView temperatureView;
        TextView hourView;

        EntryViewHolder(View itemView) {
            super(itemView);

            dateView = (TextView) itemView.findViewById(R.id.timestampView);
            hourView = (TextView) itemView.findViewById(R.id.hourView);
            humidityview = (TextView) itemView.findViewById(R.id.humidityView);
            temperatureView = (TextView) itemView.findViewById(R.id.temperatureView);
        }
    }

    private class DateFilter extends Filter {

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            String[] date = constraint.toString().split(",");
            FilterResults filterResults = new FilterResults();
            List<Entry> filteredEntries = new ArrayList<>();
            for (Entry entry : filterEntries) {
                Calendar entryDate = entry.getCalendar();
                if (date[0].equalsIgnoreCase(String.valueOf(entryDate.get(Calendar.YEAR)))
                        && date[1].equalsIgnoreCase(String.valueOf(entryDate.get(Calendar.MONTH)))
                        && date[2].equalsIgnoreCase(String.valueOf(entryDate.get(Calendar.DAY_OF_MONTH)))) {
                    filteredEntries.add(entry);
                }
            }

            filterResults.values = filteredEntries;
            filterResults.count = filteredEntries.size();

            return filterResults;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            entries = (List<Entry>) results.values;
            notifyDataSetChanged();
            String message;

            if (entries.size() > 0) {
                message = "Entries filtered";
            } else {
                message = "No entries that day";
            }
            Toast.makeText(context, message, Toast.LENGTH_LONG).show();
        }
    }
}
