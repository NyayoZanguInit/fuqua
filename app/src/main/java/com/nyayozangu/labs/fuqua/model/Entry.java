package com.nyayozangu.labs.fuqua.model;


import com.google.firebase.Timestamp;

public class Entry {

    public Timestamp createdAt;
    public int isCompleted;
    public  int lake;
    public int uf;
    public int sand;
    public int supply;
    public int makeup;
    public  int scrubber;
    public int condensate;

    public Entry(){}

    public Entry(int lake, int uf, int sand, int supply, int makeup, int scrubber, int condensate, Timestamp created_at, int is_completed) {
        this.lake = lake;

        this.uf = uf;
        this.sand = sand;
        this.makeup = makeup;
        this.supply = supply;
        this.scrubber = scrubber;
        this.condensate = condensate;
        this.createdAt = created_at;
        this.isCompleted = is_completed;
    }

    public int getLake() {
        return lake;
    }

    public void setLake(int lake) {
        this.lake = lake;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public int getIsCompleted() {
        return isCompleted;
    }

    public void setIsCompleted(int isCompleted) {
        this.isCompleted = isCompleted;
    }

    public int getUf() {
        return uf;
    }

    public void setUf(int uf) {
        this.uf = uf;
    }

    public int getSand() {
        return sand;
    }

    public void setSand(int sand) {
        this.sand = sand;
    }

    public int getSupply() {
        return supply;
    }

    public void setSupply(int supply) {
        this.supply = supply;
    }

    public int getMakeup() {
        return makeup;
    }

    public void setMakeup(int makeup) {
        this.makeup = makeup;
    }

    public int getCondensate() {
        return condensate;
    }

    public int getScrubber() {
        return scrubber;
    }

    public void setScrubber(int scrubber) {
        this.scrubber = scrubber;
    }

    public void setCondensate(int condensate) {
        this.condensate = condensate;
    }
}
