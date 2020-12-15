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

public class PrivateMessageCM extends Command {

	private static String name = "message";
	private static String description = "";
	private static String usageMessage = "";
	private static List<String> aliases = Arrays.asList("msg", "tell");

	public PrivateMessageCM() {
		super(name, description, usageMessage, aliases);
	}

	@Override
	public boolean execute(CommandSender sender, String commandLabel, String[] args) {
		if (sender instanceof ConsoleCommandSender) return true;
		Player player = (Player) sender;
		
		if(args.length < 3) {
			player.sendMessage(Main.getColorHandler().usage + args[0] + " <player> <message>");
		}else {
			Player target = Bukkit.getPlayer(args[1]);
			
			if(target == null) {
				player.sendMessage(Main.getColorHandler().offlinePlayer + "This player is offline!");
			}else {
				String message = String.join(" ", Arrays.copyOfRange(args, 1, args.length));
				player.sendMessage(ChatColor.DARK_PURPLE + "[" + ChatColor.LIGHT_PURPLE + player.getName() + ChatColor.DARK_PURPLE + " > " + ChatColor.LIGHT_PURPLE 
						+ target.getName() + ChatColor.DARK_PURPLE + "]" + ChatColor.ITALIC + message);
				target.sendMessage(ChatColor.DARK_PURPLE + "[" + ChatColor.LIGHT_PURPLE + player.getName() + ChatColor.DARK_PURPLE + " > " + ChatColor.LIGHT_PURPLE 
						+ target.getName() + ChatColor.DARK_PURPLE + "]" + ChatColor.ITALIC + message);
		
				Main.getPlayerHandler(player).setLastMessaged(target.getName());
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
				if(p != player) {
					list.add(p.getName());
				}
			});
		}
		return list;
	}

}