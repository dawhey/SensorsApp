
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

    public static List<Entry> getLatestEntries(Entries entries) {
        List<Entry> lastEntries = new ArrayList<>();

        for(int counter = entries.getEntries().size() - 1; counter >= entries.getEntries().size() - 10; counter--){
            lastEntries.add(entries.getEntries().get(counter));
        }
        return lastEntries;
    }
}
