package com.sssoyalan.soccerleague.models;

import java.util.List;

public class FixtureModel {

    private int week;
    private List<MatchModel> match_model;

    public FixtureModel(int week, List<MatchModel> match_model) {
        this.week = week;
        this.match_model = match_model;
    }

    public FixtureModel(){ }

    public int getWeek() {
        return week;
    }

    public void setWeek(int week) {
        this.week = week;
    }

    public List<MatchModel> getMatch_model() {
        return match_model;
    }

    public void setMatch_model(List<MatchModel> match_model) {
        this.match_model = match_model;
    }
}
