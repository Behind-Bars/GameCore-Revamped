/*
Author: XenoPyax
Github: https://github.com/XenoPyax
Discord: XenoPyax#5647
*/

package io.github.xenopyax.xenoapi.api;

import org.bukkit.plugin.Plugin;

// TODO: Auto-generated Javadoc
/**
 * The Class XenoHandler.
 */
public class XenoHandler {
	
	/** The plugin. */
	private Plugin plugin;
	
	/**
	 * Instantiates a new xeno handler.
	 *
	 * @param plugin the plugin
	 */
	public XenoHandler(Plugin plugin) {
		this.plugin = plugin;
	}
	
	/**
	 * Gets the question API.
	 *
	 * @return the question API
	 */
	public QuestionAPI getQuestionAPI() {
		return new QuestionAPI(plugin);
	}
	
	/**
	 * Gets the databae API.
	 *
	 * @return the databae API
	 */
	public DatabaseAPI getDatabaeAPI() {
		return new DatabaseAPI(plugin);
	}
	
	/**
	 * Gets the messenger.
	 *
	 * @param prefix the prefix
	 * @return the messenger
	 */
	public Messenger getMessenger(String prefix) {
		return new Messenger(prefix);
	}
	
	/**
	 * Gets the config manager.
	 *
	 * @return the config manager
	 */
	public ConfigManager getConfigManager() {
		return new ConfigManager(plugin);
	}

}
