package com.example.websocketdemo.model;


public class UserModel {

	private UserType type;
    private String name;
    private String sessionId;

    public enum UserType {
    	PUBLIC,
        NORMAL,
        ADMIN,
        SUPER
    }

    public UserType getType() {
        return type;
    }

    public void setType(UserType type) {
        this.type = type;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSessionId() {
        return this.sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }
    
}
