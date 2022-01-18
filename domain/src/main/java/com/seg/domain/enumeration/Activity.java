package com.seg.domain.enumeration;

public enum Activity {
    REGISTER ("Registesred"),
    ADD ("Added"),
    EDIT ("Edited"),
    DELETE ("Deleted");
    
    private final String activity;

    private Activity(final String activity) {
        this.activity = activity;
    }

    @Override
    public String toString() {
        return activity;
    }  

}
