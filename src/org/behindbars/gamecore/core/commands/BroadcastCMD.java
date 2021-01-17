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
import net.md_5.bungee.api.ChatColor;

public class BroadcastCMD extends Command {
	
	private static String name = "broadcast";
	private static String description = "broadcast a message to the entire server";
	private static String usageMessage = "";
	private static List<String> aliases = Arrays.asList("bc");
	
	public BroadcastCMD() {
		super(name, description, usageMessage, aliases);
	}

	@Override
	public boolean execute(CommandSender sender, String commandLabel, String[] args) {
		if(sender instanceof ConsoleCommandSender) return true;
		Player player = (Player)sender;
		
		if(Main.getPlayerHandler(player).getRank() != 10) {
			player.sendMessage(Main.getColorHandler().noPermission);
			return true;
		}else if(args.length < 1) {
			player.sendMessage(Main.getColorHandler().usage + "/broadcast <message>");
			return true;
		}else {
			Main.getAnnouncer().broadcast(ChatColor.WHITE + ChatColor.ITALIC.toString() + String.join(" ", args));
		}
		return true;
	}

	@Override
	public List<String> tabComplete(CommandSender sender, String alias, String[] args) {
		List<String> list = new ArrayList<>();
		return list;
	}

}
