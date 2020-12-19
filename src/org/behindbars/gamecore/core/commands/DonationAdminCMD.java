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

public class DonationAdminCMD extends Command {

	private static String name = "donationadmin";
	private static String description = "";
	private static String usageMessage = "";
	private static List<String> aliases = Arrays.asList("donationadmiun");

	public DonationAdminCMD() {
		super(name, description, usageMessage, aliases);
	}

	@Override
	public boolean execute(CommandSender sender, String commandLabel, String[] args) {
		if (sender instanceof ConsoleCommandSender) return true;
		Player player = (Player) sender;

		if(Main.getPlayerHandler(player).getRank() < 10) {
			player.sendMessage(Main.getColorHandler().noPermission);
		}else if(args.length != 4) {
			player.sendMessage(Main.getColorHandler().usage + args[0] + " add/remove <player> <donation>");
		}else {
			Player target = Bukkit.getPlayerExact(args[2]);
			if(target == null) {
				player.sendMessage(Main.getColorHandler().offlinePlayer + "This player is offline!");
			}else {
				try {
					int num = Integer.parseInt(args[3]);
		
					if(num < 1 || num > 10) {
						player.sendMessage(Main.getColorHandler().error + "Must be numbers 1-10, check /donate to see the relating number");
					}else if(args[1].equalsIgnoreCase("add")) {
						player.sendMessage(Main.getColorHandler().donation + Main.getColorHandler().message + "Added donation " + String.valueOf(args[3]) + " to " + target.getName());
						target.sendMessage(Main.getColorHandler().donation + Main.getColorHandler().message + "Donation added " + String.valueOf(args[3]) + " by " + player.getName());
			
						switch(num) {
						case 1: //Hat
							Main.getPlayerHandler(target).setDonateHat(1);
							break;
						case 2:	//PVP
							Main.getPlayerHandler(target).setDondatePvpStatus(1);
							break;
						case 3: //Nickname
							Main.getPlayerHandler(target).setDonateNickname(1);
							break;
						case 4: //Nickname
							Main.getPlayerHandler(target).setDonateFeed(1);
							break;
						case 5: //Nickname
							Main.getPlayerHandler(target).setDonateBail(1);
							break;
						case 6: //PVP
							Main.getPlayerHandler(target).setDonateNameColor(1);
							break;
						case 7: //PVP
							Main.getPlayerHandler(target).setDonateSpawn(1);
							break;
						case 8: //PVP
							Main.getPlayerHandler(target).setDonateStats(1);
							break;
						case 9: //PVP
							Main.getPlayerHandler(target).setDonateTrails(1);
							break;
						case 10: //PVP
							Main.getPlayerHandler(target).setDonateDiscoArmor(1);
							break;
						}		
					}else if(args[1].equalsIgnoreCase("remove")) {
						player.sendMessage(Main.getColorHandler().donation + Main.getColorHandler().message + "Removed donation " + String.valueOf(args[3]) + " to " + target.getName());
						target.sendMessage(Main.getColorHandler().donation + Main.getColorHandler().message + "Donation removed " + String.valueOf(args[3]) + " by " + player.getName());
			
						switch(num) {
						case 1: //Hat
							Main.getPlayerHandler(player).setDonateHat(0);
							break;
						case 2:	//PVP
							Main.getPlayerHandler(player).setDondatePvpStatus(0);
							break;
						case 3: //Nickname
							Main.getPlayerHandler(target).setDonateNickname(0);
							break;
						case 4: //Nickname
							Main.getPlayerHandler(target).setDonateFeed(0);
							break;
						case 5: //Nickname
							Main.getPlayerHandler(target).setDonateBail(0);
							break;
						case 6: //PVP
							Main.getPlayerHandler(target).setDonateNameColor(0);
							break;
						case 7: //PVP
							Main.getPlayerHandler(target).setDonateSpawn(0);
							break;
						case 8: //PVP
							Main.getPlayerHandler(target).setDonateStats(0);
						case 9: //PVP
							Main.getPlayerHandler(target).setDonateTrails(0);
							break;
						case 10: //PVP
							Main.getPlayerHandler(target).setDonateDiscoArmor(0);
							break;
						}
					}
				} catch (NumberFormatException ex){
					player.sendMessage(Main.getColorHandler().nonNumber);
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
		if(args.length == 1) {
			list.addAll(Arrays.asList("add", "remove"));
		}else if(args.length == 2) {
			Bukkit.getOnlinePlayers().forEach((p) -> {
				if(p != player) list.add(p.getName());
			});
		}
		return list;
	}

}