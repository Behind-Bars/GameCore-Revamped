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

import net.md_5.bungee.api.ChatColor;

public class VanishCMD extends Command {

	private static String name = "vanish";
	private static String description = "";
	private static String usageMessage = "";
	private static List<String> aliases = Arrays.asList("v");

	public VanishCMD() {
		super(name, description, usageMessage, aliases);
	}

	@Override
	public boolean execute(CommandSender sender, String commandLabel, String[] args) {
		if (sender instanceof ConsoleCommandSender) return true;
		Player player = (Player) sender;
		
		if(Main.getPlayerHandler(player).getRank() < 10) {
			player.sendMessage(Main.getColorHandler().noPermission);
		}else if(Main.getPlayerHandler(player).isVanished() == 1) {
			Main.getPlayerHandler(player).setVanished(0);
			Bukkit.getOnlinePlayers().forEach((p) -> p.showPlayer(Main.getInstance(), player));
			Bukkit.getServer().broadcastMessage(ChatColor.GREEN + ChatColor.BOLD.toString() + "+ " + ChatColor.GRAY + player.getName());
		}else if(Main.getPlayerHandler(player).isVanished() == 0) {
			Main.getPlayerHandler(player).setVanished(1);
			Bukkit.getOnlinePlayers().forEach((p) -> p.hidePlayer(Main.getInstance(), player));
			Bukkit.getServer().broadcastMessage(ChatColor.RED + ChatColor.BOLD.toString() + "- " + ChatColor.GRAY + player.getName());
		}else {
			player.sendMessage(Main.getColorHandler().donation + "You must take off your current hat!");
		}
		return true;
	}

	@Override
	public List<String> tabComplete(CommandSender sender, String alias, String[] args) {
		List<String> list = new ArrayList<>();
		return list;
	}

}