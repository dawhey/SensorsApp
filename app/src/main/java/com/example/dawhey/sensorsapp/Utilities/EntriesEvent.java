package com.example.dawhey.sensorsapp.Utilities;

import com.example.dawhey.sensorsapp.Models.Entries;

/**
 * Created by dawhey on 20.09.16.
 */
public class EntriesEvent {

    private Entries entries;

    public EntriesEvent(Entries entries) {
        this.entries = entries;
    }

    public Entries getEntries() {
        return entries;
    }

    public void setEntries(Entries entries) {
        this.entries = entries;
    }

}
