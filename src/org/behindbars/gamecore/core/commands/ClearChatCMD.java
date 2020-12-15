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

public class ClearChatCMD extends Command {

	private static String name = "clearchat";
	private static String description = "";
	private static String usageMessage = "";
	private static List<String> aliases = Arrays.asList("");

	public ClearChatCMD() {
		super(name, description, usageMessage, aliases);
	}

	@Override
	public boolean execute(CommandSender sender, String commandLabel, String[] args) {
		if (sender instanceof ConsoleCommandSender)	return true;
		Player player = (Player) sender;
		
		if(Main.getPlayerHandler(player).getRank() < 8) {
			player.sendMessage(Main.getColorHandler().noPermission);
		}else {
			for (int x = 0; x < 150; x++) Bukkit.getServer().broadcastMessage("");
	
			Bukkit.getServer().broadcastMessage(Main.getColorHandler().main + "Chat: " + Main.getColorHandler().message + player.getName() + " has cleared chat");
		}
		return true;
	}

	@Override
	public List<String> tabComplete(CommandSender sender, String alias, String[] args) {
		List<String> list = new ArrayList<>();
		return list;
	}

}
