package com.example.team_project.Adapter;

import java.io.Serializable;

public class LatestDATA implements Serializable {
    private String low_name;
    private String member;

    @Override
    public String toString() {
        return "LatestDATA{" +
                "low_name='" + low_name + '\'' +
                ", member='" + member + '\'' +
                '}';
    }

    public String getLow_name() {
        return low_name;
    }

    public void setLow_name(String low_name) {
        this.low_name = low_name;
    }

    public String getMember() {
        return member;
    }

    public void setMember(String member) {
        this.member = member;
    }

    public LatestDATA(String low_name, String member) {
        this.low_name = low_name;
        this.member = member;
    }
}
