package com.example.dawhey.sensorsapp.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.dawhey.sensorsapp.Models.Entries;

import com.example.dawhey.sensorsapp.Models.Entry;
import com.example.dawhey.sensorsapp.R;
import com.example.dawhey.sensorsapp.Utilities.EntriesAdapter;
import com.example.dawhey.sensorsapp.Utilities.EntriesEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

public class BrowseEntriesFragment extends Fragment {

    private RecyclerView entriesView;
    private EntriesAdapter entriesAdapter;
    private Entries entries;
    private List<Entry> entriesList;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        entriesList = new ArrayList<>();
        Bundle arguments = this.getArguments();
        if (arguments != null) {
            entries = (Entries) arguments.getSerializable("entries");
            if (entries != null) {
                entriesList = entries.getEntries();
            }
        }
        entriesAdapter = new EntriesAdapter(getContext(), entriesList);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.last_entries, null);
        getActivity().setTitle(getString(R.string.browse_entries_title));
        entriesView = (RecyclerView) v.findViewById(R.id.entries_view);
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        entriesView.setLayoutManager(manager);
        entriesView.setAdapter(entriesAdapter);
        return v;
    }

    @Subscribe
    public void onEntriesEvent(EntriesEvent event) {
        this.entries = event.getEntries();
        this.entriesList = event.getEntries().getEntries();
        entriesAdapter.swap(this.entriesList);
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }
}
