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

public class BailCMD extends Command {

	private static String name = "bail";
	private static String description = "";
	private static String usageMessage = "";
	private static List<String> aliases = Arrays.asList("");

	public BailCMD() {
		super(name, description, usageMessage, aliases);
	}

	@Override
	public boolean execute(CommandSender sender, String commandLabel, String[] args) {
		if (sender instanceof ConsoleCommandSender) return true;
		Player player = (Player) sender;
		
		if(Main.getPlayerHandler(player).getDonateBail() != 1) {
            player.sendMessage(Main.getColorHandler().noPermission);
        }else if(Main.getPlayerHandler(player).getMoney() < 5000) {
            player.sendMessage(Main.getColorHandler().main + "Jail: " + Main.getColorHandler().message + "You need $5000 to bail!");
        }else {
	        Main.getPlayerHandler(player).resetJailTime();
	        Main.getPlayerHandler(player).removeMoney(5000);
	        Location spawn = new Location(Bukkit.getWorld("world"), -0.5,125,-0.5);
	        player.teleport(spawn);
	        Main.getPlayerHandler(player).setInJail(0);
	        player.sendMessage(Main.getColorHandler().main + "Jail: " + Main.getColorHandler().message + "You have bailed yourself out of jail!");
        }
		return true;
	}

	@Override
	public List<String> tabComplete(CommandSender sender, String alias, String[] args) {
		List<String> list = new ArrayList<>();
		if (sender instanceof ConsoleCommandSender) return list;
		// Player player = (Player) sender;
		return list;
	}

}