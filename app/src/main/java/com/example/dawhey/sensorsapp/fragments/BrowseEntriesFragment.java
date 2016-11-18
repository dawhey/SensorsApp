package com.example.dawhey.sensorsapp.fragments;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;

import com.example.dawhey.sensorsapp.Models.Entries;

import com.example.dawhey.sensorsapp.Models.Entry;
import com.example.dawhey.sensorsapp.R;
import com.example.dawhey.sensorsapp.Utilities.EntriesAdapter;
import com.example.dawhey.sensorsapp.Utilities.EntriesEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class BrowseEntriesFragment extends Fragment implements View.OnClickListener, DatePickerDialog.OnDateSetListener {

    private RecyclerView entriesView;
    private EntriesAdapter entriesAdapter;
    private FloatingActionButton fab;
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
                entriesList = entries.getReversedEntries();
            }
        }
        entriesAdapter = new EntriesAdapter(getContext(), entriesList);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.browse_entries, null);
        getActivity().setTitle(getString(R.string.browse_entries_title));
        entriesView = (RecyclerView) v.findViewById(R.id.entries_view);
        fab = (FloatingActionButton) v.findViewById(R.id.floatingActionButton);
        fab.setOnClickListener(this);
        LinearLayoutManager manager = new LinearLayoutManager(getContext());

        entriesView.setLayoutManager(manager);
        entriesView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                if (dy > 0 && fab.isShown()) {
                    fab.hide();
                } else if (dy < 0 && !fab.isShown()) {
                    fab.show();
                }
            }

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                if (newState == RecyclerView.SCROLL_STATE_IDLE)
                {
                    fab.show();
                }

                super.onScrollStateChanged(recyclerView, newState);
            }
        });
        entriesView.setAdapter(entriesAdapter);


        return v;
    }

    @Subscribe
    public void onEntriesEvent(EntriesEvent event) {
        this.entries = event.getEntries();
        this.entriesList = event.getEntries().getReversedEntries();
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

    @Override
    public void onClick(View v) {
        Calendar calendar = Calendar.getInstance();

        DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(),
                this,
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH));

        datePickerDialog.show();
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        String separator = ",";
        String constraint = year + separator + month + separator + dayOfMonth;
        entriesAdapter.getFilter().filter(constraint);
    }
}
