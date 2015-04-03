package com.thedeveloperworldisyours.mubalooorganisation.models;

import com.thedeveloperworldisyours.mubalooorganisation.dummy.DummyContent;
import com.thedeveloperworldisyours.mubalooorganisation.interfaces.Item;

/**
 * Created by javiergonzalezcabezas on 3/4/15.
 */
public class DummyItem implements Item{
    public String id;
    private String name;
    private String profileImageURL;
    private String role;

    public DummyItem(String firstName){
        this.name = firstName;
    }

    public DummyItem(String id, String firstName, String profileImageURL, String role) {
        this.id = id;
        this.name = firstName;
        this.profileImageURL = profileImageURL;
        this.role = role;
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

    @Override
    public boolean isSection() {
        return false;
    }
}
