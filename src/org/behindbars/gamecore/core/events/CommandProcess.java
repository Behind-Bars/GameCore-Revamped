/*
Author: XenoPyax
Github: https://github.com/XenoPyax
Discord: XenoPyax#5647
*/

package org.behindbars.gamecore.core.events;

import org.behindbars.gamecore.Main;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

public class CommandProcess implements Listener {
	
	String[] blacklist = {};
	
	@EventHandler
	public void onCommand(PlayerCommandPreprocessEvent e) {
		Player player = e.getPlayer();
		if(Main.getPlayerHandler(player).getCombatLogTime() > 0) e.setCancelled(true);
	}

}
