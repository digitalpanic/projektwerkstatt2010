package edu.hm.easymoods;

public class CustomProfile {

	private int rValue;
	private int gValue;
	private int bValue;
	private int dimValue;
	private StellaSetType type;
	
	private String name;
	
	private int scent;
	private String song;
	
	public CustomProfile(String name) {
		this.name = name;
	}
	
	public void setColor(int r, int g, int b, int dim) {
		this.rValue = r;
		this.gValue = g;
		this.bValue = b;
		this.dimValue = dim;
	}
	
	public void setScent(int scentNumber) {
		this.scent = scentNumber;
	}
	
	public void setStellaSetType(StellaSetType type) {
		this.type = type;
	}
	
	public void setSong(String songTitle) {
		this.song = songTitle;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getRValue() {
		return rValue;
	}

	public int getGValue() {
		return gValue;
	}

	public int getBValue() {
		return bValue;
	}

	public int getDimValue() {
		return dimValue;
	}

	public StellaSetType getStellaSetType() {
		return type;
	}

	public int getScent() {
		return scent;
	}

	public String getSong() {
		return song;
	}
	

}
