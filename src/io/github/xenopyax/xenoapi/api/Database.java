/*
Author: XenoPyax
Github: https://github.com/XenoPyax
Discord: XenoPyax#5647
*/

package io.github.xenopyax.xenoapi.api;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.bukkit.plugin.Plugin;

// TODO: Auto-generated Javadoc
/**
 * The Class Database.
 */
public class Database {
	
	/** The con. */
	private Connection con;
	
	/** The plugin. */
	private Plugin plugin;
	
	/** The password. */
	private String address, database, username, password;
	
	/** The port. */
	private int port;
	
	/**
	 * Instantiates a new database.
	 *
	 * @param plugin the plugin
	 * @param address the address
	 * @param port the port
	 * @param database the database
	 * @param username the username
	 * @param password the password
	 */
	public Database(Plugin plugin, String address, int port, String database, String username, String password) {
		try {
			this.plugin = plugin;
			this.address = address;
			this.database = database;
			this.port = port;
			this.username = username;
			this.password = password;
			con = DriverManager.getConnection("jdbc:mysql://" + address + "/" + database, username, password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Gets the connection.
	 *
	 * @return the connection
	 */
	public Connection getConnection() {
		return con;
	}
	
	/**
	 * Close.
	 */
	public void close() {
		try {
			if(con != null) {
				con.close();
			}else {
				plugin.getServer().getConsoleSender().sendMessage("§a[XenoAPI] §cTried to close a DB connection that doesn't exist.");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Checks if is closed.
	 *
	 * @return true, if is closed
	 */
	public boolean isClosed() {
		try {
			if(con == null || con.isClosed()) return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	/**
	 * Gets the database.
	 *
	 * @return the database
	 */
	public String getDatabase() {
		return database;
	}

	/**
	 * Gets the address.
	 *
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * Gets the username.
	 *
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * Gets the password.
	 *
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Gets the port.
	 *
	 * @return the port
	 */
	public int getPort() {
		return port;
	}

}
