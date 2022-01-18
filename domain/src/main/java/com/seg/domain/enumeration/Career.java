package com.seg.domain.enumeration;

public enum Career {
    COMPUTER_SCIENE("Computer Sciente"),
    SOFTWARE_ENGINEER("Software Engineer");
    
    private final String career;

    private Career(final String career) {
        this.career = career;
    }

    @Override
    public String toString() {
        return career;
    }
}
