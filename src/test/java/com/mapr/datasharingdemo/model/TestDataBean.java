package com.mapr.datasharingdemo.model;

/**
 * Created by jojrg on 31.01.18.
 */
public class TestDataBean {

    private String id;
    private String name;

    public TestDataBean(String aId, String aName) {
        id = aId;
        name = aName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }



}
