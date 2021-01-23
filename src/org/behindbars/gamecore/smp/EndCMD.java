/*
Author: XenoPyax

Github: https://github.com/XenoPyax
Discord: XenoPyax#5647
*/

package org.behindbars.gamecore.smp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

public class EndCMD extends Command {

	private static String name = "end";
	private static String description = "";
	private static String usageMessage = "";
	private static List<String> aliases = Arrays.asList("");

	public EndCMD() {
		super(name, description, usageMessage, aliases);
	}

	@Override
	public boolean execute(CommandSender sender, String commandLabel, String[] args) {
		if (sender instanceof ConsoleCommandSender) return true;
		Player player = (Player) sender;
		player.teleport(Bukkit.getWorld("world_the_end").getSpawnLocation());
		return true;
	}

	@Override
	public List<String> tabComplete(CommandSender sender, String alias, String[] args) {
		List<String> list = new ArrayList<>();
		if (sender instanceof ConsoleCommandSender) return list;
		Player player = (Player) sender;
		return list;
	}

}