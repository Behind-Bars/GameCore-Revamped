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

public class ReferCMD extends Command {

	private static String name = "refer";
	private static String description = "";
	private static String usageMessage = "";
	private static List<String> aliases = Arrays.asList("");

	public ReferCMD() {
		super(name, description, usageMessage, aliases);
	}

	@Override
	public boolean execute(CommandSender sender, String commandLabel, String[] args) {
		if (sender instanceof ConsoleCommandSender) return true;
		Player player = (Player) sender;
		
		if (args[0].equalsIgnoreCase("list")) {
			if (Main.getPlayerHandler(player).getHasReferred() == 0) {
				player.sendMessage(Main.getColorHandler().main + "Refer: " + Main.getColorHandler().message + "You have not used your referral!");
				player.sendMessage(Main.getColorHandler().main + "Refer: " + Main.getColorHandler().message + "You currently have " 
						+ String.valueOf(Main.getPlayerHandler(player).getReferrals()) + " referrals!");
			}else if (Main.getPlayerHandler(player).getHasReferred() == 1) {
				player.sendMessage(Main.getColorHandler().main + "Refer: " + Main.getColorHandler().message + "You have already used your referral!");
				player.sendMessage(Main.getColorHandler().main + "Refer: " + Main.getColorHandler().message + "You currently have " 
						+ String.valueOf(Main.getPlayerHandler(player).getReferrals()) + " referrals!");
			}
		}else {	
			if(args.length != 2) {
				player.sendMessage(Main.getColorHandler().usage + args[0] + " <player>");
			}else if(Main.getPlayerHandler(player).getHasReferred() == 1) {
				player.sendMessage(Main.getColorHandler().main + "Refer: " + Main.getColorHandler().message + "You have already referred a player!");
			}else {
				Player target = Bukkit.getPlayer(args[1]);
				
				if(target == null) {
					player.sendMessage(Main.getColorHandler().offlinePlayer + "This player is offline!");
				}else if(target.getName().equals(player.getName())) {
					player.sendMessage(Main.getColorHandler().error + "You can not refer yourself!");
				}else {
		
					Main.getPlayerHandler(player).setHasReferred(1);
					Main.getPlayerHandler(target).setReferrals(Main.getPlayerHandler(target).getReferrals() + 1);
			
					target.sendMessage(Main.getColorHandler().vote + "You have received a referral from " + player.getName() + "!");
					player.sendMessage(Main.getColorHandler().vote + "You have given " + target.getName() + " a referral!");
			
					if(Main.getPlayerHandler(target).getReferrals() == 5) {
						Bukkit.getServer().broadcastMessage(Main.getColorHandler().main + "Refer: " + Main.getColorHandler().message + target.getName() + " has referred 5 people!");
					}else if(Main.getPlayerHandler(target).getReferrals() == 10) {
						Bukkit.getServer().broadcastMessage(Main.getColorHandler().main + "Refer: " + Main.getColorHandler().message + target.getName() + " has referred 10 people!");
					}
				}
			}
	
		}
		return true;
	}

	@Override
	public List<String> tabComplete(CommandSender sender, String alias, String[] args) {
		List<String> list = new ArrayList<>();
		if (sender instanceof ConsoleCommandSender) return list;
		Player player = (Player) sender;
		list.add("list");
		Bukkit.getOnlinePlayers().forEach((p) -> {
			if(p != player) {
				list.add(p.getName());
			}
		});
		return list;
	}

}