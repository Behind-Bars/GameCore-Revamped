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
import org.behindbars.gamecore.core.handlers.PlayerHandler;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

public class UnbanCMD extends Command {

	private static String name = "unban";
	private static String description = "";
	private static String usageMessage = "";
	private static List<String> aliases = Arrays.asList("");

	public UnbanCMD() {
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
				if(PlayerHandler.isBanned(target.getUniqueId())) {
					PlayerHandler.deleteLastBan(target.getUniqueId());
					player.sendMessage("§a" + target.getName() + " has been unbanned.");
				}else {
					player.sendMessage("§c" + target.getName() + " is not banned.");
				}
			}
		}
		return true;
	}

	@Override
	public List<String> tabComplete(CommandSender sender, String alias, String[] args) {
		List<String> list = new ArrayList<>();
		if (sender instanceof ConsoleCommandSender) return list;
		return list;
	}

}