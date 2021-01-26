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
import org.behindbars.gamecore.core.data.MuteInfo;
import org.behindbars.gamecore.core.handlers.PlayerHandler;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

import net.md_5.bungee.api.ChatColor;

public class MuteCMD extends Command {

	private static String name = "mute";
	private static String description = "";
	private static String usageMessage = "";
	private static List<String> aliases = Arrays.asList("");

	public MuteCMD() {
		super(name, description, usageMessage, aliases);
	}

	@Override
	public boolean execute(CommandSender sender, String commandLabel, String[] args) {
		if (sender instanceof ConsoleCommandSender) return true;
		Player player = (Player) sender;
		
		if(args.length < 3 && args.length != 2) {
			player.sendMessage("§c/mute set <player> <1d/1w/1m/1y> <reason>");
			player.sendMessage("§c/mute get <player>");
			return true;
		}
		
		if(Main.getPlayerHandler(player).getRank() == 10) {
			if(args[0].equalsIgnoreCase("get")) {
				Player target = Bukkit.getPlayer(args[1]);
				OfflinePlayer otarget = Bukkit.getOfflinePlayer(args[1]);
				if(target == null && otarget == null) {
					player.sendMessage("§cPlayer is not online or have played on this server before.");
				}else if(target != null) {
					MuteInfo info = Main.getPlayerHandler(target).getMuteInfo();
					SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm");
					player.sendMessage("§7Status §l" + (info.getMutedTo() > System.currentTimeMillis() ? "§4MUTED" : "§aUNMUTED")
							+ "\n§7Muted from: §6" + sdf.format(new Date(info.getMutedFrom())) 
							+ "\n§7Muted To: §6" + sdf.format(new Date(info.getMutedTo()))
							+ "\n§7Reason: §6" + info.getReason()
							+ "\n§7Vicitim: §6" + Bukkit.getPlayer(info.getVictim()).getName()
							+ "\n§7Victimizer: §6" + Bukkit.getOfflinePlayer(info.getVictimizer()).getName());
				}else if(otarget != null) {
					MuteInfo info = PlayerHandler.getMuteInfo(otarget);
					SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm");
					player.sendMessage("§7Status §l" + (info.getMutedTo() > System.currentTimeMillis() ? "§4MUTED" : "§aUNMUTED")
							+ "\n§7Muted from: §6" + sdf.format(new Date(info.getMutedFrom())) 
							+ "\n§7Muted To: §6" + sdf.format(new Date(info.getMutedTo()))
							+ "\n§7Reason: §6" + info.getReason()
							+ "\n§7Vicitim: §6" + Bukkit.getPlayer(info.getVictim()).getName()
							+ "\n§7Victimizer: §6" + Bukkit.getOfflinePlayer(info.getVictimizer()).getName());
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
					Main.getPlayerHandler(target).mutePlayer(ChatColor.translateAlternateColorCodes('&', String.join(" ", Arrays.copyOfRange(args, 3, args.length))),
							getTime(args[2]), player);
					SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm");
					player.sendMessage("\n§8§m--------------------------------------"
							+ "\n§7Status §l§4MUTED"
							+ "\n§7Muted from: §6" + sdf.format(new Date(System.currentTimeMillis())) 
							+ "\n§7Muted To: §6" + sdf.format(new Date(getTime(args[2])))
							+ "\n§7Reason: §6" + String.join(" ", Arrays.copyOfRange(args, 3, args.length))
							+ "\n§7Vicitim: §6" + target.getName()
							+ "\n§7Victimizer: §6" + player.getName()
							+ "\n§8§m--------------------------------------\n");
				}else if(otarget != null) {
					PlayerHandler.mutePlayer(otarget, ChatColor.translateAlternateColorCodes('&', String.join(" ", Arrays.copyOfRange(args, 3, args.length))),
							getTime(args[2]), player);
					SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm");
					player.sendMessage("\n§8§m--------------------------------------"
							+ "\n§7Status §l§4MUTED"
							+ "\n§7Muted from: §6" + sdf.format(new Date(System.currentTimeMillis())) 
							+ "\n§7Muted To: §6" + sdf.format(new Date(getTime(args[2])))
							+ "\n§7Reason: §6" + String.join(" ", Arrays.copyOfRange(args, 3, args.length))
							+ "\n§7Vicitim: §6" + otarget.getName()
							+ "\n§7Victimizer: §6" + player.getName()
							+ "\n§8§m--------------------------------------\n");
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
	public List<String> tabComplete(CommandSender sender, String alias, String[] args) {
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
			list.add("&cMuted for breaking rule #");
			list.add("&cMuted for insulting");
		}
		return list;
	}

}