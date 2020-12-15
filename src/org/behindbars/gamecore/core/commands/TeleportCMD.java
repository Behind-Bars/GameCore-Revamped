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

public class TeleportCMD extends Command {

	private static String name = "";
	private static String description = "";
	private static String usageMessage = "";
	private static List<String> aliases = Arrays.asList("");

	public TeleportCMD() {
		super(name, description, usageMessage, aliases);
	}

	@Override
	public boolean execute(CommandSender sender, String commandLabel, String[] args) {
		if (sender instanceof ConsoleCommandSender) return true;
		Player player = (Player) sender;
		
		if(Main.getPlayerHandler(player).getRank() < 10) {
			player.sendMessage(Main.getColorHandler().noPermission);
		}else if(args.length == 1) {
			player.sendMessage(Main.getColorHandler().usage + args[0] +" <player> [player]");
		}else if(args.length == 2) { //teleport 1
			Player target = Bukkit.getPlayer(args[1]);
			
			if(target == null) {
				player.sendMessage(Main.getColorHandler().offlinePlayer);
			}else {
				player.teleport(target);
				player.sendMessage(Main.getColorHandler().main + "Teleport: " + Main.getColorHandler().message + "teleporting to " + target.getName() + "!");
				target.sendMessage(Main.getColorHandler().main + "Teleport: " + Main.getColorHandler().message + player.getName() + " is teleporting to you!");
			}
		}else if(args.length == 3) { //teleport 1
			Player target1 = Bukkit.getPlayer(args[1]);
			
			if(target1 == null) {
				player.sendMessage(Main.getColorHandler().offlinePlayer);
			} else {
				Player target2 = Bukkit.getPlayer(args[2]);
				if(target2 == null) {
					player.sendMessage(Main.getColorHandler().offlinePlayer);
				}else {
					target1.teleport(target2);
					target1.sendMessage(Main.getColorHandler().main + "Teleport: " + Main.getColorHandler().message + target1.getName() + " teleporting to " + target2.getName() + "!");
					target2.sendMessage(Main.getColorHandler().main + "Teleport: " + Main.getColorHandler().message + target1.getName() + " teleporting to " + target2.getName() + "!");
				}
			}
		}else {
			player.sendMessage(Main.getColorHandler().usage + args[0] +" <player> [player]");
		}
		return true;
	}

	@Override
	public List<String> tabComplete(CommandSender sender, String alias, String[] args) {
		List<String> list = new ArrayList<>();
		if(args.length == 1 || args.length == 2) {
			Bukkit.getOnlinePlayers().forEach((p) -> list.add(p.getName()));
		}
		return list;
	}

}