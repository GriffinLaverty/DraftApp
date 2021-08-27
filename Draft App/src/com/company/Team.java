package com.company;

import java.util.ArrayList;

public class Team {
    private ArrayList<Player> Players;
    private int numRb;
    private int numWr;
    private int numQb;
    private int numTe;
    private Player QB;
    private Player RB1;
    private Player RB2;
    private Player WR1;
    private Player WR2;
    private Player TE;
    private Player FLEX;

    public Player getRB2() {
        return RB2;
    }

    public void setRB2(Player RB2) {
        this.RB2 = RB2;
    }

    public Player getWR2() {
        return WR2;
    }

    public void setWR2(Player WR2) {
        this.WR2 = WR2;
    }

    public Player getFLEX() {
        return FLEX;
    }

    public void setFLEX(Player FLEX) {
        this.FLEX = FLEX;
    }

    public Player getQB() {
        return QB;
    }

    public void setQB(Player QB) {
        this.QB = QB;
    }

    public Player getRB1() {
        return RB1;
    }

    public void setRB1(Player RB1) {
        this.RB1 = RB1;
    }

    public Player getWR1() {
        return WR1;
    }

    public void setWR1(Player WR1) {
        this.WR1 = WR1;
    }

    public Player getTE() {
        return TE;
    }

    public void setTE(Player TE) {
        this.TE = TE;
    }

    public ArrayList<Player> getPlayers() {
        return Players;
    }

    public void setPlayers(ArrayList<Player> players) {
        Players = players;
    }

    public int getNumRb() {
        return numRb;
    }

    public void setNumRb(int numRb) {
        this.numRb = numRb;
    }

    public int getNumWr() {
        return numWr;
    }

    public void setNumWr(int numWr) {
        this.numWr = numWr;
    }

    public int getNumQb() {
        return numQb;
    }

    public void setNumQb(int numQb) {
        this.numQb = numQb;
    }

    public int getNumTe() {
        return numTe;
    }

    public void setNumTe(int numTe) {
        this.numTe = numTe;
    }

    public Team() {
        Players = new ArrayList<>();
        this.numRb = 0;
        this.numWr = 0;
        this.numQb = 0;
        this.numTe = 0;
        QB = new Player();
        RB1 = new Player();
        WR1 = new Player();
        TE = new Player();
        RB2 = new Player();
        WR2 = new Player();
        FLEX = new Player();
    }
}
