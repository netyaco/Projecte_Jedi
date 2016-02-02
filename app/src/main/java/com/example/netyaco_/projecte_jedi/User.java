package com.example.netyaco_.projecte_jedi;

/**
 * Created by netyaco_ on 01/02/2016.
 */
public class User {

    private String name;
    private String pass;
    private Integer points, rank;

    User(String name, String pass, Integer points, Integer rank) {
        this.name = name;
        this.pass = pass;
        this.points = points;
        this.rank = rank;
    }

    User() {

    }

    public String getName() {
        return name;
    }

    public String getPass() {
        return pass;
    }

    public Integer getPoints() {
        return points;
    }

    public Integer getRank() {
        return rank;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPass (String pass) {
        this.pass = pass;
    }

}
