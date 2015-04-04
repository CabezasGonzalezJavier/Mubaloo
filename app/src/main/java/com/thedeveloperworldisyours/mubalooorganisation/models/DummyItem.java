package com.thedeveloperworldisyours.mubalooorganisation.models;

import com.thedeveloperworldisyours.mubalooorganisation.dummy.DummyContent;

/**
 * Created by javiergonzalezcabezas on 3/4/15.
 */
public class DummyItem {
    public String id;
    private String name;
    private String profileImageURL;
    private String role;
    private boolean section;

    public DummyItem(String firstName){
        this.name = firstName;
    }

    public DummyItem(String id, String firstName, String profileImageURL, String role,boolean section) {
        this.id = id;
        this.name = firstName;
        this.profileImageURL = profileImageURL;
        this.role = role;
        this.section = section;
    }
    @Override
    public String toString() {
        return name;
    }

    public String getName() {
        return name;
    }

    public String getProfileImageURL() {
        return profileImageURL;
    }

    public String getRole() {
        return role;
    }

    public boolean isSection() {
        return section;
    }
}
