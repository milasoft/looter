package milasoft.looter;

import java.awt.Graphics;

import org.dreambot.api.script.Category;
import org.dreambot.api.script.ScriptManifest;
import org.dreambot.api.script.impl.TaskScript;

import milasoft.looter.config.Config;
import milasoft.looter.context.ContextProvider;
import milasoft.looter.gui.GUI;
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
	
	/**
	 * Declare our gui.
	 */
	GUI gui;
	
	@Override
	public void onStart() {
		/**
		 * Pass an instance of MethodContext to our ContextProvider to use in our gui.
		 */
		ContextProvider.initialize(getClient().getMethodContext());
		/**
		 * Instantiate our GUI after we provide context to our ContextProvider.
		 */
		gui = new GUI();
		/**
		 * Display our GUI.
		 */
		gui.show();
		/**
		 * Add our task nodes to the script.
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
	
	@Override
	public void onExit() {
		/**
		 * Close our gui if left open.
		 */
		gui.dispose();
	}
}
