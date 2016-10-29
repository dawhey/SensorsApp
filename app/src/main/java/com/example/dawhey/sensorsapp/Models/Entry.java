
package com.example.dawhey.sensorsapp.Models;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

@Generated("org.jsonschema2pojo")
public class Entry implements Serializable {

    @SerializedName("device")
    @Expose
    private String device;
    @SerializedName("humidity")
    @Expose
    private Double humidity;
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("temperature")
    @Expose
    private Double temperature;
    @SerializedName("timestamp")
    @Expose
    private String timestamp;

    /**
     *
     * @return
     *     The device
     */
    public String getDevice() {
        return device;
    }

    /**
     *
     * @param device
     *     The device
     */
    public void setDevice(String device) {
        this.device = device;
    }

    /**
     * 
     * @return
     *     The humidity
     */
    public Double getHumidity() {
        return humidity;
    }

    /**
     * 
     * @param humidity
     *     The humidity
     */
    public void setHumidity(Double humidity) {
        this.humidity = humidity;
    }

    /**
     * 
     * @return
     *     The id
     */
    public Integer getId() {
        return id;
    }

    /**
     * 
     * @param id
     *     The id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 
     * @return
     *     The temperature
     */
    public Double getTemperature() {
        return temperature;
    }

    /**
     * 
     * @param temperature
     *     The temperature
     */
    public void setTemperature(Double temperature) {
        this.temperature = temperature;
    }

    /**
     * 
     * @return
     *     The timestamp
     */
    public String getTimestamp() {
        return timestamp;
    }

    /**
     * 
     * @param timestamp
     *     The timestamp
     */
    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public float getUnixTimestamp() {
        float unixTimestamp = 0;
        DateFormat dateFormat = new SimpleDateFormat("EEE, dd MMM yyyy hh:mm:ss z");
        try {
            Date date = dateFormat.parse(timestamp);
            unixTimestamp = date.getTime()/1000;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return unixTimestamp;
    }

    public String getFormattedTimestamp() {
        String formattedTimestamp = "";
        DateFormat inFormat = new SimpleDateFormat("EEE, dd MMM yyyy hh:mm:ss z");
        DateFormat outFormat = new SimpleDateFormat("dd MMM HH:mm");
        outFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
        try {
            Date date = inFormat.parse(timestamp);
            formattedTimestamp = outFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return formattedTimestamp;
    }

    public Calendar getCalendar() {
        TimeZone timeZone = TimeZone.getTimeZone("GMT");
        DateFormat dateFormat = new SimpleDateFormat("EEE, dd MMM yyyy hh:mm:ss z");
        dateFormat.setTimeZone(timeZone);
        Calendar calendar = new GregorianCalendar(timeZone);
        try {
            calendar.setTime(dateFormat.parse(timestamp));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return calendar;
    }
}
