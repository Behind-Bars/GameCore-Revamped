/*
Author: XenoPyax
Github: https://github.com/XenoPyax
Discord: XenoPyax#5647
*/

package org.behindbars.gamecore.core.commands;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.behindbars.gamecore.Main;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

public class KickCMD extends Command {

	private static String name = "kick";
	private static String description = "";
	private static String usageMessage = "";
	private static List<String> aliases = Arrays.asList("");

	public KickCMD() {
		super(name, description, usageMessage, aliases);
	}

	@Override
	public boolean execute(CommandSender sender, String commandLabel, String[] args) {
		if (sender instanceof ConsoleCommandSender) return true;
		Player player = (Player) sender;
		
		if (Main.getPlayerHandler(player).getRank() < 8) {
            player.sendMessage(Main.getColorHandler().noPermission);
        }else if (args.length < 3) {
            player.sendMessage(Main.getColorHandler().usage + "/kick <player> <reason>");
        }else {
	        Player target = Bukkit.getPlayerExact(args[1]);
	        if (target == null) {
	            player.sendMessage(Main.getColorHandler().offlinePlayer);
	        }else {
	        	target.kickPlayer("Kicked By: " + player.getName() + " for " + String.join(" ", Arrays.copyOfRange(args, 2, args.length)));
	        }
        }
		return true;
	}

	@Override
	public List<String> tabComplete(CommandSender sender, String alias, String[] args) {
		List<String> list = new ArrayList<>();
		if (sender instanceof ConsoleCommandSender)	return list;
		Player player = (Player) sender;
		if(args.length == 1) {
			Bukkit.getOnlinePlayers().forEach((p) -> {
				if(p != player) list.add(p.getName());
			});
		}
		return list;
	}

}