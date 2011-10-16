package ua.dp.zaychenko.betmanager.data;

import java.util.Date;

public class Match {

	int id;
	String firstTeam;
	String secondTeam;
	String forecast;
	float koef;
	float chance;
	float sumBet;
	int firstTeamGoals;
	int secondTeamGoals;
	double ruselt;
	Date date;
	boolean bullits;

	public boolean isBullits() {
		return bullits;
	}

	public void setBullits(boolean bullits) {
		this.bullits = bullits;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstTeam() {
		return firstTeam;
	}

	public void setFirstTeam(String firstTeam) {
		this.firstTeam = firstTeam;
	}

	public String getSecondTeam() {
		return secondTeam;
	}

	public void setSecondTeam(String secondTeam) {
		this.secondTeam = secondTeam;
	}

	public String getForecast() {
		return forecast;
	}

	public void setForecast(String forecast) {
		this.forecast = forecast;
	}

	public float getKoef() {
		return koef;
	}

	public void setKoef(float koef) {
		this.koef = koef;
	}

	public float getChance() {
		return chance;
	}

	public void setChance(float chance) {
		this.chance = chance;
	}

	public float getSumBet() {
		return sumBet;
	}

	public void setSumBet(float sumBet) {
		this.sumBet = sumBet;
	}

	public int getFirstTeamGoals() {
		return firstTeamGoals;
	}

	public void setFirstTeamGoals(int firstTeamGoals) {
		this.firstTeamGoals = firstTeamGoals;
	}

	public int getSecondTeamGoals() {
		return secondTeamGoals;
	}

	public void setSecondTeamGoals(int secondTeamGoals) {
		this.secondTeamGoals = secondTeamGoals;
	}

	public double getRuselt() {
		return ruselt;
	}

	public void setRuselt(double ruselt) {
		this.ruselt = ruselt;
	}

}
