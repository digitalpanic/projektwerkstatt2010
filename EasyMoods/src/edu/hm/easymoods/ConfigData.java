package edu.hm.easymoods;

/**
 * Singleton Class
 * @author Franziska
 *
 */
public class ConfigData {

	private String ipAddress;
	private StellaSetType type;
	private static ConfigData configDataInstance;
	
	private ConfigData(){};
	
	public static ConfigData getConfigDataInstance() {
		if (configDataInstance == null) {
			configDataInstance = new ConfigData();
		}
		
		return configDataInstance;
		
	}

	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	public StellaSetType getType() {
		return type;
	}

	public void setType(StellaSetType type) {
		this.type = type;
	}
	
	
	
}
