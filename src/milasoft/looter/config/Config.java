package milasoft.looter.config;

import java.util.ArrayList;
import java.util.List;

import org.dreambot.api.methods.container.impl.bank.BankLocation;
import org.dreambot.api.methods.map.Area;

/**
 * A singleton class to store global configuration settings.
 * @author Milasoft
 */
public class Config {

	/**
	 * Instantiate an instance of this class. 
	 */
	private static final Config config = new Config();
	
	/**
	 * A boolean to check if our script should be running.
	 */
	private boolean scriptRunning = false;
	
	/**
	 * The area on the map to loot in.
	 */
	private Area lootArea;
	
	/**
	 * A list of items to loot.
	 */
	private List<String> items = new ArrayList<String>();
	
	/**
	 * The location of the bank to use.
	 */
	private BankLocation bankLocation;
	
	/**
	 * Our status text to display on the paint.
	 */
	private String statusText = "Configuring script.";
	
	/**
	 * Hide the constructor, so that it will never be called externally. 
	 */
	private Config() {}

	/**
	 * Returns if the script is running.
	 */
	public boolean isScriptRunning() {
		return scriptRunning;
	}

	/**
	 * Sets the scriptRunning variable.
	 */
	public void setScriptRunning(boolean scriptRunning) {
		this.scriptRunning = scriptRunning;
	}

	/**
	 * Returns the looting area.
	 */
	public Area getLootArea() {
		return lootArea;
	}

	/**
	 * Sets the looting area.
	 */
	public void setLootArea(Area lootArea) {
		this.lootArea = lootArea;
	}

	/**
	 * Returns the list of items to loot.
	 */
	public List<String> getItems() {
		return items;
	}

	/**
	 * Returns the selected bank location.
	 */
	public BankLocation getBankLocation() {
		return bankLocation;
	}

	/**
	 * Sets the bank location.
	 */
	public void setBankLocation(BankLocation bankLocation) {
		this.bankLocation = bankLocation;
	}

	/**
	 * Returns the status text.
	 */
	public String getStatusText() {
		return statusText;
	}

	/**
	 * Sets the status text.
	 */
	public void setStatusText(String statusText) {
		this.statusText = statusText;
	}

	/**
	 *  Return the instance of config. 
	 */
	public static Config getConfig() {
		return config;
	}
	
}
