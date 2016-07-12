package com.noveo.internship.databaseexample.model;

public class Toy {

    private Integer id;
    private String title;
    private int cost;

    public Toy(int cost, String title) {
        this.cost = cost;
        this.title = title;
    }

    public int getCost() {
        return cost;
    }

    public String getTitle() {
        return title;
    }

}
