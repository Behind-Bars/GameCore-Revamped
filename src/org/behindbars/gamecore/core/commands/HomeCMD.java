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
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

public class HomeCMD extends Command {

	private static String name = "home";
	private static String description = "";
	private static String usageMessage = "";
	private static List<String> aliases = Arrays.asList("");

	public HomeCMD() {
		super(name, description, usageMessage, aliases);
	}

	@Override
	public boolean execute(CommandSender sender, String commandLabel, String[] args) {
		if (sender instanceof ConsoleCommandSender) return true;
		Player player = (Player) sender;
		
		if (args[0].equalsIgnoreCase("set")) {
			if (Main.getPlayerHandler(player).getRank() < 5) {
				player.sendMessage(Main.getColorHandler().noPermission);
			}else if (!(args.length == 2)) {
				player.sendMessage(Main.getColorHandler().usage + "/sethome <home>");
			}else if(player.getWorld().getName().equalsIgnoreCase("world")) {
				player.sendMessage(Main.getColorHandler().error + "You can not set home in this world!");
			}else {
				Main.getPlayerHandler(player).setHome(args[1].toUpperCase());
			}
		}else if (args[0].equalsIgnoreCase("delete")) {
			if (Main.getPlayerHandler(player).getRank() < 5) {
				player.sendMessage(Main.getColorHandler().noPermission);
			}else if (!(args.length == 2)) {
				player.sendMessage(Main.getColorHandler().usage + "/delhome <home>");
			}else {
				Main.getPlayerHandler(player).delHome(args[1].toUpperCase());
			}
		}else if (args[0].equalsIgnoreCase("list")) {
			if (Main.getPlayerHandler(player).getRank() < 5) {
				player.sendMessage(Main.getColorHandler().noPermission);
			}else {
				Main.getPlayerHandler(player).getHomeList();
			}
		}else {
			if (Main.getPlayerHandler(player).getRank() < 5) {
				player.sendMessage(Main.getColorHandler().noPermission);
			}else if (!(args.length == 2)) {
				player.sendMessage(Main.getColorHandler().usage + "/home <name>");
			}else {
				Main.getPlayerHandler(player).teleportHome(args[1].toUpperCase());
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