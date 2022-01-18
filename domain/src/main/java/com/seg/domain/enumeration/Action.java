package com.seg.domain.enumeration;

public enum Action {
    ADD ("Add"),
    EDIT ("Edit"),
    DELETE ("Delete"),
    NONE ("");    
     
    private final String action; 

    private Action(final String action) {
        this.action = action;
    }    

    @Override
    public String toString() {
        return action;
    }
}
