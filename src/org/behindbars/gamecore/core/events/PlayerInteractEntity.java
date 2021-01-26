/*
Author: XenoPyax
Github: https://github.com/XenoPyax
Discord: XenoPyax#5647
 */

package org.behindbars.gamecore.core.events;

import java.io.File;

import org.behindbars.gamecore.Main;
import org.behindbars.gamecore.core.util.GUI;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.EquipmentSlot;

import net.md_5.bungee.api.ChatColor;

public class PlayerInteractEntity implements Listener {

	@EventHandler
	public void playerInteractCrate(PlayerInteractEntityEvent event) {
		Player player = event.getPlayer();

		if(event.getRightClicked().isCustomNameVisible()) {

			if (event.getRightClicked().getCustomName().contains("Kit Tools")) {
				event.setCancelled(true);
				player.chat("/kit tools");
				return;
			}

			if (event.getRightClicked().getCustomName().contains("Wild TP")) {
				event.setCancelled(true);
				player.chat("/wild");
				return;
			}

		}


	/*	Player player = event.getPlayer();
		System.out.println("ran");
		if(event.getRightClicked().isCustomNameVisible()) {
			if (event.getRightClicked().getCustomName().contains(event.getPlayer().getName() + "'s Pet")) {
				event.getRightClicked().remove();
				event.getPlayer().getWorld().spawnParticle(Particle.CAMPFIRE_COSY_SMOKE,
						event.getPlayer().getLocation().getX(), event.getPlayer().getLocation().getY(),
						event.getPlayer().getLocation().getZ(), 0);
			}
			if (event.getRightClicked().getName().equalsIgnoreCase(ChatColor.RED + ChatColor.BOLD.toString() + "Stats")) {
				if (event.getHand().equals(EquipmentSlot.HAND)) {
					event.setCancelled(true);
					GUI.statsInv(player);
				}
			}
			if (event.getRightClicked().getName().equalsIgnoreCase(ChatColor.RED + ChatColor.BOLD.toString() + "Event")) {
				if (event.getHand().equals(EquipmentSlot.HAND)) {
					event.setCancelled(true);

					if (Main.getInfoHandler().getEventStatus() == 0) {
						player.sendMessage(Main.getColorHandler().error + "No current event!");
						return;
					} else if (Main.getInfoHandler().isLandfall() == 1) {
						Location landfallSpawn = new Location(Bukkit.getWorld("event"), 120.5, 24, -74.5, 0, 0);
						player.teleport(landfallSpawn);
						return;
					}
				}
			}
			if (event.getRightClicked().getName().equalsIgnoreCase(ChatColor.RED + ChatColor.BOLD.toString() + "Daily Reward")) {
				if (event.getHand().equals(EquipmentSlot.HAND)) {
					event.setCancelled(true);

					File f = new File("plugins/GameCore/PlayerData/" + player.getUniqueId() + ".yml");
					YamlConfiguration yml = YamlConfiguration.loadConfiguration(f);
					long elapsedTime = System.currentTimeMillis() - Main.getPlayerHandler(player).getDailyRewardDate();
					long timeUntilReuse = ((24 * 60 * 60 * 1000) - elapsedTime);
					if (timeUntilReuse > 0) {
						player.sendMessage(Main.getColorHandler().coolDown + Main.getTimeformatHandler().formatTime(timeUntilReuse));
						player.closeInventory();
						return;
					}

					player.sendMessage(Main.getColorHandler().main + "Daily Reward: " + Main.getColorHandler().message + "You have claimed your daily reward of $500 and 25 tokens!!");
					Main.getPlayerHandler(player).addMoney(500);
					Main.getPlayerHandler(player).addToken(25);
					Main.getPlayerHandler(player).setDailyRewardDate();
				}
			}
		}
		return; */



	}

}
