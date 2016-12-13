package com.iit.zakhar.minsknb.model;

import com.google.firebase.database.IgnoreExtraProperties;

import java.util.HashMap;
import java.util.Map;

@IgnoreExtraProperties
public class User {

    public String email;
    public String password;
    public String username;
    public Boolean lfpStatus;
    public Map <String, Boolean> groupMember= new HashMap<>();


    public User() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public User(String email, String password, String username,Map<String, Boolean> groupMember, Boolean lfpStatus) {
        this.email = email;
        this.password = password;
        this.username = username;
        this.groupMember = groupMember;
        this.lfpStatus = lfpStatus;
    }

}
