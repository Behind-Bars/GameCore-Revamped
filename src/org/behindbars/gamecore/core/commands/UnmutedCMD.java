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
			Player target = Bukkit.getPlayer(args[0]);
			OfflinePlayer otarget = Bukkit.getOfflinePlayer(args[0]);
			if(target == null && otarget == null) {
				player.sendMessage("§cPlayer is not online or have played on this server before.");
			}else if(target != null) {
				Main.getPlayerHandler(target).deleteMute();
				player.sendMessage("\n§8§m--------------------------------------"
						+ "\n§6" + target.getName() + "§a has been unmuted."
						+ "\n§8§m--------------------------------------\n");
				target.sendMessage("\n§8§m--------------------------------------"
						+ "\n§aYou have been unmuted."
						+ "\n§8§m--------------------------------------\n");
			}else if(otarget != null) {
				PlayerHandler.deleteMute(otarget);
				player.sendMessage("\n§8§m--------------------------------------"
						+ "\n§6" + otarget.getName() + "§a has been unmuted."
						+ "\n§8§m--------------------------------------\n");
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