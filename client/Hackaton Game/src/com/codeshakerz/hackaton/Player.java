package com.codeshakerz.hackaton;

public class Player {
	private String	nick;
	private double	longitude;
	private double	latitude;
	private int		health;
	private int		exp;
	private int 	level;
	
	Player(String nick, double longitude, double latitude, int health, int exp, int level) {
		this.nick = nick;
		this.longitude = longitude;
		this.latitude = latitude;
		this.health = health;
		this.exp = exp;
		this.level = level;
	}
};
