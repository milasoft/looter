package milasoft.looter;

import java.awt.Graphics;

import org.dreambot.api.methods.container.impl.bank.BankLocation;
import org.dreambot.api.methods.map.Area;
import org.dreambot.api.script.Category;
import org.dreambot.api.script.ScriptManifest;
import org.dreambot.api.script.impl.TaskScript;

import milasoft.looter.config.Config;
import milasoft.looter.task.impl.Bank;
import milasoft.looter.task.impl.Loot;

/**
 * The main class of the script.
 * @author Milasoft
 *
 */
@ScriptManifest(author = "Milasoft", category = Category.MONEYMAKING, name = "Milasoft Looter", version = 1.0)
public class Looter extends TaskScript {

	/**
	 * Get an instance of the Config class. 
	 */
	Config config = Config.getConfig();
	
	@Override
	public void onStart() {
		/**
		 * Set the area to loot. (Set to Lumbridge cows for testing.
		 */
		config.setLootArea(new Area(3253, 3272, 3265, 3255));
		
		/**
		 * Add the item(s) to loot to the item list.
		 */
		config.getItems().add("Bones");
		
		/**
		 * Set the bank location.
		 */
		config.setBankLocation(BankLocation.LUMBRIDGE);
		
		/**
		 * Add our tasks to the script.
		 */
		addNodes(new Bank(), new Loot());
	}
	
	@Override
	public void onPaint(Graphics g) {
		/**
		 * Draw some simple text showing which task we are doing.
		 */
		g.drawString("Status: " + config.getStatusText(), 10, 35);
	}
}
