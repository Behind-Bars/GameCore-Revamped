/*
Author: XenoPyax
Github: https://github.com/XenoPyax
Discord: XenoPyax#5647
*/

package org.behindbars.gamecore.core.events;

import org.behindbars.gamecore.Main;
import org.behindbars.gamecore.core.util.GUI;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.scheduler.BukkitRunnable;

public class PlayerRespawn implements Listener {
	
	@EventHandler
	public void onRespawn(PlayerRespawnEvent event) {
		Player player = event.getPlayer();
		Location cspawn = new Location(Bukkit.getWorld("SMP"), 32525,78,-15557);

		Location spawn = new Location(Bukkit.getWorld("world"), -0.5,125,-0.5);

		event.setRespawnLocation(cspawn);
		if(Main.getPlayerHandler(player).getAcceptRules() ==0) {
			new BukkitRunnable() {
				@Override
				public void run() {
					GUI.rulesInv(event.getPlayer());
				}
			}.runTaskLater(Main.getInstance(), 20);
		}		
	}

}
