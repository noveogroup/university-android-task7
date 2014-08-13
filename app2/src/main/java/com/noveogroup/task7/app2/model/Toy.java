package com.noveogroup.task7.app2.model;

public class Toy {

    private int id;
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
