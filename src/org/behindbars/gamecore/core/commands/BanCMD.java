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

import org.behindbars.gamecore.Main;
import org.behindbars.gamecore.core.data.BanInfo;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

public class BanCMD extends Command {

	private static String name = "bet";
	private static String description = "";
	private static String usageMessage = "";
	private static List<String> aliases = Arrays.asList("");

	public BanCMD() {
		super(name, description, usageMessage, aliases);
	}

	@Override
	public boolean execute(CommandSender sender, String commandLabel, String[] args) {
		if (sender instanceof ConsoleCommandSender) return true;
		Player player = (Player) sender;
		if(args.length < 3 && args.length != 2) {
			player.sendMessage("/bet <get/set> <player> <reason>");
			return true;
		}
		
		if(Main.getPlayerHandler(player).getRank() == 10) {
			if(args[0].equalsIgnoreCase("get")) {
				BanInfo info = Main.getPlayerHandler(Bukkit.getPlayer(args[1])).getLastBanInfo();
				if(info != null) {
					SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm");
					player.sendMessage("§7Banned from: §6" + sdf.format(new Date(info.getBannedFrom())) 
							+ "\n§7Banned To: §6" + sdf.format(new Date(info.getBannedTo()))
							+ "\n§7Reason: §6" + info.getReason()
							+ "\n§7Vicitim: §6" + Bukkit.getPlayer(info.getVictim()).getName()
							+ "\n§7Victimizer: §6" + Bukkit.getOfflinePlayer(info.getVictimizer()).getName());
				}else {
					player.sendMessage("No Info");
				}
			}
		}
			
		if(Main.getPlayerHandler(player).getRank() == 10) {
			if(args[0].equalsIgnoreCase("set")) {
				Main.getPlayerHandler(Bukkit.getPlayer(args[1])).banPlayer(String.join(" ", Arrays.copyOfRange(args, 2, args.length)), System.currentTimeMillis() + 100000, player);
			}
		}
		return true;
	}

	@Override
	public List<String> tabComplete(CommandSender sender, String alias, String[] args) {
		List<String> list = new ArrayList<>();
		if (sender instanceof ConsoleCommandSender) return list;
		Player player = (Player) sender;
		
		if(args.length == 1) {
			Bukkit.getOnlinePlayers().forEach((p) -> {
				if(p != player) list.add(p.getName());
			});
		}
		return list;
	}

}