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
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;

public class GamemodeCMD implements TabExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (sender instanceof ConsoleCommandSender)	return true;
		Player player = (Player) sender;

		if(Main.getPlayerHandler(player).getRank() < 10) {
			player.sendMessage(Main.getColorHandler().noPermission);
		}else if(!(args.length == 1)) {
			player.sendMessage(Main.getColorHandler().usage + "/gamemode <gamemode>");
		}else if(Main.getPlayerHandler(player).getDonateDiscoArmorStatus() == 1) {
			player.sendMessage(Main.getColorHandler().error + "You can not preform this command in disco armor!");
		}else if(args[0].equalsIgnoreCase("creative")
				|| args[0].equalsIgnoreCase("c")
				|| args[0].equalsIgnoreCase("1")) {
			player.setGameMode(GameMode.CREATIVE);
			player.sendMessage(Main.getColorHandler().main + "Gamemode: " +Main.getColorHandler().message + "You have updated your gamemode to " + args[0] + "!");
		} else if(args[0].equalsIgnoreCase("survival")
				|| args[0].equalsIgnoreCase("s")
				|| args[0].equalsIgnoreCase("0")) {
			player.setGameMode(GameMode.SURVIVAL);
			player.sendMessage(Main.getColorHandler().main + "Gamemode: "+Main.getColorHandler().message + "You have updated your gamemode to " + args[0] + "!");
		}else if(args[0].equalsIgnoreCase("adventure")
				|| args[0].equalsIgnoreCase("a")
				|| args[0].equalsIgnoreCase("2")) {
			player.setGameMode(GameMode.ADVENTURE);
			player.sendMessage(Main.getColorHandler().main + "Gamemode: " +Main.getColorHandler().message+ "You have updated your gamemode to " + args[0] + "!");
		} else if(args[0].equalsIgnoreCase("spectator")
				|| args[0].equalsIgnoreCase("spec")
				|| args[0].equalsIgnoreCase("3")) {
			player.setGameMode(GameMode.SPECTATOR);
			player.sendMessage(Main.getColorHandler().main + "Gamemode: "+Main.getColorHandler().message + "You have updated your gamemode to " + args[0] + "!");
		} else {
			player.sendMessage(Main.getColorHandler().error + "Invalid Gamemode!");
		}
		return true;
	}

	@Override
	public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
		List<String> list = new ArrayList<>();
		list.addAll(Arrays.asList("creative", "survival", "spectator", "adventure"));
		return list;
	}

}
