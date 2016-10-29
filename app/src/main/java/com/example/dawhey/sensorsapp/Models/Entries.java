
package com.example.dawhey.sensorsapp.Models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("org.jsonschema2pojo")
public class Entries implements Serializable {

    @SerializedName("entries")
    @Expose
    private List<Entry> entries = new ArrayList<Entry>();

    /**
     * 
     * @return
     *     The entries
     */
    public List<Entry> getEntries() {
        return entries;
    }

    /**
     * 
     * @param entries
     *     The entries
     */
    public void setEntries(List<Entry> entries) {
        this.entries = entries;
    }

    public List<Entry> getReversedEntries() {
        List<Entry> reversedEntries = new ArrayList<>();

        for (int i = entries.size() - 1; i >= 0; i--) {
            reversedEntries.add(entries.get(i));
        }
        return reversedEntries;
    }
}
