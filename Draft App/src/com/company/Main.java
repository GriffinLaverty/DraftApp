package com.company;

import java.util.LinkedList;
import java.util.Random;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        LinkedList<Player> players = new LinkedList<>();
        populatePlayers(players);

        Scanner scnr = new Scanner(System.in);

        System.out.println("How many Teams are in this Draft?");
        int numTeams = scnr.nextInt();
        System.out.println("Which draft pick do you choose?");
        int userTeamNum = scnr.nextInt();
        System.out.println("How many rounds?");
        int rounds = scnr.nextInt();

        Team[] teams = new Team[numTeams];
        populateTeams(teams);
        Team userTeam = teams[userTeamNum-1];

        int r=1;
        while(r<=rounds){
            for(int p=1;p<=numTeams;p++){
                if(p==userTeamNum){
                    Player player = userPick(userTeam,players);
                    System.out.println(r + "." + p + ": You picked " + player.getName());
                }else{
                    Player player = cpuPick(teams[p-1],players,r);
                    System.out.println(r + "." + p +": Team " + p + " picked "  + player.getName());
                }

            }
            r++;
            for(int p=numTeams;p>=1;p--){
                if(p==userTeamNum){
                    Player player = userPick(userTeam,players);
                    System.out.println(r + "." + (numTeams+1-p) + ": You picked " + player.getName());
                }else{
                    Player player = cpuPick(teams[p-1],players,r);
                    System.out.println(r + "." + (numTeams+1-p) +": Team " + p + " picked "  + player.getName());
                }

            }
            r++;
        }


    }

    public static void populateTeams(Team[] teams){
       try {
           for (int i = 0; true; i++) {
               teams[i] = new Team();
           }
       } catch(ArrayIndexOutOfBoundsException ignored){
        }
    }


    public static Player userPick(Team team, LinkedList<Player> players) {

        System.out.println("You are on the clock!");
        while(true) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter a V to view available players");
            System.out.println("Enter a T to see your current team");
            System.out.println("Enter a P to choose a player to select");
            String input = scanner.next();
            input = input.toLowerCase();
            char letter = input.charAt(0);
            switch (letter) {
                case 'v':
                    System.out.println("How many of the top available players would you like to see");
                    int num = scanner.nextInt();
                    printPlayers(num, players);
                    break;

                case 'p':
                    System.out.println("Enter the player's name you would like to select");
                    String firstName = scanner.next();
                    String lastName = scanner.next();
                    for (Player player : players) {
                        if (firstName.equals(player.getFirstName()) && lastName.equals(player.getLastName())) {
                            if(!updateTeam(player, team,true)){
                                break;
                            }
                            players.remove(player);
                            return player;
                        }
                    }
                    System.out.println("Invalid Player");
                    break;

                case 't':
                    System.out.println("QB: " + team.getQB().getName());
                    System.out.println("RB1: " + team.getRB1().getName());
                    System.out.println("RB2: " + team.getRB2().getName());
                    System.out.println("WR1: " + team.getWR1().getName());
                    System.out.println("WR2: " + team.getWR2().getName());
                    System.out.println("TE: " + team.getTE().getName());
                    System.out.println("FLEX: " + team.getFLEX().getName());
                    break;

                default:
                    System.out.println("Invalid Input");
                    break;

            }
        }
    }

    public static Player cpuPick(Team team, LinkedList<Player> players, int round){
        Random rand = new Random();
        Player tempPlayer = players.get(rand.nextInt(round*2));
        updateTeam(tempPlayer, team,false);
        players.remove(tempPlayer);
        return tempPlayer;
    }

    public static boolean updateTeam(Player player, Team team, boolean userPick) {
        if (player.getPosition().equals("RB")) {
            switch (team.getNumRb()) {
                case 0:
                    team.setNumRb(1);
                    team.setRB1(player);
                    return true;
                case 1:
                    team.setNumRb(2);
                    team.setRB2(player);
                    return true;
                case 2:
                    if(team.getNumWr()<3){
                        team.setNumRb(3);
                        team.setFLEX(player);
                        return true;
                    }
                default:
                    if(userPick) {
                        System.out.println("You already have the maximum number of Running Backs, please select a different player");
                    }
                    return false;
            }
        }
        if (player.getPosition().equals("WR")) {
            switch (team.getNumWr()) {
                case 0:
                    team.setNumWr(1);
                    team.setWR1(player);
                    return true;
                case 1:
                    team.setNumWr(2);
                    team.setWR2(player);
                    return true;
                case 2:
                    if(team.getNumRb()<3){
                        team.setNumWr(3);
                        team.setFLEX(player);
                        return true;
                    }
                default:
                    if(userPick) {
                        System.out.println("You already have the maximum number of Wide Receivers, please select a different player");
                    }
                    return false;
            }
        }
        if (player.getPosition().equals("QB")) {
            if (team.getNumQb() == 0) {
                team.setNumQb(1);
                team.setQB(player);
                return true;
            }
            if(userPick) {
                System.out.println("You already have the maximum number of Quarterbacks, please select a different player");
            }
            return false;
        }
        if (player.getPosition().equals("TE")) {
            if (team.getNumTe() == 0) {
                team.setNumTe(1);
                team.setTE(player);
                return true;
            }
            if(userPick) {
                System.out.println("You already have the maximum number of Running Backs, please select a different player");
            }
            return false;
        }
        return false;
    }
    public static void printPlayers(int amount, LinkedList<Player> players){
        System.out.println("Top " + amount + " available players");
        for(int i=0;i<amount;i++){
            Player player = players.get(i);
            System.out.println(player.getPosition() + " " + player.getName());
        }
    }

    public static void populatePlayers(LinkedList<Player> players){
        players.add(new Player("RB", "Christian McCaffery", "Panthers"));
        players.add(new Player("RB", "Dalvin Cook", "Vikings"));
        players.add(new Player("RB", "Alvin Kamara", "Saints"));
        players.add(new Player("RB", "Derrick Henry","Titans"));
        players.add(new Player("RB", "Ezekiel Elliot","Cowboys"));
        players.add(new Player("WR", "Davante Adams","Packers"));
        players.add(new Player("TE", "Travis Kelce","Chiefs"));
        players.add(new Player("RB", "Nick Chubb","Browns"));
        players.add(new Player("RB", "Saquon Barkley","Giants"));
        players.add(new Player("WR", "Tyreek Hill","Chiefs"));
        players.add(new Player("WR", "Stefon Diggs","Bills"));
        players.add(new Player("RB", "Jonathan Taylor","Colts"));
        players.add(new Player("RB", "Austin Ekeler","Chargers"));
        players.add(new Player("RB", "Aaron Jones","Packers"));
        players.add(new Player("WR", "Deandre Hopkins","Titans"));
        players.add(new Player("WR", "Calvin Ridley","Falcons"));
        players.add(new Player("WR", "DK Metcalf","Seahawks"));
        players.add(new Player("RB", "Najee Harris","Steelers"));
        players.add(new Player("RB", "Antonio Gibson","Redskins"));
        players.add(new Player("TE", "Darren Waller","Raiders"));
        players.add(new Player("RB", "Deandre Swift","Lions"));
        players.add(new Player("WR", "Justin Jefferson","Vikings"));
        players.add(new Player("RB", "Clyde Edwards-Helaire","Chiefs"));
        players.add(new Player("TE", "George Kittle","49ers"));
        players.add(new Player("RB", "J.K. Dobbins","Ravens"));
        players.add(new Player("WR", "A.J. Brown","Titans"));
        players.add(new Player("QB", "Patrick Mahomes","Titans"));
        players.add(new Player("RB", "David Montgomery","Bears"));
        players.add(new Player("RB", "Chris Carson","Seahawks"));
        players.add(new Player("WR", "Allen Robinson","Bears"));
        players.add(new Player("WR", "CeeDee Lamb","Cowboys"));
        players.add(new Player("RB", "Darrel Henderson","Rams"));
        players.add(new Player("QB", "Josh Allen","Bills"));
        players.add(new Player("QB", "Kyler Murray","Cardinals"));
        players.add(new Player("WR", "Keenan Allen","Chargers"));
        players.add(new Player("WR", "Terry McLaurin","Redskins"));
        players.add(new Player("QB", "Lamar Jackson","Ravens"));
        players.add(new Player("WR", "Cooper Kupp","Rams"));
        players.add(new Player("WR", "Robert Woods","Rams"));
        players.add(new Player("WR", "Mike Evans","Buccaneers"));
        players.add(new Player("RB", "Miles Sanders","Eagles"));
        players.add(new Player("WR", "Amari Cooper","Cowboys"));
        players.add(new Player("WR", "Adam Thielen","Rams"));


    }
}
