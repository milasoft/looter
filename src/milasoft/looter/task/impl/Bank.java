/**
 * 
 */
package milasoft.looter.task.impl;

import org.dreambot.api.methods.Calculations;

import milasoft.looter.task.AbstractTask;

/**
 * This task will go to the bank and deposit the loot.
 * @author Milasoft
 */
public class Bank extends AbstractTask {

	@Override
	public boolean accept() {
		/**
		 * Make sure our script is supposed to run.
		 */
		if(config.isScriptRunning()) {
			/**
			 * This task can only run if our inventory is full.
			 */
			if(getInventory().isFull()) {
				return true;
			}
		}
		/**
		 * Return false if the script isn't running and the inventory is not full.
		 */
		return false;
	}

	@Override
	public int execute() {
		/**
		 * Change our status text to display on the paint.
		 */
		config.setStatusText("Banking");
		
		/**
		 * First check if the player is at the bank.
		 */
		if(config.getBankLocation().getArea(1).contains(getLocalPlayer())) {
			/**
			 * If the bank is not open, open the bank.
			 */
			if(!getBank().isOpen()) {
				getBank().openClosest();
				sleepUntil(() -> getBank().isOpen(), 1500);
			}
			/**
			 * Deposit all items and close the bank.
			 */
			getBank().depositAllItems();
			getBank().close();
			
		} else {
			/**
			 * Walk to a random tile in the bank.
			 */
			if(getWalking().shouldWalk(3)) {
				getWalking().walk(config.getBankLocation().getArea(1).getRandomTile());
			}
		}
		return Calculations.random(500);
	}

}
