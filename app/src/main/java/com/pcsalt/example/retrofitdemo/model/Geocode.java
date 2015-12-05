package com.pcsalt.example.retrofitdemo.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.pcsalt.example.retrofitdemo.model.geocode.Result;
import com.pcsalt.example.retrofitdemo.model.geocode.STATUS;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by krrishnaaaa on Dec 06, 2015
 */
public class Geocode {
    @SerializedName("results")
    @Expose
    private List<Result> results = new ArrayList<>();
    @SerializedName("status")
    @Expose
    private STATUS status;

    /**
     * @return The results
     */
    public List<Result> getResults() {
        return results;
    }

    /**
     * @param results The results
     */
    public void setResults(List<Result> results) {
        this.results = results;
    }

    /**
     * @return The status
     */
    public STATUS getStatus() {
        return status;
    }

    /**
     * @param status The status
     */
    public void setStatus(STATUS status) {
        this.status = status;
    }
}
