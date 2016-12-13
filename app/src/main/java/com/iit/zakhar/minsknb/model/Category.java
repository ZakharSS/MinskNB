package com.iit.zakhar.minsknb.model;


public class Category {
    public String id;
    public String category;

    public Category() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public Category(String id, String category) {
        this.id = id;
        this.category = category;
    }

}
