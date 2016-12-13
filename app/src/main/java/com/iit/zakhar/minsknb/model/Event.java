package com.iit.zakhar.minsknb.model;

import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;

import java.util.HashMap;
import java.util.Map;

@IgnoreExtraProperties
public class Event {

    public String groupCreatorId;
    public String title;
    public String category;
    public String description;
    public String start_date;
    public Map<String, Boolean> participants = new HashMap<>();

    public Event() {
        // Default constructor required for calls to DataSnapshot.getValue(Event.class)
    }

    public Event(String groupCreatorId, String title, String category, String description, String start_date) {
        this.groupCreatorId = groupCreatorId;
        this.title = title;
        this.category = category;
        this.description = description;
        this.start_date = start_date;

    }

    // [START post_to_map]
    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("groupCreatorId", groupCreatorId);
        result.put("title", title);
        result.put("category", category);
        result.put("description", description);
        result.put("start_date", start_date);
        result.put("participants", participants);

        return result;
    }
    // [END post_to_map]
}

