/*
Author: XenoPyax
Github: https://github.com/XenoPyax
Discord: XenoPyax#5647
*/

package org.behindbars.gamecore.core.events;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.behindbars.gamecore.Main;
import org.behindbars.gamecore.trails.util.ParticleData;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import net.md_5.bungee.api.ChatColor;

public class PlayerLeave implements Listener {
	
	@EventHandler
	public void onLeave(PlayerQuitEvent event) {
		Player player = event.getPlayer();

		if(Main.getPlayerHandler(player).getCombatLogTime() > 0) {
			/*Bukkit.getServer().broadcastMessage(Main.getColorHandler().main + "CombatLog: " + Main.getColorHandler().message + player.getName()
				+ " has combat logged at location" + ChatColor.GRAY + " (" + player.getLocation().getBlockX() + "x " + player.getLocation().getBlockY() + "y " 
				+ player.getLocation().getBlockZ() + "z)" + Main.getColorHandler().message + "!");
			//player.setHealth(0);*/
		}
		Date now = new Date();
		SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");


		event.setQuitMessage(ChatColor.RED + ChatColor.BOLD.toString() + "- " + ChatColor.GRAY + event.getPlayer().getName());
		Main.getPlayerHandler(player).setSeen(format.format(now));

		World world = player.getWorld();
		List<Entity> entList = world.getEntities();

		for(Entity current : entList){
			if(!(current instanceof Player)) {
				if (current.getCustomName() != null) {
					if ((current.getCustomName()).contains(event.getPlayer().getName())) {
						current.remove();
					}
				}
			}
		}
		
		ParticleData p = new ParticleData(event.getPlayer().getUniqueId());
		if(p.hasID())
			p.endTask();
		
		return;
	}

}
