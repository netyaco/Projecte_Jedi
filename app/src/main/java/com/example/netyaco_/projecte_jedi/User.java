package com.example.netyaco_.projecte_jedi;

/**
 * Created by netyaco_ on 01/02/2016.
 */
public class User {

    private String name;
    private Integer points;

    User(String name, Integer points) {
        this.name = name;
        this.points = points;
    }

    User() {

    }

    public String getName() {
        return name;
    }


    public Integer getPoints() {
        return points;
    }

    public void setName(String name) {
        this.name = name;
    }


}
