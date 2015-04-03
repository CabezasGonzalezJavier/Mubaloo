package com.thedeveloperworldisyours.mubalooorganisation.models;

import com.thedeveloperworldisyours.mubalooorganisation.dummy.DummyContent;
import com.thedeveloperworldisyours.mubalooorganisation.interfaces.Item;

/**
 * Created by javiergonzalezcabezas on 3/4/15.
 */
public class SectionItem extends DummyContent implements Item {


    private final String title;


    public SectionItem(String title) {
        this.title = title;
    }

    public String getTitle(){
        return title;
    }


    @Override
    public boolean isSection() {
        return true;
    }

}
