package com.seg.domain.enumeration;

public enum Role {    
    ADMINISTRATOR ("Administrator"),
    CHEMISTRY_ANALYSIS ("Chemistry Analysis"), 
    GEOLOGY ("Geology"),
    GUEST ("Guest");
    
    private final String role;

    private Role(final String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return role;     
    }    
}
