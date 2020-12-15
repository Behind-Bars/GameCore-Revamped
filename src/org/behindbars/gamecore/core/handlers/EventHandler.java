/*
Author: XenoPyax
Github: https://github.com/XenoPyax
Discord: XenoPyax#5647
*/

package org.behindbars.gamecore.core.handlers;

import org.behindbars.gamecore.Main;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;

public class EventHandler {
	
	private PluginManager pluginManager;
	
	public EventHandler() {
		pluginManager = Bukkit.getPluginManager();
	}
	
	public void registerEvent(Listener event)	{
		pluginManager.registerEvents(event, Main.getInstance());
	}
}
