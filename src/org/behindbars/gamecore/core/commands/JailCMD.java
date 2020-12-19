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
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

public class JailCMD extends Command {

	private static String name = "jail";
	private static String description = "";
	private static String usageMessage = "";
	private static List<String> aliases = Arrays.asList("");

	public JailCMD() {
		super(name, description, usageMessage, aliases);
	}

	@Override
	public boolean execute(CommandSender sender, String commandLabel, String[] args) {
		if (sender instanceof ConsoleCommandSender) return true;
		Player player = (Player) sender;
		
		if(Main.getPlayerHandler(player).getRank() < 6) {
            player.sendMessage(Main.getColorHandler().noPermission);
        }else if(args.length != 2) {
            player.sendMessage(Main.getColorHandler().usage + args[0] + " <player>");
        }else {
	        Player target = Bukkit.getPlayerExact(args[1]);
	        if(target == null) {
	            player.sendMessage(Main.getColorHandler().offlinePlayer);
	        }else {
	        	player.sendMessage(Main.getColorHandler().main + "Jail: " + Main.getColorHandler().message + "Jailed " + target.getName() + " for 5 minutes!");
		        target.sendMessage(Main.getColorHandler().main + "Jail: " + Main.getColorHandler().message + "You have been jailed for five minutes!");
		        Main.getPlayerHandler(target).setJailTime(300);
		        Location jail = new Location(Bukkit.getWorld("world"), 14.5,125,-27.5);
		        target.teleport(jail);
		        Main.getPlayerHandler(target).setInJail(1);
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