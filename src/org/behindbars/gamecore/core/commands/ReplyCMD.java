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

import net.md_5.bungee.api.ChatColor;

public class ReplyCMD extends Command {

	private static String name = "reply";
	private static String description = "";
	private static String usageMessage = "";
	private static List<String> aliases = Arrays.asList("r");

	public ReplyCMD() {
		super(name, description, usageMessage, aliases);
	}

	@Override
	public boolean execute(CommandSender sender, String commandLabel, String[] args) {
		if (sender instanceof ConsoleCommandSender)	return true;
		Player player = (Player) sender;
		
		if(args.length < 2) {
			player.sendMessage(Main.getColorHandler().usage + args[0] + " <message>");
		}else {
			Player target = Bukkit.getPlayer(Main.getPlayerHandler(player).getLastMessaged());
			
			if(target == null) {
				player.sendMessage(Main.getColorHandler().offlinePlayer);
			}else {
				String message = String.join(" ", args);
				player.sendMessage(ChatColor.DARK_PURPLE + "[" + ChatColor.LIGHT_PURPLE + player.getName() + ChatColor.DARK_PURPLE + " > " 
					+ ChatColor.LIGHT_PURPLE + target.getName() + ChatColor.DARK_PURPLE + "] " + ChatColor.ITALIC + message);
				target.sendMessage(ChatColor.DARK_PURPLE + "[" + ChatColor.LIGHT_PURPLE + player.getName() + ChatColor.DARK_PURPLE + " > " 
					+ ChatColor.LIGHT_PURPLE + target.getName() + ChatColor.DARK_PURPLE + "] " + ChatColor.ITALIC + message);
			}
		}
		return true;
	}

	@Override
	public List<String> tabComplete(CommandSender sender, String alias, String[] args) {
		List<String> list = new ArrayList<>();
		return list;
	}

}