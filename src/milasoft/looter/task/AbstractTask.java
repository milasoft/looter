package milasoft.looter.task;

import org.dreambot.api.script.TaskNode;

import milasoft.looter.config.Config;

/**
 * This class extends Dreambots TaskNode to add more functionality to our scripts tasks.
 * Each task will extend this class to have access to its fields / methods.
 * @author Milasoft
 *
 */
public abstract class AbstractTask extends TaskNode {

	/**
	 * Obtain an instance of the Config class to use in our tasks.
	 */
	protected Config config = Config.getConfig();
		
	/**
	 * Checks whether the task should run.
	 */
	public abstract boolean accept();
	
	/**
	 * Executes the task if accept() returns true.
	 */
	public abstract int execute();
}
