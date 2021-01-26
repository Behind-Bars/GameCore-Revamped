/*
Author: XenoPyax
Github: https://github.com/XenoPyax
Discord: XenoPyax#5647
*/
package org.behindbars.gamecore.core.commands;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.behindbars.gamecore.core.handlers.PlayerHandler;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

public class UnmutedCMD extends Command {

	private static String name = "unmute";
	private static String description = "";
	private static String usageMessage = "";
	private static List<String> aliases = Arrays.asList("");

	public UnmutedCMD() {
		super(name, description, usageMessage, aliases);
	}

	@Override
	public boolean execute(CommandSender sender, String commandLabel, String[] args) {
		if (sender instanceof ConsoleCommandSender) return true;
		Player player = (Player) sender;
		
		if(args.length == 1) {
			OfflinePlayer target = Bukkit.getOfflinePlayer(args[0]);
			if(target == null) {
				player.sendMessage("§c" + args[0] + " hasn't played on this server.");
			}else {
				if(PlayerHandler.isMuted(target.getUniqueId())) {
					PlayerHandler.deleteMute(target.getUniqueId());
					player.sendMessage("§a" + target.getName() + " has been unmuted.");
				}else {
					player.sendMessage("§c" + target.getName() + " is not muted.");
				}
			}
		}
		return true;
	}

	@Override
	public List<String> tabComplete(CommandSender sender, String alias, String[] args) {
		List<String> list = new ArrayList<>();
		if (sender instanceof ConsoleCommandSender)
			return list;
		Player player = (Player) sender;
		return list;
	}

}