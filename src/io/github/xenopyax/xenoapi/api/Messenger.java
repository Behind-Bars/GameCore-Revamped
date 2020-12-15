/*
Author: XenoPyax
Github: https://github.com/XenoPyax
Discord: XenoPyax#5647
*/
package io.github.xenopyax.xenoapi.api;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import net.md_5.bungee.api.ChatColor;

// TODO: Auto-generated Javadoc
/**
 * The Class Messenger.
 */
public class Messenger {
	
	/** The prefix. */
	private final String PREFIX;
	
	/**
	 * Instantiates a new messenger.
	 *
	 * @param prefix the prefix
	 */
	protected Messenger(String prefix) {
		this.PREFIX = prefix;
	}
	
	/**
	 * Send.
	 *
	 * @param player the player
	 * @param message the message
	 * @param args the args
	 */
	public void send(Player player, String message, Object... args) {
		String[] strings = new String[args.length];
		int index = 0;
		String lastColor = "§f";
		
		for(String s : message.split(" ")) {
			if(s.contains("§")) {
				lastColor = s.substring(s.indexOf("§"), s.indexOf("§")+2);
			}else if(s.contains("&")) {
				lastColor = s.substring(s.indexOf("&"), s.indexOf("&")+2);
			}else if(s.contains("%s")) {
				strings[index] = args[index].toString() + lastColor;
				index++;
			}
		}
		player.sendMessage(translateColor(PREFIX) + " " + translateColor(String.format(message, strings)));
	}
	
	/**
	 * Send.
	 *
	 * @param message the message
	 * @param args the args
	 */
	public void send(String message, Object... args) {
		String[] strings = new String[args.length];
		int index = 0;
		String lastColor = "§f";
		
		for(String s : message.split(" ")) {
			if(s.contains("§")) {
				lastColor = s.substring(s.indexOf("§"), s.indexOf("§")+2);
			}else if(s.contains("%s")) {
				strings[index] = args[index].toString() + lastColor;
				index++;
			}
		}
		Bukkit.getConsoleSender().sendMessage(translateColor(PREFIX) + " " + translateColor(String.format(message, strings)));
	}
	
	/**
	 * Translate color.
	 *
	 * @param message the message
	 * @return the string
	 */
	private String translateColor(String message) {
		String msg = "";
		for(String string : message.split(" ")) {
			if(msg.equals("")) {
				msg += string;
			}else if(string.startsWith("#") && string.length() >= 7) {
				msg += " " + ChatColor.of(string.substring(0, 7)) + (string.length() > 7 ? string.substring(7, string.length()) : "");
			}else {
				msg += " " + string;
			}
		}
		return ChatColor.translateAlternateColorCodes('&', msg);
	}

}
