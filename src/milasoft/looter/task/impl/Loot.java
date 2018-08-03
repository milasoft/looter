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
		 * Make sure our script is running and our inventory is not full.
		 */
		return config.isScriptRunning() && !getInventory().isFull();
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
			GroundItem g = getGroundItems().closest(f -> canPickup(f) && isItemInList(f.getName()));
			/**
			 * Make sure the item is valid.
			 */
			if(g != null) {
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
	private boolean isItemInList(String name) {
		return config.getItems().stream().anyMatch(s -> s.equals(name));
	}
	
	/**
	 * This helper method checks if the item can be picked up.
	 */
	private boolean canPickup(GroundItem g) {
		return getMap().canReach(g) && config.getLootArea().contains(g);
	}
}
