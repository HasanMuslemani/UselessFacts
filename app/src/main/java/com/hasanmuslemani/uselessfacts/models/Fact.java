package com.hasanmuslemani.uselessfacts.models;

import androidx.annotation.NonNull;

public class Fact {

    private String id;
    private String text;

    public Fact() {}

    public Fact(String id, String text) {
        this.id = id;
        this.text = text;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @NonNull
    @Override
    public String toString() {
        return this.text;
    }
}
