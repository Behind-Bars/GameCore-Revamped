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

public class FlyCMD extends Command {

	private static String name = "fly";
	private static String description = "";
	private static String usageMessage = "";
	private static List<String> aliases = Arrays.asList("");

	public FlyCMD() {
		super(name, description, usageMessage, aliases);
	}

	@Override
	public boolean execute(CommandSender sender, String commandLabel, String[] args) {
		if (sender instanceof ConsoleCommandSender) return true;
		Player player = (Player) sender;
		
		if(Main.getPlayerHandler(player).getRank() != 10) {
			player.sendMessage(Main.getColorHandler().noPermission);
		}else if(args.length != 2) {
			if(player.getAllowFlight()) {
				player.setAllowFlight(false);
				player.sendMessage("Disabled Flight");
			} else {
				player.setAllowFlight(true);
				player.sendMessage("Enabled Flight");
			}
		}else {
			Player target = Bukkit.getPlayer(args[1]);
			if(target == null) {
				player.sendMessage(Main.getColorHandler().offlinePlayer);
			}else if(target.getAllowFlight()) {
				target.setAllowFlight(false);
				player.sendMessage(Main.getColorHandler().main + "Fly: " + Main.getColorHandler().message + "Disabled " + target.getName() + "'s flight!");
				target.sendMessage(Main.getColorHandler().main + "Fly: " + Main.getColorHandler().message + "Your flight has been disabled!");
			} else {
				target.setAllowFlight(true);
				player.sendMessage(Main.getColorHandler().main + "Fly: " + Main.getColorHandler().message + "Enabled " + target.getName() + "'s flight!");
				target.sendMessage(Main.getColorHandler().main + "Fly: " + Main.getColorHandler().message + "Your flight has been enabled!");
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
