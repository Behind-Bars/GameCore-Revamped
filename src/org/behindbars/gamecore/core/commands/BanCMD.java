/*
Author: XenoPyax
Github: https://github.com/XenoPyax
Discord: XenoPyax#5647
*/

package org.behindbars.gamecore.core.commands;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.behindbars.gamecore.Main;
import org.behindbars.gamecore.core.data.BanInfo;
import org.behindbars.gamecore.core.handlers.PlayerHandler;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;

import net.md_5.bungee.api.ChatColor;

public class BanCMD implements TabExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (sender instanceof ConsoleCommandSender) return true;
		Player player = (Player) sender;
		if(args.length < 3 && args.length != 2) {
			player.sendMessage("§c/ban set <player> <1d/1w/1m/1y> <reason>");
			player.sendMessage("§c/ban get <player>");
			return true;
		}
		
		if(Main.getPlayerHandler(player).getRank() == 10) {
			if(args[0].equalsIgnoreCase("get")) {
				Player target = Bukkit.getPlayer(args[1]);
				OfflinePlayer otarget = Bukkit.getOfflinePlayer(args[1]);
				if(target == null && otarget == null) {
					player.sendMessage("§cPlayer is not online or have played on this server before.");
				}else if(target != null) {
					BanInfo info = Main.getPlayerHandler(target).getLastBanInfo();
					if(info != null) {
						SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm");
						player.sendMessage("§7Status §l" + (info.getBannedTo() > System.currentTimeMillis() ? "§4BANNED" : "§aUNBANNED")
								+ "\n§7Banned from: §6" + sdf.format(new Date(info.getBannedFrom())) 
								+ "\n§7Banned To: §6" + sdf.format(new Date(info.getBannedTo()))
								+ "\n§7Reason: §6" + info.getReason()
								+ "\n§7Vicitim: §6" + Bukkit.getPlayer(info.getVictim()).getName()
								+ "\n§7Victimizer: §6" + Bukkit.getOfflinePlayer(info.getVictimizer()).getName());
					}else {
						player.sendMessage("§cNo Information to display");
					}
				}else if(otarget != null) {
					BanInfo info = PlayerHandler.getLastBanInfo(otarget);
					if(info != null) {
						SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm");
						player.sendMessage("\n§8§m--------------------------------------"
								+ "§7Status §l" + (info.getBannedTo() > System.currentTimeMillis() ? "§4BANNED" : "§aUNBANNED")
								+ "\n§7Banned from: §6" + sdf.format(new Date(info.getBannedFrom())) 
								+ "\n§7Banned To: §6" + sdf.format(new Date(info.getBannedTo()))
								+ "\n§7Reason: §6" + info.getReason()
								+ "\n§7Vicitim: §6" + Bukkit.getPlayer(info.getVictim()).getName()
								+ "\n§7Victimizer: §6" + Bukkit.getOfflinePlayer(info.getVictimizer()).getName()
								+ "\n§8§m--------------------------------------\n");
					}else {
						player.sendMessage("§cNo Information to display");
					}
				}
			}
		}
			
		if(Main.getPlayerHandler(player).getRank() == 10) {
			if(args[0].equalsIgnoreCase("set")) {
				Player target = Bukkit.getPlayer(args[1]);
				OfflinePlayer otarget = Bukkit.getOfflinePlayer(args[1]);
				if(target == null && otarget == null) {
					player.sendMessage("§cPlayer is not online or have played on this server before.");
				}else if(target != null) {
					Main.getPlayerHandler(target).banPlayer(ChatColor.translateAlternateColorCodes('&', String.join(" ", Arrays.copyOfRange(args, 3, args.length))),
							getTime(args[2]), player);
					Bukkit.getPlayer(args[1]).kickPlayer("§7Reason: " + ChatColor.translateAlternateColorCodes('&', String.join(" ", Arrays.copyOfRange(args, 3, args.length))) + 
							"\n§7Banned  until: §6" + new SimpleDateFormat("dd/MM/yyyy HH:mm").format(new Date(getTime(args[2]))));
				}else if(otarget != null) {
					PlayerHandler.banPlayer(otarget, ChatColor.translateAlternateColorCodes('&', String.join(" ", Arrays.copyOfRange(args, 3, args.length))),
							getTime(args[2]), player);
				}
			}
		}
		return true;
	}

	private long getTime(String string) {
		long time = TimeUnit.DAYS.toMillis(1);
		if(string.length() != 2) return time + System.currentTimeMillis();
		switch(string.substring(1)) {
		case "d":
			time = TimeUnit.DAYS.toMillis(1);
			break;
		case "w":
			time = TimeUnit.DAYS.toMillis(7);
			break;
		case "m":
			time = TimeUnit.DAYS.toMillis(31);
			break;
		case "y":
			time = TimeUnit.DAYS.toMillis(365);
			break;
		}
		return time + System.currentTimeMillis();
	}

	@Override
	public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
		List<String> list = new ArrayList<>();
		if (sender instanceof ConsoleCommandSender) return list;
		Player player = (Player) sender;
		if(args.length == 1) {
			list.addAll(Arrays.asList("set","get"));
		}else if(args.length == 2) {
			Bukkit.getOnlinePlayers().forEach((p) -> {
				if(p != player) list.add(p.getName());
			});
		}else if(args.length == 3 && args[0].equalsIgnoreCase("set")) {
			list.addAll(Arrays.asList("1d", "1w", "1m", "1y"));
		}else if(args.length == 4 && args[0].equalsIgnoreCase("set")) {
			list.add("&cBanned for breaking rule #");
			list.add("&cBanned for hacking");
			list.add("&cBanned for ongoing investigation");
		}
		return list;
	}

}