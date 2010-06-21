package edu.hm.easymoods;

public class ProfileData {
	
	private int rValue;
	private int gValue;
	private int bValue;
	private int dimValue;
	private int scentValue;
	private String name;
	private String description;
	
	public ProfileData(int r, int g, int b, int d, int scent, String n, String desc) {
		rValue = r;
		gValue = g;
		bValue = b;
		dimValue = d;
		scentValue = scent;
		name = n;
		description = desc;
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

	public int getScentValue() {
		return scentValue;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}
	
}
