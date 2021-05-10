package com.sssoyalan.soccerleague.models;

public class MatchModel {

    private String homeTeam;
    private String awayTeam;
    private int matchWeek;

    public MatchModel(String homeTeam, String awayTeam, int matchWeek) {
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.matchWeek = matchWeek;
    }

    public MatchModel(){}

    public String getHomeTeam() {
        return homeTeam;
    }

    public void setHomeTeam(String homeTeam) {
        this.homeTeam = homeTeam;
    }

    public String getAwayTeam() {
        return awayTeam;
    }

    public void setAwayTeam(String awayTeam) {
        this.awayTeam = awayTeam;
    }

    public int getMatchWeek() {
        return matchWeek;
    }

    public void setMatchWeek(int matchWeek) {
        this.matchWeek = matchWeek;
    }
}
