package com.seg.domain.enumeration;

public enum Status {
    ACTIVE ("Active"),        
    PENDING("Pending"),        
    REJECTED ("Rejected");        
    
    private String status;    

    private Status(final String status) {
        this.status = status;
    }

    @Override
    public String toString() {     
        return status;
    }
}
