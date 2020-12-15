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
import org.bukkit.entity.Player;

public class GamemodeCMD extends Command {

	private static String name = "gamemode";
	private static String description = "";
	private static String usageMessage = "";
	private static List<String> aliases = Arrays.asList("gm");

	public GamemodeCMD() {
		super(name, description, usageMessage, aliases);
	}

	@Override
	public boolean execute(CommandSender sender, String commandLabel, String[] args) {
		if (sender instanceof ConsoleCommandSender)	return true;
		Player player = (Player) sender;

		if(Main.getPlayerHandler(player).getRank() < 10) {
			player.sendMessage(Main.getColorHandler().noPermission);
		}else if(!(args.length == 2)) {
			player.sendMessage(Main.getColorHandler().usage + args[0] + " <gamemode>");
		}else if(Main.getPlayerHandler(player).getDonateDiscoArmorStatus() == 1) {
			player.sendMessage(Main.getColorHandler().error + "You can not preform this command in disco armor!");
		}else if(args[1].equalsIgnoreCase("creative")
				|| args[1].equalsIgnoreCase("c")
				|| args[1].equalsIgnoreCase("1")) {
			player.setGameMode(GameMode.CREATIVE);
			player.sendMessage(Main.getColorHandler().main + "Gamemode: " +Main.getColorHandler().message + "You have updated your gamemode to " + args[1] + "!");
		} else if(args[1].equalsIgnoreCase("survival")
				|| args[1].equalsIgnoreCase("s")
				|| args[1].equalsIgnoreCase("0")) {
			player.setGameMode(GameMode.SURVIVAL);
			player.sendMessage(Main.getColorHandler().main + "Gamemode: "+Main.getColorHandler().message + "You have updated your gamemode to " + args[1] + "!");
		}else if(args[1].equalsIgnoreCase("adventure")
				|| args[1].equalsIgnoreCase("a")
				|| args[1].equalsIgnoreCase("2")) {
			player.setGameMode(GameMode.ADVENTURE);
			player.sendMessage(Main.getColorHandler().main + "Gamemode: " +Main.getColorHandler().message+ "You have updated your gamemode to " + args[1] + "!");
		} else if(args[1].equalsIgnoreCase("spectator")
				|| args[1].equalsIgnoreCase("spec")
				|| args[1].equalsIgnoreCase("3")) {
			player.setGameMode(GameMode.SPECTATOR);
			player.sendMessage(Main.getColorHandler().main + "Gamemode: "+Main.getColorHandler().message + "You have updated your gamemode to " + args[1] + "!");
		} else {
			player.sendMessage(Main.getColorHandler().error + "Invalid Gamemode!");
		}
		return true;
	}

	@Override
	public List<String> tabComplete(CommandSender sender, String alias, String[] args) {
		List<String> list = new ArrayList<>();
		list.addAll(Arrays.asList("creative", "survival", "spectator", "adventure"));
		return list;
	}

}
