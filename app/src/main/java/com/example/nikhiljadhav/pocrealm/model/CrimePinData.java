package com.example.nikhiljadhav.pocrealm.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;
import io.realm.annotations.Required;

public class CrimePinData extends RealmObject {

    @SerializedName("i")
    @Expose
    private Integer i;
    @SerializedName("lo")
    @Expose
    private Double lo;
    @SerializedName("la")
    @Expose
    private Double la;
    @SerializedName("t")
    @Expose
    private String t;
    @SerializedName("d")
    @Expose
    private String d;
    @SerializedName("SourceName")
    @Expose
    private String sourceName;

    public Integer getI() {
        return i;
    }

    public void setI(Integer i) {
        this.i = i;
    }

    public Double getLo() {
        return lo;
    }

    public void setLo(Double lo) {
        this.lo = lo;
    }

    public Double getLa() {
        return la;
    }

    public void setLa(Double la) {
        this.la = la;
    }

    public String getT() {
        return t;
    }

    public void setT(String t) {
        this.t = t;
    }

    public String getD() {
        return d;
    }

    public void setD(String d) {
        this.d = d;
    }

    public String getSourceName() {
        return sourceName;
    }

    public void setSourceName(String sourceName) {
        this.sourceName = sourceName;
    }

}