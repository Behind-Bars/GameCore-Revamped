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

public class DonationPointCMD extends Command {

	private static String name = "donationpoint";
	private static String description = "";
	private static String usageMessage = "";
	private static List<String> aliases = Arrays.asList("");

	public DonationPointCMD() {
		super(name, description, usageMessage, aliases);
	}

	@Override
	public boolean execute(CommandSender sender, String commandLabel, String[] args) {
		if (sender instanceof ConsoleCommandSender) return true;
		Player player = (Player) sender;
		
		if(Main.getPlayerHandler(player).getRank() < 10) {
			player.sendMessage(Main.getColorHandler().noPermission);
		}else if(args.length != 3) {
			player.sendMessage(Main.getColorHandler().usage + "/donationpoint add/remove <player> <amount>");
		}else {
			Player target = Bukkit.getPlayer(args[1]);
			if(target == null) {
				player.sendMessage(Main.getColorHandler().offlinePlayer + "This player is offline!");
			}

			try {
				int num = Integer.parseInt(args[2]);
	
				if(args[0].equalsIgnoreCase("add")) {
					player.sendMessage(Main.getColorHandler().donation + "Added " + String.valueOf(args[2]) + " points to " + target.getName());
					Main.getPlayerHandler(target).addDonationPoints(Integer.parseInt(args[2]));
				}else if(args[0].equalsIgnoreCase("remove")) {
					if(num > Main.getPlayerHandler(target).getMoney()) {
						player.sendMessage(Main.getColorHandler().donation + target.getName() + " does not have enough donation points to remove!");
					}else {
						player.sendMessage(Main.getColorHandler().donation + "Removed $" + String.valueOf(args[2]) + " to " + target.getName());
						Main.getPlayerHandler(target).addDonationPoints(Integer.parseInt(args[2]));
					}
				}
			} catch (NumberFormatException ex){
				player.sendMessage(Main.getColorHandler().nonNumber);
			}
		}
		return true;
	}

	@Override
	public List<String> tabComplete(CommandSender sender, String alias, String[] args) {
		List<String> list = new ArrayList<>();
		return list;
	}

}