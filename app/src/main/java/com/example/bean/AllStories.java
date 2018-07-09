package com.example.bean;

import java.util.List;

/**
 * @author master
 * @date 2018/4/9
 */

public class AllStories {

    private String date;
    private List<Stories> stories;
    private List<Stories> top_stories;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<Stories> getStories() {
        return stories;
    }

    public void setStories(List<Stories> stories) {
        this.stories = stories;
    }

    public List<Stories> getTop_stories() {
        return top_stories;
    }

    public void setTop_stories(List<Stories> top_stories) {
        this.top_stories = top_stories;
    }

    @Override
    public String toString() {
        return "AllStories{" +
                "date='" + date + '\'' +
                ", stories=" + stories +
                ", top_stories=" + top_stories +
                '}';
    }
}
