package com.anpn.kudago;


import com.google.gson.JsonArray;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Events {

    @SerializedName("title")
    @Expose
    private String title;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @SerializedName("images")
    @Expose
    private JsonArray images;

    public JsonArray getImages() {
        return images;
    }

    public void setImages(JsonArray images) {
        this.images = images;
    }

    @SerializedName("dates")
    @Expose
    private JsonArray dates;

    public JsonArray getDates() {
        return dates;
    }

    public void setDates(JsonArray dates) {
        this.dates = dates;
    }

    @SerializedName("description")
    @Expose
    private String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String dates) {
        this.description = description;
    }

}

