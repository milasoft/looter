package milasoft.looter.task.impl;

import org.dreambot.api.methods.Calculations;
import org.dreambot.api.wrappers.items.GroundItem;

import milasoft.looter.task.AbstractTask;

/**
 * This task will check if the player should pick up the item, and pick it up if it should.
 * @author Milasoft
 *
 */
public class Loot extends AbstractTask {

	@Override
	public boolean accept() {
		/**
		 * Make sure our script is supposed to run.
		 */
		if(config.isScriptRunning()) {
			/**
			 * If the players inventory is not full, this task can execute.
			 */
			if(!getInventory().isFull()) {
				return true;
			}
		}
		/**
		 * Return false if the script isn't running and the inventory is full.
		 */
		return false;
	}
	
	@Override
	public int execute() {
		/**
		 * Change our status text to display on the paint.
		 */
		config.setStatusText("Looting");
		/**
		 * First we make sure the player is located in the looting area, and if not, walk to the looting area.
		 */
		if(config.getLootArea().contains(getLocalPlayer())) {
			/**
			 * Check if any items are on the ground and pick them up if there is.
			 */
			GroundItem g = getGroundItems().closest(f -> f.isOnScreen() && config.getLootArea().contains(f) && isItemInList(f.getName()));
			/**
			 * Make sure the item is valid.
			 */
			if(g != null && getMap().canReach(g)) {
				/**
				 * Pick up item.
				 */
				g.interact("Take");
			}
		} else {
			/**
			 * Walk to the looting area.
			 */
			if(getWalking().shouldWalk(4)) {
				getWalking().walk(config.getLootArea().getRandomTile());
			}
		}
		return Calculations.random(500);
	}
	
	/**
	 * This helper method checks our loot list to see if it contains the item.
	 */
	public boolean isItemInList(String name) {
		return config.getItems().stream().anyMatch(s -> s.equals(name));
	}

}
