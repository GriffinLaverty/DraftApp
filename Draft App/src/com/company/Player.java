package com.company;

public class Player {
    private String position;
    private String name;
    private String team;

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public Player(String position, String name, String team) {
        this.position = position;
        this.name = name;
        this.team = team;
    }

    public Player() {
        this.position="";
        this.name="";
        this.team="";
    }

    public String getLastName(){
        String[] names = name.split("\\s");
        return names[1];
    }

    public String getFirstName(){
        String[] names = name.split("\\s");
        return names[0];
    }
}
