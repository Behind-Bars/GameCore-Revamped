/*
Author: XenoPyax
Github: https://github.com/XenoPyax
Discord: XenoPyax#5647
*/

package org.behindbars.gamecore.core.handlers;

import org.behindbars.gamecore.Main;
import org.behindbars.gamecore.core.events.BlockBreak;
import org.behindbars.gamecore.core.events.CraftItem;
import org.behindbars.gamecore.core.events.PlaceBlock;
import org.behindbars.gamecore.core.events.PlayerBlockPhysics;
import org.behindbars.gamecore.core.events.PlayerJoin;
import org.behindbars.gamecore.core.events.PlayerLeave;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;

public class EventHandler {
	
	private PluginManager pluginManager;
	
	public EventHandler() {
		pluginManager = Bukkit.getPluginManager();
	}
	
	public void registerEvent(Listener... events)	{
		for(Listener event : events) {
			pluginManager.registerEvents(event, Main.getInstance());
		}
	}

	public void registerEvents() {
		registerEvent(new BlockBreak(), new CraftItem(), new PlayerBlockPhysics(), new PlayerJoin(), new PlayerLeave(), new PlaceBlock());
	}
}
