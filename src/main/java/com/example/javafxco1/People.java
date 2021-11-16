package com.example.javafxco1;

public class People {

    private String id;
    private String visa;

    public People(String id, String visa) {
        this.id = id;
        this.visa = visa;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getVisa() {
        return visa;
    }

    public void setVisa(String visa) {
        this.visa = visa;
    }
}
