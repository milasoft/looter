package milasoft.looter.context;

import org.dreambot.api.methods.MethodContext;

/**
 * This is a singleton accessor class to provide our GUI access to the api methods.
 * @author Milasoft
 */
public class ContextProvider {

	/**
	 * Instantiate an instance of the class.
	 */
	private static ContextProvider instance;
	
	/**
	 * Declare our MethodContext that we will be receiving.
	 */
	private MethodContext context;
	
	/**
	 * Hide our constructor so it can't be called outside of this class.
	 */
	private ContextProvider(){}
	
	/**
	 * Initialize our MethodContext with a passed MethodContext from the main script.
	 */
	public static void initialize(MethodContext context) {
		instance = new ContextProvider();
		instance.context = context;
	}

	/**
	 * Returns an instance of ContextProvider.
	 */
	public static ContextProvider getInstance() {
		return instance;
	}

	/**
	 * Returns an instance of our MethodContext.
	 */
	public MethodContext getContext() {
		return instance.context;
	}	
}
