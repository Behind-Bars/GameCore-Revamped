/*
Author: XenoPyax
Github: https://github.com/XenoPyax
Discord: XenoPyax#5647
*/

package org.behindbars.gamecore.core.events;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.behindbars.gamecore.Main;
import org.behindbars.gamecore.core.util.GUI;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.WorldCreator;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import net.md_5.bungee.api.ChatColor;

public class PlayerJoin implements Listener {
	
	@EventHandler
	public void onJoin(PlayerJoinEvent event) {
		Player player = event.getPlayer();
		if(Main.getHandlers().containsKey(player.getUniqueId())) Main.getHandlers().get(player.getUniqueId()).sync(player.getUniqueId());
		if(Main.getPlayerHandler(player).isBanned()) {
			player.kickPlayer("§7Reason: " + Main.getPlayerHandler(player).getLastBanInfo().getReason() + 
					"\n§7Banned until: §6" + new SimpleDateFormat("dd/MM/yyyy HH:mm").format(new Date(Main.getPlayerHandler(player).getLastBanInfo().getBannedTo())));
		}
		if(new WorldCreator("SMP").createWorld() != null) {
			if(!player.getWorld().getName().equals("SMP")) {
				player.teleport(Bukkit.getWorld("SMP").getSpawnLocation());
			}
		}
		player.addAttachment(Main.getInstance()).setPermission("worldedit.selection.pos", true);
		if(!Main.getPlayerHandler(player).isSetup()) Main.getPlayerHandler(player).setupPlayer();
		player.setCustomName(Main.getPlayerHandler(player).rankToString() + Main.getPlayerHandler(player).getNickname());
		player.setPlayerListName(Main.getPlayerHandler(player).rankToString() + Main.getPlayerHandler(player).getNickname());
		player.setWalkSpeed(0.2f);
		player.setFlySpeed(0.1f);

		player.sendMessage(ChatColor.DARK_RED + ChatColor.BOLD.toString() + "============ " + ChatColor.DARK_GRAY + "[ " + ChatColor.RED + ChatColor.BOLD + "Behind Bars" + ChatColor.DARK_GRAY + " ] " + ChatColor.DARK_RED + ChatColor.BOLD + "============");
		player.sendMessage("");
		player.sendMessage(ChatColor.RED + ChatColor.BOLD.toString() + ">> " + ChatColor.WHITE + ChatColor.ITALIC.toString() +"Remember to check /rules for updates!");
		player.sendMessage(ChatColor.RED + ChatColor.BOLD.toString() + ">> " + ChatColor.GRAY + ChatColor.ITALIC.toString() +"Share the ip address too while your at it!");
		player.sendMessage(ChatColor.RED + ChatColor.BOLD.toString() + ">> " + ChatColor.WHITE + ChatColor.ITALIC.toString() +"Type /help for help with commands!");
		player.sendMessage("");
		player.sendMessage(ChatColor.RED + ChatColor.BOLD.toString() + ">> " + ChatColor.GRAY + ChatColor.ITALIC.toString() + "IP: behind-bars.org");
		player.sendMessage(ChatColor.RED + ChatColor.BOLD.toString() + ">> " + ChatColor.WHITE + ChatColor.ITALIC.toString() + "Discord: https://discord.gg/smN32dP4H9");
		player.sendMessage("");




		player.setGameMode(GameMode.SURVIVAL);

		for(Player p : Bukkit.getOnlinePlayers()) {
			p.setCollidable(false);
			player.setCollidable(false);
		}
		
		player.setCollidable(false);
		
		if(Main.getPlayerHandler(player).isVanished() == 1) {
			event.setJoinMessage("");
			player.hidePlayer(Main.getInstance(), player);
			return;
		}

		Main.getPlayerHandler(player).setAcceptRules(1);
		
		if(Main.getPlayerHandler(player).getAcceptRules() == 0) {
			player.setFlySpeed(0f);
			player.setWalkSpeed(0f);
			player.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 1000000, 250));
			player.setAllowFlight(false);
			player.sendMessage("");
			player.sendMessage(Main.getColorHandler().main + ChatColor.BOLD.toString() + "Welcome back " + player.getName() 
				+ " chat will be disabled until you accept the rules. If you clicked escape, type /rules to try again");
			player.sendMessage("");

			if(!player.isDead()) {
				new BukkitRunnable() {
					@Override
					public void run() {
						GUI.rulesInv(player);
					}
				}.runTaskLater(Main.getInstance(), 20);
			}
		}

		if(!player.hasPlayedBefore()) {
			event.setJoinMessage(ChatColor.YELLOW + ChatColor.BOLD.toString() + "Welcome " + player.getName());

			Location cspawn = new Location(Bukkit.getWorld("SMP"), 32527.5, 75, -15558.5);
			//Location cspawn = new Location(Bukkit.getWorld("world"), -0.5,138,198.5);

			player.teleport(cspawn);

			return;
		} else {
			event.setJoinMessage(ChatColor.GREEN + ChatColor.BOLD.toString() + "+ " + ChatColor.GRAY + event.getPlayer().getName());
		}
	}

}
