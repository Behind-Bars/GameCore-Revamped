/*
Author: XenoPyax
Github: https://github.com/XenoPyax
Discord: XenoPyax#5647
*/

package io.github.xenopyax.xenoapi.api;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.plugin.Plugin;

// TODO: Auto-generated Javadoc
/**
 * The Class DatabaseAPI.
 */
public class DatabaseAPI {
	
	/** The plugin. */
	private Plugin plugin;
	
	/** The databases. */
	private List<Database> databases = new ArrayList<>();
	
	/**
	 * Instantiates a new database API.
	 *
	 * @param plugin the plugin
	 */
	protected DatabaseAPI(Plugin plugin) {
		this.plugin = plugin;
	}
	
	/**
	 * Gets the db.
	 *
	 * @param address the address
	 * @param port the port
	 * @param database the database
	 * @param username the username
	 * @param password the password
	 * @return the db
	 */
	public Database getDB(String address, int port, String database, String username, String password) {
		Database db = null;
		if(isDatabaseStored(address, port, database, username, password)) {
			db = getDatabaseFromMemory(address, port, database, username, password);
		}else {
			db = new Database(plugin, address, port, database, username, password);
		}
		return db;
	}
	
	/**
	 * Checks if is database stored.
	 *
	 * @param address the address
	 * @param port the port
	 * @param database the database
	 * @param username the username
	 * @param password the password
	 * @return true, if is database stored
	 */
	private boolean isDatabaseStored(String address, int port, String database, String username, String password) {
		for(Database db : databases) {
			if(db.getAddress().equals(address) && db.getDatabase().equals(database) && db.getPassword().equals(password) 
					&& db.getPort() == port && db.getUsername().equals(username)) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Gets the database from memory.
	 *
	 * @param address the address
	 * @param port the port
	 * @param database the database
	 * @param username the username
	 * @param password the password
	 * @return the database from memory
	 */
	private Database getDatabaseFromMemory(String address, int port, String database, String username, String password) {
		for(Database db : databases) {
			if(db.getAddress().equals(address) && db.getDatabase().equals(database) && db.getPassword().equals(password) 
					&& db.getPort() == port && db.getUsername().equals(username)) {
				return db;
			}
		}
		return null;
	}
	
}
