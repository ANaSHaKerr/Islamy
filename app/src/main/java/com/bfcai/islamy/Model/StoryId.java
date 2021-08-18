package com.bfcai.islamy.Model;

import androidx.annotation.NonNull;

import com.google.firebase.firestore.Exclude;

public class StoryId {
    @Exclude
    public String storyId;


    public  <T extends  StoryId> T withId(@NonNull final String id){
        this.storyId = id;
        return  (T) this;
    }

}
