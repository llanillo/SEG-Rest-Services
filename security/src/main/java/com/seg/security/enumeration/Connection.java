package com.seg.security.enumeration;

public enum Connection {
    SESSION("/session"),
    LOGIN("/login");

    private final String connection;

    private Connection(String connection) {
        this.connection = connection;
    }

    public static String loginUrl() {
        return SESSION.toString() + LOGIN.toString();
    }

    @Override
    public String toString() {
        return connection;
    }
}
