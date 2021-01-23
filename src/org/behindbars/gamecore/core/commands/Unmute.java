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

public class Unmute extends Command {

	private static String name = "unmute";
	private static String description = "";
	private static String usageMessage = "";
	private static List<String> aliases = Arrays.asList("");

	public Unmute() {
		super(name, description, usageMessage, aliases);
	}

	@Override
	public boolean execute(CommandSender sender, String commandLabel, String[] args) {
		if (sender instanceof ConsoleCommandSender) return true;
		Player player = (Player) sender;

		if (Main.getPlayerHandler(player).getRank() < 8) {
			player.sendMessage(Main.getColorHandler().noPermission);
		}else if (args.length != 1) {
			player.sendMessage(Main.getColorHandler().usage + "/unmute <player>");
		} else {
			Player target = Bukkit.getPlayerExact(args[0]);

			if (target == null) {
				player.sendMessage(Main.getColorHandler().offlinePlayer);
			}else {
				player.sendMessage(Main.getColorHandler().antiCheat + "Unmuted " + target.getName());
				player.sendMessage(Main.getColorHandler().antiCheat + "Unmuted by " + player.getName());
				Main.getPlayerHandler(target).removeMute();
			}
		}
		return true;
	}

	@Override
	public List<String> tabComplete(CommandSender sender, String alias, String[] args) {
		List<String> list = new ArrayList<>();
		if (sender instanceof ConsoleCommandSender)return list;
		Player player = (Player) sender;
		return list;
	}

}