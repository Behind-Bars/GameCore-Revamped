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

public class InvSeeCMD extends Command {

	private static String name = "invsee";
	private static String description = "";
	private static String usageMessage = "";
	private static List<String> aliases = Arrays.asList("");

	public InvSeeCMD() {
		super(name, description, usageMessage, aliases);
	}

	@Override
	public boolean execute(CommandSender sender, String commandLabel, String[] args) {
		if (sender instanceof ConsoleCommandSender)return true;
		Player player = (Player) sender;
		if(args.length == 2) {
			if(Main.getPlayerHandler(player).getRank() == 10 && args[0].equalsIgnoreCase("echest")) player.openInventory(Bukkit.getPlayerExact(args[1]).getEnderChest());
			if(Main.getPlayerHandler(player).getRank() == 10 && args[0].equalsIgnoreCase("inv")) player.openInventory(Bukkit.getPlayerExact(args[1]).getInventory());
		}
		return true;
	}

	@Override
	public List<String> tabComplete(CommandSender sender, String alias, String[] args) {
		List<String> list = new ArrayList<>();
		if (sender instanceof ConsoleCommandSender) return list;
		Player player = (Player) sender;
		Bukkit.getOnlinePlayers().forEach((p)-> list.add(p.getName()));
		return list;
	}

}